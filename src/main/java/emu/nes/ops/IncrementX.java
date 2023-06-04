package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * INX operation.
 * @author hdouss
 *
 */
public class IncrementX implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = registers.getX() + 1;
        registers.setX(data & 0xFF);
        updateFlags(registers, data);
        return 0;
    }

}
