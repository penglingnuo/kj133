
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="日志管理" %>
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
    <title>log_manager.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script language="javascript" src="js/page.js"></script>
    <script language="JavaScript" src="js/calendar.js"></script>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/CardIdORName.js"></script>
     <SCRIPT language="JavaScript">
       function check()
         {
	         var stime=document.all['ser_logmanager.stime'].value;
	         var etime=document.all['ser_logmanager.etime'].value;
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
			  window.parent.location.href="log_manager.do?method=doOpenExcel";
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
    <OBJECT id= "WebBrowser" height= "0" width= "0" classid= "CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"  VIEWASTEXT> 
	</OBJECT>
	<script type="text/javascript">
		function   func_print()//直接打印 
		{ 
		document.all.WebBrowser.ExecWB(6,6); 
		} 
	
		function   func_show()//预览打印 
		{ 
		document.all.WebBrowser.ExecWB(7,1); 
		} 
	</script>
    --%><%--
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
     
      <html:form action="log_manager?method=getList" onsubmit="return check()" >
          <table width="750">
              <tr>
                 <th>起始日期:</th>
                 <th align="left">
                 <html:text property="ser_logmanager.stime" size="9" styleId="file1" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_logmanager.stime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name1" onclick="this.checked?file1.disabled=false:file1.disabled=true" />&nbsp;
                 </th>
                 
                <th align="left">起始时间:</th>
                 
                 <th align="left">
                 <html:text property="ser_logmanager.minstime" size="8" styleId="file2" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_logmanager.minstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name2" onclick="this.checked?file2.disabled=false:file2.disabled=true" />&nbsp;
                 </th>
                 <th align="right">用户号:</th>
                 <th align="left">
                 <div style="position:relative;">
                 <span style="margin-left:100px;width:18px;overflow:hidden;">
                 <select  style="width:118px;margin-left:-100px" onchange="this.parentNode.nextSibling.value=this.value">
                 <logic:present name="userid_list">
              		<option value=""></option>
                 	<logic:iterate id="aa" name="userid_list">
                 		<option value='<bean:write name="aa" property="userid"/>'><bean:write name="aa" property="userid"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
                 <html:text property="ser_logmanager.userid" style="width:100px;position:absolute;left:0px;"></html:text>
				</div>
                 </th>
                
                 <%--<th>&nbsp;&nbsp;</th>
                 <th>工  种：</th>
                 <th align="left">
                    <html:select property="ser_areacheckstat.type" style="width:95px">
                         <html:option value="" ></html:option>
                         <html:options collection="workType_list" property="wordvalue" labelProperty="wordvalue"/>
                    </html:select>
                 </th>
                --%><th>&nbsp;&nbsp;</th>
                <th><html:submit>查 询</html:submit></th>
                <th>&nbsp;</th>
                <th><a href="javascript:if(confirm('确实要清除此时间段的查询结果吗?'))location='log_manager.do?method=delete'"><IMG src="Image/delete.BMP" border="0" align="absmiddle"/></a></th> 
              </tr>
              <tr>
                 <th>截止日期:</th>
                 <th align="left">
                 <html:text property="ser_logmanager.etime" size="9" styleId="file3" disabled="true" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',el:$dp.$('ser_logmanager.etime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name3" onclick="this.checked?file3.disabled=false:file3.disabled=true" />&nbsp;
                 </th>
                 
                <th align="left">截止时间:</th>
                 
                 <th align="left">
                 <html:text property="ser_logmanager.maxstime" size="8" styleId="file4" disabled="true" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'HH:mm:ss',el:$dp.$('ser_logmanager.maxstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name4" onclick="this.checked?file4.disabled=false:file4.disabled=true" />&nbsp;
                 </th>
                 <%--<th><html:text property="ser_logmanager.etime" size="18" onfocus="show_cele_date('','',Search_LogManager_Form['ser_logmanager.etime'])" /></th>
                 <th>&nbsp;&nbsp;</th>
                 --%><th align="right">姓 名:</th>
                 <th align="left">
                     <html:text property="ser_logmanager.name" size="15"  onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')"  />
                            
                 </th> 
                 <th>&nbsp;&nbsp;</th>
                 <th>

					<logic:equal name="listCount" value="0">
						<input type="button" value="打印预览" disabled>
					</logic:equal>
					<logic:notEqual name="listCount" value="0">
						<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
						<input type="button" value="打印预览"
							onclick="javascript:window.open('log_managerPrint.jsp')">

						<%--<input type="button"  value="打印预览"  onclick="javascript:window.location.href='area_check_statPrint.jsp'">--%>
					</logic:notEqual>
				</th>
                 
                 <th>&nbsp;</th>
                <th>
                <logic:equal name="listCount" value="0">
						<input type="button" value="导出excel" disabled>
					</logic:equal>
					<logic:notEqual name="listCount" value="0">
						<input type="button" value="导出excel"
							onclick="OpenExcel()">

					</logic:notEqual>
				</th>
                 <%--<th>&nbsp;&nbsp;</th>
                 <th>区 域：</th>
	            <th>
					<html:select property="ser_areacheckstat.areaid" style="width:95px">&nbsp;&nbsp;&nbsp;
		                <html:option value=""></html:option>
						<html:options collection="areaid_list" property="areaid"
							labelProperty="areaid" />
					</html:select>
				</th>
                 
                 --%><%--<th>班  次：</th>
                 <th>
                    <html:select property="ser_shuaka.banci" style="width:95px">
                        <html:option value="" ></html:option>
                        <html:options collection="ban_list" property="banname" labelProperty="banname"/>
                    </html:select>
                 </th>
                 --%><%--<th>&nbsp;&nbsp;</th>
                 <th><input type="button" value="打 印"   onclick="func_show()" /></th>
                 --%><%--<th  align="left">
                   
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0">  
	                     <input type="button"  value="打印预览"  onclick="javascript:window.location.href='downwell.do?method=viewPrint'">
	               </logic:notEqual>
                 </th>--%>
              </tr>   
          </table><br/> 
          <%--<div   style="overflow:auto;width:750;height:100%;"> 
          --%>
          <div id = "printdiv">
          <table  width="800" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" style="word-break:break-all">
               <tr>
                   <td width="60"  align="left" bgcolor="#B0C4DE" >记录号</td>
                   <td width="150"  align="left" bgcolor="#B0C4DE" >时间</td>
                   <td width="60"  align="left" bgcolor="#B0C4DE" >用户号</td>
                   <td width="70"  align="left" bgcolor="#B0C4DE" >用户名</td>
                   <td width="100"  align="left" bgcolor="#B0C4DE" >操作</td>
                   <td width="150"  align="left" bgcolor="#B0C4DE" >详细情况</td>  
               
               </tr>
               <logic:present name="logmanager_list" scope="request">
                 <logic:iterate name="logmanager_list" id="dw">
                     <tr>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="recordid" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="mydate" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="userid" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="name" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="myaction" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="loginfo" /></td>
                                          
                    </tr>  
                 </logic:iterate>
               </logic:present>
               <logic:present name="pagination" >
                  <tr>
					   <td colspan="6" align="left" bgcolor="#E6E6FA">
					        <page:pagination path="log_manager.do?method=getList" parameter="page" formName="Search_LogManager_Form" />
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
	}
</script>