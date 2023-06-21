package emu.nes.cartridge;


/**
 * Cartridge mapper interface. Acts as a memory for read/write operations for CPU and PPU.
 * Gives the possibility to set cartridge info read from the ROM file.
 * @author hdouss
 *
 */
public interface Mapper extends Content {

    /**
     * Horizontal mirroring mode constant. 
     */
    int HORIZONTAL = 0;

    /**
     * Vertical mirroring mode constant. 
     */
    int VERTICAL = 1;

    /**
     * Four screen mirroring mode constant. 
     */
    int FOUR_SCREEN = 2;

    /**
     * Sets trainer data.
     * @param trainer Trainer data.
     */
    public void setTrainer(byte[] trainer);

    /**
     * Sets program data.
     * @param program Program data
     */
    public void setPRG(final byte[] program);

    /**
     * Sets CHR data.
     * @param chr CHR data
     */
    public void setCHR(final byte[] chr);

    /**
     * Sets miscellaneous ROM Area content
     * @param misc Miscellaneous ROM area content
     */
    public void setAdditionalRom(final byte[] misc);

    /**
     * Sets nametable mirroring mode as read in the cartridge. Could be overridden by the mapper.
     * @param mode Nametable mirroring mode (see HORIZONTAL, VERTICAL and FOUR_SCREEN constants)
     */
    public void setNametableMirroring(final int mode);

    /**
     * Sets cartridge ram size
     * @param size RAM size
     */
    public void setRAMSize(final int size);

    /**
     * Sets non volatile cartridge ram size
     * @param size Non volatile RAM size
     */
    public void setNVRAMSize(final int size);

    /**
     * Sets cartridge CHR ram size
     * @param size CHR RAM size
     */
    public void setCHRAMSize(final int size);

    /**
     * Sets non volatile cartridge CHR ram size
     * @param size Non volatile CHR RAM size
     */
    public void setNVCHRAMSize(final int size);


}
