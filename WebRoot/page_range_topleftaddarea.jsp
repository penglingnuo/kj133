
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page info="区域设置-区域范围设置"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>page_range_downleft.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>

    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript">
    
    		function check()
			{
			  var name=document.all['ser_pagerangetopleft.zuhe'].value;
			  
			  if( name == ""){
			      alert('区域范围不能为空');
			      document.all['ser_pagerangetopleft.zuhe'].focus();
			      return false;
			  }			  
			  return true;
			}
			
			function del()
			{
			
				var v1 = document.getElementById("ser_pagerangetopleft.zuhe").value;
				if (confirm("确定要删除吗？"))
				
				window.parent.frames['topright'].location.href="page_range_topleft.do?method=deletetype&zuhe1="+v1;
				
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
     <html:form action="/page_range_topleft?method=addtype" target="topright" onsubmit="return check()" focus="ser_pagerangetopleft.areaname" method="post">
     <td ><font size="2" color="red">区域范围设置:</font></td>
     <table >

     <tr>
           <td>地点类型 ： ID ： 名称 </td>
     </tr>
     <tr>
           <td>
            <html:select  property="ser_pagerangetopleft.zuhe" style="width:150px">
               
               <html:options collection="zuhe_list" property="zuhe" labelProperty="zuhe"/>
            
             </html:select>
            </td>
      </tr>     

	  <TR>
                <TD colspan="4" align="center">
                   <html:submit>增 加</html:submit>&nbsp;&nbsp;
                    <%--<a href="javascript:if(confirm('确实要删除吗?'))location='page_range_topleft.do?method=deletetype&zuhe1=<%=zuhe1%>'"  target="topright"><IMG src="Image/delete.BMP" border="0" align="absmiddle"/></a>--%>
                    
                    <a href="javascript:del();"><IMG src="Image/delete.BMP" border="0" align="absmiddle"/></a>
                </TD>
             </TR>  
	 </table>      

      </html:form>
  </body>
</html:html>
