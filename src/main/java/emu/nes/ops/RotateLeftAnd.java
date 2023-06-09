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
        // shifting converts to int, so consider only 8 bits
        int data = ((res.getData() & 0xFF) << 1) | (registers.getStatus().carry() ? 1 : 0);
        int addr = res.getAddress();
        if (addr != 0) {
            bus.write(addr, (byte) data);
        }
        final int acc = registers.getAcc();
        final int result = acc & data;
        updateFlags(registers, result);
        registers.getStatus().setCarry(data > 0xFF);
        registers.setAcc((byte) result);
        return 0;
    }

}
