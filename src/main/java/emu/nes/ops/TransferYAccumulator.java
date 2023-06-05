package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * TYA operation. CPU status negative and zero flags are set according to the new accumulator value.
 * @author hdouss
 *
 */
public class TransferYAccumulator implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = registers.getY();
        updateFlags(registers, data);
        registers.setAcc(data);
        return 0;
    }

}
