
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="历史记录查询里的分站的定位信息"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>historyNote_down.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
  </head>
      <body bgColor="white" background="Image/right.gif">
           <%=request.getAttribute("cid")%>号分站的定位信息 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="返 回"  onClick="javascript:history.back()">
		     <TABLE  width="1331" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
		        <TR>
		          <TD width="46"  align="left" bgcolor="#B0C4DE">卡号</TD>
		          <TD width="55"  align="left" bgcolor="#B0C4DE">姓名</TD>
		          <TD width="93"  align="left" bgcolor="#B0C4DE">工种</TD>
		          <TD width="85"  align="left" bgcolor="#B0C4DE">班组</TD>
		          <TD width="54"  align="left" bgcolor="#B0C4DE">分站号</TD>
		          <TD width="72"  align="left" bgcolor="#B0C4DE">定位器号</TD>
		          <TD width="183" align="left" bgcolor="#B0C4DE">状态信息</TD>
		          <TD width="164" align="left" bgcolor="#B0C4DE">起始时间</TD>
		          <TD width="164" align="left" bgcolor="#B0C4DE">截止时间</TD>
		          <TD width="53"  align="left" bgcolor="#B0C4DE">地图号</TD>
		          <TD width="126" align="left" bgcolor="#B0C4DE">保持时间</TD>
		          <TD width="236" align="left" bgcolor="#B0C4DE">结束原因</TD>              
		        </TR>
		        <logic:present name="HistoryNote_Locator_List">
		          <logic:iterate name="HistoryNote_Locator_List" id="hlocator">
		            <TR onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
		                <TD align="left" bgcolor="#E6E6FA"><bean:write name="hlocator" property="cid" /></TD>
		                <TD align="left" bgcolor="#E6E6FA"><bean:write name="hlocator" property="username" /></TD>
		                <TD align="left" bgcolor="#E6E6FA"><bean:write name="hlocator" property="type" /></TD>
		                <TD align="left" bgcolor="#E6E6FA"><bean:write name="hlocator" property="gro" /></TD>
		                <TD align="left" bgcolor="#E6E6FA"><bean:write name="hlocator" property="rid" /></TD>
		                <TD align="left" bgcolor="#E6E6FA"><bean:write name="hlocator" property="lid" /></TD>
		                <TD align="left" bgcolor="#E6E6FA"><bean:write name="hlocator" property="info" /></TD>
		                <TD align="left" bgcolor="#E6E6FA"><bean:write name="hlocator" property="stime" /></TD>
		                <TD align="left" bgcolor="#E6E6FA"><bean:write name="hlocator" property="etime" /></TD>
		                <TD align="left" bgcolor="#E6E6FA"><bean:write name="hlocator" property="mid" /></TD>
		                <TD align="left" bgcolor="#E6E6FA"><bean:write name="hlocator" property="stay" /></TD>
		                <TD align="left" bgcolor="#E6E6FA"><bean:write name="hlocator" property="ov" /></TD>  
		            </TR>
		          </logic:iterate>
		        </logic:present>
		     </TABLE>
    </body>
</html:html>
