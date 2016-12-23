<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>showOcxLoad.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <style TYPE="text/css">
		<!--
		A:visited{text-decoration:none}
		 -->
	</style>
	 <script type="text/javascript">
	         function check(){
	              var loca=document.getElementById('loca').value;
	              var chec=document.getElementById('chec');
	              if(!chec.checked){
		                if(loca<0){
			                alert('定位保留中断必须大于等于0');
			                return false;
		                }
	              }
	              return true;
	         }
     </script>
  </head>
  
  <body bgColor="white" background="Image/right.gif">
     <html:form action="showOcx?method=ocxUpdate" focus="search_ocx.dbip" onsubmit="return check()">
        <fieldset style="margin:0% 27% 0% 27%;border:1px solid #red;">
	     <legend style="padding:0.5px 0.5px;">数据库配置</legend>
	          <table border="0" align="center">
	            <tr>
	               <td>数据库IP：</td>
	               <td>&nbsp;&nbsp;<html:text  property="search_ocx.dbip"/><font color="red">&nbsp; *</font></td>
<%--	               <td>&nbsp;&nbsp;<html:text  property="search_ocx.dbip" readonly="true" style="background-color: #dfdfdf"/><font color="red">&nbsp; *</font></td>--%>
	            </tr>
	            <tr>
	               <td>数据库名称：</td>
	               <td>&nbsp;&nbsp;<html:text  property="search_ocx.dbname"/><font color="red">&nbsp; *</font></td>
	            </tr>
	            <tr>
	               <td>数据库用户名：</td>
	               <td>&nbsp;&nbsp;<html:text  property="search_ocx.dbusername"/><font color="red">&nbsp; *</font></td>
	            </tr>
	            <tr>
	               <td>数据库密码：</td>
<%--	               <td>&nbsp;&nbsp;<html:password  property="search_ocx.dbpassword" readonly="true" style="background-color: #dfdfdf" size="21"/><font color="red"> *</font></td>--%>
	               <td>&nbsp;&nbsp;<html:password  property="search_ocx.dbpassword"/><font color="red">&nbsp; *</font></td>
	            </tr>
	         </table>       
	   </fieldset>
       
       <fieldset style="margin:0% 27% 0% 27%;border:1px solid #red;">
	     <legend style="padding:0.5px 0.5px;">连接设置</legend>
	          <table border="0" align="center">
	            <tr>
	               <td>采集服务器IP：</td>
	               <td><html:text  property="search_ocx.cip"/><font color="red">&nbsp;&nbsp;*</font></td>
	            </tr>
	            <tr>
	               <td>采集服务器端口：</td>
	               <td><html:text  property="search_ocx.cpost" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="5"/><font color="red">&nbsp;&nbsp;*</font></td>
	            </tr>
	            <tr>
	               <td>地图号：</td>
	               <td><html:text  property="search_ocx.mid" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="2"/><font color="red">&nbsp;&nbsp;*</font></td>
	            </tr>
	         </table>       
	   </fieldset>
	   
	   <fieldset style="margin:0% 27% 0% 27%;border:1px solid #red;">
	     <legend style="padding:0.5px 0.5px;">报警配置</legend>
	          <table border="0" align="center">
	            <tr>
	               <td>井下超时(分)：</td>
	               <td>&nbsp;&nbsp;<html:text  property="search_ocx.overminute" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="5"/><font color="red">&nbsp;&nbsp;*</font></td>
	            </tr>
	            <tr>
	               <td>严重超时(分)：</td>
	               <td>&nbsp;&nbsp;<html:text  property="search_ocx.reoverminute" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="5"/><font color="red">&nbsp;&nbsp;*</font></td>
	            </tr>
	         </table>       
	   </fieldset>
	   
<%--	   <fieldset style="margin:0% 27% 0% 27%;border:1px solid #red;">--%>
<%--	     <legend style="padding:0.5px 0.5px;">时间设置</legend>--%>
<%--	         <table border="0" align="center">--%>
<%--	            <tr>--%>
<%--	                <td>定位保留中断：</td>--%>
<%--	                <td  align="left">&nbsp;--%>
<%--	                    <html:text property="search_ocx.lostlocator" styleId="loca"  disabled="true" size="8"/>--%>
<%--	                    <html:hidden property="search_ocx.hiddenlocator" styleId="hidd"/>--%>
<%--	                    <input type="checkbox" id="chec" onClick="document.getElementById('loca').disabled=true;document.getElementById('loca').value='-1';document.getElementById('hidd').value='-1';if (!this.checked)  {document.getElementById('loca').value='0';document.getElementById('loca').disabled=false }" checked>始终保留--%>
<%--	                    <font color="red">&nbsp;&nbsp;*</font>--%>
<%--	               </td>--%>
<%--	             </tr>--%>
<%--	         </table>--%>
<%--	   </fieldset>--%>
	   
	   <fieldset style="margin:0% 27% 0% 27%;border:1px solid #red;">
	     <legend style="padding:0.5px 0.5px;">显示设置</legend>
	         <table border="0" align="center">
	             <tr>
	                <td>
	                  <html:radio property="search_ocx.locatetype"  value="0" />默认精确定位 
	                  <html:radio property="search_ocx.locatetype"  value="1"  />默认区域定位
	                  <font color="red">&nbsp;&nbsp;*</font>
	                </td>
	              </tr>
	              <tr>
	                <td align="center">
	                  <html:submit>确 定</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
	                  <html:reset>取 消</html:reset>
	                </td>
	             </tr>
	         </table>
	   </fieldset>
      </html:form>
  </body>
</html:html>
