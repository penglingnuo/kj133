<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="区域超员时段小计"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>overfreight_call_down.jsp</title>
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
	<script language="javascript" src="js/CardIdORName.js"></script>
	<link href="Css/calendar-blue.css" rel="stylesheet">

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


	<html:form action="overfreight_call_down?method=init" onsubmit="return check()">

		<%-- 
          <div   style="overflow:auto;width:825;height:500;"> 
          --%>
          
          <td ><font size="2" color="red">区域超员时段小计:</font>
          </td>
          <td>
          
                     <logic:equal  name="listCount1" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount1" value="0">
	                 	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>   
	                     <input type="button"  value="打印预览"  onclick="javascript:window.open('overfreight_call_downPrint.jsp')">
	               </logic:notEqual>
           </td>
          
          
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
				<td width="150" align="center" bgcolor="#B0C4DE">
					超员时刻
				</td>
				<td width="80" align="center" bgcolor="#B0C4DE">
					额定总人数
				</td>
				
				<td width="80" align="center" bgcolor="#B0C4DE">
					额定采掘人数
				</td>
				<td width="50" align="center" bgcolor="#B0C4DE">
					总人数
				</td>
				<td width="50" align="center" bgcolor="#B0C4DE">
					采掘人数
				</td>
				<td width="80" align="center" bgcolor="#B0C4DE">
					总超员人数
				</td>
				<td width="80" align="center" bgcolor="#B0C4DE">
					采掘超员人数
				</td>
				
				
				

			</tr>
			<logic:present name="relist" scope="request">
				<logic:iterate name="relist" id="relist">
				<tr onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
					
						<td align="center" bgcolor="#E6E6FA">
							<bean:write name="relist" property="areaid" />						
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="aname"/>
							
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="recordtime" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="maxsum" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="caijuesum" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="peoplecount" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="minercount" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="allchaomans" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="chaomans" />
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
		--%></table></div>
	</html:form>
</body>
</html:html>

