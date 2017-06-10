# Trees

Tree structures provide an excellent opportunity 
to stop and think through some methods for generalizing
these objects to apply more broadly.

We can also learn some tricks and apply some 
programming patterns along the way.

## Inheritance Organization

Inheritance organization is as follows:
* `Tree.java` - top level abstract class.
* `BinTree.java` - binary tree, extends Tree, additional abstract methods.
* `LinkedBinTree.java` - concrete binary tree, implements linked tree structure.


## Interfaces

### Tree

Tree abstract data type, implemented as a method, provides several methods:
* element
* root
* is root
* parent
* number of hildren
* children
* is leaf
* length
* is empty
* positions
* iterable

### BinaryTree

Provides a few additional methods specific to the binary structure:
* left 
* right
* sibling

Children can now be implemented - return them chilluns.

## Concrete Implementations

### Linked Binary Tree

LinkedBinTree is now capable of implementing 
utility methods to modify the tree structure.
* add root(e)
* add left(p,e)
* add right(p,e)
* replace(p,e)
* delete(p) 
* attach(p,t1,t2)



some of  these methods (all of them?) are private. 

## Mutable Linked Binary Tree

Exposes methods to modify the tree using public methods.
