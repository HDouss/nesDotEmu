package emu.nes.graphics;

import java.io.PrintStream;
import java.util.Arrays;

public class Tile {
    
    private static final int ROW_PIXELS = 8;

    private byte[] data;

    public Tile(byte[] content) {
        this.data = content;
        /*for (int i = 0; i< 8;i++) {
            System.out.println(Arrays.toString(Arrays.copyOfRange(content, 8*i, 8*i+8)));
        }
        System.out.println();
        System.out.println();*/
    }

    public byte getPixel(final int xcor, final int ycor) {
        return this.data[xcor + ycor * Tile.ROW_PIXELS];
    }
}
