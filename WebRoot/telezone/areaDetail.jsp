<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>areaDetail.jsp</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
	</head>

	<body>
		<div
			style="font-weight: bold; font-size: 13px; background-repeat: repeat; background-image: url(Image/title.bmp);">
			&gt;&gt;当前位置：当前井下区域人员
		</div>
		<div id="searchDiv">
			<span id="searchInput"> <input id="keyWord" type="text"
					size="10"> </span>
			<span id="searchButton"> <input id="submit" type="button"
					value="查找"></input> </span>
		</div>
		<div id="showData">
			<table cellpadding="0" cellspacing="0" border="1"
				style="border: 1px solid gray; font-size: 12px; text-align: center; width: 100%;">
				<tr>
					<td>
						卡号
					</td>
					<td>
						姓名
					</td>
					<td>
						部门
					</td>
					<td>
						分站
					</td>
					<td>
						下井时间
					</td>
					<td>
						区域名称
					</td>
				</tr>
				<c:forEach items="${ Data }" var="data">
					<tr>
						<td>
							${ data.cardid }
						</td>
						<td>
							${ data.staffername }
						</td>
						<td>
							${ data.stafferdepartment }
						</td>
						<td>
							${ data.cardreadername }
						</td>
						<td>
							${ data.downtime }
						</td>
						<td>
							${ data.antenna }
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</html>
