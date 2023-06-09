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
        byte stack = registers.getStack();
        // stack implicitly converted to integer, consider only 8 bits for stack address
        bus.write(0x0100 + (stack & 0xFF), (byte) (pc >> 8));
        stack--;
        bus.write(0x0100 + (stack & 0xFF), (byte) pc);
        stack--;
        registers.setStack(stack);
        return 0;
    }

}
