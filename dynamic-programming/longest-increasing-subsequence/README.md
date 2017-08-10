# Longest Increasing (Non-Contiguous) Subsequence

 Given a sequence of integers, 
 find the longest increasing 
 non-contiguous subsequence such that
 each element is increasing.

Step 1: formulate answer as recurrence relation

Step 2: show that number of parameter values taken on by algorithm is bounded by polynomial

Step 3: Specify an order of evaluation such that information needed is always available

## Recurrence Relation

Let $L(j)$ denote the longest increasing non-contiguous subsequence
that ends at position j. This subsequence starts at an earlier position i,
specifically at the position i that has the longest increasing
non-contiguous subsequence. Thus,

$$
L(j) = ( \max_{i<j, A[i]<A[j]} L(i) ) + 1
$$

That is, we want to find a value of i (less than j) 
such that the integer value at i is less than the integer value of j.
We take the L(i) value and add one to it.

We can do this with a 1D array.

## Obtaining the Subsequence

We can do the easy version, and just count the *length* of the 
longest increasing subsequence, or we can keep track of more information
and return the actual subsequence itself.

We will do both.

