package emu.nes.addressing;

import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Indirect X addressing: next byte is added to X register value
 * to give the address (and the following one) that contains the actual data.
 * @author hdouss
 *
 */
public class IndirectX implements Addressing {

    @Override
    public AddressingResult address(Bus bus, Registers registers, int pc) {
        int location = (bus.read(pc + 1) + registers.getX()) & 0xFF;
        int low = bus.read(location);
        int high = bus.read((location + 1) & 0xFF);
        registers.setPc(pc + 2);
        final int addr = low | (high << 8);
        final int data = bus.read(addr);
        AddressingResult result = new AddressingResult();
        result.address = addr;
        result.data = data;
        return result;
    }

}
