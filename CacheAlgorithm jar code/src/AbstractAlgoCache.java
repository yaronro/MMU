
public abstract class AbstractAlgoCache <K, V> extends java.lang.Object 
implements IAlgoCache <K,V>{
	
	private int capacity;

	//constructor
	public AbstractAlgoCache(int capacity){

		setCapacity(capacity);
	}

	public int getCapacity() {
		return capacity;
	}

	//check if capacity valid
	public void setCapacity(int capacity) {
		if (capacity> 0)
		{
			this.capacity = capacity;
		}
		else 
		{
			System.out.println("not valid capacity");
		}
	}
}