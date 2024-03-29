package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * TAX operation. CPU status negative and zero flags are set according to the new X value.
 * @author hdouss
 *
 */
public class TransferAccumulatorX implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        byte data = registers.getAcc();
        updateFlags(registers, data);
        registers.setX(data);
        return 0;
    }

}
