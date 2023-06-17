package emu.nes.graphics;

import emu.nes.memory.ByteMemory;

/**
 * Object Attribute Memory accessible from the PPU.
 */
public class OAM extends ByteMemory {

    public OAM() {
        super(0x100);
    }

    /**
     * Returns an OAM entry by its index.
     * @param idx Entry index
     * @return An OAM entry
     */
    public Entry entry(final int idx) {
        final Entry result = new Entry();
        result.spriteY = this.read(idx * 4);
        result.spriteTile = this.read(idx * 4 + 1);
        result.spriteAttribute = this.read(idx * 4 + 2);
        result.spriteX = this.read(idx * 4 + 3);
        return result;
    }
}
