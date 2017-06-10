import java.util.*;
import java.io.*;

class Illegal extends IllegalArgumentException {}
class SuperIllegal extends IllegalArgumentException {
	public SuperIllegal(String msg){
		super(msg);
	}
}

/** Average - a simple class wrapping a sum and a counter. */
class Average { 
	private int n = 0;
	private int sum = 0;
	public Average() {}
	public double addAnother(double s) {
		sum += s;
		n++;
		return (1.0*sum)/n;
	}
	public String toString() { return String.format("%.2f",(1.0*sum)/n); }
	public double getAvg() {
		return (1.0*sum)/n;
	}
	public void resest() { 
		this.n = 0;
		this.sum = 0;
	}
}

/** Statistics - a static/procedural program for computing statistics from a CSV file. */
public class Statistics {
	/* Procedural, static program.
	 *
	 * A minimally object-oriented statistics program. 
	 * This was a final project for my CSC 142 Java Programming 1 students,
	 * so it was not supposed to be procedural-heavy.
	 *
	 * The map collections and Average class are useful in helping
	 * reduce the number of operations.
	 */
	public static void main(String[] args) throws FileNotFoundException, Illegal { 

		System.out.println("\n\nStatistics Program: Alpine Meadows Meteorological Data\n\n");

		// Get stdin scanner
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		/////////////////////////
		// I had some abstraction problems up front:
		// - how much of the problem should be generalized?
		// - how deep should the object-oriented design go?
		// - where to stamp out the generality and just get something done?
		//
		// Ultimately, my conclusion was,
		// the top priority is getting it done.
		// We can pontificate later, and iteratively improve.
		//
		// But it is better to quickly solve something that 
		// really needs a more complicated solution,
		// than it is to over-complicate a simple problem.
		////////////////////////

		// Get headers.
		String[] headers = getHeaders(sc);

		// Extract data by column,
		// computing a running average as we go.
		// Ignore first column.
		Map<String,Average> m = new HashMap<String,Average>();
		for(int h=0; h<headers.length; h++) { 
			m.put(headers[h], new Average());
		}

		// Now run through all the data and compute the running average
		Average a;
		while(sc.hasNextLine()) {
			double[] data = getData(sc, headers.length);
			// if problems, data.length == 0
			for(int d=0; d<data.length; d++) { 
				a = m.get(headers[d]);
				a.addAnother(data[d]);
			}
		}

		// Print a nice fancy report.
		for(int h=0; h<headers.length; h++) { 
			System.out.println("Average: "+headers[h] + " = " + m.get(headers[h]));
		}
			

	}

	/** Get one row of data from file */
	public static double[] getData(Scanner sc, int ncols) { 

		double[] data = new double[ncols];

		String line = sc.nextLine();
		line = scrubComments(line);

		// Now split at ,
		String[] splitline = line.split(",");

		// Hope this works.
		// Skip malformed/empty lines
		if(splitline.length<2) { 
			// return empty []
			return data;
		}

		// Skip first column
		for(int c=1; c<ncols; c++) { 
			try { 
				String token = splitline[c];
				double d = Double.parseDouble(splitline[c]);
				data[c] = d;

			} catch(ArrayIndexOutOfBoundsException e) { 
				continue;
			}
		}
		return data;

	}

	/** Get headers from first row of file */
	public static String[] getHeaders(Scanner sc) throws Illegal {

		// Read the following from the file:
		// - column headers
		int row = 0;
		while(sc.hasNextLine()) {
			
			String line = sc.nextLine();
			line = scrubComments(line);

			// Now split at ,
			String[] splitline = line.split(",");

			// Skip malformed/empty lines
			if(splitline.length<2) { 
				continue;
			} else { 
				return splitline;
			}
		}
		throw new Illegal();
	}

	/** Scrub comments beginning at # and ending at end of line */
	public static String scrubComments(String lineIn) {
		String lineOut = "";

		int commentIndex = -1;
		for(int j=0; j<lineIn.length(); j++) { 
			// find the hash sign
			char c = lineIn.charAt(j);
			if(c=='#') { 
				commentIndex = j;
				break;
			}
		}
		if(commentIndex>=0) {
			lineOut = lineIn.substring(0,commentIndex);
		} else {
			lineOut = lineIn;
		}
		return lineOut;
	}

}

