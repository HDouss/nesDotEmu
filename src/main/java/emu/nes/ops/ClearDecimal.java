package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * CLD operation.
 * @author hdouss
 *
 */
public class ClearDecimal implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        registers.getStatus().setDecimal(false);
        return 0;
    }

}
