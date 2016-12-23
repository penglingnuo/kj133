
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="班次" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>bantype.jsp</title>
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
				             str=str+','+init[i].value;       
				       }
				     location.href="bantype.do?method=delete&id="+str;   
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
			function check()
			{
			  var name=document.all['bantype.ban_name'].value;
			  var iid=document.all['bantype.ban_id'].value;
			  var bid=document.all['bantype.bd'].value;
			  if( name == ""){
			      alert('班次名称不能为空');
			      document.all['bantype.ban_name'].focus();
			      return false;
			  }if( bid == ""){
			      alert('班类型不能为空');
			      document.all['bantype.bd'].focus();
			      return false;
			  }if( iid == 0){
			      alert('班次编号不能为空');
			      document.all['bantype.ban_id'].focus();
			      return false;
			  }
			  
			  return true;
			}
		   
		</script>
  </head>
  
  <body  bgColor="white" background="Image/right.gif">
         <logic:messagesPresent message="true">
			  <html:messages id="message" message="true">
			     <font color="red"><bean:write name="message" /></font>
			  </html:messages>
       </logic:messagesPresent>
        <!-- message -->
     <html:form action="/bantype?method=init" onsubmit="return check()" focus="bantype.ban_name" >
     
          <html:hidden property="bantype.ban_name2"/>
          <table width="650">
          <%--
           迟到延时(分)：<html:text property="bantype.qidao_time" size="5"  maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')"/>&nbsp;&nbsp;&nbsp;
           早退延时(分)：<html:text property="bantype.zaotui_time" size="5"  maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')"/>&nbsp;&nbsp;&nbsp;
           <html:text property="bantype.bantypeid" size="5"  maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')"/>&nbsp;&nbsp;&nbsp;--%>
           <td>班次名称：</td>
           <td>
           <html:text property="bantype.ban_name" size="9"   />&nbsp;&nbsp;&nbsp;
           </td>
           <td>班次类型：</td>
           <td>
             <html:select property="bantype.bd" style="width:80px">
               <html:option value=""></html:option>
               <html:option value="三八制">三八制</html:option>
               <html:option value="四六制">四六制</html:option>
               
             </html:select>&nbsp;&nbsp;&nbsp;
             </td>
           <td>班次编号：</td>
           <td>
           <html:text property="bantype.ban_id" size="5"   onkeyup="this.value=this.value.replace(/\D/g,'')"/>&nbsp;&nbsp;&nbsp;
           </td>
           
           <td>加班起计(小时)：</td>
           <td>
           <html:text property="bantype.add_time" size="5"  maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')"/>&nbsp;&nbsp;&nbsp;
           </td>
	   </table>
	      <table width="480">
	        <tr>
		          <td>上班时间：</td>
		          <td><html:select property="bantype.start_time_h">
		                   <html:options collection="hour" property="value" labelProperty="key" />
		               </html:select> 点
		               <html:select property="bantype.start_time_m">
		                   <html:options collection="minute" property="value" labelProperty="key" />
		               </html:select> 分
		          </td>
		          <td>&nbsp;&nbsp;开始考勤：</td>
		          <td><html:select property="bantype.start_stime_h">
		                   <html:options collection="hour" property="value" labelProperty="key" />
		               </html:select> 点
		               <html:select property="bantype.start_stime_m">
		                   <html:options collection="minute" property="value" labelProperty="key" />
		               </html:select> 分
		          </td>
		          
		          <td>&nbsp;&nbsp;<html:submit   value="增 加"  /> </td>  
	        </tr>
	        <tr>
		          <td>下班时间：</td>
		          <td>
		               <html:select property="bantype.end_time_h">
		                   <html:options collection="hour" property="value" labelProperty="key" />
		               </html:select> 点
		               <html:select property="bantype.end_time_m">
		                   <html:options collection="minute" property="value" labelProperty="key" />
		               </html:select> 分
		          </td>
		          <td>&nbsp;&nbsp;结束考勤：</td>
		          <td>
		               <html:select property="bantype.end_stime_h">
		                   <html:options collection="hour" property="value" labelProperty="key" />
		               </html:select> 点
		               <html:select property="bantype.end_stime_m">
		                   <html:options collection="minute" property="value" labelProperty="key" />
		               </html:select> 分
		          </td>
		        
		          
		          <td>&nbsp;&nbsp;<input type="button"   value="删 除" onclick="DeleteSomeLine(Search_BanType_Form)" /></td>
	        </tr>
	      </table><br /> 
	      
	      <table cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
	        <tr>
	              
	              <td width="125" align="left" bgcolor="#B0C4DE">班次名称<input type="checkbox" name="checkall" value="checkbox" onClick="CheckAll(Search_BanType_Form);" >(全选)</td>
	              <td width="65" align="left" bgcolor="#B0C4DE">班次类型</td>
	              <td width="65" align="left" bgcolor="#B0C4DE">班次编号</td>
	              <td width="65" align="left" bgcolor="#B0C4DE">上班时间</td>
	              <td width="65" align="left" bgcolor="#B0C4DE">下班时间</td>
	              <td width="85" align="left" bgcolor="#B0C4DE">开始考勤时间</td>
	              <td width="85" align="left" bgcolor="#B0C4DE">结束考勤时间</td>
	              <%--<td width="65" align="left" bgcolor="#B0C4DE">迟到延时</td>
	              <td width="65" align="left" bgcolor="#B0C4DE">早退延时</td>
	              <td width="65" align="left" bgcolor="#B0C4DE">加班起计</td> 
	        --%>
	        </tr>
	        <logic:present name="ban">
	             <logic:iterate name="ban" id="list">
	               <tr>
	               
	                 <td bgcolor="#E6E6FA"><input type="checkbox" name="id"  value="<bean:write name='list' property='ban_id'/>+$+<bean:write name='list' property='bd'/>" />
	                       <a href="bantype.do?method=load&banname=<bean:write name='list' property='ban_name' />&bantypeid=<bean:write name='list' property='bantypeid' />&bd=<bean:write name='list' property='bd' />&ban_id=<bean:write name='list' property='ban_id' /> " title="修 改 班 次 设 置" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''"  class="A:link" />
	                       <bean:write name="list" property="ban_name" /></a>     
	                 </td>
	                 <td bgcolor="#E6E6FA"><bean:write name="list" property="bd" /></td>
	                 <td bgcolor="#E6E6FA"><bean:write name="list" property="ban_id" /></td>	                 
	                 <td bgcolor="#E6E6FA"><bean:write name="list" property="start_time" /></td>
	                 <td bgcolor="#E6E6FA"><bean:write name="list" property="end_time" /></td>
	                 <td bgcolor="#E6E6FA"><bean:write name="list" property="start_stime" /></td>
	                 <td bgcolor="#E6E6FA"><bean:write name="list" property="end_stime" /></td>
	                 <%--<td bgcolor="#E6E6FA"><bean:write name="list" property="qidao_time" /></td>
	                 <td bgcolor="#E6E6FA"><bean:write name="list" property="zaotui_time" /></td>
	                 <td bgcolor="#E6E6FA"><bean:write name="list" property="add_time" /></td>
	               --%></tr>
	             </logic:iterate>
	        </logic:present>
	      </table>
      </html:form>
  </body>
</html:html>
<!--  -->
