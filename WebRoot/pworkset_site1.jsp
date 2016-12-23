
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page info="人员工作设置-地点设置" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>pworkset_site.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script language="javascript" src="js/page.js"></script>
    <script language="JavaScript" src="js/calendar.js"></script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/CardIdORName.js"></script>

        <style TYPE="text/css">
		<!--
		 A:link{color: #6699CC;text-decoration:none}
		 A:hover{color:#FF0000;}
		 -->
    </style>

    <%--
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
    
  --%>
	    <script language="javascript">
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
				     
				     //window.parent.frames["arightFrame"].location.href="pworkset.do?method=deleteSite&lid="+str;   
				    
				     window.location.href="pworkset.do?method=deleteSite1&lid="+str;   
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
		</script>
  </head>
  
  <body bgColor="white" background="Image/right.gif" >
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>

    <html:form action="/pworkset?method=addSite" target="arightFrame"  focus="pworkset.zuhe" >
    <td ><font size="2" color="red">地点设置--指定时间到达指定地点:</font></td>
    <br />
    <td><font size="2" color="blue">人员：null</font></td>
    <td>&nbsp;&nbsp;</td>
    <td><font size="2" color="blue">工种：null</font></td>
    <table width="320">
    <%--
    <html:hidden property="sid" value="<%=sid%>"/>
    <html:hidden property="name" value="<%=name%>"/>
    <html:hidden property="type" value="<%=type%>"/>
    
    <html:hidden property="name" value="<%=request.getAttribute("name") %>"/>
    <html:hidden property="type" value="<%=request.getAttribute("type") %>"/>
    --%>
    <tr>
           <td >地点类型 ：ID ：名称</td>
           <td>
            <html:select  property="pworkset.zuhe" style="width:150px">
               
               <html:option value="" ></html:option>
               <html:options collection="zuhe_list" property="zuhe" labelProperty="zuhe"/>
            
             </html:select>
            </td>
           
           
    </tr>
    <tr>
            <td align="right">指定时间(分)</td>
            <td><html:text property="pworkset.appointminute" size="5" maxlength="3" />
            &nbsp;&nbsp;
            <html:submit disabled="true">增 加</html:submit>&nbsp;&nbsp;
            <input type="button" value="删 除" onclick="DeleteSomeLine(Search_Pworkset_Form)" />        
            <%--
            <a href="javascript:Del();" ><IMG src="Image/delete.BMP" border="0" align="absmiddle"/></a>
            --%>
            </td>
            
                   
    </tr>
    
      </table>
      <br />
    <table  width="400" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
              
               <tr>
                   <td width="120"  align="left" bgcolor="#B0C4DE" >姓名&nbsp;<input type="checkbox" name="checkall" value="checkbox" onClick="CheckAll(Search_Pworkset_Form);" >(全选)</td>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >地点类型</td>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >ID</td>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >地点名称</td>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >指定时间</td>
        
               </tr>
               <logic:present name="relist" scope="request">
                 <logic:iterate name="relist" id="dw">
                     <tr>
                       <td align="left" bgcolor="#E6E6FA">
                       <input type="checkbox" name="checkId" id="idd" value="<bean:write name='dw' property='stafferid'/>+$+<bean:write name="dw" property="idtype" />+$+<bean:write name="dw" property="id" />" />
                       <bean:write name="dw" property="name" />
                       
                       </td>
                       <td align="left" bgcolor="#E6E6FA">
                       <bean:write name="dw" property="idtype" />
                      
                       </td>
                       <td align="left" bgcolor="#E6E6FA">
                       
                       <bean:write name="dw" property="id" />
                       </td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="shortnamea" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="appointminute" /></td>
                       
                    </tr>  
                 </logic:iterate>
               </logic:present>
               
         </table>   
        
         </html:form>     
  </body>
</html:html>
