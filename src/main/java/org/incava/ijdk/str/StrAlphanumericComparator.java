package org.incava.ijdk.str;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.incava.ijdk.lang.Str;

public class StrAlphanumericComparator implements Comparator<Str> {
    private final Pattern pattern;

    public StrAlphanumericComparator() {
        this.pattern = Pattern.compile("\\d+(?:\\.\\d)*");
    }
    
    public int compare(Str s, Str t) {
        // I'd like to use Str#scan, but that gets cyclical, with both in development right now
        // (3.8.0)

        Integer sIdx = 0;
        Integer tIdx = 0;

        Integer sLen = s.length();
        Integer tLen = t.length();

        while (sIdx < sLen && tIdx < tLen) {
            String sNum = matchNumber(s, sIdx);
            String tNum = matchNumber(t, tIdx);

            if (sNum == null) {
                if (tNum == null) {
                    Character sCh = s.charAt(sIdx);
                    Character tCh = t.charAt(tIdx);
                    int cmp = sCh.compareTo(tCh);
                    if (cmp == 0) {
                        ++sIdx;
                        ++tIdx;
                    }
                    else {
                        return cmp;
                    }
                }
                else {
                    return 1;
                }
            }
            else if (tNum == null) {
                return -1;
            }
            else {
                Double sDbl = Double.valueOf(sNum);
                Double tDbl = Double.valueOf(tNum);
                int cmp = sDbl.compareTo(tDbl);
                if (cmp == 0) {
                    sIdx += sNum.length();
                    tIdx += tNum.length();
                }
                else {
                    return cmp;
                }
            }
            
        }

        return sLen - tLen;
    }

    public boolean equals(Object obj) {
        return obj instanceof StrIgnoreCaseComparator;
    }

    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns whether the substring starting at <code>idx</code> is a number, floating point or
     * integer.
     */
    public String matchNumber(Str x, int idx) {
        Matcher matcher = this.pattern.matcher(x.str());
        matcher.useAnchoringBounds(false);
        return matcher.find(idx) && matcher.start() == idx ? matcher.group(0) : null;
    }
}
