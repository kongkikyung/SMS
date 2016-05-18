package com.cnt.sms.vo;

import java.util.Date;

public class Member {
	private int no;
	private String email;
	private String password;
	private String name;
	private String teamName;
	private String teamMark;
	private String newMark;
	private Date createdDate;
	private Date modifiedDate;
	public int getNo() {
		return no;
	}
	public Member setNo(int no) {
		this.no = no;
		return this;
	}
	public String getTeamName() {
		return teamName;
	}
	public Member setTeamName(String teamName) {
		this.teamName = teamName;
		return this;
	}
	public String getTeamMark() {
		return teamMark;
	}
	public Member setTeamMark(String teamMark) {
		this.teamMark = teamMark;
		return this;
	}
	public String getNewMark() {
		return newMark;
	}
	public Member setNewMark(String newMark) {
		this.newMark = newMark;
		return this;
	}
	public String getName() {
		return name;
	}
	public Member setName(String name) {
		this.name = name;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Member setPassword(String password) {
		this.password = password;
		return this;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Member setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public Member setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
}
