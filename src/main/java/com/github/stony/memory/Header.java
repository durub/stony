package com.github.stony.memory;

/**
 * Utility class to access the header (first 64 bytes) information.
 */
public final class Header {
    /**
     * The memory from which the first 64 bytes are accessed.
     */
    private final Memory memory;

    private static final short VERSION_ADDRESS = 0x0;
    private static final short HIGH_MEMORY_BASE_ADDRESS = 0x4;
    private static final short PROGRAM_COUNTER_INITIAL_VALUE_ADDRESS = 0x6;
    private static final short DICTIONARY_LOCATION_ADDRESS = 0x8;
    private static final short OBJECT_TABLE_LOCATION_ADDRESS = 0xa;
    private static final short GLOBAL_VARIABLES_TABLE_LOCATION_ADDRESS = 0xc;
    private static final short STATIC_MEMORY_BASE_ADDRESS = 0xe;
    private static final short FILE_LENGTH_ADDRESS = 0x1a;
    private static final short ROUTINES_OFFSET_ADDRESS = 0x28;
    private static final short STATIC_STRINGS_OFFSET_ADDRESS = 0x2a;

    /**
     * Constructs a Header from a Memory instance.
     * @param memory memory to be read from.
     */
    public Header(Memory memory) {
        this.memory = memory;
    }

    /**
     * Returns the file version number (1 to 6).
     * @return the file version number.
     */
    public byte getVersionNumber() {
        return (byte) memory.readByte(VERSION_ADDRESS);
    }

    /**
     * Returns the high memory base address.
     * The high memory starts at this address and continues to the end of the story file.
     *
     * @return the high memory based address.
     */
    public int getHighMemoryBaseAddress() {
        return memory.readWord(HIGH_MEMORY_BASE_ADDRESS);
    }

    /**
     * Returns the program counter initial value.
     * The program counter should be set to this value at the interpreter's start.
     *
     * @return the program counter initial value.
     */
    public int getProgramCounterInitialValue() {
        return memory.readWord(PROGRAM_COUNTER_INITIAL_VALUE_ADDRESS);
    }

    public int getDictionaryAddress() {
        return memory.readWord(DICTIONARY_LOCATION_ADDRESS);
    }

    public int getObjectTableAddress() {
        return memory.readWord(OBJECT_TABLE_LOCATION_ADDRESS);
    }

    public int getGlobalVariablesTableAddress() {
        return memory.readWord(GLOBAL_VARIABLES_TABLE_LOCATION_ADDRESS);
    }

    public int getStaticMemoryBaseAddress() {
        return memory.readWord(STATIC_MEMORY_BASE_ADDRESS);
    }

    /**
     * Returns the routines offset. This value is divided by 8.
     * The "real" value can be adquired by multiplying it by 8.
     *
     * @return the routines offset, divided by 8.
     */
    public int getRoutinesOffset() {
        if (getVersionNumber() < 6) {
            throw new UnsupportedAccessException(6);
        }
        return memory.readWord(ROUTINES_OFFSET_ADDRESS);
    }

    /**
     * Returns the static strings offset. This value is divided by 8.
     * The "real" value can be adquired by multiplying it by 8.
     *
     * @return the static strings offset, divided by 8.
     */
    public int getStaticStringsOffset() {
        if (getVersionNumber() < 6) {
            throw new UnsupportedAccessException(6);
        }
        return memory.readWord(STATIC_STRINGS_OFFSET_ADDRESS);
    }

    /**
     * Returns the file length. Supported on version 3 and above.
     * @return the file length of the story.
     */
    public int getFileLength() {
        if (getVersionNumber() < 3) {
            throw new UnsupportedAccessException(3);
        }

        final int multiplier;
        if (getVersionNumber() <= 3) {
            multiplier = 2;
        } else if (getVersionNumber() == 4 || getVersionNumber() == 5) {
            multiplier = 4;
        } else {
            multiplier = 8;
        }

        return multiplier * memory.readWord(FILE_LENGTH_ADDRESS);
    }
}
