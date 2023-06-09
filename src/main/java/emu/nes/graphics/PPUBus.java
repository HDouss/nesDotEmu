package emu.nes.graphics;

import java.util.Optional;
import emu.nes.Memory;
import emu.nes.cartridge.Cartridge;

/**
 * PPU Bus logic.
 * @author hdouss
 *
 */
public class PPUBus {

    /**
     * Optionally a connected cartridge.
     */
    private Optional<Cartridge> cartridge;
    
    /**
     * Nametable RAM (2KB).
     */
    private Nametable nametable;

    /**
     * Palette RAM (256B).
     */
    private Palette palette;

    /**
     * Connects the ppu bus to the cartridge, or to void.
     * @param cartridge Cartridge or void to connect
     */
    public void insert(Optional<Cartridge> cartridge) {
        this.cartridge = cartridge;
    }

    /**
     * Accessor to the cartridge.
     * @return Connected cartridge
     */
    public Optional<Cartridge> cartridge() {
        return this.cartridge;
    }

    public int read(final int addr) {
        Optional<? extends Memory> selected = this.memory(addr);
        if (selected.isPresent()) {
            return selected.get().read(addr) & 0xFF;
        }
        return 0;
    }

    public void write(final int addr, final int value) {
        Optional<? extends Memory> selected = this.memory(addr);
        if (selected.isPresent()) {
            selected.get().write(addr, value);
        }
    }

    /**
     * Selects the correct memory depending on the address.
     * @param addr Memory address
     * @return The memory corresponding to the address.
     */
    private Optional<? extends Memory> memory(int addr) {
        Optional<Memory> result = Optional.empty();
        if (addr < 0x2000) {
            return this.cartridge;
        }
        if (addr < 0x3F00) {
            return Optional.of(this.nametable);
        }
        if (addr == 0x4000) {
            return Optional.of(this.palette);
        }
        return result;
    }
}
