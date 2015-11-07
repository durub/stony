package com.github.stony.interpreter;

import com.github.stony.memory.Memory;
import com.github.stony.memory.Header;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Stack;

public final class Interpreter {
    final Memory memory;
    final Header header;
    final InputStream inputStream;
    final OutputStream outputStream;
    final Stack<Integer> stack;
    final private Instruction instruction;
    private boolean finished;

    /* Program counter offset */
    int pcOffset;

    /* Program counter */
    int pc;

    public Interpreter(byte[] fileData, InputStream inputStream, OutputStream outputStream) {
        this.memory = new Memory(fileData);
        this.header = new Header(memory);
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.stack = new Stack<>();
        this.instruction = new Instruction(this);
        this.finished = false;
        this.pc = this.header.getProgramCounterInitialValue();
    }

    public void executeInstruction() {
        decodeInstruction();
    }

    public boolean isFinished() {
        return finished;
    }

    private void decodeInstruction() {
        final int firstByte = memory.readByte(pc);

        if (firstByte >= 0x00 && firstByte <= 0x1f) {
            //System.out.println("2OP - long, small constant, small constant");
            executeTwoOpSmallSmall();
        } else if (firstByte >= 0x20 && firstByte <= 0x3f) {
            //System.out.println("2OP - long, small constant, variable");
            executeTwoOpSmallVariable();
        } else if (firstByte >= 0x40 && firstByte <= 0x5f) {
            //System.out.println("2OP - long, variable, small constant");
            executeTwoOpVariableSmall();
        } else if (firstByte >= 0x60 && firstByte <= 0x7f) {
            //System.out.println("2OP - long, variable, variable");
            executeTwoOpVariableVariable();
        } else if (firstByte >= 0x80 && firstByte <= 0x8f) {
            System.out.println("1OP - short, large constant");
        } else if (firstByte >= 0x90 && firstByte <= 0x9f) {
            System.out.println("1OP - short, small constant");
        } else if (firstByte >= 0xa0 && firstByte <= 0xaf) {
            System.out.println("1OP - short, variable");
        } else if (firstByte == 0xbe) {
            if (header.getVersionNumber() < 5) {
                // long
            } else {
                // extended
            }
        } else if (firstByte >= 0xb0 && firstByte <= 0xbf) {
            //System.out.println("0OP - short");
            executeZeroOpShort();
        } else if (firstByte >= 0xc0 && firstByte <= 0xdf) {
            //System.out.println("2OP - variable");
            executeTwoOpVariable();
        } else if (firstByte >= 0xe0 && firstByte <= 0xff) {
            // VAR - variable
            //System.out.println("VAR - variable");
            executeVariable();
        }
    }

    private void executeZeroOpShort() {
        final int opcode = memory.readByte(pc) & 0x0f;

        pcOffset = 1;
        if (opcode == Opcodes.NEW_LINE) {
            instruction.new_line();
        } else if (opcode == Opcodes.QUIT) {
            finished = true;
        } else {
            throw new RuntimeException("Unknown 0OP opcode: " + opcode);
        }
    }

    private void executeVariable() {
        final int opcode = memory.readByte(pc) & 0x1f;
        //final int operandTypes = memory.readByte(pc + 1);

        if (opcode == Opcodes.PRINT_NUM) {
            final int variable = memory.readByte(pc + 2);
            pcOffset = 3;
            instruction.print_num(variable);
        } else {
            throw new RuntimeException("Unknown VAR opcode: " + opcode);
        }
    }

    /* Methods for two operands */
    private void executeTwoOpSmallSmall() {
        final int op1 = memory.readByte(pc + 1);
        final int op2 = memory.readByte(pc + 2);
        pcOffset = 3;
        executeTwoOp(op1, op2);
    }

    private void executeTwoOpVariableVariable() {
        final int op1 = loadVariable(memory.readByte(pc + 1));
        final int op2 = loadVariable(memory.readByte(pc + 2));
        pcOffset = 3;
        executeTwoOp(op1, op2);
    }

    private void executeTwoOpSmallVariable() {
        final int op1 = memory.readByte(pc + 1);
        final int op2 = loadVariable(memory.readByte(pc + 2));
        pcOffset = 3;
        executeTwoOp(op1, op2);
    }

    private void executeTwoOpVariableSmall() {
        final int op1 = loadVariable(memory.readByte(pc + 1));
        final int op2 = memory.readByte(pc + 2);
        pcOffset = 3;
        executeTwoOp(op1, op2);
    }

    private void executeTwoOpVariable() {
        final int operandTypes = memory.readByte(pc + 1);
        final int firstOperandType = operandTypes >> 6;
        final int secondOperandType = (operandTypes >> 4) & 0x03;
        final int op1;
        final int op2;

        pcOffset = 2;
        op1 = readVariableOperand(firstOperandType);
        op2 = readVariableOperand(secondOperandType);

        executeTwoOp(op1, op2);
    }

    private int readVariableOperand(int operandType) {
        int op;
        switch (operandType) {
            case 0x00:
                op = memory.readWord(pc + pcOffset);
                pcOffset += 2;
                break;
            case 0x01:
                op = memory.readByte(pc + pcOffset);
                pcOffset += 1;
                break;
            case 0x02:
                op = loadVariable(memory.readByte(pc + pcOffset));
                pcOffset += 1;
                break;
            case 0x03:
            default:
                op = -1;
        }
        return op;
    }

    private void executeTwoOp(int op1, int op2) {
        final int opcode = memory.readByte(pc) & 0x1f;

        if (opcode == Opcodes.ADD) {
            instruction.add(op1, op2);
        } else if (opcode == Opcodes.SUB) {
            instruction.sub(op1, op2);
        } else if (opcode == Opcodes.MUL) {
            instruction.mul(op1, op2);
        } else if (opcode == Opcodes.DIV) {
            instruction.div(op1, op2);
        } else if (opcode == Opcodes.MOD) {
            instruction.mod(op1, op2);
        } else if (opcode == Opcodes.OR) {
            instruction.or(op1, op2);
        } else if (opcode == Opcodes.AND) {
            instruction.and(op1, op2);
        } else {
            throw new RuntimeException("Unknown 2OP opcode: " + opcode);
        }
    }

    /**
     * Stores the value in a variable. Supports stack (0), local (0x01-0x0f) and global (0x10-0ff) variables.
     * Variables are stored as word values.
     *
     * @param variableNumber variable number.
     * @param value variable's new value.
     */
    void storeVariable(int variableNumber, int value) {
        if (variableNumber == 0x00) {
            stack.push(value);
        } else if (variableNumber < 0x10) {
            throw new UnsupportedOperationException("Local variables are not supported at the moment.");
        } else if (variableNumber <= 0xff) {
            variableNumber -= 0x10; /* adjust the offset to be in the range 0..f5, which can be summed directly */
            variableNumber *= 2; /* variables are words, not bytes. multiply by two to get the correct address. */
            memory.writeWord(header.getGlobalVariablesTableAddress() + variableNumber, value);
        } else {
            throw new RuntimeException("Invalid variable " + variableNumber);
        }
    }

    /**
     * Reads the value of a variable. Supports stack (0), local (0x01-0x0f) or global (0x10-0xff).
     * Variables are read as word values.
     *
     * @param variable variable number.
     * @return the variable's value.
     */
    int loadVariable(int variable) {
        if (variable == 0x00) {
            return stack.peek();
        } else if (variable < 0x10) {
            throw new UnsupportedOperationException("Local variables are not supported at the moment.");
        } else if (variable <= 0xff) {
            variable -= 0x10; /* adjust the offset to be in the range 0..f5, which can be summed directly */
            variable *= 2; /* variables are words, not bytes. multiply by two to get the correct address. */
            return memory.readSignedWord(header.getGlobalVariablesTableAddress() + variable);
        } else {
            throw new RuntimeException("Invalid variable " + variable);
        }
    }

    /**
     * Converts a packed address to a byte address. Valid for routine calls.
     * @param packedAddress packed address.
     * @return byte address converted from packed address.
     */
    int packedAddressToByteAddressRoutine(int packedAddress) {
        if (header.getVersionNumber() == 6 || header.getVersionNumber() == 7) {
            return packedAddressToByteAddress(packedAddress) + 8 * header.getRoutinesOffset();
        } else {
            return packedAddressToByteAddress(packedAddress);
        }
    }

    /**
     * Converts a packed address to a byte address. Valid for print_paddr.
     * @param packedAddress packed address.
     * @return byte address converted from packed address.
     */
    int packedAddressToByteAddressPrintAddr(int packedAddress) {
        if (header.getVersionNumber() == 6 || header.getVersionNumber() == 7) {
            return packedAddressToByteAddress(packedAddress) + 8 * header.getStaticStringsOffset();
        } else {
            return packedAddressToByteAddress(packedAddress);
        }
    }

    private int packedAddressToByteAddress(int packedAddress) {
        final byte version = header.getVersionNumber();
        final int multiplier;
        if (version > 0 && version <= 3) {
            multiplier = 2;
        } else if (version >= 4 && version <= 7) {
            multiplier = 4;
        } else if (version == 8) {
            multiplier = 8;
        } else {
            throw new RuntimeException("Unknown version: " + version);
        }

        return multiplier * packedAddress;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
