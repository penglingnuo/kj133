
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page info="登录"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />


	<title>KJ133管技查询登陆</title>
	<script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script>
	<link href="Css/masa.css" rel="stylesheet">
	<%--背景--%>
	<script language="javascript">
         function check(){
            var name=document.all['gjuser.gjusername'].value;
            var password=document.all['gjuser.gjuserpwd'].value;
            if( name == ""){
                 alert("用户名不能为空")
                 document.all['gjuser.gjusername'].focus();
                 return false;
             }if( password == ""){
                  alert("密码不能为空")
                  document.all['gjuser.gjuserpwd'].focus();
                  return false;
             }
             return true;          
         }
         
         function init() {
         	//document.forms[0].submit(); 
		 }
     </script>
</head>

<%--<body bgColor="#244b8a" style="overflow:hidden">
--%>
<body bgColor="#244b8a" onload="init()">

	<html:form action="gjuser?method=gjuserLogin" focus="gjuser.gjusername"
		onsubmit="return check()" >
		
		<%--<table height="481" cellSpacing="0" cellPadding="0" width="100%" border="0">
		--%>
		<table height="80%" cellSpacing="0" cellPadding="0" width="100%" border="0">
			<tr>
				<td align="center" bgColor="#244b8a" height="135">
					<p>
						<br>
						<br>
						<br>
						<br>
						<font face="Arial Black" color="#ffffff" size="6">管技查询登陆</font>
					</p>
				</td>
			</tr>
			<tr>
				<td bgColor="#ffffff" height="1"></td>
			</tr>
			<tr>
				<td bgColor="#5f86c4">
					<IMG height="28" src="Image/login1_masa1.gif" width="25">
				</td>
			</tr>
			<tr>
				<td vAlign="bottom" bgColor="#5f86c4" height="199">
					<table cellSpacing="0" cellPadding="5" width="338" align="center"
						border="0">
						<tr>
							<td align="center">
								<IMG height="12" src="Image/login1_masa3.gif" width="12">
							</td>
							<td>
								<font color="white">用户名：</font>
							</td>
							<td>
								&nbsp;
								<FONT face="宋体"> <html:text property="gjuser.gjusername"
										 size="18" style="width:130px;" value=""
										onmouseover="this.style.background='#CCCCCC'; "
										onmouseout="this.style.background='#FFFFFF'; this.style.borderColor=''" />
								</font><font color="#ffffff">*</font><%--
								<FONT face="宋体"> <html:text property="user.userid"
										value="sys" size="18" style="width:130px;"
										onmouseover="this.style.background='#CCCCCC'; "
										onmouseout="this.style.background='#FFFFFF'; this.style.borderColor=''" />
								</font><font color="#ffffff">*</font>
							--%></td>
						</tr>
						<tr>
							<td align="center">
								<IMG height="12" src="Image/login1_masa3.gif" width="12">
							</td>
							<td>
								<font color="white">密&nbsp;&nbsp;码：</font>
							</td>
							<td>
								&nbsp;
								<html:password property="gjuser.gjuserpwd" value=""
									redisplay="false" size="18" style="width:130px;"
									onmouseover="this.style.background='#CCCCCC'; "
									onmouseout="this.style.background='#FFFFFF'; this.style.borderColor=''" />
								<font color="#ffffff">*</font>
							</td>
						</tr>
						<tr>
							<td align="center"></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td align="center">
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgColor="#5f86c4">
					<table cellSpacing="0" cellPadding="0" width="100%" border="0">
						<tr>
							<td>
								&nbsp;
							</td>
							<td vAlign="bottom" width="436"
								background="IMAGE/login1_masa4.gif" height="53" align="center">
								<table cellSpacing="0" cellPadding="0" width="75%"
									align="center" border="0">
									<tr align="center">
										<td align="center">
											<html:submit>登 录</html:submit>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<html:reset>取 消</html:reset>
										</td>
									</tr>
								</table>
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td vAlign="bottom" align="right" bgColor="#5f86c4" height="52">
					<IMG height="28" src="Image/login1_masa5.gif" width="25">
				</td>
			</tr>
			<tr>
				<td bgColor="#ffffff" height="1"></td>
			</tr>
		</table>
	</html:form>
	<table cellSpacing="0" cellPadding="0" width="100%" border="0">
		<tr>
			<td align="center">
				<font style="FONT-FAMILY: 'Courier New'" color="#ffffff">
					<br>北京天一众合科技发展有限责任公司
					<br> 
						<font style="FONT-SIZE: 8pt; FONT-FAMILY: Verdana">
							Beijing Telezone Technology Co., Ltd &nbsp; 版权所有 2008-2012 &nbsp; 版本 v1.0
						</font>
				</font>
			</td>
		</tr>
	</table>

</body>
</html:html>
