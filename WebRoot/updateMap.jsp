
<%@ page language="java" pageEncoding="UTF-8"%>
 

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
 <%
   String id=request.getAttribute("mid").toString();
   request.setAttribute("pic_id",id); 
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base /><%--
     <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    --%><link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
      <style TYPE="text/css">
			<!--
			A:visited{text-decoration:none}
			 -->
	  </style>
    <script language="JavaScript">
         function check()
          {
            var name=document.all['mapadd.name'].value;
            var scale=document.all['mapadd.scale'].value;
            var info=document.all['mapadd.info'].value;
            var file=document.all['mapadd.file'].value;
            if( name == "")
             {
               alert('地图名称不能为空');
               document.all['mapadd.name'].focus();
               return false;
             }
             if( scale == "")
              {
                alert('比例尺不能为空');
                document.all['mapadd.scale'].focus();
                return false;
              }
              if(document.all['name1'].checked== true ) 
              {
                 if(file == "")
                  {
                    alert('请选择图片');
                    document.all['mapadd.file'].focus();
                    return false;
                  }
              }
              return true;
          }
     
    </script>
  </head>
  
  <body bgColor="white" background="Image/right.gif">
      <html:form action="/loadMap?method=update" target="leftFrame"  method="post"  enctype="multipart/form-data" focus="mapadd.name" onsubmit="return check()">
	      <TABLE>
	        <TR>
	          <TD>地&nbsp;图&nbsp;号：</TD>
	          <TD><html:text property="mapadd.id" readonly="true"  style="background-color: #dfdfdf"  size="18" /></TD>
	        </TR>
	        <TR>
	          <TD>地图名称：</TD>
	          <TD ><html:text property="mapadd.name" size="18" maxlength="10" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /> </TD>
	        </TR>
	        <TR>
	          <TD>比&nbsp;例&nbsp;尺：</TD>
	          <TD><html:text  property="mapadd.scale" size="18" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')"/>（毫米，像素点） </TD>
	        </TR>
	       <TR>
	          <TD>地图说明：</TD>
	          <TD><html:text  property="mapadd.info" size="18"  maxlength="127" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /> </TD>
	        </TR>
	        <%--<TR>
	          <TD>选择图片：</TD>
	          <TD>
	                <html:file  property="mapadd.file" styleId="file" disabled="true" size="18" />
	                <input type="checkbox" name="name1" onclick="this.checked?file.disabled=false:file.disabled=true" />选中则按钮可用  
	           </TD>
	        </TR>
	        --%><TR>
	          <TD colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	              <html:submit>确  定</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
	              <html:reset>取  消</html:reset>
	          </TD>
	        </TR>
	        <TR>
	          <TD colspan="2" > 
	            <html:img  src="loadMap.do?method=show"  paramId="id" paramName="pic_id" width="410" height="270" />
	          </TD>
	        </TR>
	      </TABLE>
      </html:form>
  </body>
</html:html>
