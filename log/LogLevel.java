package org.incava.ijdk.log;


/**
 * <p>Represents a logging/output level. Is essentially a wrapper around an
 * Integer.</p>
 */
class LogLevel implements Comparable {
    private Integer level = null;
    
    public LogLevel(int level) {
        this(new Integer(level));
    }

    public LogLevel(Integer level) {
        this.level = level;
    }

    public int compareTo(Object other) {
        if (other instanceof LogLevel) {
            LogLevel qother = (LogLevel)other;
            return this.level.compareTo(qother.level);
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
}
