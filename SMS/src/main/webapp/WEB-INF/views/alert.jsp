<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type = "text/javascript">
	var id = "${userSession.getUserID()}";
	var pwd = "${userSession.getPassword()}";
	if(id!=0){
		alert("회원 정보를 다시 확인 해주세요.")
	} else {
		alert("회원 정보를 다시 확인 해주세요.");
	}
	
</script>
</head>
<body>
<a href="${pageContext.request.contextPath}/loginController/logout">다시 입력</a>
</body>
</html>