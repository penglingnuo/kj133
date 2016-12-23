<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="工作异常员工清单"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>abnormity_work_top.jsp</title>
	<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script>
	<script language="javascript" src="js/page.js"></script>
	<%--分页--%>
	<script language="JavaScript" src="js/calendar.js"></script>
	<%--时间--%>
	<link href="Css/TestDate.css" rel="stylesheet">
	<%--背景--%>
	<script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
	<script language="javascript" src="js/CardIdORName.js"></script>
	<link href="Css/calendar-blue.css" rel="stylesheet">

	<script language="javascript" src="js/StafferORName.js"></script>
	<SCRIPT language="JavaScript">
       function check()
         {
	         var stime=document.all['abnormity_work_top.stime'].value;
	         var etime=document.all['abnormity_work_top.etime'].value;
	         var stime1 = new Date(stime.substring(0,4),stime.substring(5,7),stime.substring(8,10),stime.substring(11,13),stime.substring(14,16),stime.substring(17,19)); 
	         var etime1 = new Date(etime.substring(0,4),etime.substring(5,7),etime.substring(8,10),etime.substring(11,13),etime.substring(14,16),etime.substring(17,19)); 
	         
	         var DyMilli = 1000 * 60 * 60 * 24 

             var days = Math.round((etime1.getTime()-stime1.getTime()) / DyMilli); 

	         if( stime > etime){
	            alert('起始时间不能大于截止时间');
	            return false;

             }if(days>7){
                alert('起始时间与截止时间不能超过7天');
                return false;
             }else{
             
             return true;
             }
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
        <style TYPE="text/css">
		<!--
		 A:link{color: #6699CC;text-decoration:none}
		 A:hover{color:#FF0000;}
		 -->
    </style>
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

	<html:form action="abnormity_work_top?method=init" onsubmit="return check()">
		<table width="540">
			<tr>
				<th>
					起始日期：
				</th>
				<th align="left">
                 <html:text property="abnormity_work_top.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('abnormity_work_top.stime')})"/>&nbsp;
                 </th>
				<%--<th>
					<html:text property="abnormity_work_top.stime" size="18"
						onfocus="show_cele_date('','',Search_abnormity_work_top_Form['abnormity_work_top.stime'])" />
				</th>
				--%><th>
					&nbsp;&nbsp;
				</th>
				<th>
					&nbsp;&nbsp;
				</th>
				<th> 
					员 工： 
				</th>
				<th  align="left">
 
 					<html:text property="abnormity_work_top.stafferid" size="12"
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
					&nbsp;&nbsp;
				</th>
				<th>
					&nbsp;&nbsp;
				</th>
				<th>
					&nbsp;&nbsp;
				</th>
				<th>
					&nbsp;&nbsp;
				</th>


			</tr>
			<tr>
				<th>
					截止日期：
				</th>
				<th align="left">
                 <html:text property="abnormity_work_top.etime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('abnormity_work_top.etime')})"/>&nbsp;
                 </th>
				<%--<th>
					<html:text property="abnormity_work_top.etime" size="18"
						onfocus="show_cele_date('','',Search_abnormity_work_top_Form['abnormity_work_top.etime'])" />
				</th>
				--%><th>
					&nbsp;&nbsp;
				</th>
				<th>
					&nbsp;&nbsp;
				</th>

				
				<th>
					<html:submit>查  询</html:submit>
				</th>
				<th>
                   
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0"> 
	                 	 <input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>  
	                     <input type="button"  value="打印预览"  onclick="javascript:window.location.href='abnormity_work_topPrint.jsp'">
	               </logic:notEqual>
                 </th>
				</tr>
		
		</table>
		<td ><font size="2" color="red">工作异常员工清单:</font></td>
		<%-- 
          <div   style="overflow:auto;width:825;height:500;"> 
          --%>
 
<div id = "printdiv">         
		<table cellspacing="1" cellpadding="1" bgcolor="#6CA6CD" border="0"
			style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
			<tr>
				<td width="67" align="left" bgcolor="#B0C4DE">
					员工编号
				</td>
				<td width="100" align="center" bgcolor="#B0C4DE">
					姓名
				</td>
				<td width="67" align="center" bgcolor="#B0C4DE">
					卡号
				</td>
				<td width="150" align="center" bgcolor="#B0C4DE">
					入井时间
				</td>
				<td width="150" align="center" bgcolor="#B0C4DE">
					升井时间
				</td>
				<td width="50" align="center" bgcolor="#B0C4DE">
					异常次数
				</td>
				<td width="80" align="center" bgcolor="#B0C4DE">
					班组
				</td>
				<td width="80" align="center" bgcolor="#B0C4DE">
					工种
				</td>
				<td width="80" align="center" bgcolor="#B0C4DE">
					职务
				</td>


				
				

			</tr>
			<logic:present name="relist" scope="request">
				<logic:iterate name="relist" id="relist">
				<tr onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
				
						<td align="left" bgcolor="#E6E6FA">
						<a href="abnormity_work_down.do?method=init&stafferid=<bean:write name="relist" property="stafferid" />&downtime=<bean:write name="relist" property="downtime" />" title="员工工作异常明细" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="down" >
							<bean:write name="relist" property="stafferid" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="name" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="cardid" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="downtime" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="uptime" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="abnormitylist" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="gro" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="worktype" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="occupation" />
						</td>

						
						

					</tr>
				</logic:iterate>
			</logic:present><%--
			<logic:present name="pagination">
				<tr>
					<td colspan="7" align="left" bgcolor="#E6E6FA">
						<page:pagination path="car_move_log.do?method=getList"
							parameter="page" formName="Search_car_move_log_Form" />
					</td>
				</tr>
			</logic:present>
		--%>
		
		</table></div>
		
	</html:form>
</body>

</html:html>

