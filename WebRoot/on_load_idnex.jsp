
<%@ page language="java" pageEncoding="UTF-8"%>



<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>on_load_idnex.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>

  </head>
  
<%--<frameset rows="*" cols="450,*"   frameborder="no" border="1">
--%>
<frameset cols="50%,*"   frameborder="no" border="1">
  <frame src="on_load_left.do?method=initialization" name="leftFrame" scrolling="auto"   id="leftFrame" title="leftFrame" />
  <frame src="on_load_right.jsp" name="mainFrame" id="mainFrame" title="mainFrame" />
</frameset>
<noframes><body>
</body>
</noframes>
</html:html>
