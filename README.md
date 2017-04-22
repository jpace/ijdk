# Overview

IJDK (Incava Java Development Kit) is a library of general-purposed code, much of it inspired by
Ruby. For example, reading a file is this simple:

```java
    List<String> lines = IO.readLines("foo.txt");
```

Note the similarity to idiomatic Ruby:

```ruby
    lines = IO.readlines("foo.txt")
```

# Usage

IJDK has a very expansive (but growing) library for Java I/O and collections, as an alternative to
the JDK code. It is mostly inspired by Ruby, and parallels Ruby code as closely as possible, usually
moreso than the Apache Commons and Guava libraries.

## shortcuts for list creation:

This is one of the most used pieces of code in IJDK, a shortcut for `new
ArrayList<Type>(Arrays.asList(new Type[] { one, two, three }))`. The returned list is a
dynamically-sized array (extending ArrayList), unlike `Arrays.asList`, which returns a fixed-size
array.

```java
   List<String> names = List.of("bart", "lisa", "maggie");
   List<Integer> ages = List.of(10, 8, 1);
   List<Double> numbers = List.of(3.14, 2.818, 1.414);
   numbers.add(3.17);
```

## Enhanced collections

IJDK contains collection classes that extend those in the JDK.

These collections are being considered for being branched into a separate project from IJDK.

### List<T>

An extension of ArrayList, with Ruby-like methods.

```java
   List<Integer> nums = List.of(1, 3, 5, 7);
   nums.get(0);    // == 1
   nums.get(-1);   // == 7
   nums.append(9).append(11).append(13);
   nums.get(-3);   // == 9
   nums.first();   // == 1
   nums.last();    // == 13
   nums.takeFirst();  // == 1
   nums.takeFirst();  // == 3
   nums.takeLast();   // == 13
```

### Common Collections

Classes for common Java collections of generics, such as:

```java
   // instead of List<String>; varargs constructor
   StringList sl = new StringList("apple", "banana", "cherry");
   boolean result = sl.anyStartsWith("ba");  // true
   boolean result = sl.anyStartsWith("do");  // false
   
   // instead of List<Integer>; varargs constructor
   IntegerList il = new IntegerList(3, 6, 9);
   int max = il.maximum();                 // max == 9
   int avg = il.average();                 // avg == 6
   int min = il.minimum();                 // min == 3
```

### Pair and KeyValue

```java
    Pair<String, Integer> score = Pair.create("Bird", 33);
    // score.first() == "Bird", score.second() == 33
```

Similarly, the nearly identical KeyValue:

```java
    KeyValue<String, Double> kv = KeyValue.of("one", 1.23);
    // kv.key() == "one", kv.value() == 1.23
```

### Range

This converts easily to C-style arrays, and supports simple iteration:

```java
    Range r = new Range(3, 11);
    if (r.includes(4)) { // true
    }
    if (r.includes(2)) { // false
    }
    for (Integer i : r) {
        // iterate from 3 through 11
    }
    Integer[] ary = r.toExpandedArray();  // ary == [ 3, 4, 5, 6, 7, 8, 9, 10, 11 ]
```

### MultiMap

Does one-to-many mappings.

```java
    MultiMap<String, String> firstToLastNames = new MultiMap<String, String>();
    firstToLastNames.put("James", "Gosling");
    firstToLastNames.put("James", "Rumbaugh");
    firstToLastNames.put("James", "Foley");
    for (String lastName : firstToLastNames.get("James")) {
    }
```

* negative indices handled for Array.get(n), so that get(-1) returns the last element in the list,
  get(-2) the second to last, etc., similar to Ruby:

```ruby
    # Ruby
    names = %w{ Bart Lisa Homer Marge }
    m = names[-1]
    h = names[-2]
```

```java
    // Java
    List<String> names = Arrays.asList("Bart", "Lisa", "Homer", "Marge");
    String m = ListExt.get(names, -1);   // m == "Marge"
    String h = ListExt.get(names, -2);   // h == "Homer"
```

* bounds checking for List.get(n), returning null when out of range instead of
  throwing an exception:

```java
    String x = ListExt.get(names, 9);   // x == null
```

* as above, for characters in Strings

* "safe" iterators for arrays and collections, which handles the case when they are null

```java
    String[] ary = new String[0];
    for (String str : ICore.iter(ary)) { // executes zero times
    }
    String[] ary = null;
    for (String str : ICore.iter(ary)) {  // also executes zero times
    }
```

* iterators to execute a given number of times, similar to Ruby:

```ruby
    3.times { puts "hello" }
```

```java
    for (Integer : ICore.iter(3)) {
        ICore.puts("hi");
    }
```

* colorized strings (on ANSI terminals)

* an alternative "logging" (debugging output) module

* alternate classes that shadow JDK ones, such as:

```java
    Integer num = Int.toInteger("1");   // num == 1
    Integer num = Int.toInteger("");    // num == null
    Integer num = Int.toInteger("xyz"); // num == null
    Integer num = Int.toInteger(null);  // num == null
```

* a *Pathname* class, which extends java.io.File with Ruby-like functionality

```java
    Pathname pn = new Pathname("abc/def.txt");
    pn.baseName();     // == "def.txt"
    pn.relativePath(); // == "abc/def.txt"
    pn.extension();    // == "txt"
```

* briefer package hierarchy

A top-level package, `ijdk`, contains subpackages, such as `ijdk.lang`, making for briefer import
statements. This breaks with normal Java conventions, given that IJDK is more for brevity and
clarity than convention. All IJDK commonly used classes are in `ijdk.lang`, making for more concise
imports:

```java
    import ijdk.lang.*;
```

The older version of IJDK, with classes containing static methods around Java core classes:

```java
    import org.incava.ijdk.lang.StringExt;
```

The newer (3.0) version of IJDK, with classes whose instances wrap (shadow) Java core classes:

```java
    import ijdk.lang.Str;
```

Note that the names of the shadow classes follow the convention of the class that they wrap, with
shorter names, such as Str (String), Int (Integer), Obj (Object), etc.

# Installation

From the GitHub project [ijdk](http://github.com/jpace/ijdk "IJDK"), download and build the project
with Gradle.

IJDK is not yet available via a Maven repository. (Volunteers for such effort are welcome and very
appreciated.)

# Help

Please email me at jeugenepace at gmail dot com if you have questions about IJDK.

# Contributing

I'm eager to learn how others use this library, and what additional features they would like. Please
email me at the above address.

# Credits

Much of this was inspired by Ruby.
