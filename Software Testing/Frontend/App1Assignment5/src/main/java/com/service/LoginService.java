package com.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This class provides a method to check the login credentials of a user.
 *
 * @author aryanverma
 *
 */
@Component
public class LoginService {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	/**
	 * This method checks the login credentials of a user.
	 * @param uname the username of the user
	 * @param pass the password of the user
	 * @return true if the login credentials are correct, false otherwise
	 */
	public boolean checkLogin(String uname, String pass) {
		
		String url = "http://localhost:8082/users/" + uname;
		
		try {
			String response = restTemplate.getForObject(url, String.class);
			System.out.println("\nJSON Response in String format");
			System.out.println(response);
			
			JSONParser parse = new JSONParser();
			JSONObject jobj = (JSONObject) parse.parse(response);
			String password = (String) jobj.get("pass");
			
			if (password.equals(pass)) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
