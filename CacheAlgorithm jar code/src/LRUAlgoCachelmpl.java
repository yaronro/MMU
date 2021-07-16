 import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;


public class LRUAlgoCachelmpl<K,V>
extends AbstractAlgoCache<K,V>  implements IAlgoCache <K,V> {

	protected Map<K, V> lruLinked;


	//constructor
	public LRUAlgoCachelmpl(int capacity) {
		super(capacity);
		
		//LinkedHashMap with this argument: 0.75f, true. Acting like LRU caching.
		this.lruLinked = new LinkedHashMap<>(getCapacity(), 0.75f, true);

	}

	@Override
	public V getElement(K key) {

		Entry< K, V> entry = lruLinked.entrySet().iterator().next();
		return  entry.getValue();	
	}

	@Override
	public V putElement(K key, V value) {


		if(lruLinked.containsValue(value))
		{
			// Get, take the element and put it at the end of the list 
			return lruLinked.get(value);
		}

		if(lruLinked.size() < getCapacity())
		{
			lruLinked.put(key, value);
		}else {
			
			//Delete the first element to be deleted according to LRU
			removeElemnt(key);
			// And put the elemnt in the Hash according to the order of LRU
			lruLinked.put(key, value);


		}
		return value;
	}

	@Override
	public void removeElemnt(K key) {

		K keyTemp = lruLinked.keySet().iterator().next();
		lruLinked.remove(keyTemp);

	}




}
