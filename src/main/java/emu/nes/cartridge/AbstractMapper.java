package emu.nes.cartridge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import emu.nes.graphics.HorizontalCIRAM;
import emu.nes.graphics.Tile;
import emu.nes.graphics.VerticalCIRAM;
import emu.nes.memory.Memory;

/**
 * Abstract mapper with a simple implementation for the cartridge informations setters. 
 * @author hdouss
 *
 */
public abstract class AbstractMapper implements Mapper {

    /**
     * How many bytes per tiles (assumes 2 bits per pixel).
     */
    private static final int TILE_BYTES = (Tile.TILE_PIXELS * 2) / 8;

    /**
     * Trainer data.
     */
    protected byte[] trainer;

    /**
     * Program data.
     */
    protected byte[] prg;

    /**
     * CHR data
     */
    protected byte[] chr;

    /**
     * Additional misc data.
     */
    protected byte[] misc;

    /**
     * Nametable mirroring mode.
     */
    protected int mirroring;

    /**
     * Ram size.
     */
    protected int ramSize;

    /**
     * Non volatile ram size.
     */
    protected int nvRamSize;

    /**
     * CH ram size.
     */
    protected int chRamSize;

    /**
     * Non volatile CH ram size.
     */
    protected int nvChRamSize;

    /**
     * Array of tiles existing in the cartridge.
     */
    protected Tile[] tiles;

    /**
     * Setter for Trainer data.
     */
    public void setTrainer(final byte[] trainer) {
        this.trainer = trainer;
    }

    /**
     * Setter for Program data.
     */
    public void setPRG(final byte[] program) {
        this.prg = program;
    }

    /**
     * Setter for CHR data. Fills the tiles array along the way.
     */
    public void setCHR(final byte[] chr) {
        this.chr = chr;
        final List<Tile> tileList = new ArrayList<>(chr.length / AbstractMapper.TILE_BYTES);
        int cursor = 0;
        while (cursor < chr.length) {
            final byte[] raw = Arrays.copyOfRange(chr, cursor, cursor + AbstractMapper.TILE_BYTES);
            tileList.add(new Tile(raw));
            cursor = cursor + AbstractMapper.TILE_BYTES;
        }
        this.tiles = tileList.toArray(new Tile[0]);
    }

    /**
     * Setter for Additional misc data.
     */
    public void setAdditionalRom(final byte[] misc) {
        this.misc = misc;
    }

    /**
     * Setter for nametable mirroring mode.
     */
    public void setNametableMirroring(final int mode) {
        this.mirroring = mode;
    }

    /**
     * Setter for Ram size.
     */
    public void setRAMSize(final int size) {
        this.ramSize = size;
    }

    /**
     * Setter for Non volatile ram size.
     */
    public void setNVRAMSize(final int size) {
        this.nvRamSize = size;
    }

    /**
     * Setter for CH ram size.
     */
    public void setCHRAMSize(final int size) {
        this.chRamSize = size;
    }

    /**
     * Setter for Non volatile CH ram size.
     */
    public void setNVCHRAMSize(final int size) {
        this.nvChRamSize = size;
    }

    @Override
    public Memory nametable() {
        Memory result = this;
        if (this.mirroring == Mapper.HORIZONTAL) {
            result = new HorizontalCIRAM();
        }
        if (this.mirroring == Mapper.VERTICAL) {
            result = new VerticalCIRAM();
        }
        return result;
    }

}
