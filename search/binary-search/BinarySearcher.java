// Comparable does not require any import statements

/**
 * Binary Searcher class.
 *
 * This binary search class takes an array of data of an arbitrary type,
 * and provides a binary search algorithm.
 *
 * This requires that the data type that the user is storing
 * implements Comparable and the compareTo function.
 *
 * This also requires that the array be sorted.
 *
 * Implements the following methods:
 *		- Binary search (iterative, while loop)
 *		- Binary search (recursive)
 *		- Binary search duplicate count
 */
public class BinarySearcher<T extends Comparable<T>> {

	// Store the data passed in by the user
	protected T[] data;

	/** Constructor - pass in the data to be searched. */
	public BinarySearcher(T[] data) { 
		this.data = data;
	}


	/** 
	 * Methods 
	 * */


	/** Perform a binary search to find target in array of data.
	 *
	 * This returns the (negative) insertion index
	 * if the target is not found.
	 */
	public int binarySearch(T target) {

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



	/** Perform a (recursive) binary search to find target in array of data.
	 *
	 * This calls a private recursive method.
	 * It returns the (negative) insertion index if target is not found. 
	 */
	public int binarySearchRecursive(T target) { 
		return binarySearchRecursive_(target, 0, data.length);
	}

	/** Perform a (recursive) binary search to find target in array of data.
	 *
	 * This is the protected workhorse method.
	 */
	protected int binarySearchRecursive_(T target, int lo, int hi) { 
		int mid = (lo+hi)/2;
		if( lo>hi ) { 
			// Base case:
			// Return insertion index
			return -(mid+1);
		} else {
			// Recursive case:
			// Keep looking
			if( target.compareTo(data[mid]) < 0 ) {
				// Go left
				return binarySearchRecursive_(target, lo, mid-1);
			} else if( target.compareTo(data[mid]) > 0 ) {
				// Go right
				return binarySearchRecursive_(target, mid+1, hi);
			} else {
				// Equals
				return mid;
			}
		}
	}


	/** Count duplicates with binary search.
	 *
	 * This uses a modified binary search that moves right 
	 * and looks for the right boundary, instead of finding
	 * the first index where target occurs. 
	 */
	public int countDuplicates(T target) { 
		// Use a modified binary search to find the left/right boundaries
		int left, right;
		left = binaryLeftBoundarySearch(target);
		System.out.println("Left boundary: " +left);
		if(left<0) { 
			return 0;
		} else {
			right = binaryRightBoundarySearch(target);
			System.out.println("Right boundary: "+right);
			return right-left+1;
		}
	}


	/** Search for the right boundary of a run of objects. 
	 *
	 * This works basically the same as binary search,
	 * except we don't have an equals to stop us, and we 
	 * default to moving right. 
	 *
	 * To return the right boundary, return hi instead of mid.
	 */
	public int binaryRightBoundarySearch(T target) {

		// Start by defining your lo/hi index
		int lo = 0;
		int hi = data.length-1;

		int mid = (data.length)/2;

		// Can use while loop, or recursive structure
		while(lo<=hi) { 

			// Need to re-define mid each time
			mid = (lo+hi)/2;

			// Classic, man.
			if(target.compareTo(data[mid]) < 0) { 
				// Move left
				hi = mid-1;
			} else {
				// Move right by default.
				lo = mid+1;
			}
		}

		// Above loop won't stop, 
		// we always get here.
		//
		// Add 1 to get to the first occurrence
		// of target in data array.
		return hi; 
	}


	/** Search for the letft boundary of a run of objects. 
	 *
	 * To return the left boundary, return lo+1 instead of mid.
	 */
	public int binaryLeftBoundarySearch(T target) {

		// Start by defining your lo/hi index
		int lo = 0;
		int hi = data.length-1;

		int mid = (data.length)/2;

		// Can use while loop, or recursive structure
		while(lo<hi) { 

			// Need to re-define mid each time
			mid = (lo+hi)/2;

			// Classic, man.
			if(target.compareTo(data[mid]) > 0) { 
				// Move right
				lo = mid + 1;
			} else {
				// Move left by default.
				hi = mid-1;
			}
		}

		// Return lo instead of mid. mid is not up to date.
		return lo+1;
	}

}
