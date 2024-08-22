package CoreJavaAssignment1;

import java.util.Scanner;

public class InputProcessor {
	//Uses a GetInput function to take inputs and validates it respectively
	String type;
	String model;
	float price;
	String insurance;
	public void GetInput() {
		Scanner sc= new Scanner(System.in);
		CheckString check=new CheckString();
	    System.out.println("Enter car model");
	    model=sc.next();
	    /*Check whether the input model is String type or not
	     * by calling isStr function using check object
	     * for class CheckString
	     */
	    if(check.isStr(model)==false) {
	    	System.out.println("Please input a valid string");
	    	
	    }
	    else {
	    	System.out.println("Enter car type");
	        type=sc.next();
	        /*Check whether the input type is String type or not
		     * by calling isStr function using check object
		     * for class CheckString
		     */
	        if(check.isStr(type)==false) {
	        	System.out.println("Please input a valid string");
	        	
	        }
	        else {
	        	System.out.println("Enter car price");
		        price=sc.nextFloat();
		        
		        // Checks whether the input Price is valid or not
		         
		        if(price<0) {
		        	System.out.println("please enter valid car price");
		        }
		        else {
		        	System.out.println("Enter insurance type");
			        insurance=sc.next();
			        /*Check whether the input insurance is String type or not
				     * by calling isStr function using check object
				     * for class CheckString
				     */

			        if(check.isStr(insurance)==false) {
				    	System.out.println("Please input a valid string");
				    }
			        
		        }
	        }
	    }
	}

}
