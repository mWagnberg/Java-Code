package mw222uu_assign3.count_words;

public class WordMain 
{
	public static void main(String[] args)
	{
		Word w = new Word("hej");
		Word w2 = new Word("hEJ");
		
		System.out.println(w.toString() + w2.toString());
		
		System.out.println(w.hashCode());
		System.out.println(w2.hashCode());
		System.out.println(w.equals("HEj"));
		
		if(w.compareTo(w2) == 0)
			System.out.println("Lika");
		
		if(w.compareTo(w2) > 0)
			System.out.println("bajs");
		
		if(w.compareTo(w2) < 0)
			System.out.println("jhf");
	}
}
