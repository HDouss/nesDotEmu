package emu.nes;

/**
 * DMA logic for dumping memory to the PPU. This happens when 0x4014 address is written to. 
 * @author hdouss
 *
 */
public class DMA implements Memory {

    @Override
    public byte read(int addr) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void write(int addr, byte value) {
        // TODO Auto-generated method stub

    }

}
