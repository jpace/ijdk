package org.incava.ijdk.lang;

/**
 * A list with extended functionality, modeled on the Enumerable class in Ruby.
 */
public interface Sequence<T> extends java.util.List<T> {
    /**
     * Adds the given element, and returns a sequence, so this method call can be chained into
     * multiple ones. The returned sequence could be either this object, or a newly-created object.
     */
    public Sequence<T> append(T element);

    /**
     * Returns the first element in the sequence.
     */
    public T first();

    /**
     * Returns the last element in the sequence.
     */
    public T last();

    /**
     * Removes and returns the first element in the sequence. Returns null if the sequence is empty.
     *
     * @return the first element
     */
    public T takeFirst();

    /**
     * Removes and returns the last element in the sequence. Returns null if the sequence is empty.
     *
     * @return the last element
     */
    public T takeLast();

    /**
     * Returns a new sequence that contains unique elements from the sequence, in the same order as
     * in this one.
     */
    public Sequence<T> unique();

    /**
     * Returns a new sequence that contains only the non-null elements from this sequence, in the
     * same order as in this sequence.
     */
    public Sequence<T> compact();

    /**
     * Returns the sequence as a String, joined by the delimiter.
     */
    public String join(String delimiter);
}
