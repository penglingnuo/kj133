<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <%
    String cid = (String)request.getAttribute("cid");
    
    
     %>
    <title>move_Area_Particular.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
       <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
       <script language="javascript" src="js/page.js"></script>
  <style TYPE="text/css">
		<!--
		 A:link{color:#FF0000;text-decoration:none} /*   未访问的链接   */ 
		  A:hover{color:#FF0000;text-decoration:none} /*   鼠标在链接上   */
		  A:visited{color:#FF0000;text-decoration:none}/*   已访问的链接   */ 
		 -->
    </style>
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
  </head>
  <body bgColor="white" background="Image/right.gif">
    <html:form action="/moveArea_left.do?method=particular1" >
     <%=cid%>号分站&nbsp;详细信息： 
    <input type="button" value="返 回" onClick="javascript:history.back()">
    
    <TABLE width="580" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
    
        <TR>
          <TD align="left" width="60"  bgcolor="#B0C4DE">定位器号</TD>
          <TD align="left" width="200" bgcolor="#B0C4DE">定位器名称</TD>
          <TD align="left" width="160" bgcolor="#B0C4DE">进入时间</TD>
          <TD align="left" width="160" bgcolor="#B0C4DE">离开时间</TD>
        </TR>
        <logic:present name="Move_Area_Particular_List">
             <logic:iterate name="Move_Area_Particular_List" id="particular">
               <TR onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
                 <TD align="left" bgcolor="#E6E6FA"><bean:write name="particular" property="lid"/></TD>
                 <TD align="left" bgcolor="#E6E6FA"><bean:write name="particular" property="shortname"/></TD>
                 <TD align="left" bgcolor="#E6E6FA"><bean:write name="particular" property="stime"/></TD>
                 <TD align="left" bgcolor="#E6E6FA"><bean:write name="particular" property="etime"/></TD>
              </TR>
            </logic:iterate>
        </logic:present>
        <logic:present name="pagination" >
                  <tr>
					   <td colspan="4" align="left" bgcolor="#E6E6FA">
					        <page:pagination path="/moveArea_left.do?method=particular1" parameter="page" formName="Search_MoveArea_Form" />
					  </td>  
                   </tr> 
       </logic:present>
     </TABLE>
   </html:form>
  </body>
</html:html>
