
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="井下考勤明细" %>
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
String name5=(String)request.getAttribute("name5");
String name6=(String)request.getAttribute("name6");
String name7=(String)request.getAttribute("name7");
String name8=(String)request.getAttribute("name8");
String name9=(String)request.getAttribute("name9");
String name10=(String)request.getAttribute("name10");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>downWell.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script language="javascript" src="js/page.js"></script>
    <script language="JavaScript" src="js/calendar.js"></script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/CardIdORName.js"></script>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"></script>
     <SCRIPT language="JavaScript">
       function check()
         {
	         var stime=document.all['ser_shuaka.stime'].value;
	         var etime=document.all['ser_shuaka.etime'].value;
	         var ksdate=document.all['ser_shuaka.ksdate'].value;
	         var kedate=document.all['ser_shuaka.kedate'].value;
	         var name9 = document.getElementById("name9").checked;
	         var name10 = document.getElementById("name10").checked;
	         var name5 = document.getElementById("name5").checked;
	         var name6 = document.getElementById("name6").checked;
	         if(name9 && name10){
	         if( stime > etime){
	            alert('入井开始日期不能大于入井结束日期');
	            return false;
         	}
           return true;
           }
           if(name5 && name6){
           if( ksdate > kedate){
	            alert('考勤开始日期不能大于考勤结束日期');
	            return false;
         	}
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
    
  --%></head>
  
  <body bgColor="white" background="Image/right.gif" >
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     
      <html:form action="downwell?method=getList" onsubmit="return check()" >
          <table width="1000">
              <tr>
                 <th align="right">班 组:</th>
                 <th align="left">
                 <div style="position:relative;">
                 <span style="margin-left:82px;width:18px;overflow:hidden;">
                 <select  style="width:118px;margin-left:-100px"  onchange="this.parentNode.nextSibling.value=this.value">
                 <logic:present name="gro_list">
              		<option value=""></option>
                 	<logic:iterate id="aa" name="gro_list">
                 		<option value='<bean:write name="aa" property="wordvalue"/>'><bean:write name="aa" property="wordvalue"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
                 <html:text property="ser_shuaka.gro" style="width:82px;position:absolute;left:0px;"></html:text>
				</div>
                 </th>
                 <th align="right">入井开始日期:</th>
                 <th><html:text property="ser_shuaka.stime" size="9" styleId="file9" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_shuaka.stime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name9" onclick="this.checked?file9.disabled=false:file9.disabled=true" />
                 </th>
                 <th align="right">入井开始时间:</th>
                 
                 <th align="left">
                 <html:text property="ser_shuaka.dstime" size="9" styleId="file1" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_shuaka.dstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name1" onclick="this.checked?file1.disabled=false:file1.disabled=true" />
                 </th>
                 <th align="left">升井开始时间:</th>
                 
                 <th align="left">
                 <html:text property="ser_shuaka.ustime" size="9" styleId="file2" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_shuaka.ustime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name2" onclick="this.checked?file2.disabled=false:file2.disabled=true" />
                 </th>
                 <th align="right">工作时间大于:</th>
                 <th align="left">
                 <html:text property="ser_shuaka.mintime" size="8" styleId="file7" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_shuaka.mintime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name7" onclick="this.checked?file7.disabled=false:file7.disabled=true" />
                 </th>
                 
                
              </tr>
              <tr>
              <th align="right">工 种:</th>
                 <th align="left">
                 <div style="position:relative;">
                 <span style="margin-left:82px;width:18px;overflow:hidden;">
                 <select  style="width:118px;margin-left:-100px" onchange="this.parentNode.nextSibling.value=this.value">
                 <logic:present name="workType_list">
              		<option value=""></option>
                 	<logic:iterate id="aa" name="workType_list">
                 		<option value='<bean:write name="aa" property="wordvalue"/>'><bean:write name="aa" property="wordvalue"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
                 <html:text property="ser_shuaka.type" style="width:82px;position:absolute;left:0px;"></html:text>
				</div> 
                 </th>
                 <th align="right">入井结束日期:</th>
                 <th><html:text property="ser_shuaka.etime" size="9" styleId="file10" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_shuaka.etime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name10" onclick="this.checked?file10.disabled=false:file10.disabled=true" />
                 </th>
                 <th align="right">入井结束时间:</th>
                 
                 <th align="left">
                 <html:text property="ser_shuaka.detime" size="9" styleId="file3" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_shuaka.detime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name3" onclick="this.checked?file3.disabled=false:file3.disabled=true" />
                 </th>
                 <th align="left">升井结束时间:</th>
                 
                 <th align="left">
                 <html:text property="ser_shuaka.uetime" size="9" styleId="file4" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_shuaka.uetime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name4" onclick="this.checked?file4.disabled=false:file4.disabled=true" />
                 </th>
                 <th align="right">工作时间小于:</th>
                 <th align="left">
                 <html:text property="ser_shuaka.maxtime" size="8" styleId="file8" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_shuaka.maxtime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name8" onclick="this.checked?file8.disabled=false:file8.disabled=true" />
                 </th>
                 
                 
              </tr>
            <tr>
                
                 <th align="right">员 工:</th>
                 <th align="left">
                     <html:text property="ser_shuaka.sid" style="width:100px" size="16" maxlength="6" styleId="cardid" onkeyup="cardNames();"  />
                      <div style="position:absolute;" id="cardpopup">
                        <table id="cardname_table" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
                              <tbody id="cardname_table_body"></tbody>
                        </table>  
                    </div>
                 </th>
                 <th align="right">考勤开始日期:</th>
                 <th><html:text property="ser_shuaka.ksdate" size="9" styleId="file6" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_shuaka.ksdate')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name6" onclick="this.checked?file6.disabled=false:file6.disabled=true" />
                 </th>
                 <th align="right">考勤结束日期:</th>
                 
                 <th align="left">
                 <html:text property="ser_shuaka.kedate" size="9" styleId="file5" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_shuaka.kedate')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name5" onclick="this.checked?file5.disabled=false:file5.disabled=true" />
                 </th>
                 <th align="left"><html:submit>查 询</html:submit></th>
                
                 <th  align="left">
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0">  
	                 	 <input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                     <input type="button"  value="打印预览"  onclick="javascript:window.open('downWellPrint.jsp')">
	               </logic:notEqual>
                 </th>
                 
                 
                 
              </tr>   
          </table><br/> 
          <div id = "printdiv">
          <table  width="1642" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
               <tr>
                   <td width="80"  align="left" bgcolor="#B0C4DE" >考勤日期</td>  
                   <td width="56"  align="left" bgcolor="#B0C4DE" >卡号</td>
                   <td width="66"  align="left" bgcolor="#B0C4DE" >员工编号</td>
                   <td width="62"  align="left" bgcolor="#B0C4DE" >姓名</td>
                   <td width="80"  align="left" bgcolor="#B0C4DE" >班组</td>
                   <td width="71"  align="left" bgcolor="#B0C4DE" >工种</td>
                   <%--<td width="71"  align="left" bgcolor="#B0C4DE" >班次</td>
                   --%><td width="150" align="left" bgcolor="#B0C4DE" >入井时间</td>
                   <td width="146" align="left" bgcolor="#B0C4DE" >升井时间</td>
                   <td width="140" align="left" bgcolor="#B0C4DE" >工作时间</td>
                   <td width="80"  align="left" bgcolor="#B0C4DE" >入井分站</td>
                   <td width="160" align="left" bgcolor="#B0C4DE" >入井分站名称</td>
                   <td width="80"  align="left" bgcolor="#B0C4DE" >入井定位器</td>
                   <td width="160" align="left" bgcolor="#B0C4DE" >入井定位器名称</td>
                   <td width="80"  align="left" bgcolor="#B0C4DE" >升井分站</td>
                   <td width="160" align="left" bgcolor="#B0C4DE" >升井分站名称</td>
                   <td width="80"  align="left" bgcolor="#B0C4DE" >升井定位器</td>
                   <td width="160" align="left" bgcolor="#B0C4DE" >升井定位器名称</td>
                  
               </tr>
               <logic:present name="downWell_list" scope="request">
                 <logic:iterate name="downWell_list" id="dw">
                     <tr>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="kqtime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="cardid" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="stafferid" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="username" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="gro" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="worktype" /></td>
                       <%--<td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="banci" /></td>
                       --%><td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="intime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="upwelltime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="worktime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="incardreader" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="incardreadername" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="upcardreader" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="upcardreadername" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="inlocator" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="inlocatorname" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="uplocator" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="uplocatorname" /></td>                    
                    </tr>  
                 </logic:iterate>
               </logic:present>
               <logic:present name="pagination" >
                  <tr>
					   <td colspan="18" align="left" bgcolor="#E6E6FA">
					        <page:pagination path="downwell.do?method=getList" parameter="page" formName="Search_ShuaKa_Form" />
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
	}if("<%=name3%>"=="true"){
	   	document.getElementById("name3").checked=true;
	   	document.getElementById("file3").disabled=false;
	}if("<%=name4%>"=="true"){
	   	document.getElementById("name4").checked=true;
	   	document.getElementById("file4").disabled=false;
	}if("<%=name5%>"=="true"){
	   	document.getElementById("name5").checked=true;
	   	document.getElementById("file5").disabled=false;
	}if("<%=name6%>"=="true"){
	   	document.getElementById("name6").checked=true;
	   	document.getElementById("file6").disabled=false;
	}if("<%=name7%>"=="true"){
	   	document.getElementById("name7").checked=true;
	   	document.getElementById("file7").disabled=false;
	}if("<%=name8%>"=="true"){
	   	document.getElementById("name8").checked=true;
	   	document.getElementById("file8").disabled=false;
	}if("<%=name9%>"=="true"){
	   	document.getElementById("name9").checked=true;
	   	document.getElementById("file9").disabled=false;
	}if("<%=name10%>"=="true"){
	   	document.getElementById("name10").checked=true;
	   	document.getElementById("file10").disabled=false;
	}
</script>