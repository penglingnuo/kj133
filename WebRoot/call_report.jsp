
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="呼救历史查询" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<%
String name1=(String)request.getAttribute("name1");
String name2=(String)request.getAttribute("name2");

 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>call_report.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <script language="javascript" src="js/page.js"></script><!--分页 -->
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
     <SCRIPT language="javascript">
        function check()
          {
               var start=document.all['ser_call_report.stime'].value;
               var end=document.all['ser_call_report.etime'].value;
	           if(start>end){
	                 alert('起始时间不能大于截止时间，请重新输入');
	                 return false;
	               }
               return true;
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
     <html:form action="call_report?method=getList" onsubmit="return check()">
      <table width="620">
              <tr align="left">
              <th>起始日期：</th>
             <th align="left">
                 <html:text property="ser_call_report.stime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_call_report.stime')})"/>&nbsp;
             </th>
             
             <th align="left">起始时间：</th>
             <th align="left">
	             <html:text property="ser_call_report.minstime" size="8" styleId="file1" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_call_report.minstime')})"/>
	             </th>
	             <th align="left"><input type="checkbox" name="name1" onclick="this.checked?file1.disabled=false:file1.disabled=true" />&nbsp;
	         </th>
             
             <th align="left">班组：</th>   
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
                 <html:text property="ser_call_report.gro" style="width:100px;position:absolute;left:0px;"></html:text>
				</div>
                 </th>    
                
                 
                 <th><html:submit>查 询</html:submit></th>
              </tr>
              <tr>
                 <th align="left">截止日期：</th>
                 <th align="left">
                 <html:text property="ser_call_report.etime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_call_report.etime')})"/>&nbsp;
           	     </th>
                 <th align="left">截止时间：</th>
                 <th align="left">
                 <html:text property="ser_call_report.maxstime" size="8" styleId="file2" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_call_report.maxstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name2" onclick="this.checked?file2.disabled=false:file2.disabled=true" />&nbsp;
                 </th>
                 
                 
                 <th align="left">员工：</th>
                 <th  align="left">
                    <html:text property="ser_call_report.sid" size="12"   styleId="names2" onkeyup="findNames2();" />
                    <div style="position:absolute;" id="popup2">
				        <table id="name_table2" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="name_table_body2"></tbody>
				        </table>  
                     </div>
                     </th>
                    
                    <th>
                    <logic:equal  name="listCount" value="0">
                          <input type="button"  value="打印预览"  disabled>
	                </logic:equal>
	                <logic:notEqual name="listCount" value="0">  
	                	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                      <input type="button"  value="打印预览"  onclick="javascript:window.open('call_reportPrint.jsp')">
	                </logic:notEqual>
                 </th>
                   
              </tr>
          </table><br>
     
<div id = "printdiv">  
       <table    cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
           <tr> 
            <td width="50"  align="left" bgcolor="#B0C4DE">卡号</td>
            <td width="60"  align="left" bgcolor="#B0C4DE">姓名</td>
            <td width="80"  align="left" bgcolor="#B0C4DE">班组</td>
            <td width="90"  align="left" bgcolor="#B0C4DE">工种</td>
            <td width="100" align="left" bgcolor="#B0C4DE">状态信息</td>
            <td width="140" align="left" bgcolor="#B0C4DE">开始呼救时间</td>
            <td width="140" align="left" bgcolor="#B0C4DE">最后呼救时间</td>
           </tr>
          <logic:present name="Call_report_List">
             <logic:iterate name="Call_report_List" id="report">
                <tr>
                   <td align="left" bgcolor="#E6E6FA"><bean:write name="report" property="stafferid" /> </td>
                   <td align="left" bgcolor="#E6E6FA"><bean:write name="report" property="username" /> </td>
                   <td align="left" bgcolor="#E6E6FA"><bean:write name="report" property="gro" /> </td>
                   <td align="left" bgcolor="#E6E6FA"><bean:write name="report" property="type" /> </td>
                   <td align="left" bgcolor="#E6E6FA"><bean:write name="report" property="info" /> </td>
                   <td align="left" bgcolor="#E6E6FA"><bean:write name="report" property="stime" /> </td>
                   <td align="left" bgcolor="#E6E6FA"><bean:write name="report" property="etime" /> </td>
                </tr>
             </logic:iterate>
          </logic:present>
             <logic:present name="pagination" >
                <TR>
					 <TD colspan="7" align="center" bgcolor="#E6E6FA">
					       <page:pagination path="call_report.do?method=getList" name="pagination" parameter="page"  formName="Search_Call_Report_Form"/>
					 </TD>  
                 </TR> 
             </logic:present> 
       </table></div>
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