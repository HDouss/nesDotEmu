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

    public PPU(PPUBus bus) {
        this.bus = bus;
        this.oam = new OAM();
    }

    @Override
    public byte read(int addr) {
        return this.registers.read((addr - 0x2000) % 8);
    }

    @Override
    public void write(int addr, byte value) {
        this.registers.write((addr - 0x2000) % 8, value);
    }

    public void tick() {
        // TODO Auto-generated method stub

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + this.bus.toString() + this.oam.toString();
    }
}
