package emu.nes.addressing;

import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Absolute addressing: next two bytes defines an address that contains the data.
 * @author hdouss
 *
 */
public class Absolute implements Addressing {

    @Override
    public AddressingResult address(Bus bus, Registers registers, int pc) {
        int high = bus.read(pc + 2);
        int low = bus.read(pc + 1);
        registers.setPc(pc + 3);
        final int addr = low | (high << 8);
        final int data = bus.read(addr & 0xFFFF);
        AddressingResult result = new AddressingResult();
        result.address = addr;
        result.data = data;
        return result;
    }

}
