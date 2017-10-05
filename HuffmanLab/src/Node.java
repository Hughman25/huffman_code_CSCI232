 /* Node class for Huffman Code Lab 1 for CSCI 232
  * 
  * Authors: Michael Pollard & Hugh Jackovich
  */

//Most of this class was acquired in Lab from Killian Smith (TA)
class Node  implements Comparable<Node>
{
	private char value;
	private int weight;
	private Node left;
	private Node right;
	
	Node(char c, int w) //first constructor
	{
		this.value = c;
		this.weight = w;
		this.left = null;
		this.right = null;
	}
	Node(Node l, Node r) //second constructor
	{
		this.value = '\u0000';
		this.weight = r.weight + l.weight;
		this.left = l;
		this.right = r;
	}
	public int compareTo(Node N)
	{
		return(this.weight-N.weight);
	}
	public Node leftChild()
	{
		return left;
	}
	public Node rightChild()
	{
		return right;
	}
	public char getValue()
	{
		return value;
	}
	public int getWeight()
	{
		return weight;
	}
}