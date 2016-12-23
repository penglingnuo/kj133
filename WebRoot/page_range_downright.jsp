
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="区域设置-区域范围列表" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>page_range_topright.jsp</title>
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
    </style><%--
    
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
     
    <html:form action="page_range_downright?method=getList"  >
    <td ><font size="2" color="red">区域范围列表:</font></td>
    <table  width="480" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
               <tr>
                   <td width="60"  align="left" bgcolor="#B0C4DE" >区域等级</td>
                   <td width="80"  align="left" bgcolor="#B0C4DE" >区域名称</td>
                   <td width="60"  align="left" bgcolor="#B0C4DE" >地点类型</td>
                   <td width="40"  align="left" bgcolor="#B0C4DE" >ID</td>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >名称</td>
        
               </tr>
               <logic:present name="relist" scope="request">
                 <logic:iterate name="relist" id="dw">
                     <tr>
                       <td align="left" bgcolor="#E6E6FA">
                       <bean:write name="dw" property="areaorder" />
                       </td>
                       <td align="left" bgcolor="#E6E6FA">
                       <bean:write name="dw" property="areaname" />
                       </td>
                       <td align="left" bgcolor="#E6E6FA">
                       <bean:write name="dw" property="type" />
                       </td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="id" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="shortname" /></td>
                       
                    </tr>  
                 </logic:iterate>
               </logic:present>
               
         </table>   
        
         </html:form>     
  </body>
</html:html>
