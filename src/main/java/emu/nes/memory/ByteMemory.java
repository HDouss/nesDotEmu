package emu.nes.memory;

/**
 * Byte array backed memory defined by its size. A read/write always succeeds, because addresses
 * are modulo the size.
 */
public class ByteMemory implements Memory {

    private final byte[] data;

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
