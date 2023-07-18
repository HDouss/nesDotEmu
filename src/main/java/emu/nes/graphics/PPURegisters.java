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
     * VRAM Address combined.
     */
    private int vram = 0x0000;

    /**
     * Temporary latched address.
     */
    private int temp = 0x0000;

    /**
     * Scroll horizontal value.
     */
    private int scrollx = 0;

    /**
     * Scroll vertical value.
     */
    private int scrolly = 0;

    /**
     * Buffer of VRAM read data.
     */
    private byte buffer;

    /**
     * Builds a byte memory with 8 bytes.
     */
    public PPURegisters() {
        super(8);
    }

    /**
     * Makes a read and handle special cases for PPUDATA and PPUSTATUS read.
     * @param addr Address to read
     * @param ppu PPU
     * @return Data read
     */
    public byte read(final int addr, final PPU ppu) {
        byte result = super.read(addr);
        if (addr == PPURegisters.PPUDATA) {
            result = this.buffer;
            this.buffer = ppu.bus().read(this.vram);
            if (this.vram > 0x3EFF) {
                result = this.buffer;
            }
            System.out.println(String.format(
                "returning value %02X, buffer is %02X from address %04X",
                result, this.buffer, this.vram
            )
                );
            this.incrementAddress();
        }
        if (addr == PPURegisters.PPUSTATUS) {
            this.unsetVBlanck();
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
    public void write(final int addr, final byte value, final PPU ppu) {
        super.write(addr, value);
        if (addr == PPURegisters.PPUADDR) {
            if (!this.latch) {
                this.temp = ((value & 0x3F) << 8) + (this.temp & 0xFF);
            } else {
                this.temp = (this.temp & 0x7F00) | (value & 0xFF);
                this.vram = this.temp;
            }
            this.latch = !this.latch;
        }
        if (addr == PPURegisters.PPUDATA) {
            
            /*System.out.println(String.format(
                "writing value %02X  to address %04X",
                value, this.vram
            )
                );*/
            ppu.bus().write(this.vram, value);
            this.incrementAddress();
        }
        if (addr == PPURegisters.OAMDATA) {
            ppu.writeOAM(value);
        }
        if (addr == PPURegisters.PPUSCROLL) {
            //verify fine and coarse
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
        this.vram = this.vram + increment;
        super.write(PPURegisters.PPUADDR, (byte) (this.vram & 0xFF));
    }

    /**
     * Sets Vblank flag in PPUSTATUS.
     */
    public void setVBlanck() {
        this.write(2, (byte) (super.read(2) | 0x80));
    }

    /**
     * Clears Vblank flag in PPUSTATUS.
     */
    public void unsetVBlanck() {
        this.write(2, (byte) (super.read(2) & 0x7F));
    }

    /**
     * Sets Sprite 0 Hit flag in PPUSTATUS.
     */
    public void setSpriteZeroHit() {
        this.write(2, (byte) (super.read(2) | 0x40));
    }

    /**
     * Clears Sprite 0 Hit flag in PPUSTATUS.
     */
    public void unsetSpriteZeroHit() {
        this.write(2, (byte) (super.read(2) & 0xBF));
    }
}
