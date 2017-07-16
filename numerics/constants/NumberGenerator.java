import java.math.BigInteger;

/** Base class for number generators.
 */
public interface NumberGenerator {
	public BigInteger next();
	public BigInteger peek();
	public boolean hasNext();
}
