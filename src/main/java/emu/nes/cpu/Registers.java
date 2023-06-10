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
    private byte acc;

    /**
     * 8 bits x register.
     */
    private byte x;

    /**
     * 8 bits y register.
     */
    private byte y;

    /**
     * 16 bits program counter.
     */
    private int pc;

    /**
     * 8 bits stack pointer.
     */
    private byte stack = (byte) 0xFD;

    /**
     * 8 (actually 6 useful) bits status flags.
     */
    private Status status = new Status((byte) 0x24);

    /**
     * Accumulator getter.
     * @return Accumulator value
     */
    public byte getAcc() {
        return acc;
    }

    /**
     * Accumulator setter.
     * @param acc Accumulator value to set
     */
    public void setAcc(byte acc) {
        this.acc = acc;
    }

    /**
     * X register getter.
     * @return X register value
     */
    public byte getX() {
        return x;
    }

    /**
     * X register setter.
     * @param x X register value to set
     */
    public void setX(byte x) {
        this.x = x;
    }

    /**
     * Y register getter.
     * @return Y register value
     */
    public byte getY() {
        return y;
    }

    /**
     * Y register setter.
     * @param y Y register value to set
     */
    public void setY(byte y) {
        this.y = y;
    }

    /**
     * Program counter getter.
     * @return Program counter value
     */
    public int getPc() {
        return this.pc;
    }

    /**
     * Program counter setter.
     * @param pc Program counter value to set
     */
    public void setPc(int pc) {
        this.pc = pc & 0xFFFF;
    }

    /**
     * Stack value getter.
     * @return Stack value
     */
    public byte getStack() {
        return stack;
    }

    /**
     * Stack value setter.
     * @param stack Stack value to set
     */
    public void setStack(byte stack) {
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
        this.stack = (byte) 0xFD;
        this.status.setStatus((byte) 0x24);
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
