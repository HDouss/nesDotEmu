package emu.nes.ops;

import emu.nes.addressing.AddressingResult;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * LDY operation. CPU status negative and zero flags are set according to the new Y value.
 * @author hdouss
 *
 */
public class LoadY implements Operation {

    @Override
    public int execute(Registers registers, Bus bus, AddressingResult res) {
        byte data = res.getData();
        registers.setY(data);
        updateFlags(registers, data);
        return res.isCrossed() ? 1 : 0;
    }

}
