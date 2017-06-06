# Find One

Perfect illustration of precisely the sort of mess Java's generic types and classes approach
makes of even the simplest algorithm.

We are just trying to find duplicates in an array - how much simpler can you get?

Implementing this two ways - one is the generic types way, the other is the plain int[] way.

## Problems wtih the generic types way

* Problems begin with just the very act of initializing data. This is your first warning.
* Can we do `Arrays.asList(1,2,3,3)`? Nope.
* Also, we can use generics `<T>`, but not in a static context. 
* Also, we can define static methods that take a generic type `<T>`. But no arrays `T[]`.
* To way to initialize an all-zeros list of Integers.
* No way to convert any primitive types to wrappers in one go.
* No way to convert from PT int[] to Object type Integer[]

