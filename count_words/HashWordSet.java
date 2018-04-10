package mw222uu_assign3.count_words;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 
 * @author MichaelWagnberg
 * This program will contain several methods that handle an array with nodes with
 * my HashWordSet
 */
public class HashWordSet implements WordSet
{
	// Create fields
	private Node[] buckets;
	private int size;

	// Create a constructor holding the fields
	public HashWordSet() 
	{
		this.buckets = new Node[4];
		this.size = 0;
	}
	
	/**
	 * This will iterate over the array
	 */
	@Override
	public Iterator iterator() 
	{
		hashIterator it = new hashIterator();
		return it;
	}

	/**
	 * This will add a word if the word does'nt exist already
	 * If the array is full, then resize it
	 * @param word is the word that will be added
	 */
	@Override
	public void add(Word word) 
	{
		if(buckets.length == size)
		{
			rehash();
		}
		
		int n = word.hashCode();
		n %= buckets.length;
		
		if(contains(word) == false)
		{
			Node node = new Node(word);
			node.next = buckets[n];
			buckets[n] = node;
			size ++;
		}
		
	}
	
	/**
	 * This will resize the array
	 */
	private void rehash()
	{
		Node[] temp = buckets;
		buckets = new Node[2 * temp.length];
		size = 0;
		
		for(Node n : temp)
		{
			if(n == null)
			{
				continue;
			}
			while(n != null)
			{
				add(n.value);
				n = n.next;
			}
		}
	}

	/**
	 * This will check if a word contains in the array
	 * @param word is the word that will be checked
	 * @return true if the word contains in the array,
	 * otherwise false
	 */
	@Override
	public boolean contains(Word word) 
	{
		int n = word.hashCode();
		n %= buckets.length;
		
		Node current = buckets[n];
		while(current != null)
		{
			if(current.value.equals(word))
			{
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * This will show the current size
	 * @return the current size
	 */
	@Override
	public int size() 
	{
		return size;
	}
	
	/**
	 * This will print out a string of the array
	 * @return a string containing all the elements in the array
	 */
	@Override
	public String toString()
	{
		String str = "";
		
		for(int i = 0; i < buckets.length; i++)
		{
			str += buckets[i] + " ";
		}
		
		return str;
	}

	private class Node
	{
		// Create fields
		public Word value;
		public Node next;
		
		// Create a constructor holding the fields
		public Node(Word str)
		{
			this.value = str;
		}
	}
	
	/* I have looked up this code in the Horstman-book 
	 * It does'nt work as it should work 
	 * It prints out the same word over and over again*/
	private class hashIterator implements Iterator
	{
		// Create a field
		private int bucket;
		private Node current;
		
		// Create a constructor holding the field
		public hashIterator()
		{
			this.bucket = -1;
			this.current = null;
		}
		
		@Override
		public boolean hasNext() 
		{
			if(current != null && current.next != null)
			{
				return true;
			}
			for(int i = bucket + 1; i < buckets.length; i++)
			{
				if(buckets[i] != null)
				{
					return true;
				}
			}
			return false;
		}

		@Override
		public Word next() 
		{
			if(current != null && current.next != null)
			{
				current = current.next;
			}
			else
			{
				do
				{
					bucket++;
					if(bucket == buckets.length)
					{
						throw new NoSuchElementException();
					}
					current = buckets[bucket];
				}
				while(current == null);
			}
			return current.value;
		}
		
	}
}
