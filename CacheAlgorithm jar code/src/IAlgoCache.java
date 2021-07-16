 

public interface IAlgoCache<K,V> {

	public V getElement(K key);
	public V putElement(K key, V value);
	public void removeElemnt(K key);
	
}
