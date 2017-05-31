import java.util.*;
import java.io.*;

class MyException extends Exception {
	public MyException(String msg) { 
		super(msg);
	}
}

class BoringException extends Exception {}

public class ThrowExceptions {
	public static void main(String[] args) throws MyException {

		// We can raise BoringException if we catch it.
		try {
			throw new BoringException();
		} catch (BoringException e) { 
			System.out.println("BoringException has nothing exciting to say:");
			System.out.println(e.getMessage());
			System.out.println("Done catching BoaringException.");
		}

		// We can raise MyException without catching it.
		throw new MyException("hope you like exceptions.");
	}
}
