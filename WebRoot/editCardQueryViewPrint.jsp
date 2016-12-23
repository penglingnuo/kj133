<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>editCardQueryViewPrint.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
 <body bgcolor="#999999999">
   <table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr>
  <td width="50%">&nbsp;</td>
  <td align="left">
    <hr size="1" color="#000000">
    <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
		<%
		    int  pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		    int  lastPageIndex=Integer.parseInt(request.getAttribute("lastPageIndex").toString());
		    
			if (pageIndex > 0)
			{
		%>
		        <td><a href="editCardQuery.do?method=viewPrint&page=0"><img src="images/first.GIF" border="0"></a></td>
		        <td><a href="editCardQuery.do?method=viewPrint&page=<%=pageIndex - 1%>"><img src="images/previous.GIF" border="0"></a></td>
		<%
			}
			else
			{
		%>
		        <td><img src="images/first_grey.GIF" border="0"></td>
		        <td><img src="images/previous_grey.GIF" border="0"></td>
		<%
			}
		
			if (pageIndex < lastPageIndex)
			{
		%>
		        <td><a href="editCardQuery.do?method=viewPrint&page=<%=pageIndex + 1%>"><img src="images/next.GIF" border="0"></a></td>
		        <td><a href="editCardQuery.do?method=viewPrint&page=<%=lastPageIndex%>"><img src="images/last.GIF" border="0"></a></td>
		<%
			}
			else
			{
		%>
		        <td><img src="images/next_grey.GIF" border="0"></td>
		        <td><img src="images/last_grey.GIF" border="0"></td>
		<%
			}
		%>
		<td width="6%" height="1%">&nbsp;&nbsp;&nbsp;
          <APPLET  CODE = "PrinterApplet.class" CODEBASE = "applets" ARCHIVE = "jasperreports-2.0.2-applet.jar, PrinterApplet.jar" WIDTH = "255" HEIGHT = "37">
            <PARAM NAME = CODE VALUE = "PrinterApplet.class" >
            <PARAM NAME = CODEBASE VALUE = "applets" >
            <PARAM NAME = ARCHIVE VALUE = "jasperreports-2.0.2-applet.jar,PrinterApplet.jar" >
            <PARAM NAME="type" VALUE="application/x-java-applet;version=1.2.2">
            <PARAM NAME="scriptable" VALUE="false">
            <PARAM NAME = "REPORT_URL" VALUE ="../editCardQuery.do?method=printEditCard">
         </APPLET>
         </td>
      <td width="50%">&nbsp;&nbsp;&nbsp;<input type="button" value="返  回"  onclick="javascript:window.history.back();"></td>
      </tr>
    </table>
    <hr size="1" color="#000000">
  </td>
  <td width="50%">&nbsp;</td>
</tr>
<tr>
  <td width="50%">&nbsp;</td>
  <td align="center">

   <%=  request.getAttribute("sb").toString()%>

  </td>
  <td width="50%">&nbsp;</td>
</tr>
</table>
  </body>
</html:html>
