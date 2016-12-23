<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="重点考勤日月报"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>emphases_check_report.jsp</title>
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

  	<script language="javascript">
  		function check()
	{
         var emphases_check_report = document.getElementsByName("emphases_check_report.isDayOrMonth"); 
           if(emphases_check_report){
             for(var i=0;i<emphases_check_report.length;i++){ //适合length>=2时，当emphases_check_report.length==null时，可以直接取emphases_check_report.value值
               if(emphases_check_report[i].checked){
                 if(emphases_check_report[i].value=='day'){
                 	var daytime = document.getElementById("emphases_check_report.daytime").value;
                 	document.getElementById("emphases_check_report.isChoose").value = daytime;
                	//alert(daytime);
	     		 }else{
 					var monthtime = document.getElementById("emphases_check_report.monthtime").value;
 				    document.getElementById("emphases_check_report.isChoose").value = monthtime;
                 	//alert(monthtime);
	     		}
	     		//alert("LastReturn:"+document.getElementById("emphases_check_report.isChoose").value);
               } 
             } 
           }   
	} 
</script>
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
      <html:form action="/emphases_check_report.do?method=init">
      <input type="hidden" name="emphases_check_report.isChoose"/>
        <table width="290">
            <tr>
				<th>
					<html:radio property="emphases_check_report.isDayOrMonth" value="day" onclick="check()" />
					日报：
				</th>
				<th><html:text property="emphases_check_report.daytime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('emphases_check_report.daytime')})"/></th>
				<%--<th>
					<html:text property="emphases_check_report.daytime" size="9" onfocus="this.blur()" />
					<A
						onclick="return showCalendar('emphases_check_report.daytime', 'y-mm-dd');"
						href="#"><img src="Image/Button.gif" id="IMG2" border="0" />
					</A>
				</th>
				--%><th>
					&nbsp;
				</th>
				<th>
				<html:submit onclick="check()">统 计</html:submit>
					
				</th>
			</tr>
			<tr>
				<th>
					<html:radio property="emphases_check_report.isDayOrMonth" value="month"
						onclick="check()" />
					月报：
				</th>
				<th><html:text property="emphases_check_report.monthtime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM',el:$dp.$('emphases_check_report.monthtime')})"/></th>
				<%--<th>
					<html:text property="emphases_check_report.monthtime" size="9"
						onfocus="this.blur()" />
					<A
						onclick="return showCalendar('emphases_check_report.monthtime', 'y-mm');"
						href="#"><img src="Image/Button.gif" id="IMG2" border="0" />
					</A>
				</th>
               --%><th>&nbsp;</th>
             
               
              
               <th>
                 <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	              </logic:equal>
	              <logic:notEqual name="listCount" value="0">  
	              	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                  <input type="button"  value="打印预览"  onclick="javascript:window.open('emphases_check_reportPrint.jsp')">
	              </logic:notEqual>
	              
               </th>
              
            
            </tr>
        </table><br/>
        <div id = "printdiv">
		<table  cellspacing="1" cellpadding="2"  bgcolor="#000" border="0" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
		  <tr>
		     
             <td width="50"  align="left"   bgcolor="#ffffff">序号</td> 
             <td width="80" align="center" bgcolor="#ffffff">单位</td>
             <td width="67"  align="center" bgcolor="#ffffff">总人数</td>
             <td width="67"  align="center" bgcolor="#ffffff">入井人数</td>
             <td width="67"  align="center" bgcolor="#ffffff">入井次数</td>
             <td width="50"  align="center" bgcolor="#ffffff">早班</td>
             <td width="50"  align="center" bgcolor="#ffffff">中班</td>
             <td width="50"  align="center" bgcolor="#ffffff">晚班</td>
             <td width="67"  align="center" bgcolor="#ffffff">入井总计</td>
             <td width="80"  align="center" bgcolor="#ffffff">总时长</td>
             <td width="80"  align="center" bgcolor="#ffffff">平均时长</td>
             <td width="80"  align="center" bgcolor="#ffffff">人均入井次数</td>
          </tr>
         <logic:present name="relist">
           <logic:iterate name="relist" id="list">
              <tr>
                <td align="left"   bgcolor="#CCCCCC"><bean:write name="list" property="count" /></td>
                <td align="center" bgcolor="#ffffff"><bean:write name="list" property="type" /></td>
                <td align="center" bgcolor="#ffffff"><bean:write name="list" property="totalnum" /></td>
                <td align="center" bgcolor="#ffffff"><bean:write name="list" property="intonum" /></td>
                <td align="center" bgcolor="#ffffff"><bean:write name="list" property="downsum" /></td>
                <td align="center" bgcolor="#ffffff"><bean:write name="list" property="ban01" /></td>
                <td align="center" bgcolor="#ffffff"><bean:write name="list" property="ban02" /></td>
                <td align="center" bgcolor="#ffffff"><bean:write name="list" property="ban03" /></td>
                <td align="center" bgcolor="#ffffff"><bean:write name="list" property="bannum" /></td>
                <td align="center" bgcolor="#ffffff"><bean:write name="list" property="worktime" /></td>
                <td align="center" bgcolor="#ffffff"><bean:write name="list" property="pavtime" /></td>
                <td align="center" bgcolor="#ffffff"><bean:write name="list" property="pavintonum" /></td>
              </tr>
           </logic:iterate>
         </logic:present>
        </table></div>
     </html:form>      
  </body>
</html:html>
