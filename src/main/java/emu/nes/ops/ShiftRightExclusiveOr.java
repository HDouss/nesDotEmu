package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Unofficial SRE operation. Equivalent to shift right then XOR.
 * @author hdouss
 *
 */
public class ShiftRightExclusiveOr implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        // shifting converts implicitly to integer, consider only 8 bits 
        int data = (res.getData() & 0xFF) >> 1;
        int addr = res.getAddress();
        if (addr != 0) {
            bus.write(addr, (byte) data);
        }
        registers.getStatus().setCarry((res.getData() & 0x1) > 0);
        int result = data ^ registers.getAcc();
        updateFlags(registers, result);
        registers.setAcc((byte) result);
        return 0;
    }

}
