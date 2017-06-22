package org.incava.ijdk.lang;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Similar to DescribedAsMatcher, ContextMatcher wraps a matcher with context, displayed as
 * "&lt;expected&gt; (context)". This is mainly for use in parameterized tests, such as (from ijdk):
 *
 * <pre>
 * @Test
 * @Parameters
 * @TestCaseName("{method} {index} {params}")
 * public &lt;T extends Comparable&lt;T&gt;&gt; void compare(int expected, T x, T y) {
 *     int result = Comp.compare(x, y);
 *     assertThat(result, withContext(equalTo(expected), "x: " + x + "; y: " + y));
 * }
 * </pre>
 */
public class ContextMatcher<T> extends BaseMatcher<T> {
    public static <T> ContextMatcher<T> withContext(Matcher<T> matcher, String context) {
        return new ContextMatcher<T>(matcher, context);
    }
    
    private final Matcher<T> matcher;
    private final String context;
    
    public ContextMatcher(Matcher<T> matcher, String context) {
        this.matcher = matcher;
        this.context = context;
    }

    @Override
    public boolean matches(Object obj) {
        return matcher.matches(obj);
    }

    @Override
    public void describeTo(Description description) {
        matcher.describeTo(description);
        description.appendText(" (" + context + ") ");
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        matcher.describeMismatch(item, description);
    }   
}
