import java.util.Comparator;
import java.util.Arrays;
import java.util.Random;

public class MyComparator {
	public static final Random r = new Random();

	public static void main(String[] args) { 
		comparatorArray();
	}


	public static void comparatorArray() {

		//// Lambda comparator:
		// Comparator<Point> pc = (Point p1, Point p2)->(p2.x-p1.x);
		// Comparator<Point> pc = (Point p1, Point p2)->(p2.y-p1.y);
		// Comparator<Point> pc = (Point p1, Point p2)->( (p2.x==p1.x)?(p2.y-p1.y)?(p2.x-p1.x) );

		Comparator<Point> pc = (Point p1, Point p2)->(distanceFromOrigin(p2)-distanceFromOrigin(p1));
		
		// Make random points 
		int n = 7;
		Point[] points = new Point[n];
		for(int i=0; i<points.length; i++) { 
			Point p = new Point( randCoord(), randCoord() );
			points[i] = p;
		}
		Arrays.sort(points, pc);
		System.out.println(Arrays.toString(points));
	}

	public static int randCoord() { 
		int MIN = 100;
		int MAX = 999;
		return r.nextInt(MAX-MIN)+MIN;
	}

	public static int distanceFromOrigin(Point p) { 
		Point o = new Point(0,0);
		return Math.sqrt((p.x-o.x)*(p.x-o.x) + (p.y-o.y)*(p.y-o.y));
	}

	public static class Point {
		public int x, y;
		public Point(int x0, int y0) {x=x0; y=y0;}
		public String toString() { return "("+this.x+","+this.y+")"; }
	}

}

