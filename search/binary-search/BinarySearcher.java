// Comparable does not require any import statements

/**
 * Binary Searcher class.
 *
 * This binary search class takes an array of data of an arbitrary type,
 * and provides a binary search algorithm.
 *
 * This requires that the data type that the user is storing
 * implements Comparable and the compareTo function.
 */
public class BinarySearcher<T extends Comparable<T>> {

	// Store the data passed in by the user
	protected T[] data;

	/** Constructor - pass in the data to be searched. */
	public BinarySearcher(T[] data) { 
		this.data = data;
	}

	/** Search the array of data for target.
	 *
	 * This returns the (negative) insertion index
	 * if the target is not found.
	 */
	public int search(T target) {

		// Start by defining your lo/hi index
		int lo = 0;
		int hi = data.length-1;

		// Mid gets defined here because it will become  
		// the insertion index if we don't find target
		int mid = (data.length)/2;

		// Can use while loop, or recursive structure
		while(lo<hi) { 

			// Need to re-define mid each time
			mid = (lo+hi)/2;

			// Classic, man.
			if(target.compareTo(data[mid]) < 0) { 
				// Move left
				hi = mid-1;
			} else if(target.compareTo(data[mid]) > 0) { 
				// Move right
				lo = mid+1;
			} else {
				return mid;
			}
		}

		// Insertion index
		return -(mid+1);
	}
}