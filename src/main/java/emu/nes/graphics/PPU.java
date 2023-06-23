package emu.nes.graphics;

import java.util.Random;
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
        this.registers.write(addr, value, this.bus);
    }

    public boolean tick() {
        boolean result = false;
        // Modify PPUStatus: Sprite 0 Hit.
        //   Set when a nonzero pixel of sprite 0 overlaps a nonzero background pixel;
        //   cleared at dot 1 of the pre-render line.  Used for raster timing.
        // Modify PPUStatus: Vertical blank has started (0: not in vblank; 1: in vblank).
        //   Set at dot 1 of line 241 (the line *after* the post-render
        //   line); cleared at dot 1 of the
        //   pre-render line.
        // PPUStatus: Sprite 0 hit is not detected at x=255, nor is it detected at x=0 through 7 if the background or sprites are hidden in this area.
        /*
         * OAMADDR is set to 0 during each of ticks 257–320 (the sprite tile loading interval) of the pre-render and visible scanlines.
         * If rendering is enabled mid-scanline, there are further consequences of an OAMADDR that was not set to 0 before OAM sprite evaluation begins
         * at tick 65 of the visible scanline.
         * The value of OAMADDR at this tick determines the starting address for sprite evaluation for this scanline,
         * which can cause the sprite at OAMADDR to be treated as it was sprite 0, both for sprite-0 hit and priority.
         * If OAMADDR is unaligned and does not point to the Y position (first byte) of an OAM entry,
         * then whatever it points to (tile index, attribute, or X coordinate) will be reinterpreted as a Y position.
         */
        /*
         * Sprite 0 hit does not happen:

If background or sprite rendering is disabled in PPUMASK ($2001)
At x=0 to x=7 if the left-side clipping window is enabled (if bit 2 or bit 1 of PPUMASK is 0).
At x=255, for an obscure reason related to the pixel pipeline.
At any pixel where the background or sprite pixel is transparent
(2-bit color index from the CHR pattern is %00).
If sprite 0 hit has already occurred this frame. Bit 6 of PPUSTATUS ($2002)
is cleared to 0 at dot 1 of the pre-render line.
This means only the first sprite 0 hit in a frame can be detected.
Sprite 0 hit happens regardless of the following:

Sprite priority. Sprite 0 can still hit the background from behind.
The pixel colors. Only the CHR pattern bits are relevant, not the actual rendered colors,
and any CHR color index except %00 is considered opaque.
The palette. The contents of the palette are irrelevant to sprite 0 hits.
For example: a black ($0F) sprite pixel can hit a black ($0F) background
as long as neither is the transparent color index %00.
The PAL PPU blanking on the left and right edges at x=0, x=1, and x=254 (see Overscan).
         */
        /*
         * Priority between sprites is determined by their address inside OAM.
         * So to have a sprite displayed in front of another sprite in a scanline,
         * the sprite data that occurs first will overlap any other sprites after it.
         * For example, when sprites at OAM $0C and $28 overlap, the sprite at $0C will appear in front.
         */
        this.cycles ++;
        if (this.cycles >= 341) {
            this.cycles = 0;
            this.scanline ++;
            if (this.scanline >= 261) {
                this.scanline = -1;
                result = true;
            }
        }
        if(this.cycles < Picture.NES_WIDTH && this.scanline < Picture.NES_HEIGHT - 1) {
            this.frame.setColor(this.cycles, this.scanline + 1, new Random().nextInt(64));
        }
        return result;
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
        this.oam.write(this.registers.getOamAddress(), data);
    }

    public Frame getFrame() {
        return this.frame;
    }

    
}
