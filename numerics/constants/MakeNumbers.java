
import java.math.BigInteger;

public class MakeNumbers {

	public static final BigInteger ONE = BigInteger.ONE;
	public static final BigInteger TWO = BigInteger.valueOf(2);

	public static void main(String[] args) { 
		//testFib();
		//testLucas();
		//runFib();
		//runLucas();
	}




	/** Run the lucas generator to generate 1k lucases. */
	public static void runLucas() { 
		int N = 10000;
		LucasGenerator lucGen = new LucasGenerator();
		BigInteger lucn = ONE;
		for(int i=1; i<=N; i++) { 
			lucn = lucGen.next();
			System.out.printf("L(%d) = %s \n", i, lucn.toString());
		}
	}
	
	/** Test the lucas generator works ok. */
	public static void testLucas() { 
		System.out.println("100th Lucas Number:");
		System.out.println(nthLucas(100));
		System.out.println("Should be 489526700523968661124");
		System.out.println("Last Lucas Number below 100:");
		System.out.println(maxLucas(100));
		System.out.println("Should be 76");
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




	/** Run the fibonacci generator to generate 1k fibs. */
	public static void runFib() { 
		int N = 1000;
		FibonacciGenerator fibGen = new FibonacciGenerator();
		BigInteger fibn = ONE;
		for(int i=1; i<=N; i++) { 
			fibn = fibGen.next();
			System.out.printf("F(%d) = %s \n", i, fibn.toString());
		}
	}

	/** Test the fib generator works ok. */
	public static void testFib() { 
		System.out.println("100th Fibonacci Number:");
		System.out.println(nthFib(100));
		System.out.println("Should be 354224848179261915075");
		System.out.println("Last Fibonacci Number below 100:");
		System.out.println(maxFib(100));
		System.out.println("Should be 89");
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
