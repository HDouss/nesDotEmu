package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * STY operation.
 * @author hdouss
 *
 */
public class StoreY implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int addr = res.getAddress();
        bus.write(addr, registers.getY());
        return 0;
    }

}
