# Java Algorithm Analysis

## Content

`SelectionSort.java` - implements selection sort as Java program

`InsertionSort.java` - implements selection sort as Java program






## Content 

* `disjoint_sets.py` demonstrates solving a three-way set disjointness problem in O(n log n) time,
improving on the O(n^2) solution.

## Notes

* To do integer division in Python 3 (or in Python 2 after doing `from __future__ import division`), use double slashes: `(9+2)//2`
* To get random integers, do `import numpy as np; np.random.randomint(low,hi,size)`
* To get random doubles, do `import numpy as np; np.random.random(100,)`
* Reminder of print formatting codes: either `print("Iteration {0}",i)`, or use colon to format: `print("Time is {0:.4f}", 1.0058*2340)`

## Mistakes

Disjoint set:
* Mistake in implementing binary search: midpoint should be (max+min)//2 and not (max - min)//2
* I spent quite a bit of time fumbling with this three-way set disjointness problem, because I was trying all sorts of complicated nonsense.
	* Built in iterators, plus other floating integer counters,
	* Stack approach, doing one-at-a-time pops, comparing each item, using < or > 
	* After a while I realized I was making it all far too complicated, and that was precisely the problem.
* Organize your code from the beginning
	* Defining a binary search function? You're gonna use it again. Put it in `binary_search.py`.
	* Defining a way to check if three sets are disjoint? Don't just cobble together some sorry script.
	* Write your code in a file with a function. Test it by calling it directly, but also allow it to be imported and used by others. 
	* Start with `def my_method(A,B,C):`
	* Then do `if __name__=="__main__":` to make this testable
	* Put your test after the above if statement
	* Have a template that you're used to using - have a system.
* Don't use if/else blocks like a dork - don't indent into an if/else when you're just doing one little check.
* Same goes for try/except blocks. Python is a scripting language.

Largest 10:
* Beware the minor details of the language. Even the easiest problems can get hung up there.
	* How to generate random floating point numbers? Uniform vs. Gaussian? Integers between a and b?
	* How to format strings?
	* continue? pass? break?

Prove that if a function looping through n items, with O(n), calls a function each time, with with order O(i^2),
the total runtime will be O(n^3).
* Need to use sequence math, discrete math
* Trick with splitting i^2 into k^2 (using k^3???)

Experimental analysis of sorted():
* Keep it *SIMPLE*, start simple and work your way toward more complex.
* Print formatting codes are important
* 






