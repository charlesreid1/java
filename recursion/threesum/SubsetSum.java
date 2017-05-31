import java.util.*; 
import java.io.*; 

/** Find number of subsets of numbers that sum to 0.
 *
 * Uses recursive backtracking, and two stacks of integers.
 *
 * */
public class SubsetSum {
	private static int nsolutions = 0;

	public static void subsetSums(List<Integer> list) { 
		explore(list);
	}

	public static int sum(List<Integer> list) { 
		int sum = 0;
		for(int i=0; i<list.size(); i++) {
			sum += list.get(i);
		}
		return sum;
	}

	public static void explore(List<Integer> superset) { 
		List<Integer> brandnew = new ArrayList<Integer>();
		System.out.println(explore_(superset, brandnew));
	}

	private static int explore_(List<Integer> unchosen, List<Integer> chosen) {
		if(unchosen.isEmpty()) {
			// base case
			//
			// We may find a combination at any point, so we can never "end early".
			// We can only keep going until unchosen is empty.
			return nsolutions;
		} else {
			// Check if we have found a sum, in which case, yay!
			if( sum(chosen)==0 ) {
				//System.out.println(chosen);
				nsolutions++;
			}

			// recursive case
			for(int j = 0; j < unchosen.size(); j++ ) {

				// Make a choice
				Integer element = unchosen.get(j);
				unchosen.remove(j);
				chosen.add(element);

				// Explore consequences
				explore_(unchosen, chosen);

				// Unmake the choice
				chosen.remove(chosen.size()-1);
				unchosen.add(j,element);

			} // move on to next choice

		}
		return nsolutions;
	}

	public static void main(String[] args) { 
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));		
		List<Integer> list = new ArrayList<Integer>();
		while(in.hasNextInt()){
			list.add(in.nextInt());
		}
		subsetSums(list);
	}
}