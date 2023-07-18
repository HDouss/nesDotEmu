package emu.nes.cartridge;

import emu.nes.graphics.Tile;

/***
 * Mapper 000 implementation.
 * 
 * @author hdouss
 *
 */
public class Mapper000 extends AbstractMapper {

    private byte[] ram;

    public Mapper000() {
        this.ram = new byte[8 * 1024];
    }

    @Override
    public byte read(int addr) {
        if (addr > 0xFFFF) {
            // assumes a PPU read at that address
            return this.chr[(addr - 0x10000) % this.chr.length];
        }
        if (addr < 0x6000) {
            throw new RuntimeException("Illegal read address:" + addr);
        }
        if (addr < 0x8000) {
            return this.ram[(addr - 0x6000) % this.ramSize];
            // return 0;
        }
        return this.prg[(addr - 0x8000) % this.prg.length];
    }

    @Override
    public void write(int addr, byte value) {
        if (addr > 0xFFFF) {
            // assumes a PPU write at that address but this should not happen
            this.chr[(addr - 0x10000) % this.chr.length] = value;
        }
        if (addr < 0x6000 || addr > 0x7FFF) {
            throw new RuntimeException("Illegal write address:" + addr);
            //return;
        }
        this.ram[(addr - 0x6000) % this.ramSize] = value;
    }

    @Override
    public Tile getTile(int bank, int num) {
        return this.tiles[256 * bank + num];
    }

}
