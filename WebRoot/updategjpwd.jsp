
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
        <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <title>修改登陆密码</title>
<jsp:include flush="true" page="/checkgjuser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <SCRIPT language="javascript">
      function check(){
         var password=document.all['ser_updatePassword.oldPassword'].value;
         var first=document.all['ser_updatePassword.firstPassword'].value;
         var second=document.all['ser_updatePassword.secondPassword'].value;
         if( password==""){
           alert('原密码不能为空');
           document.all['ser_updatePassword.oldPassword'].focus();
           return false;
         }if( first=="" ){
            alert('新密码不能为空');
            document.all['ser_updatePassword.firstPassword'].focus();
            return false;
         }
         if( first != second){
             alert('两次输入的密码不同，请重新输入');
             document.all['ser_updatePassword.firstPassword'].value="";
             document.all['ser_updatePassword.secondPassword'].value="";
             document.all['ser_updatePassword.firstPassword'].focus();
             return false;
         }
         return true;
      }
    </SCRIPT>
  </head>
  
  <body bgColor="white" background="Image/right.gif">
    <logic:messagesPresent message="true" >
        <html:messages id="message" message="true">
           <font color="red"><bean:write name="message" /></font>
        </html:messages>
    </logic:messagesPresent>
    <center>
    <a href="<%=path%>/gj.jsp">返回</a>
    <html:form action="gjuser?method=updategjpwd" focus="ser_updatePassword.oldPassword" onsubmit="return check()">
        <TABLE>
        	<tr>
        		<td>ID：</td>
        		<td>${gjuser.id}<html:hidden property="id" value="${gjuser.id}"/></td>
        	</tr>
        	<tr>
        		<td>用户名：</td>
        		<td>${gjuser.gjusername}<html:hidden property="gjusername" value="${gjuser.gjusername}"/></td>
        	</tr>
           <TR>
             <TD>原密码：</TD>
             <TD><html:password property="ser_updatePassword.oldPassword"   redisplay="false" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /></TD>
           </TR>
           <TR>
            <TD>新密码：</TD>
            <TD><html:password  property="ser_updatePassword.firstPassword"  maxlength="50" redisplay="false" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /></TD>
           </TR>
          <TR>
            <TD>确认密码：</TD>
            <TD><html:password  property="ser_updatePassword.secondPassword" maxlength="50" redisplay="false"  onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')"/></TD>
          </TR>  
           <TR>
            <TD colspan="2" align="center">
                 <html:submit>确定</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
                 <html:reset>取消</html:reset>
            </TD>
          
          </TR>           
        </TABLE>
    
    </html:form>
    </center>
  </body>
</html:html>
