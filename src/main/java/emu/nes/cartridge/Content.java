package emu.nes.cartridge;

import emu.nes.graphics.Tile;
import emu.nes.memory.Memory;

/**
 * ROM file format. For now, gives only capabilities of {@link Memory} interface.
 * @author hdouss
 *
 */
public interface Content extends Memory {

    /**
     * Retrieves a tile by its bank and number.
     * @param bank Bank number
     * @param num Tile number
     * @return Corresponding tile
     */
    Tile getTile(final int bank, final int num);

    /**
     * Figures out and builds the cartridge mapper. 
     * @param content ROM file content
     * @return Cartridge mapper
     */
    Memory nametable();

}
