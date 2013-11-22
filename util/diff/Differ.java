package org.incava.ijdk.util.diff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Compares two collections, returning a list of the additions, changes, and
 * deletions between them. A <code>Comparator</code> may be passed as an
 * argument to the constructor, and will thus be used. If not provided, the
 * initial value in the <code>from</code> collection will be looked at to see if
 * it supports the <code>Comparable</code> interface. If so, its
 * <code>equals</code> and <code>compareTo</code> methods will be invoked on the
 * instances in the "from" and "to" collections; otherwise, for speed, hash
 * codes from the objects will be used instead for comparison.
 *
 * <p>The file FileDiff.java shows an example usage of this class, in an
 * application similar to the Unix "diff" program.</p>
 */
public abstract class Differ <ObjectType extends Object, DiffType extends Difference> {
    /**
     * The source array, AKA the "from" values.
     */
    protected final List<ObjectType> from;

    /**
     * The target array, AKA the "to" values.
     */
    protected final List<ObjectType> to;

    /**
     * The list of differences, as <code>DiffType</code> instances.
     */
    protected final List<DiffType> diffs;

    /**
     * The pending, uncommitted difference.
     */
    private DiffType pending;

    /**
     * The comparator used, if any.
     */
    private final Comparator<ObjectType> comparator;

    /**
     * Constructs the Differ object for the two arrays, using the given comparator.
     */
    public Differ(ObjectType[] from, ObjectType[] to, Comparator<ObjectType> comp) {
        this(Arrays.asList(from), Arrays.asList(to), comp);
    }

    /**
     * Constructs the Differ object for the two arrays, using the default
     * comparison mechanism between the objects, such as <code>equals</code> and
     * <code>compareTo</code>.
     */
    public Differ(ObjectType[] from, ObjectType[] to) {
        this(from, to, null);
    }

    /**
     * Constructs the Differ object for the two collections, using the default
     * comparison mechanism between the objects, such as <code>equals</code> and
     * <code>compareTo</code>.
     */
    public Differ(List<ObjectType> from, List<ObjectType> to) {
        this(from, to, null);
    }

    /**
     * Constructs the Differ object for the two collections, using the given
     * comparator.
     */
    public Differ(List<ObjectType> from, List<ObjectType> to, Comparator<ObjectType> comp) {
        this.from = from;
        this.to = to;
        this.comparator = comp;
        this.diffs = new ArrayList<DiffType>();
    }

    /**
     * Runs diff and returns the results.
     */
    public List<DiffType> execute() {
        traverseSequences();
        addPending();
        return diffs;
    }

    /**
     * Runs diff and returns the results.
     *
     * @deprecated <code>execute</code> is a more accurate and descriptive name.
     */
    @Deprecated 
    public List<DiffType> diff() {
        return execute();
    }

    /**
     * Subclasses override this to return their own subclass of
     * <code>Difference</code>.
     */
    public abstract DiffType createDifference(Integer delStart, Integer delEnd, Integer addStart, Integer addEnd);

    /**
     * Adds the last difference, if pending.
     */
    protected void addPending() {
        if (pending != null) {
            tr.Ace.cyan("pending", pending);
            diffs.add(pending);
        }
    }

    /**
     * Traverses the sequences, seeking the longest common subsequences,
     * invoking the methods <code>finishedFrom</code>, <code>finishedTo</code>,
     * <code>onFromNotTo</code>, and <code>onToNotFrom</code>.
     */
    protected void traverseSequences() {
        Integer[] matches = getLongestCommonSubsequences();

        int lastA = from.size() - 1;
        int lastB = to.size() - 1;
        int bi = 0;
        int ai;
        int lastMatch = matches.length - 1;
        
        for (ai = 0; ai <= lastMatch; ++ai) {
            Integer bLine = matches[ai];

            if (bLine == null) {
                onFromNotTo(ai, bi);
            }
            else {
                while (bi < bLine.intValue()) {
                    onToNotFrom(ai, bi++);
                }

                onMatch(ai, bi++);
            }
        }

        boolean calledFinishA = false;
        boolean calledFinishB = false;

        while (ai <= lastA || bi <= lastB) {
            // last FROM?
            if (ai == lastA + 1 && bi <= lastB) {
                if (!calledFinishA && callFinishedFrom()) {
                    finishedFrom(lastA);
                    calledFinishA = true;
                }
                else {
                    while (bi <= lastB) {
                        onToNotFrom(ai, bi++);
                    }
                }
            }

            // last TO?
            if (bi == lastB + 1 && ai <= lastA) {
                if (!calledFinishB && callFinishedTo()) {
                    finishedTo(lastB);
                    calledFinishB = true;
                }
                else {
                    while (ai <= lastA) {
                        onFromNotTo(ai++, bi);
                    }
                }
            }

            if (ai <= lastA) {
                onFromNotTo(ai++, bi);
            }

            if (bi <= lastB) {
                onToNotFrom(ai, bi++);
            }
        }
    }

    /**
     * Override and return true in order to have <code>finishedFrom</code> invoked
     * at the last element in the <code>from</code> array.
     */
    protected boolean callFinishedFrom() {
        return false;
    }

    /**
     * Override and return true in order to have <code>finishedTo</code> invoked
     * at the last element in the <code>to</code> array.
     */
    protected boolean callFinishedTo() {
        return false;
    }

    /**
     * Invoked at the last element in <code>from</code>, if
     * <code>callFinishedFrom</code> returns true.
     */
    protected void finishedFrom(int lastA) {
    }

    /**
     * Invoked at the last element in <code>to</code>, if
     * <code>callFinishedTo</code> returns true.
     */
    protected void finishedTo(int lastB) {
    }

    /**
     * Invoked for elements in <code>from</code> and not in <code>to</code>.
     */
    protected void onFromNotTo(int ai, int bi) {
        if (pending == null) {
            pending = createDifference(ai, ai, bi, -1);
        }
        else {
            pending.setDeleted(ai);
        }
    }

    /**
     * Invoked for elements in <code>to</code> and not in <code>from</code>.
     */
    protected void onToNotFrom(int ai, int bi) {
        if (pending == null) {
            pending = createDifference(ai, -1, bi, bi);
        }
        else {
            pending.setAdded(bi);
        }
    }

    /**
     * Invoked for elements matching in <code>from</code> and <code>to</code>.
     */
    protected void onMatch(int ai, int bi) {
        if (pending != null) {
            tr.Ace.cyan("pending", pending);
            diffs.add(pending);
            pending = null;
        }
    }

    /**
     * Compares the two objects, using the comparator provided with the
     * constructor, if any.
     */
    protected boolean equals(ObjectType x, ObjectType y) {
        return comparator == null ? x.equals(y) : comparator.compare(x, y) == 0;
    }
    
    private Map<ObjectType, List<Integer>> getBMatches(int toStart, int toEnd) {
        Map<ObjectType, List<Integer>> bMatches = null;
        if (comparator == null) {
            if (from.size() > 0 && from.get(0) instanceof Comparable) {
                // this uses the Comparable interface
                bMatches = new TreeMap<ObjectType, List<Integer>>();
            }
            else {
                // this just uses hashCode()
                bMatches = new HashMap<ObjectType, List<Integer>>();
            }
        }
        else {
            // we don't really want them sorted, but this is the only Map
            // implementation (as of JDK 1.4) that takes a comparator.
            bMatches = new TreeMap<ObjectType, List<Integer>>(comparator);
        }

        for (int bi = toStart; bi <= toEnd; ++bi) {
            ObjectType key = to.get(bi);
            List<Integer> positions = bMatches.get(key);
            if (positions == null) {
                positions = new ArrayList<Integer>();
                bMatches.put(key, positions);
            }
            positions.add(bi);
        }

        return bMatches;
    }

    private void addMatches(TreeMap<Integer, Integer> matches, int fromStart, int fromEnd, int toStart, int toEnd) {
        Map<ObjectType, List<Integer>> bMatches = getBMatches(toStart, toEnd);

        LCSTable links = new LCSTable();
        Thresholds thresh = new Thresholds();

        for (int i = fromStart; i <= fromEnd; ++i) {
            Object aElement = from.get(i); // keygen here.
            List<Integer> positions = bMatches.get(aElement);

            if (positions != null) {
                Integer k = 0;
                ListIterator<Integer> pit = positions.listIterator(positions.size());
                while (pit.hasPrevious()) {
                    Integer j = pit.previous();
                    k = thresh.insert(j, k);
                    if (k != null) {
                        links.update(i, j, k);
                    }   
                }
            }
        }

        if (!thresh.isEmpty()) {
            Integer ti = thresh.lastKey();
            Map<Integer, Integer> chain = links.getChain(ti);
            matches.putAll(chain);
        }
    }

    /**
     * Returns an array of the longest common subsequences.
     */
    public Integer[] getLongestCommonSubsequences() {
        int fromStart = 0;
        int fromEnd = from.size() - 1;

        int toStart = 0;
        int toEnd = to.size() - 1;

        TreeMap<Integer, Integer> matches = new TreeMap<Integer, Integer>();

        // common beginning and ending elements:
        while (fromStart <= fromEnd && toStart <= toEnd && equals(from.get(fromStart), to.get(toStart))) {
            matches.put(fromStart++, toStart++);
        }
        while (fromStart <= fromEnd && toStart <= toEnd && equals(from.get(fromEnd), to.get(toEnd))) {
            matches.put(fromEnd--, toEnd--);
        }

        addMatches(matches, fromStart, fromEnd, toStart, toEnd);
        
        return toArray(matches);
    }

    /**
     * Converts the map into an array.
     */
    protected static Integer[] toArray(TreeMap<Integer, Integer> map) {
        int       size = map.isEmpty() ? 0 : 1 + map.lastKey();
        Integer[] ary  = new Integer[size];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ary[entry.getKey()] = entry.getValue();
        }
        return ary;
    }
}
