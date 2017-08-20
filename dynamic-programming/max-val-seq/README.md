# Maximum Value Contiguous Subsequence

## Problem Description

Given a sequence of n real numbers, `A(1) ... A(n)`, determine a contiguous subsequence `A(i) ... A(j)` 
for which the sum of elements in the subsequence is maximized. 

Input: array of real numbers.

Output: maximum contiguous subsequence start index i, end index j, and sum s.

$$
\max \sigma_{m = i}^{j} A[m]
$$

To express this as a recurrence relation,
we can write the MVCS at position j
in terms of the MVCS at position j-1:

$$
M(j) = MAX( M(j-1)+A[j] , A[j] )
$$

Now we have n sub-problems to solve, each taking
O(1) time, so the entire problem can be solved
in O(n) time.

## Example

Suppose we have the array

```
1 -12 3 -8 7 -10 4 50 1
```

(Note that if there are no negative numbers, this is not an interesting problem).

Then we can start at the beginning: the MVCS at position 0 must be 

$$
M(0) = A[0] = 1
$$

The MVCS at position 1 is

$$
M(1) = MAX( M(0) - 12,  -12 ) \\
M(1) = MAX( -11, -12 ) \\
M(1) = -11
$$

The MVCS at position 2 is:

$$
M(2) = MAX( M(1) + 3, 3 ) \\
M(2) = MAX( -11, 3 ) \\
M(2) = 3
$$

And so on...

The final result should be 55.

## Solution

To implement this solution, we need n slots, one for each item in the array.
We can then roll on down the line.


