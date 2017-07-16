import java.math.BigInteger;

public class FibonacciNumbers {
	public static final BigInteger ONE = BigInteger.ONE;
	public static final BigInteger TWO = BigInteger.valueOf(2);

	public static void main(String[] args) { 
		System.out.println("100th Fibonacci Number:");
		System.out.println(nthFib(100));
		System.out.println("Last Fibonacci Number below 100:");
		System.out.println(maxFib(100));
	}

	public static BigInteger nthFib(int n) {
		if(n<1) { 
			throw new IllegalArgumentException("n should be 1 or more.");
		}
		FibonacciGenerator fibGen = new FibonacciGenerator();
		BigInteger fibn = ONE;
		for(int i=0; i<n; i++) { 
			fibn = fibGen.next();
		}
		return fibn;
	}

	public static BigInteger maxFib(int MAX) {
		if(MAX<1) { 
			throw new IllegalArgumentException("MAX should be 1 or more.");
		}
		FibonacciGenerator fibGen = new FibonacciGenerator();
		BigInteger fibn = fibGen.next();
		while(fibGen.peek().compareTo(BigInteger.valueOf(MAX))<0) { 
			fibn = fibGen.next();
		}
		return fibn;
	}
}
