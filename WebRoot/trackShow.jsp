<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="定位跟踪" %>
<%@ taglib uri="http://struts.apache.org/tags-bean"  prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />    
     <title>trackShow.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
     <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
	 <meta   http-equiv="refresh"  content="21" url="track.do?method=trackQuery" /> 
     <script type="text/javascript">
         function check(){
          var worktype=document.all('search_trackshow.worktype').value;
           
          var gro=document.all('search_trackshow.gro').value;
           
          var cid=document.all('search_trackshow.cid').value;
           
          var sid=document.all('search_trackshow.sid').value;
           
          var username=document.all('search_trackshow.username').value;
            
           
          var num=0;
          if(worktype.length>0){
              num++;
          }if(gro.length){
              num++;
          }if(cid.length>0){
              num++;
          }if(sid.length>0){
              num++;
          }if(username.length>0){
              num++;
          }
          if(num>1){
              alert("为了提高查询速度，请选择一个进行查询");
              document.all('search_trackshow.worktype').value="";
              document.all('search_trackshow.gro').value="";
              document.all('search_trackshow.cid').value=""; 
              document.all('search_trackshow.sid').value="";
              document.all('search_trackshow.username').value="";
              return false;
          }
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
     
        <html:form action="track?method=trackQuery"  onsubmit="return check()">
         <font color="red" size="3">  当前井下总人数：<%= request.getAttribute("downCount").toString()  %></font><br>
                工&nbsp;&nbsp;种：
                 <html:select property="search_trackshow.worktype"  style="width:80px">
                    <html:option value="" ></html:option>
                    <html:options collection="workType_list" property="wordvalue" labelProperty="wordvalue"/>
                 </html:select>&nbsp;&nbsp;&nbsp;
                 班&nbsp;&nbsp;组：
                   <html:select property="search_trackshow.gro" style="width:80px" > 
	                  <html:option value="" ></html:option>
	                  <html:options collection="gro_list" property="wordvalue" labelProperty="wordvalue"/>
                     </html:select> 
                 分站号： <html:text property="search_trackshow.cid" maxlength="3" size="9" onkeyup="this.value=this.value.replace(/\D/g,'')"/> 
                卡&nbsp;&nbsp;号： <html:text property="search_trackshow.sid" maxlength="6" size="9" onkeyup="this.value=this.value.replace(/\D/g,'')"/> 
                姓&nbsp;&nbsp;名： <html:text property="search_trackshow.username" maxlength="4" size="9" /> 
               &nbsp;&nbsp;&nbsp;<html:submit>查 询</html:submit> 
             

           <table  width="770re"cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
		     <tr>
			    <td  width="40"  align="left" bgcolor="#B0C4DE">工种</td>
				<td  width="40"  align="left" bgcolor="#B0C4DE">卡号</td>
				<td  width="60"  align="left" bgcolor="#B0C4DE">姓名</td>
				<td  width="80"  align="left" bgcolor="#B0C4DE">班组</td>
				<td  width="60"  align="left" bgcolor="#B0C4DE">所属分站</td>
				<td  width="130" align="left" bgcolor="#B0C4DE">分站名称</td>
				<td  width="30"  align="left" bgcolor="#B0C4DE">天线</td>
				<td  width="140" align="left" bgcolor="#B0C4DE">状态信息</td>
	         </tr>
		     <logic:present name="trackShow">
              <logic:iterate name="trackShow" id="show">
                 <tr>
                     <td align="left" bgcolor="#E6E6FA"><bean:write name="show" property="worktype" /></td>
                     <td align="left" bgcolor="#E6E6FA"><bean:write name="show" property="cardid" /></td>
                     <td align="left" bgcolor="#E6E6FA"><bean:write name="show" property="username" /></td>
                     <td align="left" bgcolor="#E6E6FA"><bean:write name="show" property="gro" /></td>
                     <td align="left" bgcolor="#E6E6FA"><bean:write name="show" property="cardreaderid" /></td>
                     <td align="left" bgcolor="#E6E6FA"><bean:write name="show" property="shortname" /></td>
                     <td align="left" bgcolor="#E6E6FA"><bean:write name="show" property="antenna" /></td>
                     <logic:equal name="show" property="state" value="无">
                           <td align="left" bgcolor="#E6E6FA"></td>
                     </logic:equal>
                     <logic:notEqual name="show" property="state" value="无">
                           <td align="left" bgcolor="#E6E6FA"><bean:write name="show" property="state" /></td>
                     </logic:notEqual>
                 </tr>
              </logic:iterate>
            </logic:present> 
        </table>
        </html:form>
  </body> 
</html:html>
