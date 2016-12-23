
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="行走轨迹详细信息"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>行走轨迹详细信息</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/showbackupaner.js"></script>
    <script language="javascript" src="js/page.js"></script>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"></script>
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
		}

	</SCRIPT>   
	<link href="Css/calendar-blue.css" rel="stylesheet">
  </head>
  
  <%--<body bgColor="white" background="Image/right.gif" style="overflow:hidden">
  --%>
  <body bgColor="white" background="Image/right.gif">
  
  <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
       <html:form action="workatt.do?method=getAll">
       <table width="90%" align="center">
              <tr>        		 
                 <th  align="right">
	                <input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
					<input name=Button onclick="window.close();" type=button value=关闭>
                 </th>
				   
              </tr>          
          </table><br/> 
       <div id = "printdiv">
       <table  width="90%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="1" align="center">
       <tr><td colspan="7" align="center"><font size="6">轨迹列表明细</font></td></tr>
             <logic:present name="workatt_list_staffer">
			      	<logic:iterate name="workatt_list_staffer" id="workattstaff">
			      	  <tr>  
			      	     <td align="left" >员工号：<bean:write name="workattstaff" property="stafferid" /></td>
			      	     <td align="left" >姓名：<bean:write name="workattstaff" property="name" /></td>
			      	     <td align="left" >卡号：<bean:write name="workattstaff" property="cardid" /></td>
			      	     <td align="left" >部门：<bean:write name="workattstaff" property="department" /></td>
			      	     <td align="left" >职务：<bean:write name="workattstaff" property="occupation" /></td>
			      	     <td align="left" >下井时间：<bean:write name="workattstaff" property="downtime" /></td>
			      	     <td align="left" >工作时长：<bean:write name="workattstaff" property="worktime" /></td>
			      	  </tr>
		      		</logic:iterate>
		    	</logic:present>
            </table>
       <table width="90%" align="center" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
	         <tr>
				  <td width="25%" align="left" bgcolor="#B0C4DE">进入时间</td>
				  <td width="25%" align="left" bgcolor="#B0C4DE">离开时间</td>
				  <td width="25%" align="left" bgcolor="#B0C4DE">停留时间</td>
				  <td width="25%" align="left" bgcolor="#B0C4DE">停留分站</td>
		    </tr>
		    <logic:present name="workatt_list">
		      	<logic:iterate name="workatt_list" id="workatt">
		      	  <tr>  
		      	     <td align="left" bgcolor="#E6E6FA"><bean:write name="workatt" property="starttime" /></td>
		      	     <td align="left" bgcolor="#E6E6FA"><bean:write name="workatt" property="endtime" /></td>
		      	     <td align="left" bgcolor="#E6E6FA"><bean:write name="workatt" property="stayinterval" /></td>
		      	     <td align="left" bgcolor="#E6E6FA"><bean:write name="workatt" property="crname" /></td>
		      	  </tr>
		      	</logic:iterate>
		    </logic:present>
       </table></div>
      
     </html:form>
  </body>
</html:html>
