<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.kj133.entity.GjUser"%> 


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>checkUser.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%
    	GjUser b = (GjUser) request.getSession().getAttribute("gjuser");
    	if(b==null){ 
     %>
     <script type="text/javascript">parent.location.href='<%=path%>/gjlogin.jsp';</script> 
     <%} %>
  </head>
</html>
