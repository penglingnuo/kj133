
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>addDepartment.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
     <SCRIPT language="javascript">
       function check()
         {
         
            var did=document.forms[0].elements[0].value;
            var dname=document.forms[0].elements[1].value;
            if( did == "" ){
              alert('部门编号不能为空');
              document.forms[0].elements[0].focus();
              return false;
             }if( dname == ""){
               alert('部门名称不能为空')
               document.forms[0].elements[1].focus();
              return false;
             }
             return true;
         }
         function gro()
           {
                var gid=document.forms[1].elements[0].value;
                var gname=document.forms[1].elements[1].value;
                 if( gid == "" ){
	              alert('组别编号不能为空');
	              document.forms[1].elements[0].focus();
	              return false;
	             }if( gname == ""){
	               alert('组别名称不能为空')
	               document.forms[1].elements[1].focus();
	              return false;
	             }
	             return true;
           }
        // function updateDP()
		//    {
	//		   window.parent.frames['leftFrame'].location.href='add_department.do?method=updateDepartment';
		 //  location.href="add_department.do?method=updateDepartment&name='ser_account.department_id'";   
					 
				
		//	} 
           
     </SCRIPT>
    
  </head>
  
  <body bgColor="white" background="Image/right.gif">
      <html:form action="/add_department?method=updateDepartment" target="leftFrame" onsubmit="return  check()" focus="ser_account.department_id" >
       <Table   border="1" width="420" height="150" bordercolor="#99CCCC" >
        <TR> 
          <TD>
          <TABLE>
              <TR >
                <TD>部门编号：</TD>
                <TD><html:text property="ser_account.department_id" size="10" onkeyup="this.value=this.value.replace(/\D/g,'')" style="background-color: #dfdfdf" readonly="true" /></TD>
                <TD>部门名称：</TD>
                <TD><html:text  property="ser_account.department_dname"  size="18"  /></TD>
              </TR>
                
              <TR>
                 <TD>部门描述：</TD>
                 <TD colspan="3"><html:text property="ser_account.department_depict" size="42"  /></TD>
              </TR>
              <TR>
                <TD colspan="4" align="center">
                   
                  <html:submit>修 改</html:submit>&nbsp;&nbsp;
                  <a href="add_department.do?method=deleteDepartment" target="leftFrame"><IMG src="Image/delete.BMP" border="0" align="absmiddle"/></a> 
                </TD>
              </TR>
          </TABLE>
            <TD>
          </TR>
          </TABLE>
	  </html:form>   


  </body>
</html:html>
