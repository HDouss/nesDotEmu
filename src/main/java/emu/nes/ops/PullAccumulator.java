package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * PLA operation. CPU status negative and zero flags are set according to the new accumulator value.
 * @author hdouss
 *
 */
public class PullAccumulator implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        byte stack = registers.getStack();
        stack++;
        // stack implicitly converted to integer, consider only 8 bits for stack address
        byte data = bus.read(0x0100 + (stack & 0xFF));
        updateFlags(registers, data);
        registers.setAcc(data);
        registers.setStack(stack);
        return 0;
    }

}
