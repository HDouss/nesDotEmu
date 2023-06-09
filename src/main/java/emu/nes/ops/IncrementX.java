package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * INX operation. CPU status negative and zero flags are set according to the new (incremented) value.
 * @author hdouss
 *
 */
public class IncrementX implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = registers.getX() + 1;
        registers.setX((byte) data);
        updateFlags(registers, (byte) data);
        return 0;
    }

}
