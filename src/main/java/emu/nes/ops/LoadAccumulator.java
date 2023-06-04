package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * LDA operation.
 * @author hdouss
 *
 */
public class LoadAccumulator implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData();
        registers.setAcc(data);
        updateFlags(registers, data);
        return res.isCrossed() ? 1 : 0;
    }

}
