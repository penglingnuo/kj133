
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>addgroup.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <SCRIPT language="javascript">
       function gro()
       {
         var  gname=document.forms[0].elements[1].value;
         if( gname == ""){
           alert('组别名称不能为空');
           return false;
         }
         return true; 
       }
       function  user()
          {
	       
	        var userid=document.forms[1].elements[0].value;
	        var oname=document.forms[1].elements[1].value;
	        var password=document.forms[1].elements[2].value;
               if( userid == "" ){
	              alert('用户号不能为空');
	              document.forms[1].elements[0].focus();
	              return false;
	             }if( oname == ""){
	               alert('用户姓名不能为空')
	               document.forms[1].elements[1].focus();
	               return false;
	             }if( password == ""){
	               alert('用户密码不能为空')
	               document.forms[1].elements[2].focus();
	               return false;
	             }
	             return true;
             } 
         function delete1(){
		    if(confirm('确实要删除吗?'))
	        window.parent.frames['leftFrame'].location.href="add_group.do?method=deleteGroup";
			return true;     
			}       
    </SCRIPT>
  </head>
  
  <body bgColor="white" background="Image/right.gif">
	 <html:form action="/add_group?method=updateGroup" target="leftFrame" onsubmit="return gro()" focus="ser_account.group_name" >
	   <Table border="1" width="420" height="150" bordercolor="#99CCCC" >
        <TR> 
          <TD> 
	        <TABLE>
	        <%--
              <TR>
                <TD>组别编号：</TD>
                <TD><html:text property="ser_account.group_id"   size="10" style="background-color: #dfdfdf"  readonly="true" /></TD>
                <TD>组别名称：</TD>
                <TD><html:text property="ser_account.group_name" size="18" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')"  />&nbsp;&nbsp;&nbsp;</TD>
              </TR>
             <TR>
                <TD>所属部门：</TD>
                <TD><html:text   property="ser_account.departmentid" size="10" onfocus="this.blur()" style="background-color: #dfdfdf" />&nbsp;&nbsp;&nbsp;</TD>
                <TD>组别描述：</TD>
                <TD><html:text property="ser_account.group_depict"  size="18" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')"  /></TD>
             </TR>
             <TR>
                <TD colspan="4" align="center">
                   <html:submit>修改组别</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="javascript:if(confirm('确实要删除吗?'))location='add_group.do?method=deleteGroup'" target="leftFrame"><IMG src="Image/delete.BMP" border="0" align="absmiddle"/></a>
                </TD>
             </TR>
              --%>
              <TR>
                <TD>组别编号：</TD>
                <TD><html:text property="ser_account.group_id"   size="10" style="background-color: #dfdfdf"  readonly="true" /></TD>
                <TD>组别名称：</TD>
                <TD><html:text property="ser_account.group_name" size="18"  maxlength="10" />&nbsp;&nbsp;&nbsp;</TD>
              </TR>
             <TR>
                <TD>所属部门：</TD>
                <TD><html:text   property="ser_account.departmentid" size="10" onfocus="this.blur()" style="background-color: #dfdfdf" />&nbsp;&nbsp;&nbsp;</TD>
                <TD>组别描述：</TD>
                <TD><html:text property="ser_account.group_depict"  size="18"   /></TD>
             </TR>
             <TR>
                <TD colspan="4" align="center">
                   <html:submit>修改组别</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
                    <%--<a href="javascript:if(confirm('确实要删除吗?'))location='add_group.do?method=deleteGroup'" target="leftFrame"><IMG src="Image/delete.BMP" border="0" align="absmiddle"/></a>
                    --%><input type="button" value="删 除" onclick="delete1()" />
                </TD>
             </TR>
          </TABLE>
               <TD>
              </TR>
          </TABLE>
	 </html:form> 
	 <br /><br /><br />
	 
	 
	 <html:form action="/add_group?method=addUser" target="leftFrame" onsubmit="return user()" >
	  <Table border="1" width="420" height="150" bordercolor="#99CCCC" >
        <TR> 
          <TD> 
	    <TABLE>
	         <TR>
                <TD>用户号：</TD>
                <TD><html:text  property="ser_account.uid" size="10" maxlength="10"  />&nbsp;&nbsp;&nbsp;</TD>
                <TD>用户姓名：</TD>
                <TD><html:text  property="ser_account.oname" size="18"  maxlength="10" /></TD>
              </TR>
              <TR>
                <TD>密&nbsp;&nbsp;码：</TD>
                <TD><html:password  property="ser_account.password" size="10"   />&nbsp;&nbsp;&nbsp;</TD>
                <TD>创建时间：</TD>
                <TD><html:text  property="ser_account.cratetime" size="18" onfocus="this.blur()" style="background-color: #dfdfdf" />&nbsp;&nbsp;&nbsp;</TD>
              </TR>
              <TR>
                <TD>所属部门：</TD>
                <TD><html:text   property="ser_account.departmentid" size="10" onfocus="this.blur()" style="background-color: #dfdfdf" />&nbsp;&nbsp;&nbsp;</TD>
                <TD>所属组别：</TD>
                <TD><html:text   property="ser_account.groupid" size="18" onfocus="this.blur()" style="background-color: #dfdfdf" />&nbsp;&nbsp;&nbsp;</TD>
              </TR>
              <TR>
                <TD colspan="4" align="center">
                    <html:submit>增加用户</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
                    <INPUT type="button" value="删 除"  disabled/> 
                </TD>
             </TR>
	    </TABLE>
	            <TD>
              </TR>
          </TABLE>
	 </html:form>   
  </body>
</html:html>
