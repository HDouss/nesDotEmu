package emu.nes.graphics;

import emu.nes.memory.ByteMemory;

public class Palette extends ByteMemory {

    /**
     * Builds a Palette as a byte memory with 25 entries.
     */
    public Palette() {
        super(32);
    }

    @Override
    public byte read(final int addr) {
        return super.read(this.translate(addr % 32));
    }

    @Override
    public void write(final int addr, final byte value) {
        super.write(this.translate(addr % 32), value);
    }

    /**
     * Translate the bus address to an internal index.
     * Takes into account mirroring in adresses $3F10, $3F14, $3F18 and $3F1C.
     * @param addr Bus Address
     * @return Internal index
     */
    private int translate(final int addr) {
        if (addr % 4 == 0 && addr > 15) {
            return addr - 16;
        }
        return addr;
    }

}
