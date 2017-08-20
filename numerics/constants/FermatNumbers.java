import java.math.BigInteger;

/** Compute N Fermat numbers.
 *
 * The Fermat numbers are given by the form:
 *
 * 2^2^n + 1
 *
 * The first n Fermat numbers are sequence A000215,
 * 3, 5, 17, 257, ...
 *
 * If you have the n^th Fermat number, you can compute
 * the n+1^th Fermat number using the recurrence relation:
 *
 * F(n+1) = ( F(n) - 1 )^2 + 1
 *
 * since:
 *
 * 2^2^(n+1) = (2^2^n)^2 = (2^2^n + 1 - 1)^2 + 1 = (F(n)-1)^2 + 1
 *
 */
public class FermatNumbers {

	public static final int N = 20;

	public static final BigInteger ONE = BigInteger.valueOf(1);
	public static final BigInteger TWO = BigInteger.valueOf(2);

	public static void main(String[] args) { 

		BigInteger fermat = getFermat0();
		System.out.printf("F(0) = %s \n", fermat.toString());
		System.out.println("\n");
		for(int i=1; i<=N; i++) { 
			fermat = getNextFermat(fermat);
			System.out.printf("F(%d) = %s \n", i, fermat.toString());
			System.out.println("\n");
		}
	}

	public static BigInteger getFermat0() {
		BigInteger fermat = TWO.pow(1).add(ONE);
		return fermat;
	}

	public static BigInteger getNextFermat(BigInteger fermatN) { 
		BigInteger fermatNplus1 = fermatN.subtract(ONE).pow(2).add(ONE);
		return fermatNplus1;
	}
}