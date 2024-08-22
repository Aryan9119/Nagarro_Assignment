package CoreJavaAssignment1;

import java.util.regex.Pattern;
/*CheckString class is used to return and check 
 * whether the given input is String or not
 */
public class CheckString {
	boolean isStr(String in) {
		// returns true if input matches the type string 
		//returns false if input does not matches the string type.
		return Pattern.matches("[a-zA-Z]+", in);
	}
}
