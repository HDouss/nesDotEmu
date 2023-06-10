package emu.nes.cpu;

import emu.nes.Test;
import emu.nes.cartridge.Cartridge;
import emu.nes.graphics.PPU;
import emu.nes.memory.Memory;
import emu.nes.memory.MirroredByteMemory;
import emu.nes.memory.SelectorByteMemory;
import emu.nes.sound.APU;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * CPU Bus logic.
 * @author hdouss
 *
 */
public class Bus extends SelectorByteMemory {

    public Bus(PPU ppu) {
        super(Bus.memoryMap(ppu));
    }

    /**
     * Connects the bus to the cartridge, or to void.
     * @param cartridge Cartridge or void to connect
     */
    public void insert(Optional<Cartridge> cartridge) {
        this.memories().remove(0x4020);
        if (cartridge.isPresent()) {
            this.memories().put(0x4020, cartridge.get());
        }
    }

    /**
     * Builds the memory map for the CPU.
     * @return A memory map for the CPU
     */
    private static Map<Integer, Memory> memoryMap(PPU ppu) {
        Map<Integer, Memory> result = new HashMap<>();
        result.put(0x0000, new MirroredByteMemory(0x800, 0x800));
        result.put(0x2000, ppu);
        result.put(0x4000, new APU());
        result.put(0x4018, new Test());
        return result;
    }

}
