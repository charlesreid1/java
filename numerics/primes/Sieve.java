import java.util.List;
import java.util.ArrayList;

public class Sieve {


	public static void main(String[] args) { 
		test();
	}

	public static void test() { 
		int N;
		List<Long> primes;

		// 15485863
		N = 1000000;
		primes = primeSieve( (int)(N*Math.log(N)*Math.log(N) ));
		System.out.println(primes.get(N-1));

		// 224737
		N = 20000;
		primes = primeSieve( (int)(N*Math.log(N)*Math.log(N) ));
		System.out.println(primes.get(N-1));

		// 7919
		N = 1000;
		primes = primeSieve( (int)(N*Math.log(N)*Math.log(N) ));
		System.out.println(primes.get(N-1));

		// 541
		N = 100;
		primes = primeSieve( (int)(N*Math.log(N)*Math.log(N) ));
		System.out.println(primes.get(N-1));

		// 29
		N = 10;
		primes = primeSieve( (int)(N*Math.log(N)*Math.log(N) ));
		System.out.println(primes.get(N-1));

	}



	/** Find all prime numbers below n.
	 *
	 * Return then as Integers.
	 */
	public static List<Long> primeSieve(int n) { 

        // initially assume all integers are prime 
        boolean[] isPrime = new boolean[n+1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor*factor <= n; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider mutiples factor, factor+1, ...,  n/factor
            if (isPrime[factor]) {
                for (int j = factor; factor*j <= n; j++) {
                    isPrime[factor*j] = false;
                }
            }
        }

		// Add primes to list
		ArrayList<Long> primes = new ArrayList<>();
		int nprimes = 0;
		for(int k=2; k<=n; k++) {
			if(isPrime[k]) { 
				primes.add( new Long(k) );
				nprimes++;
			}
		}

		return primes;
	}


	/** Find n prime numbers.
	 */
	public static List<Long> primeSieveN(int n) { 

		// estimate of the nth prime number:
		// p(n) ~ n log n + n log log n
		// 
		// First term was the estimate used by Gauss.
		int approx_p = 20*(int)Math.round(n*Math.log(n) + n*Math.log(Math.log(n)));

        // initially assume all integers are prime 
        boolean[] isPrime = new boolean[approx_p+1];
        for (int i = 2; i <= approx_p; i++) {
            isPrime[i] = true;
        }

        // mark non-primes using Sieve of Eratosthenes
        for (int factor = 2; factor <= approx_p; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider mutiples factor, factor+1, ...,  n/factor
            if (isPrime[factor]) {
                for (int j = factor; factor*j <= approx_p; j++) {
					try {
						isPrime[factor*j] = false;
					} catch (ArrayIndexOutOfBoundsException e) { 
						break;
					}
                }
            }
        }

		// Add primes to list
		ArrayList<Long> primes = new ArrayList<Long>();
		int nprimes = 0;
		int k = 2;
		while( nprimes < n ) { 
			if(isPrime[k]) { 
				primes.add( new Long(k) );
				nprimes++;
			}
			k++;
		}
		return primes;
	}

}
