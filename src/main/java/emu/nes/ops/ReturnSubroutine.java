package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * RTS operation.
 * @author hdouss
 *
 */
public class ReturnSubroutine implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        byte stack = registers.getStack();
        stack++;
        // stack implicitly converted to integer, consider only 8 bits for stack address
        byte pclow = bus.read(0x0100 + (stack & 0xFF));
        stack++;
        byte pchigh = bus.read(0x0100 + (stack & 0xFF));
        // bitwise operation converts to integer, consider only 8 bits
        registers.setPc(((pclow & 0xFF) | pchigh << 8) + 1);
        registers.setStack(stack);
        return 0;
    }

}
