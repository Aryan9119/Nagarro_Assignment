package CoreJavaAssignment1;
public class PremiumChecker {
	int percent;
    public int checkPremiumPercent(CoreJavaAssignment1.CarType enumType1){
    	/*Class PremiumChecker uses a member function checkPremiumPercent
		 * for checking the value of premium percentage using Switch and Case
		 * which requires the car type and returns the percentage
		 */
    	try {
	        switch (enumType1) {
	            case Hatchback:
	                percent=5;
	                break;
	            case Sedan:
	                percent=8;
	                break;
	            case SUV:
	                percent=10;
	                break;
	        }
	        
    	}catch(Exception ea) {
    		//throws an error exception catches by try statement
    		System.out.println(ea.getMessage()+" error occured");
    	}
    	return percent;
    	
    }
}
