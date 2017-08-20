public class TimeLoop {
	public static void main(String[] args) { 
		long N = (long)(1E12);
		Tim tim = new Tim();
		tim.tic();
		for(long i=0; i<N; i++) { 
			// nothing
		}
		tim.toc();
		System.out.printf("A trillion iterations took %.3f seconds\n",(tim.elapsedms()/1E3));
	}
}