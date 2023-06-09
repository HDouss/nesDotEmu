package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * ROL operation. CPU status negative and zero flags are set according to the new rotated value.
 * The implementation detects if the value read and to be updated is the accumulator value by
 * checking if AddressingResult.getAddress() returns 0.
 * @author hdouss
 *
 */
public class RotateLeft implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        // shifting converts to int, so consider only 8 bits
        int data = ((res.getData() & 0xFF) << 1) | (registers.getStatus().carry() ? 1 : 0);
        int addr = res.getAddress();
        updateFlags(registers, data);
        registers.getStatus().setCarry(data > 0xFF);
        if (addr == 0) {
            registers.setAcc((byte) data);
        } else {
            bus.write(addr, (byte) data);
        }
        return 0;
    }

}
