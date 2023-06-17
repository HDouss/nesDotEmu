package emu.nes.graphics;

import emu.nes.cartridge.Cartridge;
import emu.nes.memory.ByteMemory;
import emu.nes.memory.Memory;
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

    private Optional<Cartridge> cartridge;

    public PPUBus() {
        super(PPUBus.memoryMap());
    }

    /**
     * Connects the bus to the cartridge, or to void.
     * @param cartridge Cartridge or void to connect
     */
    public void insert(Optional<Cartridge> cartridge) {
        this.memories().remove(0x0000);
        this.cartridge = cartridge;
        if (cartridge.isPresent()) {
            this.memories().put(0x0000, cartridge.get());
        }
    }

    
    public Tile getTile(final int bank, final int num) {
        if (this.cartridge.isPresent()) {
            return this.cartridge.get().getTile(bank, num);
        } else {
            return new Tile(new byte[0]);
        }
    }
    /**
     * Builds the memory map for the PPU.
     * @return A memory map for the PPU
     */
    private static Map<Integer, Memory> memoryMap() {
        Map<Integer, Memory> result = new HashMap<>();
        result.put(0x2000, new ByteMemory(0x1000));
        result.put(0x3F00, new ByteMemory(0x0020));
        return result;
    }

}
