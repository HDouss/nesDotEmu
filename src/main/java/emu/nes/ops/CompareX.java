package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * CPX operation.
 * @author hdouss
 *
 */
public class CompareX implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData();
        Status status = registers.getStatus();
        int x = registers.getX();
        status.setCarry(x >= data);
        status.setZero((x & 0xFF) == (data & 0xFF));
        status.setNegative((byte) (x - data) < 0);
        return 0;
    }

}
