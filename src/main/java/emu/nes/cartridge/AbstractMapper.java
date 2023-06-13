package emu.nes.cartridge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import emu.nes.graphics.Tile;

/**
 * Abstract mapper with a simple implementation for the cartridge informations setters. 
 * @author hdouss
 *
 */
public abstract class AbstractMapper implements Mapper {

    /**
     * How many pixels per tile.
     */
    private static final int TILE_PIXELS = 8 * 8;

    /**
     * How many bytes per tiles (assumes 2 bits per pixel).
     */
    private static final int TILE_BYTES = (AbstractMapper.TILE_PIXELS * 2) / 8;

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
     * Four screen mode.
     */
    protected boolean fourscreen;

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
            tileList.add(new Tile(this.getPixelBytes(raw)));
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
     * Setter for Four screen mode.
     */
    public void setFourScreenMode(final boolean mode) {
        this.fourscreen = mode;
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

    /**
     * Calculates the bytes content of the tile. Every value of the array is either 0, 1, 2 or 3.
     */
    private byte[] getPixelBytes(final byte[] raw) {
        byte[] tile = new byte[AbstractMapper.TILE_PIXELS];
        for (int idx = 0; idx < 8; ++idx) {
            final int lsb = raw[idx] & 0xFF;
            final int msb = raw[idx + AbstractMapper.TILE_PIXELS / 8] & 0xFF;
            for (int pix = 0; pix < 8; ++pix) {
                tile[idx * 8 + pix] = (byte) (
                    ((lsb >> (7 - pix)) % 2) + 2 * ((msb >> (7 - pix)) % 2)
                );
            }
        }
        return tile;
    }
}
