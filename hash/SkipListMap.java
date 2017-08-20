import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
/**
 * Skip List Map.
 *
 * This class implements a sorted map
 * using a skip list data structure.
 *
 * The skip list stores MapItem<K,V> objects
 * in a hierarchical, rows-and-towers
 * skip list structure.
 *
 * There are a series of h lists,
 * each holding entries of the map
 * in sorted order.
 *
 * Analogous to java.util.ConcurrentSkipList.
 */
public class SkipListMap <K extends Comparable<K>,V> 
	extends AbstractMap<K,V> {


	public static void main(String[] args) { 
		SkipListMap<String,String> m = new SkipListMap<String,String>();
		m.put("documentation","That which we know nothing about, we must pass over in silence.");
		m.put("models","All models are wrong, some models are useful.");
		m.put("buddha","You will not be punished for your anger, you will be punished by your anger.");
		m.put("knowledge","All the evolution we know of proceeds from the vague to the definite.");

		System.out.println("Skip list map size: "+m.size());
		System.out.println("Skip list map entry for buddha: "+m.get("buddha"));

		System.out.println("Removing an item...");
		m.remove("knowledge");
		System.out.println("Skip list map size: "+m.size());

		/*
		System.out.println("Iterating over items: ");
		for(MapItem<String,String> item : m.itemSet()) {
			String i = item.getKey();
			String j = item.getValue();
			System.out.println(i + " --> " + j);
		}
		*/
		System.out.println("Done.");

	}



	/** 
	 * Skip List Map Node - utility class.
	 */
    protected static class SkipListMapNode<K extends Comparable<K>, V> 
			extends SkipList.SkipNode<K> {

        protected V value;
        protected SkipListMapNode(int level, K key) {
            super(level, key);
        }
		public K getKey() { return data; }
		public V getValue() { return value; }
        public String toString() {
            StringBuffer buff = new StringBuffer();
            buff.append(super.toString());
            buff.append("value = ").append(value).append("\n");
            return buff.toString();
        }
	}




	/** 
	 * Skip list map class. 
	 * */


	private SkipList<K> skipList;

    public SkipListMap() {
        skipList = new SkipList<K>();
    }

	/** Put a key-value pair into the map. 
	 * If a value for this key already exists, overwrite the current value.
	 * */
    public void put(K key, V value) {
        SkipList.SkipNode<K> node = skipList.addValue(key);
        if (node instanceof SkipListMapNode) {
            SkipListMapNode<K, V> mapNode = (SkipListMapNode<K, V>) node;
            mapNode.value = value;
        }
    }

	/** Get the value corresponding to this key from the map. */
    public V get(K key) {
        SkipList.SkipNode<K> node = skipList.getNode(key);
        if (node instanceof SkipListMapNode) {
            SkipListMapNode<K, V> mapNode = (SkipListMapNode<K, V>) node;
            return mapNode.value;
        }
        return null;
    }

	/** Boolean: does the map contain a value with this key? */
    public boolean contains(K key) {
        return skipList.contains(key);
    }

	/** Remove an item from the map. */
    public V remove(K key) {
        SkipList.SkipNode<K> node = skipList.removeValue(key);
        V value = null;
        if (node instanceof SkipListMapNode) {
            SkipListMapNode<K, V> treeMapNode = (SkipListMapNode<K, V>) node;
            value = treeMapNode.value;
            treeMapNode.data = null;
            treeMapNode.value = null;
        }
        return value;
    }


    public void clear() {
        skipList.clear();
    }




    public Iterator<SkipListMapNode<K,V>> iterator() {
		Set<SkipListMapNode<K,V>> s = new HashSet<>();
		SkipListMapNode<K,V> strider = (SkipListMapNode<K,V>)(skipList.getHead());
		s.add(strider);
		while(strider.getNext(0)!=null) { 
			strider = (SkipListMapNode<K,V>)(strider.getNext(0));
			s.add(strider);
		}
		return s.iterator();
    }


	public int size() { 
		return skipList.size();
	}


	/** Iterator over the items in this map. */
	public Iterable<MapItem<K,V>> itemSet() { 
		return null;
		/*
		ArrayList<MapItem<K,V>> buffer = new ArrayList<>();
		Iterator<SkipNode<T>> iter = skipList.iterator();
		while(iter.hasNext()) { 
			SkipNode<K> s = iter.next();
			SkipListMapNode<K,V> sm = (SkipListMapNode<K,V>)(s);
			buffer.add( new MapItem<K,V>(sm.getKey(), sm.getValue()) );
		}
		return buffer;
		*/
	}


    /**
     * Wrapper function to create a new node
	 * with the given key at the given skip list level.
     */
    public SkipList.SkipNode<K> createNewNode(int level, K key) {
        return (new SkipListMapNode<K, V>(level, key));
    }

    /**
     * Swap two nodes 
     */
    public void swapNode(SkipList.SkipNode<K> node, SkipList.SkipNode<K> next) {
		K temp = node.data;
		node.data = next.data;
		next.data = temp;

        if (node instanceof SkipListMapNode && next instanceof SkipListMapNode) {
            SkipListMapNode<K,V> node2 = (SkipListMapNode<K,V>) node;
            SkipListMapNode<K,V> next2 = (SkipListMapNode<K,V>) next;
            V value = node2.value;
            node2.value = next2.value;
            next2.value = value;
        }
    }




}

