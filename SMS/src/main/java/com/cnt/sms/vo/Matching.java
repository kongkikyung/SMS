package com.cnt.sms.vo;

import java.util.Date;

public class Matching {
	private int senderNo;
	private String myTeamName;
	private int recieverNo;
	private String yourTeamName;
	private int status;
	private String type;
	private Date requestDate;
	
	public int getSenderNo() {
		return senderNo;
	}
	public Matching setSenderNo(int senderNo) {
		this.senderNo = senderNo;
		return this;
	}
	public String getMyTeamName() {
		return myTeamName;
	}
	public Matching setMyTeamName(String myTeamName) {
		this.myTeamName = myTeamName;
		return this;
	}
	public int getRecieverNo() {
		return recieverNo;
	}
	public Matching setRecieverNo(int recieverNo) {
		this.recieverNo = recieverNo;
		return this;
	}
	public String getYourTeamName() {
		return yourTeamName;
	}
	public Matching setYourTeamName(String yourTeamName) {
		this.yourTeamName = yourTeamName;
		return this;
	}
	public int getStatus() {
		return status;
	}
	public Matching setStatus(int status) {
		this.status = status;
		return this;
	}
	public String getType() {
		return type;
	}
	public Matching setType(String type) {
		this.type = type;
		return this;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public Matching setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
		return this;
	}
	
	
}
