
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="卡呼救报警" %>

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
    
    <title>card_batteries.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <script language="javascript" src="js/page.js"></script><!--分页 -->
    <script language="javascript" src="js/CardIdORName.js"></script>
    <script language="javascript" src="js/jquery.js"></script>
    <script language="javascript" src="js/export_xls.js"></script>
    
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

	</SCRIPT>
    <SCRIPT language="javascript">
        function check()
          {
               var start=document.all['ser_card_batteries.stime'].value;
               var end=document.all['ser_card_batteries.etime'].value;
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
  
  <body bgColor="white" background="Image/right.gif">
    <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
      <html:form action="/card_alarm?method=getListCall" onsubmit="return check()">
          <table width="100%">
              <tr>
              <th align="center" >开始时间：</th>
             <th align="left">
                 <html:text property="ser_card_batteries.stime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,
                 dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_card_batteries.stime')})" style="width:125px;"/>&nbsp;
             </th>
             <th>
             &nbsp;
             </th>
              <th align="center">结束时间：</th>
                 <th align="left">
                 <html:text property="ser_card_batteries.etime" size="9" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',
                 isShowClear:false,el:$dp.$('ser_card_batteries.etime')})" style="width:125px;"/>&nbsp;
           	  </th>
           	   <th>
             &nbsp;
             </th>
             <th align="center">部门：</th>   
             <th align="left">
                 <div style="position:relative;">
                 <span style="margin-left:100px;width:18px;overflow:hidden;">
                 <select  style="width:118px;margin-left:-100px" onchange="this.parentNode.nextSibling.value=this.value">
                 <logic:present name="dep_list">
              		<option value=""></option>
                 	<logic:iterate id="aa" name="dep_list">
                 		<option value='<bean:write name="aa" property="wordvalue"/>'><bean:write name="aa" property="wordvalue"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
                 <html:text property="ser_card_batteries.dep" style="width:100px;position:absolute;left:0px;"></html:text>
				</div>
                 </th>
                  <th>
             	&nbsp;
             	</th>    
                 <th><html:submit>查 询</html:submit></th><th>&nbsp;</th>
                 <th>
                    <logic:equal  name="listCount" value="0">
                          <input type="button"  value="打印预览"  disabled>
	                </logic:equal>
	                <logic:notEqual name="listCount" value="0">  
	                	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                </logic:notEqual>
                  </th><th>&nbsp;</th>    
                  <th>
					<logic:equal name="listCount" value="0">
						<input type="button" value="导出excel" disabled>
					</logic:equal>
					<logic:notEqual name="listCount" value="0">
						<input type="button" value="导出excel" onclick="ExportXls('card_alarm.do?method=doOpenExcelCall')">

					</logic:notEqual>
				</th>
              </tr>
                </table><br>
      <div id = "printdiv">
        <TABLE width="100%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
           	<TR>
           		<TD align="left" width="10%"  bgcolor="#B0C4DE">卡号</TD>
           		<TD align="left" width="15%"  bgcolor="#B0C4DE">姓名</TD>
           		<TD align="left" width="15%"  bgcolor="#B0C4DE">部门</TD>
           		<TD align="left" width="15%"  bgcolor="#B0C4DE">班组</TD>
           		<TD align="left" width="15%" bgcolor="#B0C4DE">报警信息</TD>
           		<TD align="left" width="15%" bgcolor="#B0C4DE">开始报警时间</TD>
           		<TD align="left" width="15%" bgcolor="#B0C4DE">最后报警时间</TD>
           	</TR>  
           	<logic:present name="Card_call_List">
           	    <logic:iterate name="Card_call_List" id="call">
           	       <TR onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
           	          
           	          <TD align="left" bgcolor="#E6E6FA"><bean:write name="call" property="cardid" /></TD>
           	          <TD align="left" bgcolor="#E6E6FA"><bean:write name="call" property="username" /> </TD>
           	          <TD align="left" bgcolor="#E6E6FA"><bean:write name="call" property="dep" /> </TD>
           	          <TD align="left" bgcolor="#E6E6FA"><bean:write name="call" property="gro" /> </TD>
           	          <TD align="left" bgcolor="#E6E6FA"><bean:write name="call" property="info" /> </TD>
           	          <TD align="left" bgcolor="#E6E6FA"><bean:write name="call" property="mintime" /> </TD>
           	          <TD align="left" bgcolor="#E6E6FA"><bean:write name="call" property="maxtime" /> </TD>
           	       </TR>
           	    </logic:iterate>  	  
           	</logic:present>      
            <logic:present name="pagination" >
		           	<TR>
						 <TD colspan="7" align="center" bgcolor="#E6E6FA">
						        <page:pagination path="/card_alarm.do?method=getListCall" name="pagination" parameter="page"  formName="Search_Card_Batteries_Form" />
						 </TD>  
		            </TR> 
            </logic:present> 
            </TABLE></div>
      </html:form>
  </body>
</html:html>
