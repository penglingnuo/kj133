
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="区域停留信息" %>
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
    
    <title>areaStick.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script language="javascript" src="js/page.js"></script>
    <script language="JavaScript" src="js/calendar.js"></script><!--带分秒-->
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <script language="javascript" src="js/StafferORName.js"></script>
    <script language="javascript" src="js/Cardreader.js"></script>
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
               var start=document.all['ser_areastick.stime'].value;
               var end=document.all['ser_areastick.etime'].value;
	            if(start>end){
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
  
  <body bgColor="white" background="Image/right.gif" >
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
       
     <html:form action="areaStick?method=getList" onsubmit="return check()">
        <table width="800">
              <tr>
              
                   <th>起始日期：</th>
                   <th align="left">
                 <html:text property="ser_areastick.stime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_areastick.stime')})"/>&nbsp;
                 </th>
		           <%--<th><html:text property="ser_areastick.stime" size="18" onfocus="show_cele_date('','',Search_AreaStick_Form['ser_areastick.stime'])" /></th>
		           --%>
		           <th align="left">起始时间:</th>
                 
                 <th align="left">
                 <html:text property="ser_areastick.minstime" size="8" styleId="file1" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_areastick.minstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name1" onclick="this.checked?file1.disabled=false:file1.disabled=true" />&nbsp;
                 </th>
		           <th>&nbsp;</th>
		           <th>员工：</th>
		           <th>
		              <html:text property="ser_areastick.sid" size="17"  styleId="names2" onkeyup="findNames2();" />
		              <div style="position:absolute;" id="popup2">
				        <table id="name_table2" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="name_table_body2"></tbody>
				        </table>  
                      </div>
		           </th>
		           <th>&nbsp;</th>
		           <th>工种：</th>
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
                 <html:text property="ser_areastick.type" style="width:82px;position:absolute;left:0px;"></html:text>
				</div> 
                 </th>
		           <%--<th>
		               <html:select property="ser_areastick.type" style="width:120px">
	                        <html:option value="" ></html:option>
	                        <html:options collection="workType_list" property="wordvalue" labelProperty="wordvalue"/>
	                   </html:select>
		           </th>
		           --%><th><html:submit>查 询</html:submit></th>
              </tr>
             <tr>
                   <th>截止日期：</th>
                   <th align="left">
                 <html:text property="ser_areastick.etime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_areastick.etime')})"/>&nbsp;
                 </th>
		           <%--<th><html:text property="ser_areastick.etime" size="18"  onfocus="show_cele_date('','',Search_AreaStick_Form['ser_areastick.etime'])"/></th>
		           --%>
		           <th align="left">截止时间:</th>
                 
                 <th align="left">
                 <html:text property="ser_areastick.maxstime" size="8" styleId="file2" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_areastick.maxstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name2"  onclick="this.checked?file2.disabled=false:file2.disabled=true" />&nbsp;
                 </th>
		           <th>&nbsp;</th>
		           <th>分站：</th>
		           <th>
		                <html:text property="ser_areastick.cid" size="17"   styleId="names3" onkeyup="findNames3();" />
		                <div style="position:absolute;" id="popup3">
					        <table id="name_table3" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
					            <tbody id="name_table_body3"></tbody>
					        </table>  
                         </div>
		           </th>
		           <th>&nbsp;</th>
		           <th>班组：</th>
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
                 <html:text property="ser_areastick.gro" style="width:100px;position:absolute;left:0px;"></html:text>
				</div>
                 </th>
		           <%--<th>
		                <html:select property="ser_areastick.gro" style="width=120px">
		                   <html:option value="" ></html:option>
		                   <html:options collection="gro_list" property="wordvalue" labelProperty="wordvalue"/>
                        </html:select>
		           </th>
		           --%><th>
		                <logic:equal  name="listCount" value="0">
                             <input type="button"  value="打印预览"  disabled>
	                    </logic:equal>
	                    <logic:notEqual name="listCount" value="0">  
	                    	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                         <input type="button"  value="打印预览"  onclick="javascript:window.open('areaStickPrint.jsp')">
	                    </logic:notEqual>
		           </th>      
              </tr>
        </table><br/>
    
<div id = "printdiv">
    <TABLE width="1906" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" >
         <TR>
			   <TD width="48"  align="left" bgcolor="#B0C4DE">卡号</TD>
			   <TD width="56"  align="left" bgcolor="#B0C4DE">姓名</TD>
			   <TD width="93"  align="left" bgcolor="#B0C4DE">工种</TD> 
			   <TD width="85"  align="left" bgcolor="#B0C4DE">班组</TD>
			   <TD width="55"  align="left" bgcolor="#B0C4DE">分站号</TD>
			   <TD width="140" align="left" bgcolor="#B0C4DE">分站名称</TD> 
			   <TD width="170" align="left" bgcolor="#B0C4DE">进入分站时间</TD>
			   <TD width="173" align="left" bgcolor="#B0C4DE">离开分站时间</TD> 
			   <TD width="103" align="left" bgcolor="#B0C4DE">进入位置</TD>
			   <TD width="170" align="left" bgcolor="#B0C4DE">离开位置</TD>
			   <TD width="125" align="left" bgcolor="#B0C4DE">离开原因</TD>   
			   <TD width="100" align="left" bgcolor="#B0C4DE">停留时间</TD>
			   <TD width="113" align="left" bgcolor="#B0C4DE">中断次数</TD> 
			   <TD width="180" align="left" bgcolor="#B0C4DE">断线时间</TD>  
			    
		 </TR>
		 <logic:present name="AreaStick_List">
		    <logic:iterate name="AreaStick_List" id="stick">
		       <TR>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="stick" property="sid" /></TD>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="stick" property="username" /></TD>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="stick" property="type" /></TD>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="stick" property="gro" /></TD> 
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="stick" property="cid" /></TD>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="stick" property="shname" /></TD>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="stick" property="inttim" /></TD>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="stick" property="outcid" /></TD>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="stick" property="inloca" /></TD>  
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="stick" property="outloca" /></TD>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="stick" property="outrea" /></TD>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="stick" property="staty" /></TD>  
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="stick" property="times" /></TD>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="stick" property="resinti" /></TD> 
		           
		       </TR>
		    </logic:iterate>
		 </logic:present>
		   <logic:present name="pagination" >
		        <TR>
		            <TD colspan="14" align="left" bgcolor="#E6E6FA"> 
					    <page:pagination path="areaStick.do?method=getList" name="pagination" parameter="page"  formName="Search_AreaStick_Form"/>
					</TD>  
                </TR> 
            </logic:present> 
        </TABLE></div>
    
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
