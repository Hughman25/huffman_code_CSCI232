 /* Driver class for Huffman Code Lab 1 for CSCI 232
  * 
  * Authors: Michael Pollard & Hugh Jackovich
  */
 
import java.util.*;

public class Driver 
{
	private static Hashtable<Character, String> hashMap;
	private static Node root;
	private static huffmanCode message;
	private static String decoded;
	private static String[] code;
	private static char[] value;
	private static int[] weight;
	private static PriorityQueue<Node> nodeQueue;
	
	public static void main(String[] args)
	{
		while(true) //runs till quit is selected
		{
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Enter the first letter of each option: enter, code, decode, show, or quit");
			Scanner input = new Scanner(System.in);
			String selection = input.nextLine();
			switch(selection)
			{
			case "e":
				System.out.println("-------------------------------------------------------------------------");
				message = new huffmanCode(); //retrieves the message to be encoded
				value = message.getChar();
				weight = message.getInt();
				
				nodeQueue = new PriorityQueue<Node>(value.length); //makes size of Queue equal to # of unique characters
				
				for(int i = 0; i < value.length; i++)
				{
					nodeQueue.add(new Node(value[i], weight[i])); //adds a newly created node into Queue
				}
				makeTree();
				break;
			case "c":
				System.out.println("-------------------------------------------------------------------------");
				enCode();
				break;
			case "d":
				System.out.println("-------------------------------------------------------------------------");
				deCode();
				break;
			case "s":
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("Root|-First-|-Second-|-Third-|Fourth-|Fifth-|"); //helps organize the layers of children
				show(root, "");
				break;
			case "q":
				System.exit(0);
			default:
				break;
			}
		}
	}
	
	public static void makeTree()
	{
		while(nodeQueue.size() != 1)
		{
			Node temp = new Node(nodeQueue.poll(), nodeQueue.poll()); //makes new Node by pulling two out of Queue
			nodeQueue.add(temp); //adds new Node back into Queue
		}
		root = nodeQueue.poll(); //sets Node to the one Node left in the Queue
	}
	
	
	private static void enCode()
	{
		hashMap = new Hashtable<Character, String>(value.length); //makes Hashtable w/ Character and String data types

		searchAux(root, "");
		code = new String[message.getMessage().length()];
		for(int y = 0; y < message.getMessage().length(); y++) //loop builds the message in binary
		{
			code[y] = hashMap.get(message.getMessage().charAt(y)); //stored in array to retain singularity
		}
		printTable();
		printCode();
	}
	
	static void deCode()
	{
		decoded = "";
		for(int i = 0; i < code.length; i++)
		{
			for(char key : hashMap.keySet()) //Java online documentation for hashtable
			{
				if(hashMap.get(key) == code[i])
				{
					if(key == '[')
					{				//Decodes using spaces instead of '['
						key = ' ';
					}
					else if(key == '|')
					{					//Decodes making a new line instead of '|'
						key = '\n';
					}
					decoded = decoded + key;
				}
			}
		}
		System.out.println(decoded);
	}
	
/* Had working recursive method that was slightly ugly, got Killian Smith's insight
 * on how to clean it up & be more efficient -- end product
 */
	private static void searchAux(Node current, String temp)
	{
		if((current.leftChild() == null) && (current.rightChild() == null)) //hits a leaf node
		{
			if(current.equals(root)) //this will give a value of "0" to a coded message comprised of 1 unique character
			{
				hashMap.put(current.getValue(), "0");
			}
			else
				hashMap.put(current.getValue(), temp);
		}
		else
		{
			searchAux(current.leftChild(), temp+"0"); //recursive call for left child
			searchAux(current.rightChild(), temp+"1"); //recursive call for right child
		}
	}
	
	private static void printCode() //prints the message back out
	{
		for(int i = 0; i < code.length; i++)
		{
			System.out.print(code[i]);
		}
		System.out.println();
	}
	
/*
 * 	Recursive method that prints tree -- Prints left node first, then right node in Preorder fashion
 * 	On screen the upper value is the left tree, the bottom value is right tree
 */
	private static void show(Node current, String temp)
	{
		if(current == root)
		{
			System.out.println(current.getWeight());
			if(value.length != 1) //prevents crash if the message is comprised of 1 unique character
			{
				show(current.leftChild(), temp);
				temp = "";
				show(current.rightChild(), temp);
			}
		}
		else
		{
			temp = "\t" + temp;
			if((current.leftChild() == null) && (current.rightChild() == null)) //hits leaf node
			{
				System.out.println(temp + current.getValue());
				return;
			}
			else
			{
				System.out.println(temp + current.getWeight());
			}
			show(current.leftChild(), temp);
			show(current.rightChild(), temp);
			temp = temp.replaceFirst("\\t", ""); //removes 1 tab space essentially
		}	
	}
	
	public static void printTable()
	{
		for(char key : hashMap.keySet()) //Java online documentation for hashtable
		{
			System.out.println("Key: " + key + " |->| Value: " + hashMap.get(key));
		}
		System.out.println();
	}
}
 