
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>loadLocaliser.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <SCRIPT language="javascript">
        function check()
          {
           var lname=document.all['lname'].value;
           var shortname=document.all['shortname'].value;
           var mapid=document.all['mapid'].value;
           if(lname=="")
           {
             alert("定位器名不能为空")
             document.all['lname'].focus();
             return false;
           }
           if(shortname=="")
            {
              alert("简称不能为空");
              document.all['shortname'].focus();
              return false;
            }
           if(mapid=="")
           {
             alert("地图号不能为空");
             document.all['mapid'].focus();
             return false;
           }
           return true;
         }
    </SCRIPT>
  </head>
  
  <body bgColor="white" background="Image/right.gif">
       <logic:messagesPresent message="true">
		  <html:messages id="message" message="true">
		     <font color="red"><bean:write name="message" /></font>
		  </html:messages>
       </logic:messagesPresent>
        <!-- message -->
     <html:form action="/operator_locator?method=update" focus="lname" onsubmit="return check()">
        <TABLE>
          <TR>
             <TD colspan="2">
               修改定位器信息&nbsp;<font color="red">(*为必填内容)</font>
             <input type="button" value="返 回"  onClick="javascript:history.back()">
             </TD>
           </TR>
           <TR>
              <html:hidden property="recordid"/>
              <TD>定位器号：</TD>
              <TD><html:text property="locatorid"     onkeyup="this.value=this.value.replace(/\D/g,'')" /></TD><%--
              <TD><html:text property="locatorid"  style="background-color: #dfdfdf"  readonly="true" onkeyup="this.value=this.value.replace(/\D/g,'')" /></TD>
           --%></TR>
           <TR>
             <TD>定位器名：</TD>
             <TD><html:text property="lname" maxlength="50" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /><font color="red">&nbsp;&nbsp;*</font></TD>
           </TR>
            <TR>
             <TD>简&nbsp;&nbsp;&nbsp;&nbsp;称：</TD>
             <TD><html:text property="shortname" maxlength="20" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /><font color="red">&nbsp;&nbsp;*</font></TD>
           </TR>
            <TR>
             <TD>地图X坐标：</TD>
             <TD><html:text property="x" maxlength="9" onkeyup="this.value=this.value.replace(/\D/g,'')" /></TD>
           </TR>
            <TR>
             <TD>地图Y坐标：</TD>
             <TD><html:text property="y" maxlength="9" onkeyup="this.value=this.value.replace(/\D/g,'')" /></TD>
           </TR>
            <TR>
             <TD>地图Z坐标：</TD>
             <TD><html:text property="z" maxlength="9" onkeyup="this.value=this.value.replace(/\D/g,'')" /></TD>
           </TR>
           <TR>
              <TD>设备性质：</TD>
              <TD><html:select property="ground" style="width:153px">
                       <html:option value="1">井上设备</html:option>
                       <html:option value="0">井下设备</html:option>
                       <html:option value="2">不区分</html:option>
                  </html:select>
              </TD>
           </TR>
           <TR>
              <TD>使用状态：</TD>
              <TD><html:select property="state" style="width:153px">
                       <html:option value="正常">正常</html:option>
                       <html:option value="停用">停用</html:option>
                  </html:select>
              </TD>
           </TR>
            <TR>
             <TD>地&nbsp;图&nbsp;号：</TD>
             <TD><html:text property="mapid" maxlength="4" onkeyup="this.value=this.value.replace(/\D/g,'')" /><font color="red">&nbsp;&nbsp;*</font></TD>
           </TR>
        </TABLE>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <html:submit>确  定</html:submit>&nbsp;&nbsp;
        <html:reset>取  消</html:reset>
      </html:form>
  </body>
</html:html>
