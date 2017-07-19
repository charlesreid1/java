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

		System.out.println();

		target = new BigInteger("11");
		System.out.println( "Binary search for 11: " + b3.binarySearch(target) );
		System.out.println( "Searching for left boundary of 11: "  + b3.binaryLeftBoundarySearch(target));
		System.out.println( "Searching for left boundary of 11: "  + b3.binaryRightBoundarySearch(target));
		System.out.println( "Counting duplicates of 11: " + b3.countDuplicates(target));

		target = new BigInteger("1789");
		System.out.println( "Binary search for 1789: " + b3.binarySearch(target) );
		System.out.println( "Searching for left boundary of 1789: "  + b3.binaryLeftBoundarySearch(target));
		System.out.println( "Searching for right boundary of 1789: "  + b3.binaryRightBoundarySearch(target));
		System.out.println( "Counting duplicates of 1789: " + b3.countDuplicates(target));



		System.out.println("\n--------------------------------\n");

	}
}
