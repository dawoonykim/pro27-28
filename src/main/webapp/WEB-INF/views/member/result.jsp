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
	<%-- ${map } --%>
	<h1>업로드가 완료되었습니다.</h1>
	<label>아이디:</label>
	 <input type="text" name="id" value="${map.id }" readonly><br>
	<label>이름:</label>
	 <input type="text" name="name" value="${map.name }" readonly><br>
	<div class="result-images">
	  <c:forEach var="imageFileName" items="${ map.fileList}"  >
	         <%-- <img src="${pageContext.request.contextPath }/download?imageFileName=${imageFileName }" style="width:150px"> --%>
	         <img src="${contextPath }/download?imageFileName=${imageFileName }">
	         <br><br><br>
	  </c:forEach>         
	</div><p> 
	
	<a href='${contextPath }/form'> 다시 업로드 하기 </a> </p>

</body>
</html>