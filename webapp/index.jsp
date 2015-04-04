<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache">
<META http-equiv="Expire" content="0">
<title>Index Page</title>
</head>



<frameset id="mainFrames" rows="*" cols="220,*" border="1" framespacing="0">
	<frame src="${pageContext.request.contextPath}/left.jsp" id="leftFrame"
		name="leftFrame" scrolling="auto" >
	<frame src="${pageContext.request.contextPath}/main.jsp" id="main"
		name="main" scrolling="auto">
</frameset>

</html>