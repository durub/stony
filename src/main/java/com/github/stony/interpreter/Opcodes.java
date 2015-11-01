package com.github.stony.interpreter;

/**
 * Holds the numeric value of each opcode as a constant. The opcode is "valid" after they have been decoded.
 * <p>
 * Different opcodes which share the same name (often in different versions) are differentiated by a * leading TYPE__,
 * where TYPE is VAR or EXT.
 * <p>
 * Instructions:<br>
 *   long form: opcode value is the bottom 5 bits<br>
 *   variable form: opcode value is the bottom 5 bits<br>
 *   extended: opcode value is the second byte<br>
 *   short: opcode value is the bottom 4 bits
 */
public final class Opcodes {
    /* 0OP */
    public static final int RTRUE = 0x00;
    public static final int RFALSE = 0x01;
    public static final int PRINT = 0x02;
    public static final int PRINT_RET = 0x03;
    public static final int NOP = 0x04;
    public static final int SAVE = 0x05;
    public static final int RESTORE = 0x06;
    public static final int RESTART = 0x07;
    public static final int RET_POPPED = 0x08;
    public static final int POP = 0x09;
    public static final int QUIT = 0x0a;
    public static final int NEW_LINE = 0x0b;
    public static final int SHOW_STATUS = 0x0c;
    public static final int VERIFY = 0x0d;
    public static final int EXTENDED = 0x0e; // not an instruction
    public static final int PIRACY = 0x0f;

    /* 1OP */
    public static final int JZ = 0x00;
    public static final int GET_SIBLING = 0x01;
    public static final int GET_CHILD = 0x02;
    public static final int GET_PARENT = 0x03;
    public static final int GET_PROP_LEN = 0x04;
    public static final int INC = 0x05;
    public static final int DEC = 0x06;
    public static final int PRINT_ADDR = 0x07;
    public static final int CALL_1S = 0x08;
    public static final int REMOVE_OBJ = 0x09;
    public static final int PRINT_OBJ = 0x0a;
    public static final int RET = 0x0b;
    public static final int JUMP = 0x0c;
    public static final int PRINT_PADDR = 0x0d;
    public static final int LOAD = 0x0e;
    public static final int NOT_1OP = 0x0f;

    /* 2OP */
    public static final int JE = 0x01;
    public static final int JL = 0x02;
    public static final int JG = 0x03;
    public static final int DEC_CHK = 0x04;
    public static final int INC_CHK = 0x05;
    public static final int JIN = 0x06;
    public static final int TEST = 0x07;
    public static final int OR = 0x08;
    public static final int AND = 0x09;
    public static final int TEST_ATTR = 0x0a;
    public static final int SET_ATTR = 0x0b;
    public static final int CLEAR_ATTR = 0x0c;
    public static final int STORE = 0x0d;
    public static final int INSERT_OBJ = 0x0e;
    public static final int LOADW = 0x0f;
    public static final int LOADB = 0x10;
    public static final int GET_PROP = 0x11;
    public static final int GET_PROP_ADDR = 0x12;
    public static final int GET_NEXT_PROP = 0x13;
    public static final int ADD = 0x14;
    public static final int SUB = 0x15;
    public static final int MUL = 0x16;
    public static final int DIV = 0x17;
    public static final int MOD = 0x18;
    public static final int CALL_2S = 0x19;
    public static final int CALL_2N = 0x1a;
    public static final int SET_COLOUR = 0x1b;
    public static final int THROW = 0x1c;

    /* VAR */
    public static final int CALL = 0x00; // same as call_vs
    public static final int STOREW = 0x01;
    public static final int STOREB = 0x02;
    public static final int PUT_PROP = 0x03;
    public static final int SREAD = 0x04;
    public static final int PRINT_CHAR = 0x05;
    public static final int PRINT_NUM = 0x06;
    public static final int RANDOM = 0x07;
    public static final int PUSH = 0x08;
    public static final int PULL = 0x09;
    public static final int SPLIT_WINDOW = 0x0a;
    public static final int SET_WINDOW = 0x0b;
    public static final int CALL_VS2 = 0x0c;
    public static final int ERASE_WINDOW = 0x0d;
    public static final int ERASE_LINE = 0x0e;
    public static final int SET_CURSOR = 0x0f;
    public static final int GET_CURSOR = 0x10;
    public static final int SET_TEXT_STYLE = 0x11;
    public static final int BUFFER_MODE = 0x12;
    public static final int OUTPUT_STREAM = 0x13;
    public static final int INPUT_STREAM = 0x14;
    public static final int SOUND_EFFECT = 0x15;
    public static final int READ_CHAR = 0x16;
    public static final int SCAN_TABLE = 0x17;
    public static final int VAR__NOT = 0x18;
    public static final int CALL_VN = 0x19;
    public static final int CALL_VN2 = 0x1a;
    public static final int TOKENISE = 0x1b;
    public static final int ENCODE_TEXT = 0x1c;
    public static final int COPY_TABLE = 0x1d;
    public static final int PRINT_TABLE = 0x1e;
    public static final int CHECK_ARG_COUNT = 0x1f;

    /* EXT */
    public static final int EXT__SAVE = 0x00;
    public static final int EXT__RESTORE = 0x01;
    public static final int LOG_SHIFT = 0x02;
    public static final int ART_SHIFT = 0x03;
    public static final int SET_FONT = 0x04;
    public static final int DRAW_PICTURE = 0x05;
    public static final int PICTURE_DATA = 0x06;
    public static final int ERASE_PICTURE = 0x07;
    public static final int SET_MARGINS = 0x08;
    public static final int SAVE_UNDO = 0x09;
    public static final int RESTORE_UNDO = 0x0a;
    public static final int PRINT_UNICODE = 0x0b;
    public static final int CHECK_UNICODE = 0x0c;
    public static final int SET_TRUE_COLOUR = 0x0d;
    public static final int MOVE_WINDOW = 0x10;
    public static final int WINDOW_SIZE = 0x11;
    public static final int WINDOW_STYLE = 0x12;
    public static final int GET_WIND_PROP = 0x13;
    public static final int SCROLL_WINDOW = 0x14;
    public static final int POP_STACK = 0x15;
    public static final int READ_MOUSE = 0x16;
    public static final int MOUSE_WINDOW = 0x17;
    public static final int PUSH_STACK = 0x18;
    public static final int PUT_WIND_PROP = 0x19;
    public static final int PRINT_FORM = 0x1a;
    public static final int MAKE_MENU = 0x1b;
    public static final int PICTURE_TABLE = 0x1c;
    public static final int BUFFER_SCREEN = 0x1d;

    /* Prevents instantiation */
    private Opcodes() {
    }
}
