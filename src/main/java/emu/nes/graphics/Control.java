package emu.nes.graphics;

/**
 * PPU control register.
 */
public class Control {

    /**
     * PPU control register content. 
     */
    private byte content;

    /**
     * Builds a PPUCTRL register with the given data.
     * @param data Register data
     */
    public Control(final byte data) {
        this.content = data;
    }

    /**
     * Gives the VRAM address increment amount.
     * @return Address increment amount
     */
    public int addressIncrement() {
        return (this.content & 0x100) > 0 ? 32 : 1;
    }

    /**
     * Gives the nametable base address.
     * @return Nametable base address
     */
    public int getNametableAddress() {
        final int value = this.content & 0x3;
        int result = 0x2000;
        switch (value) {
            case 1: result = 0x2400; break;
            case 2: result = 0x2800; break;
            case 3: result = 0x2C00; break;
        }
        return result;
    }

    /**
     * Gives the sprite pattern table address.
     * @return Sprite pattern table address
     */
    public int getSpriteTableAddress() {
        final int bit = (this.content & 0x8) >> 3;
        return bit * 0x1000;
    }

    /**
     * Gives the background pattern table address.
     * @return Background pattern table address
     */
    public int getBackgroundTableAddress() {
        final int bit = (this.content & 0x10) >> 4;
        return bit * 0x1000;
    }

    /**
     * Gives the sprite size (8x16 or 8x8).
     * @return Sprite size returns 8 for 8x8 and 8x16
     */
    public int getSpriteSize() {
        final int bit = (this.content & 0x20) >> 5;
        return (bit + 1) * 8;
    }

    /**
     * Reports whether the PPU is in master mode (output color on EXT pins)
     * @return True if the PPU is in master mode
     */
    public boolean isMaster() {
        final int bit = (this.content & 0x40) >> 6;
        return bit > 0;
    }

    /**
     * Reports whether the PPU generates an NMI at the start of vBlank.
     * @return True if the PPU generates an NMI at the start of vBlank.
     */
    public boolean isNMIAtVblank() {
        final int bit = (this.content & 0x80) >> 7;
        return bit > 0;
    }
}
