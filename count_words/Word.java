package mw222uu_assign3.count_words;

public class Word implements Comparable<Word>
{
	// Create a field
	private String word;
	
	// Create a constructor to initialize the field
	public Word(String str) 
	{
		this.word = str;
	}
	
	/**
	 * This will print out a String
	 * @return a String
	 */
	@Override
	public String toString()
	{
		return this.word;
	}
	
	/**
	 * This will compute a String into a hash code
	 * Make all the letters in the String to lower case
	 * @return an integer that will represent the hash code
	 */
	@Override
	public int hashCode()
	{
		String str = this.word.toLowerCase();
		int n = str.hashCode();
		
		if(n < 0)
		{
			return n * -1;
		}
		
		return n;
	}
	
	/**
	 * This will check if two word are equal
	 * @param other is the other word that will be checked to
	 * @return true if the word are equal, otherwise false
	 */
	@Override
	public boolean equals(Object other)
	{
		String str = "";
		Word o;
		if(other instanceof Word)
		{
			o = (Word) other;
			str = o.toString();
		}
		
		this.word = this.word.toLowerCase();
		str = str.toLowerCase();
		
		return this.word.compareTo(str) == 0;
	}
	
	/**
	 * This will compare two words lexicographically
	 * @param w is the word that will be compared
	 * @return an integer that represent the comparison
	 */
	@Override
	public int compareTo(Word w) 
	{
		w.word = w.word.toLowerCase();
		this.word = this.word.toLowerCase();
		
		return this.word.compareTo(w.word);
	}

}
