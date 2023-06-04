package emu.nes.addressing;

public class AddressingResult {

    /**
     * Fetched data.
     */
    protected int data;

    /**
     * Address from which the data was fetched.
     */
    protected int address;

    /**
     * Whether a page boundary was crossed when fetching the data.
     */
    protected boolean crossed = false;

    /**
     * Get the data fetched.
     * @return The data
     */
    public int getData() {
        return data;
    }

    /**
     * Get the address from which the data was fetched.
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
