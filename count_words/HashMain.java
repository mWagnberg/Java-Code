package mw222uu_assign3.count_words;

public class HashMain 
{
	public static void main(String[] args)
	{
		HashWordSet hash = new HashWordSet();
		Word w = new Word("hej");
		Word w2 = new Word("hej");
		Word w3 = new Word("heja");
		Word w4 = new Word("hejsan");
		Word w5 = new Word("hejkhaj");
		Word w6 = new Word("hejf");
		
		hash.add(w);
		hash.add(w);
		hash.add(w2);
		hash.add(w3);
		hash.add(w4);
		hash.add(w5);
		hash.add(w6);
		
		System.out.println(hash.size());
		System.out.println(hash.contains(w2));
		System.out.println(hash.toString());
	}
}
