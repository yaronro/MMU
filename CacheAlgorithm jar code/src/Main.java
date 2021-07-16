
public class Main {

	public static void main(String[] args) {
		
	
		SecondChanceAlgoCacheImpl<Object,Object> algo = new SecondChanceAlgoCacheImpl<>(3);
		
		algo.putElement(1, 2);		
		algo.putElement(2, 3);
		algo.putElement(3, 2);		
		algo.putElement(4, 1);
		algo.putElement(5, 5);
		algo.putElement(6, 2);		
		algo.putElement(7, 4);
		algo.putElement(8, 5);		
//		algo.putElement(9, 3);
//		algo.putElement(10, 2);
//		algo.putElement(11, 5);
//		algo.putElement(12, 2);


		System.out.println(algo.secondLinked);
		System.out.println("val:"+algo.linkedRefBit.get(0).v + " bit:"+algo.linkedRefBit.get(0).refBit);
		System.out.println("val:"+algo.linkedRefBit.get(1).v + " bit:"+algo.linkedRefBit.get(1).refBit);
		System.out.println("val:"+algo.linkedRefBit.get(2).v + " bit:"+algo.linkedRefBit.get(2).refBit);



		
		
//		LRUAlgoCachelmpl<Object,Object> algo = new LRUAlgoCachelmpl<>(3);
//		
//		algo.putElement(1, 1);
//		algo.putElement(2, 2);
//		algo.putElement(3, 3);
//		System.out.println(algo.lruLinked);
//		algo.putElement(4, 1);
//		System.out.println(algo.lruLinked);
//		algo.putElement(5, 1);
//		System.out.println(algo.lruLinked);
//		algo.putElement(6, 7);
//		System.out.println(algo.lruLinked);
//		algo.putElement(7, 6);
//		System.out.println(algo.lruLinked);
	}
	
}
