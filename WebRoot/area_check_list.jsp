
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="区域考勤明细" %>
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
    
    <title>area_check_list.jsp</title>

    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
    	
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script language="javascript" src="js/page.js"></script>
    <script language="JavaScript" src="js/calendar.js"></script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/CardIdORName.js"></script>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
        
     <SCRIPT language="JavaScript">
       function check()
         {       		
	         var stime=document.all['ser_areachecklist.stime'].value;
	         var etime=document.all['ser_areachecklist.etime'].value;
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
    
  --%></head>
  
  <body bgColor="white" background="Image/right.gif">
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     
      <html:form action="area_check_list?method=getList" onsubmit="return check()" >
          <table width="880">
              <tr>
                 <th align="left">进入区域时间:</th>
                 <%--<th align="left"><html:text property="ser_areachecklist.stime"  size="18" onfocus="show_cele_date('','',Search_AreaCheckList_Form['ser_areachecklist.stime'])" />&nbsp;</th>
                 --%>
                 <th align="left">
                 <html:text property="ser_areachecklist.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_areachecklist.stime')})"/>&nbsp;
                 </th>
                 <th align="left">工作时间大于:</th>
                 
                 <th align="left">
                 <html:text property="ser_areachecklist.minstime" size="8" styleId="file1" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_areachecklist.minstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name1" onclick="this.checked?file1.disabled=false:file1.disabled=true" />&nbsp;
                 </th>
                 <th align="left">班 组:</th>
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
                 <html:text property="ser_areachecklist.gro" style="width:100px;position:absolute;left:0px;"></html:text>
				</div>
                 </th>
                 
                 <th align="left">工 种:</th>
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
                 <html:text property="ser_areachecklist.type" style="width:82px;position:absolute;left:0px;"></html:text>
				</div> 
                 </th>
               
                <th><html:submit>查 询</html:submit></th>
                 
              </tr>
              <tr>
                 <th align="left">离开区域时间:</th>
                 <%--<th align="left"><html:text property="ser_areachecklist.etime" size="18" onfocus="show_cele_date('','',Search_AreaCheckList_Form['ser_areachecklist.etime'])" />
                 &nbsp;
                 </th> --%>
                 <th align="left">
                 <html:text property="ser_areachecklist.etime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_areachecklist.etime')})"/>&nbsp;
                 </th>
                 <th align="left">工作时间小于:</th>
                 
                 <th align="left">
                 <html:text property="ser_areachecklist.maxstime" size="8" styleId="file2" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_areachecklist.maxstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name2"  onclick="this.checked?file2.disabled=false:file2.disabled=true" />&nbsp;
                 </th>
                 <th align="left">员 工:</th>
                 <th align="left">
                     <html:text property="ser_areachecklist.sid" size="15" styleId="cardid" onkeyup="cardNames();"  />
                      <div style="position:absolute;" id="cardpopup">
                        <table id="cardname_table" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
                              <tbody id="cardname_table_body"></tbody>
                        </table>  
                    </div>
                    &nbsp;
                 </th> 
                 
                 <th align="left">区 域:</th>
	            <th align="left">
					<html:select property="ser_areachecklist.areaid" style="width:100px">&nbsp;&nbsp;&nbsp;
		                <html:option value=""></html:option>
						<html:options collection="areaid_list" property="areaid"
							labelProperty="id_name" />
					</html:select>
					&nbsp;
				</th>
				
                
                 <%--<th>班  次：</th>
                 <th>
                    <html:select property="ser_shuaka.banci" style="width:95px">
                        <html:option value="" ></html:option>
                        <html:options collection="ban_list" property="banname" labelProperty="banname"/>
                    </html:select>
                 </th>
                 --%>
                 <th>
                   
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0">  
	                 	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                     <input type="button"  value="打印预览"  onclick="javascript:window.open('qykqmx.jsp')">
	               </logic:notEqual>
                 </th>
              </tr>   
          </table><br/> 
          
<div id = "printdiv">
          <table  width="1280" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
               <tr>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >区域编号</td>  
                   <td width="80"  align="left" bgcolor="#B0C4DE" >区域名称</td>  
                   <td width="50"  align="left" bgcolor="#B0C4DE" >卡号</td>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >员工编号</td>
                   <td width="60"  align="left" bgcolor="#B0C4DE" >姓名</td>
                   <td width="70"  align="left" bgcolor="#B0C4DE" >工种</td>
                   <td width="80"  align="left" bgcolor="#B0C4DE" >班组</td>
                   <%--<td width="71"  align="left" bgcolor="#B0C4DE" >班次</td>
                   --%>
                   <td width="150" align="left" bgcolor="#B0C4DE" >进入区域时间</td>
                   <td width="60" align="left" bgcolor="#B0C4DE" >进入方向</td>
                   <td width="150" align="left" bgcolor="#B0C4DE" >离开区域时间</td>
                   <td width="60" align="left" bgcolor="#B0C4DE" >离开方向</td>
                   <td width="100" align="left" bgcolor="#B0C4DE" >区域停留时间</td>
                   <td width="140" align="left" bgcolor="#B0C4DE" >入井时间</td>
                   <td width="140"  align="left" bgcolor="#B0C4DE" >升井时间</td>
                   
                  
               </tr>
               <logic:present name="areachecklist_list" scope="request">
                 <logic:iterate name="areachecklist_list" id="dw">
                     <tr>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="areaid" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="areaname" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="cardid" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="stafferid" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="name" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="worktype" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="gro" /></td>
                       <%--<td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="banci" /></td>
                       --%><td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="starttime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="sshortname" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="endtime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="eshortname" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="setime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="downtime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="uptime" /></td>
                                          
                    </tr>  
                 </logic:iterate>
               </logic:present>
               <logic:present name="pagination" >
                  <tr>
					   <td colspan="14" align="left" bgcolor="#E6E6FA">
					        <page:pagination path="area_check_list.do?method=getList" parameter="page" formName="Search_AreaCheckList_Form" />
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