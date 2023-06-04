package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * EOR operation.
 * @author hdouss
 *
 */
public class ExclusiveOr implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData() ^ registers.getAcc();
        updateFlags(registers, data);
        registers.setAcc(data);
        return res.isCrossed() ? 1 : 0;
    }

}
