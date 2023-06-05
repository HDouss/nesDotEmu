package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * INY operation. CPU status negative and zero flags are set according to the new (incremented) value.
 * @author hdouss
 *
 */
public class IncrementY implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = registers.getY() + 1;
        registers.setY(data & 0xFF);
        updateFlags(registers, data);
        return 0;
    }

}
