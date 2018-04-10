package mw222uu_assign1.intCollection;

public class ArrayIntList extends AbstractIntCollection implements IntList
{
	/**
	 * Add integer n to the end of the list
	 * @param n the integer which will be added
	 */
	@Override
	public void add(int n) 
	{
		values[size] = n;
		size ++;
	}

	/**
	 * Inserts integer n at position index. Shifts the element currently at that 
	 * position (if any) and any subsequent elements to the right
	 * @param n the integer which will be added
	 * @param index the index which the integer will be added to
	 */
	@Override
	public void addAt(int n, int index) throws IndexOutOfBoundsException 
	{
		if(index > values.length)
		{
			resize();
		}
		values[index] = n;
	}

	/**
	 * Remove integer at position index. Creates a new array with the length -1 compare
	 * to the original array, and then move all the integers one step to the left
	 * @param index removes the integer on that index
	 */
	@Override
	public void remove(int index) throws IndexOutOfBoundsException 
	{
		int pos = values[index-1];
		int[] arr = new int[values.length - 1];
		
		for(int i = pos; i < arr.length; i++)
		{
			values[i] = values[i + 1];
		}
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = values[i];
		}
		values = arr;
	}
	
	/**
	 * Get integer at position index
	 * @param index the position in the array
	 * @return the integer on index index
	 */
	@Override
	public int get(int index) throws IndexOutOfBoundsException 
	{
		return values[index];
	}

	/**
	 * Find position of integer n, otherwise return -1 
	 * @param n is the integer you search for
	 * @return the position of n or -1 if the integer isn't in the array
	 */
	@Override
	public int indexOf(int n) 
	{
		for(int i = 0; i < values.length; i++)
		{	
			if(n == get(values[i]))
			{
				return values[i];
			}
		}
		return -1;
	}
}
