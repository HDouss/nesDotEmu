package emu.nes.addressing;

import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Indirect addressing: next two bytes give the address which content (and the following one)
 * is the actual address of the data. Special attention to simulate the page boundary hardware bug.
 * @author hdouss
 *
 */
public class Indirect implements Addressing {

    @Override
    public AddressingResult address(Bus bus, Registers registers, int pc) {
        // data implicitly converted to integers, consider only 8 bits for addressing
        int plow = bus.read(pc + 1) & 0xFF;
        int phigh = bus.read(pc + 2) & 0xFF;
        int addrP = plow + (phigh << 8);
         // data implicitly converted to integers, consider only 8 bits for addressing
        int low = bus.read(addrP) & 0xFF;
        int high = 0;
        if (plow == 0xFF) {
            // simulate hardware bug
            high = bus.read(phigh << 8) & 0xFF;
        } else {
            // plow < 0xFF so the above addition cannot overflow
            high = bus.read(addrP + 1) & 0xFF;
        }
        registers.setPc(pc + 3);
        AddressingResult result = new AddressingResult();
        result.address = low + (high << 8);
        return result;
    }

}
