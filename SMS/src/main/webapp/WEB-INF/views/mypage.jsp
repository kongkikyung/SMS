<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String cp = request.getContextPath(); %> <%--ContextPath 선언 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>My Page</title>
</head>
<body>
	<h1>내가 신청한 매칭 현황</h1>
	<table border="1">
		<c:forEach var="list" items="${myMatchingList}">
			<tr>
				<td>상대 팀 </td>
				<td>${list.yourTeamName}</td>
				<td>대기</td>
			</tr>
		</c:forEach>
	</table>
	
	<h1>내게 온 매칭 제의 현황</h1>
	<table border="1">
		<c:forEach var="list" items="${yourMatchingList}">
			<tr>
				<td>상대 팀 </td>
				<td>${list.myTeamName}</td>
				<td>대기</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>