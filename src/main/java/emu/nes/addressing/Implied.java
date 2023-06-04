package emu.nes.addressing;

import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Implied for operations that do not need data or the data is implied from the instruction.
 * @author hdouss
 *
 */
public class Implied implements Addressing {

    @Override
    public AddressingResult address(Bus bus, Registers registers, int pc) {
        return new AddressingResult();
    }

}
