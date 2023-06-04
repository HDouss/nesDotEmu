package emu.nes.cartridge;

/**
 * Utility class to return concrete class implementation from the mapper number and submapper.
 * @author hdouss
 *
 */
public class Mappers {

    /**
     * Returns a mapper instance from the passed number and submapper
     * @param num Mapper number
     * @param sub Submapper
     * @return Actual mapper
     */
    public static Mapper resolve(int num, int sub) {
        return new Mapper000();
    }

}
