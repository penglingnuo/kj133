
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="员工考勤日报" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>employee_check_daily.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script src="js/Calendar2.js"></script><!-- 不带分秒-->
    <script language="javascript" src="js/StafferORName.js"></script>
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
  </head>
  
  <body bgColor="white" background="Image/right.gif">
  <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
      <html:form action="/employee_check_daily?method=init" >
          <table width="750" border="0">
              <tr>
                 <th>日 期:</th>
                 <th><html:text property="employee.stime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('employee.stime')})"/></th>
                 <%--<th>
                     <html:text property="employee.stime"  size="9" onfocus="this.blur()" />
                     <A onclick="return showCalendar('employee.stime', 'y-mm-dd');" href="#"><img src="Image/Button.gif"  id="IMG2"  border="0"/></A>
                 </th>
                 --%><th>&nbsp;&nbsp;</th>
                 <th align="left">部 门:</th>
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
                 <html:text property="employee.gro" style="width:100px;position:absolute;left:0px;"></html:text>
				&nbsp;
				</div>
                 </th>                 
				
                 <%--
				<th align="left">
					<html:select property="employee.type" style="width:100px">&nbsp;
		                <html:option value=""></html:option>
		                <html:option value="矿领导">矿领导</html:option>
		                <html:option value="机关人员">机关人员</html:option>
		                <html:option value="安监员">安监员</html:option>
		                <html:option value="瓦检员">瓦检员</html:option>
		                <html:option value="区队领导">区队领导</html:option>
		                <html:option value="车辆司机">车辆司机</html:option>
		                <html:option value="正式工">正式工</html:option>
		                <html:option value="劳务工">劳务工</html:option>
		                <html:option value="外委工">外委工</html:option>
		               
					</html:select>&nbsp;
				</th>
				<th>
					&nbsp;
				</th>
				--%>
                 
                 <th>员 工:</th>
                 <th>
                     <html:text property="employee.cardid" size="15" maxlength="6" styleId="names2" onkeyup="findNames2();" />
                     <div style="position:absolute;" id="popup2">
				        <table id="name_table2" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="name_table_body2"></tbody>
				        </table>  
                     </div>
                 </th>
                 <th>&nbsp;&nbsp;</th>
                 <th> <html:submit>统 计</html:submit></th>
                 <th>&nbsp;&nbsp;</th>
                 <th>
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                </logic:equal>
	                <logic:notEqual name="listCount" value="0"> 
	                	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>  
	                     <input type="button"  value="打印预览"  onclick="javascript:window.open('employee_check_dailyPrint.jsp')">
	                </logic:notEqual>
                 </th>
              </tr>
          </table><br>
          <div id = "printdiv">
        <table cellspacing="1" cellpadding="1"  bgcolor="#000" border="0" width="900" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;" id="table">
  		    <thead>
  		    <tr>
  		      <td align="left" width="50" bgcolor="#ffffff">序号</td>
 		      <td width="50" align="left" bgcolor="#ffffff">员工编号</td>
 		      <td width="44" align="left" bgcolor="#ffffff">卡号</td>
              <td width="54" align="left" bgcolor="#ffffff">姓名</td>
              <td width="88" align="left" bgcolor="#ffffff">单位</td>
              <td width="57" align="left" bgcolor="#ffffff">类型</td>
              <td width="43" align="left" bgcolor="#ffffff">早班</td>
              <td width="43" align="left" bgcolor="#ffffff">中班</td>
              <td width="43" align="left" bgcolor="#ffffff">晚班</td>
              <td width="50" align="left" bgcolor="#ffffff">入井次数</td>
              
              <td width="70" align="left" bgcolor="#ffffff">总时长</td>
              
           </tr>
           </thead>
           <tbody>
           <logic:present name="emp">
             <logic:iterate name="emp" id="list">
		           <tr>
		             <td align="left" bgcolor="#ffffff"><bean:write name="list" property="count" /></td>
		             <td align="left" bgcolor="#ffffff"><bean:write name="list" property="stafferid" /></td>
		             <td align="left" bgcolor="#ffffff"><bean:write name="list" property="cardid" /></td>
		             <td align="left" bgcolor="#ffffff"><bean:write name="list" property="name" /></td>
		             <td align="left" bgcolor="#ffffff"><bean:write name="list" property="group" /></td>
		             <td align="left" bgcolor="#ffffff"><bean:write name="list" property="type" /></td>
		             <td align="left" bgcolor="#ffffff"><bean:write name="list" property="zao" /></td>
		             <td align="left" bgcolor="#ffffff"><bean:write name="list" property="zhong" /></td>
		             <td align="left" bgcolor="#ffffff"><bean:write name="list" property="wan" /></td>
		             <td align="left" bgcolor="#ffffff"><bean:write name="list" property="downsum" /></td>
		             <td align="left" bgcolor="#ffffff"><bean:write name="list" property="worktime" /></td>
		             
		           </tr>
               </logic:iterate>
           </logic:present>
           </tbody>
        </table> 
        	<div id="controls"> 
		<div id="perpage"> 
			<select onchange="sorter.size(this.value)"> 
				<option value="100" selected="selected">100</option> 
				<option value="200">200</option> 
				<option value="300">300</option> 
				<option value="500">500</option> 
			</select> 
			<span>Entries Per Page</span> 
		</div> 
		<div id="navigation"> 
			<img src="images/first.GIF" width="25" height="25" alt="首页" onclick="sorter.move(-1,true)" /> 
			<img src="images/previous.GIF" width="25" height="25" alt="前一页" onclick="sorter.move(-1)" /> 
			<img src="images/next.GIF" width="25" height="25" alt="后一页" onclick="sorter.move(1)" /> 
			<img src="images/last.GIF" width="25" height="25" alt="最后一页" onclick="sorter.move(1,true)" /> 
		</div> 
		<div id="text">Displaying Page <span id="currentpage"></span> of <span id="pagelimit"></span></div> 
	</div> 
        
        </div> 
     </html:form>   
  	<script type="text/javascript" src="js/script.js"></script> 
	<script type="text/javascript"> 
		var sorter = new TINY.table.sorter("sorter");
		sorter.head = "head";
		sorter.asc = "asc";
		sorter.desc = "desc";
		sorter.even = "evenrow";
		sorter.odd = "oddrow";
		sorter.evensel = "evenselected";
		sorter.oddsel = "oddselected";
		sorter.paginate = true;
		sorter.currentid = "currentpage";
		sorter.limitid = "pagelimit";
		sorter.init("table",1);
  </script>     
  </body>
</html:html>
