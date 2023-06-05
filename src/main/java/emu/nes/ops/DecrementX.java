package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * DEX operation. CPU status negative and zero flags are set according to the new (decremented) value.
 * @author hdouss
 *
 */
public class DecrementX implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = registers.getX() - 1;
        registers.setX(data & 0xFF);
        updateFlags(registers, data);
        return 0;
    }

}
