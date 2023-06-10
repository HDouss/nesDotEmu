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
     * PPU Bus.
     */
    private PPUBus ppubus;

    public NES() throws InterruptedException {
        this.running = false;
        this.ppubus = new PPUBus();
        final PPU ppu = new PPU(this.ppubus);
        this.bus = new Bus(ppu);
        this.cpu = new Cpu(this.bus);
        this.clock = new Clock(this.cpu, ppu);
        this.cartridge = Optional.empty();
    }

    /**
     * Switch the NES on or off.
     */
    public void toggleOn() {
        this.running = !this.running;
        if (this.running) {
            this.bus.insert(this.cartridge);
            this.ppubus.insert(this.cartridge);
            this.cpu.on();
            this.clock.start();
        } else {
            this.clock.stop();
            this.cpu.off();
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

    public void reset() {
        if (this.running) {
            this.toggleOn();
            this.toggleOn();
        }
    }
}
