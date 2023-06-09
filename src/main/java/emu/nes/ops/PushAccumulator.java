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
        byte stack = registers.getStack();
        // stack implicitly converted to integer, consider only 8 bits for stack address
        bus.write(0x0100 + (stack & 0xFF), registers.getAcc());
        stack--;
        registers.setStack(stack);
        return 0;
    }

}
