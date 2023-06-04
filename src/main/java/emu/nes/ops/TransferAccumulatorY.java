package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * TAY operation.
 * @author hdouss
 *
 */
public class TransferAccumulatorY implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = registers.getAcc();
        updateFlags(registers, data);
        registers.setY(data);
        return 0;
    }

}
