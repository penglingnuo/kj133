
<%@ page language="java" pageEncoding="UTF-8"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>routDetail_right_showradio.jsp</title>

    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script type="text/javascript">
            function choise(obj){
                Search_routDetail_right_Form.submit();
            }
     </script> 
  </head>
  <body bgColor="white" background="Image/right.gif">
     <font color="red">请选择一个单选按钮！</font> 
     <html:form action="routDetail_right?method=addChoiceRadio" target="right_downFrame">
	     <TABLE cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" width="370">
	         <tr>
	            <td align="left" width="100" bgcolor="#B0C4DE">类型</td>
	            <td align="left" width="200" bgcolor="#B0C4DE">名称</td>
	            <td align="left" width="70"  bgcolor="#B0C4DE">ID</td>
	         </tr>
	         <logic:present name="choice">
	            <logic:iterate name="choice" id="choice">
	              <tr onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''"> 
	                  <td bgcolor="#E6E6FA">
	                       <INPUT type="radio"  name="radio"  onclick="choise(this)" value="<bean:write name='choice' property='choice_cardreaderid' />+<bean:write name='choice' property='choice_shortname'/>+<bean:write name='choice' property='choice_type'/>">
	                       <bean:write name="choice" property="choice_type"/>                       
	                   </td>
	                  <td bgcolor="#E6E6FA"><bean:write name="choice" property="choice_shortname"/></td>
	                  <td bgcolor="#E6E6FA"><bean:write name="choice" property="choice_cardreaderid"/></td>
	              </tr>
	            </logic:iterate>
	         </logic:present>
	      </TABLE>
      </html:form>
  </body>
</html:html>
