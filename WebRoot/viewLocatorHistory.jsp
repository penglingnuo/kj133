
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="定位器历史查询"%>
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
    <title>viewLocatorHistory.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
     <script language="javascript" src="js/page.js"></script>
     <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
     <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
     <script language="JavaScript" src="js/Locator.js"></script>
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
     <SCRIPT language="JavaScript">
           function check()
	       {
	         var stime=document.all['ser_viewrlocatorhistory.stime'].value;
	         var etime=document.all['ser_viewrlocatorhistory.etime'].value;
	         var name1 = document.getElementById("name1").checked;
             var name3 = document.getElementById("name3").checked;
             if(name1 && name3){
	         if( stime > etime){
	            alert('起始时间不能大于截止时间');
	            return false;
	         }
	         return true;
	       }
	       }
	       
	       function OpenExcel(){
			  window.parent.location.href="viewLocatorHistory.do?method=doOpenExcel";
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
      <html:form action="/viewLocatorHistory?method=getList" onsubmit="return check()">
          <table>
              <tr>
              <th align="left">注册日期大于:</th>
                 
                 <th align="left">
                 <html:text property="ser_viewrlocatorhistory.stime" size="9" styleId="file1" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_areachecklist.stime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name1" onclick="this.checked?file1.disabled=false:file1.disabled=true" />&nbsp;
                 
                 </th><th align="left">时间大于:</th>
                 <th align="left">
                 <html:text property="ser_viewrlocatorhistory.minstime" size="8" styleId="file2" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_viewrlocatorhistory.minstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name2" onclick="this.checked?file2.disabled=false:file2.disabled=true" />&nbsp;
                 </th>
                 <th>定位器：</th>
                 <th>
                     <html:text property="ser_viewrlocatorhistory.lid"  size="20"   styleId="names4" onkeyup="findNames4();"  /> 
                       <div style="position:absolute;" id="popup4">
					        <table id="name_table4" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
					            <tbody id="name_table_body4"></tbody>
					        </table>  
                        </div>
                 </th>
                 <th>&nbsp;</th>
                
              
              </tr>
              <tr>
              <th align="left">注册日期小于:</th>
                 
                 <th align="left">
                 <html:text property="ser_viewrlocatorhistory.etime" size="9" styleId="file3" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_viewrlocatorhistory.etime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name3" onclick="this.checked?file3.disabled=false:file3.disabled=true" />&nbsp;
                 
                 </th><th align="left">时间小于:</th>
                 <th align="left">
                 <html:text property="ser_viewrlocatorhistory.maxstime" size="8" styleId="file4" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_viewrlocatorhistory.maxstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name4" onclick="this.checked?file4.disabled=false:file4.disabled=true" />&nbsp;
                 </th>
                 
                 <th><html:submit>查 询</html:submit>&nbsp;</th>
                  <th   align="center">
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0">  
	                 	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                      <input type="button"  value="打印预览"  onclick="javascript:window.open('viewLocatorHistoryPrint.jsp')">
	                 </logic:notEqual>
	                 &nbsp;
                 </th>
              <th  align="center"> 
                <logic:equal name="listCount" value="0">
						<input type="button" value="导出excel" disabled>
					</logic:equal>
					<logic:notEqual name="listCount" value="0">
						<input type="button" value="导出excel" onclick="OpenExcel()">
					</logic:notEqual>
				</th>
              </tr>
          </table><br>
           <div id = "printdiv">
            <table   cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" >
                <tr>
                     <td width="140" align="left" bgcolor="#B0C4DE">有效起日</td>
		             <td width="140" align="left" bgcolor="#B0C4DE">有效止日</td> 
		             <td width="57"  align="left" bgcolor="#B0C4DE">定位器号</td>
		             <!--  <td width="100" align="left" bgcolor="#B0C4DE">定位器名</td>-->
		             <td width="120" align="left" bgcolor="#B0C4DE">简称</td>
		             <td width="69"  align="left" bgcolor="#B0C4DE">地图x坐标</td>
		             <td width="76"  align="left" bgcolor="#B0C4DE">地图y坐标</td>  
		             <td width="76"  align="left" bgcolor="#B0C4DE">地图z坐标</td>  
		             <td width="140" align="left" bgcolor="#B0C4DE">注册时间</td>
					 <td width="57"  align="left" bgcolor="#B0C4DE">地图号</td>
			    </tr>
              <logic:present name="viewLocatorHistoryList" scope="request">
                 <logic:iterate name="viewLocatorHistoryList" id="vlh">
                     <tr onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''" >
                         <td align="left" bgcolor="#E6E6FA"><bean:write name="vlh" property="loca_startdate"/></td>
                         <td align="left" bgcolor="#E6E6FA"><bean:write name="vlh" property="loca_modifydate"/></td>
                         <td align="left" bgcolor="#E6E6FA"><bean:write name="vlh" property="loca_locatorid"/></td>
                         <!-- <td align="left" bgcolor="#E6E6FA"><bean:write name="vlh" property="loca_lname"/></td>--> 
                         <td align="left" bgcolor="#E6E6FA"><bean:write name="vlh" property="loca_shortname"/></td>
                         <td align="left" bgcolor="#E6E6FA"><bean:write name="vlh" property="loca_x"/></td>
                         <td align="left" bgcolor="#E6E6FA"><bean:write name="vlh" property="loca_y"/></td> 
                         <td align="left" bgcolor="#E6E6FA"><bean:write name="vlh" property="loca_z"/></td> 
                         <td align="left" bgcolor="#E6E6FA"><bean:write name="vlh" property="loca_regdate"/></td>
                         <td align="left" bgcolor="#E6E6FA"><bean:write name="vlh" property="loca_mapid"/></td>
                     </tr>
                 </logic:iterate>
              </logic:present>
              <logic:present name="pagination" >
	               <tr>
			           <td colspan="9" align="center" bgcolor="#E6E6FA">
			                 <page:pagination path="viewLocatorHistory.do?method=getList" parameter="page" formName="Search_ViewLocatorHistory_Form" />
			           </td>  
	              </tr> 
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
	}if("<%=name3%>"=="true"){
	   	document.getElementById("name3").checked=true;
	   	document.getElementById("file3").disabled=false;
	}if("<%=name4%>"=="true"){
	   	document.getElementById("name4").checked=true;
	   	document.getElementById("file4").disabled=false;
	}
</script>