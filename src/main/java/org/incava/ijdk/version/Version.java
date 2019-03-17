package org.incava.ijdk.version;

import org.incava.ijdk.collect.Array;
import org.incava.ijdk.lang.Comparing;
import org.incava.ijdk.lang.DefaultComparing;

/**
 * A version of the form "1.2.3.4". A version can be of any length, with the first four digits being
 * major.minor.build.revision (AKA major.minor.patch.revision). A special case is
 * Version.of("latest"), which will always exceed (be greater than) any other version.
 */
public class Version implements Comparing<Version> {
    public static final Version LATEST = new Version(Integer.MAX_VALUE) {
            public String toString() {
                return "latest";
            }
        };
    
    public static Version of(String str) {
        return "latest".equalsIgnoreCase(str) ? LATEST : new Version(str);
    }
    
    private final Array<Integer> values;
    private final DefaultComparing<Version> comparing;
    
    public Version(String str) {
        values = Array.empty();
        for (String it : str.split("\\.")) {
            Integer num = Integer.valueOf(it);
            values.append(num);
        }
        comparing = new DefaultComparing<Version>(this);
    }

    public Version(Integer ... args) {
        values = Array.of(args);
        comparing = new DefaultComparing<Version>(this);
    }

    public Integer getMajor() {
        return values.get(0);
    }

    public Integer getMinor() {
        return values.get(1);
    }

    public Integer getBuild() {
        return values.get(2);
    }

    public Integer getPatch() {
        return getBuild();
    }

    public Integer getRevision() {
        return values.get(3);
    }

    public Array<Integer> getValues() {
        return values;
    }

    public String toString() {
        return values.join(".");
    }

    public int hashCode() {
        return values.hashCode();
    }

    /**
     * Returns whether the given object is equal to this one.
     */
    public boolean equals(Object obj) {
        return obj instanceof Version && compareTo((Version)obj) == 0;
    }

    /**
     * Returns the comparison of the given version to this one. Undefined (null) fields are compared
     * as if they are zero, so 1.2.3 == 1.2.3.0.
     */
    public int compareTo(Version other) {
        int numFields = Math.max(values.size(), other.values.size());
        int cmp = 0;
        for (int idx = 0; cmp == 0 && idx < numFields; ++idx) {
            Integer x = getValueAt(idx);
            Integer y = other.getValueAt(idx);
            cmp = new DefaultComparing<Integer>(x).compareTo(y);
        }
        return cmp;
    }

    public boolean lt(Version other) {
        return comparing.lt(other);
    }

    public boolean lte(Version other) {
        return comparing.lte(other);
    }

    public boolean gt(Version other) {
        return comparing.gt(other);
    }

    public boolean gte(Version other) {
        return comparing.gte(other);
    }

    private Integer getValueAt(int idx) {
        Integer val = values.get(idx);
        return val == null ? 0 : val;
    }
}
