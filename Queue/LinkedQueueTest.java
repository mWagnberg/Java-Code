package mw222uu_assign2.Queue;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;
/**
 * 
 * @author MichaelWagnberg
 * This JUnittest will check all the methods in LinkedQueue
 */
public class LinkedQueueTest 
{
	@Test
	public void testSize() 
	{
		LinkedQueue lq = new LinkedQueue();
		lq.enqueue(5);
		assertEquals(1, lq.size());
		lq.enqueue(5);
		lq.enqueue(5);
		lq.enqueue(5);
		assertEquals(4, lq.size());
		lq.dequeue();
		lq.dequeue();
		lq.dequeue();
		lq.dequeue();
		assertEquals(0, lq.size());
	}
	
	@Test
	public void testIsEmpty()
	{
		LinkedQueue lq = new LinkedQueue();
		lq.enqueue(5);
		assertEquals(false, lq.isEmpty());
		lq.dequeue();
		assertEquals(true, lq.isEmpty());
	}
	
	@Test
	public void testEnqueue()
	{
		LinkedQueue lq = new LinkedQueue();
		lq.enqueue(7);
		assertEquals(7, lq.last());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testDequeue()
	{
		LinkedQueue lq = new LinkedQueue();
		lq.enqueue(5);
		lq.enqueue(9);
		lq.dequeue();
		assertEquals(1,lq.size());
		lq.dequeue();
		assertEquals(0,lq.size());
		lq.dequeue();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testFirst()
	{
		LinkedQueue lq = new LinkedQueue();
		lq.enqueue(4);
		assertEquals(4, lq.first());
		lq.enqueue(8);
		assertEquals(4, lq.first());
		lq.dequeue();
		lq.dequeue();
		lq.dequeue();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testLast()
	{
		LinkedQueue lq = new LinkedQueue();
		lq.enqueue(4);
		assertEquals(4, lq.last());
		lq.enqueue(8);
		assertEquals(8, lq.last());
		lq.dequeue();
		lq.dequeue();
		lq.dequeue();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testToString()
	{
		LinkedQueue lq = new LinkedQueue();
		lq.enqueue(4);
		assertEquals(4 + " ", lq.toString());
		lq.enqueue("hej");
		assertEquals(4 + " hej ", lq.toString());
		lq.dequeue();
		lq.dequeue();
		lq.dequeue();
	}
	
	@Test
	public void testIterator()
	{
		LinkedQueue lq = new LinkedQueue();
		lq.enqueue(4);
		lq.enqueue("hej");
		lq.enqueue(6);
		assertEquals(4, lq.iterator().next());
		lq.dequeue();
		assertEquals("hej", lq.iterator().next());
		assertEquals(true, lq.iterator().hasNext());
		lq.dequeue();
		lq.dequeue();
		assertEquals(false, lq.iterator().hasNext());
	}
}
