
<%@ page language="java"  pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>menu_top.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    
  </head>
  
  <body bgColor="#FFFFFF" background="Image/right.gif">
	<div>
   		<font face="Arial Black" color="#000000" size="5">板庙子矿人员定位系统</font>
    </div> 
	<div id="jnkc" align="right" style="padding-right: 50px; padding-bottom: 5px;">
	</div>
	<script>
		setInterval("jnkc.innerHTML=new Date().toLocaleString();",1000);
	</script>
  </body>
</html:html>
