package emu.nes.addressing;

import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Zero page addressing: next byte is the address in zero page (MSB is 0x00) of the actual data.
 * @author hdouss
 *
 */
public class ZeroPage implements Addressing {

    @Override
    public AddressingResult address(Bus bus, Registers registers, int pc) {
        registers.setPc(pc + 2);
        // data interpreted as an address, consider only 8 bits for addressing
        final int addr = bus.read(pc + 1) & 0xFF;
        final byte data = bus.read(addr);
        AddressingResult result = new AddressingResult();
        result.address = addr;
        result.data = data;
        return result;
    }
}
