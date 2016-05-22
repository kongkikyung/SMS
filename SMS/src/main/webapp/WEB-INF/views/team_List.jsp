<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String cp = request.getContextPath(); %> <%--ContextPath 선언 --%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <!-- Bootstrap Core CSS -->
    <link href="<%=cp%>/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap-theme.min.css">
<title>Team List</title>
</head>
<body>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="<%=cp%>/resources/bootstrap/js/bootstrap.min.js"></script>
    
    <c:forEach var="list" items="${memberList}">
    
    <form action="${pageContext.request.contextPath}/manualMatching.do" method="post">
    	<div class="row">
	  		<div class="col-sm-6 col-md-4">
	    		<div class="thumbnail">
	      			<img src="${pageContext.servletContext.contextPath}/resources/image/${list.newMark}"/>
	      				<div class="caption">
	      					<input type="hidden" name="user_id" value="${list.no }">
	        				<h3><c:out value="${list.teamName }"/></h3>
	        				<h3><c:out value="${list.no }"/></h3>
	        				<p>팀 소개</p>
	        				<p><input type="submit" class="btn btn-primary" value="매칭 신청"> <a href="#" class="btn btn-default" role="button">Button</a></p>
	      				</div>
	    		</div>
	  		</div>
		</div>
    </form>
    
	</c:forEach>
</body>
</html>