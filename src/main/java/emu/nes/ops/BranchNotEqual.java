package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * BNE operation. Can return 0, 1 or 2 as additional cycles.
 * @author hdouss
 *
 */
public class BranchNotEqual implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        if (!registers.getStatus().zero()) {
            int data = res.getData();
            final int pc = registers.getPc();
            registers.setPc(pc + data);
            if ((pc & 0xFF00) != ((pc + data) & 0xFF00)) {
                return 2;
            } else {
                return 1;
            }
        }
        return 0;
    }

}
