package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * RTI operation.
 * @author hdouss
 *
 */
public class ReturnInterrupt implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        byte stack = registers.getStack();
        stack++;
        // stack implicitly converted to integer, consider only 8 bits for stack address
        byte flags = bus.read(0x0100 + (stack & 0xFF));
        final Status status = registers.getStatus();
        status.setStatus((flags & 0xCF) | (status.status() & 0x30));
        stack++;
        byte pclow = bus.read(0x0100 + (stack & 0xFF));
        stack++;
        byte pchigh = bus.read(0x0100 + (stack & 0xFF));
        // bitwise operation converts implicitly to integer, consider only 8 bits
        registers.setPc((pclow & 0xFF) | pchigh << 8);
        // may be break flag (and unused 5th) should be set to 0
        registers.setStack(stack);
        return 0;
    }

}
