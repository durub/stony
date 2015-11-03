package com.github.stony.memory;

/**
 * Z files have three "kinds" of memory: static (which can not be read by the application), dynamic (which can be
 * accessed normally by the application) and high memory.
 */
public final class Memory {
    /**
     * Holds the raw memory, in bytes. Words should be encoded in big endian.
     */
    private byte[] rawMemory;

    /**
     * Constructs a memory with a capacity of 512kb.
     */
    public Memory() {
        rawMemory = new byte[512 * 1024];
    }

    /**
     * Constructs the memory utilizing a pre-existing byte array.
     * @param raw byte array to be used as the basis for this memory.
     */
    public Memory(byte[] raw) {
        rawMemory = raw;
    }

    /**
     * Writes an unsigned byte to the specified address.
     * @param address address.
     * @param ubyte unsigned byte.
     */
    public void writeByte(int address, int ubyte) {
        rawMemory[address] = (byte) ubyte; // no bounds checking at the moment
    }

    /**
     * Writes an unsigned word to the specified address.
     * @param address address.
     * @param word unsigned word.
     */
    public void writeWord(int address, int word) {
        rawMemory[address] = (byte) (word >> 8);
        rawMemory[address + 1] = (byte) (word & 0xff);
    }

    /**
     * Reads an unsigned byte from the specified address.
     * @param address byte's address.
     * @return the unsigned byte present at the address.
     */
    public int readByte(int address) {
        return ((int) rawMemory[address]) & 0xff;
    }

    /**
     * Reads an unsigned word from the specified address.
     * @param address word's address.
     * @return the unsigned word present at the address.
     */
    public int readWord(int address) {
        final int unsignedFirstByte = ((int) rawMemory[address]) & 0xff;
        final int unsignedSecondByte = ((int) rawMemory[address + 1]) & 0xff;
        return (unsignedFirstByte << 8) | unsignedSecondByte;
    }
}
