import java.math.BigInteger;
import java.util.Queue;
import java.util.LinkedList;

/** 
 * Lucas Number Generator.
 *
 * This class can be used to generate the nth Lucas number.
 */
public class LucasGenerator 
	extends FibonacciGenerator 
	implements NumberGenerator {

	public int getF0() { return 2; }
	public int getF1() { return 1; }

}

