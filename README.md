# Overview

IJDK (Incava Java Development Kit) is a library of general-purposed code, much of it inspired by
Groovy (thus Ruby). For example, reading a file is this simple:

```java
    List<String> lines = IO.readLines("foo.txt");
```

Note the similarity to Ruby:

```ruby
    lines = IO.readlines("foo.txt")
```

# Usage

IJDK has a very expansive (but growing) library for Java I/O and collections, as an alternative to
the JDK code. It is mostly inspired by Ruby, and parallels Ruby code as closely as possible.

* shortcuts for list creation:

```java
   List<String> names = IUtil.list("bart", "lisa", "maggie");
   List<Integer> ages = IUtil.list(10, 8, 1);
```

* classes for common Java collections of generics, such as:

```java
   // instead of List<String>; varargs constructor
   StringList sl = new StringList("apple", "banana", "cherry");
```

* a Pair class

```java
    Pair<String, Integer> score = Pair.create("Bird", 33);
    // score.getFirst() == "Bird", score.getSecond() == 33
```

* a Range class

```java
    Range r = new Range(3, 11);
    if (r.includes(4)) {
    }
    for (Integer i : r) {
        // iterate from 3 to 11
    }
    Integer[] ary = r.toExpandedArray();
    // ary == [ 3, 4, 5, 6, 7, 8, 9, 10, 11 ]
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

* negative indices handled for List.get(n), so that get(-1) returns the last
  element in the list

```java
    List<String> names = Arrays.asList("Bart", "Lisa", "Homer", "Marge");
    String m = ListExt.get(names, -1);
    // m == "Marge"
    String h = ListExt.get(names, -2);
    // h == "Homer"
```

* bounds checking for List.get(n), returning null when out of range instead of
  throwing an exception:

```java
    String x = ListExt.get(names, 9);
    // x == null
```

* as above, for characters in Strings

* "safe" iterators for arrays and collections, which might be null

```java
    // executes zero times:
    for (String str : IUtil.iter(new String[0])) {
    }
    // executes zero times:
    for (String str : IUtil.iter(null)) {
    }
```

* iterators to execute a given number of times, similar to Ruby:

```ruby
   3.times { puts "hello" }
```

```java
   for (Integer : IUtil.iter(3)) {
       System.out.println("hi");
   }
```

* colorized strings (on ANSI terminals)

* an alternative "logging" (debugging output) module

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
