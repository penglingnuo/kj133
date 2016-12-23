<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>routDetail.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<link href="Css/TestDate.css" rel="stylesheet">
	<%--背景--%>
</head>
<%--<frameset rows="*" cols="*,440" framespacing="0" frameborder="no" border="1">
--%>
<frameset  cols="55%,*" framespacing="0" 
	border="1">
	<frameset rows="230,230" cols="*" framespacing="0" frameborder="no"
		border="0">
		<frame src="routDetail_top.do?method=init" name="left_upFrame"
			scrolling="auto" noresize="noresize" id="left_upFrame"
			title="left_upFrame" />

		<frame src="routDetail_right_init.jsp" scrolling="auto"
			noresize="noresize" name="left_downFrame" id="left_downFrame"
			title="left_downFrame" />
		<%--<frame src="routDetail_right.do?method=init&code=aa" scrolling="auto"
			noresize="noresize" name="left_downFrame" id="left_downFrame"
			title="left_downFrame" />
	--%>
	</frameset>

	<frameset rows="230,230" cols="*" framespacing="0" frameborder="no"
		border="1">
		<frame src="routDetail_top_load.jsp" name="right_upFrame"
			id="right_upFrame" title="right_upFrame" />
		<frame src="routDetail_right_load.jsp" name="right_downFrame"
			id="right_downFrame" title="right_downFrame" />
	</frameset>

</frameset>
<%--  <frameset rows="*" cols="*,440" framespacing="0" frameborder="no" border="0">--%>
<%--  <frameset rows="330,*" cols="*" framespacing="0" frameborder="no" border="0">--%>
<%--    <frame src="routDetail_top.do?method=init" name="topFrame" scrolling="auto" noresize="noresize" id="topFrame" title="topFrame" />--%>
<%----%>
<%--   <frame src="routDetail_main.jsp" name="mainFrame" id="mainFrame" title="mainFrame" />--%>
<%--    </frameset>--%>
<%--  <frame src="routDetail_right.jsp" name="rightFrame" scrolling="auto" noresize="noresize" id="rightFrame" title="rightFrame" />--%>
<%--</frameset>--%>
<noframes>
	<body>
	</body>
</noframes>
</html:html>
