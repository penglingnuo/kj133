<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
<!--这里的样式的重点是指当鼠标移到三角按钮时变成手掌-->
<%--<style type="text/css">
.navPoint {COLOR: white; CURSOR: hand; FONT-FAMILY: Webdings; FONT-SIZE: 9pt}
.a2{BACKGROUND-COLOR: A4B6D7;}
</style>
	--%>
	<title>北京天一众合KJ133矿用人员管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<script type="text/javascript" src="js/index.js"></script>
</head>
<META name=GENERATOR>

<FRAMESET border=1 frameSpacing=2 rows=70,* frameBorder=yes cols=* bordercolor="#9FB6CD">
	<FRAME id="top" name=top src="menu_top.jsp" scrolling="no" noresize >
		<%--	<FRAMESET border=0 frameBorder=YES cols=165,5,* bordercolor="#9FB6CD" id="frame">--%>
		<FRAMESET border=0 frameSpacing=0 frameBorder=no cols="12.5%,0.5%,86%" bordercolor="#9FB6CD" id="frame">
			<FRAME id="tree" name=tree src="popedom.do?method=showPage" frameborder="0"  noresize/>
			<FRAME src="center.html" name="centerFrame"  id="centerFrame" frameborder="0" scrolling="no" noresize/>
			<FRAME id="content" name="content" src="workatt.do?method=initialization" frameborder="0" noresize />
		</FRAMESET>

	<FRAME id="AlarmWindow" name="AlarmWindow" src="telezone/window.jsp" scrolling="no" >

</FRAMESET>

</html:html>
