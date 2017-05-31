import java.util.*;
import java.io.*;

/** Implements a Python-like ArrayList. 
 * 
 * Poor man's templating - Object[]
 * */
public class PythonList { 
	final private int INITCAP = 10;
	private Object[] data;
	public int length;

	public PythonList() {
		length = 0;
		data = new Object[INITCAP];
	}

	public Object get(int ix) {
		return data[ix];
	}

	public void append(Object o) { 
		if(data.length==length) {
		    resize(data.length*2);
		}
		data[length] = o;
		length++;
	}

	public void remove(int rmi) { 
		if(rmi>=length) { 
			throw new ArrayIndexOutOfBoundsException("Error accessing index "+rmi);
		}
		data[rmi] = null;
		for(int i=rmi; i<length; i++) {
			data[i] = data[i+1];
		}
		length--;
		if(length<(data.length/4)) {
			resize(data.length/2);
		}
	}

	private void resize(int newcap) {
		Object[] newarr = new Object[newcap];
		for(int i=0; i<length; i++ ) { 
			newarr[i] = data[i];
		}
		data = newarr;
	}

	public String toString() {
		return Arrays.toString(data);
	}



	public static void main(String[] args) { 

		PythonList p = new PythonList();
		Random r = new Random();

		int n = 50;

		for(int i=0; i<100; i++ ) {
			p.append(r.nextInt(6)+1);
			System.out.println(p);
		}
		System.out.println(p);
	}
}
