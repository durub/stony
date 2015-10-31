package com.github.stony.interpreter;

import com.github.stony.memory.Memory;
import com.github.stony.memory.Header;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Stack;

public final class Interpreter {
    final Memory memory;
    final Header header;
    final InputStream inputStream;
    final OutputStream outputStream;
    final Stack<Integer> stack;
    final private Instruction instruction;

    /* Program counter */
    int pc;

    public Interpreter(byte[] fileData, InputStream inputStream, OutputStream outputStream) {
        this.memory = new Memory(fileData);
        this.header = new Header(memory);
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.stack = new Stack<>();
        this.instruction = new Instruction(this);
        this.pc = this.header.getProgramCounterInitialValue();
    }

    public void executeInstruction() {
        decodeInstruction();
    }

    private void decodeInstruction() {
        int firstByte = memory.readByte(pc);

        if (firstByte >= 0x00 && firstByte <= 0x1f) {
            // 2OP - long, small constant, small constant
        } else if (firstByte >= 0x20 && firstByte <= 0x3f) {
            // 2OP - long, small constant, variable
        } else if (firstByte >= 0x40 && firstByte <= 0x5f) {
            // 2OP - long, variable, small constant
        } else if (firstByte >= 0x60 && firstByte <= 0x7f) {
            // 2OP - long, variable, variable
        } else if (firstByte >= 0x80 && firstByte <= 0x8f) {
            // 1OP - short, large constant
        } else if (firstByte >= 0x90 && firstByte <= 0x9f) {
            // 1OP - short, small constant
        } else if (firstByte >= 0xa0 && firstByte <= 0xaf) {
            // 1OP - short, variable
        } else if (firstByte == 0xbe) {
            if (header.getVersionNumber() < 5) {
                // long
            } else {
                // extended
            }
        } else if (firstByte >= 0xb0 && firstByte <= 0xbf) {
            // 0OP - short
        } else if (firstByte >= 0xc0 && firstByte <= 0xdf) {
            // 2OP - variable
        } else if (firstByte >= 0xe0 && firstByte <= 0xff) {
            // VAR - variable
        }
    }
}
