package ijdk.lang;

import java.util.Arrays;

public class StringAry extends Ary<String> {
    public StringAry(String ... ary) {
        super(ary);
    }
    
    /**
     * Returns a string of the form "[foo, bar]", or "[]" if the array is null or empty.
     */
    public String toString() {
        return isEmpty() ? "[]" : Arrays.asList(ary()).toString();
    }

    /**
     * Returns whether the array is null or empty.
     */
    public boolean isEmpty() {
        String[] ary = ary();
        return ary == null || ary.length == 0;
    }

    /**
     * Returns whether the two arrays are equal in content or both are empty.
     *
     * @see #isEmpty
     */
    public boolean equals(Object other) {
        if (other instanceof StringAry) {
            return equals(((StringAry)other).ary());
        }
        else if (other instanceof String[]) {
            return equals((String[])other);
        }
        else if (other == null) {
            return ary() == null;
        }
        else {
            return false;
        }
    }

    /**
     * Returns whether the two arrays are equal in content or both are empty.
     *
     * @see #isEmpty
     */
    public boolean equals(String[] other) {
        StringAry otherAry = new StringAry(other);
        if (isEmpty()) {
            return otherAry.isEmpty();
        }
        else if (otherAry.isEmpty()) {
            return false;
        }
        else if (length() != otherAry.length()) {
            return false;
        }
        else {
            for (int idx = 0; idx < length(); ++idx) {
                Str x = new Str(get(idx));
                Str y = new Str(otherAry.get(idx));
                if (!x.equals(y)) {
                    return false;
                }
            }
            return true;
        }
    }
}
