package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * DEY operation. CPU status negative and zero flags are set according to the new (decremented) value.
 * @author hdouss
 *
 */
public class DecrementY implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = registers.getY() - 1;
        registers.setY((byte) data);
        updateFlags(registers, data);
        return 0;
    }

}
