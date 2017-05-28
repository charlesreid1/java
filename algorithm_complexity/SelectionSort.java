import java.util.Arrays;

public class SelectionSort {

	public static void selection_sort(char[] s) {
		int min; // indexes of minimum
		int n = s.length;
		for(int i=0; i<n; i++) {

			// Print the String at each step:
			System.out.println(new String(s));

			min = i;
			for(int j=i+1; j<n; j++) { 
				if(s[j] < s[min]) {
					min = j;
				}
			}
			swap(s, i, min);
		}
	}

	public static void swap(char[] s, int i, int min) {
		char temp = s[i];
		s[i] = s[min];
		s[min] = temp;
	}

	public static void printTest(String str) {

		char[] s = str.toCharArray();

		System.out.println(str);
		selection_sort(s);
		System.out.println(new String(s));
	}

	public static void main(String[] args) { 
		printTest("SELECTIONSORT");
		printTest("S");
		printTest("1840");
		printTest("I am the batman!");
	}
}
