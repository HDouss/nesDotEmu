package emu.nes.graphics;

public class Tile {
    
    /**
     * How many pixels per tile.
     */
    public static final int TILE_PIXELS = 8 * 8;

    
    /**
     * How many pixels per row.
     */
    private static final int ROW_PIXELS = 8;

    private byte[] data;

    public Tile(byte[] content) {
        this.data = Tile.getPixelBytes(content);
    }

    public byte getPixel(final int xcor, final int ycor) {
        return this.data[xcor + ycor * Tile.ROW_PIXELS];
    }

    /**
     * Calculates the bytes content of the tile. Every value of the array is either 0, 1, 2 or 3.
     */
    private static byte[] getPixelBytes(final byte[] raw) {
        byte[] tile = new byte[Tile.TILE_PIXELS];
        for (int idx = 0; idx < 8; ++idx) {
            final int lsb = raw[idx] & 0xFF;
            final int msb = raw[idx + Tile.TILE_PIXELS / 8] & 0xFF;
            for (int pix = 0; pix < 8; ++pix) {
                tile[idx * 8 + pix] = (byte) (
                    ((lsb >> (7 - pix)) % 2) + 2 * ((msb >> (7 - pix)) % 2)
                );
            }
        }
        return tile;
    }
}
