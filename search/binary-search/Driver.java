import java.math.BigInteger;
import java.util.Random;
import java.util.Arrays;

/** 
 * Binary Search Driver.
 *
 * This runs a couple of tests of the binary search algorithm.
 */
public class Driver { 
	public static Random r = new Random();

	public static void main(String[] args) { 

		Integer[] numbers = {10, 30, 25, 22, 48, 55, 11, 9, 24};
		Arrays.sort(numbers);
		BinarySearcher<Integer> b = new BinarySearcher<>(numbers);
		System.out.println( "Searching for 30 (should be found): "+b.binarySearch(30) );
		System.out.println( "Value of numbers at search index: "+numbers[b.binarySearch(30)] );
		System.out.println( "Searching for 129 (should not be found):"+b.binarySearch(129) );
		System.out.println( "Value of numbers at insertion index: "+numbers[-(b.binarySearch(129)+1)] );

		System.out.println("\n--------------------------------\n");

		String[] cities = {"San Francisco","Seattle","San Diego","Las Vegas","Vancouver"};
		Arrays.sort(cities);
		BinarySearcher<String> b2 = new BinarySearcher<>(cities);
		System.out.println( "Searching for Seattle (should be found): "+b2.binarySearch("Seattle") );
		System.out.println( "Value of cities at search index: "+cities[b2.binarySearch("Seattle")] );
		System.out.println( "Searching for Portland (should not be found): "+b2.binarySearch("Portland") );
		System.out.println( "Value of cities at insertion index: "+cities[-(b2.binarySearch("Portland")+1)] );

		System.out.println("\n--------------------------------\n");

		int big = 15;
		BigInteger[] bigarray = new BigInteger[big];
		// A couple of easy-to-remember prime numbers
		bigarray[0] = new BigInteger("1000000000000066600000000000001"); // Belphegor's Prime
		bigarray[1] = new BigInteger("1234567891");
		bigarray[2] = new BigInteger("1000000007"); 
		bigarray[3] = new BigInteger("18181");
		bigarray[4] = new BigInteger("99999199999");
		bigarray[5] = new BigInteger("3");
		bigarray[6] = new BigInteger("3");
		bigarray[7] = new BigInteger("3");
		bigarray[8] = new BigInteger("11");
		bigarray[9] = new BigInteger("11");
		bigarray[10] = new BigInteger("1789");
		bigarray[11] = new BigInteger("1791");
		bigarray[12] = new BigInteger("11");
		bigarray[13] = new BigInteger("11");
		bigarray[14] = new BigInteger("11");
		Arrays.sort(bigarray);
		System.out.println(Arrays.toString(bigarray));
		BinarySearcher<BigInteger> b3 = new BinarySearcher<>(bigarray);

		BigInteger target;
		target = new BigInteger("18181");
		System.out.println( "Searching for 18181 (should be found): "+b3.binarySearchRecursive(target));

		target = new BigInteger("99999");
		System.out.println( "Searching for 99999 (should not be found): "+b3.binarySearchRecursive(target));

		target = new BigInteger("11");
		System.out.println( "Searching for 11 (should be found): "  + b3.binarySearchRecursive(target));
		System.out.println( "Counting duplicates of 11: " + b3.countDuplicates(target));



		System.out.println("\n--------------------------------\n");

		/*
		class Point implements Comparable<Point> {
			public int x, y;
			public Point(int x, int y) { 
				this.x = x;
				this.y = y;
			}
			public int compareTo(Point o) { 
				if(this.x<o.x) { 
					return -1;
				} else if(this.x>o.x) { 
					return 1;
				} else {
					if(this.y<o.y) { 
						return -1;
					} else if(this.y>o.y) { 
						return 1;
					} else {
						return 0;
					}
				}
			}
		}
		int n = 10;
		Point[] points = new Point[n];
		points[0] = new Point(1,1);
		points[1] = new Point(2,3);
		points[2] = new Point(8,4);
		points[3] = new Point(3,7);
		points[4] = new Point(7,3);
		points[5] = new Point(1,1);
		points[6] = new Point(2,5);
		points[7] = new Point(5,6);
		points[8] = new Point(1,1);
		points[9] = new Point(2,1);
		Arrays.sort(points);
		BinarySearcher<Point> b3 = new BinarySearcher<>(points);
		*/

	}
}
