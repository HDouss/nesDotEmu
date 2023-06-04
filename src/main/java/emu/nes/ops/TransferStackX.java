package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * TSX operation.
 * @author hdouss
 *
 */
public class TransferStackX implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = registers.getStack();
        updateFlags(registers, data);
        registers.setX(data);
        return 0;
    }

}
