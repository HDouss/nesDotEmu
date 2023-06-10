package emu.nes.memory;

/**
 * Memory abstraction describing all chips that can be read from and written to.
 * @author hdouss
 *
 */
public interface Memory {

    /**
     * Read from an address.
     * @param addr Address to read from
     * @return Value stored in the passed address
     */
    public byte read(final int addr);

    /**
     * Writes a value to an address.
     * @param addr Address to write to
     * @param value Value to write
     */
    public void write(final int addr, final byte value);
}
