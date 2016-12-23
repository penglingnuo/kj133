<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>transitTrcticSave.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
 <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <body bgColor="white" background="Image/right.gif" style="overflow:hidden">
      详细信息：
      <table cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
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
          <tr>
            <td colspan="9" bgcolor="#B0C4DE" align="center"><font color="red" size="3">已成功添加!</font></td>
          </tr>
     </table>
  </body>
</html:html>
