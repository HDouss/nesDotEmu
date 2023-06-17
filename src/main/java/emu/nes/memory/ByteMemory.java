package emu.nes.memory;

/**
 * Byte array backed memory defined by its size. A read/write always succeeds, because addresses
 * are modulo the size. So it supports mirroring out of the box.
 */
public class ByteMemory implements Memory {

    /**
     * Memory data.
     */
    private final byte[] data;

    /**
     * Builds a byte memory with the given size in bytes.
     * @param size Data size
     */
    public ByteMemory(final int size) {
        this.data = new byte[size];
    }

    @Override
    public byte read(int addr) {
        return this.data[addr % this.data.length];
    }

    @Override
    public void write(int addr, byte value) {
        this.data[addr % this.data.length] = value;
    }

}
