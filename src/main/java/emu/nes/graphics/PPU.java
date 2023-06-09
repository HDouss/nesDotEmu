package emu.nes.graphics;

import emu.nes.Memory;

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

}
