package emu.nes.addressing;

import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Unknown addressing for unofficial, undocumented OPS.
 * @author hdouss
 *
 */
public class Unknown implements Addressing {

    @Override
    public AddressingResult address(Bus bus, Registers registers, int pc) {
        return new AddressingResult();
    }

}
