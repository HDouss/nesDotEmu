package emu.nes.memory;

/**
 * Byte array backed memory that implements mirroring. Defined by its total size and mirroring size.
 */
public class MirroredByteMemory extends ByteMemory {

    /**
     * Mirroring size.
     */
    private int mirroring;

    /**
     * Constructor
     * @param size Total memory size
     * @param mirroring Mirroring size
     */
    public MirroredByteMemory(final int size, final int mirroring) {
        super(size);
        this.mirroring = mirroring;
    }

    @Override
    public byte read(int addr) {
        return super.read(addr % this.mirroring);
    }

    @Override
    public void write(int addr, byte value) {
        super.write(addr % this.mirroring, value);
    }

}
