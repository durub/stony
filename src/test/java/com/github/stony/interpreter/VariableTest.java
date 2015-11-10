package com.github.stony.interpreter;

import org.junit.Assert;
import org.junit.Test;

import java.util.EmptyStackException;

public class VariableTest extends InterpreterTest {
    @Test
    public void store() throws Exception {
        assertOutputEquals("/stories/variable/store.z3", "10\n100\n-15000");
    }

    @Test
    public void popShouldPopLastItem() throws Exception {
        final Interpreter interpreter = new Interpreter(readFileData("/stories/variable/pop.z3"), System.in, System.out);
        interpreter.stack.push(10);
        interpreter.stack.push(15);

        while (!interpreter.isFinished()) {
            interpreter.executeInstruction();
        }

        Assert.assertEquals("Stack size should be one after popping one item.", 1, interpreter.stack.size());
    }

    @Test(expected = EmptyStackException.class)
    public void popShouldThrowExceptionWhenStackIsEmpty() throws Exception {
        final Interpreter interpreter = new Interpreter(readFileData("/stories/variable/pop.z3"), System.in, System.out);
        while (!interpreter.isFinished()) {
            interpreter.executeInstruction();
        }
    }
}
