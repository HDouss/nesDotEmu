package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * DEY operation.
 * @author hdouss
 *
 */
public class DecrementY implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = registers.getY() - 1;
        registers.setY(data & 0xFF);
        updateFlags(registers, data);
        return 0;
    }

}
