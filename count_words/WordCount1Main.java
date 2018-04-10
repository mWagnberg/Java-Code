package mw222uu_assign3.count_words;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class WordCount1Main 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		File file = new File("C:\\Users\\Michael\\Desktop\\words.txt");
		Scanner in = new Scanner(file);
		Set<Word> hashSet = new HashSet<Word>();
		Set<Word> treeSet = new TreeSet<Word>();
		
		while(in.hasNext())
		{
			String str = in.next();
			Word word = new Word(str);
			
			hashSet.add(word);
			treeSet.add(word);
		}
		
		System.out.println("Among of words in HashSet: "+hashSet.size()+"\n");
		System.out.println("Word in alphabetic order in TreeSet:");
		Iterator<Word> it = treeSet.iterator();
		while(it.hasNext())
		System.out.println(it.next());
		
		in.close();
	}
}
