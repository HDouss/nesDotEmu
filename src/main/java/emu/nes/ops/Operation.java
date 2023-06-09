package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * Operation (instruction) interface.
 * @author hdouss
 *
 */
public interface Operation {

    /**
     * Executes the instructions and reports the additional cycle count needed for it.
     * @param registers CPU registers
     * @param bus CPU bus
     * @param res Addressing result of the corresponding addressing mode
     * @return Number of additional cycles needed to execute this operation
     */
    int execute(Registers registers, Bus bus, AddressingResult res);

    /**
     * Updates zero and negative flags according to the passed value.
     * @param registers CPU registers
     * @param data Data to check
     */
    default void updateFlags(Registers registers, int data) {
        final Status status = registers.getStatus();
        status.setZero((data & 0xFF) == 0);
        status.setNegative((data & 0x80) > 0);
    }
}
