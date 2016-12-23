<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>showbackup.jsp</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript" src="js/jquery.js"></script>
		
		<script type="text/javascript" src="js/OpenLayers.js" charset="UTF-8"></script>
		<script type="text/javascript" src="js/openlayers_extend.js"></script>
		<script type="text/javascript" src="js/mapconfig.js"></script>
		<link rel="stylesheet" href="Css/openlayers/theme/default/style.css"
			type="text/css" />
		<link rel="stylesheet" href="Css/openlayers_extend.css"
			type="text/css" />
		<script type="text/javascript" src="js/showbackup.js"></script>

		<link href="Css/TestDate.css" rel="stylesheet">
		<%--背景--%>
		<script src="js/Calendar2.js"></script>
		<!-- 不带分秒-->
		<script type="text/javascript" src="js/dateJs/WdatePicker.js"
			defer="defer"></script>
		<link href="Css/calendar-blue.css" rel="stylesheet">
		<script type="text/javascript">
			var ip = "<%= request.getServerName() + ":" + request.getServerPort() %>";
		</script>
	</head>

	<body bgColor="white" background="Image/right.gif" style="width: 100%;">
		<div
			style="font-weight: bold; font-size: 13px; background-repeat: repeat; 

background-image: url(Image/title.bmp);">
			&gt;&gt;当前位置：人员轨迹回放
		</div>
		<div
			style="font-weight: bold; font-size: 13px; background-repeat: repeat;">
			<span id="searchInput"> 人员：&nbsp; <input id="keyWord"
					type="text" size="10">&nbsp; <select id="stafferSelect"
					style="width: 125px;">
				</select> &nbsp;
				日期：&nbsp; <input id="realtime"
					onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('department.stime')})"
					size=9 name=department.stime/>
					            
				
				<select id="downTime"
					style="width: 145px; display: none;">
				</select>
				<input id="searchButton" type="button" value="查找" />  </span>
		</div>
		<div style="overflow: auto; height: 200px;">
			<table id="stafferInfo" 
			width="100%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" 

style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
				<tr >
					<td align="left" bgcolor="#B0C4DE">
						卡号
					</td>
					<td align="left" bgcolor="#B0C4DE">
						员工号
					</td>
					<td align="left" bgcolor="#B0C4DE">
						姓名
					</td>
					<td align="left" bgcolor="#B0C4DE">
						部门
					</td>
					<td align="left" bgcolor="#B0C4DE">
						下井时间
					</td>
					<td align="left" bgcolor="#B0C4DE">
						升井时间
					</td>
					<td align="left" bgcolor="#B0C4DE">
						工作时长
					</td>
				</tr>
				<tr>
					<td id="1" align="left" bgcolor="#E6E6FA"></td>
					<td id="2" align="left" bgcolor="#E6E6FA"></td>
					<td id="3" align="left" bgcolor="#E6E6FA"></td>
					<td id="4" align="left" bgcolor="#E6E6FA"></td>
					<td id="5" align="left" bgcolor="#E6E6FA"></td>
					<td id="6" align="left" bgcolor="#E6E6FA"></td>
					<td id="7" align="left" bgcolor="#E6E6FA"></td>
				</tr>
			</table>
			<hr>
			<table id="dataTable" width="100%" cellspacing="1" cellpadding="1"  

bgcolor="#6CA6CD" border="0" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   

13px;">
				<tr >
					<td align="left" bgcolor="#B0C4DE">
						序号
					</td>
					<td align="left" bgcolor="#B0C4DE">
						进入时间
					</td>
					<td align="left" bgcolor="#B0C4DE">
						分站号
					</td>
					<td align="left" bgcolor="#B0C4DE">
						名称
					</td>
					<td align="left" bgcolor="#B0C4DE">
						停留时间
					</td>
					<td align="left" bgcolor="#B0C4DE">
						离开时间
					</td>
				</tr>
				<tr id="dataTemplate" style="display: none;">
					<td id="0" style=" padding: 3px;" nowrap="nowrap"></td>
					<td id="1" style=" padding: 3px;" nowrap="nowrap"></td>
					<td id="2" style="padding: 3px;" nowrap="nowrap"></td>
					<td id="3" style="padding: 3px;" nowrap="nowrap"></td>
					<td id="4" style="padding: 3px;" nowrap="nowrap"></td>
					<td id="5" style=" padding: 3px;" nowrap="nowrap"></td>
				</tr>
			</table>
		</div>
		<div style="height: 1px;"></div>
		<div>
			<div id="map">
			</div>
		</div>
		<table style="display: none;">
			<tr id="loadingGif">
				<td colspan="10" style="width: 100%; height: 50px;">
					<img src="images/loading.gif">
				</td>
			</tr>
		</table>
	</body>
</html>
