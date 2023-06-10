package emu.nes.cpu;

/**
 * CPU status flags.
 * @author hdouss
 *
 */
public class Status {

    /**
     * 8 bits status flags.
     */
    private byte status;

    public Status(byte st) {
        this.status = st;
    }

    /**
     * Accessor for the carry flag.
     * @return Carry flag value
     */
    public boolean carry() {
        return (status & 0x1) > 0;
    }

    /**
     * Accessor for the zero flag.
     * @return Zero flag value
     */
    public boolean zero() {
        return (status & 0x2) > 0;
    }

    /**
     * Accessor for the Interrupt disable flag.
     * @return Interrupt disable flag value
     */
    public boolean intDisable() {
        return (status & 0x4) > 0;
    }

    /**
     * Accessor for the decimal mode flag.
     * @return Decimal mode flag value
     */
    public boolean decimal() {
        return (status & 0x8) > 0;
    }

    /**
     * Accessor for the break flag.
     * @return Break flag value
     */
    public boolean brk() {
        return (status & 0x10) > 0;
    }

    /**
     * Accessor for the overflow flag.
     * @return Overflow flag value
     */
    public boolean overflow() {
        return (status & 0x40) > 0;
    }

    /**
     * Accessor for the negative flag.
     * @return Negative flag value
     */
    public boolean negative() {
        return (status & 0x80) > 0;
    }

    /**
     * Accessor for the whole status register value.
     * @return Status register value
     */
    public int status() {
        return status;
    }

    /**
     * Modifies the status register value.
     * @param status Status value to set
     */
    public void setStatus(byte status) {
        this.status = status;
    }

    /**
     * Modifies the carry flag.
     * @param carry Carry flag value to set
     */
    public void setCarry(boolean carry) {
        this.modify(0, carry);
    }

    /**
     * Modifies the zero flag.
     * @param zero Zero flag value to set
     */
    public void setZero(boolean zero) {
        this.modify(1, zero);
    }

    /**
     * Modifies the interrupt disable flag.
     * @param disable Interrupt disable flag value to set
     */
    public void setIntDisable(boolean disable) {
        this.modify(2, disable);
    }

    /**
     * Modifies the decimal flag.
     * @param decimal Decimal flag value to set
     */
    public void setDecimal(boolean decimal) {
        this.modify(3, decimal);
    }

    /**
     * Modifies the break flag.
     * @param break Break flag value to set
     */
    public void setBreak(boolean brk) {
        this.modify(4, brk);
    }

    /**
     * Modifies the overflow flag.
     * @param overflow Overflow flag value to set
     */
    public void setOverflow(boolean overflow) {
        this.modify(6, overflow);
    }

    /**
     * Modifies the negative flag.
     * @param negative Negative flag value to set
     */
    public void setNegative(boolean negative) {
        this.modify(7, negative);
    }

    /**
     * Modifies the passed indexed bit with the passed value.
     * @param idx Index of the bit to modify
     * @param value Value to set
     */
    private void modify(int idx, boolean value) {
        if (value) {
            status |= (1 << idx);
        } else {
            status &= ~(1 << idx);
        }
    }

    @Override
    public String toString() {
        return String.format("%02X", this.status);
    }
}
