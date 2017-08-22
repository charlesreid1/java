# Adjacency Map Graph

## The Long And Short Of It

We wasted a lot of time on a pretty stupid issue.

The stupid issue was the scope of the IVertex and IEdge interfaces,
the (public but defined inside the AdjacencyMapGraph class) 
Vertex and Edge classes, and how to switch between them
while still managing to keep all of said classes generic.

Java was, as usual, throwing up all manner of compiler issues 
when I tried to implement a generic class + interface with inheritance.

## The Solution

The solution is to program to the interface, use the interface, 
don't switch to the specific class until you absolutely have to.

But to do that, we need a validate method that safely casts
an interface `IVertex<V>` type to a class `Vertex<V>` type:

```
  	@SuppressWarnings({"unchecked"})
  	private Vertex<V> validate(IVertex<V> v) {
  	  	if (!(v instanceof Vertex)) {
			throw new IllegalArgumentException("Invalid vertex");
		}

		// Safe cast:
  	  	Vertex<V> vert = (Vertex<V>) v;

		return vert;
	}

  	@SuppressWarnings({"unchecked"})
  	private Edge<E> validate(IEdge<E> e) {
  	  	if (!(e instanceof Edge)) {
			throw new IllegalArgumentException("Invalid edge");
		}

		// Safe cast:
  	  	Edge<E> edge = (Edge<E>) e;

		return edge;
	}
```

## Files

See `AdjMapGraph2.java` for the working, functional solution.
The `GraphTest2.java` class tests this graph out.

The `AdjMapGraph.java` class works, but has some issues.
See `GraphTest.java` for an illustration.


## Notes while working through issues

We are running into yet another problem with 
class organization and layout.

Putting Vertex and Edge class in separate places
means the Vertex class cannot refer to an Edge instance,
because it doesn't have a reference to E.

Goodrich says Vertex and Edge classes "should" be 
defined inside the AdjMapGraph class.
It is clear now that that is because that's the 
only way both the Edge and Vertex class will understand
both V and E generic types.

However, there are problems with THAT approach,
specifically, it protects these classes by default,
so that when we try to add vertices, 
it returns a Vertex object, so we need to 
have a way to get and assign a Vertex object
(which we can't if we have a separate class
with a driver to test out the Adjacency Map Graph).

On the other hand, we could use the interfaces
IVertex and IEdge, but then we have problems when we 
try and create an edge, and pass in two IVertex 
objects as the endpoints of the edge. The IVertex
objects can't be automatically converted to 
Vertex objects. They have to be cast as Vertex
objects. Thus, we can either keep things simple 
and validate every Vertex object, 
or we can overcomplicate things by accepting
either a Vertex or an IVertex object. (Seems stupid.)

On the third hand, if we just try and power through and make
the Vertex and Edge class public, we have to refer to it
by saying AdjMapGraph.Vertex.

Except, it actually gets worse. Because these both take 
generic types, we actually have to refer to the Vertex class
as AdjMapGraph<String,String>.Vertex<String>.

None of these issues are present in the abstraction,
and the abstraction makes perfect sense.
It isn't until you actually roll up your sleves 
and try to DO SOMETHING with Java that you start to
run into these idiotic speed bumps.

Sigh.
