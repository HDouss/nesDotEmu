package emu.nes.cartridge;

import java.util.Arrays;
import emu.nes.graphics.Tile;
import emu.nes.memory.Memory;

/**
 * Implements NES 2.0 ROM file format. Delegates read/write CPU and PPU operations to the mapper.
 * @author hdouss
 *
 */
public class NES20 implements Content {

    /**
     * Cartridge mapper.
     */
    private Mapper mapper;

    public NES20(byte[] content) {
        this.mapper = NES20.mapper(content);
    }

    @Override
    public byte read(int addr) {
        return this.mapper.read(addr);
    }

    @Override
    public void write(int addr, byte value) {
        this.mapper.write(addr, value);
    }

    @Override
    public Tile getTile(final int bank, final int num) {
        return this.mapper.getTile(bank, num);
    }

    @Override
    public Memory nametable() {
        return this.mapper.nametable();
    }

    /**
     * Figures out and builds the cartridge mapper. 
     * @param content ROM file content
     * @return Cartridge mapper
     */
    private static Mapper mapper(byte[] content) {
        int num = content[6] >> 4 + (content[7] & 0xF0) + ((content[8] & 0x0F) << 8);
        int sub = (content[8] & 0xF0) >> 4;
        Mapper result = Mappers.resolve(num, sub);
        int trainer = 0;
        if ((content[6] & 0x04) > 0) {
            trainer = 512;
            result.setTrainer(Arrays.copyOfRange(content, 16, 16 + 512));
        }
        int prgSize = NES20.getProgramSize(content);
        result.setPRG(Arrays.copyOfRange(content, 16 + trainer, 16 + trainer + prgSize));
        int chrSize = NES20.getChrSize(content);
        result.setCHR(
            Arrays.copyOfRange(
                content, 16 + trainer + prgSize, 16 + trainer + prgSize + chrSize
            )
        );
        result.setAdditionalRom(
            Arrays.copyOfRange(content, 16 + trainer + prgSize + chrSize, content.length)
        );
        result.setNametableMirroring(
            (content[6] & 0x08) > 0 ? Mapper.FOUR_SCREEN : content[6] & 0x01
        );
        int prgram = content[10] & 0x0F;
        int prgnvram = (content[10] & 0xF0) >> 4;
        int chrram = content[11] & 0x0F;
        int chrnvram = (content[11] & 0xF0) >> 4;
        result.setRAMSize(prgram == 0 ? 0 : 64 << prgram);
        result.setNVRAMSize(prgnvram == 0 ? 0 : 64 << prgnvram);
        result.setCHRAMSize(chrram == 0 ? 0 : 64 << chrram);
        result.setNVCHRAMSize(chrnvram == 0 ? 0 : 64 << chrnvram);
        return result;
    }

    /**
     * Retrieves Program content size with respect to NES 2.0 specifications.
     * @param content ROM content
     * @return Program size
     */
    private static int getProgramSize(byte[] content) {
        int msb = content[9] & 0x0F;
        int lsb = content[4];
        int size = (msb << 8) + lsb;
        if (msb == 0x0F) {
            size = size & 0xFF;
            int exp = size >> 2;
            int mult = size & 0x03;
            size = Double.valueOf(Math.pow(2, exp)).intValue() * (2 * mult + 1);
        } else {
            size = size * 16 * 1024;
        }
        return size;
    }

    /**
     * Retrieves CHR content size with respect to NES 2.0 specifications.
     * @param content ROM content
     * @return CHR size
     */
    private static int getChrSize(byte[] content) {
        int msb = content[9] & 0xF0;
        int lsb = content[5];
        int size = (msb << 8) + lsb;
        if (msb == 0x0F) {
            size = size & 0xFF;
            int exp = size >> 2;
            int mult = size & 0x03;
            size = Double.valueOf(Math.pow(2, exp)).intValue() * (2 * mult + 1);
        } else {
            size = size * 8 * 1024;
        }
        return size;
    }

}
