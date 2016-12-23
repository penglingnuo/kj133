
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page info="人员工作设置-时间设置" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>pworkset_time1.jsp</title>
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
				     
				     location.href="pworkset.do?method=deleteTime1&lid="+str;   
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
			
			function addgongzhong(){
	       
			  window.parent.frames['downFrame'].location.href="pworkset.do?method=addgongzhong";
			     return true;
			}
		</script>
  
  </head>
  
  <body bgColor="white" background="Image/right.gif" >
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     <%--
     
    <html:form action="/pworkset?method=addSite" target="leftFrame" onsubmit="return check()" focus="pworkset.zuhe" method="post" >
    --%>
    <html:form action="/pworkset?method=addTime" target="arightFrame" onsubmit="return check()" focus="pworkset.zuhe" method="post"  >
   <td ><font size="2" color="red">时间设置:</font></td>
    <br />
    <td><font size="2" color="blue">人员：null</font></td>
    <td>&nbsp;&nbsp;</td>
    <td><font size="2" color="blue">工种：null</font></td>
    <table width="450">

    <%--
    
    <html:hidden property="name" value="<%=request.getAttribute("name") %>"/>
    <html:hidden property="type" value="<%=request.getAttribute("type") %>"/>
    --%>
    <tr>
           <td >班次类型：&nbsp;&nbsp;</td>
           
           <td align="right">最小工作时间(分)：</td>
           <td><html:text property="pworkset.mintime" size="5" maxlength="3" />&nbsp;</td>
           
           <td><html:submit disabled="true">员工设置</html:submit>&nbsp;</td>
           <td><input type="button" value="工种设置" onclick="addgongzhong()" /></td>

    </tr>
    <tr>
           <td>
            <html:select  property="pworkset.bantype" style="width:80px">
               
               <html:option value="" ></html:option>
               <html:option value="三八制" >三八制</html:option>
               <html:option value="四六制" >四六制</html:option>
               <html:option value="大班制" >大班制</html:option>
               
            
             </html:select>
            &nbsp;&nbsp;</td>
            <td align="right">最大工作时间(分)：</td>
            <td><html:text property="pworkset.maxtime" size="5" maxlength="3" />&nbsp;</td>
            <td><input type="button" value="删 除"  onclick="DeleteSomeLine(Search_Pworkset_Form)" /></td>
           
           <%--<td>
           <a href="javascript:Del();" ><IMG src="Image/delete.BMP" border="0" align="absmiddle"/></a>
           </td>
    --%></tr>

    
      </table>
      <br />
    <table  width="420" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
              
               <tr>
                   <td width="120"  align="left" bgcolor="#B0C4DE" >姓名&nbsp;<input type="checkbox" name="checkall" value="checkbox" onClick="CheckAll(Search_Pworkset_Form);" >(全选)</td>
                   <td width="70"  align="left" bgcolor="#B0C4DE" >工种</td>
                   <td width="50"  align="left" bgcolor="#B0C4DE" >班次类型</td>
                   <td width="80"  align="left" bgcolor="#B0C4DE" >最小工作时间</td>
                   <td width="80"  align="left" bgcolor="#B0C4DE" >最大工作时间</td>
        
               </tr>
               <logic:present name="relist" scope="request">
                 <logic:iterate name="relist" id="dw">
                     <tr>
                       <td align="left" bgcolor="#E6E6FA">
                       <input type="checkbox" name="checkId" id="idd" value="<bean:write name='dw' property='stafferid'/>" />
                       <bean:write name="dw" property="name" />
                       
                       </td>
                       <td align="left" bgcolor="#E6E6FA">
                       <bean:write name="dw" property="worktype" />
                      
                       </td>
                       <td align="left" bgcolor="#E6E6FA">
                       <bean:write name="dw" property="bantype" />
                      
                       </td>
                       <td align="left" bgcolor="#E6E6FA">
                       
                       <bean:write name="dw" property="mintime" />
                       </td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="dw" property="maxtime" /></td>
                       
                    </tr>  
                 </logic:iterate>
               </logic:present>
               
         </table>   
        
         </html:form>     
  </body>
</html:html>
