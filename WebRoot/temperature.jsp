
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="温度查询" %> 
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
    
    <title>temperature.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
     <script language="JavaScript" src="js/calendar.js"></script><!--带分秒-->
    <script language="javascript" src="js/page.js"></script>
    <script language="javascript" src="js/Cardreader.js"></script>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
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
   <SCRIPT language="javascript">
         function check()
          {
               var start=document.all['ser_temperature.startime'].value;
               var end=document.all['ser_temperature.endtime'].value;
	            if(start > end)
	               {
	                 alert('起始时间不能大于截止时间，请重新输入');
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
  </head>

  <body bgColor="white" background="Image/right.gif">
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     
      <html:form action="/temperature?method=getList" onsubmit="return check()">
          <table>
             <tr>
             <th align="left">起始日期:</th>
                 <%--<th align="left"><html:text property="ser_areachecklist.stime"  size="18" onfocus="show_cele_date('','',Search_AreaCheckList_Form['ser_areachecklist.stime'])" />&nbsp;</th>
                 --%>
                 <th align="left">
                 <html:text property="ser_temperature.startime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_temperature.startime')})"/>&nbsp;
                 </th>
                 <th align="left">起始时间:</th>
                 
                 <th align="left">
                 <html:text property="ser_temperature.minstime" size="8" styleId="file1" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_temperature.minstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name1" onclick="this.checked?file1.disabled=false:file1.disabled=true" />&nbsp;
                 </th>
                 <th align="right">
                     分站：
                 </th>
                 <th align="left">
                        
			           <html:text property="ser_temperature.cardreaderid" size="18"    styleId="names3" onkeyup="findNames3();" />
				           <div style="position:absolute;" id="popup3">
					        <table id="name_table3" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
					            <tbody id="name_table_body3"></tbody>
					        </table>  
                         </div>&nbsp;&nbsp;
                 </th>
                 
             </tr>
             <tr>
             <th align="left">截止日期:</th>
                 <%--<th align="left"><html:text property="ser_areachecklist.stime"  size="18" onfocus="show_cele_date('','',Search_AreaCheckList_Form['ser_areachecklist.stime'])" />&nbsp;</th>
                 --%>
                 <th align="left">
                 <html:text property="ser_temperature.endtime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_temperature.endtime')})"/>&nbsp;
                 </th>
                 <th align="left">截止时间:</th>
                 
                 <th align="left">
                 <html:text property="ser_temperature.maxstime" size="8" styleId="file2" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_temperature.maxstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name2" onclick="this.checked?file2.disabled=false:file2.disabled=true" />&nbsp;&nbsp;&nbsp;&nbsp;
                 </th>
                 
                 <th align="right">
			         <html:submit>查 询</html:submit>
			         </th>
                 <th >
			         <logic:equal  name="listCount" value="0">
                          <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0">  
	                 	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                      <input type="button"  value="打印预览"  onclick="javascript:window.open('temperaturePrint.jsp')">
	                 </logic:notEqual>
                 </th>
             </tr>
          </table><br>
       <div id = "printdiv">
       <table width="600" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
              <tr>
                <td width="70"  align="left" bgcolor="#B0C4DE">分站号</td>
                <td width="240" align="left" bgcolor="#B0C4DE">分站名称</td>
                <td width="70"  align="left" bgcolor="#B0C4DE">温度</td>
                <td width="200" align="left" bgcolor="#B0C4DE">记录时间</td>
              </tr>
             <logic:present name="temperature_list" scope="request">
                 <logic:iterate name="temperature_list" id="te">
                     <tr>
                        <td align="left" bgcolor="#E6E6FA"><bean:write name="te" property="cid" /></td>
                        <td align="left" bgcolor="#E6E6FA"><bean:write name="te" property="cname" /></td>
                        <td align="left" bgcolor="#E6E6FA"><bean:write name="te" property="temp" /></td>
                        <td align="left" bgcolor="#E6E6FA"><bean:write name="te" property="times" /></td>
                     </tr>
                 </logic:iterate>
             </logic:present> 
                 <logic:present name="pagination" >
	               <TR>
			           <TD colspan="4" align="center" bgcolor="#E6E6FA">
			                 <page:pagination path="temperature.do?method=getList" parameter="page"  formName="Search_Temperature_Form" />
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