package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * SBC operation.
 * @author hdouss
 *
 */
public class SubtractCarry implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData();
        final int acc = registers.getAcc();
        int result = (byte) acc - (byte) data - 1 + (registers.getStatus().carry() ? 1 : 0);
        final byte asByte = (byte) result;
        updateFlags(registers, result);
        registers.getStatus().setCarry(asByte >= 0);
        registers.setAcc(result & 0xFF);
        registers.getStatus().setOverflow(result * asByte < 0);
        return res.isCrossed() ? 1 : 0;
    }

}
