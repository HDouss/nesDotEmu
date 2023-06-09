package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Unofficial SLO operation. Equivalent to shift left then OR.
 * @author hdouss
 *
 */
public class ShiftLeftOr implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        // shifting converts implicitly to integer, consider only 8 bits
        int data = (res.getData() & 0xFF) << 1;
        // bitwise operation converts implicitly to integer, consider only 8 bits
        int result = data | (registers.getAcc() & 0xFF);
        updateFlags(registers, result);
        int addr = res.getAddress();
        if (addr != 0) {
            bus.write(addr, (byte) data);
        }
        registers.getStatus().setCarry(result > 0xFF);
        registers.setAcc((byte) result);
        return 0;
    }

}
