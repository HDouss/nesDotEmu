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
        // data implicitly converted to integers, consider only 8 bits for addressing
        final int location = (bus.read(pc + 1) & 0xFF) + (registers.getX() & 0xFF);
        final int zeroPage = location & 0xFF;
        final int nextZeroPage = (zeroPage + 1) & 0xFF;
        // data implicitly converted to integers, consider only 8 bits for addressing
        int low = bus.read(zeroPage) & 0xFF;
        int high = bus.read(nextZeroPage) & 0xFF;
        registers.setPc(pc + 2);
        final int addr = low | (high << 8);
        final byte data = bus.read(addr);
        AddressingResult result = new AddressingResult();
        result.address = addr;
        result.data = data;
        return result;
    }

}
