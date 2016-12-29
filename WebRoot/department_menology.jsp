
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="部门考勤月报" %>  
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>department_menology.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script src="js/Calendar2.js"></script><!-- 不带分秒-->
    <link rel="stylesheet" href="js/jqueryui/cupertino/jquery-ui.css"
	type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery-ui-min.js"></script>
    <script type="text/javascript" src="js/showbackupaner.js"></script>
    <script language="javascript" src="js/page.js"></script>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"></script>
    <script language="javascript" src="js/export_xls.js"></script>
	<link href="Css/calendar-blue.css" rel="stylesheet">
	<SCRIPT language="JavaScript">
	   function check(){
	     var stime=document.all['department_menology.stime'].value;
	     var etime=document.all['department_menology.etime'].value;
	     if(stime >=etime ){
	       alert('起始日期不能大于或等于截止日期');
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
		}

	$(function(){
	
	//用户名模糊查询	    
   	 	$("#name").autocomplete({
    	source: function(request, response) {
                $.ajax({
                    url: "common.do?method=staffer",
                    dataType: "json",
                    data: {
                    	c:$("#name").val()
                    },
                    success: function(data) {
                       var names=[];
                       $(data.staffer).each(function(i, n){
                       names.push(this.cardId+"--"+this.name);
					   });
					
						response(names);
                    }
                });
            }
	});
	
	});
	</SCRIPT> 
  </head>
  
  <body bgColor="white" background="Image/right.gif" >
  <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
      <html:form action="/DepartmentMenology?method=getList" focus="department_menology.cardid"
       onsubmit="return check()">
<%--        <table width="465">--%>
        <table width="100%">
            <tr>
            
            	<th>
					员&nbsp;工：
				</th>
				<th>
					<html:text property="department_menology.cardid" styleId="name" style="width: 125px;">
					</html:text>
				</th>
				<th>
					&nbsp;
				</th>
            
            	<th align="left">部&nbsp;门:</th>
                 <th align="left">
                 <div style="position:relative;">
                 <span style="margin-left:100px;width:18px;overflow:hidden;">
                 <select  style="width:118px;margin-left:-100px;height: 21px;" name="department_menology.dep">
                 <logic:present name="dep_list">
              		<option value=""></option>
                 	<logic:iterate id="aa" name="dep_list">
                 		<option value='<bean:write name="aa" property="wordvalue"/>'><bean:write name="aa" property="wordvalue"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
				</div>
                 </th>               

               <!--  <th>&nbsp;</th>
            
            	 <th align="left">班&nbsp; 组:</th>
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
                 <html:text property="department_menology.gro" style="width:100px;position:absolute;left:0px;"></html:text>
				</div>
                 </th>   -->            

               <th>&nbsp;</th>
            
               <th>选择年月:</th>
               <th><html:text property="department_menology.stime" size="6" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM',el:$dp.$('department_menology.stime')})"/></th>
                 <%--<th>
                     <html:text property="department_menology.stime"  size="6" onfocus="this.blur()" />
                     <A onclick="return showCalendar('department_menology.stime', 'y-mm');"   href="#"><img src="Image/Button.gif"  id="IMG2"  border="0"/></A>
                 </th>
               --%><th>&nbsp;</th>
                
               <th><html:submit>查询</html:submit></th>
               <th>&nbsp;</th>
               <th>
                  <logic:equal  name="listCount" value="0">
                     <input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	              </logic:equal>
	              <logic:notEqual name="listCount" value="0">  
	              	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	              </logic:notEqual>
               </th>
               <th>&nbsp;</th>
               <th>
					<logic:equal name="listCount" value="0">
						<input type="button" value="导出excel" disabled>
					</logic:equal>
					<logic:notEqual name="listCount" value="0">
						<input type="button" value="导出excel" onclick="ExportXls('DepartmentMenology.do?method=doOpenExcel')">

					</logic:notEqual>
				</th>
              
            </tr>
        </table><br/>
        <div id = "printdiv">
       <table cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" width="100%" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;" >
  		  <tr>
  		     <td width="10%" align="left" bgcolor="#B0C4DE">姓名</td>
             <td width="10%" align="left" bgcolor="#B0C4DE">卡号</td>
             <td width="10%" align="left" bgcolor="#B0C4DE">部门</td>
             <td width="8%" align="left" bgcolor="#B0C4DE">班制</td>
             <td width="8%" align="left" bgcolor="#B0C4DE">早班</td>
             <td width="8%" align="left" bgcolor="#B0C4DE">中班</td>
             <td width="8%" align="left" bgcolor="#B0C4DE">晚班</td>
             <td width="10%" align="left" bgcolor="#B0C4DE">实井数</td>
             <td width="10%" align="left" bgcolor="#B0C4DE">虚井数</td>
             <td width="8%" align="left" bgcolor="#B0C4DE">总数</td>
             <td width="10%" align="left" bgcolor="#B0C4DE">总时长</td>
          </tr>
          <logic:present name="men">
             <logic:iterate name="men" id="list">
                 <tr onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="name" /> </td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="cardid" /> </td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="department" /> </td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="bantypename" /> </td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="zao" /> </td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="zhong" /> </td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="wan" /> </td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="overtimecountsg" /> </td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="overtimecountxg" /> </td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="countall" /> </td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="workalltime" /> </td>
                 </tr>
             </logic:iterate>
          </logic:present>
          <logic:present name="pagination" >
               <TR>
		           <TD colspan="11" align="center" bgcolor="#E6E6FA">
		                  <page:pagination path="DepartmentMenology.do?method=getList" parameter="page"  formName="Search_Department_menology_Form" />
		           </TD>  
              </TR> 
            </logic:present>  
       </table></div>
     </html:form>      
  </body>
</html:html>
