package com.beans;

import java.sql.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class MemberBean {
	
	@Length(min=8, max=12,message="帳號長度限制於8~12位數")
	private String account;
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()])(.{8,20})$",message="密碼須包含一個大小字母及特殊字元")
	private String password;
	private String name;
	private Date birthday;
	private String email;
	private String phone;
	private String address;
	
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
	
	
	
	
	
	
}
