
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="时段未出勤车辆统计"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>unworkcar.jsp</title>
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
	<script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"></script>
	<link href="Css/calendar-blue.css" rel="stylesheet">
	<SCRIPT language="JavaScript">
       function check()
         {
	         var stime=document.all['unworkcar.stime'].value;
	         var etime=document.all['unworkcar.etime'].value;
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

	<html:form action="unworkcar.do?method=getList" onsubmit="return check()">
		<table width="615">
			<tr>
				<th>
					开始时间：
				</th>
				<th>
                 <html:text property="unworkcar.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('unworkcar.stime')})"/>
                 </th>
				<%--<th>
					<html:text property="unworkcar.stime" size="18"
						onfocus="show_cele_date('','',Search_unworkcar_Form['unworkcar.stime'])" />
				</th>
				--%><th>
					&nbsp;&nbsp;
				</th>
				<th>
					结束时间：
				</th>
				<th><html:text property="unworkcar.etime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('unworkcar.etime')})"/>
				</th>
				<%--<th>
					<html:text property="unworkcar.etime" size="18"
						onfocus="show_cele_date('','',Search_unworkcar_Form['unworkcar.etime'])" />
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
                  <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	              </logic:equal>
	              <logic:notEqual name="listCount" value="0"> 
	              	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>  
	                  <input type="button"  value="打印预览"  onclick="javascript:window.open('unworkcarPrint.jsp')">
	              </logic:notEqual>
                  
              </th>

			</tr>

		</table>
		<br />
		<%-- 
          <div   style="overflow:auto;width:825;height:500;"> 
          <table  width="1642" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
          --%>
          <div id = "printdiv">
		<table cellspacing="1" cellpadding="2" bgcolor="#6CA6CD" border="0"
			style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
			<tr>
				<td width="30" align="left" bgcolor="#B0C4DE">
					序号
				</td>
				<td width="100" align="left" bgcolor="#B0C4DE">
					车辆类型
				</td>
				<td width="60" align="left" bgcolor="#B0C4DE">
					总车数
				</td>
				<td width="500" align="left" bgcolor="#B0C4DE">
					车名称
				</td>
				<td width="60" align="left" bgcolor="#B0C4DE">
					合计
				</td>
				<td width="80" align="left" bgcolor="#B0C4DE">
					未出勤率
				</td>

			</tr>
			<logic:present name="unworkcar_list" scope="request">
				<logic:iterate name="unworkcar_list" id="dw">
					<tr>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="dw" property="count" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="dw" property="cartype" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="dw" property="carcount" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="dw" property="carname" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="dw" property="reckon" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="dw" property="unworkodds" />
						</td>

					</tr>
				</logic:iterate>
			</logic:present><%--
			<logic:present name="pagination">
				<tr>
					<td colspan="6" align="left" bgcolor="#E6E6FA">
						<page:pagination path="unworkcar.do?method=getList"
							parameter="page" formName="Search_unworkcar_Form" />
					</td>
				</tr>
			</logic:present>
		--%></table></div>
	</html:form>
</body>
</html:html>
