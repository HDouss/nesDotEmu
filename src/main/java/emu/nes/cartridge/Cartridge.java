package emu.nes.cartridge;

import emu.nes.graphics.Tile;
import emu.nes.memory.Memory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Cartridge from ROM file. As a memory, the implementation compensates the offset made by the bus,
 * and delegates read/write operations to the rom file format implementation.
 * @author hdouss
 *
 */
public class Cartridge implements Memory {

    private Content content;

    public Cartridge(final Path file) throws IOException {
        this.content = new Resolver(Files.readAllBytes(file));
    }

    @Override
    public byte read(int addr) {
        return this.content.read(addr + 0x4020);
    }

    @Override
    public void write(int addr, byte value) {
        this.content.write(addr + 0x4020, value);
    }

    public Tile getTile(final int bank, final int num) {
        //System.out.println(String.format("getting tile %s from bank %s", num, bank));
        return this.content.getTile(bank, num);
    }

    /**
     * Returns the nametable memory (CIRAM or in cartridge) with appropriate mirroring.
     * @return Nametable memory kind
     */
    public Memory nametable() {
        return this.content.nametable();
    }

}
