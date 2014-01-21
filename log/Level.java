package org.incava.ijdk.log;

import org.incava.ijdk.log.output.OutputType;

/**
 * <p>Represents a logging/output level.
 */
public class Level implements Comparable<Level> {
    private Integer level = null;    

    public Level(Integer level) {
        this.level = level;
    }

    public int compareTo(Level other) {
        return other == null ? -1 : level.compareTo(other.level);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Level) {
            Level other = (Level)obj;
            return compareTo(other) == 0;
        }
        return false;
    }

    public String toString() {
        return level.toString();
    }

    public boolean isLoggable(OutputType outputType, Level lvl) {
        return !outputType.equals(OutputType.NONE) && level != null && compareTo(lvl) >= 0;
    }
}
