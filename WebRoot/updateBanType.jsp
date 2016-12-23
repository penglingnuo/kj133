
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="修改班次设置"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <%
      String bd1 = request.getAttribute("bd1").toString();
      String ban_id1 = request.getAttribute("ban_id1").toString();
      String bantypeid1 = request.getAttribute("bantypeid1").toString();
     %>
    <title>updateBanType.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>

  </head>
  
  <body bgColor="white" background="Image/right.gif">
         <logic:messagesPresent message="true">
			  <html:messages id="message" message="true">
			     <font color="red"><bean:write name="message" /></font>
			  </html:messages>
       </logic:messagesPresent>
        <!-- message -->
     <html:form action="bantype?method=update"  focus="bantype.ban_name">
         <html:hidden property="bd1" value="<%=bd1%>"/>
         <html:hidden property="ban_id1" value="<%=ban_id1%>"/>
         <html:hidden property="bantypeid1" value="<%=bantypeid1%>"/>
     
           班次名称：<html:text property="bantype.ban_name" size="9"  />&nbsp;&nbsp;&nbsp;
           <%--
           迟到延时(分)：<html:text property="bantype.qidao_time" size="5" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')"/>&nbsp;&nbsp;&nbsp;
           早退延时(分)：<html:text property="bantype.zaotui_time" size="5" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')"/>&nbsp;&nbsp;&nbsp;
           --%>
           班次类型：
           <html:select property="bantype.bd" style="width:80px">
               <html:option value=""></html:option>
               <html:option value="三八制">三八制</html:option>
               <html:option value="四六制">四六制</html:option>
               
             </html:select>&nbsp;&nbsp;&nbsp;
           班次编号：<html:text property="bantype.ban_id" size="5"   onkeyup="this.value=this.value.replace(/\D/g,'')"/>&nbsp;&nbsp;&nbsp;
           加班起计(小时)：<html:text property="bantype.add_time" size="5" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
	      <table>
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
		          <td>&nbsp;&nbsp;<html:submit value="修 改" property="submit" /> </td>
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
		                   <html:options collection="hour" property="value"  labelProperty="key" />
		               </html:select> 点
		               <html:select property="bantype.end_stime_m">
		                   <html:options collection="minute" property="value" labelProperty="key" />
		               </html:select> 分
		          </td>
		          <td>&nbsp;&nbsp;<input type="button" value="返 回" onclick="javascript:history.back()" /></td>
	        </tr>
	      </table><br /> 
	      
	      <table cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" >
	        <tr>
	              <td width="65" align="left" bgcolor="#B0C4DE">班次名称 </td>
	              <td width="65" align="left" bgcolor="#B0C4DE">班次类型</td>
	              <td width="65" align="left" bgcolor="#B0C4DE">班次编号</td>
	              <td width="65" align="left" bgcolor="#B0C4DE">上班时间</td>
	              <td width="65" align="left" bgcolor="#B0C4DE">下班时间</td>
	              <td width="85" align="left" bgcolor="#B0C4DE">开始考勤时间</td>
	              <td width="85" align="left" bgcolor="#B0C4DE">结束考勤时间</td><%--
	              <td width="65" align="left" bgcolor="#B0C4DE">迟到延时</td>
	              <td width="65" align="left" bgcolor="#B0C4DE">早退延时</td>
	              <td width="65" align="left" bgcolor="#B0C4DE">加班起计</td> 
	        --%></tr>
	        <logic:present name="ban">
	             <logic:iterate name="ban" id="list">
	               <tr onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
	                 <td bgcolor="#E6E6FA"><bean:write name="list" property="ban_name" /></td>
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
