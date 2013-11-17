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
 * initial value in the <code>a</code> ("from") collection will be looked at to
 * see if it supports the <code>Comparable</code> interface. If so, its
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
    protected final List<ObjectType> a;

    /**
     * The target array, AKA the "to" values.
     */
    protected final List<ObjectType> b;

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
    public Differ(ObjectType[] a, ObjectType[] b, Comparator<ObjectType> comp) {
        this(Arrays.asList(a), Arrays.asList(b), comp);
    }

    /**
     * Constructs the Differ object for the two arrays, using the default
     * comparison mechanism between the objects, such as <code>equals</code> and
     * <code>compareTo</code>.
     */
    public Differ(ObjectType[] a, ObjectType[] b) {
        this(a, b, null);
    }

    /**
     * Constructs the Differ object for the two collections, using the default
     * comparison mechanism between the objects, such as <code>equals</code> and
     * <code>compareTo</code>.
     */
    public Differ(List<ObjectType> a, List<ObjectType> b) {
        this(a, b, null);
    }

    /**
     * Constructs the Differ object for the two collections, using the given
     * comparator.
     */
    public Differ(List<ObjectType> a, List<ObjectType> b, Comparator<ObjectType> comp) {
        this.a = a;
        this.b = b;
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
            diffs.add(pending);
        }
    }

    /**
     * Traverses the sequences, seeking the longest common subsequences,
     * invoking the methods <code>finishedA</code>, <code>finishedB</code>,
     * <code>onANotB</code>, and <code>onBNotA</code>.
     */
    protected void traverseSequences() {
        Integer[] matches = getLongestCommonSubsequences();

        int lastA = a.size() - 1;
        int lastB = b.size() - 1;
        int bi = 0;
        int ai;
        int lastMatch = matches.length - 1;
        
        for (ai = 0; ai <= lastMatch; ++ai) {
            Integer bLine = matches[ai];

            if (bLine == null) {
                onANotB(ai, bi);
            }
            else {
                while (bi < bLine.intValue()) {
                    onBNotA(ai, bi++);
                }

                onMatch(ai, bi++);
            }
        }

        boolean calledFinishA = false;
        boolean calledFinishB = false;

        while (ai <= lastA || bi <= lastB) {
            // last A?
            if (ai == lastA + 1 && bi <= lastB) {
                if (!calledFinishA && callFinishedA()) {
                    finishedA(lastA);
                    calledFinishA = true;
                }
                else {
                    while (bi <= lastB) {
                        onBNotA(ai, bi++);
                    }
                }
            }

            // last B?
            if (bi == lastB + 1 && ai <= lastA) {
                if (!calledFinishB && callFinishedB()) {
                    finishedB(lastB);
                    calledFinishB = true;
                }
                else {
                    while (ai <= lastA) {
                        onANotB(ai++, bi);
                    }
                }
            }

            if (ai <= lastA) {
                onANotB(ai++, bi);
            }

            if (bi <= lastB) {
                onBNotA(ai, bi++);
            }
        }
    }

    /**
     * Override and return true in order to have <code>finishedA</code> invoked
     * at the last element in the <code>a</code> array.
     */
    protected boolean callFinishedA() {
        return false;
    }

    /**
     * Override and return true in order to have <code>finishedB</code> invoked
     * at the last element in the <code>b</code> array.
     */
    protected boolean callFinishedB() {
        return false;
    }

    /**
     * Invoked at the last element in <code>a</code>, if
     * <code>callFinishedA</code> returns true.
     */
    protected void finishedA(int lastA) {
    }

    /**
     * Invoked at the last element in <code>b</code>, if
     * <code>callFinishedB</code> returns true.
     */
    protected void finishedB(int lastB) {
    }

    /**
     * Invoked for elements in <code>a</code> and not in <code>b</code>.
     */
    protected void onANotB(int ai, int bi) {
        if (pending == null) {
            pending = createDifference(ai, ai, bi, -1);
        }
        else {
            pending.setDeleted(ai);
        }
    }

    /**
     * Invoked for elements in <code>b</code> and not in <code>a</code>.
     */
    protected void onBNotA(int ai, int bi) {
        if (pending == null) {
            pending = createDifference(ai, -1, bi, bi);
        }
        else {
            pending.setAdded(bi);
        }
    }

    /**
     * Invoked for elements matching in <code>a</code> and <code>b</code>.
     */
    protected void onMatch(int ai, int bi) {
        if (pending != null) {
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
    
    private Map<ObjectType, List<Integer>> getBMatches(int bStart, int bEnd) {
        Map<ObjectType, List<Integer>> bMatches = null;
        if (comparator == null) {
            if (a.size() > 0 && a.get(0) instanceof Comparable) {
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

        for (int bi = bStart; bi <= bEnd; ++bi) {
            ObjectType key = b.get(bi);
            List<Integer> positions = bMatches.get(key);
            if (positions == null) {
                positions = new ArrayList<Integer>();
                bMatches.put(key, positions);
            }
            positions.add(bi);
        }

        return bMatches;
    }

    private void addMatches(TreeMap<Integer, Integer> matches, int aStart, int aEnd, int bStart, int bEnd) {
        Map<ObjectType, List<Integer>> bMatches = getBMatches(bStart, bEnd);

        LCSTable links = new LCSTable();
        Thresholds thresh = new Thresholds();

        for (int i = aStart; i <= aEnd; ++i) {
            Object aElement = a.get(i); // keygen here.
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
        int aStart = 0;
        int aEnd = a.size() - 1;

        int bStart = 0;
        int bEnd = b.size() - 1;

        TreeMap<Integer, Integer> matches = new TreeMap<Integer, Integer>();

        // common beginning and ending elements:
        while (aStart <= aEnd && bStart <= bEnd && equals(a.get(aStart), b.get(bStart))) {
            matches.put(aStart++, bStart++);
        }
        while (aStart <= aEnd && bStart <= bEnd && equals(a.get(aEnd), b.get(bEnd))) {
            matches.put(aEnd--, bEnd--);
        }

        addMatches(matches, aStart, aEnd, bStart, bEnd);
        
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
