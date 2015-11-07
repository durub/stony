package interpreter;

import org.junit.Test;

public class ArithmeticTest extends InterpreterTest {
    @Test
    public void add() throws Exception {
        assertOutputEquals("/stories/arithmetic/add.z3", "512\n-10");
    }

    @Test
    public void sub() throws Exception {
        assertOutputEquals("/stories/arithmetic/sub.z3", "-88\n50");
    }

    @Test
    public void mul() throws Exception {
        assertOutputEquals("/stories/arithmetic/mul.z3", "18662\n-900\n50");
    }

    @Test
    public void div() throws Exception {
        assertOutputEquals("/stories/arithmetic/div.z3", "2\n-1\n10000");
    }

    @Test
    public void mod() throws Exception {
        assertOutputEquals("/stories/arithmetic/mod.z3", "0\n-50\n50");
    }
}
