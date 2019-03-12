package org.incava.ijdk.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A regular expression, more closely matching the functionality in Ruby.
 */
public class Regexp {
    private final Pattern pattern;
    
    /**
     * Creates a regular expression for the given pattern.
     *
     * @param pattern the pattern
     */
    public Regexp(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    /**
     * Returns MatchData for the first match of the pattern in the string. Does not do a full match,
     * as does java.util.regex.Matcher#matches.
     *
     * @param str the string to match against the pattern
     * @return the matching data, or null if not a match
     */
    public MatchData match(String str) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? MatchData.of(matcher) : null;
    }
}
