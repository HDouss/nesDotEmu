package emu.nes.cpu;

/**
 * CPU registers.
 * @author hdouss
 *
 */
public class Registers {

    /**
     * 8 bits accumulator.
     */
    private int acc;

    /**
     * 8 bits x register.
     */
    private int x;

    /**
     * 8 bits y register.
     */
    private int y;

    /**
     * 16 bits program counter.
     */
    private int pc;

    /**
     * 8 bits stack pointer.
     */
    private int stack = 0xFD;

    /**
     * 8 (actually 6 useful) bits status flags.
     */
    private Status status = new Status(0x24);

    /**
     * Accumulator getter.
     * @return Accumulator value
     */
    public int getAcc() {
        return acc;
    }

    /**
     * Accumulator setter.
     * @param acc Accumulator value to set
     */
    public void setAcc(int acc) {
        this.acc = acc;
    }

    /**
     * X register getter.
     * @return X register value
     */
    public int getX() {
        return x;
    }

    /**
     * X register setter.
     * @param x X register value to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Y register getter.
     * @return Y register value
     */
    public int getY() {
        return y;
    }

    /**
     * Y register setter.
     * @param y Y register value to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Program counter getter.
     * @return Program counter value
     */
    public int getPc() {
        return pc;
    }

    /**
     * Program counter setter.
     * @param pc Program counter value to set
     */
    public void setPc(int pc) {
        this.pc = pc;
    }

    /**
     * Stack value getter.
     * @return Stack value
     */
    public int getStack() {
        return stack;
    }

    /**
     * Stack value setter.
     * @param stack Stack value to set
     */
    public void setStack(int stack) {
        this.stack = stack;
    }

    /**
     * Status flags getter.
     * @return Status flags value
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Status flags setter.
     * @param status Status flags value to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Resets all registers.
     */
    public void reset() {
        this.acc = 0;
        this.x = 0;
        this.y = 0;
        this.stack = 0xFD;
        this.status.setStatus(0x24);
    }

    @Override
    public String toString() {
        return String.format(
            "PC:%04X A:%02X X:%02X Y:%02X P:%s SP:%02X",
            this.pc, this.acc, this.x, this.y,
            this.status, this.stack
        );
    }
}
