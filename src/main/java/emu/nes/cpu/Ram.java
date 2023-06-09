package emu.nes.cpu;

import emu.nes.Memory;

/**
 * NES RAM logic.
 * @author hdouss
 *
 */
public class Ram implements Memory {

    private byte[] data = new byte[2048];

    @Override
    public byte read(int addr) {
        return this.data[addr & 0x7ff];
    }

    @Override
    public void write(int addr, byte value) {
        this.data[addr & 0x7ff] = value;
    }

}
