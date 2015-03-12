# Overview

IJDK (Incava Java Development Kit) is a library of general-purposed code, much
of it inspired by Groovy (thus Ruby). For example, reading a file is this
simple:

    List<String> lines = IO.readLines("foo.txt");

Note the similarity to Ruby:

    lines = IO.readlines("foo.txt")

IJDK has a very expansive (but growing) IO library, as an alternative to the JDK
code.

Other notable components: 

* a Pair class
* a Range class
* a MultiMap, for doing one-to-many mappings
* negative indices handled for List.get(n), so that get(-1) returns the last
  element in the list
* ditto for Strings
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
