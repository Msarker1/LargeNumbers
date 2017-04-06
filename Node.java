/** The Node class contains Node and the int data values, it essentially describes what a node does. It has a constructor with a field of Node
 * which allows to find the next node in the data structure.
 * 
 * Name: Mohammad Sarker 
 * Project 1 
 * Date:4/2/2017
 * Class 313
 * 
 */ 
public class Node {
	private Node Next;	// The next node
	private int Data;	// The data the node holds
	
	public Node(int data, Node NextNode){	// constructor 
		this.Next = NextNode;				// sets the next node
		this.Data = data;					// sets data
	}
	
	public int getData(){		// gets data
		return Data;
	}
	
	public Node getNext(){// gets Next node
		return Next;
	}
	
	public void setNext(Node newNext){	// sets Next node
		Next = newNext;
	}
}