package emu.nes.graphics;

import emu.nes.cartridge.Cartridge;
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

    /**
     * Nametable starting addressing range.
     */
    private static final int NAMETABLE_START_ADDRESS = 0x2000;

    /**
     * Cartridge starting addressing range.
     */
    private static final int CARTRIDGE_START_ADDRESS = 0x0000;

    /**
     * An optionally inserted cartridge.
     */
    private Optional<Cartridge> cartridge;

    /**
     * Builds a PPU Bus with adressable memories.
     */
    public PPUBus() {
        super(PPUBus.memoryMap());
    }

    /**
     * Connects the bus to the cartridge, or to void.
     * @param cartridge Cartridge or void to connect
     */
    public void insert(Optional<Cartridge> cartridge) {
        this.memories().remove(PPUBus.CARTRIDGE_START_ADDRESS);
        this.memories().remove(PPUBus.NAMETABLE_START_ADDRESS);
        this.cartridge = cartridge;
        if (cartridge.isPresent()) {
            this.memories().put(PPUBus.CARTRIDGE_START_ADDRESS, cartridge.get());
            this.memories().put(PPUBus.NAMETABLE_START_ADDRESS, cartridge.get().nametable());
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
        result.put(0x3F00, new Palette());
        return result;
    }

}
