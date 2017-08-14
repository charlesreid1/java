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

## Finding the Subsequence Length

Finding the *length* of the longest increasing subsequence 
is not a particularly difficult problem, we just need a single 
array to keep track of the length of the longest increasing
subsequence occurring at each index.

## Find the Subsequence Itself

To find the subsequence itself requires a little more trickery.
Specifically, we need to work backwards. For each number in the 
input array, we have the length of the longest subsequence.
But we also need the index at which that longest subsequence left off.

For example, if we were considering the sequence

1 4 5 2 9 

then when we get to the number 9 we should know to skip 2 
and go backwards to 5. This is information that we obtain
during the solution of the problem, we just have to save it 
in an additional array. Then, when we get to the end, we can work
backwards, and say the longest increasing subsequence ends with 9,
and the second-to-last number is 5 (not 2).


