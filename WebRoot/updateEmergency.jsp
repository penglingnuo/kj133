
<%@ page language="java" pageEncoding="UTF-8"%>
 

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
 <%
   String name=request.getAttribute("mname").toString();
   String aaname=request.getAttribute("aaname").toString();
   request.setAttribute("pic_id",name); 
  
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base /><%--
     <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    --%><link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
  
    <script language="JavaScript">
         function check()
          {
            var name=document.all['emergencyadd.name'].value;
            
            var info=document.all['emergencyadd.info'].value;
            //var file=document.all['emergencyadd.file'].value;
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
              //if(document.all['name1'].checked== true ) 
              //{
                // if(file == "")
                //  {
                //    alert('请选择图片');
                //    document.all['emergencyadd.file'].focus();
                 //   return false;
                //  }
             // }
              return true;
          }
     
    </script>
  </head>
  
  <body bgColor="white" background="Image/right.gif">
      <html:form action="/loadEmergency?method=update" target="leftFrame"  method="post"  enctype="multipart/form-data" focus="emergencyadd.name" onsubmit="return check()">
	      <TABLE>
	        <%--<TR>
	          <TD>地&nbsp;图&nbsp;号：</TD>
	          <TD><html:text property="emergencyadd.id" readonly="true"  style="background-color: #dfdfdf"  size="18" /></TD>
	        </TR>
	        --%>
	        <html:hidden property="emergencyadd.sid" value="<%=aaname%>"/>
	        <TR>
	          <TD>路线名称：</TD>
	          <TD ><html:text property="emergencyadd.name"   size="18" /> </TD><%--
	          <TD ><html:text property="emergencyadd.name" size="18" maxlength="10" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /> </TD>
	        --%></TR>
	        <%--<TR>
	          <TD>比&nbsp;例&nbsp;尺：</TD>
	          <TD><html:text  property="emergencyadd.scale" size="18" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')"/>（毫米，像素点） </TD>
	        </TR>
	       --%><TR>
	          <TD>路线说明：</TD>
	          <TD><html:text  property="emergencyadd.info" size="18"  maxlength="127" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /> </TD>
	        </TR>
	        <%--<TR>
	          <TD>选择图片：</TD>
	          <TD>
	                <html:file  property="emergencyadd.file" styleId="file" disabled="true" size="18" />
	                <input type="checkbox" name="name1" onclick="this.checked?file.disabled=false:file.disabled=true" />选中则按钮可用  
	           </TD>
	        </TR>
	        --%><TR>
	          <TD colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	              <html:submit>修改</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
	              <html:reset>取消</html:reset>
	          </TD>
	        </TR>
	        </TABLE>
	        <table>
	        <TR>
	          <TD colspan="2" >
	            <%-- 
	            <html:img  src="loadEmergency.do?method=show"  paramId="name" paramName="pic_id" width="300" height="380" />
	            --%>
	            <html:img  src="loadEmergency.do?method=show"  paramId="name" paramName="pic_id" />
	          </TD>
	        </TR>
	        
	      </table>
      </html:form>
  </body>
</html:html>
