package CoreJavaAssignment1;
public class PremiumCalculator {	
	float premium;
	float x;
	public void calculatePremium(int percent, InsuranceType insurance,float price){
		/*Class PremiumCalculator uses a member function calculatePremium
		 * for the calculation of premium using the formula
		 * which requires the car price, percent and respective insurance type.
		 */
		try {
			if(insurance==InsuranceType.basic){
				//calculate the premium for basic insurance type.
	            premium=(percent*price)/100;       
	        }
	        else if(insurance==InsuranceType.premium){
	        	//calculates the premium for premium insurance type.
	            x=(percent*price)/100;
	            premium=x+x/5;
	        }
	        else {
	        	System.out.println("Please enter either 'basic' or 'premium'");
	        }
	        System.out.println("Total insurance to be paid will be "+premium);
		}catch(Exception eb) {
			//throws an error exception in the try statement.
			System.out.println(eb.getMessage()+" error occured");
		}       
	}
}
