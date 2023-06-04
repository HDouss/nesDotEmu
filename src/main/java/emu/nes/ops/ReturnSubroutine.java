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
        int stack = registers.getStack();
        stack++;
        int pclow = bus.read(0x0100 + stack);
        stack++;
        int pchigh = bus.read(0x0100 + stack);
        registers.setPc(((pclow | pchigh << 8) + 1) & 0xFFFF);
        registers.setStack(stack);
        return 0;
    }

}
