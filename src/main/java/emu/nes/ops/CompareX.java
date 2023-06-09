package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * CPX operation. Flags are set with the same logic as {@link Compare} operation.
 * @author hdouss
 *
 */
public class CompareX implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        byte data = res.getData();
        Status status = registers.getStatus();
        byte x = registers.getX();
        // comparison as 8 bit integers
        status.setCarry((x & 0xFF) >= (data & 0xFF));
        status.setZero((x & 0xFF) == (data & 0xFF));
        status.setNegative((byte) (x - data) < 0);
        return 0;
    }

}
