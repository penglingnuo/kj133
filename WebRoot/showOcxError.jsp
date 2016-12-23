<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>showOcxError.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <style TYPE="text/css">
	<!--
	  A:link{color:#FF0000;text-decoration:none} /*   未访问的链接   */ 
	  A:hover{color:#FF0000;text-decoration:none} /*   鼠标在链接上   */
	 
	 -->
    </style>
  </head>
  
  <body>
      <div>
             要使用此控件，需要进行参数设置，<a href="showOcxAdd.jsp">单 击 此 处</a>，进行设置。<br>
           <IMG style="FILTER: Alpha(opacity=40,style=0);" src="images/showOcxError.bmp" width="750" height="460" class="A:link" >
       </div>
  </body>
</html:html>
