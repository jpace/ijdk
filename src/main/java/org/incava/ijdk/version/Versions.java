package org.incava.ijdk.version;

import org.incava.ijdk.collect.Array;

public class Versions extends Array<Version> {
    public static final long serialVersionUID = 1L;

    public Versions() {
    }

    public Versions(Array<Version> ary) {
        super(ary);
    }

    /**
     * Returns the version that is an exact match of <code>version</code>.
     *
     * @param version the version to match
     * @return the matched version
     */
    public Version find(Version version) {
        if (version == Version.LATEST) {
            return findLatest();
        }
        for (Version v : this) {
            if (v.equals(version)) {
                return v;
            }
        }
        return null;        
    }

    /**
     * Returns the latest <code>Version</code> up through <code>version</code>.
     *
     * @param version the version to match
     * @return the matched version
     */
    public Version findThrough(Version version) {
        Version latest = null;
        for (Version v : this) {
            if (v.lte(version) && (latest == null || v.gt(latest))) {
                latest = v;
            }
        }
        return latest;
    }

    /**
     * Returns the latest <code>Version</code> up to, but not through,
     * <code>version</code>. If <code>version</code> is <code>Version.LATEST</code>, then the latest
     * <code>Version</code> is returned.
     *
     * @param version the version to match
     * @return the matched version
     */
    public Version findUpTo(Version version) {
        Version latest = null;
        for (Version v : this) {
            if (v.lt(version) && (latest == null || v.gt(latest))) {
                latest = v;
            }
        }
        return latest;
    }

    /**
     * Returns the latest <code>Version</code>.
     *
     * @return the latest version
     */
    public Version findLatest() {
        return findThrough(Version.LATEST);
    }
}
