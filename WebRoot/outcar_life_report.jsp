<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="车辆出车日/月报"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>outcar_life_report.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script>
	<link href="Css/TestDate.css" rel="stylesheet">
	<%--背景--%>
	<script language="javascript" src="js/page.js"></script>
	<!--分页 -->
	<script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
	<script src="js/Calendar2.js"></script>
	<!-- 不带分秒-->
	<script language="javascript" src="js/StafferORName.js"></script>
	<link href="Css/calendar-blue.css" rel="stylesheet">
	<script language="javascript">
	function check()
	{
         var outcar_life_report = document.getElementsByName("outcar_life_report.isDayOrMonth"); 
           if(outcar_life_report){
             for(var i=0;i<outcar_life_report.length;i++){ //适合length>=2时，当emphases_report.length==null时，可以直接取emphases_report.value值
               if(outcar_life_report[i].checked){
                 if(outcar_life_report[i].value=='day'){
                 	var daytime = document.getElementById("outcar_life_report.daytime").value;
                 	document.getElementById("outcar_life_report.isChoose").value = daytime;
                	//alert(daytime);
	     		 }else{
 					var monthtime = document.getElementById("outcar_life_report.monthtime").value;
 				    document.getElementById("outcar_life_report.isChoose").value = monthtime;
                 	//alert(monthtime);
	     		}
	     		//alert("LastReturn:"+document.getElementById("emphases_report.isChoose").value);
               } 
             } 
           }   
	} 
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
</head>

<body bgColor="white" background="Image/right.gif">
	<logic:messagesPresent message="true">
		<html:messages id="message" message="true">
			<font color="red"><bean:write name="message" />
			</font>
		</html:messages>
	</logic:messagesPresent>
	<html:form action="/outcar_life_report.do?method=init" >
		<input type="hidden" name="outcar_life_report.isChoose"/>
		<table width="390">
			<tr>
				<th>
					<html:radio property="outcar_life_report.isDayOrMonth" value="day"
						onclick="check()" />
					日报：
				</th>
				<th align="left">
                 <html:text property="outcar_life_report.daytime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('outcar_life_report.daytime')})"/>
                 </th>
				<%--
				<th>
					<html:text property="outcar_life_report.daytime" size="9"
						onfocus="this.blur()" />
					<A
						onclick="return showCalendar('outcar_life_report.daytime', 'y-mm-dd');"
						href="#"><img src="Image/Button.gif" id="IMG2" border="0" />
					</A>
				</th>
				--%><th>
					&nbsp;
				</th>
			</tr>
			<tr>
				<th>
					<html:radio property="outcar_life_report.isDayOrMonth" value="month"
						onclick="check()" />
					月报：
				</th>
				<th align="left">
                 <html:text property="outcar_life_report.monthtime" size="8" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM',el:$dp.$('outcar_life_report.monthtime')})"/>
                 </th>
				<%--
				<th>
				
					<html:text property="outcar_life_report.monthtime" size="9"
						onfocus="this.blur()" />
					<A
						onclick="return showCalendar('outcar_life_report.monthtime', 'y-mm');"
						href="#"><img src="Image/Button.gif" id="IMG2" border="0" />
					</A>
				</th>


				
				--%><th>
					&nbsp;
				</th>
				<th>
					&nbsp;
				</th>
				<th>
					<html:submit onclick="check()">统 计</html:submit>
				</th>

                <th>&nbsp;</th>
                <th>&nbsp;</th>
				<th>
                  <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	              </logic:equal>
	              <logic:notEqual name="listCount" value="0"> 
	              	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>  
	                  <input type="button"  value="打印预览"  onclick="javascript:window.open('outcar_life_reportPrint.jsp')">
	              </logic:notEqual>
               </th>
              
            
			</tr>
		</table>
		<div id = "printdiv">
		<table cellspacing="1" cellpadding="2" bgcolor="#000" border="0"
			style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
			<tr>
				<td width="45" align="left" bgcolor="#ffffff">
					序号
				</td>
				<td width="80" align="center" bgcolor="#ffffff">
					车辆类型
				</td>
				<td width="45" align="center" bgcolor="#ffffff">
					总车数
				</td>
				<td width="45" align="center" bgcolor="#ffffff">
					早车数
				</td>
				<td width="45" align="center" bgcolor="#ffffff">
					早次数
				</td>
				<td width="67" align="center" bgcolor="#ffffff">
					早平均时长
				</td>
				<td width="67" align="center" bgcolor="#ffffff">
					早出勤率
				</td>
				<td width="45" align="center" bgcolor="#ffffff">
					中车数
				</td>
				<td width="45" align="center" bgcolor="#ffffff">
					中次数
				</td>
				<td width="80" align="center" bgcolor="#ffffff">
					中平均时长
				</td>
				<td width="80" align="center" bgcolor="#ffffff">
					中出勤率
				</td>
				<td width="45" align="center" bgcolor="#ffffff">
					晚车数
				</td>
				<td width="45" align="center" bgcolor="#ffffff">
					晚次数
				</td>
				<td width="80" align="center" bgcolor="#ffffff">
					晚平均时长
				</td>
				<td width="80" align="center" bgcolor="#ffffff">
					晚出勤率
				</td>
				<td width="67" align="center" bgcolor="#ffffff">
					合计车数
				</td>
				<td width="80" align="center" bgcolor="#ffffff">
					总时长
				</td>
				<td width="80" align="center" bgcolor="#ffffff">
					平均时长
				</td>
				<td width="80" align="center" bgcolor="#ffffff">
					出车率
				</td>
				<td width="300" align="center" bgcolor="#ffffff">
					早车明细
				</td>
				<td width="300" align="center" bgcolor="#ffffff">
					中车明细
				</td>
				<td width="300" align="center" bgcolor="#ffffff">
					晚车明细
				</td>
			</tr>
			<logic:present name="relist">
				<logic:iterate name="relist" id="list">
					<tr>
						<td align="left" bgcolor="#CCCCCC">
							<bean:write name="list" property="count" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="worktype" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="cars" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="zaocheshu" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="zaocishu" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="zaoct" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="zaoodds" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="zhongcheshu" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="zhongcishu" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="zhongct" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="zhongodds" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="wancheshu" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="wancishu" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="wanct" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="wanodds" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="tote" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="alltime" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="counttime" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="carodds" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="zaocheid" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="zhongcheid" />
						</td>
						<td align="left" bgcolor="#ffffff">
							<bean:write name="list" property="wancheid" />
						</td>
					</tr>
				</logic:iterate>
			</logic:present><%--
			<logic:present name="pagination">
				<tr>
					<td colspan="22" align="left" bgcolor="#E6E6FA">
						<page:pagination path="outcar_life_report.do?method=init"
							parameter="page" formName="Search_outcar_life_report_Form" />
					</td>
				</tr>
			</logic:present>
		--%></table></div>
	</html:form>
</body>
</html:html>
