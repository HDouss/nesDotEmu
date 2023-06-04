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
        int stack = registers.getStack();
        stack++;
        int flags = bus.read(0x0100 + stack);
        final Status status = registers.getStatus();
        status.setStatus((flags & 0xCF) | (status.status() & 0x30));
        stack++;
        int pclow = bus.read(0x0100 + stack);
        stack++;
        int pchigh = bus.read(0x0100 + stack);
        registers.setPc(pclow | pchigh << 8);
        // may be break flag (and unused 5th) should be set to 0
        registers.setStack(stack);
        return 0;
    }

}
