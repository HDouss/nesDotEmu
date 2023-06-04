package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * BVC operation.
 * @author hdouss
 *
 */
public class BranchOverflowClear implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData();
        if (!registers.getStatus().overflow()) {
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
