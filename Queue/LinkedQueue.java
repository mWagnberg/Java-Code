package mw222uu_assign2.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 
 * @author MichaelWagnberg
 * This program will set the methods from QueueInterface. The methods is for to build a queue
 * and iterate over it
 */
public class LinkedQueue implements QueueInterface
{
	// Create fields
	private int size;
	private Node head;
	private Node tail;

	// Create a constructor and initialize the fields
	public LinkedQueue() 
	{
		this.size = 0;
		this.head = null;
		this.tail = null;
	}
	
	@Override
	public int size() 
	{
		return size;
	}

	@Override
	public boolean isEmpty() 
	{
		return size == 0;
	}

	@Override
	public void enqueue(Object element) 
	{
		Node node = new Node(element);
		
		if(isEmpty())
		{
			this.head = node;
			this.tail = this.head;
		}
		else
		{
			this.tail.next = node;
		}
		this.tail = node;

		size ++;
	}

	@Override
	public Object dequeue() 
	{	
		if(isEmpty())
		{
			throw new NoSuchElementException("No element in queue");
		}
		
		Object object = this.head.value;
		
		if(this.tail == this.head)
		{
			this.tail = null;
		}
		
		this.head = head.next;
		size --;
		return object;
	}

	@Override
	public Object first() 
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("No element in queue");
		}
		
		Object object = this.head.value;

		return object;
	}

	@Override
	public Object last() 
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("No element in queue");
		}
		
		Object object = this.tail.value;
		
		return object;
	}
	
	@Override
	public String toString()
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("No element in queue");
		}
		
		String str = "";
		Iterator it = iterator();
		
		while(it.hasNext())
		{
			str += it.next() + " ";
		}
		
		return str;
	}

	@Override
	public Iterator iterator() 
	{
		iteratorNode it = new iteratorNode();
		return it;
	}
	
	private class Node
	{
		Object value;
		Node next = null;
		
		Node(Object v)
		{
			value = v;
		}
	}
	
	private class iteratorNode implements Iterator
	{
		private Node current;
		
		public iteratorNode()
		{
			this.current = head;
		}
		
		@Override
		public boolean hasNext() 
		{	
			return this.current != null;
		}

		@Override
		public Object next() 
		{
			Object temp = current.value;;
			current = current.next;
			return temp;
		}
		
	}

}
