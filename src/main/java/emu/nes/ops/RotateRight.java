package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * ROR operation.
 * @author hdouss
 *
 */
public class RotateRight implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        final Status status = registers.getStatus();
        int data = (res.getData() >> 1) | ((status.carry() ? 1 : 0) << 7);
        int addr = res.getAddress();
        updateFlags(registers, data);
        status.setCarry((res.getData() & 0x1) > 0);
        if (addr == 0) {
            registers.setAcc(data & 0xFF);
        } else {
            bus.write(addr, data & 0xFF);
        }
        return 0;
    }

}
