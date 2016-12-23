<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="实时信息"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>downwell_menology.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/showbackupaner.js"></script>
    <script language="javascript" src="js/page.js"></script>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"></script>
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
       <html:form action="person.do?method=getList"
       	focus="search_workatt.cardid">
           <table width="100%">
               <tr>
                 <th>
					员&nbsp;工：
				</th>
				<th>
					<input id="keyword"type="text" size="10">
					<html:select property="search_workatt.cardid" styleId="stafferSelect" 
					style="width: 125px;"></html:select>
					<div style="position: absolute;" id="popup2">
						<table id="name_table2" bgcolor="#FFFAFA" border="0"
							cellspacing="0" cellpadding="0" />
							<tbody id="name_table_body2"></tbody>
						</table>
					</div>
				</th><th>&nbsp;&nbsp;</th>

                 <th><html:submit>查询</html:submit></th>
                 <th>
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                 </th>
               </tr>
           </table><br/>
       <div id = "printdiv">
       <table width="100%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
	         <tr>
	              <td width="5%"  align="left" bgcolor="#B0C4DE">姓名</td>
				  <td width="10%"  align="left" bgcolor="#B0C4DE">班组</td>
				  <td width="5%" align="left" bgcolor="#B0C4DE">卡号</td>
				  <td width="5%" align="left" bgcolor="#B0C4DE">职务</td>
				  <td width="15%" align="left" bgcolor="#B0C4DE">下井时间</td>
				  <td width="10%" align="left" bgcolor="#B0C4DE">工作时长</td>
				  <td width="10%" align="left" bgcolor="#B0C4DE">当前分站</td>
				  <td width="15%" align="left" bgcolor="#B0C4DE">进入时间</td>
				  <td width="10%" align="left" bgcolor="#B0C4DE">停留时间</td>
				  <td width="15%" align="left" bgcolor="#B0C4DE">报警信息</td>
		    </tr>
		    <logic:present name="workperson_list">
		      	<logic:iterate name="workperson_list" id="workatt">
		      	  <tr>
		      	    
		      	     <td align="left" bgcolor="#E6E6FA"><bean:write name="workatt" property="name" /></td>
		      	     <td align="left" bgcolor="#E6E6FA"><bean:write name="workatt" property="group" /></td>
		      	     <td align="left" bgcolor="#E6E6FA"><bean:write name="workatt" property="cardid" /></td>
		      	     <td align="left" bgcolor="#E6E6FA"><bean:write name="workatt" property="occupation" /></td>
		      	     <td align="left" bgcolor="#E6E6FA"><bean:write name="workatt" property="downtime" /></td>
		      	     <td align="left" bgcolor="#E6E6FA"><bean:write name="workatt" property="worktime" /></td>
		      	     <td align="left" bgcolor="#E6E6FA"><bean:write name="workatt" property="crname" /></td>
		      	     <td align="left" bgcolor="#E6E6FA"><bean:write name="workatt" property="starttime" /></td>
		      	     <td align="left" bgcolor="#E6E6FA"><bean:write name="workatt" property="staytime" /></td>
		      	     <td align="left" bgcolor="#E6E6FA"><bean:write name="workatt" property="info" /></td>
		      	  </tr>
		      	</logic:iterate>
		    </logic:present>
       </table></div>
      
     </html:form>
  </body>
</html:html>
