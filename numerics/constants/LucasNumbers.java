import java.math.BigInteger;

public class LucasNumbers {
	public static final BigInteger ONE = BigInteger.ONE;
	public static final BigInteger TWO = BigInteger.valueOf(2);

	public static void main(String[] args) { 
		System.out.println("100th Lucas Number:");
		System.out.println(nthLucas(100));
		System.out.println("Last Lucas Number below 100:");
		System.out.println(maxLucas(100));
	}

	public static BigInteger nthLucas(int n) {
		if(n<1) { 
			throw new IllegalArgumentException("n should be 1 or more.");
		}
		LucasGenerator lucGen = new LucasGenerator();
		BigInteger lucn = ONE;
		for(int i=0; i<n; i++) { 
			lucn = lucGen.next();
		}
		return lucn;
	}

	public static BigInteger maxLucas(int MAX) {
		if(MAX<1) { 
			throw new IllegalArgumentException("MAX should be 1 or more.");
		}
		LucasGenerator lucGen = new LucasGenerator();
		BigInteger lucn = lucGen.next();
		while(lucGen.peek().compareTo(BigInteger.valueOf(MAX))<0) { 
			lucn = lucGen.next();
		}
		return lucn;
	}
}
