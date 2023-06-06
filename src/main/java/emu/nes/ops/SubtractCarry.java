package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * SBC operation. CPU status negative and zero flags are set according to the substraction result.
 * Carry flag is set if a 'borrow' was not needed for the substraction.
 * This is the case if the result as a byte is positive.
 * Overflow is set if the substraction gives a result outside the byte range; i.e &lt-128.
 * @author hdouss
 *
 */
public class SubtractCarry implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData();
        final int acc = registers.getAcc();
        final int operand = data + 1 - (registers.getStatus().carry() ? 1 : 0);
        final int byteSubstraction = (byte) acc - (byte) operand;
        final byte result = (byte) byteSubstraction;
        updateFlags(registers, result);
        registers.getStatus().setCarry(acc >= operand);
        registers.setAcc(result & 0xFF);
        registers.getStatus().setOverflow(byteSubstraction < -128);
        return res.isCrossed() ? 1 : 0;
    }

}
