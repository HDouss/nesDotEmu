package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * Unofficial DCP operation. Equivalent to decrement then compare. 
 * @author hdouss
 *
 */
public class DecrementCompare implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData();
        int result = (data - 1) & 0xFF;
        bus.write(res.getAddress(), result);
        Status status = registers.getStatus();
        int acc = registers.getAcc();
        status.setCarry(acc >= result);
        status.setZero((acc & 0xFF) == (result & 0xFF));
        status.setNegative((byte) (acc - result) < 0);
        return 0;
    }

}
