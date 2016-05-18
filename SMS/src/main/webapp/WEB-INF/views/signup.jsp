<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>회원가입</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/signup.do" method="post" enctype="multipart/form-data">
		ID<input type="email" name="id"><br>
		password<input type="password" name="password"><br>
		이름<input type="text" name="name"><br>
		팀 명<input type="text" name="teamName"><br>
		팀 마크<input type="file" name="teamMark"><br>
		<!-- 분류<input type="radio" name="job" value="학생">학생
		<input type="radio" name="job" value="교수">교수
		<input type="radio" name="job" value="관리자">관리자<br> -->
		<input type="submit" value="가입 신청하기">
	</form>
	<br>
<a href="${pageContext.request.contextPath}/loginController/logout" onclick="myFunction()">돌아가기</a>
</body>
</html>