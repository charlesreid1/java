import java.util.Iterator;
import java.util.Random;

/** 
 * Skip List class.
 *
 * This implements a sorted set 
 * by using a skip list.
 *
 * If the skip list stores sorted key-value pairs,
 * then we have a sorted map.
 */
public class SkipList<T extends Comparable<T>> {


	public static void main(String[] args) { 
		testAddAscending();
		testAddDescending();
	}



	public static void testAddDescending() {
		System.out.println("Testing add in descending order...");

        SkipList<Integer> sList = new SkipList<Integer>();
		System.out.println("Successfully created skip list.");

		if( sList.add(55) ) { 
			System.out.println("Successfully added item to skip list. Size = " + sList.size());
		}
		if( sList.add(44) ) { 
			System.out.println("Successfully added item to skip list. Size = " + sList.size());
		}
		if( sList.add(33) ) { 
			System.out.println("Successfully added item to skip list. Size = " + sList.size());
		}
		if( sList.add(22) ) { 
			System.out.println("Successfully added item to skip list. Size = " + sList.size());
		}
		if( sList.add(11) ) { 
			System.out.println("Successfully added item to skip list. Size = " + sList.size());
		}
	}



	public static void testAddAscending() {
		System.out.println("Testing add in ascending order...");

        SkipList<Integer> sList = new SkipList<Integer>();
		System.out.println("Successfully created skip list.");

		if( sList.add(55) ) { 
			System.out.println("Successfully added item to skip list. Size = " + sList.size());
		}
		if( sList.add(66) ) { 
			System.out.println("Successfully added item to skip list. Size = " + sList.size());
		}
		if( sList.add(77) ) { 
			System.out.println("Successfully added item to skip list. Size = " + sList.size());
		}
		if( sList.add(88) ) { 
			System.out.println("Successfully added item to skip list. Size = " + sList.size());
		}
		if( sList.add(96) ) { 
			System.out.println("Successfully added item to skip list. Size = " + sList.size());
		}
		if( sList.add(99) ) { 
			System.out.println("Successfully added item to skip list. Size = " + sList.size());
		}
	}



	//////////////////////////////////
	// Utility classes:

	/** Skip Node.
	 * The skip node is n object that represents a single node 
	 * in a skip list. Nodes may have many neighbors.
	 */
    protected static class SkipNode<T extends Comparable<T>> {
		// node at level i stores i+1 neighbors
		//private ArrayList<SkipNode<T>> next;
		private SkipNode<T>[] next;
		protected T data;

		public SkipNode(int level, T data) {
			//next = new ArrayList<SkipNode<T>>();
			this.next = new SkipNode[level+1];
			this.data = data;
		}
		public SkipNode(T data) {
			this.data = data;
		}
		/** Get the highest level this SkipNode is located at */
        public int getLevel() {
            return next.length-1;
        }
		/** Set this SkipNode's next SkipNode at the specified level. */
        public void setNext(int level, SkipNode<T> target) {
			// Set this node's next item, at specified level
            this.next[level] = target;
        }
		/** Get this SkipNode's next SkipNode at the specified level. */
        public SkipNode<T> getNext(int level) {
            if (level >= this.next.length) {
				return null;
			}
            return this.next[level];
        }
		/** Compare two skip list nodes by comparing their payloads. */
		public int compareTo(SkipNode<T> node) { 
			return this.data.compareTo(node.data);
		}
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("data=").append(data);
            if (this.next!=null) {
                sb.append(" next=[");
                int size = next.length;
                for (int i=0; i<size; i++) {
                    SkipNode<T> n = next[i];
                    if (n!=null) {
						sb.append(n.data);
					} else {
						sb.append("none");
					}
                    if (i!=size-1) {
						sb.append(", ");
					}
                }
                sb.append("]");
            }
            return sb.toString();
		}
	}

	protected class LevelNodeTuple<T extends Comparable<T>> {
		SkipNode<T> node;
		int level;
		public LevelNodeTuple(int level, SkipNode<T> node) { 
			this.node = node;
			this.level = level;
		}
	}

	//////////////////////////////////////////////
	// Class proper:


	// for generating random places to put stuff in the skip list:
	private static final int MAX = 31; // max height of skip list
	private static final Random seedGenerator = new Random(); // generates random number seed
	private int randomSeed = -1; // holds random number seed
	private int size; // number of elements in skip list

	private SkipNode<T> head; // Head of the list

	public SkipList() { 
		// Start by seeding your random number generators for better perf.
		this.randomSeed = seedGenerator.nextInt();
	}

	public int size() { 
		return this.size;
	}

	/** Pure magic. */
    private int getRandom() {
        int x = randomSeed;
        x ^= x << 13;
        x ^= x >>> 17;
        randomSeed = x ^= x << 5;
        if ((x & 0x8001) != 0) // test highest and lowest bits
            return 0;
        int level = 1;
        while (((x >>>= 1) & 1) != 0) ++level;
        return level;
    }



	// Methods are divided roughly as follows:
	//	- Add methods
	//	- Remove methods
	//	- Retrieve (get/contains) methods 
	//	- Utility methods



	/** Public add method.
	 * Calls an internal add method. 
	 * Returns false if node already in skip list.
	 */
	public boolean add(T value) { 
		SkipNode<T> result = addValue(value);
		if(result != null) { 
			return true;
		}
		return false;
	}

	/** Protected add method. 
	 * Creates a new node, searches for the right spot
	 * to add it into the skip list, and rearranges 
	 * the skip list as needed.
	 */
	protected SkipNode<T> addValue(T value) { 
		// Can't make the node yet, because we don't know the level
		SkipNode<T> result = null; 

		if(head == null) { 

			// empty skip list

			// if head is null, add to new list at level MAX
			SkipNode<T> node = new SkipNode<T>(MAX,value);
			head = node;
			result = node;

		} else {

			// non-empty skip list

			// add node to a new random level:
			int insertionLevel = getRandom();
			SkipNode<T> node = new SkipNode<T>(insertionLevel,value);

			// create strider to point to the insertion index
			SkipNode<T> strider = head;	

			// Handle case where new node goes before head.
			// (Note: we can either compare nodes directly,
			//  or compare their data if we extended Comparable.)
			if(node.compareTo(head)<0) {
				swapNode(node,head);
				result = head;
			} else {
				result = node;
			}

			// start from the top:
			// work your way down and right
            for (int i=MAX; i>=0; i--) {

                SkipNode<T> nextStrider = strider.getNext(i);

                while(nextStrider!= null) {

                    if (nextStrider.compareTo(node)>0) { 
						// Next stride takes us too far. We are pointing at insertion index.
						break;
					}
					// Make the next stride
                    strider = nextStrider;
                    nextStrider = strider.getNext(i);
                }
                if (i <= insertionLevel) {
                    // node is the node that we are adding, 
					// and it should be added to this level.
                    node.setNext(i, nextStrider);
                    strider.setNext(i, node);
                }
            }
		}

        size++;
        return result;
    }



	/** Public remove method.
	 * Calls an internal remove method. 
	 * Returns false if node not found in skip list. 
	 */
    public boolean remove(T value) {
        SkipNode<T> result = removeValue(value);
        return (result!=null);
	};

	/** Protected remove method. 
	 * Removes the node with the specified value 
	 * from the skip list, and stitches things up
	 * as needed.
	 */
    protected SkipNode<T> removeValue(T value) {
		// handle the case of an empty list first
		if(size()==0) { 
			return null;
		}

		SkipNode<T> result = null;
		SkipNode<T> resultPrev = null;
		SkipNode<T> resultNext = null;
		SkipNode<T> dummyTarget = new SkipNode<T>(value);
		int level = -1;

		// Step 1:
		// Find result and resultPrev nodes,
		// plus whatever level we need to be on.
		if(head.compareTo(dummyTarget)==0) { 
			//handle the case of removing the head 
			result = head;
		} else {
			// Find the node we want, 
			// and the preceding node to the node we want.
			LevelNodeTuple<T> pair = getPredecessor(value);
			if(pair!=null) { 
				resultPrev = pair.node;
				level = pair.level;
			}
			if(resultPrev==null) { 
				// Unable to find predecessor node
				return null;
			}
			// Use the target node's predecessor 
			// to get the target node
			result = resultPrev.getNext(pair.level);
			if(result==null || result.compareTo(dummyTarget) != 0) { 
				// Unable to find predecessor value
				return null;
			}
		}

		// Step 2:
		// Find resultNext node.
		if(resultPrev==null) { 
			// This must be the head node, 
			// since it is the only node with no predecessor
			resultNext = result.getNext(0);
			if(resultNext != null) { 
				// We are removing the head,
				// so put the payload for resultNext 
				// into head (it is the new head).
				//
				// (Note: result should always be head here)
				swapNode(result,resultNext);
				resultPrev = result;
				result = resultNext;
			}

		} else {
			// This is the easy case: 
			// use the result pointer to set the resultNext pointer
			resultNext = result.getNext(level);

		}

		// Step 3:
		// Starting from the top level where this node occurs,
		// move down and remove the node.
		int maxLevel = result.getLevel();
		for(int i=maxLevel; i>=0; i--) { 
			// Result refers to the tower with our result to be removed. 
			// As we move down the levels, grabbing the top of the tower, at that level,
			// resultNext points to the next node, AT LEVEL i. 
			resultNext = result.getNext(i);

			// We are looking at a new next node and a new level, so update previous node
			if(resultPrev != null) { 
				resultPrev.setNext(i, resultNext);

				// Keep moving down if we aren't at the last level
				if(i>0) { 
					// Move down the tower 1 level
					SkipNode<T> temp = resultPrev.getNext(i-1);
					// Look for the resultsPrev node on this level
					while(temp != null && temp.compareTo(dummyTarget) != 0){
						resultPrev = temp;
						temp = temp.getNext(i-1);
					}
				}
			}
		}

		// Finished removing element! 
		// Update size and return the removed element.
		size--;
		return result;
	}

	// Get methods:
	//	- getNode
	//	- getPredecessor


	/** Workhorse method - get predecessor to this node. */
    private LevelNodeTuple<T> getPredecessor(T value) {
		SkipNode<T> node = head;
		SkipNode<T> dummyTarget = new SkipNode<T>(value);
		int resultLevel = -1;

		// Can assume we won't get here if the structure is empty. 

		// Handle the case of value -> first node
		if(node.compareTo(dummyTarget)==0) {
			return null;
		}

		// Now, look for the node (and level)
		// where next node has value "value"
		int level = node.getLevel();
		SkipNode<T> strider = node.getNext(level);
		while(strider==null) { 
			// strider is null when we hit the end of a row.
			// move down.
			if(level>0) { 
				level--;
				strider = node.getNext(level);
			} else {
				break;
			}
		}

		// We stopped, so probably at level 0.
		while(strider != null) { 
			if( strider.compareTo(dummyTarget) == 0 ){
				// Strider is pointing at the right node,
				// so we want to returnt the previous one.
				LevelNodeTuple<T> tuple = new LevelNodeTuple<T>(level,node);
				return tuple;
			} else if( strider.compareTo(dummyTarget)>=1 ) {
				// Found a node that is greater than us,
				// so stop moving right - move down.
				if(level>0) { 
					level--;
				} else {
					return null;
				}

                // Keep moving right 
                strider = node.getNext(level);

			} else {
				// Next node is less than our target node,
				// so keep moving to the right.
				// Update node,
				node = strider;
				// then update strider from node.
				strider = node.getNext(level);
				while(strider==null && level>0) { 
					level--;
					strider = node.getNext(level);
				}
			}
		}
		return null;

	}

	/** Get the SkipNode corresponding to this value.
	 * Lets getPredecessor do all the work. 
	 * */
    protected SkipNode<T> getNode(T value) {
		// Deal with the empty case
		if(head==null) { 
			return null;
		}

		SkipNode<T> dummyTarget = new SkipNode<T>(value);

		// Deal with the equals-first-element
        if(head.compareTo(dummyTarget)==0) { 
			return head;
		}

		LevelNodeTuple<T> p = getPredecessor(value);
		if(p==null) {
			return null;
		}

		// Note that this is necessary so that getPredecessor can do the work for us.
		return p.node.getNext(p.level);
	}





	// Protected utility methods:

	/** Swap out the values in SkipNode a and SkipNode b. */
	protected void swapNode(SkipNode<T> a, SkipNode<T> b) { 
		T temp = a.data;
		a.data = b.data;
		b.data = temp;
	}





	/*

	// Phishman's methods:
    public boolean add(T value) {};
    protected Node<T> addValue(T value) {};

    public boolean contains(T value) {};
    protected Node<T> getNode(T value) {};
    private NodeLevelPair<T> getPredecessor(T value) {};

    public boolean remove(T value) {};
    protected Node<T> removeValue(T value) {};

    protected void swapNode(Node<T> node, Node<T> next) {};

    public void clear() {};
    public int size() {};
    public boolean validate() {};

    public java.util.Set<T> toSet() {};
    public java.util.Collection<T> toCollection() {};
    public String getString(T value, int level) {};

	*/

    public Iterator<T> iterator() {
        return null;
    }

}

