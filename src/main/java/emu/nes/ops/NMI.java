package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * NMI operation.
 * @author hdouss
 *
 */
public class NMI implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int pc = registers.getPc();
        byte stack = registers.getStack();
        // stack implicitly converted to integer, consider only 8 bits for stack
        // address
        bus.write(0x0100 + (stack & 0xFF), (byte) (pc >> 8));
        stack--;
        bus.write(0x0100 + (stack & 0xFF), (byte) pc);
        stack--;

        final Status status = registers.getStatus();
        status.setStatus((byte) ((status.status() | 0x24) & 0xEF));
        bus.write(0x0100 + (stack & 0xFF), (byte) status.status());
        stack--;
        int lsb = bus.read(0xFFFA);
        int msb = bus.read(0xFFFB);
        registers.setPc((lsb & 0xFF) | (msb << 8));
        registers.setStack(stack);
        return 8;
    }

}
