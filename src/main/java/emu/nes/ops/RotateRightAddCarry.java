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
        int data = (res.getData() >> 1) | (carry << 7);
        int addr = res.getAddress();
        if (addr != 0) {
            bus.write(addr, data & 0xFF);
        }
        carry = res.getData() & 0x1;
        final int acc = registers.getAcc();
        final int result = acc + data + carry;
        final int byteAddition = (byte) acc + (byte) data + carry;
        updateFlags(registers, result);
        registers.getStatus().setCarry(result > 0xFF);
        registers.getStatus().setOverflow(byteAddition > 127 || byteAddition < -128);
        registers.setAcc(result & 0xFF);
        return 0;
    }

}
