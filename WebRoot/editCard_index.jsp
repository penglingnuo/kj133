
<%@ page language="java" pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>editCard_index.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
  </head>

<frameset rows="90%,*" frameborder="no" border="0" framespacing="1">
  <frame src="editcard.do?method=getWorktype" name="topFrame" scrolling="auto" noresize="noresize" id="topFrame" title="topFrame" />
  <frame src="editCard_down.jsp" name="mainFrame" id="mainFrame" title="mainFrame" scrolling="auto" noresize="noresize" /><%--
  <frame src="editCard_down.jsp" name="mainFrame" id="mainFrame" title="mainFrame" scrolling="no" />
--%></frameset>
<noframes><body>
</body>
</noframes>
</html:html>
