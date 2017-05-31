subset:
	javac SubsetSum.java && java SubsetSum < ../../data/8ints.txt

small:
	cat /dev/null > out
	javac TripleSum.java
	java TripleSum < ../../data/8ints.txt >> out
	java TripleSum < ../../data/1Kints.txt >> out
	java TripleSum < ../../data/2Kints.txt >> out
	java TripleSum < ../../data/4Kints.txt >> out
	java TripleSum < ../../data/8Kints.txt >> out 

big:
	java TripleSum < ../../data/16Kints.txt >> out
	java TripleSum < ../../data/32Kints.txt >> out
	java TripleSum < ../../data/64Kints.txt >> out 
