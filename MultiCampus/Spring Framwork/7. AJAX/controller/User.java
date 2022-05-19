package com.multi.controller;

import java.util.Date;

public class User {
	private String id;
	private String pwd;
	private String name;
	private String date;
	private String gender;
	private String email;
	private String telphone;
	

	public User(String id, String pwd, String name, String date, String gender, String email, String telphone) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.date = date;
		this.gender = gender;
		this.email = email;
		this.telphone = telphone;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + ", name=" + name + ", date=" + date + ", gender=" + gender
				+ ", email=" + email + ", telphone=" + telphone + "]";
	}
	
	
	

}
