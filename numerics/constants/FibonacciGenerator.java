import java.math.BigInteger;
import java.util.Queue;
import java.util.LinkedList;

/** 
 * Fibonacci Number Generator.
 *
 * This class can be used to generate the nth Fibonacci number,
 * or to get all Fibonacci numbers matching some criteria,
 * simply by wrapping the call to next() differently.
 */
public class FibonacciGenerator implements NumberGenerator {

	protected static final BigInteger ONE = BigInteger.ONE;

	// Starting values
	// Change to get a Lucas number/general Fib. number
	protected int F0 = 1;
	protected int F1 = 1;

	protected Queue<BigInteger> F_k;

	public FibonacciGenerator() { 
		F_k = new LinkedList<BigInteger>();
		// Seed with starting values
		F_k.add( BigInteger.valueOf(F0) );
		F_k.add( BigInteger.valueOf(F1) );
	}

	public boolean hasNext() {
		return true;
	}

	protected BigInteger generateNextFib(boolean increment) {
		/*
		 * We can think about the queue of Fibonacci numbers as being 
		 * (k-1), (k)
		 *
		 * or as being
		 * (k), (k+1)
		 */
		if(increment) { 
			BigInteger fibk = F_k.remove();
			BigInteger fibkp1 = F_k.peek();
			F_k.add( fib(fibk,fibkp1) );
			return fibk;
		} else {
			return F_k.peek();
		}
	}

	public BigInteger next() {
		return generateNextFib(true);
	}

	public BigInteger peek() {
		return generateNextFib(false);
	}

	protected static BigInteger fib(BigInteger Fkm2, BigInteger Fkm1) { 
		return Fkm1.add(Fkm2);
	}
}

