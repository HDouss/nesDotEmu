package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * CMP operation. Needs documentation for flags
 * @author hdouss
 *
 */
public class Compare implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData();
        Status status = registers.getStatus();
        int acc = registers.getAcc();
        status.setCarry(acc >= data);
        status.setZero((acc & 0xFF) == (data & 0xFF));
        status.setNegative((byte) (acc - data) < 0);
        return res.isCrossed() ? 1 : 0;
    }

}
