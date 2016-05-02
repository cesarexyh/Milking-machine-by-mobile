package com.example.learnprogect.login;

public class User {
	private String userName;
	private char sex;
//	private int year;
//	private int month;
//	private int day;
	public String getUserName() {
	return userName;
	}
	public void setUserName(String userName) {
	this.userName = userName;
	}
	
	public char getSex() {
	return sex;
	}
	public void setSex(char sex) {
	this.sex = sex;
	}
	public User(String userName, String passWord, char sex, String city,
	String hobby) {
	super();
	this.userName = userName;
	this.sex = sex;
	}
}
