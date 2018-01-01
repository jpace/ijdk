package org.incava.ijdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.incava.ijdk.collect.Array;
import org.incava.ijdk.collect.StringList;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.incava.attest.Assertions.message;
import static org.incava.attest.ContextMatcher.withContext;
import static org.incava.attest.ExistsMatcher.exists;

public class ArrayExample {
    public void println(String name, Object val) {
        System.out.printf("%-12s: %s\n", name, val);
        // System.out.printf("%s => %s\n", name, val);
    }

    public void println(String str) {
        System.out.printf("%s\n", str);
    }
    
    @Test
    public void initEmpty() {
        println("");
        println("initEmpty");
        ArrayList<Integer> list = new ArrayList<>();
        println("list", list);

        // more explicit that the array is empty (but mutable), no need to use <> or <Integer>:
        Array<Integer> ary = Array.empty();
        println("ary", ary);
    }
    
    @Test
    public void initWithElements() {
        println("");
        println("initWithElements");

        // Arrays.asList returns an immutable array, so ArrayList has to wrap it:
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 17, 212));
        println("list", list);

        // how concise
        Array<Integer> ary = Array.of(3, 17, 212);
        println("ary", ary);
    }
    
    @Test
    public void get() {
        println("");
        println("get");

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(60, 16, 252, 9, 3, 17));
        Array<Integer> ary = Array.of(60, 16, 252, 9, 3, 17);
        
        Integer n;

        n = list.size() > 0 ? list.get(list.size() - 1) : null;
        println("n", n);
        n = ary.get(-1);
        println("n", n);

        n = list.size() > 0 ? list.get(list.size() - 2) : null;
        println("n", n);        
        n = ary.get(-2);
        println("n", n);
        
        n = list.size() >= 8 ? list.get(8) : null;
        println("n", n);
        n = ary.get(8);
        println("n", n);
        
        n = list.size() > 8 ? list.get(list.size() - 8) : null;
        println("n", n);
        n = ary.get(-8);
        println("n", n);
    }
    
    @Test
    public void append() {
        println("");
        println("append");

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 7, 23, 50));

        Array<Integer> ary = Array.of(3, 7, 23, 50);

        list.add(2);
        list.add(8);
        list.add(6);
        println("list", list);        

        // chaining appends:
        ary.append(2).append(8).append(6);
        println("ary", ary);
    }

    @Test
    public void firstAndLast() {
        println("");
        println("firstAndLast");

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(60, 16, 252, 9, 3, 17));
        println("list", list);
        
        Array<Integer> ary = Array.of(60, 16, 252, 9, 3, 17);        
        println("ary", ary);

        Integer n;
        
        n = list.size() > 0 ? list.get(0) : null;
        println("n", n);
        n = ary.first();
        println("n", n);

        n = list.size() > 0 ? list.get(list.size() - 1) : null;
        println("n", n);
        n = ary.last();
        println("n", n);

        n = list.size() > 0 ? list.remove(0) : null;
        println("n", n);
        println("list", list);

        n = ary.takeFirst();
        println("n", n);
        println("ary", ary);

        n = list.size() > 0 ? list.remove(list.size() - 1) : null;
        println("n", n);
        println("list", list);

        n = ary.takeLast();
        println("n", n);
        println("ary", ary);
    }

    @Test
    public void sorted() {
        println("");
        println("sorted");

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(60, 16, 252, 9, 3, 17));
        Array<Integer> ary = Array.of(60, 16, 252, 9, 3, 17);        

        ArrayList<Integer> slist = new ArrayList<>(list);
        Collections.sort(slist);
        println("slist", slist);
        println("list", list);

        Array<Integer> sorted = ary.sorted();
        println("sorted", sorted);
        println("ary", ary);
    }

    @Test
    public void toStringList() {
        println("");
        println("toStringList");

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(60, 16, 252, 9, 3, 17));
        Array<Integer> ary = Array.of(60, 16, 252, 9, 3, 17);        

        List<String> slist = new ArrayList<>();
        for (Integer it : list) {
            slist.add(String.valueOf(it));
        }
        println("slist", slist);
        
        StringList strList = ary.toStringList();
        println("strList", strList);
    }

    @Test
    public void getRandomElement() {
        println("");
        println("getRandomElement");

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(60, 16, 252, 9, 3, 17));
        Array<Integer> ary = Array.of(60, 16, 252, 9, 3, 17);        

        Integer n;

        n = list.isEmpty() ? null : list.get(new java.util.Random().nextInt(list.size()));
        println("n", n);
        
        n = ary.getRandomElement();
        println("n", n);
    }

    @Test
    public void compact() {
        println("");
        println("compact");

        List<Integer> list = new ArrayList<>(Arrays.asList(60, 16, 252, 9, 3, 17));
        Array<Integer> ary = Array.of(60, 16, 252, 9, 3, 17);        

        List<Integer> nlist = new ArrayList<>();
        for (Integer it : list) {
            if (it != null) {
                nlist.add(it);
            }
        }
        println("nlist", nlist);

        Array<Integer> nary = ary.compact();
        println("nary", nary);
    }

    @Test
    public void unique() {
        println("");
        println("unique");

        List<Integer> list = new ArrayList<>(Arrays.asList(11, 13, 11, 2, 4, 8, 11, 8));
        println("list", list);

        Array<Integer> ary = Array.of(11, 13, 11, 2, 4, 8, 11, 8);
        println("ary", ary);

        List<Integer> ulist = new ArrayList<>();
        for (Integer it : list) {
            if (!ulist.contains(it)) {
                ulist.add(it);
            }
        }        
        println("ulist", ulist);

        Array<Integer> uary = ary.unique();
        println("uary", uary);
    }

    @Test
    public void join() {
        println("");
        println("join");

        List<Integer> list = new ArrayList<>(Arrays.asList(60, 16, 252, 9, 3, 17));
        println("list", list);
        
        Array<Integer> ary = Array.of(60, 16, 252, 9, 3, 17);        
        println("ary", ary);

        StringBuilder sb = new StringBuilder();
        List<Integer> ulist = new ArrayList<>();
        boolean isFirst = true;
        for (Integer it : list) {
            if (!isFirst) {
                sb.append(", and ");
                isFirst = false;
            }
            sb.append(String.valueOf(it));
        }
        String slist = sb.toString();
        println("slist", slist);

        String sary = ary.join(", and ");
        println("sary", sary);
    }

    @Test
    public void plus() {
        println("");
        println("plus");

        List<Integer> list = new ArrayList<>(Arrays.asList(60, 16, 252, 9, 3, 17));
        println("list", list);
        
        Array<Integer> ary = Array.of(60, 16, 252, 9, 3, 17);        
        println("ary", ary);        

        List<Integer> toAdd = Arrays.asList(9, 6, 3, 1);

        ArrayList<Integer> plist = new ArrayList<>(list);
        plist.addAll(toAdd);
        println("plist", plist);

        Array<Integer> pary = ary.plus(toAdd);
        println("pary", pary);
    }

    @Test
    public void minus() {
        println("");
        println("minus");

        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 8, 2, 9));
        println("list", list);

        Array<Integer> ary = Array.of(3, 1, 4, 8, 2, 9);
        println("ary", ary);

        List<Integer> toRemove = Arrays.asList(2, 4, 6, 8);

        ArrayList<Integer> mlist = new ArrayList<>(list);
        mlist.removeAll(toRemove);
        println("mlist", mlist);

        Array<Integer> mary = ary.plus(toRemove);
        println("mary", mary);
    }

    @Test
    public void intersection() {
        println("");
        println("intersection");

        List<Integer> other = Arrays.asList(2, 4, 6, 8);

        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 8, 2, 9));
        println("list", list);

        Array<Integer> ary = Array.of(3, 1, 4, 8, 2, 9);
        println("ary", ary);

        List<Integer> ilist = new ArrayList<>();
        for (Integer it : list) {
            if (other.contains(it)) {
                ilist.add(it);
            }
        }
        println("ilist", ilist);

        Array<Integer> iary = ary.intersection(other);
        println("iary", iary);
    }

    @Test
    public void elements() {
        println("");
        println("elements");

        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 8, 2, 9));
        println("list", list);

        Array<Integer> ary = Array.of(3, 1, 4, 8, 2, 9);
        println("ary", ary);

        List<Integer> elist = new ArrayList<>();
        elist.add(list.size() > 3  ? list.get(3) : null);
        elist.add(list.size() > 0  ? list.get(0) : null);
        elist.add(list.size() > 2  ? list.get(list.size() - 2) : null);
        elist.add(list.size() > 3  ? list.get(3) : null);
        println("elist", elist);

        Array<Integer> eary = ary.elements(3, 0, -2, 3);
        println("eary", eary);
    }

    @Test
    public void removeAll() {
        println("");
        println("removeAll");

        List<Integer> list = new ArrayList<>(Arrays.asList(11, 13, 11, 2, 4, 8, 11, 8));
        println("list", list);

        Array<Integer> ary = Array.of(11, 13, 11, 2, 4, 8, 11, 8);
        println("ary", ary);

        // not 11, since that calls remove(index), not remove(element)
        while (list.remove(Integer.valueOf(11))) {
            // nothing
        }
        println("list", list);

        ary.removeAll(11);
        println("ary", ary);
    }

    @Test
    public void set() {
        println("");
        println("set");

        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 8, 2, 9));
        println("list", list);

        Array<Integer> ary = Array.of(3, 1, 4, 8, 2, 9);
        println("ary", ary);

        while (list.size() <= 0) {
            list.add(null);
        }
        list.set(0, 4);

        if (list.size() > 3) {
            list.set(list.size() - 3, 11);
        }
        else {
            throw new IllegalArgumentException("index: " + -3 + " is below the minimum " + -list.size());
        }
        println("list", list);        

        ary.set(0, 4);
        println("ary", ary);

        ary.set(-3, 11);
        println("ary", ary);
    }

    @Test
    public void getRange() {
        println("");
        println("getRange");

        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 8, 2, 9));
        println("list", list);

        Array<Integer> ary = Array.of(3, 1, 4, 8, 2, 9);
        println("ary", ary);

        int from = 1;
        int to = list.size() - 2;
        List<Integer> rlist = new ArrayList<>();
        for (int idx = from; idx <= to; ++idx) {
            rlist.add(list.get(idx));
        }
        println("rlist", rlist);

        Array<Integer> rary = ary.get(1, -2);
        println("rary", rary);
    }

    @Test
    public void containsAny() {
        println("");
        println("containsAny");
        
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 8, 2, 9));
        println("list", list);

        Array<Integer> ary = Array.of(3, 1, 4, 8, 2, 9);
        println("ary", ary);

        boolean b;

        b = list.contains(6) || list.contains(1) || list.contains(11);
        println("b", b);

        b = list.contains(7) || list.contains(17);
        println("b", b);

        b = ary.containsAny(6, 1, 11);
        println("b", b);        

        b = ary.containsAny(7, 17);
        println("b", b);        
    }
}
