/** 
 * Maximum value contiguous subsequence.
 *
 * This finds the contiguous subsequence with the maximum sum.
 *
 * This program uses dynamic programming to find a solution.
 * */
public class MVCS { 

	public static void main(String[] args) { 
		int[] arr = {1, -12, 3 -8, 7, -10, 4, 50, 1};
		mvcs(arr);
	}


	/** Computes and returns the maximum value contiguous subsequence.
	 */
	public static void mvcs(int[] arr) { 

		// Recurrence relation:
		// M(j) = MAX( M(j-1)+A[j], A[j] )
		//
		// Base case:
		// M(0) = A[0]

		int[] dp = new int[arr.length];

		// Base case:
		dp[0] = arr[0];

		// Recursive case:
		for(int i=1; i<arr.length; i++) { 
			dp[i] = Math.max( dp[i-1]+arr[i], arr[i] );
			/*
			if( dp[i-1]+arr[i] > arr[i] ) { 
				dp[i] = dp[i-1] + arr[i];
				dp_length++;
			} else {
				dp[i] = arr[i];
				dp_start_index = i;
				dp_length = 0;
			}
			*/
		}

		int mvcs = dp[0];
		for(int j=0; j<dp.length; j++) { 
			if(dp[j] > mvcs) {
				mvcs = dp[j];
			}
		}

		System.out.printf("MVCS = %d\n",mvcs);
	}

}