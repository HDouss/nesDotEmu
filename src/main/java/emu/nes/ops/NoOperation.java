package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

public class NoOperation implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        return 0;
    }

}
