
<%@ page language="java" import="java.util.*,com.kj133.entity.vo.*" pageEncoding="UTF-8"%>
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
    <title>sys_wordRight.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script language="javascript" src="js/page.js"></script>
    <script language="JavaScript" src="js/calendar.js"></script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/CardIdORName.js"></script>
	<%
		String wordValue = "no";
		List list = (List)request.getAttribute("relist");
		if(list!=null)
		{
			 for(int i=0;i<list.size();i++)
			 {
			 	SysWordLeftVO vo = (SysWordLeftVO)list.get(i);
			 	if(vo.getWordvalue()!=null&&vo.getWordvalue().trim().length()>0&&vo.getWordvalue().equals("范围限定"))
			 		wordValue = vo.getWordvalue();
		 	 }
		 }
		 
	 %>
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
    
  
    
    --%><SCRIPT language="javascript">
       function check()
       {
            var wordvalue=document.all['wordvalue'].value;
           
             if( wordvalue == "" )
                 {
                  
                  alert("系统词值不能为空");
                  document.all['wordvalue'].focus();
                  return false;
                }
              return true;
       }
       function checkName()
       {
       		var v1 = '<%=wordValue%>';
       		if(v1!='no')
       		{
       			document.getElementById("submit").disabled = true;
       		}
       		else
       		{
       			document.getElementById("submit").disabled = false;
       			
       		}
       }
    </SCRIPT>
</head>
  
  <body bgColor="white" background="Image/right.gif" onload="checkName();">
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     
    <html:form action="sys_wordRight?method=add" onsubmit="return check()" focus="wordvalue" target="rightFrame"  >
   <table>
    <TD>系统词值：</TD>
	<TD><html:text  property="wordvalue" size="15" maxlength="8" /></TD>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<TD><input type="submit" id="submit" value="增 加"> 
	</TD>
	</table>
    <table  width="200" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
               <tr>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >删改</td>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >系统词值</td>
                   
        
               </tr>
               <logic:present name="relist" scope="request">
                 <logic:iterate name="relist" id="dw">
                     <tr>
                       <td align="left" bgcolor="#E6E6FA">
                       <a href="sys_wordRight.do?method=load&recordid=<bean:write name="dw" property="recordid" />&valuetype=<bean:write name="dw" property="valuetype" />&wordvalue=<bean:write name="dw" property="wordvalue" />" title="修改最大最小值" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="rightFrame" >
                       <bean:write name="dw" property="valuetype" />
                       </a>
                       </td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="wordvalue" /></td>
                       
                       
                    </tr>  
                 </logic:iterate>
               </logic:present>
               
         </table>   
        
         </html:form>     
  </body>
</html:html>
