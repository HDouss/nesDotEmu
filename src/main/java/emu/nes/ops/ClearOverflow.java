package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * CLV operation.
 * @author hdouss
 *
 */
public class ClearOverflow implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        registers.getStatus().setOverflow(false);
        return 0;
    }

}
