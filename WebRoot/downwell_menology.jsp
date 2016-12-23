
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="部门下井月报"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
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
    <script src="js/Calendar2.js"></script><!-- 不带分秒-->
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
		    WebBrowser1.ExecWB(7, 1); //预览
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
       <html:form action="downwell_menology?method=init">
           <table width="500">
               <tr>
                 <th>选择年月:</th>
                 <th><html:text property="downwell_menology.stime" size="6" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM',el:$dp.$('downwell_menology.stime')})"/></th>
                 <%--<th>
                     <html:text property="downwell_menology.stime"  size="6" onfocus="this.blur()" />
                     <A onclick="return showCalendar('downwell_menology.stime', 'y-mm');"   href="#"><img src="Image/Button.gif"  id="IMG2"  border="0"/></A>
                 </th>
                 --%><th>&nbsp;&nbsp;</th>
                 <th align="left">班 组:</th>
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
                 <html:text property="downwell_menology.gro" style="width:100px;position:absolute;left:0px;"></html:text>
				</div>
                 </th>

                 <th>&nbsp;&nbsp;</th>
                 <th><html:submit>统 计</html:submit></th>
                 <th>&nbsp;&nbsp;</th>
                 <th>
                    <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	               </logic:equal>
	               <logic:notEqual name="listCount" value="0"> 
	               		<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>  
	                    <input type="button"  value="打印预览"  onclick="javascript:window.open('downwell_menologyPrint.jsp')">
	               </logic:notEqual>
                 </th>
               </tr>
           </table><br/>
       <%--<div   style="overflow:auto;width:825;height:435;">  
       --%>
       <div id = "printdiv">
       <table width="1720" cellspacing="1" cellpadding="1"  bgcolor="#000" border="0" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
	         <tr>
	              <td width="50"  align="left" bgcolor="#ffffff">序号</td>
			      <td width="151" align="left" bgcolor="#ffffff">单位</td>
				  <td width="55"  align="left" bgcolor="#ffffff">人数</td>
				  <td width="115" align="left" bgcolor="#ffffff">上月次数</td>
				  <td width="115" align="left" bgcolor="#ffffff">本月次数</td>
				  <td width="120" align="left" bgcolor="#ffffff">上月天数</td>
				  <td width="120" align="left" bgcolor="#ffffff">本月天数</td>
				  <td width="120" align="left" bgcolor="#ffffff">上月时长</td>
				  <td width="120" align="left" bgcolor="#ffffff">本月时长</td>
				  <td width="155" align="left" bgcolor="#ffffff">上月人均时长</td>
				  <td width="155" align="left" bgcolor="#ffffff">本月人均时长</td>
				  <td width="155" align="left" bgcolor="#ffffff">上月平均次数</td>
				  <td width="155" align="left" bgcolor="#ffffff">本月平均次数</td>
				  <td width="190" align="left" bgcolor="#ffffff">上月低于两小时数</td>
				  <td width="190" align="left" bgcolor="#ffffff">本月低于两小时数</td>
				  <td width="150" align="left" bgcolor="#ffffff">本年度总次数</td>
		    </tr>
		    <logic:present name="down_list">
		      	<logic:iterate name="down_list" id="list">
		      	  <tr>  
		      	     <td align="left" bgcolor="#CCCCCC"><bean:write name="list" property="count" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="depname" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="peoplecount" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="downtimesa" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="downtimesb" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="downdaysa" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="downdaysb" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="downintervala" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="downintervalb" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="avgintervala" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="avgintervalb" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="avgtimesa" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="avgtimesb" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="below2timesa" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="below2timesb" /></td>
		      	     <td align="left" bgcolor="#ffffff"><bean:write name="list" property="yeartimes" /></td>
		      	  </tr>
		      	</logic:iterate>
		    </logic:present>
       </table></div>
      
     </html:form>
  </body>
</html:html>
