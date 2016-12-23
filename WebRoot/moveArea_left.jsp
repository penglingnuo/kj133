
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="活动区域查询" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>moveArea_left.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include> 

    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/page.js"></script>
    <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <script language="javascript" src="js/StafferORName.js"></script>
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
    <style TYPE="text/css">
		<!--
		 A:link{color:#FF0000;text-decoration:none} /*   未访问的链接   */ 
		  A:hover{color:#FF0000;text-decoration:none} /*   鼠标在链接上   */
		  A:visited{color:#FF0000;text-decoration:none}/*   已访问的链接   */ 
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
         
          var stime=document.all['ser_movearea.stime'].value;
          var etime=document.all['ser_movearea.etime'].value;
          var sid=document.all['ser_movearea.sid'].value;
           if(stime>etime){
             alert("开始时间大于结束时间，请重新输入");
             return false;
            }if(sid==""){
             alert("员工号不能为空");
             document.all['ser_movearea.sid'].focus();
             return false;
            }
           return true;
        }
    </SCRIPT>
  </head>
  
  <body bgColor="white" background="Image/right.gif">
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     
     <html:form action="/moveArea_left?method=getList" onsubmit="return check()" >
          <table>
              <tr>
                  <th>开始时间：</th>
                  <th align="left">
                 <html:text property="ser_movearea.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_movearea.stime')})"/>
                 </th>
                  <th>&nbsp;&nbsp;</th>
                  <th align="left">出现次数大于：</th>
                  <th align="left"><html:text property="ser_movearea.maxcount" size="5" maxlength="4" onkeyup="this.value=this.value.replace(/\D/g,'')" /></th>
                  <th>&nbsp;</th>
                  <th align="left">出现次数小于：</th>
                  <th align="left"><html:text property="ser_movearea.mincount" size="5" maxlength="4" onkeyup="this.value=this.value.replace(/\D/g,'')" /></th>
                  
              </tr>
               <tr>
                  <th>结束时间：</th>
                  <th align="left">
                 <html:text property="ser_movearea.etime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_movearea.etime')})"/>
                 </th>
                  
                  <th>&nbsp;&nbsp;</th>
                  <th colspan="5" align="left">员工： 
                      <html:text property="ser_movearea.sid" size="18"  maxlength="6"  styleId="names2" onkeyup="findNames2();" />
                      <div style="position:absolute;" id="popup2">
				        <table id="name_table2" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="name_table_body2"></tbody>
				        </table>  
                      </div>
                      <html:submit>查 询</html:submit>
                      <logic:equal  name="listCount" value="0">
                          <input type="button"  value="打印预览"  disabled>
	                  </logic:equal>
	                  <logic:notEqual name="listCount" value="0">  
	                  		<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                      <input type="button"  value="打印预览"  onclick="javascript:window.open('moveAreaPrint.jsp')">
	                  </logic:notEqual>
                  </th>
              </tr>
          </table><br> 
        <div id = "printdiv">  
        <table  cellspacing="1" cellpadding="1"   bgcolor="#6CA6CD" border="0" width="635" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
            <tr>
              <td align="left" bgcolor="#B0C4DE" width="65">分站号</td>
              <td align="left" bgcolor="#B0C4DE" width="200">分站名称</td>
              <td align="left" bgcolor="#B0C4DE" width="65">出现次数</td>
              <td align="left" bgcolor="#B0C4DE" width="100">总停留时间</td>
              <td align="left" bgcolor="#B0C4DE" width="55">地图号</td>
              <td align="left" bgcolor="#B0C4DE" width="150">地图名称</td>
            </tr>
            <logic:present name="MoveArea_List">
               <logic:iterate name="MoveArea_List" id="ma">
                    <tr>
                         <td align="left" bgcolor="#E6E6FA">
                             <a href="moveArea_left.do?method=particular&cid=<bean:write name="ma" property="cardreaderid" />"   title="定位器详细信息" class="A:link"/>
                             <bean:write  name="ma" property="cardreaderid" /></a>
                         </td>
                         <td align="left" bgcolor="#E6E6FA"><bean:write  name="ma" property="crname" /></td>
                         <td align="left" bgcolor="#E6E6FA"><bean:write  name="ma" property="appeartimes" /></td>
                         <td align="left" bgcolor="#E6E6FA"><bean:write  name="ma" property="staytime" /></td>
                         <td align="left" bgcolor="#E6E6FA"><bean:write  name="ma" property="mapid" /></td>
                         <td align="left" bgcolor="#E6E6FA"><bean:write  name="ma" property="mapname" /></td>
                     </tr>
                  </logic:iterate>
            </logic:present>
            <logic:present name="pagination" >
                  <tr>
					   <td colspan="6" align="left" bgcolor="#E6E6FA">
					        <page:pagination path="/moveArea_left.do?method=getList" parameter="page" formName="Search_MoveArea_Form" />
					  </td>  
                   </tr> 
                </logic:present>
        </table></div>
     </html:form>
  </body>
</html:html>
