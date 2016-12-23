<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="编辑员工信息" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>Employee.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
     <script language="JavaScript">
		  document.oncontextmenu=new Function("event.returnValue=false;"); 
    </script> 
    <script language="javascript" src="js/page.js"></script>
    
    <script language="javascript" src="js/StafferORName.js"></script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    
    <style TYPE="text/css">
	  <!--
		  A:link{color:#FF0000;text-decoration:none} /*   未访问的链接   */ 
		  A:hover{color:#FF0000;text-decoration:none} /*   鼠标在链接上   */
		  A:visited{color:#FF0000;text-decoration:none}/*   已访问的链接   */ 
	  -->
    </style>
    <script type="text/javascript">
        function add(){
            window.location.href="operatorEmployee.do?method=initializerator";
        }
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
				     location.href="employee.do?method=delete&sid="+str.substring(1);   
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
			
			function OpenExcel(){
			  window.parent.location.href="employee.do?method=doOpenExcel";
			     return true;
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
  
  <body bgColor="white" background="Image/right.gif"><br>
       <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
       </logic:messagesPresent>
      <html:form action="/employee?method=getStaffer">
        <table>
            <tr>
	            <th>员工：</th>
	            <th>
	                <html:text property="search_employee.cardid" size="10" maxlength="6"  styleId="names2" onkeyup="findNames2();"   />
	                  <div style="position:absolute;" id="popup2">
				        <table id="name_table2" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="name_table_body2"></tbody>
				        </table>  
                      </div>
	            </th>
	            <th>&nbsp;</th>
	            <th>工&nbsp;种：</th>
	            <th>
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
                 <html:text property="search_employee.worktype" style="width:82px;position:absolute;left:0px;"></html:text>
				</div> 
                 </th>
	            
	            <th>&nbsp;</th>
	            <th> 部&nbsp;门：</th>
	            <th>
                 <div style="position:relative;">
                 <span style="margin-left:82px;width:18px;overflow:hidden;">
                 <select  style="width:118px;margin-left:-100px" onchange="this.parentNode.nextSibling.value=this.value">
                 <logic:present name="dep_list">
              		<option value=""></option>
                 	<logic:iterate id="aa" name="dep_list">
                 		<option value='<bean:write name="aa" property="wordvalue"/>'><bean:write name="aa" property="wordvalue"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
                 <html:text property="search_employee.depart" style="width:82px;position:absolute;left:0px;"></html:text>
				</div> 
                 </th>
	            <th>&nbsp;</th>
	            <th>
	                 <html:submit>查 询</html:submit>&nbsp;
			         <input type="button" value="增 加"  onClick="add()">&nbsp;
			         <input type="button" value="删 除"  onclick="DeleteSomeLine(Search_Employee_Form)"  >
			         <logic:equal  name="listCount" value="0">
			              &nbsp;<input type="button"  value="打印预览"  disabled>
			         </logic:equal>
			         <logic:notEqual name="listCount" value="0">
			         	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
			             &nbsp;<input type="button"  value="打印预览"  onclick="javascript:window.open('employeePrint.jsp')">
			         </logic:notEqual>
	            </th>
	            <th>&nbsp;</th>
	            <th>
                <logic:equal name="listCount" value="0">
						<input type="button" value="导出excel" disabled>
					</logic:equal>
					<logic:notEqual name="listCount" value="0">
						<input type="button" value="导出excel"
							onclick="OpenExcel()">

					</logic:notEqual>
				</th>
            </tr>
        </table><br>   
           <div id = "printdiv">
             <TABLE   width="730" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
		        <TR>
			          <TD align="left"  width="105"  bgcolor="#B0C4DE">员工号<input type="checkbox" name="checkall" value="checkbox" onClick="CheckAll(Search_Employee_Form);" >(全选)</TD>
			          <TD align="left"  width="60"   bgcolor="#B0C4DE">卡号</TD>
			          <TD align="left"  width="70"   bgcolor="#B0C4DE">姓名</TD>
			          <TD align="left"  width="80"  bgcolor="#B0C4DE">部门</TD>
			          <TD align="left"  width="98"   bgcolor="#B0C4DE">班组</TD>
			          <TD align="left"  width="68"   bgcolor="#B0C4DE">工种</TD>
			          <TD align="left"  width="80"   bgcolor="#B0C4DE">职务</TD>
			          <TD align="left"  width="100"  bgcolor="#B0C4DE">到职日期</TD>
		        </TR>
		        <logic:present name="stafferList" scope="request">
                    <logic:iterate id="stafferList" name="stafferList"> 
               			 <TR>
                              <TD bgcolor="#E6E6FA">
                                  <input type="checkbox" name="id"  value="<bean:write name='stafferList' property='stafferid'/>" />
                                   <a href="loadEmployee.do?method=load&cid=<bean:write name='stafferList' property='cardid'/>&sid=<bean:write name='stafferList' property='stafferid' />" title="修 改 员 工 号 为 <bean:write name='stafferList' property='stafferid'/> 的 员 工 信 息"  onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link">
                                  <bean:write name="stafferList" property="stafferid" /></a>  
                              </TD>
                              <logic:equal name="stafferList" property="cardid" value="">
                                 <TD bgcolor="#E6E6FA"></TD>
                              </logic:equal>
                              <logic:notEqual name="stafferList" property="cardid" value="">
                                   <TD bgcolor="#E6E6FA"><bean:write name="stafferList" property="cardid" /></TD>
                              </logic:notEqual> 
				              <TD bgcolor="#E6E6FA"><bean:write name="stafferList" property="username" /></TD>
		                      <TD bgcolor="#E6E6FA"><bean:write name="stafferList" property="department" /></TD>
				              <TD bgcolor="#E6E6FA"><bean:write name="stafferList" property="gro" /></TD>
				              <TD bgcolor="#E6E6FA"><bean:write name="stafferList" property="worktype" /></TD>
				              <TD bgcolor="#E6E6FA"><bean:write name="stafferList" property="occupation" /></TD>
				              <TD bgcolor="#E6E6FA"><bean:write name="stafferList" property="jointime" /></TD>
               			  </TR>
		              </logic:iterate>
                 </logic:present>
                <logic:present name="pagination" >
                   <TR>
					  <TD colspan="8" align="center" bgcolor="#E6E6FA">
					          <page:pagination path="employee.do?method=getStaffer" name="pagination"  parameter="page"  formName="Search_Employee_Form"/>
					  </TD>  
                    </TR> 
                </logic:present> 
	        </TABLE></div>    
       </html:form>
  </body>
</html:html>
