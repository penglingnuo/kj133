
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>routDetail_right_init.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <style TYPE="text/css">
		<!--
		 A:link{color: #6699CC;text-decoration:none}
		 A:hover{color:#FF0000;}
		 -->
    </style>
    <SCRIPT language="javascript">
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
				     location.href="routDetail_right.do?method=delete&routeorder="+str.substring(1);   
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
        function add(){
          window.parent.frames['right_downFrame'].location.href='routDetail_right_add.jsp'
        }
    </SCRIPT>
  </head>
  <body bgColor="white" background="Image/right.gif">


  
    
     <logic:messagesPresent  message="true">
		  <html:messages id="message" message="true" > 
		       <font color="red"> <bean:write name="message" /></font>
		   </html:messages>
     </logic:messagesPresent>
     <table width="200">
     <td>
    <input type="button" value="增加经过地点" onclick="add()">&nbsp;&nbsp;&nbsp;
    </td>
    <td>
    <input type="button" value="删 除" onclick="DeleteSomeLine(Search_routDetail_right_Form)" />
    </td>
    </table>
    <html:form action="/routDetail_right" >
	      <table cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0"  width="410">
		       <tr>
		          <td width="100"  align="left" bgcolor="#B0C4DE">顺序&nbsp;<input type="checkbox" name="checkall" value="checkbox" onClick="CheckAll(Search_routDetail_right_Form);" >(全选)</td>
		          <td width="80"   align="left" bgcolor="#B0C4DE">地点类型</td>
		          <td width="30"   align="left" bgcolor="#B0C4DE">ID</td>
		          <td width="180"  align="left" bgcolor="#B0C4DE">名称</td>
		       </tr>
		       <logic:present name="check">
		            <logic:iterate name="check" id="check">
		              <tr>
		                  <td bgcolor="#E6E6FA">
		                      <a href="routDetail_right.do?method=load&routeorder=<bean:write name='check' property='routeorder' />" title="修 改 地 点 设 置"  onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link" target="right_downFrame">
		                      <input type="checkbox" name="id"  value="<bean:write name='check' property='routeorder'/>" /><bean:write name="check" property="routeorder" /></a>
		                  </td>
		                  <td bgcolor="#E6E6FA"><bean:write name="check" property="type" /></td>
		                  <td bgcolor="#E6E6FA"><bean:write name="check" property="pointid" /></td>
		                  <td bgcolor="#E6E6FA"><bean:write name="check" property="shortname" /></td>
		              </tr>
		            </logic:iterate>
		       </logic:present>
	      </table>
     </html:form>
  </body>
</html:html>
