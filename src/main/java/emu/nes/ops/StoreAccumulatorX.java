package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Unofficial SAX operation.
 * @author hdouss
 *
 */
public class StoreAccumulatorX implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int addr = res.getAddress();
        bus.write(addr, (byte) (registers.getAcc() & registers.getX()));
        return 0;
    }

}
