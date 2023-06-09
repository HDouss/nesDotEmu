package emu.nes;

/**
 * Responds to read/write from/to addresses 0x4018 to 0x401F (inclusive),
 * normally disabled but could be used for testing purposes.
 * @author hdouss
 *
 */
public class Test implements Memory {

    @Override
    public byte read(int addr) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void write(int addr, byte value) {
        // TODO Auto-generated method stub

    }

}
