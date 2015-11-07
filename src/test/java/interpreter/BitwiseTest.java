package interpreter;

import org.junit.Test;

public class BitwiseTest extends InterpreterTest {
    @Test
    public void or() throws Exception {
        assertOutputEquals("/stories/bitwise/or.z3", "255\n14232\n3");
    }

    @Test
    public void and() throws Exception {
        assertOutputEquals("/stories/bitwise/and.z3", "0\n768\n0");
    }
}
