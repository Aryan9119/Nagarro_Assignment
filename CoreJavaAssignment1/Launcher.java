/**
 * The class Launcher contains several useful class fields
 * and methods which has been called.
 *
 * Among the facilities provided by the class
 * are input and output;
 * iterations to add one or more Car details;
 * access to externally defined properties and environment
 * variables; a means of loading packages and libraries;
 *
 */
package CoreJavaAssignment1;
import java.util.Scanner;
public class Launcher {
	/* Takes an input via Scanner for entering the car details.
	* Creation of objects for externally defined classes.
    */

	public static void main(String[] args) {
		
		String que=null;
	    Scanner sc= new Scanner(System.in);
	    InputProcessor in=new InputProcessor();
	    OutputProcessor op=new OutputProcessor();
	    /*try and catch statement is used to catch	     * the exceptions thrown by the error
	     */
	    try {
	    	do{	 
	    		/* Object 'in' calls for InputProcessor class
	    		* for entering the car details.
	    		* Object 'op' calls for OutputProcessor class 
	    		* for printing the Car and Insurance premium details.	    	    */
	    		in.GetInput();
	    		op.GiveOutput(in);
	    		System.out.println("Do you want to add other car: yes or no?");
				que=sc.next();					        		        
		    }while(que.compareTo("yes")==0);
	    }
	    catch(Exception e) {
	    	System.out.println(e.getMessage()+" error occured");
	    }	  
	    sc.close();
	}
}
