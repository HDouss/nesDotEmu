package emu.nes.graphics;

import emu.nes.memory.MirroredByteMemory;


/**
 * PPU registers. Handles all logic related to reading and writing from registers, including
 * double read/writes for PPUADDR and PPUSCROLL.
 */
public class PPURegisters extends MirroredByteMemory {

    /**
     * PPUADDR register address.
     */
    private static final int PPUADDR = 0x2006;

    /**
     * PPUDATA register address.
     */
    private static final int PPUDATA = 0x2007;

    /**
     * PPUSTATUS register address.
     */
    private static final int PPUSTATUS = 0x2002;

    /**
     * Memory Address latch.
     */
    private boolean latch = false;

    /**
     * Memory Address combined.
     */
    private int address = 0x0000;

    public PPURegisters() {
        super(8, 8);
    }

    @Override
    public byte read(final int addr) {
        final byte result = super.read(addr);
        if (addr == PPURegisters.PPUDATA) {
            this.incrementAddress();
        }
        if (addr == PPURegisters.PPUSTATUS) {
            this.write(addr, (byte) (result & 0x7F));
            this.latch = false;
        }
        return result;
    }

    /**
     * Writing logic for handling special cases when writing to the PPUADDR or to the PPUDATA.
     * @param addr Address to write to.
     * @param value Value to write.
     * @param bus PPU bus used to read/write data from VRAM.
     */
    public void write(final int addr, final byte value, final PPUBus bus) {
        super.write(addr, value);
        if (addr == PPURegisters.PPUADDR) {
            if (this.latch) {
                this.address = value << 8;
            } else {
                this.address = this.address + (value & 0xFF);
                super.write(PPURegisters.PPUDATA, bus.read(this.address));
            }
            this.latch = !this.latch;
        }
        if (addr == PPURegisters.PPUDATA) {
            bus.write(this.address, value);
            this.incrementAddress();
        }
    }

    /**
     * Reports PPUCTRL data.
     * @return PPUCTRL data
     */
    public byte getControl() {
        return this.read(0);
    }

    /**
     * Reports PPUMASK data.
     * @return PPUMASK data
     */
    public byte getMask() {
        return this.read(1);
    }

    /**
     * Reports PPUSTATUS data.
     * @return PPUSTATUS data
     */
    public byte getStatus() {
        return this.read(2);
    }

    /**
     * Reports OAMADDR data.
     * @return OAMADDR data
     */
    public byte getOamAddress() {
        return this.read(3);
    }

    /**
     * Reports OAMDATA data.
     * @return OAMDATA data
     */
    public byte getOamData() {
        return this.read(4);
    }

    /**
     * Reports PPUSCROLL data.
     * @return PPUSCROLL data
     */
    public byte getScroll() {
        return this.read(5);
    }

    /**
     * Reports PPUADDR data.
     * @return PPUADDR data
     */
    public byte getAddress() {
        return this.read(6);
    }

    /**
     * Reports PPUDATA data.
     * @return PPUDATA data
     */
    public byte getData() {
        return this.read(7);
    }

    /**
     * Increments PPUADDRESS register by the amount specified in PPUCTRL register.
     */
    private void incrementAddress() {
        int increment = (this.getControl() & 0x100) > 0 ? 32 : 1;
        this.address = this.address + increment;
        super.write(PPURegisters.PPUADDR, (byte) ((this.address & 0xFF) + increment));
    }
}
