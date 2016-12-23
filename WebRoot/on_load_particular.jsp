
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="在场信息查询的详细" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>on_load_particular.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <SCRIPT language="JavaScript">
    	//打印功能
		function printdiv() {
		    var newWin = window.open('printer', '', '');
		    var titleHTML = document.getElementById("printdiv").innerHTML;
		    titleHTML = titleHTML.toString().replace("border=0", "border=1");
		    titleHTML = titleHTML.toString().replace("cellSpacing=1", "cellSpacing=0");
		    newWin.document.write(titleHTML);
		    newWin.document.location.reload();
		    newWin.print();
		    newWin.close();
		    WebBrowser1.ExecWB(7, 1); //预览
		}

	</SCRIPT>
     <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
  </head>
  
  <body bgColor="white" background="Image/right.gif" >
    <p><input type="button"  value="打印预览"  onclick="javascript:window.open('on_load_rightPrint.jsp')">
    	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
    </p>
    <div id = "printdiv">
     <table width="458" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
       <tr>
            <td width="52"  align="left" bgcolor="#B0C4DE">卡号</td>
            <td width="66"  align="left" bgcolor="#B0C4DE">姓名</td>
            <td width="170" align="left" bgcolor="#B0C4DE">最早记录时间</td>
            <td width="170" align="left" bgcolor="#B0C4DE">最晚记录时间</td>
       </tr>
	  <logic:present name="On_Load_Particular">
	      <logic:iterate name="On_Load_Particular" id="particular">
			   <tr>
			         <td align="left" bgcolor="#E6E6FA"><bean:write name="particular" property="sid" /></td>
			         <td align="left" bgcolor="#E6E6FA"><bean:write name="particular" property="name" /></td>
			         <td align="left" bgcolor="#E6E6FA"><bean:write name="particular" property="stime" /></td>
			         <td align="left" bgcolor="#E6E6FA"><bean:write name="particular" property="etime" /></td>
			   </tr>
	      </logic:iterate>
	   </logic:present> 
     </table></div>
  </body>
</html:html>
