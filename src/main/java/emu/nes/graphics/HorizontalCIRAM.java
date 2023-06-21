package emu.nes.graphics;

import emu.nes.memory.ByteMemory;

/**
 * Implements a CIRAM (nametable memory) with horizontal mirroring. 
 * @author hdouss
 *
 */
public class HorizontalCIRAM extends ByteMemory {

    /**
     * Horizontal CIRAM is a 2KB memory but mirroring is not straightforward.
     */
    public HorizontalCIRAM() {
        super(0x800);
    }

    @Override
    public byte read(final int addr) {
        return super.read((addr % 0x400) + 0x400 * (addr / 0x800));
    }

    @Override
    public void write(final int addr, final byte value) {
        super.write((addr % 0x400) + 0x400 * (addr / 0x800), value);
    }

}
