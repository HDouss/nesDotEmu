package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * EOR operation. Bitwise XOR operation.  CPU status negative and zero flags are set according to the XOR result.
 * @author hdouss
 *
 */
public class ExclusiveOr implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData() ^ registers.getAcc();
        updateFlags(registers, data);
        registers.setAcc((byte) data);
        return res.isCrossed() ? 1 : 0;
    }

}
