
<%@ page language="java"   pageEncoding="UTF-8" %> 
<%@ page info="部门时段查询"  %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>department_day.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script src="js/Calendar2.js"></script><!-- 不带分秒-->
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <style>   
        table,td{   
                border:1   solid   ;
                BORDER-COLLAPSE:COLLAPSE}   
    </style>   
	<link href="Css/calendar-blue.css" rel="stylesheet"> 
     <SCRIPT language="JavaScript">
       function  check()
        {
           var stime=document.all['department_day.stime'].value;
           var etime=document.all['department_day.etime'].value;
           if( stime > etime)
            {
               alert('起始日期不能大于截止日期');
               return false;
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
  </head>
  
  <body bgColor="white" background="Image/right.gif">
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     <%
       String count=request.getAttribute("count").toString();
     %>
      <html:form action="/department_day?method=getResult" onsubmit="return check()">
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
           起始日期：
           <th align="left">
                 <html:text property="department_day.stime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('department_day.stime')})"/>&nbsp;
            </th>
           <%--<html:text property="department_day.stime"  size="9" onfocus="this.blur()" /> 
          <A onclick="return showCalendar('department_day.stime', 'y-mm-dd');" href="#"><img src="Image/Button.gif"  id="IMG2"  border="0"/>
          </A>--%>
          
          &nbsp;&nbsp;
       
            截止日期：
           <th align="left">
                 <html:text property="department_day.etime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('department_day.etime')})"/>&nbsp;
           </th>  
          <%--<html:text property="department_day.etime"  size="9" /> 
          <A onclick="return showCalendar('department_day.etime', 'y-mm-dd');" href="#"><img src="Image/Button.gif"  id="IMG2"  border="0"/>
          </A>--%>
          &nbsp;&nbsp;
          <html:submit>查 询</html:submit>&nbsp;&nbsp;
          <logic:equal  name="listCount" value="0">
                      <input type="button"  value="打印预览"  disabled>
	      </logic:equal>
	      <logic:notEqual name="listCount" value="0">  
	      	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                  <input type="button"  value="打印预览"  onclick="javascript:window.open('department_dayPrint.jsp')">
	      </logic:notEqual>
	      
          <br /><br /> 
          <div id = "printdiv">
           <table width="600" >
	               <tr>
	                   <td  colspan="6"   >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部 门 人 员 下 井 情 况 统 计 表  &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下井总人数： <font color="#F00000"><%= count %></font>
	                    </td>
	                </tr>   
	              <tr>
	                  <logic:present name="time">
	                    <td width="110" bgcolor="#CCCCCC" align="center">
	                    <%--<bean:write name="time" property="stime"/> 到 <bean:write name="time" property="etime"/>--%>
	                    部门-班组（姓名）
	                    </td>
	                  </logic:present>
	                  <td width="90"  align="center" bgcolor="#CCCCCC">人数(卡号)</td>
	                  <td width="100" align="center" bgcolor="#CCCCCC">下井总人次</td>
	                  <td width="100" align="center" bgcolor="#CCCCCC">下井总时长</td>
	                  <td width="100" align="center" bgcolor="#CCCCCC">下井平均时长</td>
	                  <td width="100" align="center" bgcolor="#CCCCCC">平均下井次数</td>
	             </tr> 
                 <logic:present name="relist">
                    <logic:iterate name="relist" id="relist">
                       <tr  onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">                          
                          <logic:equal name="relist" property="dep" value="1">
                               <td align="left"><FONT color="#FF6666"><bean:write name="relist" property="department2"/></FONT></td>
                          </logic:equal>
                          <logic:equal name="relist" property="dep" value="2">
                               <td align="center"><FONT color="#336699"><bean:write name="relist" property="department2"/></FONT></td>
                          </logic:equal>
                          <logic:equal name="relist" property="dep" value="3">
                              <td align="right"><bean:write name="relist" property="department2"/></td>
                          </logic:equal>
                          <td align="center"><bean:write name="relist" property="allcount"/></td>
                          <td align="center"><bean:write name="relist" property="peoplecount"/></td>
                          <td align="center"><bean:write name="relist" property="worktime"/></td>
                          <td align="center"><bean:write name="relist" property="avgworktime"/></td>
                          <logic:equal name="relist" property="avgdowncount" value="0">
                             <td align="center"> </td>
                          </logic:equal>
                          <logic:notEqual name="relist" property="avgdowncount" value="0">
                               <td align="center"><bean:write name="relist" property="avgdowncount"/></td>
                          </logic:notEqual>
                          
                       </tr>
                    </logic:iterate>
                 </logic:present>
            </table></div>
      </html:form>
  </body>
</html:html>
