
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="部门时点查询"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>department_time.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
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
    <style>   
        table,td{   
                border:1   solid   ;
                BORDER-COLLAPSE:COLLAPSE}   
    </style>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script>  
  </head>
  
  <body bgColor="white" background="Image/right.gif"> 
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
        <%
           String count=request.getAttribute("count").toString();
        %>
       <html:form action="/department_time?method=queryShow">
           时间：
           <th align="left">
                 <html:text property="department_day.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('department_day.stime')})"/>&nbsp;
           </th>
           <%--<html:text property="department_day.stime" size="18" onfocus="show_cele_date('','',Search_department_day_Form['department_day.stime'])" />
          --%>
          &nbsp;&nbsp;
          <html:submit>查 询</html:submit>&nbsp;&nbsp;
          <logic:equal  name="listCount" value="0">
                      <input type="button"  value="打印预览"  disabled>
	      </logic:equal>
	      <logic:notEqual name="listCount" value="0">  
	      	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                  <input type="button"  value="打印预览"  onclick="javascript:window.open('department_timePrint.jsp')">
	      </logic:notEqual>
          <br/><br/>
       <div id = "printdiv">   
        <table  width="792" >
            <tr>
               <td colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  井 下 人 员 分 布 区 域 查 询 表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;井下总人数：<font color="#F000000"><%=count %></font>
               </td>
            </tr>
            <tr>
                  <logic:present name="time">
                       <td align="center" width="130" bgcolor="#CCCCCC">
                            <bean:write name="time" property="stime" /> 部门-班组（姓名）
                       </td>
                  </logic:present>
               <td align="center" width="90" bgcolor="#CCCCCC">总人数(卡号)</td>
               <td align="center" width="90" bgcolor="#CCCCCC">总时长(时长)</td>
               <td align="center" width="185" bgcolor="#CCCCCC">平均下井时间(下井时间)</td>
               <td align="center" width="142" bgcolor="#CCCCCC">当前分站(平均时长)</td>
               <td align="center" width="170" bgcolor="#CCCCCC">当前位置</td>
            </tr>
            <logic:present name="relist">
                <logic:iterate name="relist" id="relist">
                    <tr>
                       <logic:equal name="relist" property="dep" value="1">
                           <td align="left"><FONT color="#F000000"><bean:write name="relist" property="department2"  /></FONT></td>
                       </logic:equal>
                       <logic:equal name="relist" property="dep" value="2">
                           <td align="center"><FONT color="#336699"><bean:write name="relist" property="department2"  /></FONT></td>
                       </logic:equal>
                       <logic:equal name="relist" property="dep" value="3">
                           <td align="right"><bean:write name="relist" property="department2"  /></td>
                       </logic:equal>
                       <td align="center"><bean:write name="relist" property="peoplecount"  /></td>
                       <td align="center"><bean:write name="relist" property="sumtime"  /></td>
                       <td align="center"><bean:write name="relist" property="avgdatetime"  /></td>
                       <td align="center"><bean:write name="relist" property="avgtime"  /></td>
                       <td align="center"><bean:write name="relist" property="location"  /></td>
                    </tr>
                </logic:iterate>
            </logic:present>
        </table></div>
       </html:form>
  </body>
</html:html>
