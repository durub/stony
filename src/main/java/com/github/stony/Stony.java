package com.github.stony;

import com.github.stony.interpreter.Interpreter;

import java.io.InputStream;
import java.io.OutputStream;

public class Stony {
    private Interpreter interpreter;

    private byte[] fileData;
    private InputStream inputStream;
    private OutputStream outputStream;

    public Stony(byte[] fileData) {
        this.fileData = fileData;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void run() {
        if (inputStream == null) {
            inputStream = System.in;
        }
        if (outputStream == null) {
            outputStream = System.out;
        }

        interpreter = new Interpreter(fileData, inputStream, outputStream);
        while (!interpreter.isFinished()) {
            interpreter.executeInstruction();
        }
    }
}
