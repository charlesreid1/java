import java.util.List;

public class SquareRootCF {
	public static void main(String[] args) { 
		List<Integer> primes = Sieve.primeSieveInt(50);
		primes.add(0,1);
		System.out.println(primes);

		for(int i=0; i<10; i++) { 
			long[] conv = Convergents.convergents(primes, i);
			System.out.println(conv[0] + "/" + conv[1]);
			System.out.println(1.0*conv[0]/conv[1]);
		}
	}
}
