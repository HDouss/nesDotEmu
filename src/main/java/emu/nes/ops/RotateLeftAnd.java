package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Unofficial RLA operation. Equivalent to rotate left then AND.
 * @author hdouss
 *
 */
public class RotateLeftAnd implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = (res.getData() << 1) | (registers.getStatus().carry() ? 1 : 0);
        int addr = res.getAddress();
        if (addr != 0) {
            bus.write(addr, data & 0xFF);
        }
        final int acc = registers.getAcc();
        final int result = acc & data;
        updateFlags(registers, result);
        registers.getStatus().setCarry(data > 0xFF);
        registers.setAcc(result & 0xFF);
        return 0;
    }

}
