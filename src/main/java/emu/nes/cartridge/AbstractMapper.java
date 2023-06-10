package emu.nes.cartridge;

/**
 * Abstract mapper with a simple implementation for the cartridge informations setters. 
 * @author hdouss
 *
 */
public abstract class AbstractMapper implements Mapper {

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
     * Setter for CHR data
     */
    public void setCHR(final byte[] chr) {
        this.chr = chr;
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
}
