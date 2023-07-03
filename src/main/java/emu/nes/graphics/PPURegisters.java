package emu.nes.graphics;

import emu.nes.memory.ByteMemory;


/**
 * PPU registers. Handles all logic related to reading and writing from registers, including
 * double read/writes for PPUADDR and PPUSCROLL.
 */
public class PPURegisters extends ByteMemory {

    /**
     * PPUADDR register address.
     */
    private static final int PPUADDR = 6;

    /**
     * PPUDATA register address.
     */
    private static final int PPUDATA = 7;

    /**
     * PPUSTATUS register address.
     */
    private static final int PPUSTATUS = 2;

    /**
     * OAMADDR register address.
     */
    private static final int OAMADDR = 3;

    /**
     * OAMDATA register address.
     */
    private static final int OAMDATA = 4;

    /**
     * PPUSCROLL register address.
     */
    private static final int PPUSCROLL = 5;

    /**
     * Double writing latch.
     */
    private boolean latch = false;

    /**
     * Memory Address combined.
     */
    private int address = 0x0000;

    /**
     * Scroll horizontal value.
     */
    private int scrollx = 0;

    /**
     * Scroll vertical value.
     */
    private int scrolly = 0;

    public PPURegisters() {
        super(8);
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
     * Writing logic for handling special cases when writing to the PPUADDR, PPUDATA or OAMDATA.
     * @param addr Address to write to.
     * @param value Value to write.
     * @param bus PPU bus used to read/write data from VRAM.
     */
    public void write(final int addr, final byte value, final PPUBus bus) {
        super.write(addr, value);
        if (addr == PPURegisters.PPUADDR) {
            if (!this.latch) {
                this.address = (value & 0xFF) << 8;
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
        if (addr == PPURegisters.OAMDATA) {
            super.write(PPURegisters.OAMADDR, (byte) (super.read(PPURegisters.OAMADDR) + 1));
        }
        if (addr == PPURegisters.PPUSCROLL) {
            if (!this.latch) {
                this.scrollx = value;
            } else {
                this.scrolly = value;
            }
            this.latch = !this.latch;
        }
    }

    /**
     * Reports PPUCTRL data.
     * @return PPUCTRL data
     */
    public Control getControl() {
        return new Control(this.read(0));
    }

    /**
     * Reports PPUMASK data.
     * @return PPUMASK data
     */
    public Mask getMask() {
        return new Mask(this.read(1));
    }

    /**
     * Reports PPUSTATUS data. Probably unused.
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
     * Reports PPUSCROLL data. Probably unused.
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
     * Gets the stored horizontal scroll value.
     * @return Horizontal scroll value.
     */
    public int getHorizontalScroll() {
        return this.scrollx;
    }

    /**
     * Gets the stored vertical scroll value.
     * @return Vertical scroll value.
     */
    public int getVerticalScroll() {
        return this.scrolly;
    }

    public void reset() {
        byte reset = 0;
        for (int index = 0; index < 8; ++index) {
            this.write(index, reset);
        }
    }

    /**
     * Increments PPUADDRESS register by the amount specified in PPUCTRL register.
     */
    private void incrementAddress() {
        int increment = this.getControl().addressIncrement();
        this.address = this.address + increment;
        super.write(PPURegisters.PPUADDR, (byte) (this.address & 0xFF));
    }

    public void setVBlanck() {
        this.write(2, (byte) (this.getStatus() | 0x80));
    }

    public void unsetVBlanck() {
        this.write(2, (byte) (this.getStatus() & 0x7F));
    }
}
