<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>transitTrctic.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
  </head>
  
	  <%--<frameset rows="*" cols="173*,617*" framespacing="0" frameborder="NO" border="0">
	  --%>
	  <frameset cols="20%,*" framespacing="1" frameborder="no" border="0">
	  <frame src="transitTrctic.do?method=init" name="leftFrame" >
	  <frame src="transitTrctic.do?method=show" name="mainFrame">
	</frameset>
	<noframes><body>

</body></noframes>
</html:html>
