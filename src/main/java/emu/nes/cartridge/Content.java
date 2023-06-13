package emu.nes.cartridge;

import emu.nes.graphics.Tile;
import emu.nes.memory.Memory;

/**
 * ROM file format. For now, gives only capabilities of {@link Memory} interface.
 * @author hdouss
 *
 */
public interface Content extends Memory {

    Tile getTile(final int bank, final int num);


}
