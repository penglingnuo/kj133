<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="历史查询明细" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>历史查询明细</title>
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
		//WebBrowser1.ExecWB(7, 1); //预览
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
     
      <html:form action="workatthis?method=arealist">
          <table width="90%" align="center">
              <tr>        		 
                 
                 <th  align="right">
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0">  
	                     <input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	               </logic:notEqual>
					<input name=Button onclick="window.close();" type=button value=关闭>
                 </th>
				   
              </tr>          
          </table><br/> 
           <div id = "printdiv">
           <table  width="90%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="1" align="center" style="border-collapse: collapse; TABLE-LAYOUT: fixed; FONT-SIZE: 13px;">
            <tr><td colspan="8" align="center"><font size="6">轨迹列表明细</font></td></tr>
           <logic:present name="workatt_list_staffer_his">
			      	<logic:iterate name="workatt_list_staffer_his" id="workattstaffhis">
			      	  <tr>  
			      	     <td align="left" >员工号：<bean:write name="workattstaffhis" property="stafferid" /></td>
			      	     <td align="left" >姓名：<bean:write name="workattstaffhis" property="name" /></td>
			      	     <td align="left" >卡号：<bean:write name="workattstaffhis" property="cardid" /></td>
			      	     <td align="left" >部门：<bean:write name="workattstaffhis" property="department" /></td>
			      	     <td align="left" >职务：<bean:write name="workattstaffhis" property="occupation" /></td>
			      	     <td align="left" >下井时间：<bean:write name="workattstaffhis" property="downtime" /></td>
			      	     <td align="left" >升井时间：<bean:write name="workattstaffhis" property="uptime" /></td>
			      	     <td align="left" >工作时长：<bean:write name="workattstaffhis" property="worktime" /></td>
			      	  </tr>
		      		</logic:iterate>
		    	</logic:present>
            </table>
            
          <table  width="90%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" align="center">
         	 
               <tr>
                   <td width="25%"  align="center" bgcolor="#B0C4DE" >进入时间</td>
                   <td width="25%"  align="center" bgcolor="#B0C4DE" >离开时间</td>
                   <td width="20%"  align="center" bgcolor="#B0C4DE" >停留时间</td> 
                   <td width="20%"  align="center" bgcolor="#B0C4DE" >停留分站</td>      
               </tr>
               <logic:present name="area_list" scope="request">
                 <logic:iterate name="area_list" id="dw">
                     <tr>
                       <td align="center" bgcolor="#E6E6FA"><bean:write name="dw" property="starttime" /></td>
                       <td align="center" bgcolor="#E6E6FA"><bean:write name="dw" property="endtime" /></td>
                       <td align="center" bgcolor="#E6E6FA"><bean:write name="dw" property="stayinterval" /></td>
                       <td align="center" bgcolor="#E6E6FA"><bean:write name="dw" property="crname" /></td>
                    </tr>  
                 </logic:iterate>
               </logic:present>
         </table>   
        </div>
         </html:form>     
  </body>
</html:html>
