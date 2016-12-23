
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="区域设置-区域列表" %>
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
    <html:form action="page_range_topright?method=getList"  >
     <td ><font size="2" color="red">区域列表:</font></td><%--
    <table  width="570" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
    --%>
    <table  width="100%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
               <tr>
                   <td width="80"  align="left" bgcolor="#B0C4DE" >区域名称</td>
                   <td width="80"  align="left" bgcolor="#B0C4DE" >所属区域</td>
                   <td width="60"  align="left" bgcolor="#B0C4DE" >区域等级</td>
                   <td width="60"  align="left" bgcolor="#B0C4DE" >区域类型</td>
                   <td width="40"  align="left" bgcolor="#B0C4DE" >定员数</td>
                   <td width="70"  align="left" bgcolor="#B0C4DE" >采掘定员数</td>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >超时时间</td>
                   <td width="120"  align="left" bgcolor="#B0C4DE" >说明</td>
        
               </tr>
               <logic:present name="relist" scope="request">
                 <logic:iterate name="relist" id="dw">
                     <tr>
                       <td align="left" bgcolor="#E6E6FA">
                       <a href="page_range_topleft.do?method=init&areaname=<bean:write name="dw" property="areaname" />&parentarea=<bean:write name="dw" property="parentarea" />&areaorder=<bean:write name="dw" property="areaorder" />&areatype=<bean:write name="dw" property="areatype" />&maxsum=<bean:write name="dw" property="maxsum" />&caijuesum=<bean:write name="dw" property="caijuesum" />&stayminute=<bean:write name="dw" property="stayminute" />&areainfo=<bean:write name="dw" property="areainfo" />" title="区域设置" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="topleft" >
                       <bean:write name="dw" property="areaname" />
                       </a>
                       
                       </td>
                       <td align="left" bgcolor="#E6E6FA">
                       <bean:write name="dw" property="parentarea" />
                       </td>
                       <td align="left" bgcolor="#E6E6FA">
                       <a href="page_range_downright.do?method=getList&areaname=<bean:write name="dw" property="areaname" />&areaorder=<bean:write name="dw" property="areaorder" />" title="区域范围列表" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="downright" >
                       <bean:write name="dw" property="areaorder" />
                       </a>
                       </td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="areatype" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="maxsum" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="caijuesum" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="stayminute" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="areainfo" /></td>
                       
                    </tr>  
                 </logic:iterate>
               </logic:present>
               
         </table>   
        
         </html:form>     
  </body>
</html:html>
