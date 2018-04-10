package mw222uu_assign4.GenericLinkedQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Queue<T>
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
	public void enqueue(T element) 
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
	public T dequeue() 
	{	
		if(isEmpty())
		{
			throw new NoSuchElementException("No element in queue");
		}
		
		T object = this.head.value;
			
		if(this.tail == this.head)
		{
			this.tail = null;
		}
		
		this.head = head.next;
		size --;
		return object;
	}

	@Override
	public T first() 
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("No element in queue");
		}
		
		T object = this.head.value;

		return object;
	}

	@Override
	public T last() 
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("No element in queue");
		}
		
		T object = this.tail.value;
		
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
		Iterator<T> it = iterator();
		
		while(it.hasNext())
		{
			str += it.next() + " ";
		}
		
		return str;
	}

	@Override
	public Iterator<T> iterator() 
	{
		iteratorNode it = new iteratorNode();
		if(isEmpty())
		{
			throw new NoSuchElementException("No element in queue");
		}
		return it;
	}

	// This private inner-class contains the Node-class
	private class Node
	{
		// Create fields
		T value;
		Node next = null;
		
		// Create a constructor holding the fields
		Node(T v)
		{
			value = v;
		}
	}
	
	// This private inner-class contains the iterator-class
	private class iteratorNode implements Iterator<T>
	{
		// Create fields
		private Node current;
		
		// Create a constructor holding the fields
		public iteratorNode()
		{
			this.current = head;
		}
		
		/**
		 * This will check if the queue has element in it
		 * @return true if the queue has an element, otherwise false
		 */
		@Override
		public boolean hasNext() 
		{	
			return this.current != null;
		}

		/**
		 * This will return the next T (generic object) in queue.
		 * @return next T in queue
		 */
		@Override
		public T next() 
		{
			T temp = current.value;;
			current = current.next;
			return temp;
		}
	}
}
