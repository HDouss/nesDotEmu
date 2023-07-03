package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * LSR operation. CPU status negative and zero flags are set according to the new shifted value.
 * Carry flag is set if the original bit 0 value is set.
 * The implementation detects if the value read and to be updated is the accumulator value by
 * checking if AddressingResult.getAddress() returns 0.
 * @author hdouss
 *
 */
public class ShiftRight implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        final Status status = registers.getStatus();
        // shifting converts to int, so consider only 8 bits
        final byte fetched = res.getData();
        int data = (fetched & 0xFF) >> 1;
        int addr = res.getAddress();
        updateFlags(registers, data);
        status.setCarry((fetched & 0x1) > 0);
        if (addr == 0) {
            registers.setAcc((byte) data);
        } else {
            bus.write(addr, (byte) data);
        }
        return 0;
    }

}
