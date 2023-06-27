package emu.nes;

import emu.nes.cartridge.Cartridge;
import emu.nes.cpu.Bus;
import emu.nes.cpu.Cpu;
import emu.nes.graphics.PPU;
import emu.nes.graphics.PPUBus;
import java.util.Optional;

public class NES {

    /**
     * Running state.
     */
    private boolean running;

    /**
     * CPU Bus.
     */
    private Bus bus;

    /**
     * Internal clock ticking CPU, PPU and APU.
     */
    private Clock clock;

    /**
     * Optionally an inserted cartridge.
     */
    private Optional<Cartridge> cartridge;

    /**
     * NES CPU.
     */
    private Cpu cpu;

    /**
     * NES PPU.
     */
    private PPU ppu;

    /**
     * PPU Bus.
     */
    private PPUBus ppubus;

    public NES() throws InterruptedException {
        this.running = false;
        this.ppubus = new PPUBus();
        this.ppu = new PPU(this.ppubus);
        this.bus = new Bus(this.ppu, new DMA(this.ppu));
        this.cpu = new Cpu(this.bus);
        this.clock = new Clock(this.cpu, this.ppu);
        this.cartridge = Optional.empty();
    }

    /**
     * Switch the NES on or off.
     * @param gui 
     */
    public void toggleOn(final GUI gui) {
        this.running = !this.running;
        if (this.running) {
            this.bus.insert(this.cartridge);
            this.ppubus.insert(this.cartridge);
            this.cpu.on();
            this.clock.start(gui);
        } else {
            this.clock.stop();
            this.cpu.off();
            this.ppu.off();
        }
    }

    public void insert(Cartridge cartridge) {
        this.cartridge = Optional.of(cartridge);
    }

    public void eject() {
        this.cartridge = Optional.empty();
        this.bus.insert(this.cartridge);
        this.ppubus.insert(this.cartridge);
    }

    public void reset(final GUI gui) {
        if (this.running) {
            this.toggleOn(gui);
            this.toggleOn(gui);
        }
    }

    /**
     * Accessor for the NES CPU.
     * @return Nes CPU
     */
    public Cpu cpu() {
        return this.cpu;
    }

    /**
     * Accessor for the NES PPU.
     * @return Nes PPU
     */
    public PPU ppu() {
        return this.ppu;
    }
}
