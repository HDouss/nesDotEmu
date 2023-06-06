package emu.nes.cpu;

import emu.nes.DMA;
import emu.nes.Memory;
import emu.nes.Test;
import emu.nes.cartridge.Cartridge;
import emu.nes.graphics.PPU;
import emu.nes.sound.APU;
import java.util.Optional;

/**
 * CPU Bus logic.
 * @author hdouss
 *
 */
public class Bus {

    /**
     * Optionally a connected cartridge.
     */
    private Optional<Cartridge> cartridge;

    /**
     * RAM.
     */
    private Ram ram = new Ram();

    /**
     * PPU.
     */
    private PPU ppu = new PPU();

    /**
     * DMA.
     */
    private DMA dma = new DMA();

    /**
     * APU.
     */
    private APU apu = new APU();

    /**
     * Normally disabled adresses.
     */
    private Test test = new Test();

    /**
     * Connects the bus to the cartridge, or to void.
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
            return Optional.of(this.ram);
        }
        if (addr < 0x4000) {
            return Optional.of(this.ppu);
        }
        if (addr == 0x4014) {
            return Optional.of(this.dma);
        }
        if (addr < 0x4018) {
            return Optional.of(this.apu);
        }
        if (addr < 0x4020) {
            return Optional.of(this.test);
        }
        if (addr < 0x10000) {
            return this.cartridge;
        }
        return result;
    }

}
