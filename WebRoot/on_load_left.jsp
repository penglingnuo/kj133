
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="在场信息查询" %> 

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>on_load_left.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <style TYPE="text/css">
		<!--
		  A:link{color:#FF0000;text-decoration:none} /*   未访问的链接   */ 
		  A:hover{color:#FF0000;text-decoration:none} /*   鼠标在链接上   */
		  A:visited{color:#FF0000;text-decoration:none}/*   已访问的链接   */ 
		 -->
    </style>
    <SCRIPT language="javascript">
      function check()
       {
          var stime=document.all['ser_onload.stime'].value;
          var etime=document.all['ser_onload.etime'].value;
          if(stime>etime){
             alert("开始时间不能大于结束时间，请重新输入");
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
  </head><%--
  
  <body bgColor="white" background="Image/right.gif" style="overflow:hidden">
  --%><body bgColor="white" background="Image/right.gif" >
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     
      <html:form action="on_load_left?method=getList" onsubmit="return check()">
        <table width="400">
           <tr>
               <th>开始时间：</th>
               <th align="left">
                 <html:text property="ser_onload.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_onload.stime')})"/>
               <th>&nbsp;&nbsp;</th>
               <th align="left"><html:submit>查  询</html:submit></th> 
               <th>&nbsp;&nbsp;</th> 
               <th>
                   <logic:equal  name="listCount" value="0">
                      <input type="button"  value="打印预览"  disabled>
	               </logic:equal>
	               <logic:notEqual name="listCount" value="0">  
	               	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                  <input type="button"  value="打印预览"  onclick="javascript:window.open('on_load_leftPrint.jsp')">
	             </logic:notEqual>
	           </th> 
           </tr>
           <tr>
               <th>结束时间：</th>
               <th align="left">
                 <html:text property="ser_onload.etime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_onload.etime')})"/>
                 </th>
           </tr>
        </table><br/>
        <div id = "printdiv">
	    <table  cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
	         <tr>
	            <td width="60"  align="left" bgcolor="#B0C4DE">分站号</td>
	            <td width="120" align="left" bgcolor="#B0C4DE">分站名称</td>
	            <td width="65"  align="left" bgcolor="#B0C4DE">在场人数</td>
	            <td width="60"  align="left" bgcolor="#B0C4DE">地图号</td>
	            <td width="120" align="left" bgcolor="#B0C4DE">地图名称</td>
	         </tr>
	        <logic:present name="On_load_left">
	           <logic:iterate name="On_load_left" id="left">
	             <tr>
			        <td align="left" bgcolor="#E6E6FA">
			            <a href="on_load_left.do?method=particular&cid=<bean:write name='left' property='cardreaderid' />" target="mainFrame" title="查 看 <bean:write name="left" property="cardreaderid" /> 号 分 站 信 息"  onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link" >
			             <bean:write name="left" property="cardreaderid" /> </a>
			       </td>
			        <td align="left" bgcolor="#E6E6FA"><bean:write name="left" property="crname" /> </td>
			        <td align="left" bgcolor="#E6E6FA"><bean:write name="left" property="onscenecount" /> </td>
			        <td align="left" bgcolor="#E6E6FA"><bean:write name="left" property="mapid" /> </td>
			        <td align="left" bgcolor="#E6E6FA"><bean:write name="left" property="mapname" /> </td>
			     </tr>
	           </logic:iterate>
	        </logic:present>
		   </table></div>
	  </html:form>
  </body>
</html:html>
