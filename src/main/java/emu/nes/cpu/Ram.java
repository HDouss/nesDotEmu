package emu.nes.cpu;

import emu.nes.Memory;

/**
 * NES RAM logic.
 * @author hdouss
 *
 */
public class Ram implements Memory {

    private int[] data = new int[2048];

    @Override
    public int read(int addr) {
        return this.data[addr & 0x7ff];
    }

    @Override
    public void write(int addr, int value) {
        this.data[addr & 0x7ff] = value;
    }

}
