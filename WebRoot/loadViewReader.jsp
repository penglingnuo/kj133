
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>loadViewReader.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
   <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
   <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
   <SCRIPT language="javascript">
       function check()
       {
            if(document.all['ser_operator_viewReader.crname'].value=="")
              {
                alert("分站名不能为空");
                document.all['ser_operator_viewReader.crname'].focus();
                return false;
              }
             if(document.all['ser_operator_viewReader.mapid'].value=="")
              {
                alert("地图号不能为空");
                document.all['ser_operator_viewReader.mapid'].focus();
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
      <html:form action="/operartrViewReader?method=update" focus="ser_operator_viewReader.crname" onsubmit="return check()">
        <TABLE border="0">
           <TR>
             <TD colspan="2">
              修改分站信息 &nbsp;&nbsp;&nbsp;<font color="red">（*为必填内容）</font>
              <input type="button" value="返 回"  onClick="history.back()">
             </TD>
           </TR>
           <TR>
              <html:hidden property="ser_operator_viewReader.recordid"/>
              <TD>分&nbsp;站&nbsp;号：</TD>
              <TD><html:text property="ser_operator_viewReader.cardreaderid" maxlength="20" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')"  /><font color="red">&nbsp;&nbsp;*</font></TD><%--
              <TD><html:text property="ser_operator_viewReader.cardreaderid" readonly="true" style="background-color: #dfdfdf"  /></TD>
           --%>
           </TR>
           <TR>
              <TD>分&nbsp;站&nbsp;名：</TD>
              <TD><html:text property="ser_operator_viewReader.crname" maxlength="25" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')"/></TD>
           </TR>
           <TR>
              <TD>简&nbsp;&nbsp;&nbsp;&nbsp;称：</TD>
              <TD><html:text property="ser_operator_viewReader.shortname" maxlength="20" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')"/><font color="red">&nbsp;&nbsp;*</font></TD>
           </TR>
           <TR>
              <TD>地图X坐标：</TD>
              <TD><html:text property="ser_operator_viewReader.x" maxlength="9" onkeyup="this.value=this.value.replace(/\D/g,'')" /></TD>
           </TR>
           <TR>
              <TD>地图Y坐标：</TD>
              <TD><html:text property="ser_operator_viewReader.y" maxlength="9" onkeyup="this.value=this.value.replace(/\D/g,'')" /></TD>
           </TR>
           <TR>
              <TD>地图Z坐标：</TD>
              <TD><html:text property="ser_operator_viewReader.z" maxlength="9" onkeyup="this.value=this.value.replace(/\D/g,'')" /></TD>
           </TR>
 
           <TR>
              <TD>忽略中断：</TD>
              <TD><html:text property="ser_operator_viewReader.ignoretimes" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')" /><font color="red">&nbsp;&nbsp;*</font></TD>
           </TR>
           <TR>
              <TD>指定忽略中断：</TD>
              <TD><html:text property="ser_operator_viewReader.locatorignoretimes" maxlength="2" onkeyup="this.value=this.value.replace(/\D/g,'')" /></TD>
           </TR>
           <TR>
              <TD>地&nbsp;图&nbsp;号：</TD>
              <TD><html:text property="ser_operator_viewReader.mapid" maxlength="4" onkeyup="this.value=this.value.replace(/\D/g,'')" />  <font color="red">&nbsp;&nbsp;*</font></TD>
           </TR>
           <TR>
              <TD>指定定位器：</TD>
              <TD><html:text property="ser_operator_viewReader.ignorelocator" onkeyup="this.value=this.value.replace(/\D/g,'')" /></TD>
           </TR>
           <TR>
              <TD>设备性质：</TD>
              <TD><html:select property="ser_operator_viewReader.ground" style="width:153px">
                       <html:option value="1">井上设备</html:option>
                       <html:option value="0">井下设备</html:option>
                       <html:option value="2">不区分</html:option>
                  </html:select>
              </TD>
           </TR>
           <TR>
              <TD>使用状态：</TD>
              <TD><html:select property="ser_operator_viewReader.state" style="width:153px">
                       <html:option value="正常">正常</html:option>
                       <html:option value="停用">停用</html:option>
                  </html:select>
              </TD>
           </TR>
           <TR>
              <TD>验卡分站：</TD>
              <TD><html:select property="ser_operator_viewReader.checkreader" style="width:153px">
                       <html:option value="1">是</html:option>
                       <html:option value="0">否</html:option>
                  </html:select></TD>
           </TR>
           <TR>
              <TD>声音报警：</TD>
              <TD><html:select property="ser_operator_viewReader.ifalarm" style="width:153px">
                       <html:option value="1">使用</html:option>
                       <html:option value="0">不使用</html:option>
                  </html:select></TD>
           </TR>
           <TR>
             <TD>使用天线：</TD>
             <TD>
                 <html:multibox property="ser_operator_viewReader.useantenna" value="1" />A&nbsp;
                 <html:multibox property="ser_operator_viewReader.useantenna" value="2" />B&nbsp;
                 <html:multibox property="ser_operator_viewReader.useantenna" value="4" />C&nbsp;
             </TD>
           </TR>
           <TR>
            <TD colspan="2" align="center">
               <html:submit>确  定</html:submit>&nbsp;&nbsp;&nbsp;
               <html:reset>取  消</html:reset>
            </TD>
           </TR>
        </TABLE>
       </html:form>
  </body>
</html:html>
