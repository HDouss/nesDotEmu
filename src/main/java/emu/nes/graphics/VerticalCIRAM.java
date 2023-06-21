package emu.nes.graphics;

import emu.nes.memory.ByteMemory;

/**
 * Implements a CIRAM (nametable memory) with vertical mirroring. 
 * @author hdouss
 *
 */
public class VerticalCIRAM extends ByteMemory {

    /**
     * Builds a vertical CIRAM which can be seen as a 2KB mirrored memory.
     */
    public VerticalCIRAM() {
        super(0x800);
    }

}
