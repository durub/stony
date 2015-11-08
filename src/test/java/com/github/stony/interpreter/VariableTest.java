package com.github.stony.interpreter;

import org.junit.Test;

public class VariableTest extends InterpreterTest {
    @Test
    public void store() throws Exception {
        assertOutputEquals("/stories/variable/store.z3", "10\n100\n-15000");
    }
}
