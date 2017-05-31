// Code Jam starting strategy:
import java.util.*;
import java.io.*;

public class TripleSum {

	/** Print all triples ijk such that a[i]+a[j]+a[k] = 0.
	 * Keep it simple:
	 * straightforward approach, simple variable names, 
	 * easy algorithm analysis, printf - only the basics.
	 */
	public static void printAll(int[] arr) { 
		int n = arr.length;
		for(int i=0; i<n; i++ ) { 
			for(int j=i+1; j<n; j++) { 
				for(int k=j+1; k < n; k++) { 
					if( arr[i] + arr[j] + arr[k] == 0 ) {
						System.out.printf("%4d %4d %4d \n",arr[i],arr[j],arr[k]);
					}
				}
			}
		}
	}

	/** count all triples ijk such that a[i]+a[j]+a[k] = 0.
	 * If the purpose is to time an algorithm, 
	 * don't include an if switch that will have to be checked a bazillion times.
	 * */
	public static int countAll(int[] arr) { 
		int n = arr.length;
		int count = 0;
		for(int i=0; i<n; i++ ) { 
			for(int j=i+1; j<n; j++) { 
				for(int k=j+1; k < n; k++) { 
					if( arr[i] + arr[j] + arr[k] == 0 ) {
						count++;
					}
				}
			}
		}
		return count;
	}

    public static void main(String[] args)  { 

		// Code Jam starting strategy:
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		// Scanner is easy to use. If not... super awkward...

		// Load numbers into ArrayList
		ArrayList<Integer> alist = new ArrayList<Integer>();
		while(in.hasNextInt()) {
			alist.add(in.nextInt());
		}
		// turn ArrayList into int[]
		int[] a = new int[alist.size()];
		for(int i=0; i<alist.size(); i++) { 
			a[i] = alist.get(i);
		}

		// Time
        long start = System.currentTimeMillis();

        int count = countAll(a);

        long now = System.currentTimeMillis();
        double elapsed = (now - start) / 1000.0;

        System.out.printf("elapsed time = %.4f\n",elapsed);
        System.out.println(count);
    } 
} 
