<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testhtml.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table cellpadding="0" cellspacing="0" border="1" style="width: 100%; font-size: 12px;">
    	<tr>
    		<td colspan="4" style="padding-left: 3px;">详细信息</td>
    	</tr>
    	<tr>
    		<td>姓名：</td>
    		<td id="name"></td>
    		<td>卡号：</td>
    		<td id="cardid"></td>
    	</tr>
    	<tr>
    		<td>下井时间</td>
    		<td></td>
    		<td>时长</td>
    		<td></td>
    	</tr>
    	<tr>
    		<td>区域</td>
    		<td></td>
    		<td>位置</td>
    		<td></td>
    	</tr>
    	<tr>
    		<td>进入时间</td>
    		<td></td>
    		<td>时长</td>
    		<td></td>
    	</tr>
    	<tr>
    		<td>其他信息</td>
    		<td colspan="3"></td>
    	</tr>
    </table>
  </body>
</html>
