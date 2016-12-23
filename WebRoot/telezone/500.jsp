<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>500.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
  	<div style="width: 100%; height: 100%;">
  		<img src="img/error.jpg" style="position: absolute; left: 100px; top: 100px;">
  		<span style="position: absolute; left: 70px; top: 250px; font-size: 15px; font-weight: bold;">
  			抱歉，系统忙，请稍候再试
  		</span>
  	</div>
  </body>
</html>
