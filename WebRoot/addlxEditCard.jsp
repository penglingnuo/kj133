
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="增加11" %> 
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>addEditCard.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <SCRIPT language="javascript">
       function check()
       {
            var cidmin=document.all['mincardid'].value;
            var cidmax=document.all['maxcardid'].value;
            if(cidmin=="")
              {
                alert("卡号不能为空");
                document.all['mincardid'].focus();
                return false;
              }
             if( cidmin <= 0 )
                 {
                  alert("卡号应在1到16385之间");
                  document.all['mincardid'].value="";
                  document.all['mincardid'].focus();
                  return false;
                }
             if( cidmin >= 16385 )
                 {
                  alert("卡号应在1到16385之间");
                  document.all['mincardid'].value="";
                  document.all['mincardid'].focus();
                  return false;
                }
            if(cidmax=="")
              {
                alert("卡号不能为空");
                document.all['maxcardid'].focus();
                return false;
              }
             if( cidmax <= 0 )
                 {
                  alert("卡号应在1到16385之间");
                  document.all['maxcardid'].value="";
                  document.all['maxcardid'].focus();
                  return false;
                }
             if( cidmax >= 16385 )
                 {
                  alert("卡号应在1到16385之间");
                  document.all['maxcardid'].value="";
                  document.all['maxcardid'].focus();
                  return false;
             }
             if( mincardid >= maxcardid ){
                 alert("最小卡号不能大于等于最大卡号");
                 document.all['mincardid'].focus();
             }
              return true;
       }
     
    </SCRIPT>
    </head>
  <body bgColor="white" background="Image/right.gif">
        
     <html:form action="/operateCard?method=savelx" focus="mincardid" onsubmit="return check()" target="topFrame" >
     <td ><font size="2" color="red">说明:</font><font size="2" color="blue">该连续注册为跳过包含4的卡号</font></td>
      <br />
		    <TABLE width="700">
		    
		      <TR>
		        <TD>增加卡号：从&nbsp;</TD>
		        <TD><html:text property="mincardid" size="6" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="5" />&nbsp;<font color="red">*</font>&nbsp;</TD>
		        <TD>到&nbsp;</TD>
		        <TD><html:text property="maxcardid" size="6" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="5" />&nbsp;<font color="red">*</font>&nbsp;</TD>
		        
		        <TD>卡型号：</TD>
		        <TD>
		           <html:select property="cardmode">
		               <html:option value="KGE39">KGE39</html:option>
		               <html:option value="车辆">车辆</html:option>
		           </html:select>&nbsp;&nbsp;&nbsp;
		         </TD>
		         
		        <TD>卡状态：</TD>
		        <TD>
		           <html:select property="cardstate">
		               <html:option value="正常">正常</html:option>
		               <html:option value="停用">停用</html:option>
		           </html:select>&nbsp;&nbsp;&nbsp;
		        </TD>
		        <TD><html:submit>确 定</html:submit> &nbsp;</TD>
		        <TD><html:reset>取 消</html:reset></TD>
		        
		     </TR> 
		    </TABLE>
    </html:form>
  </body>
</html:html>
