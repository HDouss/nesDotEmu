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
        byte high = bus.read(pc + 2);
        byte low = bus.read(pc + 1);
        registers.setPc(pc + 3);
        // data implicitly converted to integers, consider only 8 bits
        final int addr = (low & 0xFF) | ((high & 0xFF) << 8);
        final byte data = bus.read(addr);
        AddressingResult result = new AddressingResult();
        result.address = addr;
        result.data = data;
        return result;
    }

}
