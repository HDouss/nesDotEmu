package emu.nes.graphics;

public class Entry {

    /**
     * Attribute value for being in front of the background.
     */
    public static int FRONT = 0;

    /**
     * Attribute value for being behind the background.
     */
    public static int BEHIND = 1;

    /**
     * Sprite Y coordinate.
     */
    int spriteY;

    /**
     * Sprite Tile identifier.
     */
    int spriteTile;

    /**
     * Sprite attribute. 
     */
    byte spriteAttribute;

    /**
     * Sprite X coordinate.
     */
    int spriteX;

    /**
     * Gives the palette index based on the sprite attribute.
     * @return Palette index
     */
    public int getPalette() {
        return this.spriteAttribute & 0x3;
    }

    /**
     * Gives the priority related to background based on the sprite attribute.
     * @return Priority true if this sprite should be in front of background
     */
    public boolean getPriority() {
        return (this.spriteAttribute & 0x20) == 0;
    }

    /**
     * Gives the horizontally flipped property based on the sprite attribute.
     * @return Whether the sprite is flipped horizontally.
     */
    public boolean isFlippedHorizontally() {
        return (this.spriteAttribute & 0x40) > 0;
    }

    /**
     * Gives the vertically flipped property based on the sprite attribute.
     * @return Whether the sprite is flipped vertically.
     */
    public boolean isFlippedVertically() {
        return (this.spriteAttribute & 0x80) > 0;
    }
}
