<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>outOfTimeCard.jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body bgColor="white" background="Image/right.gif" style="width: 100%;">
    <table id="showDataTable" cellpadding="0" cellspacing="0" border="1" style="width: 100%;font-size: 12px; border: 1px solid gray; text-align: center; padding: 1px;">
		<tr style="font-size: 14px; font-weight: bold;">
			<td>卡号</td>
			<td>员工号</td>
			<td>姓名</td>
			<td>部门</td>
			<td>下井时间</td>
			<td>下井时长</td>
			<td>所属基站</td>
		</tr>
		<c:forEach items="${ outOfTimeCard }" var="ot">
			<tr>
				<td>${ ot.cardid }</td>
				<td>${ ot.stafferid }</td>
				<td>${ ot.staffername }</td>
				<td>${ ot.stafferdepartment }</td>
				<td>${ ot.downtime }</td>
				<td>${ ot.betweentime }</td>
				<td>${ ot.cardreadername }</td>
			</tr>	
		</c:forEach>
	</table>
  </body>
</html>
