package emu.nes.cartridge;

import emu.nes.Memory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Cartridge from ROM file.
 * @author hdouss
 *
 */
public class Cartridge implements Memory {

    private Content content;

    public Cartridge(final Path file) throws IOException {
        this.content = new Resolver(Files.readAllBytes(file));
    }

    @Override
    public int read(int addr) {
        return this.content.read(addr);
    }

    @Override
    public void write(int addr, int value) {
        this.content.write(addr, value);
    }

}
