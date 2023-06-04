package emu.nes.addressing;

import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Addressing modes capabilities. Provides an {@link AddressingResult}.
 * @author hdouss
 *
 */
public interface Addressing {

    /**
     * 
     * @param bus NES Cpu bus.
     * @param registers Cpu registers.
     * @param pc Program counter adress where the instruction was read.
     *  This is different from the actual PC register value which could have been incremented before
     *  calling addressing mode.
     * @return Addressing result with data read, memory address fetched
     *  and whether a page boundary was crossed  
     */
    AddressingResult address(Bus bus, Registers registers, int pc);

}
