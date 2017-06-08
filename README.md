# Overview

IJDK (Incava Java Development Kit) is a library of general-purpose Java code, much of it inspired by
and modeled on the equivalent in Ruby. For example, reading a file:

```java
    List<String> lines = IO.readLines("foo.txt");
```

Note the similarity to idiomatic Ruby:

```ruby
    lines = IO.readlines("foo.txt")
```

# Usage

IJDK has a very expansive (and growing) library for Java I/O and collections, as an alternative to
the JDK. IJDK is mostly inspired by Ruby, and parallels Ruby classes (such as Map/Hash and
List/Array) closely, usually moreso than it does the Apache Commons and Guava libraries.

As IJDK gets more behavior, modules -- such as I/O -- may be split into subprojects.

## Enhanced collections

IJDK contains collection classes that extend the List (ArrayList) and Map (TreeMap) classes from the
JDK.

### Array<T>

An extension of ArrayList, with Ruby-like methods (thus the name matching Array in Ruby).

```java
    Integer x = null;
    Array<Integer> nums = Array.of(1, 3, 5, 7);
    
    x = nums.get(0);
    assertEqual(1, x);
    
    x = nums.get(-1);
    assertEqual(7, x);
    
    nums.append(9).append(11).append(13);
    assertEqual(Array.of(1, 3, 5, 7, 9, 11, 13), nums);

    x = nums.get(-3);
    assertEqual(9, x);
    
    x = nums.first();
    assertEqual(1, x);

    x = nums.last();
    assertEqual(13, x);
    
    x = nums.takeFirst();
    assertEqual(1, x);
    assertEqual(Array.of(3, 5, 7, 9, 11, 13), nums);
    
    x = nums.takeFirst();
    assertEqual(3, x);
    assertEqual(Array.of(5, 7, 9, 11, 13), nums);

    x = nums.takeLast();
    assertEqual(13, x);
    assertEqual(Array.of(5, 7, 9, 11), nums);

    StringArray strArray = nums.toStringArray();
    assertEqual(StringArray.of("5", "7", "9", "11"), strArray);

    nums.append(2).append(2).append(2);
    assertEqual(Array.of(5, 7, 9, 11, 2, 2, 2), nums);

    Array<Integer> uniq = nums.unique();
    assertEqual(Array.of(5, 7, 9, 11, 2), uniq);

    assertEqual(true, nums.containsAny(2, 3));
    assertEqual(false, nums.containsAny(3, 4));

    nums.removeAll(2);
    assertEqual(Array.of(5, 7, 9, 11), nums);

    nums.set(0, 4);
    assertEqual(Array.of(4, 7, 9, 11), nums);

    nums.set(-1, 10);
    assertEqual(Array.of(4, 7, 9, 10), nums);

    nums.set(-2, 8);
    assertEqual(Array.of(4, 7, 8, 10), nums);

    nums.set(1, 6);
    assertEqual(Array.of(4, 6, 8, 10), nums);

    x = nums.getRandomElement();
    assertEqual(true, Array.of(4, 6, 8, 10).contains(x));

    String str = nums.join(" + ");
    assertEqual("4 + 6 + 8 + 10", str);

    Array<Integer> odds = Array.of(1, 3, 5);
    Array<Integer> evens = Array.of(2, 4, 6);
    Array<Integer> numbers = odds.plus(evens);
    assertEqual(Array.of(1, 3, 5, 2, 4, 6), numbers);
    
    Array<Integer> squares = numbers.minus(Array.of(2, 3, 5, 6));
    assertEqual(Array.of(1, 4), squares);

    Array<Integer> elements = numbers.elements(1, 0, -2, 0, -4);
    assertEqual(Array.of(3, 1, 4, 1, 5), elements);
```

### Common Collections

Classes for common Java collections of generics, such as:

```java
   // instead of Array<String>; varargs constructor
   StringArray sl = new StringArray("apple", "banana", "cherry");
   boolean result = sl.anyStartsWith("ba");  // true
   boolean result = sl.anyStartsWith("do");  // false
   
   // instead of Array<Integer>; varargs constructor
   IntegerArray il = new IntegerArray(3, 6, 9);
   int max = il.maximum();                 // max == 9
   int avg = il.average();                 // avg == 6
   int min = il.minimum();                 // min == 3
```

## Pair and KeyValue

```java
    Pair<String, Integer> score = Pair.create("Bird", 33);
    // score.first() == "Bird", score.second() == 33
```

Similarly, the nearly identical KeyValue:

```java
    KeyValue<String, Double> kv = KeyValue.of("one", 1.23);
    // kv.key() == "one", kv.value() == 1.23
```

## Range

A Range is a pair of integers. It converts to arrays, and supports iteration.

```java
    Range r = new Range(3, 7);
    r.includes(4); // true
    r.includes(2); // false

    // inclusive of both first and last values
    for (Integer i : r) {
        // iterate from 3 *through* 7
    }

    Array<Integer> list = r.toArray(); // list == [ 3, 4, 5, 6, 7 ]

    // exclusive of last value
    for (Integer i : r.upTo()) {
        // iterate from 3 *through* 6
    }

```

## Iterators

"Safe" iterators for arrays and collections, which handles the case when they are null.

```java
    String[] ary = new String[0];
    for (String str : ICore.iter(ary)) { // executes zero times
    }
    String[] ary = null;
    for (String str : ICore.iter(ary)) {  // also executes zero times
    }
```

Execute a given number of times, similar to Ruby:

```ruby
    3.times { puts "hello" }
```

```java
    for (Integer i : ICore.iter(3)) {
        ICore.puts("hi");
    }
```

## MultiMap

Does one-to-many mappings.

```java
    MultiMap<String, String> firstToLastNames = new MultiMap<String, String>();
    firstToLastNames.put("James", "Gosling");
    firstToLastNames.put("James", "Rumbaugh");
    firstToLastNames.put("James", "Foley");
    for (String lastName : firstToLastNames.get("James")) {
    }
```

## Int

An lternate class to Integer:

```java
    Integer num = Int.toInteger("1");   // num == 1
    Integer num = Int.toInteger("");    // num == null
    Integer num = Int.toInteger("xyz"); // num == null
    Integer num = Int.toInteger(null);  // num == null
```

## Pathname

Pathname extends java.io.File with Ruby-like functionality:

```java
    Pathname pn = new Pathname("abc/def.txt");
    pn.baseName();     // == "def.txt"
    pn.relativePath(); // == "abc/def.txt"
    pn.extension();    // == "txt"
```

## Comp

ijdk.lang.Comp extends and replaces Comparable (`compareTo`) usage, normalizing the result to be
simply -1, 0, or 1. Comp also contains `lt` (less than), `lte` (less than or equal), `gt` (greater
than), and `gte` (greater than or equal), for simpler comparisons:

```java
   if (Comp.gte("abc", "bbc")) {
   }
   if (Comp.lt("def", "fed")) {
   }
```

## Assertions

`org.incava.test.Assertions` (this may be repackaged to `org.incava.ijdk.test`) contains
alternatives to JUnit4 org.junit.Assert. Consistent with the test/unit and minitest libraries in
Ruby, the primary assertion method is of the form:

```java
   public static <T> T assertEqual(T expected, T actual, String msg);
```

The message is the last parameter, in comparision to it being the first parameter in JUnit4
`Assert.assertEquals`. That's because the message is considered of lesser importance than the
expected value and the result. In a later version, this method will have varargs arguments as the
message components (but see below for an alternative).

In comparision to `Assert.assertEquals`, which has a void return type, `assertEqual` returns the
`actual` value. That supports a more concise and elegant style of instantiating objects "inside" the
call to `assertEqual`.

```java
   Type expected = new Type(expArgs);
   Type value = assertEqual(expected, new Type(args));
   // do stuff with value
```

Among the variations of `assertEqual`, one supports assertions of C-style arrays (Object[]),
including with better output on failure.

The `message` method converts key/value pairs to more descriptive output on failure of an assertion,
providing more context than just the values being compared:

```java
    import static org.incava.test.Assertions.message;
    // ...
    
    @Test
    @Parameters
    @TestCaseName("{method} {index} {params}")
    public <T> void equalsTest(boolean expected, NullableObject<T> nobj, Object obj) {
        boolean result = nobj.equals(obj);
        assertEqual(expected, result, message("nobj", nobj, "obj", obj));
    }
```

## Miscellaneous

* colorized strings (on ANSI terminals)

* an alternative "logging" (debugging output) module

* logical package structure

The primary classes are in org.incava.ijdk.lang (roughly equivalent of java.lang) and
org.incava.ijdk.collect. 

The names of the shadow classes follow the convention of the class that they wrap, with shorter
names, such as Str (String), Int (Integer), Obj (Object), etc.

# Installation

From the GitHub project [ijdk](http://github.com/jpace/ijdk "IJDK"), download and build the project
with Gradle.

IJDK is available in the Maven repository.

    repositories {
        mavenCentral()
    }

    dependencies {
        compile group: 'org.incava', name: 'ijdk', version: '3.3.2'
    }

# Help

Please email me at jeugenepace at gmail dot com if you have questions about IJDK.

# Contributing

I'm eager to learn how others use this library, and what additional features they would like. Please
email me at the above address.

# Credits

Much of this was inspired by Ruby.
