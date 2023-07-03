package emu.nes.addressing;

import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Absolute X addressing: next two bytes defines an address that should be shifted (offset)
 * by the X register value. The resulting address contains the data.
 * @author hdouss
 *
 */
public class AbsoluteX implements Addressing {

    @Override
    public AddressingResult address(Bus bus, Registers registers, int pc) {
        // data implicitly converted to integers, consider only 8 bits for addressing
        int high = bus.read(pc + 2) & 0xFF;
        int low = bus.read(pc + 1) & 0xFF;
        registers.setPc(pc + 3);
        // data implicitly converted to integers, consider only 8 bits for addressing
        final int addr = (registers.getX() & 0xFF) + low + (high << 8);
        AddressingResult result = new AddressingResult();
        // addition result may overflow
        result.address = addr & 0xFFFF;
        result.fetch = (address -> bus.read(address));
        result.crossed = (addr & 0xFF00) != (high << 8);
        return result;
    }

}
