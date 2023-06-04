package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * SEI operation.
 * @author hdouss
 *
 */
public class SetInterrupt implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        registers.getStatus().setIntDisable(true);
        return 0;
    }

}
