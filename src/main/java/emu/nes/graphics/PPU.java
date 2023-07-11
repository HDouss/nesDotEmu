package emu.nes.graphics;

import java.util.ArrayList;
import java.util.List;
import emu.nes.memory.Memory;

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
        return this.registers.read(addr);
    }

    @Override
    public void write(final int addr, final byte value) {
        this.registers.write(addr, value, this);
    }

    /**
     * Called on each clock tick. Return true if a NMI cpu interrupt should occur.
     * @return Whether a NMI cpu interrupt should occur.
     */
    public boolean tick() {
        this.cycles ++;
        if (this.cycles >= 257 && this.cycles <= 320 && this.scanline < 240) {
            this.registers.write(3, (byte) 0);
        }
        if (this.cycles >= 341) {
            this.cycles = 0;
            this.scanline ++;
            if (this.scanline == 241) {
                final Mask mask = this.registers.getMask();
                this.frame.setMask(mask);
                long now = System.currentTimeMillis();
                if (mask.showBackground()) {
                    this.renderBackground();
                }
                Frame background = new Frame(this.frame);
                background.setMask(mask);
                long back = System.currentTimeMillis();
                if (mask.showSprites()) {
                    this.renderForeground(background);
                }
                //this.renderCHR();
                long fore = System.currentTimeMillis();
                /*this.debug.append(
                    String.format(
                        "backgroung rendering took %s ms. foreground rendering took %s ms. \n",
                        back - now, fore - back
                    )
                );*/
                this.registers.setVBlanck();
            }
            if (this.scanline >= 261) {
                this.scanline = -1;
                this.registers.unsetVBlanck();
                this.registers.unsetSpriteZeroHit();
                this.nonzero = new boolean[Picture.NES_WIDTH * Picture.NES_HEIGHT];
            }
        }
        return this.registers.getControl().isNMIAtVblank();
    }

    /**
     * Renders background.
     */
    private void renderBackground() {
        final Mask mask = this.registers.getMask();
        int cursor = this.registers.getControl().getNametableAddress();
        int attrAddr = cursor + 0x03C0;
        byte attributes = this.bus.read(attrAddr);
        Byte[] attrs = new Byte[960];
        this.fillAttrs(attrs, 0, attributes);
        int tileIndex = 0;
        int hscroll = this.registers.getHorizontalScroll();
        int vscroll = this.registers.getVerticalScroll();
        while (tileIndex < 960) {
            int tileNum = this.bus.read(cursor);
            Tile tile = this.bus.getTile(
                this.registers.getControl().getBackgroundTableBank(), tileNum
            );
            if (tileIndex % 4 == 0 && attrs[tileIndex] == null) {
                attrAddr++;
                attributes = this.bus.read(attrAddr);
                this.fillAttrs(attrs, tileIndex, attributes);
            }
            int startx = (tileIndex % 32) * 8;
            int starty = (tileIndex / 32) * 8;
            int color = 4 * attrs[tileIndex];
            for (int pixelx = 0; pixelx < 8; ++pixelx) {
                for (int pixely = 0; pixely < 8; ++pixely) {
                    final byte pixel = tile.getPixel(pixelx, pixely);
                    final int xcor = startx + pixelx - hscroll;
                    final int ycor = starty + pixely - vscroll;
                    if(xcor > 7 || mask.showLeftBackground()) {
                        final int index = xcor + Picture.NES_WIDTH * ycor;
                        if (pixel > 0 && index > -1 && index < Picture.NES_WIDTH * Picture.NES_HEIGHT) {
                            this.nonzero[index] = true;
                        }
                        this.frame.setNESColor(xcor, ycor, this.bus.read(0x3F00 + color + pixel));
                    }
                }
            }
            cursor++;
            tileIndex++;
        }
    }

    /**
     * Renders all CHR content.
     */
    private void renderCHR() {
        int bank = 0;
        int tileIndex = 0;
        while (tileIndex < 256 || bank < 1) {
            if(tileIndex == 256) {
                bank = 1;
                tileIndex = 0;
            }
            Tile tile = this.bus.getTile(bank, tileIndex);
            
            int startx = bank* 8 * 16 + ((tileIndex)%16)*8;
            int starty = ((tileIndex )/ 16) * 8;
            
            for (int pixelx = 0; pixelx < 8; ++pixelx) {
                for (int pixely = 0; pixely < 8; ++pixely) {
                    final byte pixel = tile.getPixel(pixelx, pixely);
                        this.frame.setNESColor(startx + pixelx, starty + pixely, 16+pixel*3);
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
                if(xcor < 0 || xcor > Picture.NES_WIDTH -1 || ycor < 0 || ycor > Picture.NES_HEIGHT -1) {
                    continue;
                }
                if(xcor > 7 || mask.showLeftSprites()) {
                    if (pixel > 0) {
                        result.add(xcor + Picture.NES_WIDTH * ycor);
                        if (entry.getPriority()) {
                            this.frame.setNESColor(xcor, ycor, this.bus.read(0x3F10 + color + pixel));
                        } else {
                            this.frame.setColor(xcor, ycor, background.getColor(xcor, ycor));
                        }
                    } else {
                        this.frame.setColor(xcor, ycor, background.getColor(xcor, ycor));
                    }
                }
            }
        }
        return result;
    }

    private void fillAttrs(Byte[] attrs, int index, byte attributes) {
        for(int row = 0; row < 4; ++row) {
            for(int colum = 0; colum < 4; ++colum) {
                final int tileIndex = colum + 32 * row;
                if (tileIndex + index < 960) {
                    attrs[tileIndex + index] = (byte) (
                        (attributes >> (2 * (colum / 2) + 4 * (row / 2))) & 0x3
                    );
                }
            }
        }
        
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
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
    public Frame getFrame() {
        return this.frame;
    }

    public void off() {
        this.cycles = 0;
        this.scanline = 0;
        this.registers.reset();
    }

    public StringBuilder getDebug() {
        return debug;
    }

}
