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
        int high = bus.read(pc + 2);
        int low = bus.read(pc + 1);
        registers.setPc(pc + 3);
        final int addr = registers.getX() + (low | (high << 8));
        final int data = bus.read(addr & 0xFFFF);
        AddressingResult result = new AddressingResult();
        result.address = addr;
        result.data = data;
        result.crossed = (addr & 0xFF00) != (high << 8);
        return result;
    }

}
