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
the JDK code. It is mostly inspired by Ruby, and parallels Ruby code as closely as possible.

* shortcuts for list creation:

This is one of the most used pieces of code in IJDK, a shortcut for `new
ArrayList<Type>(Arrays.asList(new Type[] { one, two, three }))`. The returned list is a
dynamically-sized array, unllike `Arrays.asList`, which returns a fixed-size array.

```java
   List<String> names = ICore.list("bart", "lisa", "maggie");
   List<Integer> ages = ICore.list(10, 8, 1);
   List<Double> numbers = ICore.list(3.14, 2.818, 1.414);
```

* classes for common Java collections of generics, such as:

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

* a Pair class

```java
    Pair<String, Integer> score = Pair.create("Bird", 33);
    // score.first() == "Bird", score.second() == 33
```

* a Range class

This converts easily to C-style arrays, and supports simple iteration:

```java
    Range r = new Range(3, 11);
    if (r.includes(4)) {
    }
    for (Integer i : r) {
        // iterate from 3 to 11
    }
    Integer[] ary = r.toExpandedArray();  // ary == [ 3, 4, 5, 6, 7, 8, 9, 10, 11 ]
```

* a MultiMap, for doing one-to-many mappings

```java
    MultiMap<String, String> firstToLastNames = new MultiMap<String, String>();
    firstToLastNames.put("James", "Gosling");
    firstToLastNames.put("James", "Rumbaugh");
    firstToLastNames.put("James", "Foley");
    for (String lastName : firstToLastNames.get("James")) {
    }
```

* negative indices handled for Array.get(n), so that get(-1) returns the last element in the list,
  get(-2) the second to last, etc.

```java
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
    for (String str : ICore.iter(new String[0])) { // executes zero times
    }    
    for (String str : ICore.iter(null)) {  // also executes zero times
    }
```

* iterators to execute a given number of times, similar to Ruby:

```ruby
   3.times { puts "hello" }
```

```java
   for (Integer : ICore.iter(3)) {
       System.out.println("hi");
   }
```

* colorized strings (on ANSI terminals)

* an alternative "logging" (debugging output) module

* alternate classes that shadow JDK ones, such as:

```java
   Integer num = Integerr.toInteger("1");   // num == 1
   Integer num = Integerr.toInteger("");    // num == null
   Integer num = Integerr.toInteger("xyz"); // num == null
   Integer num = Integerr.toInteger(null);  // num == null
```

# Installation

From the GitHub project [ijdk](http://github.com/jpace/ijdk "IJDK"), download and build the project
with Gradle.

IJDK is not yet available via a Maven repository. (Volunteers for such effort are welcome and very
appreciated.)

# Help

Please email me at jeugenepace at gmail dot com if you have questions about
IJDK.

# Contributing

I'm eager to learn how others use this library, and what additional features
they would like. Please email me at the above address.

# Credits

Much of this was inspired by Ruby.
