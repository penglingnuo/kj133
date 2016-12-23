
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="编辑定位器" %>
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
    
    <title>locator.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
 
    <script language="javascript" src="js/page.js"></script>
    <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <script language="JavaScript" src="js/Locator.js"></script>
    <link   href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
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
    <SCRIPT language="JavaScript">
      function check()
       {
         var stime=document.all['ser_locator.stime'].value;
         var etime=document.all['ser_locator.etime'].value;
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
       function add()
       {
         location.href="addLocalizer.jsp";
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
    <script language="javascript">
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
				     location.href="operator_locator.do?method=delete&lid="+str.substring(1);   
					 return true;
				    }
				   }else{
					    alert("请先选择要删除的记录!");
					    return false;
				    }
			}
			//扫描已选复选框的个数
			//参数:表单名,元素名
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
			
			function OpenExcel(){
			  window.parent.location.href="localizer.do?method=doOpenExcel";
			     return true;
		    }
		</script>
  </head>
  
  <body bgColor="white" background="Image/right.gif"  > 
	    <logic:messagesPresent message="true">
	        <html:messages id="message" message="true">
	        <font color="red"><bean:write name="message" /></font>
	        </html:messages>
		</logic:messagesPresent>
    <html:form action="/localizer?method=getlist" onsubmit="return check()">
       <table width="750">
       <tr>
              <th align="left">注册日期大于:</th>
                 
                 <th align="left">
                 <html:text property="ser_locator.stime" size="9" styleId="file1" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_locator.stime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name1" onclick="this.checked?file1.disabled=false:file1.disabled=true" />&nbsp;
                 
                 </th><th align="left">时间大于:</th>
                 <th align="left">
                 <html:text property="ser_locator.minstime" size="8" styleId="file2" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_locator.minstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name2" onclick="this.checked?file2.disabled=false:file2.disabled=true" />&nbsp;
                 </th>
                 <th align="left">定位器：</th>
                 <th align="left">
                  <html:text property="ser_locator.locatorid" size="15" styleId="names4" onkeyup="findNames4();"  />
                   <div style="position:absolute;" id="popup4">
				        <table id="name_table4" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="name_table_body4"></tbody>
				        </table>  
                  </div>
                 </th>
                 <th><html:submit>查 询</html:submit></th>
                
              
              </tr>
              <tr>
              <th align="left">注册日期小于:</th>
                 
                 <th align="left">
                 <html:text property="ser_locator.etime" size="9" styleId="file3" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_locator.etime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name3" onclick="this.checked?file3.disabled=false:file3.disabled=true" />&nbsp;
                 
                 </th><th align="left">时间小于:</th>
                 <th align="left">
                 <html:text property="ser_locator.maxstime" size="8" styleId="file4" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_locator.maxstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name4" onclick="this.checked?file4.disabled=false:file4.disabled=true" />&nbsp;
                 </th>
                 
                 <th>
                 <input type="button" value="增 加"  onclick="add()">&nbsp;
                 </th>
                 <th align="center"> 
	                  <input type="button" value="删 除"  onclick="DeleteSomeLine(Search_locator_Form)" />&nbsp; 
                 </th>
                  <th   align="center">
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0">  
	                 	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                      <input type="button"  value="打印预览"  onclick="javascript:window.open('localizerPrint.jsp')">
	                 </logic:notEqual>
	                 &nbsp;
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
             <%--<tr>
                 <th>注册日期大于： </th>
                 <th>
                     <html:text property="ser_locator.stime"  size="18"  onfocus="show_cele_date('','',Search_locator_Form['ser_locator.stime'])" />
                 </th>
                 <th>&nbsp;</th>
                 <th align="left">
                          定位器：
                       <html:text property="ser_locator.locatorid" size="28"  styleId="names4" onkeyup="findNames4();"   />
                        <div style="position:absolute;" id="popup4">
					        <table id="name_table4" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
					            <tbody id="name_table_body4"></tbody>
					        </table>  
                        </div>
                 </th> 
              </tr>
              <tr>
                 <th>注册日期小于： </th>
                 <th>
                    <html:text property="ser_locator.etime"  size="18"  onfocus="show_cele_date('','',Search_locator_Form['ser_locator.etime'])" />    
                 </th>
                 <th>&nbsp;</th>
                 <th> 
	                  <html:submit  value="查 询" />&nbsp; 
	                    <input type="button" value="增 加"  onclick="add()">&nbsp; 
	                  <input type="button" value="删 除"  onclick="DeleteSomeLine(Search_locator_Form)" />&nbsp; 
	                  <logic:equal  name="listCount" value="0">
	                       <input type="button"  value="打印预览"  disabled>
		             </logic:equal>
		             <logic:notEqual name="listCount" value="0">  
		                   <input type="button"  value="打印预览"  onclick="javascript:window.open('localizerPrint.jsp')">
		             </logic:notEqual>
                </th> 
       --%></table><br/>
       <div id = "printdiv">
           <table width="1000"  cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" >
             <tr>
	           <td width="150" align="left"  bgcolor="#B0C4DE">定位器号&nbsp;<input type="checkbox" name="checkall" value="checkbox" onClick="CheckAll(Search_locator_Form);" >(全选)</td>
	           <td width="160"  align="left"  bgcolor="#B0C4DE">定位器名</td>
	           <td width="160" align="left"  bgcolor="#B0C4DE">简称</td>
	           <td width="84"  align="left"	 bgcolor="#B0C4DE">地图X坐标</td>
	           <td width="84"  align="left"  bgcolor="#B0C4DE">地图Y坐标</td> 
	           <td width="84"  align="left"  bgcolor="#B0C4DE">地图Z坐标</td> 
	           <td width="210" align="left"  bgcolor="#B0C4DE">注册时间</td>
	           <td width="58"  align="left"  bgcolor="#B0C4DE">地图号</td>
	           <td width="70"  align="left"  bgcolor="#B0C4DE">设备性质</td>
	           <td width="140" align="left"  bgcolor="#B0C4DE">使用状态</td>
           </tr>
		    
          <logic:present name="loactor">
             <logic:iterate name="loactor" id="loactor">
                <tr>
                  <td align="left" bgcolor="#E6E6FA">
                        <input type="checkbox" name="id"  value="<bean:write name='loactor' property='locatorid'/>" />
                             <a href="operator_locator.do?method=load&lid=<bean:write name='loactor' property='locatorid' 
                             />&rid=<bean:write name='loactor' property='recordid' />"   class="A:link" title="修 改 <bean:write name="loactor" property="locatorid" /> 号 定 位 器 信 息" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" >
                             <bean:write name="loactor" property="locatorid" /> </a>  
                  </td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="loactor" property="lname" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="loactor" property="shortname" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="loactor" property="x" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="loactor" property="y" /></td>  
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="loactor" property="z" /></td>  
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="loactor" property="regdate" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="loactor" property="mapid" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="loactor" property="ground" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="loactor" property="state" /></td>
                </tr>
             </logic:iterate>
          </logic:present>
               <logic:present name="pagination" >
	               <tr>
			          <td colspan="11" align="center" bgcolor="#E6E6FA">
			               <page:pagination path="localizer.do?method=getlist" parameter="page" formName="Search_locator_Form" />
			          </td>  
	              </tr> 
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