package emu.nes.addressing;

import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Immediate addressing: next byte gives the actual data.
 * @author hdouss
 *
 */
public class Immediate implements Addressing {

    @Override
    public AddressingResult address(Bus bus, Registers registers, int pc) {
        registers.setPc(pc + 2);
        final int addr = pc + 1;
        final int data = bus.read(addr);
        AddressingResult result = new AddressingResult();
        result.address = addr;
        result.data = data;
        return result;
    }

}
