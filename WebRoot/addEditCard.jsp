
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
            var cid=document.all['cardid'].value;
            if(cid=="")
              {
                alert("卡号不能为空");
                document.all['cardid'].focus();
                return false;
              }
             if( cid <= 0 )
                 {
                  alert("卡号应在1到16385之间");
                  document.all['cardid'].value="";
                  document.all['cardid'].focus();
                  return false;
                }
             if( cid >= 16385 )
                 {
                  alert("卡号应在1到16385之间");
                  document.all['cardid'].value="";
                  document.all['cardid'].focus();
                  return false;
                }
              return true;
       }
      function add(){
			  window.parent.frames['mainFrame'].location.href='addlxEditCard.jsp';
	  } 
    </SCRIPT>
    </head>
  <body bgColor="white" background="Image/right.gif">
        
     <html:form action="/operateCard?method=save" focus="cardid" onsubmit="return check()" target="topFrame" >
		    <TABLE width="700">
		      <TR>
		        <TD>增加卡号：</TD>
		        <TD><html:text property="cardid" size="10" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="5" />&nbsp;<font color="red">*</font>&nbsp;</TD>
		        
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
		        <TD><html:reset>取 消</html:reset>&nbsp;</TD>
		        <TD><input type="button" value="注册连续卡号"  onclick="add()"></TD>
		     </TR> 
		    </TABLE>
    </html:form>
  </body>
</html:html>
