package emu.nes.cpu;

import emu.nes.DMA;
import emu.nes.Test;
import emu.nes.cartridge.Cartridge;
import emu.nes.graphics.PPU;
import emu.nes.memory.ByteMemory;
import emu.nes.memory.Memory;
import emu.nes.memory.SelectorByteMemory;
import emu.nes.sound.APU;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * CPU Bus logic. Acts like a selector byte memory to address RAM, PPU and APU.
 * Overrides the write method to handle the special case for DMA.
 * @author hdouss
 *
 */
public class Bus extends SelectorByteMemory {

    /**
     * DMA address.
     */
    private static final int DMA_ADDRESS = 0x4014;

    /**
     * DMA.
     */
    private DMA dma;

    /**
     * Builds a cpu bus with the passed PPU and DMA.
     * @param ppu Connected PPU.
     * @param dma Connected DMA.
     */
    public Bus(final PPU ppu, final DMA dma) {
        super(Bus.memoryMap(ppu));
        this.dma = dma;
    }

    /**
     * Connects the bus to the cartridge, or to void.
     * @param cartridge Cartridge or void to connect
     */
    public void insert(final Optional<Cartridge> cartridge) {
        this.memories().remove(0x4020);
        if (cartridge.isPresent()) {
            this.memories().put(0x4020, cartridge.get());
        }
    }

    @Override
    public void write(int addr, byte value) {
        if (addr == Bus.DMA_ADDRESS) {
            this.dma.write(value, this);
        }
        super.write(addr, value);
    }

    /**
     * Builds the memory map for the CPU.
     * @param ppu Connected PPU.
     * @return A memory map for the CPU
     */
    private static Map<Integer, Memory> memoryMap(final PPU ppu) {
        Map<Integer, Memory> result = new HashMap<>();
        result.put(0x0000, new ByteMemory(0x800));
        result.put(0x2000, ppu);
        result.put(0x4000, new APU());
        result.put(0x4018, new Test());
        return result;
    }

}
