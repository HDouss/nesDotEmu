package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * BIT operation.
 * @author hdouss
 *
 */
public class BitTest implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData();
        final int acc = registers.getAcc();
        final int result = acc & data;
        final Status status = registers.getStatus();
        status.setZero(result == 0);
        status.setOverflow((data & 0x40) > 0);
        status.setNegative((data & 0x80) > 0);
        return 0;
    }

}
