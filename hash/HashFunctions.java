public class HashFunctions {

	public static void main(String[] args) { 
		System.out.println("AAA");
		System.out.println(hashCodeLoud("AAA"));
	}

	public static int hashCodeLoud(String s) { 
		int h = 0;
		for(int i=0; i<s.length(); i++) { 
			System.out.print(h);
			h = (h<<5) | (h>>>27);
			System.out.print(" --> ");
			System.out.print(h);
			h += (int)(s.charAt(i));
			System.out.print(" --> ");
			System.out.println(h);
		}
		System.out.println("Finally: "+h);
		return h;
	}

	public static int hashCode(String s) { 
		int h = 0;
		for(int i=0; i<s.length(); i++) { 
			h = (h<<5) | (h>>>27);
			h += (int)(s.charAt(i));
		}
		return h;
	}
}