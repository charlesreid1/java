public class Driver { 
	public static void main(String[] args) { 
		Integer[] data = {10, 30, 25, 22, 48, 11, 9, 24};
		BinarySearcher<Integer> b = new BinarySearcher<>(data);
		System.out.println( b.search(30) );
	}
}
