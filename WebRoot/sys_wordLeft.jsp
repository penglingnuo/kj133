
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="系统词库维护" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>sys_wordLeft.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script language="javascript" src="js/page.js"></script>
    <script language="JavaScript" src="js/calendar.js"></script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/CardIdORName.js"></script>

        <style TYPE="text/css">
		<!--
		 A:link{color: #6699CC;text-decoration:none}
		 A:hover{color:#FF0000;}
		 -->
    </style>
    <%--
    
    <style type="text/css">
	    .mouseOut {
	    background: #708090;
	    color: #FFFAFA;
	    }
	
	    .mouseOver {
	    background: #FFFAFA;
	    color: #000000;
	    }
    </style>
    
  --%></head>
  
  <body bgColor="white" background="Image/right.gif" >
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     
    <html:form action="sys_wordLeft?method=getList"  >
    <table  width="400" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
               <tr>
                   <td width="70"  align="left" bgcolor="#B0C4DE" >系统词名称</td>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >词类型</td>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >最大值</td>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >最小值</td>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >最多个数</td>
        
               </tr>
               <logic:present name="relist" scope="request">
                 <logic:iterate name="relist" id="dw">
                     <tr>
                       <td align="left" bgcolor="#E6E6FA">
                       <a href="sys_wordRight.do?method=init&wordname=<bean:write name="dw" property="wordname" />&wordtype=<bean:write name="dw" property="wordtype" />&maxvalue=<bean:write name="dw" property="maxvalue" />&minvalue=<bean:write name="dw" property="minvalue" />&maxlines=<bean:write name="dw" property="maxlines" />" title="系统词值" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="rightFrame" >
                       <bean:write name="dw" property="wordname" />
                       </a>
                       </td>
                       <td align="left" bgcolor="#E6E6FA">
                       <a href="sys_wordLeftDown.do?method=load&wordname=<bean:write name="dw" property="wordname" />&maxvalue=<bean:write name="dw" property="maxvalue" />&minvalue=<bean:write name="dw" property="minvalue" />&maxlines=<bean:write name="dw" property="maxlines" />&wordvalue=<bean:write name="dw" property="wordvalue" />" title="修改最大最小值" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="leftFrame" >
                       <bean:write name="dw" property="wordtype" />
                       </a>
                       </td>
                       <td align="left" bgcolor="#E6E6FA">
                       
                       <bean:write name="dw" property="maxvalue" />
                       </td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="minvalue" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="maxlines" /></td>
                       
                    </tr>  
                 </logic:iterate>
               </logic:present>
               
         </table>   
        
         </html:form>     
  </body>
</html:html>
