package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * SEC operation.
 * @author hdouss
 *
 */
public class SetCarry implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        registers.getStatus().setCarry(true);
        return 0;
    }

}
