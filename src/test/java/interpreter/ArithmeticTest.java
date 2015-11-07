package interpreter;

import org.junit.Test;

public class ArithmeticTest extends InterpreterTest {
    @Test
    public void add() throws Exception {
        assertOutputEquals("/stories/arithmetic/add.z3", "512\n-10");
    }
}
