package org.incava.ijdk.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A regular expression, more closely matching the functionality in Ruby.
 */
public class Regexp {
    /**
     * Creates a regular expression for the given pattern.
     *
     * @param pattern the pattern
     * @return the regular expression
     */
    public static Regexp of(String pattern) {
        return new Regexp(pattern);
    }
    
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

    /**
     * Returns MatchData for the first match of the pattern in the string, starting at the given
     * offset. Does not do a full match, as does java.util.regex.Matcher#matches.
     *
     * @param str the string to match against the pattern
     * @param offset the offset at which to match against the pattern
     * @return the matching data, or null if not a match
     */
    public MatchData match(String str, Integer offset) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find(offset) ? MatchData.of(matcher) : null;
    }

    /**
     * Returns whether the regular expression matches the string. As with <code>match</code>, does
     * not do a full match, as does java.util.regex.Matcher#matches.
     *
     * @param str the string to match against the pattern
     * @return the whether there was a match
     */
    public boolean isMatch(String str) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    /**
     * Returns whether the regular expression matches the string, starting at the given offset. As
     * with <code>match</code>, does not do a full match, as does java.util.regex.Matcher#matches.
     *
     * @param str the string to match against the pattern
     * @param offset the offset at which to match against the pattern
     * @return the whether there was a match
     */
    public boolean isMatch(String str, Integer offset) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find(offset);
    }

    /**
     * Returns the regular expression as a pattern.
     *
     * @return the pattern
     */
    public String toString() {
        return pattern.toString();
    }
}
