# Hashtable Maps

Notes on where I got stuck, things that surprised me, 
things I learned, etc.

Table of Contents:

Maps:
* Map ADT Interface
* Abstract map
* Unsorted array map
* Sorted array map 

Hash functions:
* Hash functions
* Object hash codes

Hash maps:
* Hash map base
* Chained hash map
* Probe hash map



## Map ADT Interface

Walking through the process of creating a Map ADT.

Start by defining an interface for the Map ADT.

```
Map.java
```

Next, define an abstract class to lay out some useful 
methods, fields, and utility classes.

```
AbstractMap.java
```

## Abstract map

The process of defining the abstract map:
* Needed to define a map item wrapper class
* This is designed to take a K,V key value pair
* Also need to define four more classes:
* Two Iterators and two Iterables

Iterators vs Iterables - ?
* Map key Iterator object
* Map value Iterator object
* Map key Iterable object (?)  - thought I understood this, but maybe not?
* Map value Iterable object (?) 
* The Iterator objects are like mini-scanners.
* The Iterable objects just have an iterator() method and are what are actually returned.

itemSet:
* Note that the key and value sets use the itemSet method
* We aren't defining itemSet, just using it
* itemSet must be defined in our concrete class
* This means we have three total Iterators and three total Iterables

More iterators vs iterables:
* Iterable just says, I return an Iterator. it is part of the core language.
* Iterator has to be defined for each container.

Exceptions:
* NoSuchElementException
* UnsupportedOperationException
* Don't forget to check for size! 
* Especially if you are not wraapping someone else's iterator

Ordering of class declaration and undeclared class declarations
* I could not use MapItem class undefined in the Map interface (most generic of all)
* MapItem class was actually protected and lived in AbstractMap class
* Therefore, we had to hold off declaring an abstract MapItem iterator until the AbstractMap class, not the Map interface


## Unsorted array map

Organization of classes:
* Main method to illustrate usage and tests
* Private or protected classes that are important and used internally by the class
* Finally, the contents of the class itself.


## Sorted array map

Sorted array map challenges:
* Had some difficulties with implementing insert and figuring out where to insert next item
* Issue was, user passes us a Key type object, and we have a `MapItem<Key,Value>` item
* Resolution was, we implement a comparator to compare MapItem objects... the only thing it does is look at keys (no values).
* Then, when the user gives us a key, we create a dummy `MapItem<K,V>` object
* This allows us to compare Map Item objects, but no need to create a value.

## Hash Functions

First, the bitwise operators:
* I get the >> is to add a 0 to the binary representation of the number
* I do not understand the <<< or | notation

Scanners, readers, files, and buffers:
* Always make a new Scanner 
* Always pass the Scanner a buffered something
* If reading from input, pass it a BufferedReader with an InputStreamReader
* If reading from a file, pass it a BufferedReader with a FileReader
* BufferedReader always needs a Reader


## Object hash code

Developoing a consistent approach/tactic/organization to the code.
* Start with your public static void main.
* Move on to utility classes.
* Finish with the bulk of the class, its specifics, etc.
* Starting with the test - usage - test-driven.

Overriding the default hashCode() function of a Word object. Important observations:
* The hash method for two objects considered equal must be the same.
* Built-in String class implements the equals method so that two Strings equal if precisely same sequence of chars.
* String class also implements custom hashCode() method.
* Examine the hashCode() of String class - great example to learn from.
* Java's hash code implementation: 230,000 words, 12 collisions.

New hash code calculation:
* We define the equals method for the class so that X
* We compute a robust hash code for a list by taking exclusive or of element hash codes, while performing cyclic shift.
* Perform bitwise xor, then a 5 bit cyclic shift.

Overriding equals() and hashCode() methods:
* See [link](https://stackoverflow.com/questions/15722485/hashset-storing-equal-objects#15722565)
* Need to use `@Override`, take `method(Object o)` as parameter, try to convert to correct type
* This verifies, to the compiler, that you are accurately handling all cases the equals() method might see

When hashCode versus compareTo is used:
* If you are using a tree set or a tree map, you are using natural ordering, therefore you are using compareTo()
* If you are using a hash set or a hash map, you are basing things on the hash code, therefore you are using hashCode()


## Abstract hash map 

NOTE: Not a "parent" or "base" class - these terms are too general. 

This is an "abstract" class, meaning it is not meant to actually be implemented.

While chaining and linear probing are quite different implementations of hash maps,
they still share some commonalities, so we implement an abstract hash map class.
This class should contain any implementation details common to all types of hash maps,
distinct from more general map types.

Fields:
* number of entires in table
* length of table
* prime factor
* hash function parameters (shift and scale)

Public methods:
* classic map/dictionary adt:
	* get from map
	* put into map
	* remove from map

Protected methods:
* get from bucket (protected)
* put into bucket (protected)
* remove from bucket (protected)

Private methods:
* hashValue - implemented concretely in this class. Intended to be used without modification by all other classes.
* create table - purely abstract. Not implemented.
* resize table - purely abstract. Not implemented.

```

This is taking a bit of effort to understand...

```

Three constructors:
* one default (picks default capacity, default prime)
* one taking capcity (default prime)
* one taking capacity and prime 

Note that the prime number we specify in the constructor 
is used as part of the hash function.

Public methods:
* we provide public get, put, and remove methods
* we actually know enough, at this point, to know that our hashmap will be impemented using buckets.
* that means we can implement the get, put, and remove methods to call the corresponding bucket methods.

Then the pseudocode will look something like this:

```

public_get(key):
	return private_bucket_get( hash(key), key )

```

Last but not least, the put and remove methods should also check if we need to resize.
The Goodrich book implements these methods in the abstract class.

Protected/private methods:
* the hash function itself - multiply/divide mod two different bases
* bucket versions of get/put/remove
* create table with given capacity

Note: when we say "bucket", what we really mean is, "one particular hash code".

The approach of using buckets is, if two or three items have the same hash code,
we don't have to give up - we just say, add it to (this particular) hash code bucket.

## Chained Hash Map

Slightly confusing - it is not as clear/concrete as I anticipated.

This class creates buckets that are unsorted array maps. 
In several of the books/videos we covered on the subject,
chained hash maps use linked lists. However, our Unsorted 
array map uses an ArrayList, which under the hood is 
a dynamically resized array.

We could change this by using a LinkedList instead of
an ArrayList. That would slow down search and random access though. 


Constructors:
* Constructors just call super constructors.

Protected methods:
* Creating a table with the specified internal capacity
* Getting/putting/removing items from particular buckets (1 bucket per hash)
* Note: no resize method needed for this, for now.

Public methods:
* Getting/putting/removing items



## Timing Built-In Map

Note: had to rename the map interface to MyMap.
That way, can compare two map types directly.

Timing of built-in map type shows tree map is really fast,
even though it is reported as log N in the source code,
in practice it is closer to O(1) for N < 1M.


## Timing Chained Map

This turned out to be, verbatim, the same method used to time the built-in map.

Hooray for interfaces!

But double hooray for mine, which is much simpler.


## Timing Comparison: Built-In Map vs Hand-Rolled Map

This class led me into some difficulties with casting and typing.

Initially, I thought to keep it simple, have two methods for the two types,
not bother with this foo-foo-willy-riff-raff type stuff.

However, it became clear that the code for the two cases (Map or MyMap) 
would be exactly the same, so I tried to wrap everything together 
as much as possible.

It eventually became clear this was a mistake. 

Problems:
* Could not use generic type `<T>`
* This is due to Java's lack of support for [refied generics](http://gafter.blogspot.com/2006/11/reified-generics-for-java.html)
* Using instanceof works, but raises compiler warnings
* Adding `-Xlint:unchecked` shows impossible-to-resolve issues
* See [link](https://stackoverflow.com/a/27154147)

Using a static method that took a generic type `<T>` led to 
casting warnings and compiler errors, so I passed in the map objects
as a generic Object type. This, in turn, still uses generics for the 
map key value types, so it still raises warnings, but these warnings
are possible to ignore. 

We only have two classes to check for. The general logic is to use
the `instanceof` method to check if an object is an instance of 
the `ChainedHashMap` class or the built-in `HashMap` class.

This leads to two redundant blocks of code, that literally do 
exactly the same thing. This is pretty dumb.

How could we clean this up?
* The problem is, these objects are *apparently* similar without actually being related.
* Solution is, force them to be related via an interface, or accept that they are different and pass them to different methods.
* Trying to ride two horses is a bad idea.
* ***Option 1:*** Write a wrapper class that implements a built-in HashMap under the hood, and a MyMap interface externally.
* ***Option 2:*** Write separate test functions to test separate types.  


