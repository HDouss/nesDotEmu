package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * INC operation. CPU status negative and zero flags are set according to the new (incremented) value.
 * @author hdouss
 *
 */
public class Increment implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData();
        int result = data + 1;
        updateFlags(registers, result);
        bus.write(res.getAddress(), (byte) result);
        return 0;
    }

}
