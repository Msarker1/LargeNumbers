/**
 * The Project1 class has two LargeNumbers N1 and N2 that are being fed in nodes, from each read line from the "datafile.txt". It then sends
 * the N1 and N2 to the sum and product method of the LargeNumbers class. Afterwards it prints the sum and product of the two LargeNumbers on to 
 * a "results.txt" which is located in the project folder. Within each read line the split function splits each value with a "," then adds it to a array.
 * From that array, it adds the values in reverse to the LargeNumbers(N1,N2) to add and multiply. The values are being added alternating from N1
 * and N2 so the first read line will add values to the First LargeNumber N1 and the second read line will add the values to the N2. The iteration 
 * also skips the every third line because its empty. The output LargeNumber is used to call the sum and product methods from LargeNumbers because 
 * the sum and product method is not allowed to be in main.
 * 
 * Name: Mohammad Sarker 
 * Project 1 
 * Date:4/2/2017
 * Class 313
 * 
 * The Project1
 */
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

public class Project1 {
	static LargeNumbers N1 = new LargeNumbers(); 	// LargeNumbers 1
	static LargeNumbers N2 = new LargeNumbers();	// LargeNumbers 2 
	static LargeNumbers output = new LargeNumbers();// LargeNumbers used to access sum and product methods
	public static void main(String [] args){
		FileReader thefile;				
		BufferedReader infile;
		String oneLine;
		boolean b=false;
		try{	
			FileOutputStream f = new FileOutputStream("results.txt"); 	// path to output file located in project folder
			PrintStream p = new PrintStream(f);
			System.setOut(p); 	// sets all system.out to results.txt instead of console

			thefile=new FileReader("datafile.txt");	// name of test data file
			infile=new BufferedReader(thefile);
			int c=0;		/// counter to skip every third line
			while((oneLine=infile.readLine())!=null){
				c++;
				if(c%3==0)			// skips every third line because its blank
					continue;
				System.out.println(oneLine);
				String Numbers[]=oneLine.split(",");	// splits numbers into parts
				for(int i=Numbers.length-1;i>=0;i--){	// Adds the nodes from each line to particular linked list
					if(b==false){									
						N1.addLast(Integer.parseInt(Numbers[i]));	
						
					}
					else{
						
						N2.addLast(Integer.parseInt(Numbers[i]));
				}}
				if(b==true||b==false){	// makes sure to have numbers of different lines in different linked list
					if(b==true) {	// each iteration it will be either true or false thus allowing for a different LargeNumbers
						b=false; 	// to add nodes to each time
					}
					else
						b=true;
				}
				output.sum(N1, N2); // calls sum, and sends in the pair N1 and N2 to be added
				output.product(N1,N2);// calls product and sends in the pair N1 and N2 to be added
				}
		}
		catch(Exception e){System.out.println(e);
		}
	}
}