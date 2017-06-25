# Hashtable Maps

Walking through the process of creating a Map ADT.

Start by defining an interface for the Map ADT.

```Map.java```

Next, define an abstract class to lay out some useful 
methods, fields, and utility classes.

```AbstractMap.java```

## Abstract map

The process of defining the abstract map:
* Needed to define a map item wrapper class, of course
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

entrySet:
* Note that the key and value sets use the entrySet method
* We aren't defining entrySet, just using it
* entrySet must be defined in our concrete class
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

