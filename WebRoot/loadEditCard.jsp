<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>loadEditCard.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;")
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet">
  </head>
  
  <body bgColor="white" background="Image/right.gif">
     <html:form action="/operateCard?method=update"  focus="cardmode"  target="topFrame" >
		   <html:hidden property="recordid"/>
		    <TABLE>
		      <TR>
		        <TD>修改卡号：</TD>
		        <TD><html:text property="cardid" size="10" style="background-color: #dfdfdf"  onfocus="this.blur()" onkeyup="this.value=this.value.replace(/\D/g,'')" /> &nbsp;&nbsp;</TD>
		        <TD>卡型号：</TD>
		        <TD>
		           <html:select property="cardmode">
		               <html:option value="KGE39">KGE39</html:option>
		               <html:option value="车辆">车辆</html:option>
		           </html:select>&nbsp;&nbsp;&nbsp;
		         </TD>
		        <TD>卡状态：</TD>
		        <TD>
		           <html:select property="cardstate">
		               <html:option value="正常">正  常</html:option>
		               <html:option value="取消">取  消</html:option>
		           </html:select>&nbsp;&nbsp;&nbsp;
		        </TD>
		        <TD><html:submit>确 定</html:submit> &nbsp;</TD>
		        <TD><html:reset>取 消</html:reset></TD>
		      </TR>
		    </TABLE>
        </html:form>
  </body>
</html:html>
