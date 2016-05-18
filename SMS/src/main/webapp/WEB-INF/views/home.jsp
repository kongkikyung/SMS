<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
	<title>home</title>
</head>
<body>
<h1>
	SMS
</h1>

<P>  The time on the server is ${serverTime}. </P>
	
	<h1>로그인</h1>
	<form action="loginController/login.do" method="post">
		ID<input type="text" name="id"><br>
		비밀번호<input type="password" name="password"><br>
		<input type="submit" value="로그인">
	</form>
	
	<a href="/mju/loginController/signupPage.do">회원가입</a>
	</body>
</html>
