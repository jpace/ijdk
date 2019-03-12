package org.incava.ijdk.regexp;

import java.util.regex.Matcher;
import org.incava.ijdk.collect.StringArray;

/**
 * A set of elements from a regular expression match. The first element is the entire region
 * matched, and each of the other elements is for a capture within the pattern. If the passed
 * matcher does not match, then the array will be empty.
 *
 * Thus for MatchData md, md.get(0) is the entire region, and md.get(-1) is the last capture.
 */
public class MatchData extends StringArray {
    public static final long serialVersionUID = 1L;

    public static MatchData of(Matcher matcher) {
        MatchData md = new MatchData();
        int nGroups = matcher.groupCount();
        for (int idx = 0; idx <= nGroups; ++idx) {
            String str = matcher.group(idx);
            md.add(str);
        }
        return md;
    }
    
    public MatchData(Matcher matcher) {
        if (matcher.matches()) {
            int nGroups = matcher.groupCount();
            for (int idx = 0; idx <= nGroups; ++idx) {
                String str = matcher.group(idx);
                add(str);
            }
        }
    }
    
    public MatchData() {
    }

    public MatchData(StringArray other) {
        super(other);
    }
}
