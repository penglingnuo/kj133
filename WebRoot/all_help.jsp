
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="卡所有报警查询" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>all_help.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"></script>
    <script language="javascript" src="js/StafferORName.js"></script>
    <script language="JavaScript" src="js/Locator.js"></script>
    <script language="javascript" src="js/Cardreader.js"></script>
    <script language="javascript" src="js/page.js"></script><!--分页 -->
      <SCRIPT language="javascript">
        function check()
          {
               var start=document.all['ser_allhelp.stime'].value;
               var end=document.all['ser_allhelp.etime'].value;
	           if(start>=end){
	                 alert('起始时间不能大于或等于截止时间，请重新输入');
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
  
  <body bgColor="white" background="Image/right.gif" >
    <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
       <html:form action="all_help?method=getList" onsubmit="return check()">
		       <table >
		          <tr>
		            <th>起始时间：</th>
		            <th><html:text property="ser_allhelp.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_allhelp.stime')})"/>
</th>
		            <th>&nbsp;&nbsp;</th>
		            <th>班组：</th>
		            <th align="left">
		               <html:select property="ser_allhelp.gro" style="width:140px" >&nbsp;&nbsp;&nbsp;
                    		<html:option value="" ></html:option>
                    		<html:options collection="gro_list" property="wordvalue" labelProperty="wordvalue"/>
                       </html:select>  
                	</th>
                	<th>&nbsp;&nbsp;</th>
		            <th>报警信息：</th>
		            <th rowspan="3">
		                  <html:select property="ser_allhelp.info" size="8" multiple="true">
			              	 <html:option value="1">处于呼叫状态</html:option>
				             <html:option value="2">卡电池报警</html:option>
				             <html:option value="4">通行报警</html:option>
				             <html:option value="8">呼救</html:option>
				             <html:option value="16">卡信号中断</html:option>
				             <html:option value="32">下井超时</html:option>
						     <html:option value="64">定位器电池报警</html:option>
						     <html:option value="128">定位中断</html:option>
			           </html:select>
		            </th>
		          </tr>
		          <tr>
		            <th>截止时间：</th>
		            <th><html:text property="ser_allhelp.etime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_allhelp.etime')})"/>
</th>
		            <th>&nbsp;&nbsp;</th>
		            <th>分站：</th>
		            <th align="left">
		                 <html:text property="ser_allhelp.cid" size="18"   styleId="names3" onkeyup="findNames3();" />
		                 <div style="position:absolute;" id="popup3">
					        <table id="name_table3" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
					            <tbody id="name_table_body3"></tbody>
					        </table>  
                         </div>
		            </th>
		            <th>&nbsp;&nbsp;</th>
		            <th align="left"><html:radio property="ser_allhelp.rad" value="radand" />状态与</th>
		          </tr>
		          
		          <tr>
		            <th colspan="5" align="left">
		                 定位器： 
		                  <html:text property="ser_allhelp.lid" size="9"   maxlength="4" styleId="names4" onkeyup="findNames4();" />&nbsp;  
		                  <div style="position:absolute;" id="popup4">
					        <table id="name_table4" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
					            <tbody id="name_table_body4"></tbody>
					        </table>  
                          </div>  
		                 员工： 
		                  <html:text property="ser_allhelp.sid" size="7"  maxlength="6"  styleId="names2" onkeyup="findNames2();" />&nbsp; 
		                   <div style="position:absolute;" id="popup2">
						        <table id="name_table2" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
						            <tbody id="name_table_body2"></tbody>
						        </table>  
                           </div>
		               
		               <html:submit>查 询</html:submit>&nbsp;
		               <logic:equal  name="listCount" value="0">
                          <input type="button"  value="打印预览"  disabled>
	                   </logic:equal>
	                   <logic:notEqual name="listCount" value="0"> 
	                   	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>  
	                      <input type="button"  value="打印预览"  onclick="javascript:window.location.href='all_help.do?method=viewPrint'">
	                   </logic:notEqual>
		            </th>
		            <th>&nbsp;&nbsp;</th>
		            <th align="left"><html:radio property="ser_allhelp.rad" value="rador" />状态或</th>
		          </tr>
		        </table><br/> 
		        
<div id = "printdiv">
	  <div   style="overflow:auto;width:800;height:400;"> 
       <TABLE width="1640" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" >
  		<TR>
	         <TD width="50"  align="left" bgcolor="#B0C4DE">卡号</TD>
	         <TD width="55"  align="left" bgcolor="#B0C4DE">姓名</TD>
	         <TD width="100" align="left" bgcolor="#B0C4DE">班组</TD>
	         <TD width="80"  align="left" bgcolor="#B0C4DE">工种</TD>
	         <TD width="60"  align="left" bgcolor="#B0C4DE">分站号</TD>
	         <TD width="220" align="left" bgcolor="#B0C4DE">分站名称</TD>
	         <TD width="75"  align="left" bgcolor="#B0C4DE">定位器号</TD>
	         <TD width="220" align="left" bgcolor="#B0C4DE">定位器名称</TD>
	         <TD width="170" align="left" bgcolor="#B0C4DE">开始报警时间</TD>
	         <TD width="170" align="left" bgcolor="#B0C4DE">最后报警时间</TD>
	         <TD width="440" align="left" bgcolor="#B0C4DE">状态信息</TD>
	      </TR>
	      <logic:present name="All_help_List">
	          <logic:iterate name="All_help_List" id="all">
	              <TR onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
	                <TD align="left" bgcolor="#E6E6FA"><bean:write name="all" property="stafferid" /> </TD>
	                <TD align="left" bgcolor="#E6E6FA"><bean:write name="all" property="username" /> </TD>
	                <TD align="left" bgcolor="#E6E6FA"><bean:write name="all" property="grop" /> </TD>
	                <TD align="left" bgcolor="#E6E6FA"><bean:write name="all" property="worktype" /> </TD>
	                <TD align="left" bgcolor="#E6E6FA"><bean:write name="all" property="cardreaderid" /> </TD>
	                <TD align="left" bgcolor="#E6E6FA"><bean:write name="all" property="shortname" /> </TD>
	                <TD align="left" bgcolor="#E6E6FA"><bean:write name="all" property="locatorid" /> </TD>
	                <TD align="left" bgcolor="#E6E6FA"><bean:write name="all" property="locatorname" /> </TD>
	                <TD align="left" bgcolor="#E6E6FA"><bean:write name="all" property="stime" /> </TD>
	                <TD align="left" bgcolor="#E6E6FA"><bean:write name="all" property="etime" /> </TD>
	                <TD align="left" bgcolor="#E6E6FA"><bean:write name="all" property="info" /> </TD>
	     		 </TR>
	          </logic:iterate>
	      </logic:present>
	            <logic:present name="pagination" >
		             <TR>
						 <TD colspan="12" align="left" bgcolor="#E6E6FA">
						     <page:pagination path="all_help.do?method=getList" name="pagination" parameter="page"  formName="Search_AllHelp_Form"/>
						 </TD>  
	                 </TR> 
                 </logic:present> 
	     </TABLE> 
	     </div></div>
       </html:form> 
  </body>
</html:html>
