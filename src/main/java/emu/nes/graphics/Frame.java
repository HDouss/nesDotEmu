package emu.nes.graphics;

import java.awt.Color;

public class Frame {

    /**
     * RGB equivalent of NES colors.
     */
    private static final int[] RGB = {
        new Color(84, 84, 84).getRGB(),
        new Color(0, 30, 116).getRGB(),
        new Color(8, 16, 144).getRGB(),
        new Color(48, 0, 136).getRGB(),
        new Color(68, 0, 100).getRGB(),
        new Color(92, 0, 48).getRGB(),
        new Color(84, 4, 0).getRGB(),
        new Color(60, 24, 0).getRGB(),
        new Color(32, 42, 0).getRGB(),
        new Color(8, 58, 0).getRGB(),
        new Color(0, 64, 0).getRGB(),
        new Color(0, 60, 0).getRGB(),
        new Color(0, 50, 60).getRGB(),
        new Color(0, 0, 0).getRGB(),
        new Color(0, 0, 0).getRGB(),
        new Color(0, 0, 0).getRGB(),

        new Color(152, 150, 152).getRGB(),
        new Color(8, 76, 196).getRGB(),
        new Color(48, 50, 236).getRGB(),
        new Color(92, 30, 228).getRGB(),
        new Color(136, 20, 176).getRGB(),
        new Color(160, 20, 100).getRGB(),
        new Color(152, 34, 32).getRGB(),
        new Color(120, 60, 0).getRGB(),
        new Color(84, 90, 0).getRGB(),
        new Color(40, 114, 0).getRGB(),
        new Color(8, 124, 0).getRGB(),
        new Color(0, 118, 40).getRGB(),
        new Color(0, 102, 120).getRGB(),
        new Color(0, 0, 0).getRGB(),
        new Color(0, 0, 0).getRGB(),
        new Color(0, 0, 0).getRGB(),

        new Color(236, 238, 236).getRGB(),
        new Color(76, 154, 236).getRGB(),
        new Color(120, 124, 236).getRGB(),
        new Color(176, 98, 236).getRGB(),
        new Color(228, 84, 236).getRGB(),
        new Color(236, 88, 180).getRGB(),
        new Color(236, 106, 100).getRGB(),
        new Color(212, 136, 32).getRGB(),
        new Color(160, 170, 0).getRGB(),
        new Color(116, 196, 0).getRGB(),
        new Color(76, 208, 32).getRGB(),
        new Color(56, 204, 108).getRGB(),
        new Color(56, 180, 204).getRGB(),
        new Color(60, 60, 60).getRGB(),
        new Color(0, 0, 0).getRGB(),
        new Color(0, 0, 0).getRGB(),

        new Color(236, 238, 236).getRGB(),
        new Color(168, 204, 236).getRGB(),
        new Color(188, 188, 236).getRGB(),
        new Color(212, 178, 236).getRGB(),
        new Color(236, 174, 236).getRGB(),
        new Color(236, 174, 212).getRGB(),
        new Color(236, 180, 176).getRGB(),
        new Color(228, 196, 144).getRGB(),
        new Color(204, 210, 120).getRGB(),
        new Color(180, 222, 120).getRGB(),
        new Color(168, 226, 144).getRGB(),
        new Color(152, 226, 180).getRGB(),
        new Color(160, 214, 228).getRGB(),
        new Color(160, 162, 160).getRGB(),
        new Color(0, 0, 0).getRGB(),
        new Color(0, 0, 0).getRGB(),
    };

    /**
     * Pixels colors.
     */
    private int[][] pixels = new int[Picture.NES_WIDTH][];

    /**
     * Builds a frame object
     */
    public Frame() {
        for(int idx = 0; idx < Picture.NES_WIDTH; ++idx) {
            this.pixels[idx] = new int[Picture.NES_HEIGHT];
        }
    }

    public int getColor(final int xcor, final int ycor) {
        return this.pixels[xcor][ycor];
    }

    public void setColor(final int xcor, final int ycor, int color) {
        this.pixels[xcor][ycor] = this.getRgb(color);
    }

    /**
     * Converts a byte color value to an RGB value.
     * @param color byte color value from 0 to 63.
     * @return An RGB equivalent
     */
    private int getRgb(int color) {
        return Frame.RGB[color];
    }

}
