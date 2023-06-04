package emu.nes.addressing;

import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Relative addressing: next byte is the actual data which is just a relative offset to add
 * to the program counter (see branching operations). 
 * @author hdouss
 *
 */
public class Relative implements Addressing {

    @Override
    public AddressingResult address(Bus bus, Registers registers, int pc) {
        registers.setPc(pc + 2);
        final int addr = pc + 1;
        final int value = bus.read(addr);
        final int data = value > 127 ? value - 256 : value;
        AddressingResult result = new AddressingResult();
        result.address = addr;
        result.data = data;
        return result;
    }

}