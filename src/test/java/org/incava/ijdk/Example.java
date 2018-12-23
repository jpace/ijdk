package org.incava.ijdk;

import java.io.*;
import java.util.*;
import java.util.Arrays;
import org.incava.ijdk.collect.*;
import org.incava.ijdk.collect.MultiMap;
import org.incava.ijdk.io.*;
import org.incava.ijdk.lang.*;
import org.incava.ijdk.util.*;

import static org.incava.ijdk.util.IUtil.*;

public class Example {
    public void mapFromKeyToCollection() {
        String name = "Homer";
        String firstAddress = "742 Evergreen Terrace"; // home
        String secondAddress = "1000 Mammon Street";   // Burns'
        
        List<String> otherAddresses = Arrays.asList(new String[] { 
                "1313 Mockingbird Lane", // Muensters
                "518 Crestview Drive",    // Beverly Hillbillies
            });
        
        {
            // old way:
            Map<String, List<String>> nameToAddressesMapList = new TreeMap<String, List<String>>();
            
            List<String> addresses = nameToAddressesMapList.get(name);
            if (addresses == null) {
                addresses = new ArrayList<String>();
                nameToAddressesMapList.put(name, addresses);
            }
            addresses.add(firstAddress);
            addresses.add(secondAddress);
            addresses.addAll(otherAddresses);

            for (String addr : nameToAddressesMapList.get(name)) {
            }
        }

        // new way:
        {
            MultiMap<String, String> nameToAddressesMapList = new MultiMap<String, String>();
            nameToAddressesMapList.add(name, firstAddress);
            nameToAddressesMapList.add(name, secondAddress);

            // with varargs (String ...)
            nameToAddressesMapList.putEach(name, "1313 Mockingbird Lane", "518 Crestview Drive");

            // put of a collection adds each element, equating to putAll
            nameToAddressesMapList.put(name, otherAddresses);
            
            for (String addr : nameToAddressesMapList.get(name)) {
            }

            // or use a different collection type instead of the default ArrayList:
            MultiMap<String, Integer> nameToSet = new MultiMap<String, Integer>() {
                public Collection<Integer> getCollection() {
                    return new TreeSet<Integer>();
                }
            };
            nameToSet.add("grade", 4);
        }
    }

    public void simplifiedListCreation() {
        // old way:
        {
            List<String> list = new ArrayList<String>();
            list.add("one");
            list.add("two");
            list.add("three");
        }

        // new way:
        {
            List<String> list = IUtil.<String>list(); // specify the generic, since the list type would otherwise be unknown
            list.add("one");
            list.add("two");
            list.add("three");

            List<String> fruits = list("apple", "banana", "cherry");

            List<Double> interesting = list(-1.0, 2.718, 3.14);
        }
    }

    // Datatypes
    public void dataTypePair() {
        // Pair - useful for returning two values from a method:
        Pair<Integer, Integer> maxIndexAndValue = new Pair<Integer, Integer>(14, 10000000);
        
        // // PairList - not quite a mapping, and also retains insertion order:
        // PairList<String, Integer> nameAndAge = new PairList<String, Integer>();
        // nameAndAge.add(Pair.create("Pat", 46));
        // nameAndAge.add(Pair.create("Jeff", 45));
        // nameAndAge.add(Pair.create("Derek", 39));
    }

    // String processing:
    public void stringProcessingStartsWith() {
        String s = "this is a test";

        // starts with, for characters
        // old way, null reference not checked:
        if (s.indexOf('t') == 0) {
        }

        // null reference checked
        int index = s == null ? -1 : s.indexOf('t');
        if (index == 0) {
        }

        // new way:
        if (Strings.startsWith(s, 't')) {
        }
    }

    public void stringPad() {
        // old way:
        {
            String padded = "testing";
            while (padded.length() < 16) {
                padded += '*';
            }
        }

        // new way:
        { 
            String padded = Strings.pad("testing", '*', 16);

            // or for spaces (default character):
            String padded2 = Strings.pad("testing", 16);
        }
    }

    public void stringPadLeft() {
        // old way:
        {
            String padded = "testing";
            while (padded.length() < 16) {
                padded = '*' + padded;
            }
        }

        // new way:
        {
            String padded = Strings.padLeft("testing", '*', 16);

            // or for spaces (default character):
            String padded2 = Strings.padLeft("testing", 16);
        }
    }

    public void stringLeft() {
        // get at most n characters from the string:

        String str = "testing";

        // old way:
        {
            String sub = str.substring(0, Math.min(str.length() - 1, 10));
        }

        // new way:
        {
            String sub = Strings.left(str, 10);
        }

        // ditto for Strings.right
    }

    public void stringJoin() {
        // get a collection in the form "a:b:c:d"
        // old way:

        String[] ary = new String[] { "sorhy", "panaphonic", "magnetbox" };

        {
            StringBuilder joined = new StringBuilder();
            boolean isFirst = true;
            for (String s : ary) {
                if (isFirst) {
                    isFirst = false;
                }
                else {
                    joined.append(':');
                }
                joined.append(s);
            }
            String str = joined.toString();
        }

        // new way
        {
            String str = Strings.join(ary, ":");
        }
    }

    public void stringCharAt() {
        // guards against out of range (returning null)
        // allows negative index, for distance from end (-1 == last char)

        String str = "object-oriented constructor";
        
        // old way
        {
            int idx = 4;
            Character ch = idx < 0 || idx >= str.length() ? null : str.charAt(idx);
        }
        
        // new way
        {
            Character ch1 = Strings.charAt(str, 4);
        }
            
        // old way, last character:
        {
            Character ch = str.charAt(str.length() - 1);
        }
        
        // new way
        {
            Character ch = Strings.charAt(str, -1);
        }
        
        // above can be used with Strings.substring -- negative indices and
        // binding the maximum range to within the string:
        String substr0 = Strings.substring(str, 16, -8); // "cons"
        String substr1 = Strings.substring(str, 16, -1); // "constructor"
        String substr2 = Strings.substring(str, 16, 111); // "constructor"
    }

    public void datatypeRange() {
        // useful for bound values (min, max)

        // old way:
        int min = -1;
        int max = 9999;
        int x = 34;

        if (x >= min && x <= max) {
        }

        // new way
        Range minMax = new Range(-1, 9999);
        if (minMax.includes(x)) {
        }

        // also good for iterating
        Range rg = new Range(3, 17);
        for (int v : rg) {
            System.out.println("v: " + v);
        }   
    }

    public void fileReading() {
        // shortcut for reading a file

        // old way:
        try {
            String fname = "/tmp/something.txt";
            File file = new File(fname);
            List<String> lines = new ArrayList<String>();

            // egads, I'm not even going to do this. you get the idea:
            // new BuffyTheLineReader(new InputStreamer(new File(fname)))...
        }
        catch (Exception e) {
        }

        // new way
        {
            List<String> lines = IO.readLines("/tmp/something.txt");
        }
    }

    public void iterateSafely() {
        // allows iteration against a null object
        
        // old way:
        List<Integer> coll = new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 1, 2, 3, 5, 8, 13 }));
        if (coll != null) {
            for (Integer x : coll) {
            }
        }

        // new way:
        for (Integer x : IUtil.iter(coll)) {
        }
        
        String[] sary = null;
        for (String s : IUtil.iter(sary)) {
            // never called
        }
    }

    public void iteratorNumber() {
        // old way:
        for (int i = 0; i < 10; ++i) {
        }

        // new way:
        for (int i : iter(10)) {
        }
    }

    public void orInsteadOfTertiary() {
        // old way:
        String s = null;
        {
            String t = s == null ? "default for t" : s;
        }

        // new way:
        {
            String t = IUtil.or(s, "default for t");
        }

        // chain them
        
        String t = null;
        // old way:
        {
            String u = s == null ? (t == null ? "default for u" : t) : s;
        }
        
        // new way (static import):
        {
            String u = or(s, t, "default for u");
        }
            
        // the same exists for and:
        // old way:
        { 
            String x = s != null && t != null ? "both" : null;
        }

        // new way:
        { 
            String x = and(s, t, "both");
        }
    }

    public void safeGetForLists() {
        // old way:
        List<String> list = new ArrayList<String>();
        int idx = 4;
        {
            String s = idx < list.size() ? list.get(idx) : null;
        }

        // new way:
        {
            String s = Lists.get(list, idx);
        }

        // use negative indices for distance from end (-1 == end)
        
        {
            String s = Lists.get(list, -1); // last in list
            String t = Lists.get(list, -2); // next to list
            String u = Lists.get(list, -3); // second from last
        }
    }

    public static void main(String[] args) {
        new Example().iterateSafely();
    }
}
