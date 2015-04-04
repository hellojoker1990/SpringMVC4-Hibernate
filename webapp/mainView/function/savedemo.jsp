<%@ include file="../common/includes.jsp"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="${_contextPath}/static/js/jquery-1.11.0.min.js"></script>
</head>
<body>

	<form method="post" id="loginForm"
		action="${_contextPath}/demo/save">
		<div class="main">
			<div class="login">
				<ul>
					<li><span>id:</span> ${demo.id}</li>
					<li><span>name:</span> <input type="text"
						name="name" id="name" value="${demo.name}"/></li>
					<li><span>age:</span> <input type="text"
						name="age" id="age" value="${demo.age}"/></li>
					<li><span>createDate:</span> <input type="text"
						name="createDate" id="createDate" value="${demo.createDate}"/></li>
					<li><span>modifyDate:</span> <input type="text"
						name="modifyDate" id="modifyDate" value="${demo.modifyDate}"/></li>

				</ul>


				<div class="botton">
					<input name="" type="submit" 
						value="submit"  />
				</div>
			</div>
		</div>
	</form>
</body>



</html>