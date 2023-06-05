package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * ROR operation. CPU status negative and zero flags are set according to the new rotated value.
 * Carry flag is set if the original bit 0 value is set.
 * The implementation detects if the value read and to be updated is the accumulator value by
 * checking if AddressingResult.getAddress() returns 0.
 * @author hdouss
 *
 */
public class RotateRight implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        final Status status = registers.getStatus();
        int data = (res.getData() >> 1) | ((status.carry() ? 1 : 0) << 7);
        int addr = res.getAddress();
        updateFlags(registers, data);
        status.setCarry((res.getData() & 0x1) > 0);
        if (addr == 0) {
            registers.setAcc(data & 0xFF);
        } else {
            bus.write(addr, data & 0xFF);
        }
        return 0;
    }

}
