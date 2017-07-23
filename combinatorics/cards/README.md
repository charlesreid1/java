# Combinatorics: Cards

This directory contains codes investigating combinatorics topics involving decks of playing cards.

Contents:
* CountAces class
* Utility classes
	* Binomial class
	* Hypergeometric class


## CountAces

This counts the probability of having at least one ace in a given hand, 
compared to the probability of having exactly one ace in a given hand.



## Utility Classes

### Binomial

This computes your basic binomial coefficient. 
This is useful for describing the number of outcomes 
or probability of k successful outcomes in n independent trials.
This is used for sampling with replacement.

```
binom(n,k) = ( n! )/( k! (n-k)! )
```

### Hypergeometric

The hypergeometric function is used for sampling without replacement.
This descries the number of outcomes of exactly k successes, given that 
there are K successes possible, in n samples of a population of size N.

```
binom(K,k,N,n) = ( n! )/( k! (n-k)! )
```

