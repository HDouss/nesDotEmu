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
        byte data = res.getData();
        int result = (data - 1) & 0xFF;
        bus.write(res.getAddress(), (byte) result);
        Status status = registers.getStatus();
        byte acc = registers.getAcc();
        // comparison as 8 bit integers
        status.setCarry((acc & 0xFF) >= result);
        status.setZero((acc & 0xFF) == (result & 0xFF));
        status.setNegative((byte) (acc - result) < 0);
        return 0;
    }

}
