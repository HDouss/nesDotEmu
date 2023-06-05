package emu.nes.ops;

import java.util.HashMap;
import java.util.Map;

/**
 * Essentially a data holder that maps an OPCODE to its corresponding operations and cycle count.
 * @author hdouss
 *
 */
public class Operations {

    private static Map<Integer, Operation> operations = new HashMap<>();
    private static Map<Integer, Integer> cycles = new HashMap<>();
    private static final Operation BRK = new Break();
    private static final Operation ORA = new InclusiveOr();
    private static final Operation XXX = new Unknown();
    private static final Operation ASL = new ShiftLeft();
    private static final Operation PHP = new PushStatus();
    private static final Operation BPL = new BranchPositive();
    private static final Operation CLC = new ClearCarry();
    private static final Operation JSR = new JumpSubroutine();
    private static final Operation AND = new And();
    private static final Operation BIT = new BitTest();
    private static final Operation ROL = new RotateLeft();
    private static final Operation PLP = new PullStatus();
    private static final Operation BMI = new BranchNegative();
    private static final Operation SEC = new SetCarry();
    private static final Operation RTI = new ReturnInterrupt();
    private static final Operation EOR = new ExclusiveOr();
    private static final Operation STA = new StoreAccumulator();
    private static final Operation STX = new StoreX();
    private static final Operation STY = new StoreY();
    private static final Operation LSR = new ShiftRight();
    private static final Operation PHA = new PushAccumulator();
    private static final Operation JMP = new Jump();
    private static final Operation BVC = new BranchOverflowClear();
    private static final Operation CLI = new ClearInterrupt();
    private static final Operation RTS = new ReturnSubroutine();
    private static final Operation ADC = new AddCarry();
    private static final Operation ROR = new RotateRight();
    private static final Operation PLA = new PullAccumulator();
    private static final Operation BVS = new BranchOverflowSet();
    private static final Operation SEI = new SetInterrupt();
    private static final Operation DEY = new DecrementY();
    private static final Operation TXA = new TransferXAccumulator();
    private static final Operation BCC = new BranchCarryClear();
    private static final Operation TYA = new TransferYAccumulator();
    private static final Operation LDX = new LoadX();
    private static final Operation LDY = new LoadY();
    private static final Operation LDA = new LoadAccumulator();
    private static final Operation TAY = new TransferAccumulatorY();
    private static final Operation TAX = new TransferAccumulatorX();
    private static final Operation TXS = new TransferXStack();
    private static final Operation BCS = new BranchCarrySet();
    private static final Operation CLV = new ClearOverflow();
    private static final Operation TSX = new TransferStackX();
    private static final Operation CPY = new CompareY();
    private static final Operation CMP = new Compare();
    private static final Operation DEC = new Decrement();
    private static final Operation DEX = new DecrementX();
    private static final Operation INY = new IncrementY();
    private static final Operation INX = new IncrementX();
    private static final Operation NOP = new NoOperation();
    private static final Operation BNE = new BranchNotEqual();
    private static final Operation CPX = new CompareX();
    private static final Operation CLD = new ClearDecimal();
    private static final Operation SBC = new SubtractCarry();
    private static final Operation INC = new Increment();
    private static final Operation SED = new SetDecimal();
    private static final Operation BEQ = new BranchEqual();
    static {

        addInstruction(0x0, Operations.BRK, 7);
        addInstruction(0x1, Operations.ORA, 6);
        addInstruction(0x2, Operations.XXX, 1);
        addInstruction(0x3, Operations.XXX, 1);
        addInstruction(0x4, Operations.XXX, 1);
        addInstruction(0x5, Operations.ORA, 3);
        addInstruction(0x6, Operations.ASL, 5);
        addInstruction(0x7, Operations.XXX, 1);
        addInstruction(0x8, Operations.PHP, 3);
        addInstruction(0x9, Operations.ORA, 2);
        addInstruction(0xA, Operations.ASL, 2);
        addInstruction(0xB, Operations.XXX, 1);
        addInstruction(0xC, Operations.XXX, 1);
        addInstruction(0xD, Operations.ORA, 4);
        addInstruction(0xE, Operations.ASL, 6);
        addInstruction(0xF, Operations.XXX, 1);

        addInstruction(0x10, Operations.BPL, 2);
        addInstruction(0x11, Operations.ORA, 5);
        addInstruction(0x12, Operations.XXX, 1);
        addInstruction(0x13, Operations.XXX, 1);
        addInstruction(0x14, Operations.XXX, 1);
        addInstruction(0x15, Operations.ORA, 4);
        addInstruction(0x16, Operations.ASL, 6);
        addInstruction(0x17, Operations.XXX, 1);
        addInstruction(0x18, Operations.CLC, 2);
        addInstruction(0x19, Operations.ORA, 4);
        addInstruction(0x1A, Operations.XXX, 1);
        addInstruction(0x1B, Operations.XXX, 1);
        addInstruction(0x1C, Operations.XXX, 1);
        addInstruction(0x1D, Operations.ORA, 4);
        addInstruction(0x1E, Operations.ASL, 7);
        addInstruction(0x1F, Operations.XXX, 1);

        addInstruction(0x20, Operations.JSR, 6);
        addInstruction(0x21, Operations.AND, 6);
        addInstruction(0x22, Operations.XXX, 1);
        addInstruction(0x23, Operations.XXX, 1);
        addInstruction(0x24, Operations.BIT, 3);
        addInstruction(0x25, Operations.AND, 3);
        addInstruction(0x26, Operations.ROL, 5);
        addInstruction(0x27, Operations.XXX, 1);
        addInstruction(0x28, Operations.PLP, 4);
        addInstruction(0x29, Operations.AND, 2);
        addInstruction(0x2A, Operations.ROL, 2);
        addInstruction(0x2B, Operations.XXX, 1);
        addInstruction(0x2C, Operations.BIT, 4);
        addInstruction(0x2D, Operations.AND, 4);
        addInstruction(0x2E, Operations.ROL, 6);
        addInstruction(0x2F, Operations.XXX, 1);

        addInstruction(0x30, Operations.BMI, 2);
        addInstruction(0x31, Operations.AND, 5);
        addInstruction(0x32, Operations.XXX, 1);
        addInstruction(0x33, Operations.XXX, 1);
        addInstruction(0x34, Operations.XXX, 1);
        addInstruction(0x35, Operations.AND, 4);
        addInstruction(0x36, Operations.ROL, 6);
        addInstruction(0x37, Operations.XXX, 1);
        addInstruction(0x38, Operations.SEC, 2);
        addInstruction(0x39, Operations.AND, 4);
        addInstruction(0x3A, Operations.XXX, 1);
        addInstruction(0x3B, Operations.XXX, 1);
        addInstruction(0x3C, Operations.XXX, 1);
        addInstruction(0x3D, Operations.AND, 4);
        addInstruction(0x3E, Operations.ROL, 7);
        addInstruction(0x3F, Operations.XXX, 1);

        addInstruction(0x40, Operations.RTI, 6);
        addInstruction(0x41, Operations.EOR, 6);
        addInstruction(0x42, Operations.XXX, 1);
        addInstruction(0x43, Operations.XXX, 1);
        addInstruction(0x44, Operations.XXX, 1);
        addInstruction(0x45, Operations.EOR, 3);
        addInstruction(0x46, Operations.LSR, 5);
        addInstruction(0x47, Operations.XXX, 1);
        addInstruction(0x48, Operations.PHA, 3);
        addInstruction(0x49, Operations.EOR, 2);
        addInstruction(0x4A, Operations.LSR, 2);
        addInstruction(0x4B, Operations.XXX, 1);
        addInstruction(0x4C, Operations.JMP, 3);
        addInstruction(0x4D, Operations.EOR, 4);
        addInstruction(0x4E, Operations.LSR, 6);
        addInstruction(0x4F, Operations.XXX, 1);

        addInstruction(0x50, Operations.BVC, 2);
        addInstruction(0x51, Operations.EOR, 5);
        addInstruction(0x52, Operations.XXX, 1);
        addInstruction(0x53, Operations.XXX, 1);
        addInstruction(0x54, Operations.XXX, 1);
        addInstruction(0x55, Operations.EOR, 4);
        addInstruction(0x56, Operations.LSR, 6);
        addInstruction(0x57, Operations.XXX, 1);
        addInstruction(0x58, Operations.CLI, 2);
        addInstruction(0x59, Operations.EOR, 4);
        addInstruction(0x5A, Operations.XXX, 1);
        addInstruction(0x5B, Operations.XXX, 1);
        addInstruction(0x5C, Operations.XXX, 1);
        addInstruction(0x5D, Operations.EOR, 4);
        addInstruction(0x5E, Operations.LSR, 7);
        addInstruction(0x5F, Operations.XXX, 1);

        addInstruction(0x60, Operations.RTS, 6);
        addInstruction(0x61, Operations.ADC, 6);
        addInstruction(0x62, Operations.XXX, 1);
        addInstruction(0x63, Operations.XXX, 1);
        addInstruction(0x64, Operations.XXX, 1);
        addInstruction(0x65, Operations.ADC, 3);
        addInstruction(0x66, Operations.ROR, 5);
        addInstruction(0x67, Operations.XXX, 1);
        addInstruction(0x68, Operations.PLA, 4);
        addInstruction(0x69, Operations.ADC, 2);
        addInstruction(0x6A, Operations.ROR, 2);
        addInstruction(0x6B, Operations.XXX, 1);
        addInstruction(0x6C, Operations.JMP, 5);
        addInstruction(0x6D, Operations.ADC, 4);
        addInstruction(0x6E, Operations.ROR, 6);
        addInstruction(0x6F, Operations.XXX, 1);

        addInstruction(0x70, Operations.BVS, 2);
        addInstruction(0x71, Operations.ADC, 5);
        addInstruction(0x72, Operations.XXX, 1);
        addInstruction(0x73, Operations.XXX, 1);
        addInstruction(0x74, Operations.XXX, 1);
        addInstruction(0x75, Operations.ADC, 4);
        addInstruction(0x76, Operations.ROR, 6);
        addInstruction(0x77, Operations.XXX, 1);
        addInstruction(0x78, Operations.SEI, 2);
        addInstruction(0x79, Operations.ADC, 4);
        addInstruction(0x7A, Operations.XXX, 1);
        addInstruction(0x7B, Operations.XXX, 1);
        addInstruction(0x7C, Operations.XXX, 1);
        addInstruction(0x7D, Operations.ADC, 4);
        addInstruction(0x7E, Operations.ROR, 7);
        addInstruction(0x7F, Operations.XXX, 1);

        addInstruction(0x80, Operations.XXX, 1);
        addInstruction(0x81, Operations.STA, 6);
        addInstruction(0x82, Operations.XXX, 1);
        addInstruction(0x83, Operations.XXX, 1);
        addInstruction(0x84, Operations.STY, 3);
        addInstruction(0x85, Operations.STA, 3);
        addInstruction(0x86, Operations.STX, 3);
        addInstruction(0x87, Operations.XXX, 1);
        addInstruction(0x88, Operations.DEY, 2);
        addInstruction(0x89, Operations.XXX, 1);
        addInstruction(0x8A, Operations.TXA, 2);
        addInstruction(0x8B, Operations.XXX, 1);
        addInstruction(0x8C, Operations.STY, 4);
        addInstruction(0x8D, Operations.STA, 4);
        addInstruction(0x8E, Operations.STX, 4);
        addInstruction(0x8F, Operations.XXX, 1);

        addInstruction(0x90, Operations.BCC, 2);
        addInstruction(0x91, Operations.STA, 6);
        addInstruction(0x92, Operations.XXX, 1);
        addInstruction(0x93, Operations.XXX, 1);
        addInstruction(0x94, Operations.STY, 4);
        addInstruction(0x95, Operations.STA, 4);
        addInstruction(0x96, Operations.STX, 4);
        addInstruction(0x97, Operations.XXX, 1);
        addInstruction(0x98, Operations.TYA, 2);
        addInstruction(0x99, Operations.STA, 5);
        addInstruction(0x9A, Operations.TXS, 2);
        addInstruction(0x9B, Operations.XXX, 1);
        addInstruction(0x9C, Operations.XXX, 1);
        addInstruction(0x9D, Operations.STA, 5);
        addInstruction(0x9E, Operations.XXX, 1);
        addInstruction(0x9F, Operations.XXX, 1);

        addInstruction(0xA0, Operations.LDY, 2);
        addInstruction(0xA1, Operations.LDA, 6);
        addInstruction(0xA2, Operations.LDX, 2);
        addInstruction(0xA3, Operations.XXX, 1);
        addInstruction(0xA4, Operations.LDY, 3);
        addInstruction(0xA5, Operations.LDA, 3);
        addInstruction(0xA6, Operations.LDX, 3);
        addInstruction(0xA7, Operations.XXX, 1);
        addInstruction(0xA8, Operations.TAY, 2);
        addInstruction(0xA9, Operations.LDA, 2);
        addInstruction(0xAA, Operations.TAX, 2);
        addInstruction(0xAB, Operations.XXX, 1);
        addInstruction(0xAC, Operations.LDY, 4);
        addInstruction(0xAD, Operations.LDA, 4);
        addInstruction(0xAE, Operations.LDX, 4);
        addInstruction(0xAF, Operations.XXX, 1);

        addInstruction(0xB0, Operations.BCS, 2);
        addInstruction(0xB1, Operations.LDA, 5);
        addInstruction(0xB2, Operations.XXX, 1);
        addInstruction(0xB3, Operations.XXX, 1);
        addInstruction(0xB4, Operations.LDY, 4);
        addInstruction(0xB5, Operations.LDA, 4);
        addInstruction(0xB6, Operations.LDX, 4);
        addInstruction(0xB7, Operations.XXX, 1);
        addInstruction(0xB8, Operations.CLV, 2);
        addInstruction(0xB9, Operations.LDA, 4);
        addInstruction(0xBA, Operations.TSX, 2);
        addInstruction(0xBB, Operations.XXX, 1);
        addInstruction(0xBC, Operations.LDY, 4);
        addInstruction(0xBD, Operations.LDA, 4);
        addInstruction(0xBE, Operations.LDX, 4);
        addInstruction(0xBF, Operations.XXX, 1);

        addInstruction(0xC0, Operations.CPY, 2);
        addInstruction(0xC1, Operations.CMP, 6);
        addInstruction(0xC2, Operations.XXX, 1);
        addInstruction(0xC3, Operations.XXX, 1);
        addInstruction(0xC4, Operations.CPY, 3);
        addInstruction(0xC5, Operations.CMP, 3);
        addInstruction(0xC6, Operations.DEC, 5);
        addInstruction(0xC7, Operations.XXX, 1);
        addInstruction(0xC8, Operations.INY, 2);
        addInstruction(0xC9, Operations.CMP, 2);
        addInstruction(0xCA, Operations.DEX, 2);
        addInstruction(0xCB, Operations.XXX, 1);
        addInstruction(0xCC, Operations.CPY, 4);
        addInstruction(0xCD, Operations.CMP, 4);
        addInstruction(0xCE, Operations.DEC, 6);
        addInstruction(0xCF, Operations.XXX, 1);

        addInstruction(0xD0, Operations.BNE, 2);
        addInstruction(0xD1, Operations.CMP, 5);
        addInstruction(0xD2, Operations.XXX, 1);
        addInstruction(0xD3, Operations.XXX, 1);
        addInstruction(0xD4, Operations.XXX, 1);
        addInstruction(0xD5, Operations.CMP, 4);
        addInstruction(0xD6, Operations.DEC, 6);
        addInstruction(0xD7, Operations.XXX, 1);
        addInstruction(0xD8, Operations.CLD, 2);
        addInstruction(0xD9, Operations.CMP, 4);
        addInstruction(0xDA, Operations.XXX, 1);
        addInstruction(0xDB, Operations.XXX, 1);
        addInstruction(0xDC, Operations.XXX, 1);
        addInstruction(0xDD, Operations.CMP, 4);
        addInstruction(0xDE, Operations.DEC, 7);
        addInstruction(0xDF, Operations.XXX, 1);

        addInstruction(0xE0, Operations.CPX, 2);
        addInstruction(0xE1, Operations.SBC, 6);
        addInstruction(0xE2, Operations.XXX, 1);
        addInstruction(0xE3, Operations.XXX, 1);
        addInstruction(0xE4, Operations.CPX, 3);
        addInstruction(0xE5, Operations.SBC, 3);
        addInstruction(0xE6, Operations.INC, 5);
        addInstruction(0xE7, Operations.XXX, 1);
        addInstruction(0xE8, Operations.INX, 2);
        addInstruction(0xE9, Operations.SBC, 2);
        addInstruction(0xEA, Operations.NOP, 2);
        addInstruction(0xEB, Operations.XXX, 1);
        addInstruction(0xEC, Operations.CPX, 4);
        addInstruction(0xED, Operations.SBC, 4);
        addInstruction(0xEE, Operations.INC, 6);
        addInstruction(0xEF, Operations.XXX, 1);

        addInstruction(0xF0, Operations.BEQ, 2);
        addInstruction(0xF1, Operations.SBC, 5);
        addInstruction(0xF2, Operations.XXX, 1);
        addInstruction(0xF3, Operations.XXX, 1);
        addInstruction(0xF4, Operations.XXX, 1);
        addInstruction(0xF5, Operations.SBC, 4);
        addInstruction(0xF6, Operations.INC, 6);
        addInstruction(0xF7, Operations.XXX, 1);
        addInstruction(0xF8, Operations.SED, 2);
        addInstruction(0xF9, Operations.SBC, 4);
        addInstruction(0xFA, Operations.XXX, 1);
        addInstruction(0xFB, Operations.XXX, 1);
        addInstruction(0xFC, Operations.XXX, 1);
        addInstruction(0xFD, Operations.SBC, 4);
        addInstruction(0xFE, Operations.INC, 7);
        addInstruction(0xFF, Operations.XXX, 1);
    }

    /**
     * Gets the operation by OPCode.
     * @param inst OPCode
     * @return Operation corresponding to the OPCode
     */
    public static Operation get(int inst) {
        return Operations.operations.get(inst);
    }

    /**
     * Gets the cycles needed for the passed OPCode to execute.
     * @param inst OPCode
     * @return Cycles needed for the passed OPCode
     */
    public static int cycles(int inst) {
        return Operations.cycles.get(inst);
    }

    /**
     * Adds a mapping between the OPCode, the operation and the cycles count.
     * @param inst OPCode
     * @param ops Operation
     * @param count Cycles needed for the operation to execute
     */
    private static void addInstruction(int inst, Operation ops, int count) {
        Operations.operations.put(inst, ops);
        Operations.cycles.put(inst, count);
    }

}
