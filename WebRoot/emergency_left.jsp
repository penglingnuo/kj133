
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
    <script type="text/javascript" src="js/jquery.js"></script>
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
					if (e.name == 'name')
					e.checked = form.checkall.checked;
				}
			}
		 
		   function DeleteSomeLine(form)
		    {
			   if (SelectedCounts(form,"name")>0){
				    if(confirm("删除已选中的记录,将无法恢复！\n     确定要这样做吗？"))
				    {
				     var str="";
				     var init=document.getElementsByName('name');
				     for(var i=0;i<init.length;i++)
				       {
				         if(init[i].checked)
				             str+=(','+init[i].value);       
				       }
				     location.href="emergencyleft.do?method=delete&name="+str.substring(1);   
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
			   window.parent.frames['topFrame'].location.href="telezone/EscapeLine.jsp";
			  return false;
			}
	</script>
	<script type="text/javascript">
		$.ajax({
			type: "POST",
			url: "indexright.do?method=maplist&tempId=" + Math.random() * 100000,
			dataType: "text",
			error: function(){
			},
			beforeSend : function(){
			},
			success: function(msg){
				var json = eval('('+ msg +')'); 
				
				$("#maplist option").remove();
				
				$(json.maplist).each(function(i, n){
					$("#maplist").append("<option value='" + n.mapid + "'>" + n.mapname + "</option>"); 
				});
			}
		});
		
		$(function(){
			$("#maplist").change(function(){
				window.parent.frames['topFrame'].getMap(""+$(this).val()+"");
			});
			
			$("td[show]").each(function(i, n){
				var show = parseInt($(this).attr("show"), 10);
				if(show == 0) {
					$(this).text("不显示");
				}else {
					$(this).text("显示");
				}
			});
		});
		
		function getName() {
			var name = $("#map_name").val();
			return name;
		}
		function getInfo() {
			var info = $("#map_explain").val();
			return info;
		}
		function getIsShow() {
			if($("#isShow").attr("checked")) {
				return "1";
			}else{
				return "0";
			}
		}
		
		function a_click(obj) {
			$("#showtable").find("input[type='button']").attr("disabled", "disabled");
			
			$("tr[name='"+obj+"']").find("input[type='button']").removeAttr("disabled");
			
			$("#map_name").val(obj);
			
			$("#map_explain").val($("tr[name='"+obj+"']").find("td").eq(1).text());
			
			var show = parseInt($("tr[name='"+obj+"']").find("td").eq(2).attr("show"), 10);
			if(show == 1) {
				$("#isShow").attr("checked", "checked");
			}else {
				$("#isShow").removeAttr("checked");
			}
		}
		
		function modify(name) {
			$.ajax({
				type: "POST",
				url: "escapeline.do?method=modify&tempId=" + Math.random() * 100000,
				data: "n=" + getName() + "&i=" + getInfo() + "&s=" + getIsShow() + "&on=" + name,
				dataType: "text",
				error: function(){
				},
				beforeSend : function(){
				},
				success: function(msg){
					var json = eval('('+ msg +')'); 
					
					if(json.suc == "-1") {
						alert("名字重复");
						return false;
					}else {
						alert("修改成功");
						window.parent.location.reload();
					}
				}
			});
		}
	</script>
  </head>
  
  <body bgColor="white" background="Image/right.gif" >
  
      <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
  
     <html:form action="/emergencyleft?method=getList">
        
         <td ><font size="2" color="red">说明:</font><font size="2" color="blue">该图为避灾路线图，即在图中会标示出紧急情况出现时逃生的路线。</font></td>
      <br />
         <TABLE width="300">
         
          <TR>
              <TD>路线名称：</TD>
              <TD>
                   <input id="map_name" name="ser_emergencyLeft.map_name" size="10"/>&nbsp;&nbsp;
                     <html:submit>查 询</html:submit>&nbsp;&nbsp; 
                   <input type="button" value="删 除"   onclick="DeleteSomeLine(Search_emergencyLeft_Form)" />
                    
                  
              </TD>
            </TR>
            <TR>
              <TD>路线说明：</TD>
              <TD><input id="map_explain" name="ser_emergencyLeft.map_explain" size="20" />&nbsp;&nbsp;&nbsp;
              <input type="button" value="增 加"   onclick="add()" />
                                      
              </TD>
            </TR>
            <TR>
              <TD>地图：</TD>
              <TD>
                  <select id="maplist"></select>                    
              </TD>
            </TR>
            <TR>
              <TD>是否显示：</TD>
              <TD>
                  <input type="checkbox" id="isShow" value="1">
              </TD>
            </TR>
            
           
          </TABLE>
          
          <br/>
          <%--
          <TABLE width="360" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
          --%>
          <TABLE width="100%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" id="showtable">
             <TR>
                <TD width="110"  align="left" bgcolor="#B0C4DE">路线名称<input type="checkbox" name="checkall" value="checkbox" onClick="CheckAll(Search_emergencyLeft_Form);">(全选)</TD>
                <TD width="200"  align="left" bgcolor="#B0C4DE">路线说明</TD>        
                <TD width="200"  align="left" bgcolor="#B0C4DE">地图显示</TD>        
                <TD width="50"  align="left" bgcolor="#B0C4DE">修改</TD>        
             </TR>
            <logic:present name="emergency_list">
              <logic:iterate name="emergency_list" id="list">
                   <TR name="<bean:write name='list' property='pathname'/>" onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
	                  <TD bgcolor="#E6E6FA">
	                    <input type="checkbox" name="name"  value="<bean:write name='list' property='pathname'/>" />
	                    <a href="escapeline.do?method=getLine&n=<bean:write name='list' property='pathname' />"  class="A:link" title="修 改 <bean:write name='list' property='pathname' /> 线路指示图" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" onclick="a_click('<bean:write name='list' property='pathname'/>')" target="topFrame" >
                         <bean:write name="list" property="pathname" /></a>  
	                   </TD>
	                  <TD bgcolor="#E6E6FA"><bean:write name="list" property="pathinfo" /></TD>
	                  <TD bgcolor="#E6E6FA" n="<bean:write name='list' property='pathname'/>" show="<bean:write name="list" property="show" />"></TD>
	                  <TD bgcolor="#E6E6FA" n="<bean:write name='list' property='pathname'/>">
	                  	<input type="button" value="修改" onclick="modify('<bean:write name='list' property='pathname'/>')" disabled="disabled">
	                  </TD>
                  </TR>
              </logic:iterate>
            </logic:present> 
          </TABLE>
     </html:form>
  </body>
</html:html>
