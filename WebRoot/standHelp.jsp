
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="分站报警查询"%>
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
    
    <title>standHelp.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script language="JavaScript" src="js/calendar.js"></script>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%> 
     <script language="javascript" src="js/Cardreader.js"></script>
     <script language="javascript" type="text/javascript" src="js/jquery.js"></script>
     <script language="javascript" type="text/javascript" src="js/export_xls.js"></script>
    <SCRIPT>
       function check()
        {
         var stime=document.all['standHelp.stime'].value;
         var etime=document.all['standHelp.etime'].value;
         if( stime > etime)
          {
            alert('起始时间不能大于截止时间');
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
		   // WebBrowser1.ExecWB(7, 1); //预览
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
    <html:form action="/standHelp?method=init" onsubmit="return check()" >
     <table>
         <tr>
             <th>起始日期：</th>
             <th align="left">
                 <html:text property="standHelp.stime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('standHelp.stime')})"/>&nbsp;
             </th>
             
             <th>起始时间：</th>
             <th align="left">
                 <html:text property="standHelp.minstime" size="8" styleId="file1" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('standHelp.minstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name1" onclick="this.checked?file1.disabled=false:file1.disabled=true" />&nbsp;
                 </th>
             
             
             <th>分站：</th>
             <th>
                   <html:text property="standHelp.cid" size="18" styleId="names3" onkeyup="findNames3();" />
                   <div style="position:absolute;" id="popup3">
				        <table id="name_table3" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="name_table_body3"></tbody>
				        </table>  
                   </div>
             </th>
          </tr>
          <tr>
             
             <th>截止日期：</th>
             <th align="left">
                 <html:text property="standHelp.etime" size="9" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('standHelp.etime')})"/>&nbsp;
           	 </th>
             <th>截止时间：</th>
             <th align="left">
                 <html:text property="standHelp.maxstime" size="8" styleId="file2" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('standHelp.maxstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name2" onclick="this.checked?file2.disabled=false:file2.disabled=true" />&nbsp;
                 </th>
             
             
             <th>&nbsp;</th>
             <th><html:submit>查 询</html:submit></th>
             <th>&nbsp;</th>
             <th>
                  <logic:equal  name="listCount" value="0">
                      <!-- <input type="button"  value="打印预览"  disabled>-->
                      <input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();" disabled/>
                      <input type="button"  value="导出excel"  disabled>
	              </logic:equal>
	              <logic:notEqual name="listCount" value="0"> 
	              	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>
	              	<input type="button"  value="导出excel"  onclick="ExportXls('standHelp.do?method=export')">  
	              </logic:notEqual>
             </th>
         </tr>
     </table><br>
     <div id = "printdiv">
    <TABLE width="786" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" >
	  		<TR>
		          <TD width="60"  align="left" bgcolor="#B0C4DE">分站号<br></TD>
		          <TD width="180" align="left" bgcolor="#B0C4DE">分站名称<br></TD>
		          <TD width="160" align="left" bgcolor="#B0C4DE">开始报警时间<br></TD>
		          <TD width="160" align="left" bgcolor="#B0C4DE">最后报警时间<br></TD>
		          <TD width="64" align="left" bgcolor="#B0C4DE">报警次数<br></TD>
		          <TD width="160" align="left" bgcolor="#B0C4DE">状态信息<br></TD>
	         </TR>
         <logic:present name="StandList">
            <logic:iterate name="StandList" id="list">
               <TR onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''" >
           		   <TD align="left" bgcolor="#E6E6FA"><bean:write name="list" property="cardreaderid" /><br></TD>
           		   <TD align="left" bgcolor="#E6E6FA"><bean:write name="list" property="shortname" /></TD>
           		   <TD align="left" bgcolor="#E6E6FA"><bean:write name="list" property="mintime" /></TD>
           		   <TD align="left" bgcolor="#E6E6FA"><bean:write name="list" property="maxtime" /></TD>
           		   <TD align="left" bgcolor="#E6E6FA"><bean:write name="list" property="alarmtimes" /></TD>
           		   <TD align="left" bgcolor="#E6E6FA"><bean:write name="list" property="info" /></TD>
         	   </TR>
            </logic:iterate>
         </logic:present>
             <logic:present name="pagination" >
		          <TR>
					  <TD colspan="7" align="center" bgcolor="#E6E6FA"> 
					       <page:pagination path="standHelp.do?method=init" name="pagination" parameter="page"  formName="Search_StandHelp_Form" />
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