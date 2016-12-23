<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="历史数据查询" %>
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
    
    <title>historyNote_top.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <script language="javascript" src="js/page.js"></script>
    <script language="javascript" src="js/Cardreader.js"></script>
    <style TYPE="text/css">
		<!--
		 A:link{color:#FF0000;text-decoration:none} /*   未访问的链接   */ 
		 A:hover{color:#FF0000;text-decoration:none} /*   鼠标在链接上   */
		 a:visited{color:#FF0000;text-decoration:none}/*   已访问的链接   */ 
		 -->
    </style>
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
               var start=document.all['ser_historynote.stime'].value;
               var end=document.all['ser_historynote.etime'].value;
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
  
  <body bgColor="white" background="Image/right.gif">
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     
     <html:form action="/historyNote?method=getList" onsubmit="return check()">
        <table >
          <tr>
          
            <%--<th>起始时间：</th>
            <th><html:text property="ser_historynote.stime" size="18" onfocus="show_cele_date('','',Search_HistoryNote_Form['ser_historynote.stime'])" />&nbsp;&nbsp;&nbsp;</th>
            --%>
            <th>起始日期：</th>
                   <th align="left">
                 <html:text property="ser_historynote.stime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_historynote.stime')})"/>
                 </th>
		           
		           <th align="left">起始时间：</th>
                 
                 <th align="left">
                 <html:text property="ser_historynote.minstime" size="8" styleId="file1" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_historynote.minstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name1" onclick="this.checked?file1.disabled=false:file1.disabled=true" />&nbsp;&nbsp;
                 </th>
            <th>报警信息：</th>
            <th rowspan="3">
              <html:select property="ser_historynote.info" size="8" multiple="true">
 					<html:option value="1">定员呼叫</html:option>
				    <html:option value="2">全员呼叫</html:option>
				    <html:option value="4">部分允许</html:option>
				    <html:option value="8">部分禁止</html:option>
				    <html:option value="16">A天线故障</html:option>
				    <html:option value="32">B天线故障</html:option>
					<html:option value="64">C天线故障</html:option>
					<html:option value="128">定位中断</html:option>
	           </html:select>
            </th>
            <th>
                  <html:submit> 查   询 </html:submit>
            </th>
          </tr>
          <tr>
          <th>截止日期：</th>
                   <th align="left">
                 <html:text property="ser_historynote.etime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_historynote.etime')})"/>
                 </th>
		           
		           <th align="left">截止时间：</th>
                 
                 <th align="left">
                 <html:text property="ser_historynote.maxstime" size="8" styleId="file2" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_historynote.maxstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name2"  onclick="this.checked?file2.disabled=false:file2.disabled=true" />&nbsp;&nbsp;
                 </th>
            <%--<th>截止时间：</th>
            <th align="left"><html:text property="ser_historynote.etime" size="18"  onfocus="show_cele_date('','',Search_HistoryNote_Form['ser_historynote.etime'])" />  </th>
            --%>
            
            <th align="left"><html:radio property="ser_historynote.rad" value="radand" />状态与</th>
             <th>
               <logic:equal  name="listCount" value="0">
                      <input type="button"  value="打印预览"  disabled>
	             </logic:equal>
	             <logic:notEqual name="listCount" value="0">  
	             	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                  <input type="button"  value="打印预览"  onclick="javascript:window.open('historyNotePrint.jsp')">
	             </logic:notEqual>
            </th>
          </tr>
          
          <tr>
            <th align="right">分站：</th>
            <th align="left">
                 <html:text property="ser_historynote.cid"  size="9" styleId="names3" onkeyup="findNames3();"  />&nbsp; 
	            <div style="position:absolute;" id="popup3">
				        <table id="name_table3" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="name_table_body3"></tbody>
				        </table>  
                 </div>
            </th>
            <th>&nbsp;&nbsp;&nbsp;&nbsp;</th>
            <th>&nbsp;&nbsp;&nbsp;&nbsp;</th>
            <th>&nbsp;&nbsp;&nbsp;&nbsp;</th>
            <th align="left"><html:radio property="ser_historynote.rad" value="rador" />状态或</th>
           
          </tr>
        </table><br/>
        <div id = "printdiv">
         <TABLE   cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" >
		    <TR>
		         <TD width="50"  align="left" bgcolor="#B0C4DE" >分站号</TD>
		         <TD width="160" align="left" bgcolor="#B0C4DE" >分站名称</TD>
		         <TD width="150" align="left" bgcolor="#B0C4DE" >记录时间</TD>
		         <TD width="40"  align="left" bgcolor="#B0C4DE" >温度</TD>
		         <TD width="40"  align="left" bgcolor="#B0C4DE" >卡数</TD>
		         <TD width="250" align="left" bgcolor="#B0C4DE" >状态信息</TD>
		    </TR>
          <logic:present name="HistoryNote_List">
             <logic:iterate name="HistoryNote_List" id="hnote">
                 <TR>
                 
                    <logic:equal    name="hnote" property="ccount" value="0">
                       <TD align="left" bgcolor="#E6E6FA"><bean:write name="hnote" property="cid" /></TD>
                    </logic:equal>
                    <logic:notEqual name="hnote" property="ccount" value="0">
                       <TD align="left" bgcolor="#E6E6FA">
		               <a href="historyNote_Locator.do?method=getList&cid=<bean:write name='hnote' property='cid' />&date=<bean:write name='hnote' property='times' />"  class="A:link" title="查 看 <bean:write name="hnote" property="cid" /> 号 分 站 的 定 位 信 息"  onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" >
		                   <bean:write name="hnote" property="cid" />
		               </a>
		            </TD>
                    </logic:notEqual>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="hnote" property="name" /> </TD>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="hnote" property="times" /> </TD>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="hnote" property="temper" /> </TD>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="hnote" property="ccount" /> </TD>
		            <TD align="left" bgcolor="#E6E6FA"><bean:write name="hnote" property="info" /> </TD>
		        </TR> 
              </logic:iterate>
           </logic:present>   
           	      <logic:present name="pagination" >
	           	      <TR>
						  <TD colspan="9" align="center" bgcolor="#E6E6FA"> 
						       <page:pagination path="historyNote.do?method=getList" name="pagination" parameter="page"  formName="Search_HistoryNote_Form"/>
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