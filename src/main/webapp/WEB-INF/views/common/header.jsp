<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function logout() {
	alert("로그아웃되었습니다.");
}
</script>
</head>
<body>
	<table border=0 width="100%">
		<tr>
			<td><a href="${contextPath}/main.do"> <img
					src="${contextPath}/resources/images/고양이.jpg" width="200"
					height="100" />
			</a></td>
			<td>
				<h1 align="center">
					<font size=30>내가 만드는 스프링 게시판!!</font>
				</h1>
			</td>

			<td>
				<%-- <a href="${contextPath}/member/loginForm.do"><h3>로그인</h3></a> --%>
				<c:choose>
					<c:when test="${isLogOn ==true && member!=null}">
						<h3>환영합니다. ${member.name }님!</h3>
						<a href="${contextPath}/member/logout.do" onclick="logout()"><h3>로그아웃</h3></a>
					</c:when>
					<c:otherwise>
						<a href="${contextPath}/member/loginForm.do"><h3>로그인</h3></a>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>