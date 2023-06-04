package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * ASL operation.
 * @author hdouss
 *
 */
public class ShiftLeft implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData() << 1;
        int addr = res.getAddress();
        updateFlags(registers, data);
        registers.getStatus().setCarry(data > 0xFF);
        if (addr == 0) {
            registers.setAcc(data & 0xFF);
        } else {
            bus.write(addr, data & 0xFF);
        }
        return 0;
    }

}
