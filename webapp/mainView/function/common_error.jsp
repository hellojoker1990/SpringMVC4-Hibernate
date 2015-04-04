<%@ include file="../common/includes.jsp"%>
<%@ page pageEncoding="UTF-8"  isErrorPage="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<html>
	<head>
		<title>error page</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<META http-equiv="Pragma" content="no-cache">
		<META http-equiv="Cache-Control" content="no-cache">
		<META http-equiv="Expire" content="0">
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.min.js"></script>

		<script type="text/javascript">
			$(function(){
				
			});
			
		</script>
		
		<style type="text/css">
			#contentDiv table{
				margin-left:auto;
				margin-right:auto;
			}
			.right{
				text-align:right;
				padding-right:5px;
			}
			#contentDiv input, #contentDiv textarea,{
				border-top: 1px solid #555;
				border-left: 1px solid #555;
				border-bottom: 1px solid #555;
				border-right: 1px solid #555;
				padding: 1px;
				color: #333;
			}
			.commonInput{
				border-top: 1px solid #555;
				border-left: 1px solid #555;
				border-bottom: 1px solid #555;
				border-right: 1px solid #555;
				padding: 1px;
				color: #333;
			}
			#contentDiv input.noborder, #contentDiv textarea.noborder,{
				border: 0px;
			}			
			select {
			        position:relative;
			        font-size:12px;
			        line-height:18px;
			        border:0px;
			}
			.breaktext{
				 overflow:hidden;
				 white-space:nowrap;
				 text-overflow:ellipsis;
			}
			.minlen{ 
				width:expression(document.body.clientWidth <= 1024? "1024px": "auto" );
				min-width:900px;
			}
		</style>
	</head>
	<body style="background-color:#F8F8F6;" class="minlen">
		
		<TABLE cellSpacing=0 cellPadding=0 width=100% align=center border=0>
  		<TBODY>
  			<TR>
   				<TD>
     				<TABLE cellSpacing=0 cellPadding=0 width=100% align=center border=0>
       				<TBODY>
       					<TR>
				          <TD vAlign=top align=left>
				          	<TABLE cellSpacing=0 cellPadding=3 width="100%" border=0>
				              <TBODY>
					              <TR>
					                <TD  valign="bottom">
					                	
					                		<font style="color:black;font-weight:bold;">
					                			错误提示
					                		</font>
					                	
					                </TD>
					                <TD >
					                	
					                </TD>
					              </TR>
					              <TR>
					                <TD colSpan=2 height=5></TD>
												</TR>
												<tr>
													<td colSpan="2">
														<div id="contentDiv" style="width:100%;">
														系统出现异常：请与系统管理员联系！！！<br/>
														<a href="" target="_top">返回首页</a>
														<%-- 
														<hr>
														${exception}<br/>
														<hr>
														<%
														if (exception != null) {
															for (int i = 0; i < exception.getStackTrace().length; i++) {
																out.println(exception.getStackTrace()[i].toString());
																out.print("<br/>");
															}
														}
														%>
														--%>
														</div>
													</td>
												</tr>
											</TBODY>
										</TABLE>
									</TD>
       					</TR>
       				</TBODY>
       			</TABLE>
       		</TD>
        </TR>
   		</TBODY>
   	</TABLE>
   
	</BODY>
</HTML>