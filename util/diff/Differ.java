package org.incava.ijdk.util.diff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
    private final List<ObjectType> from;

    /**
     * The target array, AKA the "to" values.
     */
    private final List<ObjectType> to;

    /**
     * The list of differences, as <code>DiffType</code> instances.
     */
    private final List<DiffType> diffs;

    /**
     * The pending, uncommitted difference.
     */
    private DiffType pending;

    /**
     * The point at which the pending deletion starts.
     */
    private Integer delStart = Difference.NONE;

    /**
     * The point at which the pending deletion ends.
     */
    private Integer delEnd = Difference.NONE;

    /**
     * The point at which the pending addition starts.
     */
    private Integer addStart = Difference.NONE;

    /**
     * The point at which the pending addition ends.
     */
    private Integer addEnd = Difference.NONE;

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
     * Subclasses implement this to return their own subclass of
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
     * invoking the methods <code>finishedFrom</code>, <code>finishedTo</code>,
     * <code>onFromNotTo</code>, and <code>onToNotFrom</code>.
     */
    protected void traverseSequences() {
        LCS<ObjectType> lcs = new LCS<ObjectType>(from, to, comparator);
        List<Integer> matches = lcs.getMatches();

        int lastFrom = from.size() - 1;
        int lastTo = to.size() - 1;
        int toIdx = 0;
        int fromIdx = 0;
        int lastMatch = matches.size() - 1;
        
        while (fromIdx <= lastMatch) {
            Integer toElement = matches.get(fromIdx);

            if (toElement == null) {
                onFromNotTo(fromIdx, toIdx);
            }
            else {
                while (toIdx < toElement.intValue()) {
                    onToNotFrom(fromIdx, toIdx++);
                }

                onMatch(fromIdx, toIdx++);
            }
            fromIdx++;
        }

        boolean calledFinishFrom = false;
        boolean calledFinishTo = false;

        while (fromIdx <= lastFrom || toIdx <= lastTo) {
            // last from?
            if (fromIdx == lastFrom + 1 && toIdx <= lastTo) {
                if (!calledFinishFrom && callFinishedFrom()) {
                    finishedFrom(lastFrom);
                    calledFinishFrom = true;
                }
                else {
                    while (toIdx <= lastTo) {
                        onToNotFrom(fromIdx, toIdx++);
                    }
                }
            }

            // last to?
            if (toIdx == lastTo + 1 && fromIdx <= lastFrom) {
                if (!calledFinishTo && callFinishedTo()) {
                    finishedTo(lastTo);
                    calledFinishTo = true;
                }
                else {
                    while (fromIdx <= lastFrom) {
                        onFromNotTo(fromIdx++, toIdx);
                    }
                }
            }

            if (fromIdx <= lastFrom) {
                onFromNotTo(fromIdx++, toIdx);
            }

            if (toIdx <= lastTo) {
                onToNotFrom(fromIdx, toIdx++);
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
    protected void finishedFrom(int lastFrom) {
    }

    /**
     * Invoked at the last element in <code>to</code>, if
     * <code>callFinishedTo</code> returns true.
     */
    protected void finishedTo(int lastTo) {
    }

    /**
     * Sets the point as deleted. The start and end points will be modified to
     * include the given index.
     */
    public void setDeleted(int index) {
        pending.setDeleted(index);
        delStart = Math.min(index, delStart);
        delEnd = Math.max(index, delEnd);
        tr.Ace.setVerbose(true);
        tr.Ace.yellow("pending", pending);
        tr.Ace.yellow("delStart", delStart);
        tr.Ace.yellow("delEnd", delEnd);
    }

    /**
     * Sets the point as added. The start and end points will be modified to
     * include the given index.
     */
    public void setAdded(int index) {
        pending.setAdded(index);
        addStart = Math.min(index, addStart);
        addEnd = Math.max(index, addEnd);
        tr.Ace.setVerbose(true);
        tr.Ace.cyan("pending", pending);
        tr.Ace.cyan("addStart", addStart);
        tr.Ace.cyan("addEnd", addEnd);
    }

    /**
     * Invoked for elements in <code>from</code> and not in <code>to</code>.
     */
    protected void onFromNotTo(int fromIdx, int toIdx) {
        if (pending == null) {
            pending = createDifference(fromIdx, fromIdx, toIdx, Difference.NONE);
            delStart = fromIdx;
            delEnd   = fromIdx;
            addStart = toIdx;
            addEnd   = Difference.NONE;
        }
        else {
            setDeleted(fromIdx);
        }
    }

    /**
     * Invoked for elements in <code>to</code> and not in <code>from</code>.
     */
    protected void onToNotFrom(int fromIdx, int toIdx) {
        tr.Ace.log("fromIdx", fromIdx);
        if (pending == null) {
            pending = createDifference(fromIdx, Difference.NONE, toIdx, toIdx);
            delStart = fromIdx;
            delEnd   = Difference.NONE;
            addStart = toIdx;
            addEnd   = toIdx;
        }
        else {
            setAdded(toIdx);
        }
    }

    /**
     * Invoked for elements matching in <code>from</code> and <code>to</code>.
     */
    protected void onMatch(int fromIdx, int toIdx) {
        if (pending != null) {
            diffs.add(pending);
            pending = null;
            delStart = null;
            delEnd   = null;
            addStart = null;
            addEnd   = null;
        }
    }
}
