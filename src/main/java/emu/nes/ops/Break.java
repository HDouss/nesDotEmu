package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * BRK operation.
 * @author hdouss
 *
 */
public class Break implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int stack = registers.getStack();
        final int pc = registers.getPc();
        bus.write(0x0100 + stack, (pc >> 8) & 0xFF);
        stack--;
        bus.write(0x0100 + stack, pc & 0xFF);
        stack--;
        final Status status = registers.getStatus();
        bus.write(0x0100 + stack, status.status() | 0x30);
        stack--;
        registers.setStack(stack);
        registers.setPc(bus.read(0xFFFE) | (bus.read(0xFFFF) << 8));
        status.setBreak(true);
        // probably should set irq disabled to true
        return 0;
    }

}
