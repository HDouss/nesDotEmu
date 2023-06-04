package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * CLI operation.
 * @author hdouss
 *
 */
public class ClearInterrupt implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        registers.getStatus().setIntDisable(false);
        return 0;
    }

}
