package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * JMP operation.
 * @author hdouss
 *
 */
public class Jump implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        int data = res.getAddress();
        registers.setPc(data);
        return 0;
    }

}
