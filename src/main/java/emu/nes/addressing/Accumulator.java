package emu.nes.addressing;

import emu.nes.cpu.Bus;
import emu.nes.cpu.Registers;

/**
 * Accumulator addressing: not a real addressing, the fetched data is actually
 * the accumulator value. The resulting address is put to 0 to indicate it was not fetched from a
 * real memory address.
 * @author hdouss
 *
 */
public class Accumulator implements Addressing {

    @Override
    public AddressingResult address(Bus bus, Registers registers, int pc) {
        AddressingResult result = new AddressingResult();
        result.address = 0;
        result.fetch = (address -> registers.getAcc());
        return result;
    }
}
