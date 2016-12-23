<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="管技考勤明细" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<%@ page import="com.kj133.entity.GjUser"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <title>管技人员信息考勤信息</title>
    <jsp:include flush="true" page="/checkgjuser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script language="javascript" src="js/page.js"></script>
    <script language="JavaScript" src="js/calendar.js"></script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/CardIdORName.js"></script>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"></script>
     <SCRIPT language="JavaScript">
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
       function check(){
	        var riqi=document.all['ser_shuaka.ktime'].value;
	        if(riqi==null||riqi==''){
	        	 alert("请选择日期");
	        	 return false;
	        } 
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

<table border="0"  align="center" width="90%">
	<tr >
		<td align="left">当前用户：${gjuser.gjusername}</td>
		<td align="right"> 
			<a href="<%=path%>/updategjpwd.jsp">修改密码</a> 
			<%  
				GjUser b = (GjUser) request.getSession().getAttribute("gjuser");
				if(b!=null){ 
					//如果是admin用户就有新增的权限
					if(b.getGjusername().equals("admin")){
			%>
				<a href="gjuser.do?method=getallgjuser">管技用户管理</a>	
			<%}} %>
			<a href="gjuser.do?method=gjuserLogOut">退出</a>
		</td>
	</tr>
</table>
<div id = "printdiv">
<table width="90%" align="center" bgcolor="#6CA6CD" border="0">
<tr>
<td align="center" valign="top" ><font size=6>范各庄矿中层及以下管技人员井下走动巡查公示</font></td>
</tr>
</table></br>

          <table width="90%" align="center">
              <tr>        
              <td align="right" valign="top" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		 <th align="right">日期:</th>
                 <th><html:text property="ser_shuaka.ktime" size="9" styleId="file9" disabled="false" onfocus="WdatePicker({skin:'whyGreen',isShowClear:true,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_shuaka.ktime')})"/>
                 </th>
                 
                 
                 <th align="right"><html:submit>查 询</html:submit></th>
                
                 <th  align="right">
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
          </table> 
           
          <table  width="90%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" align="center">
         
               <tr>
		   <td width="8%"  align="center" bgcolor="#B0C4DE" >部门</td>
                   <td width="5%"  align="center" bgcolor="#B0C4DE" >序号</td>  
                   <td width="10%"  align="center" bgcolor="#B0C4DE" >姓名</td>
                   
                   <td width="10%"  align="center" bgcolor="#B0C4DE" >职务</td>
                   <td width="15%"  align="center" bgcolor="#B0C4DE" >下井时间</td>
                   <td width="15%"  align="center" bgcolor="#B0C4DE" >升井时间</td>
                   <td width="16%" align="center" bgcolor="#B0C4DE" >工作时长</td>
                   <td width="10%" align="center" bgcolor="#B0C4DE" >班次</td>
                   <td width="11%"  align="center" bgcolor="#B0C4DE" >行走路线</td>          
               </tr>
               <logic:present name="downWell_list" scope="request">
                 <logic:iterate name="downWell_list" id="dw" indexId= "index">
                     <tr>
		       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="department" /></td>
                       <td align="center" bgcolor="#E6E6FA"><%= ++index %></td>
                       <td align="center" bgcolor="#E6E6FA"><bean:write name="dw" property="name" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="worktype" /></td>
                       <td align="center" bgcolor="#E6E6FA"><bean:write name="dw" property="downtime" /></td>
                       <td align="center" bgcolor="#E6E6FA"><bean:write name="dw" property="uptime" /></td>
                       <td align="center" bgcolor="#E6E6FA"><bean:write name="dw" property="worktime" /></td>
                       <td align="center" bgcolor="#E6E6FA"><bean:write name="dw" property="banci" /></td>
                       <td align="center" bgcolor="#E6E6FA"><html:link target= "_black" page="/gj.do?method=getAreaList&cardid=${dw.cardid}&name=${dw.name}&worktype=${dw.worktype}&banci=${dw.banci}&downtime=${dw.downtime}&uptime=${dw.uptime}">行走轨迹</html:link>
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
