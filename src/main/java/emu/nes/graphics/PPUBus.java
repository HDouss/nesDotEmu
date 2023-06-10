package emu.nes.graphics;

import emu.nes.cartridge.Cartridge;
import emu.nes.memory.Memory;
import emu.nes.memory.MirroredByteMemory;
import emu.nes.memory.SelectorByteMemory;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * PPU Bus logic.
 * @author hdouss
 *
 */
public class PPUBus extends SelectorByteMemory {

    public PPUBus() {
        super(PPUBus.memoryMap());
    }

    /**
     * Connects the bus to the cartridge, or to void.
     * @param cartridge Cartridge or void to connect
     */
    public void insert(Optional<Cartridge> cartridge) {
        this.memories().remove(0x0000);
        if (cartridge.isPresent()) {
            this.memories().put(0x0000, cartridge.get());
        }
    }

    /**
     * Builds the memory map for the PPU.
     * @return A memory map for the PPU
     */
    private static Map<Integer, Memory> memoryMap() {
        Map<Integer, Memory> result = new HashMap<>();
        result.put(0x2000, new MirroredByteMemory(0x1000, 0x1000));
        result.put(0x3F00, new MirroredByteMemory(0x0020, 0x0020));
        return result;
    }

}
