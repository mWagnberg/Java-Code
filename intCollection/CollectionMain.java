package mw222uu_assign1.intCollection;

public class CollectionMain 
{
	public static void main(String[] args)
	{
		ArrayIntList ari = new ArrayIntList();
		
		// Check add-method
		ari.add(1);
		
		// Check addAt-method
		ari.addAt(2, 1);
		ari.addAt(3, 2);
		ari.addAt(4, 3);
		ari.addAt(5, 4);
		ari.addAt(6, 5);
		ari.addAt(7, 6);
		ari.addAt(8, 7);
		ari.addAt(9, 11);
		
		System.out.println("Create a list of integer by the methods add and addAt:");
		for(int element : ari.values)
		{
			System.out.print(element + " ");
		}
		System.out.println();
		
		// Check remove-method
		System.out.println("Remove an integer on index 3 and print the list again:");
		ari.remove(3);
		for(int element : ari.values)
		{
			System.out.print(element + " ");
		}
		System.out.println();
		
		// Check get-method
		System.out.println("Get the integer on index 5:");
		System.out.println(ari.get(5));
		
		// Check indexOf-method
		System.out.println("Find position of integer 5:");
		System.out.println(ari.indexOf(5));
		System.out.println();
		
		//ArrayIntStack
		ArrayIntStack ais = new ArrayIntStack();
		System.out.println("Fill the stack with the push-method:");
		
		// Check the push-method
		ais.push(5);
		ais.push(7);
		
		System.out.println();
		
		// Check the pop-method
		System.out.println(ais.peek());
		
		
		
		
		
		
		
		
	}
}
