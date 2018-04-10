package mw222uu_assign4.GenericLinkedQueue;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class LinkedQueueTest {

	@Test
	public void testSize() 
	{
		LinkedQueue<String> lq = new LinkedQueue<>();
		lq.enqueue("hej");
		assertEquals(1, lq.size());
		lq.enqueue("då");
		lq.enqueue("hejsan");
		lq.enqueue("kalas");
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
		LinkedQueue<Integer> lq = new LinkedQueue<>();
		lq.enqueue(7);
		assertEquals(false, lq.isEmpty());
		lq.dequeue();
		assertEquals(true, lq.isEmpty());
	}
	
	@Test
	public void testEnqueue()
	{
		LinkedQueue<String> lq = new LinkedQueue<>();
		lq.enqueue("hej");
		assertEquals("hej", lq.first());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testDequeue()
	{
		LinkedQueue<Integer> lq = new LinkedQueue<>();
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
		LinkedQueue<String> lq = new LinkedQueue<>();
		lq.enqueue("Hej");
		assertEquals("Hej", lq.first());
		lq.enqueue("då");
		assertEquals("Hej", lq.first());
		lq.dequeue();
		lq.dequeue();
		lq.dequeue();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testLast()
	{
		LinkedQueue<Integer> lq = new LinkedQueue<>();
		lq.enqueue(4);
		assertEquals(new Integer(4), lq.last());
		lq.enqueue(8);
		assertEquals(new Integer(8), lq.last());
		lq.dequeue();
		lq.dequeue();
		lq.dequeue();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testToString()
	{
		LinkedQueue<Integer> lq = new LinkedQueue<>();
		LinkedQueue<String> lq2 = new LinkedQueue<>();
		lq.enqueue(4);
		assertEquals(4 + " ", lq.toString());
		lq2.enqueue("hej");
		assertEquals("hej ", lq2.toString());
		assertEquals(4 + " hej ", lq.toString() + lq2.toString());
		lq.dequeue();
		lq.dequeue();
		lq.dequeue();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testIterator()
	{
		LinkedQueue<Integer> lq = new LinkedQueue<>();
		LinkedQueue<String> lq2 = new LinkedQueue<>();
		lq.enqueue(4);
		lq.enqueue(6);
		assertEquals(new Integer(4), lq.iterator().next());
		lq.dequeue();
		assertEquals(new Integer(6), lq.iterator().next());
		assertEquals(true, lq.iterator().hasNext());
		lq.dequeue();
		lq2.enqueue("Hej");
		lq2.enqueue("Då");
		assertEquals("Hej", lq2.iterator().next());
		lq2.dequeue();
		assertEquals("Då", lq2.iterator().next());
		lq2.dequeue();
		lq2.dequeue();
		lq2.dequeue();
		assertEquals(false, lq.iterator().hasNext());
	}

}
