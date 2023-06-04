package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * BEQ operation.
 * @author hdouss
 *
 */
public class BranchEqual implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getData();
        if (registers.getStatus().zero()) {
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
