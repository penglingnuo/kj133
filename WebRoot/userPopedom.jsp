<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="权限管理"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>userPopedom.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
     <style TYPE="text/css">
		<!--
		 A:link{color:#FF0000;text-decoration:none} /*   未访问的链接   */ 
		 A:hover{color:#FF0000;text-decoration:none} /*   鼠标在链接上   */
		 a:visited{color:#FF0000;text-decoration:none}/*   已访问的链接   */ 
		 -->
    </style>
  </head>
  
  <body bgColor="white" background="Image/right.gif">
  <logic:messagesPresent message="true">
			  <html:messages id="message" message="true">
			     <font color="red"><bean:write name="message" /></font>
			  </html:messages>
       </logic:messagesPresent>
     <table cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" >
        <tr>
           <td width="80"  height="25" align="left" bgcolor="#B0C4DE" >用户号</td>
           <td width="80"  align="left" bgcolor="#B0C4DE" >用户姓名</td>
           <td width="150" align="left" bgcolor="#B0C4DE" >创建时间</td>
           <td  width="120" align="center" bgcolor="#B0C4DE" >权限设置</td>
        </tr>
        <logic:present name="userList">
           <logic:iterate name="userList" id="list">
               <tr>
                  <td height="20" align="left" bgcolor="#E6E6FA" ><bean:write name="list" property="userid" /></td>
                  <td align="left" bgcolor="#E6E6FA" ><bean:write name="list" property="oname" /></td>
                  <td align="left" bgcolor="#E6E6FA" ><bean:write name="list" property="createtime" /></td>
                  <logic:empty name="list" property="uid">
                      <td align="center" bgcolor="#E6E6FA" >
                         <a href="popedom.do?method=init&uid=<bean:write name='list' property='userid' />&query=123" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="a">增加权限</a>
                      </td>
                  </logic:empty>
                  
                  <logic:notEmpty  name="list" property="uid">
                     <td align="center" bgcolor="#E6E6FA" >
                        <a href="popedom.do?method=loadCheck&uid=<bean:write name='list' property='userid' />&query=123" onmousemove="javascript:window.status=''" onmouseout="javascript:window.status=''" class="a"><font color="#CD5C5C">修改权限</font></a>
                     </td>
                  </logic:notEmpty>
               </tr>
           </logic:iterate>
        </logic:present>
     </table>
  </body>
</html:html>
