package interpreter;

import com.github.stony.interpreter.Interpreter;
import org.junit.Assert;

import java.io.*;
import java.net.URISyntaxException;

/**
 * Barebones framework to test story files. Testing classes should extend this.
 *
 * The idea is to test the output
 */
class InterpreterTest {
    /**
     * InterpreterTest should not be instantiated directly, only extended.
     */
    protected InterpreterTest() {
    }

    /**
     * Asserts if the output of the finished story is equal to the output parameter.
     *
     * @param filePath resource file path.
     * @param output expected output.
     * @throws IOException when the file doesn't exist or a read error occured.
     * @throws URISyntaxException when the file path doesn't convert to a valid URI.
     */
    protected void assertOutputEquals(String filePath, String output) throws IOException, URISyntaxException {
        final InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        final OutputStream outputStream = new ByteArrayOutputStream();
        final Interpreter interpreter = new Interpreter(readFileData(filePath), inputStream, outputStream);
        assertOutputEquals(interpreter, output);
    }

    /**
     * Asserts if the output of the finished story is equal to the output parameter.
     *
     * @param interpreter interpreter with a ByteArrayOutputStream as the output stream.
     * @param output expected output.
     * @throws IOException if converting the output stream to a UTF-8 string failed.
     * @throws IllegalArgumentException when the interpreter output stream is not an instance of ByteArrayOutputStream.
     */
    protected void assertOutputEquals(Interpreter interpreter, String output) throws IOException {
        final OutputStream outputStream = interpreter.getOutputStream();
        if (!(outputStream instanceof ByteArrayOutputStream)) {
            throw new IllegalArgumentException("Interpreter output stream must be a ByteArrayOutputStream.");
        }

        while (!interpreter.isFinished()) {
            interpreter.executeInstruction();
        }

        Assert.assertEquals(output, ((ByteArrayOutputStream) outputStream).toString("UTF-8"));
    }

    /**
     * Returns the content of a resource file as a byte array.
     *
     * @param filePath path to the resource file.
     * @return content of the resource file.
     * @throws IOException when the file doesn't exist or a read error occured.
     * @throws URISyntaxException when the file path doesn't convert to a valid URI.
     */
    @SuppressWarnings("TryFinallyCanBeTryWithResources")
    protected byte[] readFileData(String filePath) throws IOException, URISyntaxException {
        final File file = new File(getClass().getResource(filePath).toURI());
        final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            byte[] bytes = new byte[(int) randomAccessFile.length()];
            randomAccessFile.readFully(bytes);
            return bytes;
        } finally {
            randomAccessFile.close();
        }
    }
}
