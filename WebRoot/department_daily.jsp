
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="部门考勤日报" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>department_daily.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script src="js/Calendar2.js"></script><!-- 不带分秒-->
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
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"></script>
	<link href="Css/calendar-blue.css" rel="stylesheet"> 
  </head>
  
  <body bgColor="white" background="Image/right.gif">
  <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
      <html:form action="/department_daily?method=init" >
        <table width="325">
            <tr>
               <th>日 期： </th>
               <th><html:text property="department.stime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('department.stime')})"/></th>
               <%--<th>
                  <html:text property="department.stime"  size="9" onfocus="this.blur()" />
                  <A onclick="return showCalendar('department.stime', 'y-mm-dd');" href="#"><img src="Image/Button.gif"  id="IMG2"  border="0"/></A>
               </th>
               --%><th>&nbsp;&nbsp;</th>
                <%--<th align="left">班 组:</th>
                 <th align="left">
                 <div style="position:relative;">
                 <span style="margin-left:100px;width:18px;overflow:hidden;">
                 <select  style="width:118px;margin-left:-100px" onchange="this.parentNode.nextSibling.value=this.value">
                 <logic:present name="gro_list">
              		<option value=""></option>
                 	<logic:iterate id="aa" name="gro_list">
                 		<option value='<bean:write name="aa" property="wordvalue"/>'><bean:write name="aa" property="wordvalue"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
                 <html:text property="department.gro" style="width:100px;position:absolute;left:0px;"></html:text>
				</div>
                 </th>

               --%><th>&nbsp;&nbsp;</th>
               <th><html:submit>统 计</html:submit></th>
               <th>&nbsp;&nbsp;</th>
               <th>
                    <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                </logic:equal>
	                <logic:notEqual name="listCount" value="0">  
	                	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                     <input type="button"  value="打印预览"  onclick="javascript:window.open('department_dailyPrint.jsp')">
	                </logic:notEqual>
               </th>
            </tr>
        </table><br>
        <div id = "printdiv">
       <table cellspacing="1" cellpadding="1"  bgcolor="#000" border="0" width="761" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;" >
  		  <tr>
  		     <td width="30" align="left" bgcolor="#ffffff">序号</td>
             <td width="50" align="left" bgcolor="#ffffff">部门</td>
             <td width="40" align="left" bgcolor="#ffffff">总人数</td>
             <td width="50" align="left" bgcolor="#ffffff">入井人数</td>
             <td width="50" align="left" bgcolor="#ffffff">上井人数</td>
             <td width="50" align="left" bgcolor="#ffffff">入井次数</td>
             <td width="50" align="left" bgcolor="#ffffff">上井次数</td>
             <td width="40" align="left" bgcolor="#ffffff">早班</td>
             <td width="40" align="left" bgcolor="#ffffff">中班</td>
             <td width="40" align="left" bgcolor="#ffffff">晚班</td>
<%--             <td width="30" align="left" bgcolor="#ffffff">其他</td>--%>
             <td width="67" align="left" bgcolor="#ffffff">总时长</td>
             <td width="67" align="left" bgcolor="#ffffff">平均时长</td>
             <td width="80" align="left" bgcolor="#ffffff">人均入井次数</td>
          </tr>
          <logic:present name="depart">
             <logic:iterate name="depart" id="list">
                 <tr onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
                    <td align="left" bgcolor="#CCCCCC"><bean:write name="list" property="count" /> </td>
                    <td align="left" bgcolor="#ffffff"><bean:write name="list" property="group" /> </td>
                    <td align="left" bgcolor="#ffffff"><bean:write name="list" property="countp" /> </td>
                    <td align="left" bgcolor="#ffffff"><bean:write name="list" property="downsum" /> </td>
                    <td align="left" bgcolor="#ffffff"><bean:write name="list" property="upsum" /> </td>
                    <td align="left" bgcolor="#ffffff"><bean:write name="list" property="cishu" /> </td>
                    <td align="left" bgcolor="#ffffff"><bean:write name="list" property="upcishu" /> </td>
                    <td align="left" bgcolor="#ffffff"><bean:write name="list" property="zao" /> </td>
                    <td align="left" bgcolor="#ffffff"><bean:write name="list" property="zhong" /> </td>
                    <td align="left" bgcolor="#ffffff"><bean:write name="list" property="wan" /> </td>
<%--                    <td align="left" bgcolor="#ffffff"><bean:write name="list" property="qita" /> </td>--%>
                    <td align="left" bgcolor="#ffffff"><bean:write name="list" property="worktime" /> </td>
                    <td align="left" bgcolor="#ffffff"><bean:write name="list" property="counttime" /> </td>
                    <td align="left" bgcolor="#ffffff"><bean:write name="list" property="pcdcount" />次 </td>
                    
                 </tr>
             </logic:iterate>
          </logic:present>
       </table></div>
     </html:form>      
  </body>
</html:html>
