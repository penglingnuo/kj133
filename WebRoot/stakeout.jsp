
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%
	String ipResult1 = (String)request.getAttribute("ipResult1");
	String ipResult2 = (String)request.getAttribute("ipResult2");
	String ipResult3 = (String)request.getAttribute("ipResult3");
	String ipResult4 = (String)request.getAttribute("ipResult4");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>stakeout.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    
  </head>
  
  <body bgColor="white" background="Image/right.gif">
    <logic:messagesPresent message="true" >
        <html:messages id="message" message="true">
           <font color="red"><bean:write name="message" /></font>
        </html:messages>
    </logic:messagesPresent>
    <html:form action="/stakeout?method=getList">
        <TABLE>
           <TR>
             <td align="right"><font size="4" color="blue">模 块</font></td>
             <td>&nbsp;&nbsp;&nbsp;</td>
             <td align="left"><font size="4" color="blue">状 态</font></td>
          </TR>  
           <TR>
             
             <td>&nbsp;&nbsp;&nbsp;</td>
             
          </TR>
          <TR>
             <td align="right">数据库：</td>
             <td>&nbsp;&nbsp;&nbsp;</td>
             
             <th align="left">
             <logic:equal  name="listCount1" value="0">
			 	<font size="2" color="228B22"><%= ipResult1 %></font>
			 </logic:equal>
			 <logic:notEqual name="listCount1" value="0">  
			    <font size="2" color="red"><%= ipResult1 %></font>
			 </logic:notEqual>
            </th>
          
          </TR>
          <TR>
             <td align="right">网络连接：</td>
             <td>&nbsp;&nbsp;&nbsp;</td>
             
             <th align="left">
             <logic:equal  name="listCount2" value="0">
			 	<font size="2" color="228B22"><%= ipResult2 %></font>
			 </logic:equal>
			 <logic:notEqual name="listCount2" value="0">  
			    <font size="2" color="red"><%= ipResult2 %></font>
			 </logic:notEqual>
             </th>
         
          </TR>  
          <TR>
             <td align="right">数据采集：</td>
             <td>&nbsp;&nbsp;&nbsp;</td>
             
             <th align="left">
             <logic:equal  name="listCount3" value="0">
			 	<font size="2" color="228B22"><%= ipResult3 %></font>
			 </logic:equal>
			 <logic:notEqual name="listCount3" value="0">  
			    <font size="2" color="red"><%= ipResult3 %></font>
			 </logic:notEqual>
             </th>
         
          </TR>
          <TR>
             <td align="right">web服务：</td>
             <td>&nbsp;&nbsp;&nbsp;</td>
             <th align="left">
             <logic:equal  name="listCount4" value="0">
			 	<font size="2" color="228B22"><%= ipResult4 %></font>
			 </logic:equal>
			 <logic:notEqual name="listCount4" value="0">  
			    <font size="2" color="red"><%= ipResult4 %></font>
			 </logic:notEqual>
             </th>
          
          </TR>  
          <%--<TR>
             <td align="right">打 印：</td>
             <td>&nbsp;&nbsp;&nbsp;</td>
             <th align="left">
             <logic:equal  name="listCount1" value="0">
			 	<font size="2" color="228B22"><%= ipResult1 %></font>
			 </logic:equal>
			 <logic:notEqual name="listCount1" value="0">  
			    <font size="2" color="red"><%= ipResult1 %></font>
			 </logic:notEqual>
             </th>
          </TR>  
                       
        --%>
        </TABLE>
    
    </html:form>
  </body>
</html:html>
