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
    <title>personInMineDetail.jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.timers.js"></script>
	<script type="text/javascript">
		$(function() {
        });
	</script>
  </head>
  
  <body bgColor="white" background="Image/right.gif">
  	<div style="font-weight: bold; font-size: 13px; background-repeat: repeat; background-image: url(Image/title.bmp);">
  		&gt;&gt;当前位置：当前井下人员位置表
  	</div>
  	<div id="searchDiv">
  		<span id="searchInput">
  			<input id="keyWord" type="text" size="10">
  		</span>
  		<span id="searchButton">
  			<input id="submit" type="button" value="查找"></input>
  		</span>
  	</div>
  	<div id="showData">
  		<table cellpadding="0" cellspacing="0" border="1" style="border: 1px solid gray; font-size: 12px; text-align: center; width: 100%;">
  			<tr>
  				<td>地图</td>
  				<td>工种</td>
  				<td>卡号</td>
  				<td>姓名</td>
  				<td>班组</td>
  				<td>所属分站</td>
  				<td>分站名称</td>
  				<td>所属定位器</td>
  				<td>定位器名称</td>
  				<td>入井时间</td>
  				<td>进入分站时间</td>
  			</tr>
  			<c:forEach items="${ info }" var="in">
  				<tr>
	  				<td>${ in.map }</td>
	  				<td>${ in.worktype }</td>
	  				<td>${ in.cardid }</td>
	  				<td>${ in.staffername }</td>
	  				<td>${ in.staffergroup }</td>
	  				<td>${ in.cardreader }</td>
	  				<td>${ in.cardreadername }</td>
	  				<td>${ in.locator }</td>
	  				<td>${ in.locatorname }</td>
	  				<td>${ in.downtime }</td>
	  				<td>${ in.inlocatortime }</td>
	  			</tr>
  			</c:forEach>
  		</table>
  	</div>
  </body>
</html>
