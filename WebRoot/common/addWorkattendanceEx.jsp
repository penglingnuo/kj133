<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
    <title>addWorkattendanceEx.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<script type="text/javascript" src="js/jquery.js"></script>
  	<script type="text/javascript" src="js/common/addWorkattendanceEx.js"></script>
  	
  	<link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script src="js/Calendar2.js"></script><!-- 不带分秒-->
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"></script>
	<link href="Css/calendar-blue.css" rel="stylesheet"> 
  </head>
  
  <body bgColor="white" background="Image/right.gif" style="width: 100%;">
  	<table style="width: 100%; padding: 3px;" cellpadding="0" cellspacing="0" border="0">
  		<tr>
  			<td width="20%">
  				人员：&nbsp;
				<input id="keyWord" type="text" size="10">&nbsp;
				<select id="stafferSelect" style="width: 125px;">
				</select>
  			</td>
  			<td width="15%">
  				开始时间：&nbsp;
				<input id="starttime" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('department.stime')})" size=15 name=department.stime>
  			</td>
  			<td width="50%">
  				结束时间：&nbsp;
				<input id="endtime" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('department.etime')})" size=15 name=department.etime>
  			</td>
  		</tr>
  		<tr>
  			<td width="20%">
  				下井分站：&nbsp;&nbsp;
				<select id="downccardreader">
					<c:forEach items="${ cardreader }" var="cr">
						<option value="${ cr.cardreaderid }">${ cr.crname }</option>
					</c:forEach>
			  	</select>
  			</td>
  			<td colspan="2" width="50%">
  				下井定位器：&nbsp;&nbsp;
				<select id="downlocator">
					<c:forEach items="${ locator }" var="l">
						<option value="${ l.locatorId }">${ l.lname }</option>
					</c:forEach>
			  	</select>
			 </td>
  		</tr>
  		<tr>
  			<td width="20%">
  				升井分站：&nbsp;&nbsp;
				<select id="upcardreader">
					<c:forEach items="${ cardreader }" var="cr">
						<option value="${ cr.cardreaderid }">${ cr.crname }</option>
					</c:forEach>
				</select>
  			</td>
  			<td colspan="2" width="50%">
  				升井定位器：&nbsp;&nbsp;
			 	<select id="uplocator">
					<c:forEach items="${ locator }" var="l">
						<option value="${ l.locatorId }">${ l.lname }</option>
					</c:forEach>
			  	</select>
			 </td>
  		</tr>
  		<tr>
  			<td width="20%">
  				&nbsp;
  			</td>
  			<td colspan="2" width="50%">
  				<input id="addWorkAttendanceEx" type="button" size="4" value="增加">
			</td>
  		</tr>
  	</table>
  </body>
</html>
