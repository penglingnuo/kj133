<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>page_range.jsp</title>
	<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<link href="Css/TestDate.css" rel="stylesheet">
	<%--背景--%>
</head>
<frameset cols="*" rows="50%,*" framespacing="1" frameborder="no"
	border="1">
	<frameset cols="30%,*" framespacing="1" frameborder="no"
	border="1">
			<frame src="page_range_topleftadd.jsp" name="topleft"
			scrolling="auto" noresize="noresize" id="topleft"
			title="topleft" />

			
			<frame src="page_range_topright.do?method=getList" name="topright"
			id="topright" title="topright" />
			
   </frameset>
   <frameset cols="30%,*" framespacing="1" frameborder="no"
	border="1">
			<frame src="page_range_downleft.do?method=getWordlib" name="downleft"
			scrolling="auto" noresize="noresize" id="downleft"
			title="downleft" />

			
			<frame src="page_range_downright.jsp" name="downright"
			id="downright" title="downright" />
			
   </frameset>
			
			
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html:html>
