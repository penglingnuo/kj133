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
    
    <title>管技人员考勤明细</title>
   <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script language="javascript" src="js/page.js"></script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/CardIdORName.js"></script>
     <SCRIPT language="JavaScript">
       
       function printdiv() {
	    var newWin = window.open('printer', '', '');
	    var titleHTML = document.getElementById("printdiv").innerHTML;
	    for(var i=0;i<2;i++){
	    	titleHTML = titleHTML.toString().replace("border=0", "border=1");	
	    }
	    for(var i=0;i<2;i++){
	    	titleHTML = titleHTML.toString().replace("cellSpacing=1", "cellSpacing=0");
	    }
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
  <% 


  		       String cardid= (String)request.getParameter("cardid");
		       String name= (String)request.getParameter("name");
		       String worktype= (String)request.getParameter("worktype");
		       String downtime= (String)request.getParameter("downtime");
		       String uptime= (String)request.getParameter("uptime");
		       String banci= (String)request.getParameter("banci");
  
  %>
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     
      <html:form action="gj?method=getList" onsubmit="return check()" >
          <table width="80%" align="center">
              <tr>        		 
                 
                 <th  align="right">
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0">  
	                     <!--  <input type="button"  value="打印预览"  onclick="javascript:window.open('gjPrint.jsp')">-->
	                     <input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	               </logic:notEqual>
	               
	               <OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 id=WebBrowser width=0></OBJECT> 
					<!-- <input name=Button onClick=document.all.WebBrowser.ExecWB(4,1) type=button value=另存为> -->
					<input name=Button onclick="window.close();" type=button value=关闭>
                 </th>
				   
              </tr>          
          </table><br/> 
           <div id = "printdiv">
            <table  width="80%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="1" align="center">
            <tr>
            			<!-- <td>姓名：<%= name %></td>--> 
 				  <td>卡号：<%= cardid %></td>
 				 <!-- <td>单位：<%= worktype %></td>-->
 				 <td>下井时间：<%= downtime %></td>
 				 <td>升井时间：<%= uptime %></td>
 				  <!--<td>班次：<%= banci %></td>-->
 				 </tr>
            </table>
          <table  width="80%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" align="center">
         	 
         	 
               <tr>
                   <td width="10%"   align="center" bgcolor="#B0C4DE" >分站编号</td>  
                   <td width="20%"  align="center" bgcolor="#B0C4DE" >分站位置</td>
                   <td width="25%"  align="center" bgcolor="#B0C4DE" >进入时间</td>
                   <td width="25%"  align="center" bgcolor="#B0C4DE" >离开时间</td>
                   <td width="20%"  align="center" bgcolor="#B0C4DE" >停留时间</td>       
               </tr>
               <logic:present name="downWell_list" scope="request">
                 <logic:iterate name="downWell_list" id="dw">
                     <tr>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="cardreaderid" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="crname" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="starttime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="endtime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="betweentime" /></td>
                    </tr>  
                 </logic:iterate>
               </logic:present>
         </table>   
        </div>
         </html:form>     
  </body>
</html:html>
