package mw222uu_assign4;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryIntHeapTest 
{
	@Test
	public void InsertTest() 
	{
		BinaryIntHeap bih = new BinaryIntHeap();
		bih.insert(12);
		bih.insert(2);
		bih.insert(7);
		bih.insert(8);
		bih.insert(1);
		bih.insert(21);
		bih.insert(4);
		String str = "0 21 8 12 2 1 7 4 0 0 ";
		assertEquals(str, bih.print());
	}
	
	@Test
	public void isEmptyTest()
	{
		BinaryIntHeap bih = new BinaryIntHeap();
		assertEquals(true, bih.isEmpty());
		bih.insert(3);
		assertEquals(false, bih.isEmpty());
	}
	
	@Test
	public void pullHighestTest()
	{
		BinaryIntHeap bih = new BinaryIntHeap();
		bih.insert(4);
		assertEquals(4, bih.pullHighest());
		assertEquals(true, bih.isEmpty());
		bih.insert(4);
		bih.insert(7);
		bih.insert(45);
		assertEquals(45, bih.pullHighest());
		assertEquals(7, bih.pullHighest());
	}
	
	@Test
	public void sizeTest()
	{
		BinaryIntHeap bih = new BinaryIntHeap();
		assertEquals(0, bih.size());
		bih.insert(4);
		assertEquals(1, bih.size());

		for(int i = 0; i < 8; i++)
		{
			bih.insert(i);
		}

		assertEquals(9, bih.size());
	}

}
