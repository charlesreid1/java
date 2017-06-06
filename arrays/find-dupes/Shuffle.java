import java.util.Random;
import java.util.ArrayList;

public class Shuffle {
	/**
	 * Fisher Yates shuffle - generic method.
	 */
	public static <T> void shuffle(ArrayList<T> inputs) { 
		Random r = new Random();
		int n = inputs.size();
		for(int j=n-1; j>0; j--) {
			int k = r.nextInt(j+1);
			swap(inputs, j, k);
		}
	}

	/** swap two "Objects" - generic method. */
	private static <T> void swap(ArrayList<T> arr, int x, int y){
		if(x != y) {
			T temp = arr.get(x);
			arr.set(x, arr.get(y)); 
			arr.set(y, temp); 
		}
	}

}

