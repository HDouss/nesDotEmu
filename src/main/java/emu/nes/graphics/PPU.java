package emu.nes.graphics;

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

    public PPU(final PPUBus bus) {
        this.bus = bus;
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

    public void tick() {
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
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + this.bus.toString() + this.oam.toString();
    }

    public void writeOAM(final byte read) {
        // TODO Auto-generated method stub
        
    }

    
}
