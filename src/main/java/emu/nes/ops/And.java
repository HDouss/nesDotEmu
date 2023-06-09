package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * AND operation. Bitwise AND.
 * @author hdouss
 *
 */
public class And implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        byte data = res.getData();
        final int acc = registers.getAcc();
        final int result = acc & data;
        updateFlags(registers, result);
        registers.setAcc((byte) result);
        return res.isCrossed() ? 1 : 0;
    }

}
