
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="编辑分站"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<%
String name1=(String)request.getAttribute("name1");
String name2=(String)request.getAttribute("name2");
String name3=(String)request.getAttribute("name3");
String name4=(String)request.getAttribute("name4");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>editViewReader.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
     <script language="javascript" src="js/page.js"></script>
     <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
     <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
     <script language="javascript" src="js/Cardreader.js"></script>
     <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
     <style TYPE="text/css">
		<!--
		  A:link{color:#FF0000;text-decoration:none} /*   未访问的链接   */ 
		  A:hover{color:#FF0000;text-decoration:none} /*   鼠标在链接上   */
		  A:visited{color:#FF0000;text-decoration:none}/*   已访问的链接   */ 
		 -->
     </style>
    <SCRIPT language="JavaScript">
      function CheckAll(form)
			{
				for (var i=0;i<form.elements.length;i++){
					var e = form.elements[i];
					if (e.name == 'id')
					e.checked = form.checkall.checked;
				}
			}
		 
		   function DeleteSomeLine(form)
		    {
			   if (SelectedCounts(form,"id")>0){
				    if(confirm("删除已选中的记录,将无法恢复！\n     确定要这样做吗？"))
				    {
				     var str="";
				     var init=document.getElementsByName('id');
				     for(var i=0;i<init.length;i++)
				       {
				         if(init[i].checked)
				             str+=(','+init[i].value);       
				       }
				     location.href="operartrViewReader.do?method=delete&cid="+str.substring(1);   
					 return true;
				    }
			   }else{
				    alert("请先选择要删除的记录!");
				    return false;
			    }
			} 
			function SelectedCounts(form,ItemID)
			{
			  var SelectedCounts=0;  //初始选中个数为0
			  var f=form;
			  for (i=0;i<f.elements.length;i++)
			    if (f.elements[i].name==ItemID && f.elements[i].checked==true)
				    {
			          SelectedCounts++;
					 }
			  return SelectedCounts;
			}
      function check()
       {
         var stime=document.all['ser_viewreader.stime'].value;
         var etime=document.all['ser_viewreader.etime'].value;
         var name1 = document.getElementById("name1").checked;
         var name3 = document.getElementById("name3").checked;
         if(name1 && name3){
         if( stime > etime){
            alert('起始时间不能大于截止时间');
            return false;
         }
         return true;
       }
       }
       
       function OpenExcel(){
			  window.parent.location.href="editViewReader.do?method=doOpenExcel";
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
			     <font color="red"><bean:write name="message" /></font>
			  </html:messages>
           </logic:messagesPresent>
      
     <html:form action="editViewReader?method=getList" onsubmit="return check()" >
          <table width="750">
          <tr>
              <th align="left">注册日期大于:</th>
                 
                 <th align="left">
                 <html:text property="ser_viewreader.stime" size="9" styleId="file1" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_viewreader.stime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name1" onclick="this.checked?file1.disabled=false:file1.disabled=true" />&nbsp;
                 
                 </th><th align="left">时间大于:</th>
                 <th align="left">
                 <html:text property="ser_viewreader.minstime" size="8" styleId="file2" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_viewreader.minstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name2" onclick="this.checked?file2.disabled=false:file2.disabled=true" />&nbsp;
                 </th>
                 <th align="left">分站:</th>
                 <th align="left">
                    <html:text property="ser_viewreader.cid" size="16"   styleId="names3" onkeyup="findNames3();"  />
	                     <div style="position:absolute;" id="popup3">
					        <table id="name_table3" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
					            <tbody id="name_table_body3"></tbody>
					        </table>  
                         </div>
                </th>
                <th><html:submit>查 询</html:submit>&nbsp;</th>
                
              
              </tr>
              <tr>
              <th align="left">注册日期小于:</th>
                 
                 <th align="left">
                 <html:text property="ser_viewreader.etime" size="9" styleId="file3" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_viewreader.etime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name3" onclick="this.checked?file3.disabled=false:file3.disabled=true" />&nbsp;
                 
                 </th><th align="left">时间小于:</th>
                 <th align="left">
                 <html:text property="ser_viewreader.maxstime" size="8" styleId="file4" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_viewreader.maxstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name4" onclick="this.checked?file4.disabled=false:file4.disabled=true" />&nbsp;
                 </th>
                 
                 
                 <th><input type="button" value="增 加"  onclick="javascript:location.href='addEditViewReader.jsp'">
                 </th>
                 <th align="center">
	                   <input type="button" value="删 除"  onclick="DeleteSomeLine(Search_ViewReader_Form)" />
                 
                 </th>
                  <th   align="center">
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0"> 
	                 	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>  
	                      <input type="button"  value="打印预览"  onclick="javascript:window.open('editViewReaderPrint.jsp')">
	                 </logic:notEqual>
                 </th>
              
	            <th  align="center"> 
                <logic:equal name="listCount" value="0">
						<input type="button" value="导出excel" disabled>
					</logic:equal>
					<logic:notEqual name="listCount" value="0">
						<input type="button" value="导出excel" onclick="OpenExcel()">
					</logic:notEqual>
				</th>
              </tr>
               </table><br/>
               <div id = "printdiv">
         <TABLE width="1500" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" >
          <TR>
             <TD width="150" align="left" bgcolor="#B0C4DE">分站号<input type="checkbox" name="checkall" value="checkbox" onClick="CheckAll(Search_ViewReader_Form);" >(全选)</TD>
             <TD width="124"  align="left" bgcolor="#B0C4DE">分站名</TD>
             <TD width="70" align="left" bgcolor="#B0C4DE">简称</TD>
              <TD width="84"  align="left" bgcolor="#B0C4DE">地图X坐标</TD>
             <TD width="84"  align="left" bgcolor="#B0C4DE">地图Y坐标</Td>
             <TD width="84"  align="left" bgcolor="#B0C4DE">地图Z坐标</Td>
             <TD width="210" align="left" bgcolor="#B0C4DE">注册时间</TD>
             <TD width="80"  align="left" bgcolor="#B0C4DE">地图号</TD>
              <TD width="70"  align="left" bgcolor="#B0C4DE">忽略中断</TD>
             <TD width="70"  align="left" bgcolor="#B0C4DE">指定定位器</TD>
             <TD width="70"  align="left" bgcolor="#B0C4DE">定位器名称</TD>
             <TD width="80"  align="left" bgcolor="#B0C4DE">指定忽略中断</TD>
             <TD width="70"  align="left" bgcolor="#B0C4DE">使用状态</TD>
             <%-- <TD width="150" align="left" bgcolor="#B0C4DE">最后修改时间</TD>--%>
             <TD width="70"  align="left" bgcolor="#B0C4DE">设备性质</TD>
             <TD width="70"  align="left" bgcolor="#B0C4DE">验卡分站</TD>
             <TD width="70"  align="left" bgcolor="#B0C4DE">使用天线</TD>
             <TD width="70"  align="left" bgcolor="#B0C4DE">声音报警</TD>
           </TR>
         <logic:present name="ViewReaderList" scope="request">
            <logic:iterate name="ViewReaderList" id="vr">
                  <TR> 
                      <TD align="left" bgcolor="#E6E6FA">
                        <input type="checkbox" name="id"  value="<bean:write name='vr' property='cardreaderid'/>" />
                       <a href="operartrViewReader.do?method=load&cid=<bean:write name='vr' property='cardreaderid'/>&rid=<bean:write name='vr' property='recordid' 
                                       />"  class="A:link"  title="修改<bean:write name="vr" property="cardreaderid" /> 号分站信息" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" >
                         <bean:write name="vr" property="cardreaderid" /></a>  
                      </TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="crname" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="shortname" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="x" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="y" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="z" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="regdate" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="mapid" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="ignoretimes" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="ignorelocator" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="sname" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="locatorignoretimes" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="state" /></TD>
                     <%-- <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="modifydate" /></TD>--%>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="ground" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="checkreader" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="useantenna1" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="ifalarm" /></TD>
                  </TR>  
            </logic:iterate>
         </logic:present>
         <logic:present name="pagination" >
               <TR>
		           <TD colspan="17" align="center" bgcolor="#E6E6FA">
		                  <page:pagination path="editViewReader.do?method=getList" parameter="page"  formName="Search_ViewReader_Form" />
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
	}if("<%=name3%>"=="true"){
	   	document.getElementById("name3").checked=true;
	   	document.getElementById("file3").disabled=false;
	}if("<%=name4%>"=="true"){
	   	document.getElementById("name4").checked=true;
	   	document.getElementById("file4").disabled=false;
	}
</script>