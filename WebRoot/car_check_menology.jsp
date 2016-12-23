
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="车辆考勤月览表"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>car_check_menology.jsp</title>
	<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script>
	<link href="Css/TestDate.css" rel="stylesheet">
	<%--背景--%>
	<script src="js/Calendar2.js"></script>
	<!-- 不带分秒-->
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>	
	<script language="javascript" src="js/StafferORName.js"></script>
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
	<link href="Css/calendar-blue.css" rel="stylesheet"><%--
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

<body bgColor="white" background="Image/right.gif">
	<logic:messagesPresent message="true">
		<html:messages id="message" message="true">
			<font color="red"><bean:write name="message" />
			</font>
		</html:messages>
	</logic:messagesPresent>
	<html:form action="/car_check_menology?method=init">
		<table width="550">
			<tr>
				<th>
					选择年月：
				</th>
				<th align="left">
                 <html:text property="car_check_menology.stime" size="6" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM',el:$dp.$('car_check_menology.stime')})"/>
                 </th>
				<%--
				<th>
					<html:text property="car_check_menology.stime" size="6"
						onfocus="this.blur()" />
					<A onclick="return showCalendar('car_check_menology.stime','y-mm');"
						href="#"><img src="Image/Button.gif" id="IMG2" border="0" />
					</A>
				</th>
				--%><th>
					&nbsp;&nbsp;
				</th>
				<th>
					车辆类型：
				</th>
				<th align="left">
                 <div style="position:relative;">
                 <span style="margin-left:82px;width:18px;overflow:hidden;">
                 <select  style="width:118px;margin-left:-100px" onchange="this.parentNode.nextSibling.value=this.value">
                 <logic:present name="workType_list">
              		<option value=""></option>
                 	<logic:iterate id="aa" name="workType_list">
                 		<option value='<bean:write name="aa" property="worktype"/>'><bean:write name="aa" property="worktype"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
                 <html:text property="car_check_menology.cartype" style="width:82px;position:absolute;left:0px;"></html:text>
				</div> 
                 </th>
				<%--
				<th>
					<html:select property="car_check_menology.cartype" style="width:110px">&nbsp;&nbsp;&nbsp;
		                <html:option value=""></html:option>
						<html:options collection="workType_list" property="wordvalue"
							labelProperty="wordvalue" />
					</html:select>
				</th>
				--%><th>
					&nbsp;&nbsp;
				</th>
				<th>
					<html:submit>统 计</html:submit>
				</th>
				<th>
					&nbsp;&nbsp;
				</th>
				<th>
					<logic:equal name="listCount" value="0">
						<input type="button" value="打印预览" disabled>
					</logic:equal>
					<logic:notEqual name="listCount" value="0">
						<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
						<input type="button" value="打印预览"
							onclick="javascript:window.open('car_check_menologyPrint.jsp')">
					</logic:notEqual>
				</th>
			</tr>
		</table>
		<br>
		
<div id = "printdiv">
			<table cellspacing="1" cellpadding="1" bgcolor="#000" border="0" 
				style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
				<tr>
					<td align="left" width="50" bgcolor="#ffffff">
						序号
					</td>
					<td align="left" width="80" bgcolor="#ffffff">
						车辆类型
					</td>
					<td align="left" width="80" bgcolor="#ffffff">
						车辆名称
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						1
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						2
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						3
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						4
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						5
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						6
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						7
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						8
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						9
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						10
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						11
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						12
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						13
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						14
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						15
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						16
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						17
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						18
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						19
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						20
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						21
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						22
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						23
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						24
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						25
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						26
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						27
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						28
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						29
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						30
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						31
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						早班
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						中班
					</td>
					<td align="left" width="30" bgcolor="#ffffff">
						晚班
					</td>
					<td align="left" width="40" bgcolor="#ffffff">
						总出勤
					</td>
				</tr>
				<logic:present name="relist">
					<logic:iterate name="relist" id="list">
						<tr onmouseover="this.style.background='#CCCCCC'; "
							onmouseout="this.style.background=''; this.style.borderColor=''">
						
							<td align="left" bgcolor="#CCCCCC">
								<bean:write name="list" property="count" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="worktype" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="staffername" />
							</td>

							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="a" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="b" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="c" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="d" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="e" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="f" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="g" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="h" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="i" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="j" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="k" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="l" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="m" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="n" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="o" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="p" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="q" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="r" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="s" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="t" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="u" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="v" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="w" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="x" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="y" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="z" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="aa" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="bb" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="cc" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="dd" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="ee" />
							</td>
							
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="ban01" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="ban02" />
								
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="ban03" />
							</td>
							<td align="left" bgcolor="#ffffff">
								<bean:write name="list" property="heji" />
							</td>

						</tr>
					</logic:iterate>
				</logic:present>
			</table></div>
		 
	
	</html:form>
</body>
</html:html>
