import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;


public class SecondChanceAlgoCacheImpl<K,V> extends AbstractAlgoCache<K,V>  implements IAlgoCache <K,V> {

	// Node of Linked list.
	public class Node<K,V>
	{
		boolean refBit;
		K k;
		V v;
		Node(K k,V v,boolean refBit )
		{
			this.refBit = refBit;
			this.k = k;
			this.v = v;
		}
	}

	protected LinkedList<Node<K,V>> linkedRefBit;
	protected LinkedHashMap<K, V> secondLinked;


	//constructor
	public SecondChanceAlgoCacheImpl(int capacity) {
		super(capacity);

		this.secondLinked = new LinkedHashMap<>(getCapacity(), 0.75f, true);
		this.linkedRefBit=new LinkedList<Node<K,V>>();
	}

	@Override
	public V getElement(K key) {

		Entry< K, V> entry = secondLinked.entrySet().iterator().next();
		return  entry.getValue();
	}

	@Override
	public V putElement(K key, V value)
	{
		// if the element exist
		if(secondLinked.containsValue(value))
		{
			// create Node with reference bit = true.
			Node<K,V> tmp =new Node<K,V>(key,value,true);

			//find the element in the linked list and set the Node in.
			for (int i = 0; i < linkedRefBit.size(); i++)
			{
				if(linkedRefBit.get(i).v == value)
				{
					linkedRefBit.set(i, tmp);
					return value;
				}
			}
			return value;
		}
		// If the first condition did not exist and there is another place in the Hash
		if(secondLinked.size() < getCapacity())
		{
			Node<K,V> tmp =new Node<K,V>(key,value,false);

			//Enter both lists in the linked list and in the HashLinkedList.
			secondLinked.put(key, value);	
			linkedRefBit.add(tmp);
		}
		else
		{
			//Checks if there is a second chance in the first place.
			if(linkedRefBit.getFirst().refBit == true)
			{

				Node<K,V> tmp1 =new Node<K,V>(linkedRefBit.get(0).k,linkedRefBit.get(0).v,false);

				//if there is a second chance change reference bit to false.
				linkedRefBit.set(0, tmp1);

				//And check whether the second place has a second chance and so on, up to the n place.
				for (int i = 1; i < linkedRefBit.size(); i++)
				{


					//When I discovered the first place in the list that does not have a second chance
					//delete from the list as and puts into the list as FIFO. Of course updating both lists.
					if(linkedRefBit.get(i).refBit == false)
					{

						Node<K,V> newtmp =new Node<K,V>(key,value,false);

						K k = linkedRefBit.get(i).k;
						secondLinked.remove(k);
						linkedRefBit.remove(i);

						linkedRefBit.add(newtmp);
						secondLinked.put(key, value);
						return value;
					}
					else
					{
						Node<K,V> tmp =new Node<K,V>(key,value,false);
						linkedRefBit.set(i, tmp);
					}
				}
			}
			if(linkedRefBit.getFirst().refBit == false)
			{
				Node<K,V> tmp =new Node<K,V>(key,value,false);

				removeElemnt(linkedRefBit.getFirst().k);
				linkedRefBit.remove(linkedRefBit.getFirst());

				linkedRefBit.add(tmp);
				secondLinked.put(key, value);
			}
		}
		return value;
	}

	@Override
	public void removeElemnt(K key) {
		K keyTemp = secondLinked.keySet().iterator().next();
		secondLinked.remove(keyTemp);
	}

}