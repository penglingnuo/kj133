
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="区域设置-二级区域设置"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>page_range_downleftaddarea.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>

    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript">
    
    			function check()
			{
			  var name=document.all['ser_pagerangetopleft.areaname'].value;
			  var type=document.all['ser_pagerangetopleft.areatype'].value;
			  
			  if( name == ""){
			      alert('区域名称不能为空');
			      document.all['ser_pagerangetopleft.areaname'].focus();
			      return false;
			  }if( type == ""){
			      alert('区域类型不能为空');
			      document.all['ser_pagerangetopleft.areatype'].focus();
			      return false;
			  }			  
			  return true;
			}
			
			function CheckAll(form)
			{
				for (var i=0;i<form.elements.length;i++){
					var e = form.elements[i];
					if (e.name == 'checkId')
					e.checked = form.checkall.checked;
				}
			}
			
			function DeleteSomeLine(form)
			{
				var v1 = document.getElementById("idd").value;
				
			    if (SelectedCounts(form,"checkId")>0){
				    if(confirm("删除已选中的记录,将无法恢复！\n     确定要这样做吗？"))
				    {
				     var str="";
				     var init=document.getElementsByName('checkId');
				     for(var i=0;i<init.length;i++)
				       {
				         if(init[i].checked)
				         {
				             str=str+','+init[i].value;    
				         }
				       }
				     //document.getElementById("frm").action="aaddcheck_log.do?method=delete&lid=''";
				     //document.getElementById("frm").submit();
				    // location.href="page_range_topleft.do?method=delete&lid="+str;   
				     window.parent.frames['topright'].location.href="page_range_topleft.do?method=delete&lid="+str;   
					 return true;
				    }
				   }else{
					    alert("请先选择要删除的记录!");
					    return false;
				    }
			}
			function addSomeLine(form)
			{
				var v1 = document.getElementById("idd").value;
				
			    if (SelectedCounts(form,"checkId")>0){
				   
				     var str="";
				     var init=document.getElementsByName('checkId');
				     for(var i=0;i<init.length;i++)
				       {
				         if(init[i].checked)
				         {
				             str=str+','+init[i].value;    
				         }
				       }
				     
				     window.parent.frames['topright'].location.href="page_range_topleft.do?method=add2&lid="+str;   
					 return true;
				    
				   }else{
					    alert("请先选择要添加的记录!");
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
			
	</script>		

  </head>
  
  <body bgColor="white" background="Image/right.gif">
         <logic:messagesPresent message="true">
			  <html:messages id="message" message="true">
			     <font color="red"><bean:write name="message" /></font>
			  </html:messages>
       </logic:messagesPresent>
        <!-- message -->
     <html:form action="/page_range_topleft?method=update" target="topright"  focus="ser_pagerangetopleft.areaname">
     <td ><font size="2" color="red">获取二级区域范围:</font></td>
     <table >

	  <TR>
                <TD colspan="4" align="center">
                   
                    <%--
                    <html:submit>增 加</html:submit>&nbsp;&nbsp;
                    <a href="javascript:if(confirm('确实要删除吗?'))location='page_range_topleft.do?method=delete&areaname1=<%=request.getAttribute("areaname") %>'" target="topright"><IMG src="Image/delete.BMP" border="0" align="absmiddle"/></a>
                --%>
                <input type="button" value="获取二级区域范围"  onclick="addSomeLine(Search_PageRangeTopleft_Form)" />&nbsp;&nbsp;
                <%--<input type="button" value="删 除"  onclick="DeleteSomeLine(Search_PageRangeTopleft_Form)" />--%>
                </TD>
             </TR>  
	 </table>
	 	      <table cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
	        <tr>
	              
	              <td width="125" align="left" bgcolor="#B0C4DE">地点类型<input type="checkbox" name="checkall" value="checkbox" onClick="CheckAll(Search_PageRangeTopleft_Form);" >(全选)</td>
	              <td width="65" align="left" bgcolor="#B0C4DE">名称</td>
	              <td width="65" align="left" bgcolor="#B0C4DE">ID</td>
           </tr>
	        <logic:present name="list">
	             <logic:iterate name="list" id="list">
	               <tr>
	               
	                 <td bgcolor="#E6E6FA">
	                 <input type="checkbox" name="checkId" id="idd" value="<bean:write name='list' property='type'/>+$+<bean:write name="list" property="id" />" />

	                       <bean:write name="list" property="type" />     
	                 </td>
	                 <td bgcolor="#E6E6FA"><bean:write name="list" property="shortname" /></td>	                 
	                 <td bgcolor="#E6E6FA"><bean:write name="list" property="id" /></td>
                   </tr>
	             </logic:iterate>
	        </logic:present>
	      </table>      

      </html:form>
  </body>
</html:html>
