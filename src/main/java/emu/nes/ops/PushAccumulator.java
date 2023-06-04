package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * PHA operation.
 * @author hdouss
 *
 */
public class PushAccumulator implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int stack = registers.getStack();
        bus.write(0x0100 + stack, registers.getAcc());
        stack--;
        registers.setStack(stack);
        return 0;
    }

}
