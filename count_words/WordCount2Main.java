package mw222uu_assign3.count_words;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordCount2Main 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		File file = new File("C:\\Users\\Michael\\Desktop\\words.txt");
		Scanner in = new Scanner(file);
		HashWordSet hashSet = new HashWordSet();
		TreeWordSet treeSet = new TreeWordSet();
		
		while(in.hasNext())
		{
			String str = in.next();
			Word word = new Word(str);
			
			hashSet.add(word);
			treeSet.add(word);
		}
		
		System.out.println("Among of words in HashSet: "+hashSet.size()+"\n");
		System.out.println("Word in alphabetic order in TreeSet:");
		for(int i = 0; i < treeSet.size(); i++)
		{
			System.out.println(treeSet.iterator().next());	
		}

		System.out.println("Has next: "+treeSet.iterator().hasNext());

		in.close();
	}
}
