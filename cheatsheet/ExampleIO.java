/** Example IO: Read a stream of numbers from stdin.
 *
 * This can then be passed in on the command line.
 *
 * $ java ExampleIO < myfile.txt
 */
public class ExampleIO {
	public static void main(String[] args) { 
		Sanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	}
}
