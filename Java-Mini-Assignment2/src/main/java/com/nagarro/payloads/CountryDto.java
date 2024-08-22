package com.nagarro.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Setter
//@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CountryDto {
 int count;
 String name;
 Country country[];
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Country[] getCountry() {
	return country;
}
public void setCountry(Country[] country) {
	this.country = country;
}
 
 
}
