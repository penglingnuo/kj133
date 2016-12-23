<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="区域超时人次统计"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>area_overtime_top.jsp</title>
	<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
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
	         var stime=document.all['area_overtime_top.stime'].value;
	         var etime=document.all['area_overtime_top.etime'].value;
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

	<html:form action="area_overtime_top?method=init" onsubmit="return check()">
		<table width="650">
			<tr>
				<th>
					起始日期：
				</th>
				<th align="left">
                 <html:text property="area_overtime_top.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('area_overtime_top.stime')})"/>
                 </th>
				
				<%--<th>
					<html:text property="area_overtime_top.stime" size="18"
						onfocus="show_cele_date('','',Search_area_overtime_top_Form['area_overtime_top.stime'])" />
				</th>
				--%><th>
					&nbsp;&nbsp;
				</th>
				<th>
					&nbsp;&nbsp;
				</th>
				<th>
					截止日期：
				</th>
				<th align="left">
                 <html:text property="area_overtime_top.etime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('area_overtime_top.etime')})"/>
                 </th>
				<%--
				<th>
					<html:text property="area_overtime_top.etime" size="18"
						onfocus="show_cele_date('','',Search_area_overtime_top_Form['area_overtime_top.etime'])" />
				</th>
				--%>
				<th>
					&nbsp;&nbsp;
				</th>
				
				
				<th>
					<html:submit>查 询</html:submit>
				</th>
				<th>
					&nbsp;&nbsp;
				</th>
				<th >
                   
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0">  
	                 	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                     <input type="button"  value="打印预览"  onclick="javascript:window.open('area_overtime_topPrint.jsp')">
	               </logic:notEqual>
                 </th>

			</tr>
		
		</table>
	  <td ><font size="2" color="red">区域超时人次统计:</font></td>
		<%-- 
          <div   style="overflow:auto;width:825;height:500;"> 
          --%>
  
<div id = "printdiv">        
		<table cellspacing="1" cellpadding="1" bgcolor="#6CA6CD" border="0"
			style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
			
			<tr>
				<td width="67" align="left" bgcolor="#B0C4DE">
					区域号
				</td>
				<td width="100" align="center" bgcolor="#B0C4DE">
					区域名称
				</td>
				<td width="60" align="center" bgcolor="#B0C4DE">
					超时人次
				</td>
				<td width="150" align="center" bgcolor="#B0C4DE">
					起始时间
				</td>
				<td width="150" align="center" bgcolor="#B0C4DE">
					结束时间
				</td>
				

			</tr>
			<logic:present name="relist" scope="request">
				<logic:iterate name="relist" id="relist">
				<tr onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
					
						<td align="center" bgcolor="#E6E6FA">
							<a href="area_overtime_down.do?method=init&areaid=<bean:write name="relist" property="areaid" />&st=<bean:write name="relist" property="st" />&et=<bean:write name="relist" property="et" />" title="区域超时明细" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="down" >
							<bean:write name="relist" property="areaid" />
							</a>
							
							</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="areaname"/>
							
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="ncount" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
						<bean:write name="relist" property="st" />
						
						</td>
						<td align="left" bgcolor="#E6E6FA">
						<bean:write name="relist" property="et" />
						
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

