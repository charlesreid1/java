import java.util.*;
import java.io.*;
/**
 * Racing Gems ICPC Puzzle.
 *
 * You are playing a racing game.
 * Your character starts at the x axis (y=0)
 * and ends at the finish line (y=h).
 * Your character must stay in the bounds
 * of the track, at x=0 and x=w.
 *
 * On the track there are several gems,
 * located at (x,y) coordinates.
 * Your goal is to collect as many gems 
 * as possible.
 *
 * The catch is that you move with a constant
 * vertical velocity v, and a horizontal velocity
 * you control, between -v/r and v/r.
 *
 * n = number of gems
 * r = horiz. velocity limit
 * w = width of track
 * h = height of track
 *
 * This solution implements simulated annealing.
 */
public class RacingGems {

	public static void main(String[] args) { 
		solve();
	}


	/** Utility class for an (x,y) point. */
	public static class Point implements Comparable<Point> { 
		public long x, y;
		public Point(long x0, long y0) { 
			x = x0; y = y0;
		}
		/** String representation of (x,y) point. */
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("(");
			sb.append(x);
			sb.append(",");
			sb.append(y);
			sb.append(")");
			return sb.toString();
		}
		/** Compare by x, break ties by y. */
		public int compareTo(Point o) { 
			// Using Long.compare() makes this succinct.
			if(this.x==o.x) { 
				return Long.compare(this.y, o.y);
			} else {
				return Long.compare(this.x, o.x);
			}
		}
	}

	/** Read points (gem locations) from input file. */
	public static Point[] getPoints() {
		// Read points from input
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int n = s.nextInt();
		int r = s.nextInt();
		long w = s.nextLong();
		long h = s.nextLong();

		// Create a sorted array of Points
		Point[] p = new Point[n];
		for(int i=0; i<n; i++) { 
			long x = s.nextLong();
			long y = s.nextLong();

			// Transform the coordinates of the points 
			// to a system where we can think about
			// "x movement" cost
			p[i] = new Point( x - (y*r), x + (y*r) );
			//
			// Don't do this:
			//p[i] = new Point(x,y);
		}
		Arrays.sort(p);
		return p;
	}

	public static int insert(long[] arr, long target, int size) { 
		// This is basically a binary search
		// for the first location in arr
		// where........???
		int lo = 0; 
		int hi = size - 1;
		int mid = (lo+hi)/2;
		while( lo <= hi ) {
			mid = (lo+hi)/2;
			if(arr[mid] > target) { 
				hi = mid-1;
			} else {
				lo = mid+1;
			}
		}
		arr[lo] = target;
		return lo;
	}


	/** Solve the problem using the standard approach:
	 * determine where in the racing gems array 
	 * a particular gem is located, and figure out whether
	 * we can reach this new gem.
	 * The insert() method tells us about how many 
	 * gems we can get to before this one.
	 */
	public static void solve() {
		Point[] p = getPoints();
		int n = p.length;
		int size = 1;
		long[] dp = new long[n];
		dp[0] = p[0].y;
		for(int i=0; i<n; i++) { 
			long target = p[i].y;
			int newsize = insert(dp, target, size)+1;
			size = Math.max(size, newsize);
			System.out.println(size);
		}
		System.out.println(size);
	}
}