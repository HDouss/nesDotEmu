package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * TAY operation. CPU status negative and zero flags are set according to the new Y value.
 * @author hdouss
 *
 */
public class TransferAccumulatorY implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        byte data = registers.getAcc();
        updateFlags(registers, data);
        registers.setY(data);
        return 0;
    }

}
