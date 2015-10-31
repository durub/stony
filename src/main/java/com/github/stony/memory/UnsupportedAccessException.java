package com.github.stony.memory;

/**
 * Used for reporting unsupported operations at the current version level.
 */
public class UnsupportedAccessException extends RuntimeException {
    /**
     * Minimum version in which the feature becomes available.
     */
    private Integer minimumVersion;

    /**
     * Constructs an UnsupportedAccessException from the minimum version required to use the feature.
     *
     * @param minimumVersion minimum version in which the feature becomes available. Can be null.
     */
    public UnsupportedAccessException(Integer minimumVersion) {
        this.minimumVersion = minimumVersion;
    }
}
