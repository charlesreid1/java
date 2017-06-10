
## Garbage collector overhead limit

When running string permutation with string of length 11,
we run into the following problem with the garbage collector:

```
Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
	at StringPermutations.stringPermutations(StringPermutations.java:102)
	at StringPermutations.testScaling(StringPermutations.java:17)
	at StringPermutations.main(StringPermutations.java:9)
make: *** [time] Error 1
```