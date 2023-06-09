package emu.nes.graphics;

import emu.nes.Memory;

/**
 * Nametable memory accessible from the PPU bus.
 */
public class Nametable implements Memory {

    byte[] data = new byte[1024 * 4];

    @Override
    public byte read(int addr) {
        return this.data[(addr - 0x2000) % 0x1000];
    }

    @Override
    public void write(int addr, byte value) {
        this.data[(addr - 0x2000) % 0x1000] = value;
    }

}
