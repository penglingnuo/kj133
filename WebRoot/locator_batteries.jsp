
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="定位器电池报警" %>
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
    
    <title>locator_batteries.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
    <script language="javascript" src="js/page.js"></script><!--分页 -->
    <script language="JavaScript" src="js/Locator.js"></script>
    <script language="javascript" type="text/javascript" src="js/jquery.js"></script>
     <script language="javascript" type="text/javascript" src="js/export_xls.js"></script>
     
    <SCRIPT language="javascript">
        function check()
          {
               var start=document.all['ser_locator_batteries.stime'].value;
               var end=document.all['ser_locator_batteries.etime'].value;
                  if(start>end){
	                 alert('起始时间不能大于结束时间，请重新输入');
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
		   // WebBrowser1.ExecWB(7, 1); //预览
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
     <html:form action="/locator_batteries?method=getList" onsubmit="return  check()">
     <table width="60%">
			<tr>
				<th>
					起始日期:
				</th>
				<th align="left">
                 <html:text property="ser_locator_batteries.stime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_locator_batteries.stime')})"/>&nbsp;
                 </th>
				<th>
					&nbsp;&nbsp;
				</th>
				<th align="left">起始时间:</th>
                 
                 <th align="left">
                 <html:text property="ser_locator_batteries.minstime" size="8" styleId="file1" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_locator_batteries.minstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name1" onclick="this.checked?file1.disabled=false:file1.disabled=true" />&nbsp;
                 </th>
				<th>
					&nbsp;&nbsp;
				</th>
				
				<th>定位器：</th>
                <th>
                     <html:text property="ser_locator_batteries.lid" size="10" maxlength="4"  styleId="names4" onkeyup="findNames4();"  />
                     <div style="position:absolute;" id="popup4">
					        <table id="name_table4" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
					            <tbody id="name_table_body4"></tbody>
					        </table>  
                     </div>
                </th>
			</tr>
			<tr>
				<th>
					截止日期:
				</th>
				<th align="left"><html:text property="ser_locator_batteries.etime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_locator_batteries.etime')})"/>&nbsp;</th>
				<th>
					&nbsp;&nbsp;
				</th>
                 <th align="left">截止时间:</th>
                 
                 <th align="left">
                 <html:text property="ser_locator_batteries.maxstime" size="8" styleId="file2" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_locator_batteries.maxstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name2"  onclick="this.checked?file2.disabled=false:file2.disabled=true" />&nbsp;
                 </th>
				<th>
					&nbsp;&nbsp;
				</th>
				
				<th>
					<html:submit>查 询</html:submit>&nbsp;
				</th>
				
				
                 <th>
                   
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印"  disabled>
                         <input type="button"  value="导出excel"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0">  
	                 	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                     <!--  <input type="button"  value="打印预览"  onclick="javascript:window.open('locator_batteriesPrint.jsp')">-->
	                     <input type="button"  value="导出excel" onclick="ExportXls('locator_batteries.do?method=export')">
	               </logic:notEqual>
                 </th>
              
			</tr>
		</table>
     <%--
         <table>
             <tr>
                <th>起始时间：</th>
                <th><html:text property="ser_locator_batteries.stime" size="18" onfocus="show_cele_date('','',Search_Locator_Batteries_Form['ser_locator_batteries.stime'])" /></th>
                <th>&nbsp;</th>
                <th>结束时间：</th>
                <th><html:text property="ser_locator_batteries.etime" size="18" onfocus="show_cele_date('','',Search_Locator_Batteries_Form['ser_locator_batteries.etime'])" /></th>
                <th>&nbsp;</th>
                <th>定位器：</th>
                <th>
                     <html:text property="ser_locator_batteries.lid" size="10" maxlength="4"  styleId="names4" onkeyup="findNames4();"  />
                     <div style="position:absolute;" id="popup4">
					        <table id="name_table4" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
					            <tbody id="name_table_body4"></tbody>
					        </table>  
                     </div>
                </th>
                <th>&nbsp;</th>
                <th><html:submit>查  询</html:submit></th>
                <th>&nbsp;</th>
                <th>
                    <logic:equal  name="listCount" value="0">
                       <input type="button"  value="打印预览"  disabled>
	                </logic:equal>
	                <logic:notEqual name="listCount" value="0">  
	                  <input type="button"  value="打印预览"  onclick="javascript:window.open('locator_batteriesPrint.jsp')">
	                </logic:notEqual>
                </th>
             </tr>
         </table>
         --%>
         
         <br>
         
      
        <div id = "printdiv">
          <table width="100%"cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
              <tr>
	              <td width="73"  align="left" bgcolor="#B0C4DE">定位器号</td>
	              <td width="200" align="left" bgcolor="#B0C4DE">定位器名</td>
	              <td width="160" align="left" bgcolor="#B0C4DE">报警信息</td>
	              <td width="170" align="left" bgcolor="#B0C4DE">开始报警时间</td>
	              <td width="170" align="left" bgcolor="#B0C4DE">最后报警时间</td>
             </tr>
            <logic:present name="Locator_batteries_List">
               <logic:iterate name="Locator_batteries_List" id="locaor_batt">
                   <tr>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="locaor_batt" property="locatorid"/></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="locaor_batt" property="shortname"/></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="locaor_batt" property="info"/></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="locaor_batt" property="stime"/></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="locaor_batt" property="etime"/></td>
                  </tr>
               </logic:iterate>
            </logic:present>
            <logic:present name="pagination" >
	             <tr>
					 <td colspan="5" align="center" bgcolor="#E6E6FA">
						 <page:pagination path="locator_batteries.do?method=getList" name="pagination" parameter="page"  formName="Search_Locator_Batteries_Form"/>
					 </td>  
	             </tr> 
            </logic:present> 
          </table>   </div>   
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