package emu.nes.addressing;

import java.util.HashMap;
import java.util.Map;

/**
 * Essentially a data holder that maps an OPCODE to its corresponding addressing mode.
 * @author hdouss
 *
 */
public class Addressings {

    private static Map<Byte, Addressing> map = new HashMap<>();
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

        Addressings.add(0x0, Addressings.IMP);
        Addressings.add(0x1, Addressings.INDX);
        Addressings.add(0x2, Addressings.XXX);
        Addressings.add(0x3, Addressings.INDX);
        Addressings.add(0x4, Addressings.ZP);
        Addressings.add(0x5, Addressings.ZP);
        Addressings.add(0x6, Addressings.ZP);
        Addressings.add(0x7, Addressings.ZP);
        Addressings.add(0x8, Addressings.IMP);
        Addressings.add(0x9, Addressings.IMM);
        Addressings.add(0xA, Addressings.ACC);
        Addressings.add(0xB, Addressings.IMM);
        Addressings.add(0xC, Addressings.ABS);
        Addressings.add(0xD, Addressings.ABS);
        Addressings.add(0xE, Addressings.ABS);
        Addressings.add(0xF, Addressings.ABS);

        Addressings.add(0x10, Addressings.REL);
        Addressings.add(0x11, Addressings.INDY);
        Addressings.add(0x12, Addressings.XXX);
        Addressings.add(0x13, Addressings.INDY);
        Addressings.add(0x14, Addressings.ZPX);
        Addressings.add(0x15, Addressings.ZPX);
        Addressings.add(0x16, Addressings.ZPX);
        Addressings.add(0x17, Addressings.ZPX);
        Addressings.add(0x18, Addressings.IMP);
        Addressings.add(0x19, Addressings.ABSY);
        Addressings.add(0x1A, Addressings.IMP);
        Addressings.add(0x1B, Addressings.ABSY);
        Addressings.add(0x1C, Addressings.ABSX);
        Addressings.add(0x1D, Addressings.ABSX);
        Addressings.add(0x1E, Addressings.ABSX);
        Addressings.add(0x1F, Addressings.ABSX);

        Addressings.add(0x20, Addressings.ABS);
        Addressings.add(0x21, Addressings.INDX);
        Addressings.add(0x22, Addressings.XXX);
        Addressings.add(0x23, Addressings.INDX);
        Addressings.add(0x24, Addressings.ZP);
        Addressings.add(0x25, Addressings.ZP);
        Addressings.add(0x26, Addressings.ZP);
        Addressings.add(0x27, Addressings.ZP);
        Addressings.add(0x28, Addressings.IMP);
        Addressings.add(0x29, Addressings.IMM);
        Addressings.add(0x2A, Addressings.ACC);
        Addressings.add(0x2B, Addressings.IMM);
        Addressings.add(0x2C, Addressings.ABS);
        Addressings.add(0x2D, Addressings.ABS);
        Addressings.add(0x2E, Addressings.ABS);
        Addressings.add(0x2F, Addressings.ABS);

        Addressings.add(0x30, Addressings.REL);
        Addressings.add(0x31, Addressings.INDY);
        Addressings.add(0x32, Addressings.XXX);
        Addressings.add(0x33, Addressings.INDY);
        Addressings.add(0x34, Addressings.ZPX);
        Addressings.add(0x35, Addressings.ZPX);
        Addressings.add(0x36, Addressings.ZPX);
        Addressings.add(0x37, Addressings.ZPX);
        Addressings.add(0x38, Addressings.IMP);
        Addressings.add(0x39, Addressings.ABSY);
        Addressings.add(0x3A, Addressings.IMP);
        Addressings.add(0x3B, Addressings.ABSY);
        Addressings.add(0x3C, Addressings.ABSX);
        Addressings.add(0x3D, Addressings.ABSX);
        Addressings.add(0x3E, Addressings.ABSX);
        Addressings.add(0x3F, Addressings.ABSX);

        Addressings.add(0x40, Addressings.IMP);
        Addressings.add(0x41, Addressings.INDX);
        Addressings.add(0x42, Addressings.XXX);
        Addressings.add(0x43, Addressings.INDX);
        Addressings.add(0x44, Addressings.ZP);
        Addressings.add(0x45, Addressings.ZP);
        Addressings.add(0x46, Addressings.ZP);
        Addressings.add(0x47, Addressings.ZP);
        Addressings.add(0x48, Addressings.IMP);
        Addressings.add(0x49, Addressings.IMM);
        Addressings.add(0x4A, Addressings.ACC);
        Addressings.add(0x4B, Addressings.IMM);
        Addressings.add(0x4C, Addressings.ABS);
        Addressings.add(0x4D, Addressings.ABS);
        Addressings.add(0x4E, Addressings.ABS);
        Addressings.add(0x4F, Addressings.ABS);

        Addressings.add(0x50, Addressings.REL);
        Addressings.add(0x51, Addressings.INDY);
        Addressings.add(0x52, Addressings.XXX);
        Addressings.add(0x53, Addressings.INDY);
        Addressings.add(0x54, Addressings.ZPX);
        Addressings.add(0x55, Addressings.ZPX);
        Addressings.add(0x56, Addressings.ZPX);
        Addressings.add(0x57, Addressings.ZPX);
        Addressings.add(0x58, Addressings.IMP);
        Addressings.add(0x59, Addressings.ABSY);
        Addressings.add(0x5A, Addressings.IMP);
        Addressings.add(0x5B, Addressings.ABSY);
        Addressings.add(0x5C, Addressings.ABSX);
        Addressings.add(0x5D, Addressings.ABSX);
        Addressings.add(0x5E, Addressings.ABSX);
        Addressings.add(0x5F, Addressings.ABSX);

        Addressings.add(0x60, Addressings.IMP);
        Addressings.add(0x61, Addressings.INDX);
        Addressings.add(0x62, Addressings.XXX);
        Addressings.add(0x63, Addressings.INDX);
        Addressings.add(0x64, Addressings.ZP);
        Addressings.add(0x65, Addressings.ZP);
        Addressings.add(0x66, Addressings.ZP);
        Addressings.add(0x67, Addressings.ZP);
        Addressings.add(0x68, Addressings.IMP);
        Addressings.add(0x69, Addressings.IMM);
        Addressings.add(0x6A, Addressings.ACC);
        Addressings.add(0x6B, Addressings.IMM);
        Addressings.add(0x6C, Addressings.IND);
        Addressings.add(0x6D, Addressings.ABS);
        Addressings.add(0x6E, Addressings.ABS);
        Addressings.add(0x6F, Addressings.ABS);

        Addressings.add(0x70, Addressings.REL);
        Addressings.add(0x71, Addressings.INDY);
        Addressings.add(0x72, Addressings.XXX);
        Addressings.add(0x73, Addressings.INDY);
        Addressings.add(0x74, Addressings.ZPX);
        Addressings.add(0x75, Addressings.ZPX);
        Addressings.add(0x76, Addressings.ZPX);
        Addressings.add(0x77, Addressings.ZPX);
        Addressings.add(0x78, Addressings.IMP);
        Addressings.add(0x79, Addressings.ABSY);
        Addressings.add(0x7A, Addressings.IMP);
        Addressings.add(0x7B, Addressings.ABSY);
        Addressings.add(0x7C, Addressings.ABSX);
        Addressings.add(0x7D, Addressings.ABSX);
        Addressings.add(0x7E, Addressings.ABSX);
        Addressings.add(0x7F, Addressings.ABSX);

        Addressings.add(0x80, Addressings.IMM);
        Addressings.add(0x81, Addressings.INDX);
        Addressings.add(0x82, Addressings.IMM);
        Addressings.add(0x83, Addressings.INDX);
        Addressings.add(0x84, Addressings.ZP);
        Addressings.add(0x85, Addressings.ZP);
        Addressings.add(0x86, Addressings.ZP);
        Addressings.add(0x87, Addressings.ZP);
        Addressings.add(0x88, Addressings.IMP);
        Addressings.add(0x89, Addressings.IMM);
        Addressings.add(0x8A, Addressings.IMP);
        Addressings.add(0x8B, Addressings.IMM);
        Addressings.add(0x8C, Addressings.ABS);
        Addressings.add(0x8D, Addressings.ABS);
        Addressings.add(0x8E, Addressings.ABS);
        Addressings.add(0x8F, Addressings.ABS);

        Addressings.add(0x90, Addressings.REL);
        Addressings.add(0x91, Addressings.INDY);
        Addressings.add(0x92, Addressings.XXX);
        Addressings.add(0x93, Addressings.INDY);
        Addressings.add(0x94, Addressings.ZPX);
        Addressings.add(0x95, Addressings.ZPX);
        Addressings.add(0x96, Addressings.ZPY);
        Addressings.add(0x97, Addressings.ZPY);
        Addressings.add(0x98, Addressings.IMP);
        Addressings.add(0x99, Addressings.ABSY);
        Addressings.add(0x9A, Addressings.IMP);
        Addressings.add(0x9B, Addressings.ABSY);
        Addressings.add(0x9C, Addressings.ABSX);
        Addressings.add(0x9D, Addressings.ABSX);
        Addressings.add(0x9E, Addressings.ABSY);
        Addressings.add(0x9F, Addressings.ABSY);

        Addressings.add(0xA0, Addressings.IMM);
        Addressings.add(0xA1, Addressings.INDX);
        Addressings.add(0xA2, Addressings.IMM);
        Addressings.add(0xA3, Addressings.INDX);
        Addressings.add(0xA4, Addressings.ZP);
        Addressings.add(0xA5, Addressings.ZP);
        Addressings.add(0xA6, Addressings.ZP);
        Addressings.add(0xA7, Addressings.ZP);
        Addressings.add(0xA8, Addressings.IMP);
        Addressings.add(0xA9, Addressings.IMM);
        Addressings.add(0xAA, Addressings.IMP);
        Addressings.add(0xAB, Addressings.IMM);
        Addressings.add(0xAC, Addressings.ABS);
        Addressings.add(0xAD, Addressings.ABS);
        Addressings.add(0xAE, Addressings.ABS);
        Addressings.add(0xAF, Addressings.ABS);

        Addressings.add(0xB0, Addressings.REL);
        Addressings.add(0xB1, Addressings.INDY);
        Addressings.add(0xB2, Addressings.XXX);
        Addressings.add(0xB3, Addressings.INDY);
        Addressings.add(0xB4, Addressings.ZPX);
        Addressings.add(0xB5, Addressings.ZPX);
        Addressings.add(0xB6, Addressings.ZPY);
        Addressings.add(0xB7, Addressings.ZPY);
        Addressings.add(0xB8, Addressings.IMP);
        Addressings.add(0xB9, Addressings.ABSY);
        Addressings.add(0xBA, Addressings.IMP);
        Addressings.add(0xBB, Addressings.ABSY);
        Addressings.add(0xBC, Addressings.ABSX);
        Addressings.add(0xBD, Addressings.ABSX);
        Addressings.add(0xBE, Addressings.ABSY);
        Addressings.add(0xBF, Addressings.ABSY);

        Addressings.add(0xC0, Addressings.IMM);
        Addressings.add(0xC1, Addressings.INDX);
        Addressings.add(0xC2, Addressings.IMM);
        Addressings.add(0xC3, Addressings.INDX);
        Addressings.add(0xC4, Addressings.ZP);
        Addressings.add(0xC5, Addressings.ZP);
        Addressings.add(0xC6, Addressings.ZP);
        Addressings.add(0xC7, Addressings.ZP);
        Addressings.add(0xC8, Addressings.IMP);
        Addressings.add(0xC9, Addressings.IMM);
        Addressings.add(0xCA, Addressings.IMP);
        Addressings.add(0xCB, Addressings.IMM);
        Addressings.add(0xCC, Addressings.ABS);
        Addressings.add(0xCD, Addressings.ABS);
        Addressings.add(0xCE, Addressings.ABS);
        Addressings.add(0xCF, Addressings.ABS);

        Addressings.add(0xD0, Addressings.REL);
        Addressings.add(0xD1, Addressings.INDY);
        Addressings.add(0xD2, Addressings.XXX);
        Addressings.add(0xD3, Addressings.INDY);
        Addressings.add(0xD4, Addressings.ZPX);
        Addressings.add(0xD5, Addressings.ZPX);
        Addressings.add(0xD6, Addressings.ZPX);
        Addressings.add(0xD7, Addressings.ZPX);
        Addressings.add(0xD8, Addressings.IMP);
        Addressings.add(0xD9, Addressings.ABSY);
        Addressings.add(0xDA, Addressings.IMP);
        Addressings.add(0xDB, Addressings.ABSY);
        Addressings.add(0xDC, Addressings.ABSX);
        Addressings.add(0xDD, Addressings.ABSX);
        Addressings.add(0xDE, Addressings.ABSX);
        Addressings.add(0xDF, Addressings.ABSX);

        Addressings.add(0xE0, Addressings.IMM);
        Addressings.add(0xE1, Addressings.INDX);
        Addressings.add(0xE2, Addressings.IMM);
        Addressings.add(0xE3, Addressings.INDX);
        Addressings.add(0xE4, Addressings.ZP);
        Addressings.add(0xE5, Addressings.ZP);
        Addressings.add(0xE6, Addressings.ZP);
        Addressings.add(0xE7, Addressings.ZP);
        Addressings.add(0xE8, Addressings.IMP);
        Addressings.add(0xE9, Addressings.IMM);
        Addressings.add(0xEA, Addressings.IMP);
        Addressings.add(0xEB, Addressings.IMM);
        Addressings.add(0xEC, Addressings.ABS);
        Addressings.add(0xED, Addressings.ABS);
        Addressings.add(0xEE, Addressings.ABS);
        Addressings.add(0xEF, Addressings.ABS);

        Addressings.add(0xF0, Addressings.REL);
        Addressings.add(0xF1, Addressings.INDY);
        Addressings.add(0xF2, Addressings.XXX);
        Addressings.add(0xF3, Addressings.INDY);
        Addressings.add(0xF4, Addressings.ZPX);
        Addressings.add(0xF5, Addressings.ZPX);
        Addressings.add(0xF6, Addressings.ZPX);
        Addressings.add(0xF7, Addressings.ZPX);
        Addressings.add(0xF8, Addressings.IMP);
        Addressings.add(0xF9, Addressings.ABSY);
        Addressings.add(0xFA, Addressings.IMP);
        Addressings.add(0xFB, Addressings.ABSY);
        Addressings.add(0xFC, Addressings.ABSX);
        Addressings.add(0xFD, Addressings.ABSX);
        Addressings.add(0xFE, Addressings.ABSX);
        Addressings.add(0xFF, Addressings.ABSX);
    }

    public static Addressing get(byte inst) {
        return Addressings.map.get(inst);
    }

    private static void add(int opcode, Addressing implementation) {
        Addressings.map.put((byte) opcode, implementation);
    }

}
