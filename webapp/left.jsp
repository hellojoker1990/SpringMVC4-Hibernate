<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Left Page</title>
</head>

<body>
	<ul>

		<li><a href="${pageContext.request.contextPath}/demo/1" target="main">demo web</a></li>
		<li><a href="${pageContext.request.contextPath}/demo/1.do" target="main">demo web.do</a></li>
		<li><a href="${pageContext.request.contextPath}/demo/xml/write/1.xml" target="main">demo xml</a></li>
		<li><a href="${pageContext.request.contextPath}/demo/json/write/1.json" target="main">demo json</a></li>
		<li><a href="${pageContext.request.contextPath}/demo/findall" target="main">demo findall</a></li>
		<li><a href="${pageContext.request.contextPath}/demo/flashcache" target="main">flashcache</a></li>
		<li><a href="${pageContext.request.contextPath}/demo/presave" target="main">demo add</a></li>

	</ul>
</body>
</html>