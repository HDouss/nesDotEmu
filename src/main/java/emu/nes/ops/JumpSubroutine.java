package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * JSR operation. Writes current PC to the stack before jump.  
 * @author hdouss
 *
 */
public class JumpSubroutine implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int pc = registers.getPc() - 1;
        registers.setPc(res.getAddress());
        int stack = registers.getStack();
        bus.write(0x0100 + stack, (pc >> 8) & 0xFF);
        stack--;
        bus.write(0x0100 + stack, pc & 0xFF);
        stack--;
        registers.setStack(stack);
        return 0;
    }

}
