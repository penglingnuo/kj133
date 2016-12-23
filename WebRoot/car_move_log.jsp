<%@ page language="java" import="java.util.*,com.kj133.entity.vo.*" pageEncoding="UTF-8"%>
<%@ page info="单车运行记录查询"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>
<%
String name1=(String)request.getAttribute("name1");
String name2=(String)request.getAttribute("name2");

 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />
	<title>car_move_log.jsp</title>
	<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<%
		Car_move_logVO vo = null;
		List volist = (List)request.getAttribute("car_move_log_list");
		if(volist!=null)
		{
			vo = (Car_move_logVO) volist.get(volist.size()-1);
		}			
	 %>
	<script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script>
	<script language="javascript" src="js/page.js"></script>
	<%--分页--%>
	<script language="JavaScript" src="js/calendar.js"></script>
	<%--时间--%>
	<script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
	<link href="Css/TestDate.css" rel="stylesheet">
	<%--背景--%>
	<script language="javascript" src="js/CardIdORName.js"></script>
	<link href="Css/calendar-blue.css" rel="stylesheet">

	<script language="javascript" src="js/StafferORName.js"></script>
	<SCRIPT language="JavaScript">
       function check()
         {
	         var stime=document.all['car_move_log.stime'].value;
	         var etime=document.all['car_move_log.etime'].value;
	         if( stime > etime){
	            alert('起始时间不能大于截止时间');
	            return false;
         }
           return true;
       }
    </SCRIPT>
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
	<%--
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
    
  --%>
</head>

<body bgColor="white" background="Image/right.gif">
	<logic:messagesPresent message="true">
		<html:messages id="message" message="true">
			<font color="red"><bean:write name="message" />
			</font>
		</html:messages>
	</logic:messagesPresent>

	<html:form action="car_move_log?method=getList" onsubmit="return check()">
		<table width="700">
			<tr>
				<th>
					考勤开始日期:
				</th>
				<th align="left">
                 <html:text property="car_move_log.stime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('car_move_log.stime')})"/>&nbsp;
                 </th>
				<%--<th>
					<html:text property="car_move_log.stime" size="18"
						onfocus="show_cele_date('','',Search_car_move_log_Form['car_move_log.stime'])" />
				</th>
				--%>
				<th>
					&nbsp;&nbsp;
				</th>
				<th align="left">工作时间大于:</th>
                 
                 <th align="left">
                 <html:text property="car_move_log.minstime" size="8" styleId="file1" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('car_move_log.minstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name1" onclick="this.checked?file1.disabled=false:file1.disabled=true" />&nbsp;
                 </th>
				<th>
					&nbsp;&nbsp;
				</th>
				<th align="right">
					车辆类型:
				</th>
				<th align="left">
                 <div style="position:relative;">
                 <span style="margin-left:82px;width:18px;overflow:hidden;">
                 <select  style="width:118px;margin-left:-100px" onchange="this.parentNode.nextSibling.value=this.value">
                 <logic:present name="carType_list">
              		<option value=""></option>
                 	<logic:iterate id="aa" name="carType_list">
                 		<option value='<bean:write name="aa" property="worktype"/>'><bean:write name="aa" property="worktype"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
                 <html:text property="car_move_log.cartype" style="width:82px;position:absolute;left:0px;"></html:text>
				</div> 
                 </th>
				
				<th>
					&nbsp;&nbsp;
				</th>
				
				<th>
					<html:submit>统 计</html:submit>
				</th>

			</tr>
			<tr>
				<th>
					考勤结束日期:
				</th>
				
				<%--
				<th>
					<html:text property="car_move_log.etime" size="18"
						onfocus="show_cele_date('','',Search_car_move_log_Form['car_move_log.etime'])" />
				</th>
				--%>
				<th align="left"><html:text property="car_move_log.etime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('car_move_log.etime')})"/>&nbsp;</th>
				<th>
					&nbsp;&nbsp;
				</th>
                 <th align="left">工作时间小于:</th>
                 
                 <th align="left">
                 <html:text property="car_move_log.maxstime" size="8" styleId="file2" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('car_move_log.maxstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name2"  onclick="this.checked?file2.disabled=false:file2.disabled=true" />&nbsp;
                 </th>
				<th>
					&nbsp;&nbsp;
				</th>
				<th align="right">
					车 辆:
				</th>
				<th  align="left">
 
 					<html:text property="car_move_log.cardid" size="12"
						maxlength="6" styleId="names2" onkeyup="findNames2();" />
					&nbsp;
					<div style="position:absolute;" id="popup2">
						<table id="name_table2" bgcolor="#FFFAFA" border="0"
							cellspacing="0" cellpadding="0" />
							<tbody id="name_table_body2"></tbody>
						</table>
					</div>
				</th>
				
				
				<th>
					&nbsp;&nbsp;
				</th>
				
                 <th>
                   
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0"> 
	                 	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>  
	                     <input type="button"  value="打印预览"  onclick="javascript:window.open('car_move_logPrint.jsp')">
	               </logic:notEqual>
                 </th>
              
			</tr>
		</table>
		<br />
		<%-- 
          <div   style="overflow:auto;width:825;height:500;"> 
          --%>
<div id = "printdiv">
		<table cellspacing="1" cellpadding="1" bgcolor="#6CA6CD" border="0"
			style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
			<tr>
				<td width="67" align="left" bgcolor="#B0C4DE">
					序号
				</td>
				<td width="130" align="center" bgcolor="#B0C4DE">
					车名称
				</td>
				<td width="100" align="center" bgcolor="#B0C4DE">
					卡号/合计
				</td>
				<td width="80" align="center" bgcolor="#B0C4DE">
					车辆类型
				</td>
				<td width="150" align="center" bgcolor="#B0C4DE">
					入井时间
				</td>
				<td width="150" align="center" bgcolor="#B0C4DE">
					升井时间
				</td>
				<td width="100" align="center" bgcolor="#B0C4DE">
					工作时间
				</td>

			</tr>
			
			<logic:present name="car_move_log_list" scope="request">
			
			<tr>
				<td width="67" align="left" bgcolor="#B0C4DE">
					
				</td>
				<td width="130" align="left" bgcolor="#B0C4DE">
					运行总趟数
				</td>
				<td width="100" align="left" bgcolor="#B0C4DE">
					<%=vo.getAcount()%>
				</td>
				<td width="80" align="left" bgcolor="#B0C4DE">
					
				</td>
				<td width="150" align="left" bgcolor="#B0C4DE">
					
				</td>
				<td width="150" align="left" bgcolor="#B0C4DE">
					
				</td>
				<td width="100" align="left" bgcolor="#B0C4DE">
					
				</td>

			</tr>
			<tr>
				<td width="67" align="left" bgcolor="#B0C4DE">
					合计
				</td>
				<td width="130" align="left" bgcolor="#B0C4DE">
					运行总时长
				</td>
				<td width="100" align="left" bgcolor="#B0C4DE">
					<%=vo.getAtime()%>
				</td>
				<td width="80" align="left" bgcolor="#B0C4DE">
					
				</td>
				<td width="150" align="left" bgcolor="#B0C4DE">
					
				</td>
				<td width="150" align="left" bgcolor="#B0C4DE">
					
				</td>
				<td width="100" align="left" bgcolor="#B0C4DE">
					
				</td>

			</tr>
			<tr>
				<td width="67" align="left" bgcolor="#B0C4DE">
					
				</td>
				<td width="130" align="left" bgcolor="#B0C4DE">
					平均运行时长
				</td>
				<td width="100" align="left" bgcolor="#B0C4DE">
				   <%=vo.getCounttime()%>
				</td>
				<td width="80" align="left" bgcolor="#B0C4DE">
					
				</td>
				<td width="150" align="left" bgcolor="#B0C4DE">
					
				</td>
				<td width="150" align="left" bgcolor="#B0C4DE">
					
				</td>
				<td width="100" align="left" bgcolor="#B0C4DE">
					
				</td>

			</tr>
			<%--<tr>
			<td align="left" bgcolor="#E6E6FA">
			运行总趟数
			</td>
			<td align="left" bgcolor="#E6E6FA">
			<bean:write name="car_move_log_list" property="acount" />
			</td>
			<td align="left" bgcolor="#E6E6FA">
			
			</td>
			<td align="left" bgcolor="#E6E6FA">
			
			</td>
			<td align="left" bgcolor="#E6E6FA">
			
			</td>
			<td align="left" bgcolor="#E6E6FA">
			
			</td>
			<td align="left" bgcolor="#E6E6FA">
			
			</td>
			</tr>
				--%><logic:iterate name="car_move_log_list" id="dw">
					<tr>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="dw" property="count" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="dw" property="carname" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="dw" property="cardid" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="dw" property="cartype" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="dw" property="intime" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="dw" property="upwelltime" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="dw" property="worktime" />
						</td>

					</tr>
				</logic:iterate>
			</logic:present>
			
			<%--
			<logic:present name="pagination">
				<tr>
					<td colspan="7" align="left" bgcolor="#E6E6FA">
						<page:pagination path="car_move_log.do?method=getList"
							parameter="page" formName="Search_car_move_log_Form" />
					</td>
				</tr>
			</logic:present>
		--%></table></div>
	</html:form>
</body>
</html:html>
<script  language="javascript">
	if("<%=name1%>"=="true"){
	   	document.getElementById("name1").checked=true;
	   	document.getElementById("file1").disabled=false;
	}if("<%=name2%>"=="true"){
	   	document.getElementById("name2").checked=true;
	   	document.getElementById("file2").disabled=false;
	}
</script>