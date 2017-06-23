## priority queue interface - PriorityQueue.java

This class keeps it simple:
* implements an interface
* provides a method for adding and removing items
* throws empty exceptions
* provides a way to iterate over it
* provides size and isEmpty methods to check on state of PQ

## priority queue ADT - AbstractPriorityQueue.java

This provides a class implementation of the priority queue interface.
This class is used to do anything that cannot be done in an interface, e.g., 
implementing protected helper classes.
* Define the Item helper class being used to store key-value pairs in the priority queue
* Define any additional abstract methods needed

## unsorted priority queue

Add method here is trivial. Remove minimum and peek minimum methods are not, require a min search.
* Note that using a Linked List requires a few changes from the positional list example in Goodrich.
* In Goodrich the use of Positions allows them to implement a find minimum method that returns the minimum position.
* From there, the positional list allows you to *both* obtain the value in the list at that location *and* remove it using the Postiion object.
* That is not the case with Linked Lists, which implements its list position class under the hood.

Two options:
* Use iterator
* Use integer index

Iterator approach:
* Use a list iterator to walk through the list, searching for a minimum.
* Return the list iterator from the find minimum method,
* Use the list iterator object to peek or remove.
* Pros: only traverse list once
	* Go through list once to find the min, 
	* Second time through, you have iterator object pointing directly at node

Integer approach:
* Use a cumulative variable to find the minimum index
* Just return the minimum index
* Pros: This uses the *simlest* built-in methods available for the list. Since we are using built-in linked list, use built-in linked list remove method.
* Cons: This costs an extra traversal through the list 
	* Go through the list once to find the min
	* Then internal remove method goes through again to find the element
	* Not that bad, an O(N) operation becomes an O(N) operation

## sorted priority queue

Basic idea is same as unsorted priority queue, except for insert.
* Remove method here is trivial, since you are just removing the first item in the list.
* Insert method was slightly different.
* Start by getting a descending iterator, and popping one item out.
* Use the iterator to walk through all items: while walker not null and while new node less than walker
* The while loop tries to advance the walker, increments an index
* Once we get out of the loop, check which condition caused the stop
* If we got to the front of the list, add to the front of the list
* Otherwise, add wherever our counter stopped



## use as stack

Extending the priority queue data type to implement the stack ADT.
Just using the PQ and one additional integer instance variable.
* Note: extends goes before implements
* This illustrates how you can chain together abstract classes and interfaces
* Really helps explain the logic behind the Java Collections objects
















