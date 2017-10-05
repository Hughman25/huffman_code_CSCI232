 /* huffmanCode class for Huffman Code Lab 1 for CSCI 232
  * 
  * Authors: Michael Pollard & Hugh Jackovich
  */

import java.util.Scanner;

public class huffmanCode {
	
	private char[] unique, value;
	private int[] count, weight;
	private String message = "";
	
	public huffmanCode()
	{
		System.out.println("Enter in a body of text that will be coded: terminate with an ending $, Spaces will be coded as [ and new lines as |");
		Scanner input = new Scanner(System.in);
		while(input.hasNextLine())				//will continue reading lines until termination
		{
			message = message + "\n" + input.nextLine();
			if(message.endsWith("$"))			//when message is ended with $, loop will be terminated
			{
				if(message.length() < 3)
				{
					System.out.println("Arguement too small, try again");
					Driver.main(null);
				}
				message = message.substring(1, message.length() - 1); //For some reason kept getting a "\n" infront of message
				break;													//this removes that and the terminating $ character
			}
		}
		message = message.replaceAll("\\n","|"); //replaces new lines with |
		message = message.replaceAll("\\s","["); //replaces spaces with [
		setMessage(message);
	}

	private void setMessage(String message) //this method counts up unique characters & their frequency into two arrays
	{
		unique = new char[message.length()];
		count = new int[message.length()];
		
		for(int i = 0; i < message.length(); i++)
		{
			char temp = message.charAt(i);
			if(unique[0] == 0)
			{
				unique[0] = temp;
			}
			for(int g = 0; g < unique.length; g++)
			{
				if(unique[g] == 0) 
				{
					unique[g] = temp;
					count[g] = 1;
					break;
				}
				if(temp == (unique[g]))
				{
					count[g]++;
					break;
				}
			}	
		}
		resizeArrays();
	}
	
	public void resizeArrays() //resizing the arrays to be more fitting
	{
		int x;
		for(x = 0; x < count.length; x++)
		{
			if(count[x] == 0)
			{
				break;
			}
		}
		value = new char[x];
		weight = new int[x];
		for(int z = 0; z < value.length; z++)
		{
			value[z] = unique[z];
			weight[z] = count[z];
		}
	}
	public char[] getChar()
	{
		return value;
	}
	public int[] getInt()
	{
		return weight;
	}
	public String getMessage()
	{
		return message;
	}
}
