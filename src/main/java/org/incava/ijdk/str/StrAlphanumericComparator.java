package org.incava.ijdk.str;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.incava.ijdk.lang.Str;
import org.incava.ijdk.lang.Strings;

public class StrAlphanumericComparator implements Comparator<Str> {
    private final Pattern pattern;
    
    public StrAlphanumericComparator() {
        this.pattern = Pattern.compile("\\d+(?:\\.\\d+)?");
    }

    public int compare(Str s, Str t) {
        // I'd like to use Str#scan, but that gets cyclical, with both in development right now
        // (3.8.0)

        return compare(s, t, 0, 0);
    }

    public int compare(Str s, Str t, Integer sIdx, Integer tIdx) {
        int sLen = s.length();
        int tLen = t.length();
        
        if (sIdx >= sLen || tIdx >= tLen) {
            return Integer.signum(sLen - tLen);
        }

        String sNum = matchNumber(s, sIdx);
        String tNum = matchNumber(t, tIdx);

        if (sNum == null) {
            if (tNum == null) {
                int cmp = compareChars(s, sIdx, t, tIdx);
                return cmp == 0 ? compare(s, t, sIdx + 1, tIdx + 1) : cmp;
            }
            else {
                return 1;
            }
        }
        else if (tNum == null) {
            return -1;
        }
        else {
            int cmp = compareNumbers(sNum, tNum);
            return cmp == 0 ? compare(s, t, sIdx + sNum.length(), tIdx + tNum.length()) : cmp;
        }            
    }    

    public static int compareChars(Str s, int sIdx, Str t, int tIdx) {
        Character sCh = s.charAt(sIdx);
        Character tCh = t.charAt(tIdx);
        return sCh.compareTo(tCh);
    }

    public static int compareNumbers(String sNum, String tNum) {
        Double sDbl = Double.valueOf(sNum);
        Double tDbl = Double.valueOf(tNum);
        return sDbl.compareTo(tDbl);
    }

    /**
     * Returns whether the substring starting at <code>idx</code> is a number, floating point or
     * integer.
     *
     * @param x the string to check
     * @param idx the starting index
     * @return the number, as a string
     */
    public String matchNumber(Str x, int idx) {
        Matcher matcher = this.pattern.matcher(x.str());
        matcher.useAnchoringBounds(false);
        return matcher.find(idx) && matcher.start() == idx ? matcher.group(0) : null;
    }
}
