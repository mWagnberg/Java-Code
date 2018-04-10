package mw222uu_assign2.Queue;
/**
 * 
 * @author MichaelWagnberg
 * This program will check if all the methods in LinkedQueue is correct implemented
 */
public class QueueMain 
{
	public static void main(String[] args)
	{
		LinkedQueue queue = new LinkedQueue();
		System.out.println("Enque: hej, 5 and 456");
		queue.enqueue("hej");
		queue.enqueue(5);
		queue.enqueue(456);
		System.out.println("Size: "+queue.size());
		System.out.println("Queue: "+queue.toString());
		System.out.println("Queue empty?: "+queue.isEmpty());
		System.out.println("First element: "+queue.first());
		System.out.println("Last element: "+queue.last());
		
		System.out.println("Dequeue: "+queue.dequeue());
		System.out.println("First: "+queue.first());
		System.out.println("Last: "+queue.last());
		System.out.println("Size: "+queue.size());
		
		System.out.println("Queue: "+queue.toString());
		
		System.out.println("Iterator - next: "+queue.iterator().next());
		System.out.println("Iterator - hasNext: "+queue.iterator().hasNext());
		
	}
	
	
}
