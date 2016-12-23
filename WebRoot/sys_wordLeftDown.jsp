
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="系统词库维护" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>sys_wordLeftDown.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
		     function go(){
               window.history.go(-1);
             }
    </script> 
    <script language="javascript" src="js/page.js"></script>
    <script language="JavaScript" src="js/calendar.js"></script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/CardIdORName.js"></script>

        <style TYPE="text/css">
		<!--
		 A:link{color: #6699CC;text-decoration:none}
		 A:hover{color:#FF0000;}
		 -->
    </style>
    <%--
    <style type="text/css">
	    .mouseOut {
	    background: #708090;
	    color: #FFFAFA;
	    }
	
	    .mouseOver {
	    background: #FFFAFA;
	    color: #000000;
	    }
    </style>
    
  --%></head>
  
  <body bgColor="white" background="Image/right.gif" >
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     
    <html:form action="sys_wordLeftDown?method=update" target="leftFrame" >
    	      <TABLE>
	        <TR>
	          <TD>系统词名称：</TD>
	          <TD><html:text property="wordname" readonly="true"  style="background-color: #dfdfdf"  size="18" /></TD>
	        </TR>
	        <TR>
	          <TD>最&nbsp;大&nbsp;值：</TD>
	          <TD><html:text  property="maxvalue" size="18" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')"/></TD>
	        </TR>
	        <TR>
	          <TD>最&nbsp;小&nbsp;值：</TD>
	          <TD><html:text  property="minvalue" size="18" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')"/></TD>
	        </TR>
	       
	        <TR>
	          <TD colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	              <html:submit>修 改</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
	              <%--<html:reset>取  消</html:reset>
	              --%><input type="button" value="返 回" onClick="go()">
	          </TD>
	        </TR>
	      </TABLE>

        
         </html:form>     
  </body>
</html:html>
