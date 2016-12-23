
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="明细记录查询"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>note.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
     <script language="javascript" src="js/page.js"></script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
    <script language="javascript" src="js/Cardreader.js"></script>
    <script language="JavaScript" src="js/Locator.js"></script>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
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
               var start=document.all['ser_note.stime'].value;
               var end=document.all['ser_note.etime'].value;
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
  
  <body bgColor="white" background="Image/right.gif" >
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
      <html:form action="/note?method=getList" onsubmit="return check()"  > 
         <table border="0" width="730">
          <tr >
            <th align="right">起始时间：</th>
            <th align="left">
                 <html:text property="ser_note.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_note.stime')})"/>
                 </th>
            <%--<th align="left"><html:text property="ser_note.stime" size="18" onfocus="show_cele_date('','',Search_Note_Form['ser_note.stime'])" />&nbsp;&nbsp;&nbsp;</th>
            --%><th >工种：</th>
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
                 <html:text property="ser_note.type" style="width:82px;position:absolute;left:0px;"></html:text>
				</div> 
                 </th>
            <%--<th align="left"><html:select property="ser_note.type" style="width:150px">
	                    <html:option value="" ></html:option>
	                    <html:options collection="workType_list" property="wordvalue" labelProperty="wordvalue"/>
	                 </html:select>&nbsp;&nbsp;&nbsp;
	        </th>
            --%><th>报警信息：&nbsp;&nbsp;&nbsp;</th>
            <th rowspan="3">
              <html:select property="ser_note.info" size="8" multiple="true">
		             <html:option value="1">处于呼叫状态</html:option>
		             <html:option value="2">卡电池报警</html:option>
		             <html:option value="4">通行报警</html:option>
		             <html:option value="8">呼救</html:option>
		             <html:option value="16">卡信号中断</html:option>
		             <html:option value="32">下井超时</html:option>
				     <html:option value="64">定位器电池报警</html:option>
				     <html:option value="128">定位中断</html:option>
	           </html:select>
            </th>
          </tr>
          
          <tr>
            <th align="right">截止时间：</th>
            <th align="left">
                 <html:text property="ser_note.etime" size="18" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_note.etime')})"/>
                 </th>
            <%--<th align="left"><html:text property="ser_note.etime" size="18" onfocus="show_cele_date('','',Search_Note_Form['ser_note.etime'])" /></th>
            --%><th>班组：</th>
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
                 <html:text property="ser_note.gro" style="width:100px;position:absolute;left:0px;"></html:text>
				</div>
                 </th>
            <%--<th align="left">
                <html:select property="ser_note.gro" style="width:150px" >
                    <html:option value="" ></html:option>
                    <html:options collection="gro_list" property="wordvalue" labelProperty="wordvalue"/>
                </html:select> 
           </th>
            --%><th align="left"><html:radio property="ser_note.rad" value="radand" />状态与</th>
          </tr>
          
          <tr>
            <th colspan="4" align="left">
	               分站：<html:text property="ser_note.cid" size="6"  maxlength="4" styleId="names3" onkeyup="findNames3();"   />&nbsp;
	                  <div style="position:absolute;" id="popup3">
					        <table id="name_table3" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
					            <tbody id="name_table_body3"></tbody>
					        </table>  
                       </div>  
	               
	               定位器：<html:text property="ser_note.lid" size="6"  maxlength="4"  styleId="names4" onkeyup="findNames4();"  />&nbsp;
	                     <div style="position:absolute;" id="popup4">
					        <table id="name_table4" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
					            <tbody id="name_table_body4"></tbody>
					        </table>  
                        </div>  
	               员工：<html:text property="ser_note.sid" size="6" maxlength="6" styleId="names2" onkeyup="findNames2();" />&nbsp;
	                 <div style="position:absolute;" id="popup2">
				        <table id="name_table2" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="name_table_body2"></tbody>
				        </table>  
                      </div>
	               
	             <html:submit>查 询</html:submit>&nbsp;
	             <logic:equal  name="listCount" value="0">
                      <input type="button"  value="打印预览"  disabled>
	             </logic:equal>
	             <logic:notEqual name="listCount" value="0">  
	             	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                  <input type="button"  value="打印预览"  onclick="javascript:window.open('notePrint.jsp')">
	             </logic:notEqual>
             </th>
            <th align="left"><html:radio property="ser_note.rad" value="randor" />状态或</th>
          </tr>
        </table><br/>
    <%--<div   style="overflow:auto;width:810;height:500;">        
     --%>
     <div id = "printdiv">
     <table  width="1460"  cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" >
       <tr>
         <td width="48"   align="left" bgcolor="#B0C4DE">卡号</td>
         <td width="52"   align="left" bgcolor="#B0C4DE">姓名</td>
         <td width="100"   align="left" bgcolor="#B0C4DE">工种</td>
         <td width="85"   align="left" bgcolor="#B0C4DE">班组</td>
         <td width="60"   align="left" bgcolor="#B0C4DE">分站号</td>
         <td width="113"  align="left" bgcolor="#B0C4DE">分站名称</td>
         <td width="69"   align="left" bgcolor="#B0C4DE">定位器号</td>
         <td width="69"   align="left" bgcolor="#B0C4DE">定位器名</td>
         <td width="150"  align="left" bgcolor="#B0C4DE">状态信息</td>
         <td width="160"  align="left" bgcolor="#B0C4DE">起始时间</td>
         <td width="160"  align="left" bgcolor="#B0C4DE">截止时间</td>
         <td width="90"  align="left" bgcolor="#B0C4DE">保持时间</td>
         <td width="265"  align="left" bgcolor="#B0C4DE">结束原因</td>
         <td width="54"   align="left" bgcolor="#B0C4DE">地图号</td>
      </tr> 
      <logic:present name="Note_List">
        <logic:iterate name="Note_List" id="note">
           <tr >
               <td  align="left" bgcolor="#E6E6FA"><bean:write name="note" property="cid" /></td>
               <td  align="left" bgcolor="#E6E6FA"><bean:write name="note" property="name" /></td>
               <td  align="left" bgcolor="#E6E6FA"><bean:write name="note" property="type" /></td>
               <td  align="left" bgcolor="#E6E6FA"><bean:write name="note" property="gro" /></td>  
               <td  align="left" bgcolor="#E6E6FA"><bean:write name="note" property="rid" /></td>
               <td  align="left" bgcolor="#E6E6FA"><bean:write name="note" property="sname" /></td>
               <td  align="left" bgcolor="#E6E6FA"><bean:write name="note" property="lid" /></td>  
               <td  align="left" bgcolor="#E6E6FA"><bean:write name="note" property="lname" /></td>  
               <td  align="left" bgcolor="#E6E6FA"><bean:write name="note" property="info" /></td>
               <td  align="left" bgcolor="#E6E6FA"><bean:write name="note" property="stime" /></td>
               <td  align="left" bgcolor="#E6E6FA"><bean:write name="note" property="etime" /></td>
               <td  align="left" bgcolor="#E6E6FA"><bean:write name="note" property="stay" /></td> 
               <td  align="left" bgcolor="#E6E6FA"><bean:write name="note" property="overinfo" /></td>
               <td  align="left" bgcolor="#E6E6FA"><bean:write name="note" property="mid" /></td>  
           </tr>
        </logic:iterate>
      </logic:present>
      		 <logic:present name="pagination"  >
		         <tr >
					<td colspan="18" align="left" bgcolor="#E6E6FA"> 
					   <page:pagination path="note.do?method=getList" name="pagination" parameter="page"  formName="Search_Note_Form"/>
				     </td>  
		          </tr> 
          	 </logic:present> 
        </table></div>
        
      </html:form>
  </body>
    
</html:html>
