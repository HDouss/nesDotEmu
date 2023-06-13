package emu.nes.graphics;

public class Tile {
    
    private static final int ROW_PIXELS = 8;

    private byte[] data;

    public Tile(byte[] content) {
        this.data = content;
    }

    public byte getPixel(final int xcor, final int ycor) {
        return this.data[xcor + ycor * Tile.ROW_PIXELS];
    }
}
