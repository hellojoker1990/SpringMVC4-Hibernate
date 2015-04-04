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

	<form method="post" id="loginForm"
		action="${pageContext.request.contextPath}/j_spring_security_check">
		<div class="main">
			<div class="login">
				<ul>
					<li><span>UserName:</span> <input type="text"
						name="j_username" id="j_username" /></li>
					<li><span>Passwd:</span> <input type="password"
						name="j_password" id="j_password" /></li>
					<li><span>remember-me:</span> <input type="checkbox"
						name="remember-me" id="remember-me" /></li>

				</ul>


				<div class="botton">
					<input name="" type="button" class="denglu"
						value="submit" onclick="validate()" /><input
						name="" type="button" class="quxiao" value="cancle"
						onclick="window.location.reload()" />
				</div>
			</div>
		</div>
	</form>
</body>

<script type="text/javascript">
function validate(){
	var username = document.getElementById("j_username").value;
	if(username == ""){
		alert("UserName is not NULL");
        document.getElementById("j_username").select();
        return;
	}
	var password = document.getElementById("j_password").value;
	if(password == ""){
		alert("Passwd is not NULL");
        document.getElementById("j_password").select();
        return;
	}
	
	
	
    document.getElementById("loginForm").submit();
    
    
   
}
function keydown(e){
	//alert("123");
	var e = e||event;
	var keycode = e.keyCode ||e.which || e.charCode;
    if(keycode == 13){
       validate();
    }
}

document.onkeydown = keydown;


if("1"=="${error}"){
	alert("用户名或密码错误");
}
</script>


</html>