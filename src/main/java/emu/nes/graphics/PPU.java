package emu.nes.graphics;

import emu.nes.memory.Memory;
import java.util.ArrayList;
import java.util.List;

/**
 * PPU logic.
 * @author hdouss
 *
 */
public class PPU implements Memory {

    /**
     * PPU Bus.
     */
    private PPUBus bus;

    /**
     * PPU registers.
     */
    private PPURegisters registers;

    /**
     * PPU Object Attribute Memory.
     */
    private OAM oam;

    /**
     * Current cycle.
     */
    private int cycles = 0;

    /**
     * Current scanline.
     */
    private int scanline = 0;

    /**
     * Frame being currently drawn.
     */
    private Frame frame;

    /**
     * Output PPU debugging info.
     */
    private StringBuilder debug = new StringBuilder();

    /**
     * Non zero background pixels.
     */
    private boolean[] nonzero = new boolean[Picture.NES_WIDTH * Picture.NES_HEIGHT];

    /**
     * Builds a PPU with the passed PPU bus.
     * @param bus PPU bus
     */
    public PPU(final PPUBus bus) {
        this.bus = bus;
        this.frame = new Frame();
        this.oam = new OAM();
        this.registers = new PPURegisters();
    }

    @Override
    public byte read(final int addr) {
        return this.registers.read(addr, this);
    }

    @Override
    public void write(final int addr, final byte value) {
        this.registers.write(addr, value, this);
    }

    /**
     * Called on each clock tick. Return true if a frame is completed drawing.
     * @return Whether a frame finished drawing
     */
    public boolean tick() {
        this.cycles++;
        boolean result = false;
        if (this.cycles >= 257 && this.cycles <= 320 && this.scanline < 240) {
            this.registers.write(3, (byte) 0);
        }
        if (this.cycles < 256 && this.scanline >= 0 && this.scanline < 240) {
            final Mask mask = this.registers.getMask();
            this.frame.setMask(mask);
            if (mask.showBackground()) {
                this.renderBackground();
            }
            // Frame background = new Frame(this.frame);
            // background.setMask(mask);
            if (mask.showSprites()) {
                this.renderForeground(null);// background);
            }
        }
        if (this.cycles >= 341) {
            this.cycles = 0;
            this.scanline++;
            if (this.scanline == 241) {
                this.registers.setVBlanck();
                result = true;
            }
            if (this.scanline >= 261) {
                this.scanline = -1;
                this.registers.unsetVBlanck();
                this.registers.unsetSpriteZeroHit();
                this.nonzero = new boolean[Picture.NES_WIDTH * Picture.NES_HEIGHT];
                this.frame = new Frame();
            }
        }
        return result;
    }

    /**
     * Returns whether NMI should be issued at vblank.
     * @return A boolean indicating if an NMI should be issued at vblank.
     */
    public boolean nmiVBlank() {
        return this.registers.getControl().isNMIAtVblank();
    }

    /**
     * Renders background.
     */
    private void renderBackground() {
        if (this.cycles < 8 && !this.registers.getMask().showLeftBackground()) {
            return;
        }
        int hscroll = this.registers.getHorizontalScroll();
        int vscroll = this.registers.getVerticalScroll();
        int cursor = this.registers.getControl().getNametableAddress();
        int xcor = (hscroll + this.cycles) % (Picture.NES_WIDTH * 2);
        int ycor = (vscroll + this.scanline) % (Picture.NES_HEIGHT * 2);
        cursor += (xcor / Picture.NES_WIDTH) * 0x400 + (ycor / Picture.NES_HEIGHT) * 0x800;
        // Coordinates relative to selected nametable (cursor)
        // which has the same size as the screen
        int xcorRel = xcor % Picture.NES_WIDTH;
        int ycorRel = ycor % Picture.NES_HEIGHT;
        // tile index in memory
        int tileIndex = (xcorRel / 8) + 32 * (ycorRel / 8);
        int tileNum = this.bus.read(cursor + tileIndex) & 0xFF;
        // attributes index
        int attrIndex = (tileIndex / 4) % 8 + (tileIndex / 128) * 8;
        // attributes address
        int attrAddr = cursor + 0x03C0 + attrIndex;
        byte attributes = this.bus.read(attrAddr);
        // tile position inside the 16*16 tile (bottom right is 3, top left is 0)
        int pos = (tileIndex % 4) / 2 + 2 * (((tileIndex / 32) % 4) / 2);
        int color = (attributes >> (pos * 2)) & 3;
        Tile tile = this.bus.getTile(
            this.registers.getControl().getBackgroundTableBank(), tileNum
        );
        final byte pixel = tile.getPixel(xcorRel % 8, ycorRel % 8);
        final int index = this.cycles + Picture.NES_WIDTH * this.scanline;
        if (pixel > 0) {
            this.nonzero[index] = true;
        } else {
            color = 0;
        }
        this.frame.setNESColor(
            this.cycles, this.scanline, this.bus.read(
                0x3F00 + 4 * color + pixel
                )
        );
    }

    /**
     * Renders all CHR content.
     */
    private void renderCHR() {
        int bank = 0;
        int tileIndex = 0;
        while (tileIndex < 256 || bank < 1) {
            if (tileIndex == 256) {
                bank = 1;
                tileIndex = 0;
            }
            Tile tile = this.bus.getTile(bank, tileIndex);

            int startx = bank * 8 * 16 + ((tileIndex) % 16) * 8;
            int starty = ((tileIndex) / 16) * 8;

            for (int pixelx = 0; pixelx < 8; ++pixelx) {
                for (int pixely = 0; pixely < 8; ++pixely) {
                    final byte pixel = tile.getPixel(pixelx, pixely);
                    this.frame.setNESColor(startx + pixelx, starty + pixely, 16 + pixel * 3);
                }
            }
            tileIndex++;
        }
    }

    /**
     * Renders sprites.
     * @param background Rendered background
     */
    private void renderForeground(final Frame background) {
        for (int idx = 63; idx > 0; --idx) {
            this.renderSprite(idx, background);
        }
        List<Integer> spriteNonzeros = this.renderSprite(0, background);
        for (Integer pixel : spriteNonzeros) {
            if (this.nonzero[pixel]) {
                this.registers.setSpriteZeroHit();
                break;
            }
        }
    }

    /**
     * Renders an individual sprite and reports the non zero pixels of this sprite.
     * @param idx Sprite index
     * @param background Rendered background
     * @return A list of screen pixels that are non zero
     */
    private List<Integer> renderSprite(int idx, Frame background) {
        List<Integer> result = new ArrayList<>(64);
        Entry entry = this.oam.entry(idx);
        if (this.cycles < entry.spriteX || this.cycles > entry.spriteX + 8
            || this.scanline < entry.spriteY || this.scanline > entry.spriteY + 8) {
            return result;
        }
        /*
         * if(this.cycles != entry.spriteX || this.scanline != entry.spriteY) { return result; }
         */
        final Control control = this.registers.getControl();
        int bank = control.getSpriteTableAddress();
        if (control.getSpriteSize() == 16) {
            bank = entry.spriteTile % 2;
        }
        if (control.getSpriteSize() == 16) {
            if (entry.isFlippedVertically()) {
                entry.spriteTile = (byte) (entry.spriteTile + 1);
                result.addAll(this.renderEntry(entry, bank, background));
                entry.spriteY = entry.spriteY + 8;
                entry.spriteTile = entry.spriteTile - 1;
                result.addAll(this.renderEntry(entry, bank, background));
                entry.spriteY = entry.spriteY - 8;
            } else {
                result.addAll(this.renderEntry(entry, bank, background));
                entry.spriteY = entry.spriteY + 8;
                entry.spriteTile = entry.spriteTile + 1;
                result.addAll(this.renderEntry(entry, bank, background));
                entry.spriteY = entry.spriteY - 8;
                entry.spriteTile = entry.spriteTile - 1;
            }
        } else {
            result.addAll(this.renderEntry(entry, bank, background));
        }
        return result;
    }

    /**
     * Renders an OAM entry.
     * @param entry Entry to render
     * @param bank CHR bank to select tile from
     * @param background Rendered background
     * @return A list of screen pixels that are non zero
     */
    private List<Integer> renderEntry(Entry entry, int bank, Frame background) {
        List<Integer> result = new ArrayList<>(64);
        final Mask mask = this.registers.getMask();
        Tile tile = this.bus.getTile(bank, entry.spriteTile);
        int startx = entry.spriteX;
        int starty = entry.spriteY;
        int color = 4 * entry.getPalette();
        for (int pixelx = 0; pixelx < 8; ++pixelx) {
            for (int pixely = 0; pixely < 8; ++pixely) {
                final byte pixel = tile.getPixel(pixelx, pixely);
                final int xcor = startx + (entry.isFlippedHorizontally() ? 8 - pixelx : pixelx);
                final int ycor = starty + (entry.isFlippedVertically() ? 8 - pixely : pixely);
                if (xcor < 0 || xcor > Picture.NES_WIDTH - 1 || ycor < 0
                    || ycor > Picture.NES_HEIGHT - 1) {
                    continue;
                }
                if (xcor > 7 || mask.showLeftSprites()) {
                    if (pixel > 0) {
                        result.add(xcor + Picture.NES_WIDTH * ycor);
                        if (entry.getPriority() || !this.nonzero[xcor + ycor * Picture.NES_WIDTH]) {
                            this.frame.setNESColor(
                                xcor, ycor, this.bus.read(0x3F10 + color + pixel)
                            );
                        } else {
                            System.out.println("wtd1");
                            // this.frame.setColor(xcor, ycor, background.getColor(xcor, ycor));
                        }
                    } else {
                        System.out.println("wtd2");
                        // this.frame.setColor(xcor, ycor, background.getColor(xcor, ycor));
                    }
                }
            }
        }
        return result;
    }

    private void fillAttrs(Byte[] attrs, int index, byte attributes) {
        for (int row = 0; row < 4; ++row) {
            for (int colum = 0; colum < 4; ++colum) {
                final int tileIndex = colum + 32 * row;
                if (tileIndex + index < 960 * 4) {
                    attrs[tileIndex + index] = (byte) (
                        (attributes >> (2 * (colum / 2)
                        + 4 * (row / 2))) & 0x3
                    );
                }
            }
        }

    }

    @Override
    public String toString() {
        return super.toString() + this.bus.toString() + this.oam.toString();
    }

    /**
     * Writes a byte to the OAM according to the OAM address.
     * @param data Data to write
     */
    public void writeOAM(final byte data) {
        this.oam.write(this.registers.getOamAddress() & 0xFF, data);
        this.registers.write(0x2003, (byte) (this.registers.getOamAddress() + 1));
    }

    /**
     * Accessor for the PPU bus.
     * @return PPUBUus
     */
    public PPUBus bus() {
        return this.bus;
    }

    /**
     * Accessor for the frame drawn.
     * @return Drawn frame
     */
    public Frame getFrame() {
        return this.frame;
    }

    /**
     * Turns the PPU off. Resets registers.
     */
    public void off() {
        this.cycles = 0;
        this.scanline = 0;
        this.registers.reset();
    }

    /**
     * Accessor for the written debugging information.
     * @return Debugging info
     */
    public StringBuilder getDebug() {
        return this.debug;
    }

}
