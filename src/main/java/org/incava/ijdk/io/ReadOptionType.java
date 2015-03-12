package org.incava.ijdk.io;

/**
 * Options for reading files.
 * 
 * @see IO#readLines
 */
public enum ReadOptionType { 
    /**
     * Return only lines containing other than whitespace.
     */
    NONEMPTY, 

    /**
     * Return lines with end-of-line characters/sequences for this OS
     */
    ADD_EOLNS, 

    /**
     * Throw an exception if there is an error reading the file.
     */
    WITH_EXCEPTION;
}
