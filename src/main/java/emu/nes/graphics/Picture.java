package emu.nes.graphics;

import java.awt.image.BufferedImage;
import emu.nes.GUI;

public class Picture {

    /**
     * NES screen video output height in pixels. 
     */
    public static final int NES_HEIGHT = 240;

    /**
     * NES screen video output width in pixels.
     */
    public static final int NES_WIDTH = 256;

    /**
     * Magnifying factor.
     */
    private static final int FACTOR = 3;

    /**
     * GUI screen video output height in pixels. 
     */
    public static final int SCREEN_HEIGHT = NES_HEIGHT * FACTOR;

    /**
     * GUI screen video output width in pixels. 
     */
    public static final int SCREEN_WIDTH = NES_WIDTH * FACTOR;

    /**
     * Application GUI.
     */
    private GUI gui;

    public Picture(GUI gui) {
        this.gui = gui;
    }

    public void draw(PPU ppu) {
        final Frame frame = ppu.getFrame();
        BufferedImage output = new BufferedImage(
            Picture.SCREEN_WIDTH, Picture.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB
        );
        for (int xcor = 0; xcor < Picture.SCREEN_WIDTH; ++xcor) {
            for (int ycor = 0; ycor < Picture.SCREEN_HEIGHT; ++ycor) {
                output.setRGB(
                    xcor, ycor, frame.getColor(xcor / Picture.FACTOR, ycor / Picture.FACTOR)
                );
            }
        }
        this.gui.setFrame(output);
        this.gui.repaint();
    }

}
