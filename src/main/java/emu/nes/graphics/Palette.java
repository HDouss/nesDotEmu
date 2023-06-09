package emu.nes.graphics;

import emu.nes.Memory;

/**
 * Palette memory accessible from the PPU bus.
 */
public class Palette implements Memory {

    byte[] data = new byte[0x20];

    @Override
    public byte read(int addr) {
        return this.data[(addr - 0x3F00) % 0x20];
    }

    @Override
    public void write(int addr, byte value) {
        this.data[(addr - 0x3F00) % 0x20] = value;
    }

}
