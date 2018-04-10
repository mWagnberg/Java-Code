package mw222uu_assign4;
/**
 * 
 * @author MichaelWagnberg
 * This program will create a binary max heap. Max heap means that the children always is smaller
 * than the parent
 */
public class BinaryIntHeap 
{
	// Create fields
	private int[] arr;
	private int position;
	private int size;
	
	// Create a constructor holding the fields
	public BinaryIntHeap()
	{
		position = 1;
		size = 10;
		arr = new int[size];
	}
	
	/**
	 * This will insert a integer in the heap
	 * @param n is the integer that will be added
	 */
	public void insert(int n)
	{
		// Check if the heap is full, if true then resize
		if(position == size)
		{
			resize();
		}
		
		/* Insert the integer at the last position in the heap and create a child and a parent
		 * While the parent value is smaller than the child value then swap places
		 * and update the variables*/
		else
		{
			arr[position] = n;
			int child = position;
			int parent = child / 2;
			
			while(arr[parent] < arr[child] && parent > 0)
			{
				int temp = arr[parent];
				arr[parent] = arr[child];
				arr[child] = temp;
				
				child = parent;
				parent = child / 2;
			}
			
			// Add one the position so that you always add the new integer at the last position
			position ++;
		}
	}
	
	/**
	 * This will resize the array
	 */
	private void resize() 
	{
		int[] list = new int[arr.length*2];
		
		for(int i = 0; i < arr.length; i++)
		{
			list[i] = arr[i];
		}
		
		arr = list;
	}

	/**
	 * This will return and remove the first/highest integer in the heap
	 * @return the first/highest integer
	 */
	public int pullHighest()
	{
		// Add the highest integer to a variable
		int temp = arr[position-size()];
		int high = position-size();
		
		// Insert the last integer to the first position
		arr[high] = arr[position-1];
		arr[position-1] = 0;
	
		/* While the right child is less than the length of the array
		 * and the value of the highest is less than its children*/
		while(rightChild(high) < arr.length && (arr[high] < arr[rightChild(high)] || arr[high] < arr[leftChild(high)]))
		{
			/* If the right child is larger than the left child, the highest
			 * integer will swap position with the right child
			 * and update the highest integer */
			if(arr[leftChild(high)] < arr[rightChild(high)])
			{
				int temp2 = arr[high];
				arr[high] = arr[rightChild(high)];
				arr[rightChild(high)] = temp2;
				high = (2 * high) + 1;
			}

			/* If the left child is larger than the right child, the highest
			 * integer will swap position with the left child
			 * and update the highest integer */
			else
			{
				int temp3 = arr[high];
				arr[high] = arr[leftChild(high)];
				arr[leftChild(high)] = temp3;
				high = (2 * high);
			}
		}
		
		// Decrease the size and return the highest integer
		position --;
		return temp;
	}
	
	/**
	 * This will compute the left child of n
	 * @param n is the integer that will computed
	 * @return the left child of n
	 */
	public int leftChild(int n)
	{
		return n * 2;
	}

	/**
	 * This will compute the right child of n
	 * @param n is the integer that will computed
	 * @return the right child of n
	 */
	public int rightChild(int n)
	{
		return (n * 2) + 1;
	}

	/**
	 * This will compute the parent of n
	 * @param n is the integer that will computed
	 * @return the parent of n
	 */
	public int getParent(int n)
	{
		return n / 2;
	}
	
	/**
	 * This will return the size of the heap
	 * @return the size
	 */
	public int size()
	{
		return position-1;
	}
	
	/**
	 * This will check if the heap is empty
	 * @return true if the heap is empty, otherwise false
	 */
	public boolean isEmpty()
	{
		return position == 1;
	}
	
	/**
	 * This will print the heap
	 * @return a string containing the integers in the heap
	 */
	public String print()
	{
		String str = "";
		for(int element : arr)
		{
			str += element + " ";
		}
		
		return str;
	}
}
