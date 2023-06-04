package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * CLC operation.
 * @author hdouss
 *
 */
public class ClearCarry implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        registers.getStatus().setCarry(false);
        return 0;
    }

}
