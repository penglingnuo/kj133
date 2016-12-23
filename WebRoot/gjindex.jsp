
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="管技考勤明细" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>管技人员信息考勤信息</title>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script language="javascript" src="js/page.js"></script>
    <script language="JavaScript" src="js/calendar.js"></script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/CardIdORName.js"></script>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"></script>
     <SCRIPT language="JavaScript">
       function check()
         {
	         var stime=document.all['ser_shuaka.stime'].value;
	         var etime=document.all['ser_shuaka.etime'].value;	        
	         if( stime > etime){
	            alert('入井开始日期不能大于入井结束日期');
	            return false;
         	}
       }
       
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
       
    </SCRIPT><%--
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
    
  --%></head>
  
  <body bgColor="white" background="Image/right.gif" >
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     
      <html:form action="gj?method=getList" onsubmit="return check()" >
          <table width="1000">
              <tr>        
              <th align="right">工 种:</th>
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
                 <html:text property="ser_shuaka.type" style="width:82px;position:absolute;left:0px;"></html:text>
				</div>     
				</th>   
				 <th align="right">开始日期:</th>
                 <th><html:text property="ser_shuaka.stime" size="9" styleId="file9" disabled="false" onfocus="WdatePicker({skin:'whyGreen',isShowClear:true,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_shuaka.stime')})"/>
                 </th>
                 <th align="right">结束时间:</th>
                <th><html:text property="ser_shuaka.etime" size="9" styleId="file10" disabled="false" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_shuaka.etime')})"/>
                 </th>
                 
                 <th align="left"><html:submit>查 询</html:submit></th>
                
                 <th  align="left">
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0">  
	                     <!--  <input type="button"  value="打印预览"  onclick="javascript:window.open('gjPrint.jsp')">-->
	                     <input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	               </logic:notEqual>
	               
	               <OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 id=WebBrowser width=0></OBJECT> 

					<!--<input name=Button onClick=document.all.WebBrowser.ExecWB(4,1) type=button value=另存为> -->

					<input name=Button onclick="window.close();" type=button value=关闭>
	               
                 </th>
				   
              </tr>          
          </table><br/> 
           <div id = "printdiv">
          <table  width="100%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
         
               <tr>
                   <td width="3%"  align="center" bgcolor="#B0C4DE" >序号</td>  
                   <td width="10%"  align="center" bgcolor="#B0C4DE" >姓名</td>
                   <td width="15%"  align="center" bgcolor="#B0C4DE" >部门</td>
                   <td width="15%"  align="center" bgcolor="#B0C4DE" >工种</td>
                   <td width="12%"  align="center" bgcolor="#B0C4DE" >下井时间</td>
                   <td width="12%"  align="center" bgcolor="#B0C4DE" >升井时间</td>
                   <td width="11%" align="center" bgcolor="#B0C4DE" >工作时长</td>
                   <td width="10%" align="center" bgcolor="#B0C4DE" >班次</td>
                   <td width="10%"  align="center" bgcolor="#B0C4DE" >行走路线</td>          
               </tr>
               <logic:present name="downWell_list" scope="request">
                 <logic:iterate name="downWell_list" id="dw" indexId= "index">
                     <tr>
                       <td align="left" bgcolor="#E6E6FA"><%= ++index %></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="name" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="department" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="worktype" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="downtime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="uptime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="worktime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="banci" /></td>
                       <td align="left" bgcolor="#E6E6FA"><html:link target= "_black" page="/gj.do?method=getAreaList&cardid=${dw.cardid}&name=${dw.name}&worktype=${dw.worktype}&banci=${dw.banci}&downtime=${dw.downtime}&uptime=${dw.uptime}">行走轨迹</html:link>
                       </td>                 
                    </tr>  
                 </logic:iterate>
               </logic:present>
               <logic:present name="pagination" >
                  <tr>
					   <td colspan="18" align="left" bgcolor="#E6E6FA">
					        <page:pagination path="gj.do?method=getList" parameter="page" formName="Search_ShuaKa_Form" />
					  </td>  
                   </tr> 
                </logic:present> 
                
         </table>   
        </div>
         </html:form>     
  </body>
</html:html>
