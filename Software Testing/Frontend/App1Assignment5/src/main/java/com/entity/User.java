package com.entity;

import org.springframework.stereotype.Component;

@Component
public class User {
	
	String uname;

	String pass;
	
	public User() {
		super();
	}

	public User(String uname, String pass) {
		super();
		this.uname = uname;
		this.pass = pass;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Override
	public String toString() {
		return "User [uname=" + uname + ", pass=" + pass + "]";
	}

}
