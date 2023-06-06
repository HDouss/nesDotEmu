package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Unofficial LAX operation. CPU status negative and zero flags are set according to the new accumulator/X register value.
 * @author hdouss
 *
 */
public class LoadAccumulatorX implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData();
        registers.setAcc(data);
        registers.setX(data);
        updateFlags(registers, data);
        return res.isCrossed() ? 1 : 0;
    }

}
