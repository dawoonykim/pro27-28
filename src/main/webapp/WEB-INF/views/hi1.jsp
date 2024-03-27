<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
hihi
하하하하
오오오 유후...<br>
${stu.name }<br>
${stu.ban }<br>
<img src="${contextPath }/resources/images/duke.png"><br>
<img src="${pageContext.request.contextPath }/resources/img/hanbit.jpg">
</body>
</html>