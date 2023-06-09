package emu.nes.cpu;

import emu.nes.addressing.AddressingResult;
import emu.nes.addressing.Addressings;
import emu.nes.ops.Operations;

/**
 * NES CPU (6502) logic. Mainly implemented when ticked by the clock (tick method).
 * @author hdouss
 *
 */
public class Cpu {

    private Bus bus;
    private Registers registers;
    private int remaining = 0;

    int cycleCount = 7;

    public Cpu(Bus bus) {
        this.bus = bus;
        this.registers = new Registers();
    }

    /**
     * Ticks the cpu. Reads PC, fetches instruction, figure out operation and addressing mode, then
     * executes the instruction.
     * Finally, it retains how many cycles it has to wait for the next operation.
     * This only happens if cycles of previous operation elapsed.
     */
    public void tick() {
        if (this.remaining == 0) {
            System.out.println(String.format("%s CYC:%s", this, this.cycleCount));
            final int pc = this.registers.getPc();
            this.registers.setPc(pc + 1);
            byte inst = this.bus.read(pc);
            AddressingResult data = Addressings.get(inst).address(this.bus, this.registers, pc);
            this.remaining = Operations.cycles(inst)
                + Operations.get(inst).execute(this.registers, this.bus, data);
        }
        this.cycleCount++;
        this.remaining--;
    }

    /**
     * Puts on the CPU with the power up state. 
     */
    public void on() {
        int lsb = this.bus.read(0xFFFC);
        int msb = this.bus.read(0xFFFD);
        this.registers.setPc((lsb & 0xFF) | (msb << 8));
        // for nestest
        this.registers.setPc(0xC000);
    }

    /**
     * Put off the CPU. Resets the registers.
     */
    public void off() {
        this.cycleCount = 7;
        this.registers.reset();
    }

    @Override
    public String toString() {
        return this.registers.toString();
    }
}
