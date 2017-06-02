# Lists

## Abstract List Type

The list abstract data type (ADT) implements the following methods:
* size
* isEmpty
* first
* last
* addFirst
* addLast
* removeFirst

Other convenience methods:
* remove
* removeFirst
* removeLast
* removeElement

## Mistakes Learnings Tricks

Use the `Integer` class when processing integers!

When you have a String and you want an integer:
* Integer.parse("525")

When you have an integer and you want a String:
* Integer.toString(str)

In general, you can also use the String class to turn the object into a String:
* `String.valueOf(o)`

Linked List specific details:
* Need to handle cases where you are adding to an empty list, or removing last item from list
* Especially when using tail pointer, be aware of what it is pointing to and when it needs to be updated.

Linked list methods should automatically become a list of bookkeeping things to do.
* Add method: consider adding to an empty list, non-empty list
* Add at index i:
	* What are all possible cases?
	* i is too big, i is too small
	* the list is empty 
	* i is a special case
	* how can we fold any of these cases into other cases (list empty -> i too small)


Size of linked list:;
* Number of bytes: 
	* See [https://charlesreid1.com/wiki/Java/Memory](https://charlesreid1.com/wiki/Java/Memory)
	* 8 bytes per reference to memory (64 bit)
	* 16 bytes of object overhead
* Size estimation:
	* 1000-integer linked list should theoretically use up:
	* 1000 nodes x ( 4 bytes per integer + 8 bytes per ref + 16 bytes object overhead )
	* 28,0000 bytes ~ 28 kB
	* What's the power of 2 that would correspond to that? 
	* 1000 integers ~ 1024 integers ~ 2^10 integers
	* Round it up to 8 bytes per integer, 8 bytes per ref, 16 bytes per object overhead)
	* 2^5 = 32 bytes per integer
	* 32 x 8 = 2^5 * 2^3 = 2^8 = 256 bits = 256 zeroes and ones
	* 10 GB / 10 kB/obj = 10x10^6 / 10 = 10^6


Exceptions:

```
class Empty extends Exception{};
```

Circular linked list:
* Take care with the details of pointers.

Doubly linked list:
* It makes algorithms easier, but 


Nice link explaining sizes of Strings in memory:
* https://stackoverflow.com/a/18030595

```
sizeof(string) =
8 + // object header used by the VM
8 + // 64-bit reference to char array (value)
8 + string.length() * 2 + // character array itself (object header + 16-bit chars)
4 + // offset integer
4 + // count integer
4 + // cached hash code
```



