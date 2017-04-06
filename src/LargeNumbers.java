/** The LargeNumbers class describes all the procedures that the LargeNumbers can allow. It has addLast and addFirst methods which allows to add 
 * nodes to the beginning and end of a LargeNumbers. The isEmpty tells us if the List is empty. The sum method checks to see if each LargeNumbers are not 
 * empty so that it can begin to iterate through both list and add the sum of each node into a solution LargeNumber list. The product method
 * does something similar, it also iterates through both LargeNumbers and multiplies each node of a particular LargeNumbers to every single node of the 
 * other LargeNumbers. Then it stores that value on to a accumulator and continues to iterate until both LargeNumbers have been iterated through.
 * The product method also calls the print method to print the sum and product of the two LargeNumbers. The makeSame method is used to copy the LargeNumbers
 * node into a new LargeNumbers because we do not want to change the original values of the pair of LargeNumbers. The sameAmount method makes each Large
 * Numbers have the same amount of nodes. The flipList method simply reverses the LargeNumbers from tail to head. The print method prints each node
 * of the solution LargeNumber list and appends 0's where needed.
 * 
 * Name: Mohammad Sarker 
 * Project 1 
 * Date:4/2/2017
 * Class 313
 * 
 */
public class LargeNumbers {
	private Node First = null;	// head
	private Node Last = null;	// tail 
	private int Size = 0;
	
	public void addLast( int data){	// adds nodes to back of linked list
		Node n = new Node( data, null);	// new node that will be the last node
		if(isEmpty()){	// if the list is empty the first(head) is the last node
			First = n;
		}
		else
			Last.setNext(n);	// else last points to n
		Last = n;	// then the last node becomes n
		Size++;	// size is increased by 1
	}
	public void addFirst(int data){	// adds nodes to front of linked list
		Node n = new Node(data,First);		// new node to be added in front also the next field points to first
		First = n;	// first simply becomes the new node;
		Size++;	// size is increased by 1
	}
	
	public boolean isEmpty(){	// checks to see if linked list is empty
		return Size == 0;
	}


	public LargeNumbers sum(LargeNumbers N1, LargeNumbers N2){	// adds the two linked lists nodes together and returns the solution
		int carry = 0,sum = 0;	// carry and sum are used for holding the carry over value and sum for the sum of two nodes
		boolean full = false;	// checks to see if N1 and N2 are not empty
		LargeNumbers Num1 = new LargeNumbers();		// holds copy value of N1
		LargeNumbers Num2 = new LargeNumbers();		// holds copy value of N2
		LargeNumbers sol = new LargeNumbers();		// holds the sum of the two LargeNumbers
		
		if(N1.First!=null && N2.First!=null){; // makes them have same amount of nodes only when both list has nodes
			makeSame(N1,Num1);	// copies N1 into Num1
			makeSame(N2,Num2);  // copies N2 into Num2
			sameAmount(Num1,Num2);	// makes both list have same amount of nodes
			Node n1=Num1.First;	// sets n1 to head of Num1
			Node n2=Num2.First;	// sets n2 to head of Num2
			full = true;	//  its true to ensure both LargeNumbers are not empty
			
			while(full){
				if(carry+n1.getData()+n2.getData()>999){	// if the sum of the two nodes are greater than 999 
					sum=carry+n1.getData()+n2.getData()-1000; //subtract 1000 to get remainder and add to solution list
					sol.addLast(sum);
					carry=1;	// carry will be set to 1 because when sum is greater than 999 and each node can only have 999 there must be a carry over
				}
				else{
					sum=carry+n1.getData()+n2.getData();	// else if value is not greater than 999 simply add to solution list
					sol.addLast(sum);
					carry=0;
				}
				if(n1.getNext()==null && n2.getNext()==null)	// when n1 and n2 are null that means iteration is complete thus end loop
					full=false;
			
				n1=n1.getNext();	// while the loop is iterating this allows to go to each different node and add them together
				n2=n2.getNext();	
			}
		}
		return sol;	// after loop is complete return the solution LargeNumbers
	}
	public void product(LargeNumbers N1,LargeNumbers N2){	// multiplies two LargeNumbers and prints the solution
		int count = 0;	// counter to see if a 0 needs to be added 
		int carry = 0;	// carry for the multiplication 
		boolean indent = false;	// used for adding 0 in front of accumulator list
		LargeNumbers partialProduct = new LargeNumbers();	// contains the current values of each nodes being multiplied
		LargeNumbers accumulator = new LargeNumbers();	// holds all the values of the multiplication by adding it to the list each iteration
		accumulator.addLast(0);	// to make sure accumulator is not empty for program to work
		
		if(N1.First != null && N2.First != null){	// if both N1 and N2 are not empty then iterate through them and add the product of each node
			Node n1 = N1.First;
			Node n2 = N2.First;
			
			while(n2 != null){	// while n2 is not empty iterate through n1
				
				while(n1 != null){	// while n1 is not empty iterate and multiply the current node of n2 with each node of n1
					int prod = (n2.getData()*n1.getData())+carry; // contains the product of the the current nodes being multiplied
					if(prod > 999){		// if the product is greater than 999 modulo the value by 1000 because each node can only have 999 value to get remainder
						partialProduct.addLast(prod % 1000);	
						carry = prod/1000; 	// the carry over value is simply the product divided by 1000;
						
						if(n1.getNext() == null){	// if there is no next node just add the remainder to the partialProduct list
							partialProduct.addLast(prod / 1000);
							accumulator=sum(partialProduct,accumulator);  // adds the values of the partialProduct with the accumulator
						}
					}
		
					else{	// if the product value is not greater than 999 just add the value to the partialProduct list
						partialProduct.addLast(prod);
						carry=0;
						if(n1.getNext() == null){	// if the next node is null just add the values to the accumulator
							accumulator = sum(partialProduct,accumulator);
						}
					}
					
					if(indent){	// this is important for adding, this makes sure each time a node is multiplied by every node of the other list 
						for(int i=0;i<count;i++) //you add a 0 to the front of list
							partialProduct.addFirst(0);
						indent = false;		// sets indent to false otherwise it would keep adding zeros to list
					}
					
					n1 = n1.getNext();	// goes to next node
				}
				
				n2 = n2.getNext(); 	// after the first node of n2 is multiplied by every other node of n1 go to next node of n2 and do the same again
				count++; // counter plus one because one iteration of outer while loop is complete
				n1=N1.First;	// resets n1 to N1 head because you need to multiply the same values with the next node of n2
				partialProduct.First = null;	// resets partialProduct values
				partialProduct.Size = 0;		// reset otherwise the partialProduct will keep adding every multiplication values
				carry = 0;	
				indent=true;
			}
			
			System.out.print("Sum:");		// once iteration is complete print the values of the sum and product
			print(sum(N1,N2));
			System.out.print("Product:");
			print(accumulator);
			
			N1.First=null;		// resets the pointers so other values are not stored with same numbers in main
			N1.Size=0;
			N2.First=null;
			N2.Size=0;
		}
	}

	private void makeSame(LargeNumbers Num1, LargeNumbers copyNum) {	// goes through both list and makes copyNum equal to Num2
		Node p = Num1.First;
		
		while(p!=null){	// iterates and adds each node of Num1 to copyNum
			copyNum.addLast(p.getData());
			p=p.getNext();
		}
	}
	public void flipList(LargeNumbers n){	// reverses the LargeNumbers
		Node current=n.First;	// sets the current node to head of LargeNumbers
		Node last=null;
		Node next=null;
		
		while(current!=null){	// while the current is not empty
			next=current.getNext();	// the next node is equal to the current next node
			current.setNext(last);	// then the current next node is set to the last node
			last=current;	// last then becomes the current node
			current=next;	// current becomes the next 
		}
		n.First=last;	// then n's head node is equal to the last node;
	}
	
	public void sameAmount(LargeNumbers N1, LargeNumbers N2){
		int low=0,high=0;
		low=N1.Size<N2.Size? N1.Size:N2.Size;	// get sizes to make them have same amount of nodes
		high=N1.Size>N2.Size? N1.Size:N2.Size;
		
		for(int i = low; i  < high; i++){	// appends 0s to make to have same amount of nodes to add
			if(N1.Size<N2.Size)
				N1.addLast(0);
			else 
				N2.addLast(0);
			}
		}

	public void print(LargeNumbers n){	// prints the nodes of the LargeNumbers
		flipList(n); // reverses the LargeNumber because the values are stored backwards
		Node p = n.First;
		for(int i = 0; i < n.Size; i++){
			if(i==0)				// skips the first node so it wont have 0's in front of most significant digit
				System.out.print(p.getData());
			else if(p.getData()<100 && p.getData()>9)	// if node is between 99 to 10 then add 0 to left of it
				System.out.print("0"+p.getData());		
			else if(p.getData()<10)
				System.out.print("00"+p.getData()); // if node is between 1 to 9 add 00 to the left
			else
				System.out.print(p.getData()); 	// if its more than 99 then just print the value
			p = p.getNext();
		}
		System.out.println();	// print line for clarity 
		
	}
}
