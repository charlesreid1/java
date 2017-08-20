# Racing Gems: Dynamic Programming Problem

## Problem Description

This problem is an ICPC programming competition problem.

In this problem, we have a race track
of width x = 0 to x = w, height y = 0 to 
y = h. We have a character that begins at 
the x-axis (y=0) and moves toward the 
finish line (y=h) with a steady velocity v.

You control the character's horizontal velocity,
-v/r to +v/r, and your goal is to obtain 
as many gems as possible.

Determine the maximum number of gems 
that can be obtained by the character.

## Solution Procedure

To solve this problem, we begin by transforming 
all (x,y) points into a new coordinate system,
where we can think about the "x movement" cost.
This is because our principal restriction
is on horizontal movement.

To do this, transform points from `(x,y)`
to `(x - y*r, x + y*r)`.
Then each gem's new coordinate point
now represents the starting x locations
if we were to go backwards,
from the gem to the start line,
at the maximum leftward horizontal velocity,
-v/r, or at the maximum rightward 
horizontal velocity, +v/r.

Next, we begin by marching along through 
various starting positions on the original x axis,
and see whether we can reach the resulting gem.

In practice, trying different starting positions
on the original x axis just means we sort each of the 
(transformed) points by its (transformed) x coordinates,
which indicates the last possible starting point on the x 
axis that would allow us to reach that gem.

We then perform a binary search/insertion. If the 
corresponding y-coordinate is somewhere between the 
maximum/minimum values we already have, it means
we must choose between this gem and another gem
we already looked at. Thus, the size will not change.

If, on the other hand, we perform a binary insertion, 
and the item is inserted at the right boundary of the array 
of values, it means we have a new gem that can be reached,
in addition to all of the prior gems that we can reach.

To express this another way: we want to iterate over each gem;
we assume that we start as far to the left as possible, 
and travel in the maximum upward/right direction.
Any gem can be reached by traveling in this manner, 
but the question is whether we have to backtrack to the left
to reach a given gem. 

(Note that this program does not answer the question of *which* gems
can be reached, it only answers the question of *how many*.)

## Example Input

Here is an example input.

The first line contains the integers n, r, w, and h:
* n = 10 is number of gems available
* r = 3 is horizontal velocity control parameter
* w = 30 is the width
* h = 30 is the height

The lines that follow specify the location of each gem.

```
10 3 30 30
14 9
2 20
3 23
15 19
13 5
17 24
6 16
21 5
14 10
3 6
```

The output is:

```
3
```

