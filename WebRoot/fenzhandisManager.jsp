
<%@ page language="java" pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>fenzhandisManager.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include> 
  </head>
	<frameset rows="*" cols="280,*" framespacing="0" frameborder="NO" border="0">
		  <frame  src="fenzhandisleft.do?method=getList"  name="leftFrame" scrolling="auto" noresize>
		  <frame src="fenzhandisright.jsp" name="mainFrame">
    </frameset>
		<noframes>
		  <body></body>
		 </noframes>
</html:html>
