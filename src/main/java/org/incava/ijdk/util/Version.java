package org.incava.ijdk.util;

import org.incava.ijdk.collect.DynamicArray;
import org.incava.ijdk.lang.Comp;
import org.incava.ijdk.lang.Obj;

public class Version implements Comparable<Version> {
    private final Integer major;
    private final Integer minor;
    private final Integer build;
    private final Integer revision;
    
    public Version(String str) {
        String[] nums = str.split("\\.");
        major = getNumber(nums, 0, null);
        minor = getNumber(nums, 1, null);
        build = getNumber(nums, 2, null);
        revision = getNumber(nums, 3, null);
    }

    public Version(Integer ... args) {
        major = getNumber(args, 0, null);
        minor = getNumber(args, 1, null);
        build = getNumber(args, 2, null);
        revision = getNumber(args, 3, null);
    }

    public Integer getMajor() {
        return major;
    }

    public Integer getMinor() {
        return minor;
    }

    public Integer getBuild() {
        return build;
    }

    public Integer getPatch() {
        return getBuild();
    }

    public Integer getRevision() {
        return revision;
    }

    public String toString() {
        return DynamicArray.of(major, minor, build, revision).compact().join(".");
    }

    public int hashCode() {
        int hash = new Obj(major).hashCode();
        hash = hash * 3 + new Obj(minor).hashCode();
        hash = hash * 17 + new Obj(build).hashCode();
        hash = hash * 31 + new Obj(revision).hashCode();
        return hash;
    }

    public boolean equals(Object obj) {
        return obj instanceof Version && compareTo((Version)obj) == 0;
    }

    public int compareTo(Version other) {
        int cmp;
        if ((cmp = compareField(major, other.major)) == 0) {
            if ((cmp = compareField(minor, other.minor)) == 0) {
                if ((cmp = compareField(build, other.build)) == 0) {
                    cmp = compareField(revision, other.revision);
                }
            }
        }
        return cmp;
    }

    /**
     * Returns whether this version is less than the other.
     */
    public boolean lt(Version other) {
        return Comp.lt(this, other);
    }

    /**
     * Returns whether this version is less than or equal to the other.
     */
    public boolean lte(Version other) {
        return Comp.lte(this, other);
    }

    /**
     * Returns whether this version is greater than the other.
     */
    public boolean gt(Version other) {
        return Comp.gt(this, other);
    }

    /**
     * Returns whether this version is greater than or equal to the other.
     */
    public boolean gte(Version other) {
        return Comp.gte(this, other);
    }    

    static int compareField(Integer x, Integer y) {
        return Comp.compare(x, y);
    }

    static Integer getNumber(String[] strs, Integer index, Integer defValue) {
        return strs.length > index ? Integer.valueOf(strs[index]) : defValue;
    }

    static Integer getNumber(Integer[] ints, Integer index, Integer defValue) {
        return ints.length > index ? ints[index] : defValue;
    }

    static StringBuilder append(StringBuilder sb, Integer value, boolean prependDot) {
        if (value == null) {
            return sb;
        }
        else if (prependDot) {
            sb.append('.');
        }
        return sb.append(value);
    }
}
