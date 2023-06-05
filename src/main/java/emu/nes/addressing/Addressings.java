package emu.nes.addressing;

import java.util.HashMap;
import java.util.Map;

/**
 * Essentially a data holder that maps an OPCODE to its corresponding addressing mode.
 * @author hdouss
 *
 */
public class Addressings {

    private static Map<Integer, Addressing> map = new HashMap<>();
    private static final Addressing IMP = new Implied();
    private static final Addressing INDX = new IndirectX();
    private static final Addressing XXX = new Unknown();
    private static final Addressing ZP = new ZeroPage();
    private static final Addressing IMM = new Immediate();
    private static final Addressing ACC = new Accumulator();
    private static final Addressing ABS = new Absolute();
    private static final Addressing REL = new Relative();
    private static final Addressing INDY = new IndirectY();
    private static final Addressing ZPX = new ZeroPageX();
    private static final Addressing ZPY = new ZeroPageY();
    private static final Addressing ABSY = new AbsoluteY();
    private static final Addressing ABSX = new AbsoluteX();
    private static final Addressing IND = new Indirect();
    static {

        map.put(0x0, Addressings.IMP);
        map.put(0x1, Addressings.INDX);
        map.put(0x2, Addressings.XXX);
        map.put(0x3, Addressings.INDX);
        map.put(0x4, Addressings.ZP);
        map.put(0x5, Addressings.ZP);
        map.put(0x6, Addressings.ZP);
        map.put(0x7, Addressings.ZP);
        map.put(0x8, Addressings.IMP);
        map.put(0x9, Addressings.IMM);
        map.put(0xA, Addressings.ACC);
        map.put(0xB, Addressings.IMM);
        map.put(0xC, Addressings.ABS);
        map.put(0xD, Addressings.ABS);
        map.put(0xE, Addressings.ABS);
        map.put(0xF, Addressings.ABS);

        map.put(0x10, Addressings.REL);
        map.put(0x11, Addressings.INDY);
        map.put(0x12, Addressings.XXX);
        map.put(0x13, Addressings.INDY);
        map.put(0x14, Addressings.ZPX);
        map.put(0x15, Addressings.ZPX);
        map.put(0x16, Addressings.ZPX);
        map.put(0x17, Addressings.ZPX);
        map.put(0x18, Addressings.IMP);
        map.put(0x19, Addressings.ABSY);
        map.put(0x1A, Addressings.IMP);
        map.put(0x1B, Addressings.ABSY);
        map.put(0x1C, Addressings.ABSX);
        map.put(0x1D, Addressings.ABSX);
        map.put(0x1E, Addressings.ABSX);
        map.put(0x1F, Addressings.ABSX);

        map.put(0x20, Addressings.ABS);
        map.put(0x21, Addressings.INDX);
        map.put(0x22, Addressings.XXX);
        map.put(0x23, Addressings.INDX);
        map.put(0x24, Addressings.ZP);
        map.put(0x25, Addressings.ZP);
        map.put(0x26, Addressings.ZP);
        map.put(0x27, Addressings.ZP);
        map.put(0x28, Addressings.IMP);
        map.put(0x29, Addressings.IMM);
        map.put(0x2A, Addressings.ACC);
        map.put(0x2B, Addressings.IMM);
        map.put(0x2C, Addressings.ABS);
        map.put(0x2D, Addressings.ABS);
        map.put(0x2E, Addressings.ABS);
        map.put(0x2F, Addressings.ABS);

        map.put(0x30, Addressings.REL);
        map.put(0x31, Addressings.INDY);
        map.put(0x32, Addressings.XXX);
        map.put(0x33, Addressings.INDY);
        map.put(0x34, Addressings.ZPX);
        map.put(0x35, Addressings.ZPX);
        map.put(0x36, Addressings.ZPX);
        map.put(0x37, Addressings.ZPX);
        map.put(0x38, Addressings.IMP);
        map.put(0x39, Addressings.ABSY);
        map.put(0x3A, Addressings.IMP);
        map.put(0x3B, Addressings.ABSY);
        map.put(0x3C, Addressings.ABSX);
        map.put(0x3D, Addressings.ABSX);
        map.put(0x3E, Addressings.ABSX);
        map.put(0x3F, Addressings.ABSX);

        map.put(0x40, Addressings.IMP);
        map.put(0x41, Addressings.INDX);
        map.put(0x42, Addressings.XXX);
        map.put(0x43, Addressings.INDX);
        map.put(0x44, Addressings.ZP);
        map.put(0x45, Addressings.ZP);
        map.put(0x46, Addressings.ZP);
        map.put(0x47, Addressings.ZP);
        map.put(0x48, Addressings.IMP);
        map.put(0x49, Addressings.IMM);
        map.put(0x4A, Addressings.ACC);
        map.put(0x4B, Addressings.IMM);
        map.put(0x4C, Addressings.ABS);
        map.put(0x4D, Addressings.ABS);
        map.put(0x4E, Addressings.ABS);
        map.put(0x4F, Addressings.ABS);

        map.put(0x50, Addressings.REL);
        map.put(0x51, Addressings.INDY);
        map.put(0x52, Addressings.XXX);
        map.put(0x53, Addressings.INDY);
        map.put(0x54, Addressings.ZPX);
        map.put(0x55, Addressings.ZPX);
        map.put(0x56, Addressings.ZPX);
        map.put(0x57, Addressings.ZPX);
        map.put(0x58, Addressings.IMP);
        map.put(0x59, Addressings.ABSY);
        map.put(0x5A, Addressings.IMP);
        map.put(0x5B, Addressings.ABSY);
        map.put(0x5C, Addressings.ABSX);
        map.put(0x5D, Addressings.ABSX);
        map.put(0x5E, Addressings.ABSX);
        map.put(0x5F, Addressings.ABSX);

        map.put(0x60, Addressings.IMP);
        map.put(0x61, Addressings.INDX);
        map.put(0x62, Addressings.XXX);
        map.put(0x63, Addressings.INDX);
        map.put(0x64, Addressings.ZP);
        map.put(0x65, Addressings.ZP);
        map.put(0x66, Addressings.ZP);
        map.put(0x67, Addressings.ZP);
        map.put(0x68, Addressings.IMP);
        map.put(0x69, Addressings.IMM);
        map.put(0x6A, Addressings.ACC);
        map.put(0x6B, Addressings.IMM);
        map.put(0x6C, Addressings.IND);
        map.put(0x6D, Addressings.ABS);
        map.put(0x6E, Addressings.ABS);
        map.put(0x6F, Addressings.ABS);

        map.put(0x70, Addressings.REL);
        map.put(0x71, Addressings.INDY);
        map.put(0x72, Addressings.XXX);
        map.put(0x73, Addressings.INDY);
        map.put(0x74, Addressings.ZPX);
        map.put(0x75, Addressings.ZPX);
        map.put(0x76, Addressings.ZPX);
        map.put(0x77, Addressings.ZPX);
        map.put(0x78, Addressings.IMP);
        map.put(0x79, Addressings.ABSY);
        map.put(0x7A, Addressings.IMP);
        map.put(0x7B, Addressings.ABSY);
        map.put(0x7C, Addressings.ABSX);
        map.put(0x7D, Addressings.ABSX);
        map.put(0x7E, Addressings.ABSX);
        map.put(0x7F, Addressings.ABSX);

        map.put(0x80, Addressings.IMM);
        map.put(0x81, Addressings.INDX);
        map.put(0x82, Addressings.IMM);
        map.put(0x83, Addressings.INDX);
        map.put(0x84, Addressings.ZP);
        map.put(0x85, Addressings.ZP);
        map.put(0x86, Addressings.ZP);
        map.put(0x87, Addressings.ZP);
        map.put(0x88, Addressings.IMP);
        map.put(0x89, Addressings.IMM);
        map.put(0x8A, Addressings.IMP);
        map.put(0x8B, Addressings.IMM);
        map.put(0x8C, Addressings.ABS);
        map.put(0x8D, Addressings.ABS);
        map.put(0x8E, Addressings.ABS);
        map.put(0x8F, Addressings.ABS);

        map.put(0x90, Addressings.REL);
        map.put(0x91, Addressings.INDY);
        map.put(0x92, Addressings.XXX);
        map.put(0x93, Addressings.INDY);
        map.put(0x94, Addressings.ZPX);
        map.put(0x95, Addressings.ZPX);
        map.put(0x96, Addressings.ZPY);
        map.put(0x97, Addressings.ZPY);
        map.put(0x98, Addressings.IMP);
        map.put(0x99, Addressings.ABSY);
        map.put(0x9A, Addressings.IMP);
        map.put(0x9B, Addressings.ABSY);
        map.put(0x9C, Addressings.ABSX);
        map.put(0x9D, Addressings.ABSX);
        map.put(0x9E, Addressings.ABSY);
        map.put(0x9F, Addressings.ABSY);

        map.put(0xA0, Addressings.IMM);
        map.put(0xA1, Addressings.INDX);
        map.put(0xA2, Addressings.IMM);
        map.put(0xA3, Addressings.INDX);
        map.put(0xA4, Addressings.ZP);
        map.put(0xA5, Addressings.ZP);
        map.put(0xA6, Addressings.ZP);
        map.put(0xA7, Addressings.ZP);
        map.put(0xA8, Addressings.IMP);
        map.put(0xA9, Addressings.IMM);
        map.put(0xAA, Addressings.IMP);
        map.put(0xAB, Addressings.IMM);
        map.put(0xAC, Addressings.ABS);
        map.put(0xAD, Addressings.ABS);
        map.put(0xAE, Addressings.ABS);
        map.put(0xAF, Addressings.ABS);

        map.put(0xB0, Addressings.REL);
        map.put(0xB1, Addressings.INDY);
        map.put(0xB2, Addressings.XXX);
        map.put(0xB3, Addressings.INDY);
        map.put(0xB4, Addressings.ZPX);
        map.put(0xB5, Addressings.ZPX);
        map.put(0xB6, Addressings.ZPY);
        map.put(0xB7, Addressings.ZPY);
        map.put(0xB8, Addressings.IMP);
        map.put(0xB9, Addressings.ABSY);
        map.put(0xBA, Addressings.IMP);
        map.put(0xBB, Addressings.ABSY);
        map.put(0xBC, Addressings.ABSX);
        map.put(0xBD, Addressings.ABSX);
        map.put(0xBE, Addressings.ABSY);
        map.put(0xBF, Addressings.ABSY);

        map.put(0xC0, Addressings.IMM);
        map.put(0xC1, Addressings.INDX);
        map.put(0xC2, Addressings.IMM);
        map.put(0xC3, Addressings.INDX);
        map.put(0xC4, Addressings.ZP);
        map.put(0xC5, Addressings.ZP);
        map.put(0xC6, Addressings.ZP);
        map.put(0xC7, Addressings.ZP);
        map.put(0xC8, Addressings.IMP);
        map.put(0xC9, Addressings.IMM);
        map.put(0xCA, Addressings.IMP);
        map.put(0xCB, Addressings.IMM);
        map.put(0xCC, Addressings.ABS);
        map.put(0xCD, Addressings.ABS);
        map.put(0xCE, Addressings.ABS);
        map.put(0xCF, Addressings.ABS);

        map.put(0xD0, Addressings.REL);
        map.put(0xD1, Addressings.INDY);
        map.put(0xD2, Addressings.XXX);
        map.put(0xD3, Addressings.INDY);
        map.put(0xD4, Addressings.ZPX);
        map.put(0xD5, Addressings.ZPX);
        map.put(0xD6, Addressings.ZPX);
        map.put(0xD7, Addressings.ZPX);
        map.put(0xD8, Addressings.IMP);
        map.put(0xD9, Addressings.ABSY);
        map.put(0xDA, Addressings.IMP);
        map.put(0xDB, Addressings.ABSY);
        map.put(0xDC, Addressings.ABSX);
        map.put(0xDD, Addressings.ABSX);
        map.put(0xDE, Addressings.ABSX);
        map.put(0xDF, Addressings.ABSX);

        map.put(0xE0, Addressings.IMM);
        map.put(0xE1, Addressings.INDX);
        map.put(0xE2, Addressings.IMM);
        map.put(0xE3, Addressings.INDX);
        map.put(0xE4, Addressings.ZP);
        map.put(0xE5, Addressings.ZP);
        map.put(0xE6, Addressings.ZP);
        map.put(0xE7, Addressings.ZP);
        map.put(0xE8, Addressings.IMP);
        map.put(0xE9, Addressings.IMM);
        map.put(0xEA, Addressings.IMP);
        map.put(0xEB, Addressings.IMM);
        map.put(0xEC, Addressings.ABS);
        map.put(0xED, Addressings.ABS);
        map.put(0xEE, Addressings.ABS);
        map.put(0xEF, Addressings.ABS);

        map.put(0xF0, Addressings.REL);
        map.put(0xF1, Addressings.INDY);
        map.put(0xF2, Addressings.XXX);
        map.put(0xF3, Addressings.INDY);
        map.put(0xF4, Addressings.ZPX);
        map.put(0xF5, Addressings.ZPX);
        map.put(0xF6, Addressings.ZPX);
        map.put(0xF7, Addressings.ZPX);
        map.put(0xF8, Addressings.IMP);
        map.put(0xF9, Addressings.ABSY);
        map.put(0xFA, Addressings.IMP);
        map.put(0xFB, Addressings.ABSY);
        map.put(0xFC, Addressings.ABSX);
        map.put(0xFD, Addressings.ABSX);
        map.put(0xFE, Addressings.ABSX);
        map.put(0xFF, Addressings.ABSX);
    }

    public static Addressing get(int inst) {
        return Addressings.map.get(inst);
    }

}
