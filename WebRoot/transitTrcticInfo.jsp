<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="根据标题得到详细信息"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>transitTrcticInfo.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>

  </head>
  
  <body bgColor="white" background="Image/right.gif"  >
      详细信息：
      <table  width="1060" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
          <tr>
             <td width="60" align="left" bgcolor="#B0C4DE" >卡号</td>
             <td width="60" align="left" bgcolor="#B0C4DE" >分站号</td>
             <td width="150" align="left" bgcolor="#B0C4DE" >分站名称</td>
             <%--<td width="80" align="left" bgcolor="#B0C4DE" >定位器号</td>
             <td width="150" align="left" bgcolor="#B0C4DE" >定位器名称</td>
             --%><td width="100" align="left" bgcolor="#B0C4DE" >允许或禁止</td>
             <td width="100" align="left" bgcolor="#B0C4DE" >时间限定</td>
             <td width="100" align="left" bgcolor="#B0C4DE" >起始日期</td>
             <td width="80" align="left" bgcolor="#B0C4DE" >起始时间</td> 
             <td width="100" align="left" bgcolor="#B0C4DE" >终止日期</td>
             <td width="80" align="left" bgcolor="#B0C4DE" >终止时间</td> 
          </tr>
          <logic:present name="infoList" scope="request">
                 <logic:iterate name="infoList" id="info">
                     <tr>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="cardid" /></td>
                       <logic:equal name="info" property="cardreaderid"  value="0">
                           <td align="left" bgcolor="#E6E6FA"> </td>
                           <td align="left" bgcolor="#E6E6FA"> </td>
                       </logic:equal>
                       <logic:notEqual name="info" property="cardreaderid" value="0">
                          <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="cardreaderid" /></td>
                          <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="shortname" /></td>
                       </logic:notEqual>
                       <%--<logic:equal name="info" property="locatorid" value="0">
                            <td align="left" bgcolor="#E6E6FA"> </td>
                            <td align="left" bgcolor="#E6E6FA"> </td>
                       </logic:equal>
                       <logic:notEqual name="info" property="locatorid" value="0">
                            <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="locatorid" /></td>
                            <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="lname" /></td>
                       </logic:notEqual>
                       --%><td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="alloworestop" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="limit" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="sriqi" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="stime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="eriqi" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="etime" /></td>
                     </tr>
                 </logic:iterate>
           </logic:present>
      </table>
  </body>
</html:html>
