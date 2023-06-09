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
        final byte data = res.getData();
        final int increment = (data + 1) & 0xFF;
        final byte acc = registers.getAcc();
        final int operand = increment + 1 - (registers.getStatus().carry() ? 1 : 0);
        final int byteSubstraction = acc - (byte) operand;
        final byte result = (byte) byteSubstraction;
        updateFlags(registers, result);
        registers.getStatus().setCarry((acc & 0xFF) >= operand);
        registers.getStatus().setOverflow(byteSubstraction < -128);
        registers.setAcc((byte) result);
        bus.write(res.getAddress(), (byte) increment);
        return 0;
    }

}
