
public interface Position<E> { 
	/** 
	 * Return the element stored at this position.
	 * Returns stored element.
	 * Throws illegal state exception if position not valid.
	 */
	E getElement() throws IllegalStateException;
}
