package CoreJavaAssignment1;

public class OutputProcessor {
	
	public void GiveOutput(InputProcessor take) {
		/*Class OutputProcessor uses GiveOutput function  
		 * which takes an object 'take' for class InputProcessor
		 * for providing the details of model,price and calculated premium percentage
		 */
		
		//Creates an object pc for class PremiumCalculator
		PremiumCalculator pc=new PremiumCalculator();
		
		//Creates an object pcc for class PremiumChecker
        PremiumChecker pcc=new PremiumChecker();
        
        //Changes the variable type to an enum variable to access the members of enum
		CarType typeEnum=CarType.valueOf(take.type);
        InsuranceType insuranceEnum=InsuranceType.valueOf(take.insurance);
        
        System.out.println("Car Model- "+ take.model);
        System.out.println("Car Price- "+ take.price);
        
        /* checkPremiumPercent return the percentage for the respective carType
         * calulatePremium prints the calculated insurance premium
         * for respective insurance type
         */
        pc.calculatePremium(pcc.checkPremiumPercent(typeEnum),insuranceEnum,take.price);
	}

}
