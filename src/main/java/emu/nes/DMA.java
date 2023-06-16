package emu.nes;

import emu.nes.cpu.Bus;
import emu.nes.graphics.PPU;

/**
 * DMA logic for dumping memory to the PPU. This happens when 0x4014 address is written to. 
 * @author hdouss
 *
 */
public class DMA {

    /**
     * PPU.
     */
    private PPU ppu;

    /**
     * Builds a DMA connected to the passed PPU.
     * @param ppu PPU to connect to.
     */
    public DMA(final PPU ppu) {
        this.ppu = ppu;
    }

    /**
     * Reads 256 bytes of data starting from 0xvalue00 to 0xvalueFF and writes them to the PPU OAM. 
     * @param value Memory address.
     * @param bus Bus used to read data.
     */
    public void write(final byte value, final Bus bus) {
        int start = (value & 0xFF) << 8;
        for (int address = start; address < start + 0x100; ++address) {
            this.ppu.writeOAM(bus.read(address));
        }
    }

}
