
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>登陆错误页</title>
    
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
   
  </head>
  
  <body bgColor="white"  style="overflow:hidden">   
      <html:form action="/login?method=userLogin" focus="user.opassword" onsubmit="return check()">
	      <div align="center">
	        <br><br><br><br><br><br>
          <table  width="600" height="300"  cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
               <tr>
                   <td width="1000"  height="300"  bgcolor="#E0EEEE" >
	                     <div align="center">
		                       <table width="300">
			                          <tr>
			                            <td><font color="red">&nbsp;确认是否有以下情况：</font></td>
			                          </tr>
			                          <tr>
			                            <td>&nbsp;</td>
			                          </tr>
			                          <tr>
			                            <td>&nbsp;&nbsp;·您的键盘是否锁定大写？</td>
			                          </tr>
			                          <tr>
			                            <td>&nbsp;&nbsp;&nbsp;如果是这样，请转为小写，密码是区分大小写的。</td>
			                          </tr>
			                          <tr>
			                            <td>&nbsp;</td>
			                          </tr>
			                           <tr>
			                            <td>&nbsp;·您是否输入了错误的用户名和密码？</td>
			                          </tr>
			                          <tr>
			                            <td>&nbsp;&nbsp;&nbsp;请确认您的用户名和密码是否正确。</td>
			                          </tr>
			                          <tr>
			                            <td>&nbsp;</td>
			                          </tr>
			                          <tr>
			                            <td>如果您仍然看不到请求的页面，请与技术支持联系。</td>
			                          </tr>
		                       </table>
	                    </div>
                   </td>
                   <td width="1000"  height="300"  bgcolor="#CCCCCC" >
                      <div align="center">
                          <table width="300">
                             <tr>
                                 <td colspan="2" align="center"><font color="red">您的用户名或密码不正确！</font></td>
                             </tr>
                             <tr>
			                     <td>&nbsp;</td>
			                      <td>&nbsp;</td>
			                 </tr>
			                 <tr>
			                    <td  align="right" height=30 width=90><FONT color=065EC8>用户名：</FONT></td>
					            <td><html:text  property="user.userid" size="18" style="width: 130px;"  /></td>
			                 </tr>
			                 <tr>
				               	<td  align="right" height=30 ><FONT color=1771DE>密　码：</FONT></td>
				                <td><html:password property="user.opassword"  redisplay="false" size="18"  style="width: 130px;" /></td>
				             </tr>
				             <tr>
			                     <td>&nbsp;</td>
			                     <td>&nbsp;</td>
			                 </tr>
			                 <tr>
				                 <td height=40 align="center" colspan=2>
				                      
				                      <html:submit>确 定</html:submit>&nbsp;&nbsp;
		                              <html:reset>取 消</html:reset>
				                </td>
				            </tr>
                          </table>
                      </div>
                   </td>
               </tr> 
         </table> 
          <br><br> 
             北京天一众合科技发展有限责任公司
						<br>
						<font style="FONT-SIZE: 8pt; FONT-FAMILY: Verdana">Beijing Telezone Technology 
						 Co., Ltd &nbsp; 版权所有  2008-2012</font>
	      </div>
       </html:form>
        <script language="javascript">
         document.all['user.opassword'].focus();
         function check(){
            var name=document.all['user.userid'].value;
            var password=document.all['user.opassword'].value;
            if( name == ""){
                 alert("用户名不能为空")
                 document.all['user.userid'].focus();
                 return false;
             }if( password == ""){
                  alert("密码不能为空")
                  document.all['user.opassword'].focus();
                  return false;
             }
             return true;
         }
     </script>
  </body>
</html:html>
