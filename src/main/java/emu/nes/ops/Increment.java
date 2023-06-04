package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * INC operation.
 * @author hdouss
 *
 */
public class Increment implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData();
        int result = (data + 1) & 0xFF;
        updateFlags(registers, result);
        bus.write(res.getAddress(), result);
        return 0;
    }

}
