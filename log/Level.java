package org.incava.ijdk.log;

/**
 * <p>Represents a logging/output level. Is essentially a wrapper around an
 * Integer.</p>
 */
public class Level implements Comparable {
    private Integer level = null;    

    public Level(Integer level) {
        this.level = level;
    }

    public int compareTo(Object other) {
        if (other instanceof Level) {
            Level qother = (Level)other;
            return level.compareTo(qother.level);
        }
        else {
            return -1;
        }
    }

    public boolean equals(Object other) {
        return compareTo(other) == 0;
    }

    public String toString() {
        return level.toString();
    }

    public boolean isLoggable(OutputType outputType, Level lvl) {
        return !outputType.equals(OutputType.NONE) && level != null && compareTo(lvl) >= 0;
    }
}
