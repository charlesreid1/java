subset:
	javac SubsetSum.java && java SubsetSum < 8ints.txt

small:
	cat /dev/null > out
	javac TripleSum.java
	java TripleSum < 8ints.txt >> out
	java TripleSum < 1Kints.txt >> out
	java TripleSum < 2Kints.txt >> out
	java TripleSum < 4Kints.txt >> out
	java TripleSum < 8Kints.txt >> out 

big:
	java TripleSum < 16Kints.txt >> out
	java TripleSum < 32Kints.txt >> out
	java TripleSum < 64Kints.txt >> out 
