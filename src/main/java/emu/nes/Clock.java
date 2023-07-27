package emu.nes;

import emu.nes.cpu.Cpu;
import emu.nes.graphics.PPU;
import emu.nes.graphics.Picture;

/**
 * Clocking logic for CPU, PPU and APU.
 * @author hdouss
 *
 */
public class Clock {

    /**
     * Clock period in nanos. Assumes about 1.66Mhz for CPU and 5.37Mhz for PPU. 
     */
    protected static final int PERIOD = 200;

    /**
     * CPU.
     */
    private final Cpu cpu;

    /**
     * PPU.
     */
    private final PPU ppu;

    /**
     * Running boolean state.
     */
    protected boolean running;

    /**
     * Runnable logic. Essentially timing and ticking.
     */
    private Runnable runnable;

    /**
     * Thread controlling the runnable.
     */
    private Thread thread;

    /**
     * Picture instance to draw the frame. 
     */
    private Picture picture;

    public Clock(final Cpu cpu, final PPU ppu) throws InterruptedException {
        this.cpu = cpu;
        this.ppu = ppu;
        //final int waitTime = Clock.PERIOD / 2;
        this.runnable = new Runnable() {
            public void run() {
                int ticks = 0;
                long last = System.nanoTime();
                while (true) {
                    long now = System.nanoTime();
                    long duration = now - last;
                    if (duration > Clock.PERIOD) {
                        last = now;
                        boolean drawn = Clock.this.ppu.tick();
                        if (drawn) {
                            Clock.this.picture.draw(Clock.this.ppu);
                            //System.out.println("drawn at tick " + ticks);
                            if (Clock.this.ppu.nmiVBlank()) {
                                Clock.this.cpu.nmi();
                            }
                        }
                        if (ticks % 3 == 0) {
                            Clock.this.cpu.tick();
                        }
                        ticks++;
                    } else {
                        //LockSupport.parkNanos(waitTime);
                    }
                }
            }
        };
        this.thread = new Thread(this.runnable);
    }

    /**
     * Starts ticking the clock.
     * @param gui 
     */
    public void start(GUI gui) {
        if (!this.running) {
            this.picture = new Picture(gui);
            this.running = true;
            this.thread = new Thread(this.runnable);
            this.thread.setPriority(Thread.MAX_PRIORITY);
            this.thread.start();
        }
    }

    @SuppressWarnings("deprecation")
    public void stop() {
        this.running = false;
        this.thread.stop();
    }

}
