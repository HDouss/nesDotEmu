package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * TAX operation.
 * @author hdouss
 *
 */
public class TransferAccumulatorX implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = registers.getAcc();
        updateFlags(registers, data);
        registers.setX(data);
        return 0;
    }

}
