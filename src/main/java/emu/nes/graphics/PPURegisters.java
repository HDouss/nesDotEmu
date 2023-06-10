package emu.nes.graphics;

import emu.nes.memory.Memory;

/**
 * PPU registers.
 */
public class PPURegisters implements Memory {

    byte[] memory = new byte[8];

    @Override
    public byte read(int addr) {
        return this.memory[addr];
    }

    @Override
    public void write(int addr, byte value) {
        this.memory[addr] = value;
    }

    public byte getControl() {
        return this.memory[0];
    }

    public byte getMask() {
        return this.memory[1];
    }

    public byte getStatus() {
        return this.memory[2];
    }

    public byte getOamAddress() {
        return this.memory[3];
    }

    public byte getOamData() {
        return this.memory[4];
    }

    public byte getScroll() {
        return this.memory[5];
    }

    public byte getAddress() {
        return this.memory[6];
    }

    public byte getData() {
        return this.memory[7];
    }
}
