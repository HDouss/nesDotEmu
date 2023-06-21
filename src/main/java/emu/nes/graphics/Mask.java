package emu.nes.graphics;

/**
 * PPU mask register.
 */
public class Mask {

    /**
     * PPU Mask register content. 
     */
    private byte content;

    /**
     * Builds a PPMASK register with the given data.
     * @param data Register data
     */
    public Mask(final byte data) {
        this.content = data;
    }

    /**
     * Gives the grayscale property based on the mask register.
     * @return Whether the grayscale mode is enabled.
     */
    public boolean isGrayscale() {
        return (this.content & 0x1) > 0;
    }

    /**
     * Gives the show background in leftmost 8 pixels property based on the mask register.
     * @return Whether the background is shown in leftmost 8 pixels of the screen.
     */
    public boolean showLeftBackground() {
        return (this.content & 0x2) > 0;
    }

    /**
     * Gives the show sprites in leftmost 8 pixels property based on the mask register.
     * @return Whether the sprites are shown in leftmost 8 pixels of the screen.
     */
    public boolean showLeftSprites() {
        return (this.content & 0x4) > 0;
    }

    /**
     * Gives the show background property based on the mask register.
     * @return Whether the background is shown.
     */
    public boolean showBackground() {
        return (this.content & 0x8) > 0;
    }

    /**
     * Gives the show sprites property based on the mask register.
     * @return Whether the sprites are shown.
     */
    public boolean showSprites() {
        return (this.content & 0x10) > 0;
    }

    /**
     * Gives the emphasize red property based on the mask register.
     * @return Whether the red should be emphasized.
     */
    public boolean isRedEmphasized() {
        return (this.content & 0x20) > 0;
    }

    /**
     * Gives the emphasize green property based on the mask register.
     * @return Whether the green should be emphasized.
     */
    public boolean isGreenEmphasized() {
        return (this.content & 0x40) > 0;
    }

    /**
     * Gives the emphasize blue property based on the mask register.
     * @return Whether the blue should be emphasized.
     */
    public boolean isBlueEmphasized() {
        return (this.content & 0x80) > 0;
    }
}
