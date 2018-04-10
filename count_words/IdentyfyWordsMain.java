package mw222uu_assign3.count_words;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * 
 * @author MichaelWagnberg
 * This program will read a file. Then it takes out the letters and the whitespace from the text
 * and put the new text in another file
 */
public class IdentyfyWordsMain 
{

	public static void main(String[] args) throws FileNotFoundException
	{
		/* Create a File-object and a PrintWriter-object
		 * Set the file into the File-object that you want to modify
		 * Set the new file into the PrintWriter-object that you want to save that file */
		File file = new File("C:\\Users\\Michael\\Desktop\\ComputerScience.txt");
		PrintWriter print = new PrintWriter("C:\\Users\\Michael\\Desktop\\words.txt");
		Scanner in = new Scanner(file);
		
		String str = "";
		
		/* This loops through the text in the file 
		 * In String str a line from file i saved and then the for-loop loops through the String
		 * and takes out letters and whitespace */
		while(in.hasNextLine())
		{
			str = in.nextLine();
			
			for(int i = 0; i < str.length(); i++)
			{
				char word = str.charAt(i);
				
				if(Character.isLetter(word) || Character.isWhitespace(word))
				{
					print.print(word);
				}
			}
			if(str.length() > 0)
			{
				print.println();				
			}
		}
		
		in.close();
		print.close();
	}
}
