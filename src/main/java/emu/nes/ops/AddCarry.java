package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * ADC operation. Carry flag updated depending if the result is > 255. Overflow is set to true
 * if the actual result is &gt; 127 or &lt;-128. This is detected by checking if the two operands
 * are of the same sign and the result is of a different sign (after being wrapped in the interval
 * -128 -> 127).
 * @author hdouss
 *
 */
public class AddCarry implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData();
        final int acc = registers.getAcc();
        final int result = acc + data + (registers.getStatus().carry() ? 1 : 0);
        updateFlags(registers, result);
        registers.getStatus().setCarry(result > 0xFF);
        registers.getStatus().setOverflow(
            // accumulator and data(memory) are of the same sign
            !(((acc ^ data) & 0x80) > 0)
                // and the result is of a different sign
                && ((acc ^ result) & 0x80) > 0
        );
        registers.setAcc(result & 0xFF);
        return res.isCrossed() ? 1 : 0;
    }

}
