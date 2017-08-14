/** 
 * General Fibonacci Number Generator.
 *
 * This class can be used to generate the nth 
 * general Fibonacci number,
 *
 * F(a,b,n) 
 *
 * which is the nth term in the sequence defined by
 *
 * F(a,b,n) = F(a,b,n-1) + F(a,b,n-1)
 *
 * and 
 *
 * F(a,b,0) = a
 * F(a,b,1) = b
 */
public class GeneralFibonacciGenerator 
	extends FibonacciGenerator 
	implements NumberGenerator {

	protected int a, b;

	public GeneralFibonacciGenerator(int a, int b) {
		super(a,b);
		this.a = a;
		this.b = b;
	}
	public int getF0() { 
		return this.a; 
	}
	public int getF1() { 
		return this.b; 
	}

}

