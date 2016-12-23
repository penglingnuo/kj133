
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>pworksetIn.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
  </head>

<frameset cols="40%,*" frameborder="no" border="0" framespacing="1">
 <%--<frameset rows="50%,*" frameborder="no" border="0" framespacing="0">
 
  <frame src="sys_wordLeft.do?method=getList" name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" title="leftFrame" />
  <frame src="routDetail_main.jsp" name="leftdFrame" scrolling="auto" noresize="noresize" id="leftdFrame" title="leftdFrame" />
  </frameset>
  --%>
  <frame src="pworkset.do?method=init" name="aleftFrame" scrolling="auto" noresize="noresize" id="aleftFrame" title="aleftFrame" />
  <%--<frame src="pworkseta.jsp" name="arightFrame" scrolling="auto" noresize="noresize" id="arightFrame" title="arightFrame" />--%>
  <frameset rows="50%,*" frameborder="no" border="0" framespacing="1">

  <frame src="pworkset.do?method=siteinit1" name="topFrame" scrolling="auto" noresize="noresize" id="topFrame" title="topFrame" />
  <frame src="pworkset.do?method=timeinit1" name="downFrame" id="downFrame" title="downFrame" scrolling="auto" noresize="noresize" />
  </frameset>

  </frameset>
<noframes><body>
</body>
</noframes>
</html:html>
