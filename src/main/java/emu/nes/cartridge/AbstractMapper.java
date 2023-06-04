package emu.nes.cartridge;

/**
 * Abstract mapper with a simple implementation for the cartridge informations setters. 
 * @author hdouss
 *
 */
public abstract class AbstractMapper implements Mapper {

    protected byte[] trainer;

    protected byte[] prg;

    protected byte[] chr;

    protected byte[] misc;

    protected boolean fourscreen;

    protected int ramSize;

    protected int nvRamSize;

    protected int chRamSize;

    protected int nvChRamSize;

    public void setTrainer(final byte[] trainer) {
        this.trainer = trainer;
    }

    public void setPRG(final byte[] program) {
        this.prg = program;
    }

    public void setCHR(final byte[] chr) {
        this.chr = chr;
    }

    public void setAdditionalRom(final byte[] misc) {
        this.misc = misc;
    }

    public void setFourScreenMode(final boolean mode) {
        this.fourscreen = mode;
    }

    public void setRAMSize(final int size) {
        this.ramSize = size;
    }

    public void setNVRAMSize(final int size) {
        this.nvRamSize = size;
    }

    public void setCHRAMSize(final int size) {
        this.chRamSize = size;
    }

    public void setNVCHRAMSize(final int size) {
        this.nvChRamSize = size;
    }
}
