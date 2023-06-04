package emu.nes.addressing;

import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Zero page X addressing: next byte is added to the X register value to result in the address
 * in zero page (MSB is 0x00) of the actual data. The addition result wraps within the zero page.
 * @author hdouss
 *
 */
public class ZeroPageX implements Addressing {

    @Override
    public AddressingResult address(Bus bus, Registers registers, int pc) {
        registers.setPc(pc + 2);
        final int addr = (bus.read(pc + 1) + registers.getX()) & 0xFF;
        final int data = bus.read(addr);
        AddressingResult result = new AddressingResult();
        result.address = addr;
        result.data = data;
        return result;
    }

}
