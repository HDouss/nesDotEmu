package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * PHP operation. Status pushed to the stack is forced to have 1 in bits 4 and 5.
 * @author hdouss
 *
 */
public class PushStatus implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        byte stack = registers.getStack();
        // stack implicitly converted to integer, consider only 8 bits for stack address
        bus.write(0x0100 + (stack & 0xFF), (byte) (registers.getStatus().status() | 0x30));
        stack--;
        registers.setStack(stack);
        return 0;
    }

}
