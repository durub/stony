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
     * Subtracts a and b and stores into the variable identified by c.<br>
     * [opcode] [a] [b] [c]
     *
     * @param a first operand.
     * @param b second operand.
     */
    public void sub(int a, int b) {
        final int storeVariable = interpreter.memory.readByte(interpreter.pc + interpreter.pcOffset);
        final int result = a - b;
        interpreter.storeVariable(storeVariable, result);
        interpreter.pc += interpreter.pcOffset + 1;
    }

    /**
     * Multiplies a and b and stores into the variable identified by c.<br>
     * [opcode] [a] [b] [c]
     *
     * @param a first operand.
     * @param b second operand.
     */
    public void mul(int a, int b) {
        final int storeVariable = interpreter.memory.readByte(interpreter.pc + interpreter.pcOffset);
        final int result = a * b;
        interpreter.storeVariable(storeVariable, result);
        interpreter.pc += interpreter.pcOffset + 1;
    }

    /**
     * Multiplies a and b and stores into the variable identified by c.<br>
     * [opcode] [a] [b] [c]
     *
     * @param a first operand.
     * @param b second operand.
     */
    public void div(int a, int b) {
        final int storeVariable = interpreter.memory.readByte(interpreter.pc + interpreter.pcOffset);
        final int result = a / b;
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


    /**
     * 2OP - long, small constant, small constant
     * [opcode] [variable address] [constant] [offset]
     * Increments the variable pointed by variable address. If the new value is greater than constant, branch to offset.
     */
    public void inc_chk() {
        final int globalVariables = interpreter.header.getGlobalVariablesTableAddress();
        final int variableAddress = interpreter.memory.readByte(interpreter.pc + 1);
        int variableValue = interpreter.memory.readByte(globalVariables + variableAddress);
        final int constantValue = interpreter.memory.readByte(interpreter.pc + 2);
        final int branchAddress = interpreter.memory.readByte(interpreter.pc + 3);

        /* Increment and store the variable */
        variableValue++;
        interpreter.memory.writeByte(variableAddress, variableValue);

        if (variableValue > constantValue) {
            /* branch */
            interpreter.pc += branchAddress;
        } else {
            interpreter.pc += 4;
        }
    }

    public void or() {
        final int a = interpreter.memory.readByte(interpreter.pc + 1);
        final int b = interpreter.memory.readByte(interpreter.pc + 2);
        interpreter.stack.push(a | b);
        interpreter.pc += 3;
    }

    public void call() {
        final int operandTypes = interpreter.memory.readByte(interpreter.pc + 1);
        final int routineAddress;
        final int a, b, c; /* three arguments */

        routineAddress = interpreter.packedAddressToByteAddressRoutine(interpreter.memory.readWord(interpreter.pc + 2));
        interpreter.pc = routineAddress;
    }
}
