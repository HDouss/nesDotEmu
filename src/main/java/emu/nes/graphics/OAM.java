package emu.nes.graphics;

/**
 * Object Attribute Memory accessible from the PPU bus.
 */
public class OAM {

    private Entry[] entries = new Entry[64];
    
    public OAM() {
        for (int i = 0; i < 64; i++) {
            entries[i] = new Entry();
        }
    }
}
