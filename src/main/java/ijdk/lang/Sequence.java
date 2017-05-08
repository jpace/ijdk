package ijdk.lang;

/**
 * A list with extended functionality, modeled on the Enumerable class in Ruby.
 */
public interface Sequence<T> extends java.util.List<T> {
    /**
     * Adds the given element, and returns the sequence, so this method call can be chained into
     * multiple ones.
     */
    // public Sequence<T> append(T element);

    /**
     * Returns the first element in the sequence.
     */
    public T first();

    /**
     * Returns the last element in the sequence.
     */
    public T last();
}
