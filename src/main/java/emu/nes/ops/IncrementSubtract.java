package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Unofficial ISC (or ISB) operation. Equivalent to increment then subtract.
 * @author hdouss
 *
 */
public class IncrementSubtract implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        final int data = res.getData();
        final int increment = (data + 1) & 0xFF;
        final int acc = registers.getAcc();
        final int operand = increment + 1 - (registers.getStatus().carry() ? 1 : 0);
        final int byteSubstraction = (byte) acc - (byte) operand;
        final byte result = (byte) byteSubstraction;
        updateFlags(registers, result);
        registers.getStatus().setCarry(acc >= operand);
        registers.getStatus().setOverflow(byteSubstraction < -128);
        registers.setAcc(result & 0xFF);
        bus.write(res.getAddress(), increment);
        return 0;
    }

}
