# Arrays

Dates: 05/29/2017

See [http://charlesreid1.com/wiki/Arrays](http://charlesreid1.com/wiki/Arrays)

## Content

`array-of-objects` - simple example "high score" object. Illustrates basic features
	of arrays, particularly arrays of objects.

`arrays-class` - demonstrate use of Arrays class built-in methods (sort and binarySearch).

`copies` - for making copies of arrays, use `.clone()`

`fisher-yates` - implementing a Fisher-Yates shuffle method.

`python-list` - implementing an array-based list object that works like the Python list.

`random-stuff` - random class and methods

`string` - Strings are arrays too.

`threesum` - illustrating some basic nesting behavior for algorithmic analysis.

`tic-tac-toe` - exemplifying good design practices. Start with data container,
	provide necessary accessor methods. class constants to keep sides straight.


## To Figure Out

Code templates ready to go:

Code templates needing developing:
* Comparator
* Iterable
* Lambdas
* Exceptions - what to raise and when
* Bracket accessor notation for an object
* Generic types/templating
* Testing and @Test
* Generic typtes, more general types, less code, less waste


## Mistakes/Learnings

### GameEntry

GameEntry class:
* Pay closer attention to accessor methods - public private attributes, get set methods
* Be aware of what is exposed, even in whiteboard questions.
* Need to nail down the little things so you can focus on the big things. 
* Always include a toString to make your tests easier.

Javadocs:
* Just do it. Every single method. Every single time.

```
/** Always write a simple javadocs description. */
public static void doStuff(int a, int b) {
}
```

Accessing and structures:
* Counters. indexing math.
* Logic and details details details.

Comparators/comparable:
* Knock out a comparable interface without even thinking.

Testing, error-handling:
* Test for exceptions
* Handle exceptions and know what exceptions will be thrown, how to raise your own, and etc.
* Don't say, who knows what'll happen if we pass something without 5 duplicates to the find 5 duplicates method.
	* Write at test
	* What if n < 6? What if no dupes? What if not integers? What if... unsortable? What if custom objects?
	* Scoreboard: print scoreboard by alphabetical order, print scoreboard by score value, depending. 
	* Using lambda expressions.

### FindIt

* Need for test inputs:
	* can either hard-code test inputs, 
	* Can generate them on the fly
	* if you have a static class full of static methods you just add a static method.
	* maybe even break it out into a separate class, but just keep it simple.

* Better test inputs:
	* Read from file with file scanner. 
	* Competitive coding level of operation - well-oiled machine.
	* Starting template that you knock out quickly allows fluency to complex problem specifications/inputs.

* Timing
	* Comparative timing, output to a file 
	* `java MyProgram < input.txt > output.txt`

### Fisher Yates

Even the simplest problem can get complicated fast.

Fisher Yates:
* Type generics, all calsses, etc etc.
* Kept it general, Object[] and swap(Object[], int x, int y)

Writing a deck of cards class
* Classy but messy - 2 3 4 ... 
* Simpler cards? Uno cards.
* Array/integer range? 
* Lambda/generator expression?

Whew.
* Type generics? Documentation? 
* References for the complicated stuff?
* Testing?
* Decorators?
* Do you know where to go? Have references.


### Tic Tac Toe

It gets easier - each time you go through another example,
there are fewer questions, the questions are more targeted, 
the idea becomes clearer. There is a little bit of difficulty in 
getting to the answer before the book, if the code is already finished,
but that's what you'll have to do.

Extending behavior is really useful, as an exercise.





