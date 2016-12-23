<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>transitTrcticRight.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>

  </head>
  
  <body bgColor="white" background="Image/right.gif"><%--
       <body bgColor="white" background="Image/right.gif" style="overflow:hidden">
       --%>
       <body bgColor="white" background="Image/right.gif">
      详细信息：
      <table width="800" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
          <tr>
             <td width="60" align="left" bgcolor="#B0C4DE" >卡号</td>
             <td width="120" align="left" bgcolor="#B0C4DE" >分站号或定位器号</td>
             <td width="120" align="left" bgcolor="#B0C4DE" >分站名称</td>
             <td width="100" align="left" bgcolor="#B0C4DE" >允许或禁止</td>
             <td width="100" align="left" bgcolor="#B0C4DE" >时间限定</td>
             <td width="80" align="left" bgcolor="#B0C4DE" >起始日期</td>
             <td width="60" align="left" bgcolor="#B0C4DE" >起始时间</td> 
             <td width="80" align="left" bgcolor="#B0C4DE" >终止日期</td>
             <td width="60" align="left" bgcolor="#B0C4DE" >终止时间</td> 
          </tr>
          
     </table>
  </body>
</html:html>
