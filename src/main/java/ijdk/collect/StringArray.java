package ijdk.collect;

import ijdk.lang.Str;
import java.util.Arrays;

public class StringArray extends Array<String> {
    public StringArray(String ... ary) {
        super(ary);
    }
    
    /**
     * Returns a string of the form "[foo, bar]", or "[]" if the array is null or empty.
     */
    public String toString() {
        return isEmpty() ? "[]" : Arrays.asList(ary()).toString();
    }

    /**
     * Returns whether the two arrays are equal in content or both are empty.
     *
     * @see #isEmpty
     */
    public boolean equals(Object other) {
        if (other instanceof StringArray) {
            return equals(((StringArray)other).ary());
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
        StringArray otherArray = new StringArray(other);
        if (isEmpty()) {
            return otherArray.isEmpty();
        }
        else if (otherArray.isEmpty()) {
            return false;
        }
        else if (length() != otherArray.length()) {
            return false;
        }
        else {
            for (int idx = 0; idx < length(); ++idx) {
                Str x = new Str(get(idx));
                Str y = new Str(otherArray.get(idx));
                if (!x.equals(y)) {
                    return false;
                }
            }
            return true;
        }
    }
}
