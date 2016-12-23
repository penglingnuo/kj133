
<%@ page language="java" pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>routDetail_right_add.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>  
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script language="javascript">
        function check(){
           var routeorder=document.all['routDetail_right.routeorder'].value;
           var id=document.all['routDetail_right.pointid'].value;
           if(routeorder == ""){
               alert('顺序不能为空');
               document.all['routDetail_right.routeorder'].focus();
               return false;
           }if(id == ""){
               alert('请正确的选择地点类型');
               document.all['routDetail_right.type'].focus();
               return false;
           }
           return true;
        }        
        function show(){
           var routeorder=document.all['routDetail_right.routeorder'].value;
           var param="save";
           window.parent.frames['right_downFrame'].location.href='routDetail_right.do?method=showRadio&routeorder='+routeorder+'&param='+param; 
        }
    </SCRIPT>
  </head>
  
  <body bgColor="white" background="Image/right.gif">
     <html:form action="/routDetail_right?method=save" focus="routDetail_right.routeorder" onsubmit="return check()" target="left_downFrame">
	     <TABLE border="0">
	         <tr>
	            <td colspan="4" align="center">增 加 经 过 地 点</td>
	          </tr>
	         <tr>
	             <td align="left" >顺序：</td>
	             <td align="left" ><html:text property="routDetail_right.routeorder" size="10" onkeyup="this.value=this.value.replace(/\D/g,'')"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	             <td align="left" >地点类型：</td>
	             <td align="left" ><html:text property="routDetail_right.type" size="15" onmousedown="show()"  onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /></td>
	         </tr>
	         <tr>
	             <td align="left" >I D：</td>
	             <td align="left" ><html:text property="routDetail_right.pointid" size="10" style="background-color: #dfdfdf"  readonly="true" /></td>
	             <td align="left" >名 称：</td>
	             <td align="left" ><html:text property="routDetail_right.shortname" size="15" style="background-color: #dfdfdf"  readonly="true" /></td>
	         </tr>
	         <tr>
	            <td colspan="4" align="center">
	            <html:submit >确 定</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            <html:reset>取 消</html:reset>
	            </td>
	          </tr>
	      </TABLE>
     </html:form>
  </body>
</html:html>
