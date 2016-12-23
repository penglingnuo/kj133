<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="入井超时查询"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<%
String name1=(String)request.getAttribute("name1");
String name2=(String)request.getAttribute("name2");
String name3=(String)request.getAttribute("name3");
String name4=(String)request.getAttribute("name4");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>overTime.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script language="JavaScript" src="js/calendar.js"></script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%> 
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <script language="javascript" src="js/CardIdORName.js"></script>
    <script language="javascript" src="js/jquery.js"></script>
    <script language="javascript" src="js/export_xls.js"></script>
    
    <SCRIPT>
       function check()
        {
         
         var h=document.getElementById("hour").value;
	         if(h ==""){
	         	 return true;
	         }else{
	         	 //if(/^(\\-?)(\\d+)$/.test(h))
	         	if(isNaN(h)){
	         	alert("请输入数字！");
				return false;
	         	}
				else
			 	return true;
				
	         }
        
        /*  var stime=document.all['overtime.stime'].value;
         var etime=document.all['overtime.etime'].value;
         if( stime > etime)
          {
            alert('起始时间不能大于截止时间');
            return false;
          }
          return true; */
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
		}

	</SCRIPT>
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
    <html:form action="/OverTime?method=getList" onsubmit="return check()" >
       <table width="100%">
         <tr>
         <th align="left">开始日期:</th>
                 <th align="left">
                 <html:text property="overtime.stime" size="9" style="width:125px;" onfocus="WdatePicker({skin:'whyGreen',
                 isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('overtime.stime')})"/>&nbsp;
                 </th>
                  <th align="left">结束日期:</th>
                 <th align="left">
                 <html:text property="overtime.etime" size="9" style="width:125px;" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,
                 dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('overtime.etime')})"/>&nbsp;
                 </th>
                 <th align="left">部门:</th>
                 <th align="left">
                 <div style="position:relative;">
                 <span style="margin-left:100px;width:18px;overflow:hidden;">
                 <select  style="width:118px;margin-left:-100px" onchange="this.parentNode.nextSibling.value=this.value">
                 <logic:present name="dpt_list">
              		<option value=""></option>
                 	<logic:iterate id="aa" name="dpt_list">
                 		<option value='<bean:write name="aa" property="wordvalue"/>'><bean:write name="aa" property="wordvalue"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
                 <html:text property="overtime.dpt" style="width:100px;position:absolute;left:0px;"></html:text>
				</div>
                 </th>
                 
                 <th align="left">超时时间:</th>
                 <th align="left">
					<select name="overtime.cdn">
						<option value="1">等于</option>
						<option value="2">小于</option>
						<option value="3">大于</option>
					</select>
					<html:text styleId="hour" property="overtime.hours" style="width:60px;"></html:text>
				</th>
             
                 <th><html:submit>查 询</html:submit></th>
                 
                 <th>
                 <logic:equal  name="listCount" value="0">
                       <input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>
	                </logic:equal>
	                <logic:notEqual name="listCount" value="0">
	                	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>   
	                </logic:notEqual>
                 </th>
				<th>
					<logic:equal name="listCount" value="0">
						<input type="button" value="导出excel" disabled="disabled">
					</logic:equal>
					<logic:notEqual name="listCount" value="0">
						<input type="button" value="导出excel" onclick="ExportXls('OverTime.do?method=doOpenExcel')">
					</logic:notEqual>
				</th>
         </tr>
       </table><br/>
       <div id = "printdiv">
	     <table width="100%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
		     <tr>
			    <td width="6%"  align="left" bgcolor="#B0C4DE">卡号</td>
				<td width="6%"  align="left" bgcolor="#B0C4DE">姓名</td>
				<td width="12%"  align="left" bgcolor="#B0C4DE">部门</td>
				<td width="12%"  align="left" bgcolor="#B0C4DE">班组</td>
				<td width="8%" align="left" bgcolor="#B0C4DE">状态信息</td>
				<td width="13%" align="left" bgcolor="#B0C4DE">入井时间</td>
				<td width="13%" align="left" bgcolor="#B0C4DE">升井时间</td>
				<td width="10%" align="left" bgcolor="#B0C4DE">停留时间</td>
				<td width="10%" align="left" bgcolor="#B0C4DE">额定时间</td>
				<td width="10%" align="left" bgcolor="#B0C4DE">超时时间</td>
				
			  </tr>
				<logic:present name="over_List">
				  <logic:iterate name="over_List" id="list">
				      <tr>
					     <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="cardid" /></td>
					     <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="username" /></td>
					     <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="department" /></td>
					     <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="gro" /></td>
					     <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="info" /></td>
					     <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="downtime" /></td>
					     <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="uptime" /></td>	
					     <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="staytime" /></td>
					     <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="ratetime" /></td>
					     <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="overtime" /></td>	    
				     </tr>
				  </logic:iterate>
				</logic:present>
				 <logic:present name="pagination" >
		           <tr>
					  <td colspan="10" align="center" bgcolor="#E6E6FA"> 
					       <page:pagination path="OverTime.do?method=getList" name="pagination" parameter="page"  formName="Search_OverTime_Form" />
					  </td>  
		           </tr> 
                 </logic:present> 
        </table></div>
    </html:form>
  </body>
</html:html>
