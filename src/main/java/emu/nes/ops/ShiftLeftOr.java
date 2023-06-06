package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Unofficial SLO operation. Equivalent to shift left then OR.
 * @author hdouss
 *
 */
public class ShiftLeftOr implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData() << 1;
        int result = data | registers.getAcc();
        updateFlags(registers, result);
        int addr = res.getAddress();
        if (addr != 0) {
            bus.write(addr, data & 0xFF);
        }
        registers.getStatus().setCarry(result > 0xFF);
        registers.setAcc(result & 0xFF);
        return 0;
    }

}
