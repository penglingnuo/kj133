<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>showOcx.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include> 
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
	<script type="text/javascript" >
	     function init(){
	        loadMap.WebInitialize('<%= request.getSession().getAttribute("con").toString() %>','<%= request.getAttribute("uid").toString() %>','<%= request.getAttribute("ip").toString() %>','<%= request.getAttribute("mapid").toString() %>',<%= request.getAttribute("post") %>,<%= request.getAttribute("keepdatatime") %>,<%= request.getAttribute("overminute") %>,<%= request.getAttribute("removeminute") %>,<%= request.getAttribute("locatetype") %>,<%= request.getAttribute("lostlocator") %>);
		}
		
	</script>
	
  </head>
	
<body onload="init()" background="Image/right.gif">
  
  
  <div> 
      <OBJECT
	      id="loadMap"
		  classid="clsid:141A0AE4-DB9E-4D20-9C13-04A14F2DBBA4"
		  width="100%"
		  height="100%"
		  align=left
		  hspace=0
		  vspace=0
	   >
	</OBJECT>
   </div>
   <br />
 
   
  </body>
</html:html>
