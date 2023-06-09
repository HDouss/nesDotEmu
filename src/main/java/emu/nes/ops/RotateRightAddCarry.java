package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Unofficial RRA operation. Equivalent to rotate right then add carry.
 * @author hdouss
 *
 */
public class RotateRightAddCarry implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int carry = registers.getStatus().carry() ? 1 : 0;
        // shifting converts to int, so consider only 8 bits
        byte data = (byte) (((res.getData() & 0xFF) >> 1) | (carry << 7));
        int addr = res.getAddress();
        if (addr != 0) {
            bus.write(addr, data);
        }
        carry = res.getData() & 0x1;
        final byte acc = registers.getAcc();
        // integer result consider only 8 bits
        final int result = (acc & 0xFF) + (data & 0xFF) + carry;
        final int byteAddition = acc + data + carry;
        updateFlags(registers, result);
        registers.getStatus().setCarry(result > 0xFF);
        registers.getStatus().setOverflow(byteAddition > 127 || byteAddition < -128);
        registers.setAcc((byte) result);
        return 0;
    }

}
