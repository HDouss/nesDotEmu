package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Unofficial NOP operation that returns an additional cycle if boundary crossing happened.
 * @author Hamdi-DOUSS
 *
 */
public class NoOPCrossing implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        return res.isCrossed() ? 1 : 0;
    }

}
