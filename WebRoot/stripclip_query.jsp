<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>stripclip_query.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<link href="Css/TestDate.css" rel="stylesheet">
	<%--背景--%>
</head>
<frameset  rows="70%,*" framespacing="1" frameborder="no"
	border="1">
	<%--<frameset  cols="90%,*" framespacing="2" frameborder="no"
	border="1">
			--%>
			<frame src="stripclip_query_top.do?method=getWordlib" name="top"
			scrolling="auto" id="top"
			title="top" /><%--
			
			<frame src="stripclip_query_right.jsp" name="top_right"
			id="top_right" title="top_right" />

	</frameset>		
			--%><frame src="stripclip_query_down.jsp" name="down"
			id="down" title="down" />
			
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html:html>
