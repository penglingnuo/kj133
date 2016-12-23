
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	<head>
		<%
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
		%>
		<title>增加用户</title>
		<jsp:include flush="true" page="/checkgjuser.jsp"></jsp:include>
		<link href="Css/TestDate.css" rel="stylesheet">
		<%--背景--%>

		<SCRIPT language="JavaScript">
    
      function user() {
			var name=document.all['gjuser.gjusername'].value;
            var password=document.all['gjuser.gjuserpwd'].value;
            var qrpwd=document.all['gjuser.qruserpwd'].value;
            if( name == ""){
                 alert("用户名不能为空")
                 document.all['gjuser.gjusername'].focus();
                 return false;
             }if( password == ""){
                  alert("密码不能为空")
                  document.all['gjuser.gjuserpwd'].focus();
                  return false;
             }if( qrpwd == ""){
                  alert("确认密码不能为空")
                  document.all['gjuser.gjuserpwd'].focus();
                  return false;
             }if( qrpwd != password){
                  alert("两次输入的密码不一致，请重新输入")
                  document.all['gjuser.gjuserpwd'].focus();
                  return false;
             }
             return true;  
         }
         
         function checkgjusername(){
         	var gjusername=document.all['gjuser.gjusername'].value;
         	if(gjusername!=null&&gjusername!=""){
         		//window.parent.frames['content'].location.href="gjuser.do?method=checkgjusername&gjusername="+gjusername;
         		location.href="gjuser.do?method=checkgjusername&gjusername="+gjusername;
		        return true;
         	}else{
         		alert("用户名不能为空");
         		return false;
         	}
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
			<a href="<%=path%>/gj.jsp">返回</a><p>
			<b><font size="5">增加用户</font></b>
			<html:form  action="gjuser.do?method=addgjuser" onsubmit="return user()"
				method="post">
				<Table border="1" width="420" height="150" bordercolor="#99CCCC">
					<TR>
						<TD>
							<TABLE align="center">
								<TR>
									<td>
										用户名：
									</td>
									<td>
										<html:text property="gjuser.gjusername" value=""/>
									</td>
								</TR>
								<TR>
									<td>
										密码：
									</td>
									<td>
										<html:password property="gjuser.gjuserpwd" value="" />
									</td>
								</TR>
								<TR>
									<td>
										确认密码：
									</td>
									<td>
										<html:password property="gjuser.qruserpwd" value=""/>
									</td>
								</TR>
								<tr>
									<td colspan="2" align="center">
										<html:submit>提交</html:submit>
										<html:reset>取消</html:reset>
									</td>
								</tr>
							</TABLE>
						<TD>
					</TR>
				</TABLE>
			</html:form>
		</center>
	</body>
</html:html>


