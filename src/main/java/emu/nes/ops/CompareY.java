package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * CPY operation. Flags are set with the same logic as {@link Compare} operation.
 * @author hdouss
 *
 */
public class CompareY implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        byte data = res.getData();
        Status status = registers.getStatus();
        byte y = registers.getY();
        // comparison as 8 bit integers
        status.setCarry((y & 0xFF) >= (data & 0xFF));
        status.setZero((y & 0xFF) == (data & 0xFF));
        status.setNegative((byte) (y - data) < 0);
        return 0;
    }

}
