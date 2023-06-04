package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * TXS operation.
 * @author hdouss
 *
 */
public class TransferXStack implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        registers.setStack(registers.getX());
        return 0;
    }

}
