package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * PLP operation. Status 4th and 5th bits are not changed and forced to remain with their value before pulling. 
 * @author hdouss
 *
 */
public class PullStatus implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        byte stack = registers.getStack();
        stack++;
        // stack implicitly converted to integer, consider only 8 bits for stack address
        byte data = bus.read(0x0100 + (stack & 0xFF));
        final Status status = registers.getStatus();
        status.setStatus((data & 0xCF) | (status.status() & 0x30));
        registers.setStack(stack);
        return 0;
    }

}
