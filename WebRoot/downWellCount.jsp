
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="下井次数统计"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>downWellCount.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/page.js"></script><!--分页 -->
    <script src="js/Calendar2.js"></script><!-- 不带分秒-->
    <script language="javascript" src="js/StafferORName.js"></script>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"></script>
	<link href="Css/calendar-blue.css" rel="stylesheet"> 
	<SCRIPT language="javascript">
	   function check()
		   {
		     var stime=document.all['ser_downwellcount.stime'].value;
		     var etime=document.all['ser_downwellcount.etime'].value;
		      if(stime>etime)
			    {
			        alert("起始日期不能大于截止日期，请重新输入");
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
     
     <html:form action="downWellCount?method=getList" onsubmit="return check()" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
      <table width="555">
            <tr>
                <th>起始日期：</th>
                <th><html:text property="ser_downwellcount.stime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_downwellcount.stime')})"/></th>
                <%--<th>
                    <html:text property="ser_downwellcount.stime"  size="9" onfocus="this.blur()" />
                    <A onclick="return showCalendar('ser_downwellcount.stime', 'y-mm-dd');" href="#"><img src="Image/Button.gif"  id="IMG2"  border="0"/></A>
                </th>
                --%><th>&nbsp;&nbsp;</th>
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
                 <html:text property="ser_downwellcount.worktype" style="width:82px;position:absolute;left:0px;"></html:text>
				</div> 
                 </th>
                 
                <th>&nbsp;</th>
                <th align="left">
                       员 工： <html:text property="ser_downwellcount.stafferid" size="13" maxlength="6" styleId="names2" onkeyup="findNames2();" />&nbsp;
                      <div style="position:absolute;" id="popup2">
				        <table id="name_table2" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="name_table_body2"></tbody>
				        </table>  
                      </div>
                </th>  
           </tr>
           <tr>
               <th>截止日期： </th>
               <th><html:text property="ser_downwellcount.etime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_downwellcount.etime')})"/></th>
               <%--<th>
                   <html:text property="ser_downwellcount.etime"  size="9" onfocus="this.blur()" /> 
                   <A onclick="return showCalendar('ser_downwellcount.etime', 'y-mm-dd');" href="#"><img src="Image/Button.gif"  id="IMG2"  border="0"/></A>
              </th>
              --%><th>&nbsp;&nbsp;</th>
                <th align="left">班 组:</th>
                 <th align="left">
                 <div style="position:relative;">
                 <span style="margin-left:82px;width:18px;overflow:hidden;">
                 <select  style="width:118px;margin-left:-100px" onchange="this.parentNode.nextSibling.value=this.value">
                 <logic:present name="gro_list">
              		<option value=""></option>
                 	<logic:iterate id="aa" name="gro_list">
                 		<option value='<bean:write name="aa" property="wordvalue"/>'><bean:write name="aa" property="wordvalue"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
                 <html:text property="ser_downwellcount.group" style="width:82px;position:absolute;left:0px;"></html:text>
				</div>
                 </th>
              
              <th>&nbsp;</th>
              <th align="left"> 
                  <html:submit>统 计</html:submit>&nbsp;&nbsp;
                  <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	              </logic:equal>
	              <logic:notEqual name="listCount" value="0">
	              		<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>   
	                  <input type="button"  value="打印预览"  onclick="javascript:window.open('downWellCountPrint.jsp')">
	              </logic:notEqual>
              </th>
         </tr>
      </table><br> 
 <div id = "printdiv">
         <table cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" width="620" >
             <tr>
                 <td width="50"  align="left"  bgcolor="#B0C4DE" >卡号</td>
                 <td width="60"  align="left"  bgcolor="#B0C4DE" >姓名</td>
                 <td width="120" align="left"  bgcolor="#B0C4DE" >班组</td>
                 <td width="90"  align="left"  bgcolor="#B0C4DE" >工种</td>
                 <td width="100" align="left"  bgcolor="#B0C4DE" >入井次数统计</td>
                 <td width="100" align="left"  bgcolor="#B0C4DE" >起始日期</td>
                 <td width="100" align="left"  bgcolor="#B0C4DE" >截止日期</td>
            </tr>
            <logic:present name="DownWellCount_List">
              <logic:iterate name="DownWellCount_List" id="cou">
                  <tr  onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''" >
                      <td align="left" bgcolor="#E6E6FA"><bean:write name="cou" property="sid" /></td>
                      <td align="left" bgcolor="#E6E6FA"><bean:write name="cou" property="username" /></td>
                      <td align="left" bgcolor="#E6E6FA"><bean:write name="cou" property="gro" /></td>
                      <td align="left" bgcolor="#E6E6FA"><bean:write name="cou" property="type" /></td> 
                      <td align="left" bgcolor="#E6E6FA"><bean:write name="cou" property="downtimes" /></td>
                      <td align="left" bgcolor="#E6E6FA"><bean:write name="cou" property="stime" /></td>
                      <td align="left" bgcolor="#E6E6FA"><bean:write name="cou" property="etime" /></td>
                 </tr>   
              </logic:iterate>
            </logic:present>
                <logic:present name="pagination" >
                   <TR>
					  <TD colspan="7" align="center" bgcolor="#E6E6FA">
					          <page:pagination path="downWellCount.do?method=getList" name="pagination" parameter="page"  formName="Seach_DownWellCount_Form"/>
					  </TD>  
                    </TR> 
            </logic:present> 
         </table>   </div> 
     </html:form>      
  </body>
</html:html>
