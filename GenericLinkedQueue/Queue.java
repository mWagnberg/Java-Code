package mw222uu_assign4.GenericLinkedQueue;

import java.util.Iterator;

public interface Queue<T> 
{
	/**
	 * This will return the current size
	 * @return current queue size
	 */
	public int size();
	/**
	 * This will check if the queue is empty
	 * @return true if queue is empty, otherwise false
	 */
	public boolean isEmpty();
	
	/**
	 * This will add an element at the end of the queue
	 * @param element is the element which will be added
	 */
	public void enqueue(T element);
	
	/**
	 * This will return and remove the first element of the queue
	 * @return the first element
	 */
	public T dequeue();
	
	/**
	 * This will return the first element of the queue without removing
	 * @return the first element
	 */
	public T first();
	
	/**
	 * This will return the last element of the queue without removing
	 * @return the last element
	 */
	public T last();
	
	/**
	 * This will return a string representing of the queue content
	 * @return a string
	 */
	public String toString();
	
	/**
	 * This will iterate over the element in the queue
	 * Also check if there's a next element in queue
	 * and return the next element in queue
	 */
	public Iterator<T> iterator();
}
