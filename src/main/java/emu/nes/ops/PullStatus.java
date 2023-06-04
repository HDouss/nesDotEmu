package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;
import emu.nes.cpu.Status;

/**
 * PLP operation.
 * @author hdouss
 *
 */
public class PullStatus implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int stack = registers.getStack();
        stack++;
        int data = bus.read(0x0100 + stack);
        final Status status = registers.getStatus();
        status.setStatus((data & 0xCF) | (status.status() & 0x30));
        registers.setStack(stack);
        return 0;
    }

}
