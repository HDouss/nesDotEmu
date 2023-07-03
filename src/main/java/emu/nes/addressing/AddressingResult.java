package emu.nes.addressing;

import java.util.function.Function;

public class AddressingResult {

    /**
     * Address from which the data was fetched.
     */
    protected int address;

    /**
     * Whether a page boundary was crossed when fetching the data.
     */
    protected boolean crossed = false;

    /**
     * Function to retrieve the data.
     */
    protected Function<Integer, Byte> fetch;

    /**
     * Get the data fetched.
     * @return The data
     */
    public byte getData() {
        return this.fetch.apply(this.address);
    }

    /**
     * Get the address from which the data was fetched. This is either the memory address
     * or 0 if the data was not retrieved from memory, but from a CPU register.
     * @return The address
     */
    public int getAddress() {
        return address;
    }

    /**
     * Whether a page boundary was crossed when fetching the data.
     * @return A boolean to indicate if a page boundary was crossed.
     */
    public boolean isCrossed() {
        return crossed;
    }
}
