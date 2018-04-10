package mw222uu_assign1.intCollection;

public class ArrayIntStack extends AbstractIntCollection implements IntStack
{
	/**
	 * Add integer at top of stack
	 * @param n is the integer which will be added
	 */
	@Override
	public void push(int n) 
	{
		if(size == values.length)
		{
			this.resize();
		}
		
		values[size] = n;
		size ++;
	}

	/**
	 * Returns and removes integer at top of stack
	 * @return the integer on the top of the stack
	 */
	@Override
	public int pop() throws IndexOutOfBoundsException 
	{
		if(!this.isEmpty())
		{
			int check = this.peek();
			size --;
			return check;
			
		}
		else
		{
			return 0;			
		}
	}

	/**
	 * Returns without removing integer at top of stack
	 * @return the integer on the top of the stack
	 */
	@Override
	public int peek() throws IndexOutOfBoundsException 
	{
		if(size > 0)
		{
			return values[0];
		}
		else
		{
			return 0;			
		}
	}
	
	/**
	 * Create a new array that's twice as long as the original array
	 */
	protected void resize()
	{
		size = 2 * size;
		int[] arr = new int[size];
		
		for(int i = 0; i < values.length; i++)
		{
			arr[i] = values[i];
		}
		
		values = arr;
	}
}
