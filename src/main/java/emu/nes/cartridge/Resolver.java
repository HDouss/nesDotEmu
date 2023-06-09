package emu.nes.cartridge;

/**
 * A content implementation that figures out the file format and delegates operations to the file
 * format implementation.
 * @author hdouss
 *
 */
public class Resolver implements Content {

    private Content rom;

    public Resolver(byte[] content) {
        this.rom = Resolver.resolve(content);
    }

    /**
     * Figure out the file format from the content.
     * For now only supports NES 2.0 format.
     * @param content cartridge content
     * @return
     */
    private static Content resolve(byte[] content) {
        return new NES20(content);
    }

    @Override
    public byte read(int addr) {
        return this.rom.read(addr);
    }

    @Override
    public void write(int addr, byte value) {
        this.rom.write(addr, value);
    }

}
