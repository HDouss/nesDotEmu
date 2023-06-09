package emu.nes.cartridge;

/***
 * Mapper 000 implementation.
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
        if (addr < 0x6000) {
            throw new RuntimeException("Illegal read address:" + addr);
        }
        if (addr < 0x8000) {
            return this.ram[(addr - 0x6000) % this.ramSize];
        }
        return this.prg[(addr - 0x8000) % this.prg.length];
    }

    @Override
    public void write(int addr, byte value) {
        if (addr < 0x6000 || addr > 0x7FFF) {
            throw new RuntimeException("Illegal write address:" + addr);
        }
        this.ram[(addr - 0x6000) % this.ramSize] = value;
    }

}
