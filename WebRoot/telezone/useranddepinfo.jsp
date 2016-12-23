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
		<title>用户部门权限控制</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript" src="js/jquery.js"></script>
		
		<script type="text/javascript">
		
			var userid = "";
			var department = "";
			$(function(){
				$("#userid div").hover(function(){
					if($(this).attr("isColor")) {
					}else {
						$(this).css("background-color", "#C2E8FF");
					}
				},function(){
					if($(this).attr("isColor")) {
					}else {
						$(this).css("background-color", "");
					}
				}).bind("click", function(){
					$("#userid div[isColor='1']").css("background-color", "").removeAttr("isColor");
					
					$("#userid").text($(this).text());
					
					$(this).css("background-color", "#F5E1F0");
					$(this).attr("isColor", "1");
					$("#department input").removeAttr("checked");
					
					userid = $(this).text();
					
					$.ajax({
						type: "POST",
						url: "useranddep.do?method=getOuserDepartmentinfo&tempID=" + Math.random() * 10000,
						data: "userid=" + userid,
						dataType: "text",
						success: function(msg){
							var json = eval('('+ msg +')');
							
							$(json.reportpopedom).each(function(i, n){
								$("#department input[data='"+n.department+"']").attr("checked", "checked");
							});
						}
					});
				});
				
				$("#modifyUserReportPopedom").bind("click", function(){
					
					if(userid === "") {
						alert("请选择用户");
						return false;
					}
					department = "";
					
					$("#department input").each(function(i, n){
						if($(this).attr("checked")) {
							department = department + $(this).attr("data") + ",";
						}
					});
					
					$.ajax({
						type: "POST",
						url: "useranddep.do?method=modifyOuserDepartmentinfo&tempID=" + Math.random() * 10000,
						data: "userid=" + userid + "&department=" + department,
						dataType: "text",
						success: function(msg){
							var json = eval('('+ msg +')');
							
							if(json.isSuccess === '1') {
								alert("修改成功");
							} 
						}
					});
				});
			});
		</script>
	</head>

	<body bgColor="white" background="Image/right.gif">
		<div
			style="font-weight: bold; font-size: 13px; background-repeat: repeat; background-image: url(Image/title.bmp);">
			&gt;&gt;当前位置：用户部门权限设置&gt;&gt;<span id="userid"></span>
		</div>
		<div style="float: left; font-size: 12px;">
			<div style="float: left; width: 150px;" id="userid">
				<c:forEach items="${ ouser }" var="ouser">
					<div style="cursor: hand;">
						${ ouser.userid }
					</div>
				</c:forEach>
			</div>
			<div style="float: left; width: 450px;" id="department">
				<c:forEach items="${ staffer }" var="staffer">
					<div>
						<input type="checkbox" data="${ staffer.department }">
						${ staffer.department }
					</div>
				</c:forEach>
			</div>
		</div>
		<div>
			<input type="button" value="修改" id="modifyUserReportPopedom">
		</div>
	</body>
</html>
