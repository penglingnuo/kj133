
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>map_left.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <style TYPE="text/css">
		<!--
		  A:link{color:#FF0000;text-decoration:none} /*   未访问的链接   */ 
		  A:hover{color:#FF0000;text-decoration:none} /*   鼠标在链接上   */
		  A:visited{color:#FF0000;text-decoration:none}/*   已访问的链接   */ 
		 -->
    </style>
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
				     location.href="mapleft.do?method=delete&id="+str.substring(1);   
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
			function add(){
			   window.parent.frames['topFrame'].location.href="map_add.jsp";
			  return false;
			}
	</script>
  </head>
  
  <body bgColor="white" background="Image/right.gif" >
      <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
  
     <html:form action="/mapleft?method=getList">
         <TABLE width="360">
            <TR>
              <TD>地图说明：</TD>
              <TD><html:text  property="ser_mapLeft.map_explain" size="25" />&nbsp;&nbsp;
                 <input type="button" value="增 加"   onclick="add()" />    
              </TD>
            </TR>
            
            <TR>
              <TD>地图名称：</TD>
              <TD>
                   <html:text  property="ser_mapLeft.map_name" size="16"/>&nbsp;
                   <html:submit>查 询</html:submit>&nbsp;&nbsp; 
                   <input type="button" value="删 除"   onclick="DeleteSomeLine(Search_MapLeft_Form)" />
                  
              </TD>
            </TR>
          </TABLE><br/>
          
          <TABLE width="360" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
             <TR>
                <TD width="110"  align="left" bgcolor="#B0C4DE">地图号<input type="checkbox" name="checkall" value="checkbox" onClick="CheckAll(Search_MapLeft_Form);">(全选)</TD>
                <TD width="250"  align="left" bgcolor="#B0C4DE">地图名称</TD>        
             </TR>
            <logic:present name="map_list">
              <logic:iterate name="map_list" id="list">
                   <TR onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
	                  <TD bgcolor="#E6E6FA">
	                    <input type="checkbox" name="id"  value="<bean:write name='list' property='mapid'/>" />
	                    <a href="loadMap.do?method=load&mid=<bean:write name='list' property='mapid' />"  class="A:link" title="修 改 <bean:write name='list' property='mapid' /> 号 地 图 信 息" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" target="topFrame" >
                         <bean:write name="list" property="mapid" /></a>  
	                   </TD>
	                  <TD bgcolor="#E6E6FA"><bean:write name="list" property="mapname" /></TD>
                  </TR>
              </logic:iterate>
            </logic:present> 
          </TABLE>
     </html:form>
  </body>
</html:html>
