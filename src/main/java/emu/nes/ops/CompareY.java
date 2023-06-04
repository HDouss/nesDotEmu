package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * CPY operation.
 * @author hdouss
 *
 */
public class CompareY implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData();
        Status status = registers.getStatus();
        int y = registers.getY();
        status.setCarry(y >= data);
        status.setZero((y & 0xFF) == (data & 0xFF));
        status.setNegative((byte) (y - data) < 0);
        return 0;
    }

}
