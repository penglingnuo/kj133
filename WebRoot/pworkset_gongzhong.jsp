
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
    <title>pworkset_gongzhong.jsp</title>
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
    <script type="text/javascript">
            function check()
			{
			  var mintime=document.all['pworkset.mintime'].value;
			  
			  var worktype=document.all['pworkset.worktype'].value;
			  var maxtime=document.all['pworkset.maxtime'].value;
			  
			  if( worktype == ""){
			      alert('工种不能为空');
			      document.all['pworkset.worktype'].focus();
			      return false;
			  }if( mintime == ""){
			      alert('最小工作时间不能为空');
			      document.all['pworkset.mintime'].focus();
			      return false;
			  }if( maxtime == ""){
			      alert('最大工作时间不能为空');
			      document.all['pworkset.maxtime'].focus();
			      return false;
			  }
			  return true;
			}
    
    
    </script>

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
    <html:form action="/pworkset?method=addgzTime" target="downFrame" onsubmit="return check()"  method="post"  >
   <td ><font size="2" color="blue">通过工种选项可以将所选的工种进行设置时间</font></td>
    <br />
    <table width="">
    <tr>
           <td>工种：</td>
			<td>
                <div style="position:relative;">
                <span style="margin-left:82px;width:18px;overflow:hidden;">
                <select  style="width:100px;margin-left:-82px" onchange="this.parentNode.nextSibling.value=this.value">
                <logic:present name="workType_list">
             		<option value=""></option>
             		<option value="全部工种">全部工种</option>
                	<logic:iterate id="aa" name="workType_list">
                		<option value='<bean:write name="aa" property="wordvalue"/>'><bean:write name="aa" property="wordvalue"/></option>
                	</logic:iterate>
                </logic:present>
                </select>
                </span>
                <html:text property="pworkset.worktype" style="width:82px;position:absolute;left:0px;"></html:text>
			    </div> 
            </td>
           
           <td align="right">最小工作时间(分)：</td>
           <td><html:text property="pworkset.mintime" size="5" maxlength="3" />&nbsp;</td>

    </tr>
    <tr>
    <td >班次类型：</td>
            <td>
            <html:select  property="pworkset.bantype" style="width:100px">
               
<%--               <html:option value="" ></html:option>--%>
               <html:option value="三八制" >三八制</html:option>
               <html:option value="四六制" >四六制</html:option>
               <html:option value="大班制" >大班制</html:option>
             </html:select>
            &nbsp;&nbsp;</td>
          
            <td align="right">最大工作时间(分)：</td>
            <td><html:text property="pworkset.maxtime" size="5" maxlength="3" />&nbsp;</td>
           
           <%--<td>
           <a href="javascript:Del();" ><IMG src="Image/delete.BMP" border="0" align="absmiddle"/></a>
           </td>
    --%></tr>
    <tr>
    
    <td align="right"><html:submit>设置</html:submit></td>
    </tr>

    
      </table>
        
        
         </html:form>     
  </body>
</html:html>
