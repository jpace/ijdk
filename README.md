# Overview

IJDK (Incava Java Development Kit) is a library of general-purposed code, much of it inspired by
Groovy (thus Ruby). For example, reading a file is this simple:

```java
    List<String> lines = IO.readLines("foo.txt");
```

Note the similarity to Ruby:

```java
    lines = IO.readlines("foo.txt")
```

# Usage

IJDK has a very expansive (but growing) IO library, as an alternative to the JDK
code.

Other notable components: 

### a Pair class

```java
    Pair<String, Integer> score = Pair.create("Bird", 33);
```

### a Range class

```
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

```
    MultiMap<String, String> firstToLastNames = new MultiMap<String, String>();
    firstToLastNames.put("James", "Gosling");
    firstToLastNames.put("James", "Rumbaugh");
    firstToLastNames.put("James", "Foley");
    for (String lastName : firstToLastNames.get("James")) {
    }
```

* negative indices handled for List.get(n), so that get(-1) returns the last
  element in the list

    List<String> names = Arrays.asList("Bart", "Lisa", "Homer", "Marge");
    String m = ListExt.get(names, -1);
    // m == "Marge"
    String h = ListExt.get(names, -2);
    // h == "Homer"

* bounds checking for List.get(n), returning null when out of range instead of
  throwing an exception:

    String x = ListExt.get(names, 9);
    // x == null

* as above, for characters in Strings

* "safe" iterators for arrays and collections, which might be null

* and() and or() methods returning the last/first element that is not null/false

* colorized strings (on ANSI terminals)

* an alternative "logging" (debugging output) module

# Installation

The GitHub project [ijdk](http://github.com/jpace/ijdk "IJDK") has the core
libraries. This project, ijdkproj, includes the build files and the unit tests.

# Help

Please email me at jeugenepace at gmail dot com if you have questions about
IJDK.

# Contributing

I'm eager to learn how others use this library, and what additional features
they would like. Please email me at the above address.

# Credits

Much of this was inspired by Ruby, as well as Groovy and Gradle.
