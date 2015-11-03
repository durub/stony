package com.github.stony.interpreter;

import java.io.IOException;

public final class Instruction {
    private final Interpreter interpreter;

    public Instruction(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    /**
     * Adds a and b and stores into the variable identified by c.<br>
     * [opcode] [a] [b] [c]
     *
     * @param a first operand.
     * @param b second operand.
     */
    public void add(int a, int b) {
        final int storeVariable = interpreter.memory.readByte(interpreter.pc + interpreter.pcOffset);
        final int result = a + b;
        interpreter.storeVariable(storeVariable, result);
        interpreter.pc += interpreter.pcOffset + 1;
    }

    /**
     * Prints the number stored in the [a] variable.
     * [opcode] [operand_types] [a]
     */
    public void print_num() {
        final int numVariable = interpreter.memory.readByte(interpreter.pc + 2);
        final int num = interpreter.loadVariable(numVariable);
        try {
            interpreter.outputStream.write(String.valueOf(num).getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to output stream.", e);
        }
        interpreter.pc += 3;
    }
}
