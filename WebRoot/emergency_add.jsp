
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="增加地图信息"%> 

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>map_add.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
     <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
     <style TYPE="text/css">
			<!--
			A:link{text-decoration:none}
			A:visited{text-decoration:none}
			A:hover {color: #CCCCFF;text-decoration:none}
			 -->
	  </style>
    <script language="JavaScript">
         function check()
          {
            
            var name=document.all['emergencyadd.name'].value;
            
            var info=document.all['emergencyadd.info'].value;
            var file=document.all['emergencyadd.file'].value;
            
            if( name == "")
             {
               alert('路线名称不能为空');
               document.all['emergencyadd.name'].focus();
               return false;
             }
             
              if( info == "")
               {
                 alert('路线说明不能为空')
                 document.all['emergencyadd.info'].focus();
                 return false;
               }
               if( file == "")
                 {
                   alert('请选择图片');
                   document.all['emergencyadd.file'].focus();
                   return false;
                 }
              return true;
          }
    </script>
  </head>
  
  <body bgColor="white" background="Image/right.gif">
  
      <html:form action="/emergencyadd" target="leftFrame"  method="post"  enctype="multipart/form-data" focus="emergencyadd.name" onsubmit="return check()">
	      <TABLE width="400"><%--
	        <TR>
	          <TD>地&nbsp;图&nbsp;号：</TD>
	          <TD><html:text property="emergencyadd.id" maxlength="4" onkeyup="this.value=this.value.replace(/\D/g,'')"/><font color="red">&nbsp;&nbsp;*</font></TD>
	        </TR>
	        --%><TR>
	          <TD>路线名称：</TD>
	          <TD><html:text property="emergencyadd.name" maxlength="10" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /><font color="red">&nbsp;&nbsp;*</font></TD>
	        </TR>
	       <TR>
	          <TD>路线说明：</TD>
	          <TD><html:text  property="emergencyadd.info" maxlength="127" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /><font color="red">&nbsp;&nbsp;*</font></TD>
	        </TR><%--
	        <TR>
	          <TD>比&nbsp;例&nbsp;尺：</TD>
	          <TD><html:text  property="emergencyadd.scale" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')"/>（毫米，像素点）<font color="red">&nbsp;&nbsp;*</font></TD>
	        </TR>
	        --%><TR>
	          <TD>选择图片：</TD>
	          <TD><html:file  property="emergencyadd.file" /><font color="red">&nbsp;&nbsp;*</font></TD>
	        </TR>
	        <TR>
	          <TD colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	              <html:submit>确 定</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
	              <input type="button" value="返 回" onclick="javascript:window.location.href='emergency_top.jsp'"/>
	          </TD>
	        </TR>
	        <TR>
	          <TD colspan="2" > 
	         
	          </TD>
	        </TR>
	      </TABLE>
      </html:form>
  </body>
</html:html>
