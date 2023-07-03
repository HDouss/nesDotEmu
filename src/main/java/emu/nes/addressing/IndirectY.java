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
        final int pageZeroAddr = bus.read(pc + 1) & 0xFF;
        final int nextZeroPage = (pageZeroAddr + 1) & 0xFF;
        // data implicitly converted to integers, consider only 8 bits for addressing
        final int y = registers.getY() & 0xFF;
        final int add = (bus.read(pageZeroAddr) & 0xFF) + y;
        int low = add & 0xFF;
        int carry = add >> 8;
        int high = (carry + bus.read(nextZeroPage)) & 0xFF;
        registers.setPc(pc + 2);
        final int addr = low | (high << 8);
        AddressingResult result = new AddressingResult();
        result.address = addr;
        result.fetch = (address -> bus.read(address));
        result.crossed = ((addr - y) & 0xFF00) != (high << 8);
        return result;
    }

}
