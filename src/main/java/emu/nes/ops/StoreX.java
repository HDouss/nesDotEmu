package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * STX operation.
 * @author hdouss
 *
 */
public class StoreX implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int addr = res.getAddress();
        bus.write(addr, registers.getX());
        return 0;
    }

}
