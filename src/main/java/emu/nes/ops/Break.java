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
        byte stack = registers.getStack();
        final int pc = registers.getPc();
        // stack implicitly converted to integer, consider only 8 bits for stack address
        bus.write(0x0100 + (stack & 0xFF), (byte) (pc >> 8));
        stack--;
        bus.write(0x0100 + (stack & 0xFF), (byte) pc);
        stack--;
        final Status status = registers.getStatus();
        bus.write(0x0100 + (stack & 0xFF), (byte) (status.status() | 0x30));
        stack--;
        registers.setStack(stack);
        registers.setPc((bus.read(0xFFFE) & 0xFF) | (bus.read(0xFFFF) << 8));
        status.setBreak(true);
        // probably should set irq disabled to true
        return 0;
    }

}
