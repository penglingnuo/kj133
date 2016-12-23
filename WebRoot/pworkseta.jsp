
<%@ page language="java" pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>pworkseta.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
  </head>

<frameset rows="50%,*" frameborder="no" border="0" framespacing="1">

  <frame src="pworkset.do?method=siteinit&sid=<%=request.getAttribute("sid9") %>&name=<%=request.getAttribute("name9") %>&type=<%=request.getAttribute("type9") %> " name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" title="leftFrame" />
  <frame src="pworkset.do?method=timeinit&sid=<%=request.getAttribute("sid9") %>&name=<%=request.getAttribute("name9") %>&type=<%=request.getAttribute("type9") %> " name="rightFrame" id="rightFrame" title="rightFrame" scrolling="auto" noresize="noresize" />
  </frameset>
<noframes><body>
</body>
</noframes>
</html:html>
