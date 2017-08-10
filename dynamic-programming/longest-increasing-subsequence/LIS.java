/**
 * Longest Increasing (Non-Contiguous) Subsequence.
 *
 * Given a sequence of integers, find the longest increasing 
 * non-contiguous subsequence such that each element is increasing.
 *
 * L(j) = longest strictly increasing subsequence ending at position j.
 *
 * Recurrence relation:
 *
 * L(j) = ( max value of L(i), where i < j, A[i] < A[j] ) + 1
 *
 * (Make sure you add one!)
 *
 * Note that this program provides two methods:
 * The first method lis() computes the length of the LIS.
 * The second method lisSequnce() returns the actual LIS itself.
 *
 * */
public class LIS {

	/** Driver. */
	public static void main(String[] args) { 
		int[] arr = {2,4,3,5,1,7,6,9,8};
		lis(arr);
		lisSequence(arr);
	}



	/** Compute the length of the longest increasing subsequence. 
	 * 
	 * This does not return the sequence - just the length.
	 */
	public static void lis(int[] arr) { 

		// Dynamic programming lookup table
		int[] dp = new int[ arr.length ];

		// Populate the dynamic programming table to find the max
		int globalMax = 0;
		for(int j=0; j<arr.length; j++) { 
			if(j==0) { 
				dp[j] = 1;
			} else {
				int max = 0;
				// This inner loop makes the cost O(N^2)
				for(int k=j; k>=0; k--) { 
					if(arr[k] < arr[j]) { 
						max = Math.max( dp[k], max );
					}
				}
				dp[j] = max + 1;

				// Save yourself a trip through the array again later
				globalMax = Math.max( dp[j], globalMax );
			}
		}
		System.out.printf("Maximum increasing subsequence length: %d \n",globalMax);
	}



	/** Find the longest increasing subsequence. 
	 * 
	 * This returns the longest sequence itself.
	 * Just a little copy-pasta.
	 */
	public static void lisSequence(int[] arr) { 
		// This is nearly identical to lis() above,
		// except we need one additional piece of information:
		// the location of the predecessor subsequence.

		// Dynamic programming lookup table
		int[] dp = new int[ arr.length ];
		int[] idp = new int[ arr.length ];
		java.util.Arrays.fill(idp,-1);

		// Populate the dynamic programming table to find the max
		int globalMax = 0;
		for(int j=0; j<arr.length; j++) { 
			if(j==0) { 
				dp[j] = 1;
			} else {
				int max = 0;
				// This inner loop makes the cost O(N^2)
				for(int k=j; k>=0; k--) { 
					if(arr[k] < arr[j]) { 
						if(dp[k] > max) { 
							idp[j] = k;
							max = dp[k];
							break;
						}
					}
				}
				dp[j] = max + 1;

				// Save yourself a trip through the array again later
				globalMax = Math.max( dp[j], globalMax );
			}
		}

		// Use a string buffer so we can reverse the sequence
		StringBuffer sb = new StringBuffer();
		int i = dp.length-1;
		while(i>=0) { 
			if(idp[i]>=0) { 
				// Print out this item, and move on to its predecessor
				sb.append(arr[i]+" ");
				i = idp[i];
			} else if(i==0) { 
				// Last item will have predecessor of -1,
				// but we still want to print it out
				sb.append(arr[i]);
				break;
			} else {
				i--;
			}
		}
		sb.reverse();
		System.out.println(sb.toString());
	}
}
