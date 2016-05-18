<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String cp = request.getContextPath(); %> <%--ContextPath 선언 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    
    <div class="row">
  		<div class="col-sm-6 col-md-4">
    		<div class="thumbnail">
      			<img src="<%=cp %>/resources/${list.newMark}" alt="...">
      				<div class="caption">
        				<h3><c:out value="${list.teamName }"/></h3>
        				<p>팀 소개</p>
        				<p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
      				</div>
    		</div>
  		</div>
	</div>
	</c:forEach>
<script type="text/javascript">

    alert(cp);

</script>
	
	
</body>
</html>