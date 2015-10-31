package com.github.stony.interpreter;

/**
 * Holds the numeric value of each opcode as a constant.
 */
public final class Opcodes {
    /* 1OP */
    private static final int JZ = 0x00;

    /* 2OP */
    private static final int JE = 0x01;
    private static final int JL = 0x02;
    private static final int JG = 0x03;
    private static final int DEC_CHK = 0x04;
    private static final int INC_CHK = 0x05;
    private static final int JIN = 0x06;
    private static final int TEST = 0x07;
    private static final int OR = 0x08;
    private static final int AND = 0x09;
    private static final int TEST_ATTR = 0x0a;
    private static final int SET_ATTR = 0x0b;
    private static final int CLEAR_ATTR = 0x0c;
    private static final int STORE = 0x0d;
    private static final int INSERT_OBJ = 0x0e;
    private static final int LOADW = 0x0f;
    private static final int LOADB = 0x10;
    private static final int GET_PROP = 0x11;
    private static final int GET_PROP_ADDR = 0x12;
    private static final int GET_NEXT_PROP = 0x13;
    private static final int ADD = 0x14;
    private static final int SUB = 0x15;
    private static final int MUL = 0x16;
    private static final int DIV = 0x17;
    private static final int MOD = 0x18;
    private static final int CALL_2S = 0x19;
    private static final int CALL_2N = 0x1a;
    private static final int SET_COLOUR = 0x1b;
    private static final int THROW = 0x1c;

    /* Prevents instantiation */
    private Opcodes() {
    }
}
