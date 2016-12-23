<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>transitTrcticLeft.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <style TYPE="text/css">
		<!--
		  A:link{color:#FF0000;text-decoration:none} /*   未访问的链接   */ 
		  A:hover{color:#FF0000;text-decoration:none} /*   鼠标在链接上   */
		  A:visited{color:#FF0000;text-decoration:none}/*   已访问的链接   */ 
		 -->
    </style>
    <script type="text/javascript">
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
				     location.href="transitTrctic.do?method=delete&cid="+str.substring(1);   
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
             window.parent.frames['mainFrame'].location.href="transitTrctic.do?method=load";
         }
    </script>
  </head>
  
  <body bgColor="white" background="Image/right.gif">
       <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     <html:form action="transitTrctic?method=bad">
      <td ><font size="2" color="red">注意:</font><font size="2" color="blue">在同一个分站上设置的新策略将会自动覆盖旧策略</font></td>
      <br />
       <td >通行策略：</td>
      <table width="153" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
          <tr>
            <td  align="left" bgcolor="#B0C4DE">标题<input type="checkbox" name="checkall" value="checkbox" onClick="CheckAll(Search_TransitTrctic_Form);" >(全选)</td>
          </tr>
	      <logic:present name="captionList" scope="request">
              <logic:iterate name="captionList" id="ca">
                 <tr>
                     <td align="left" bgcolor="#E6E6FA">
                       <input type="checkbox" name="id"  value="<bean:write name='ca' property='operationnumber'/>" />
                       
                       <a href="transitTrctic.do?method=showInfo1&number=<bean:write name='ca' property='operationnumber' />&caption=<bean:write name='ca' property='caption' />" target="mainFrame" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link">
                         <bean:write name="ca" property="caption"/>
                       </a>
                     </td>
                 </tr>  
              </logic:iterate>
	      </logic:present>
      </table>
      
      <table>
         <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
         </tr>
         <tr>
            <td align="left"><input type="button" value="新 增" onClick="add()">&nbsp;&nbsp;</td>
            <td><input type="button" value="删 除" onclick="DeleteSomeLine(Search_TransitTrctic_Form)" ></td>
         </tr>
      </table>
      </html:form>
  </body>
</html:html>
