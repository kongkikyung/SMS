package com.cnt.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		} finally {
			try {if (rs!=null) rs.close(); } catch (Exception e) {}
			try {if (stmt!=null) stmt.close(); } catch (Exception e) {}
		}
	}
}
