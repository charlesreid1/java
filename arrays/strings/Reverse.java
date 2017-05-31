public class Reverse { 
	/** Implement a linear time string reversal algorithm. */
	public static String stringReverse(String in){ 
		char[] c = in.toCharArray();
		int n = in.length();
		char[] r = new char[n];
		for(int i=0; i<n; i++) { 
			r[i] = c[n-1-i];
		}
		return new String(r);
	}

	public static String stringReverseFaster(String in){ 
		char[] c = in.toCharArray();
		int n = in.length();
		for(int i=0; i<(n/2); i++){
			int j = (n-1)-i;
			char temp = c[i];
			c[i] = c[j];
			c[j] = temp;
		}
		return new String(c);
	}

	public static void main(String[] args) {
		String h = "Hello world!";
		System.out.println(h);
		System.out.println(stringReverse(h));
		System.out.println(stringReverseFaster(h));
	}
}
