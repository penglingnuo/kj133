
<%@ page language="java" pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>sys_word.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
  </head>

<frameset cols="50%,*" frameborder="no" border="0" framespacing="1">
 <%--<frameset rows="50%,*" frameborder="no" border="0" framespacing="0">
 
  <frame src="sys_wordLeft.do?method=getList" name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" title="leftFrame" />
  <frame src="routDetail_main.jsp" name="leftdFrame" scrolling="auto" noresize="noresize" id="leftdFrame" title="leftdFrame" />
  </frameset>
  --%>
  <frame src="sys_wordLeft.do?method=getList" name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" title="leftFrame" />
  <frame src="editCard_down.jsp" name="rightFrame" id="rightFrame" title="rightFrame" scrolling="auto" noresize="noresize" /><%--
  <frame src="sys_wordRight.jsp" name="rightFrame" id="rightFrame" title="rightFrame" scrolling="auto" noresize="noresize" /><%--
  <frame src="editCard_down.jsp" name="mainFrame" id="mainFrame" title="mainFrame" scrolling="no" />
--%></frameset>
<noframes><body>
</body>
</noframes>
</html:html>
