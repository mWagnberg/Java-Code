package mw222uu_assign3.count_words;

import java.util.Iterator;
/**
 * 
 * @author MichaelWagnberg
 * This program implements a Binary Search Tree.
 */
public class TreeWordSet implements WordSet
{
	// Create fields
	private Node root;
	private int size;
	private int count;
	private treeIterator iter;
	
	// Create a constructor holding the fields
	public TreeWordSet() 
	{
		this.root = null;
		size = 0;
		count = 0;
		iter = null;
	}

	/**
	 * This will call the Iterator-class
	 * @return a iterator-object
	 */
	@Override
	public Iterator iterator() 
	{
		treeIterator it = new treeIterator();
		return it;
	}

	/**
	 * This will call the add-method from the Node-class
	 * @param word is the word that will be added
	 */
	@Override
	public void add(Word word) 
	{
		if(this.root == null)
		{
			this.root = new Node(word);
			size ++;
		}
		else
		{
			root.add(word);
		}
	}

	/**
	 * This will call the contains-method from the Node-class
	 * and check if a word contains i the tree
	 * @return true if the word contains, otherwise false
	 */
	@Override
	public boolean contains(Word word) 
	{
		return root.contains(word);
	}
	
	/**
	 * This will print out the node to a String
	 * @return a String containing the node
	 */
	@Override
	public String toString()
	{
		String str = root.value.toString();
		
		return str;
	}

	/**
	 * This will print out how many nodes there is
	 * @return a integer containing the size
	 */
	@Override
	public int size() 
	{
		return this.size;
	}

	private class treeIterator implements Iterator
	{
		// Create a field
		private Word[] arr;
		
		// Create a constructor holding the field
		public treeIterator()
		{
			this.arr = new Word[size];
		}

		/**
		 * This will check if there more space in the array
		 * @return true if there is more space, otherwise false
		 */
		@Override
		public boolean hasNext() 
		{
			if(count == arr.length)
			{
				return false;
			}
			else
			{
				return true;		
			}
		}

		/**
		 * This will check the next node in the array and return it
		 * @return the next node
		 */
		@Override
		public Word next() 
		{
			root.print(arr, 0);
			Word word = arr[count];
			count++;
			return word;
		}
		
	}
	
	private class Node
	{
		// Create fields
		Word value;
		Node left = null;
		Node right = null;
		
		// Create a constructor holding the fields
		public Node(Word word)
		{
			this.value = word;
		}
		
		/**
		 * This will add a node to the array
		 * @param newWord is the word that will be added
		 */
		public void add(Word newWord)
		{
			if(newWord.compareTo(this.value) < 0)
			{
				if(left == null)
				{
					left = new Node(newWord);
					size ++;
				}
				else
				{
					left.add(newWord);
				}
			}
			else if(newWord.compareTo(this.value) > 0)
			{
				if(right == null)
				{
					right = new Node(newWord);
					size ++;
				}
				else
				{
					right.add(newWord);
				}
			}
		}
		
		/**
		 * This will check if a word is in the array
		 * @param checkWord is the word that will be checked
		 * @return true if the word contains in the array, otherwise false
		 */
		public boolean contains(Word checkWord)
		{
			if(checkWord.compareTo(this.value) < 0)
			{
				if(left == null)
				{
					return false;
				}
				else
				{
					return left.contains(checkWord);
				}
			}
			else if(checkWord.compareTo(this.value) > 0)
			{
				if(right == null)
				{
					return false;
				}
				else
				{
					return right.contains(checkWord);
				}
			}
			return true;
		}
		
		/**
		 * This method will help to iterate over the nodes
		 * @param arr is the array-input
		 * @param n is the start-point in the array
		 * @return an integer
		 */
		public int print(Word[] arr, int n)
		{
			
			if(left != null)
			{
				n = left.print(arr, n);
			}
			arr[n] = this.value;
			n ++;
			
			if(right != null)
			{
				n = right.print(arr, n);
			}
			
		return n;
		}
	}
	
}
