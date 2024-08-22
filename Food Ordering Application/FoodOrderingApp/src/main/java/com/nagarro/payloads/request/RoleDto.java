package com.nagarro.payloads.request;

public class RoleDto {
	
	private int roleid;	
	private String roleName;
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public RoleDto(int roleid, String roleName) {
		super();
		this.roleid = roleid;
		this.roleName = roleName;
	}
	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
