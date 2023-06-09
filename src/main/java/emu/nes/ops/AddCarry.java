package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * ADC operation. CPU status negative and zero flags are set according to the addition result.
 * Carry flag updated depending if the (integer) result is > 255. Overflow is set to true
 * if the integer result of operands as bytes is &gt; 127 or &lt;-128; i.e: outside the byte type boundaries.
 * 
 * @author hdouss
 *
 */
public class AddCarry implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        byte data = res.getData();
        final byte acc = registers.getAcc();
        final int carry = registers.getStatus().carry() ? 1 : 0;
        // data implicitly converted to integers, consider only 8 bits for integer addition
        final int result = (acc & 0xFF) + (data & 0xFF) + carry;
        final int byteAddition = acc + data + carry;
        updateFlags(registers, result);
        registers.getStatus().setCarry(result > 0xFF);
        registers.getStatus().setOverflow(byteAddition > 127 || byteAddition < -128);
        registers.setAcc((byte) result);
        return res.isCrossed() ? 1 : 0;
    }

}
