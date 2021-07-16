import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

public class RandomAlgoCacheImpl<K, V>
extends AbstractAlgoCache<K,V> implements IAlgoCache <K,V>{

	Random rand = new Random();
	protected HashMap<K,V> cacheMap;
	
	//constructor
	public RandomAlgoCacheImpl(int capacity) {
		super(capacity);
		this.cacheMap = new HashMap<>(getCapacity());
	}

	@Override
	public V getElement(K key) {
		Entry< K, V> entry = cacheMap.entrySet().iterator().next();
		return  entry.getValue();
	}

	@Override
	public V putElement(K key, V value) {

		if(cacheMap.containsValue(value))
			return null;

		if( cacheMap.size() < getCapacity() )
			cacheMap.put(key, value);		
		else
		{
			//Create an array of keys and insert all keys from Hash to array.
			//And select a random element from the array.
			
			List<K> keysArr = new ArrayList<K>(cacheMap.keySet());
			int randomKey = rand.nextInt(keysArr.size());
			K newK = keysArr.get(randomKey);
			removeElemnt(newK);

			cacheMap.put(key, value);

		}
		return value;

	}

	@Override
	public void removeElemnt(K key) {
		// TODO Auto-generated method stub
		cacheMap.remove(key);
	}
}