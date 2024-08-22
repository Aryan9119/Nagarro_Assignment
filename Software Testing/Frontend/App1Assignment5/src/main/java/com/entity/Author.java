package com.entity;

import org.springframework.stereotype.Component;

@Component
public class Author {
	
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Author() {
		super();
	}

	public Author(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Author [name=" + name + "]";
	}
	
}
