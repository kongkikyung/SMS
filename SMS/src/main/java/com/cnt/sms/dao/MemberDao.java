package com.cnt.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cnt.sms.vo.Matching;
import com.cnt.sms.vo.Member;

public class MemberDao {
	Connection connection; 
	Member member;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public Member login(String id, String password) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		member = new Member();
		
		try {
			stmt = connection.prepareStatement("SELECT * FROM MEMBERS");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String email = rs.getString("EMAIL");
				String pwd = rs.getString("PWD");
				
				if(id.equals(email) && password.equals(pwd)) {
					member = new Member().setNo(rs.getInt("MNO"))
							.setTeamName(rs.getString("MTEAM"))
							.setPassword(rs.getString("PWD"))
							.setEmail(rs.getString("EMAIL"))
							.setName(rs.getString("MNAME"))
							.setCreatedDate(rs.getDate("CRE_DATE"))
							.setModifiedDate(rs.getDate("MOD_DATE"));
					
					return member;
				}
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	public void signup(String id, String password, String name, String teamName, String teamMark, String newMark) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.prepareStatement("INSERT INTO members (EMAIL, PWD, MNAME, MTEAM, TEAMMARK, NEWMARK, CRE_DATE, MOD_DATE)" + "values(?,?,?,?,?,?, NOW(), NOW())");
			stmt.setString(1, id);
			stmt.setString(2, password);
			stmt.setString(3, name);
			stmt.setString(4, teamName);
			stmt.setString(5, teamMark);
			stmt.setString(6, newMark);
			
			stmt.execute();
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public List<Matching> getMyMatchingList(int myId) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT SENDER_MNO, RECEIVER_MNO, MATCHING_TYPE, CURRENT_STATUS, REQUEST_DATE" +
									" FROM MATCHING" +
									" WHERE SENDER_MNO = " + myId +
									" ORDER BY REQUEST_DATE ASC");
			
			ArrayList<Matching> matchingList = new ArrayList<Matching>();
			
			while(rs.next()) {
				matchingList.add(new Matching()
						.setSenderNo(rs.getInt("SENDER_MNO"))
						.setMyTeamName(getTeamName(rs.getInt("SENDER_MNO")))
						.setRecieverNo(rs.getInt("RECEIVER_MNO"))
						.setYourTeamName(getTeamName(rs.getInt("RECEIVER_MNO")))
						.setType(rs.getString("MATCHING_TYPE"))
						.setStatus(rs.getInt("CURRENT_STATUS"))
						.setRequestDate(rs.getDate("REQUEST_DATE")));
			}
			return matchingList;
			
		} catch (Exception e) {
			throw e;
		} 
	}
	
	public List<Matching> getYourMatchingList(int myId) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT SENDER_MNO, RECEIVER_MNO, MATCHING_TYPE, CURRENT_STATUS, REQUEST_DATE" +
									" FROM MATCHING" +
									" WHERE RECEIVER_MNO = " + myId +
									" ORDER BY REQUEST_DATE ASC");
			
			ArrayList<Matching> matchingList = new ArrayList<Matching>();
			
			while(rs.next()) {
				matchingList.add(new Matching()
						.setSenderNo(rs.getInt("SENDER_MNO"))
						.setMyTeamName(getTeamName(rs.getInt("SENDER_MNO")))
						.setRecieverNo(rs.getInt("RECEIVER_MNO"))
						.setYourTeamName(getTeamName(rs.getInt("RECEIVER_MNO")))
						.setType(rs.getString("MATCHING_TYPE"))
						.setStatus(rs.getInt("CURRENT_STATUS"))
						.setRequestDate(rs.getDate("REQUEST_DATE")));
			}
			return matchingList;
			
		} catch (Exception e) {
			throw e;
		} 
	}
	
	public List<Member> getMeberList() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT MNO, EMAIL, PWD, MNAME, MTEAM, TEAMMARK, NEWMARK, CRE_DATE, MOD_DATE" +
									" FROM MEMBERS" +
									" ORDER BY MNO ASC");
			
			ArrayList<Member> members = new ArrayList<Member>();
			
			while(rs.next()) {
				members.add(new Member()
						.setNo(rs.getInt("MNO"))
						.setEmail(rs.getString("EMAIL"))
						.setPassword(rs.getString("PWD"))
						.setTeamName(rs.getString("MTEAM"))
						.setName(rs.getString("MNAME"))
						.setTeamMark(rs.getString("TEAMMARK"))
						.setNewMark(rs.getString("NEWMARK"))
						.setCreatedDate(rs.getDate("CRE_DATE"))
						.setModifiedDate(rs.getDate("MOD_DATE")));
			}
			return members;
			
		} catch (Exception e) {
			throw e;
		} 
	}
	
	public void requestMatching(int myNo, int userNo) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.prepareStatement("INSERT INTO matching (SENDER_MNO, RECEIVER_MNO, MATCHING_TYPE, CURRENT_STATUS, REQUEST_DATE)" 
											+ "values(?,?,?,?,NOW())");
			stmt.setInt(1, myNo);
			stmt.setInt(2, userNo);
			stmt.setString(3, "manual");
			stmt.setInt(4, 3);
			
			stmt.execute();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public String getTeamName(int id) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		String teamName = null;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT MTEAM FROM MEMBERS WHERE MNO = " + id);
			
			while(rs.next()) {
				teamName = rs.getString("MTEAM");
			}
			
			return teamName;
			
		} catch (Exception e) {
			throw e;
		} 
	}
}
