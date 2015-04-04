<%@ include file="../common/includes.jsp"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.min.js"></script>
</head>
<body>
	<a href="${_contextPath}/logout" target="_top">注销</a> <br>

	<a href="${_contextPath}/demo/findall?locale=en_US">English</a> <br>
	<a href="${_contextPath}/demo/findall?locale=zh_CN">中文</a> <br>
	
	id=${id}

	<div>
		<fmt:message key="model.test.message" />
		<s:message code="model.test.message"/>

	</div>

	<c:if test="${! empty results}">
		<c:forEach items="${results}" var="obj">
	${obj.id} || ${obj.name} || ${obj.age} <br>
		</c:forEach>
	</c:if>
</body>


<script type="text/javascript">
	$(function() {
		alert("spring mvc");
	});
</script>
</html>