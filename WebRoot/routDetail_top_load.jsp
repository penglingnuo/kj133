
<%@ page language="java" pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>routDetail_top_load.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
  </head>
  
  <body bgColor="white" background="Image/right.gif">
     <html:form action="routDetail_top?method=update" focus="routDetail_top.routename" target="left_upFrame">
       <table>
          <tr>
            <td colspan="2" align="center">修 改 巡 检 路 线 设 置</td>
          </tr>
          <tr>
            <td>编号：</td>
            <td><html:text size="5" property="routDetail_top.code"  style="background-color: #dfdfdf"  readonly="true"/>&nbsp;&nbsp;&nbsp;
               路线名称：<html:text size="15" property="routDetail_top.routename"  maxlength="25" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" />
	         </td>
          </tr>
          <tr>
            <td>说明：</td>
            <td><html:text size="34" property="routDetail_top.routeinfo"  maxlength="95" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /></td>
          </tr>
          <tr>
             <td colspan="2" align="center"><html:submit>确 定</html:submit> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <html:reset>取 消</html:reset></td>
          </tr>
       </table>
      </html:form>
  </body>
</html:html>
