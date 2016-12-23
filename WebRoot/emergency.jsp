
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>emergency.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
  </head><%--
  
	  <frameset rows="*" cols="370,*" frameborder="NO" border="0" framespacing="1">
	  --%>
	  <frameset cols="35%,*" frameborder="NO" border="0" framespacing="1">
	  <frame src="emergency_left.jsp" name="leftFrame">
	 
	    <frame src="emergency_top.jsp" name="topFrame">
	    <%--
	  <frame src="emergency_left.jsp" name="leftFrame" scrolling="NO" >
	 
	    <frame src="emergency_top.jsp" name="topFrame" scrolling="NO" >
		--%>
		</frameset>
	<noframes><body>
</body></noframes>
</html:html>
