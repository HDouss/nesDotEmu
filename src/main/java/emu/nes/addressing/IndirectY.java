package emu.nes.addressing;

import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Indirect Y addressing: next byte is an address A which content is added to Y register value
 * to give the LSB of the actual data address. The MSB of the data address is read from the address
 * following A or the following one if the addition [data(A) + y] resulted in a carry. 
 * @author hdouss
 *
 */
public class IndirectY implements Addressing {

    @Override
    public AddressingResult address(Bus bus, Registers registers, int pc) {
        final int pageZeroAddr = bus.read(pc + 1);
        final int y = registers.getY();
        final int add = bus.read(pageZeroAddr) + y;
        int low = add & 0xFF;
        int carry = add >> 8;
        int high = (carry + bus.read((pageZeroAddr + 1) & 0xFF)) & 0xFF;
        registers.setPc(pc + 2);
        final int addr = low | (high << 8);
        final int data = bus.read(addr);
        AddressingResult result = new AddressingResult();
        result.address = addr;
        result.data = data;
        result.crossed = ((addr - y) & 0xFF00) != (high << 8);
        return result;
    }

}
