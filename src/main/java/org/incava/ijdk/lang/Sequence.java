package org.incava.ijdk.lang;

/**
 * A list with extended functionality, modeled on the Enumerable class in Ruby.
 *
 * @param <T> the element type
 * @param <C> the collection (sequence) type, a subclass of Sequence
 */
public interface Sequence<T, C extends Sequence<T, C>> extends java.util.List<T> {
    /**
     * Adds the given element, and returns a sequence, so this method call can be chained into
     * multiple ones. The returned sequence could be either this object, or a newly-created object.
     *
     * @param element the element to add
     * @return this sequence
     */
    public C append(T element);

    /**
     * Returns the first element in the sequence.
     *
     * @return the first element, or null if none
     */
    public T first();

    /**
     * Returns the last element in the sequence.
     *
     * @return the last element, or null if none
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
     *
     * @return a new sequence of unique elements
     */
    public C unique();

    /**
     * Returns a new sequence that contains only the non-null elements from this sequence, in the
     * same order as in this sequence.
     *
     * @return a new sequence of non-null elements
     */
    public C compact();

    /**
     * Returns the sequence as a String, joined by the delimiter.
     * 
     * @param delimiter the delimiter between each element
     * @return the joined string
     */
    public String join(String delimiter);
}
