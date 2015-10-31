package com.github.stony.interpreter;

public final class Instruction {
    private final Interpreter interpreter;

    public Instruction(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public void add() {
        interpreter.pc += 2;
    }
}
