<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kj133.entity.GjUser"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
        <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <title>管理管技用户</title>
<jsp:include flush="true" page="/checkgjuser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <SCRIPT language="javascript">
      
    </SCRIPT>
  </head>
  
  <body bgColor="white" background="Image/right.gif">
    <logic:messagesPresent message="true" >
        <html:messages id="message" message="true">
           <font color="red"><bean:write name="message" /></font>
        </html:messages>
    </logic:messagesPresent>
    <center>
    <a href="<%=path%>/gj.jsp">返回</a><p>
    <B><font size="5">管理管技用户</font></B> <a href="<%=path%>/addgjuser.jsp">新建用户</a><p>
        <TABLE border="1" width="500px" align="center">
        	<tr align="center">
        		<td>ID</td>
        		<td>用户名</td>
        		<td>操作</td>
        	</tr>
        	<% 
        		List gjlist = (List)request.getSession().getAttribute("gjlist");
        		if(gjlist!=null){
        			if(gjlist.size()!=0){
        			 for(int i=0;i<gjlist.size();i++){
        			 	GjUser gju = (GjUser)gjlist.get(i);
        			    request.setAttribute("gjid", gju.getId());
        			    request.setAttribute("gjusername", gju.getGjusername());
        			    request.setAttribute("gjuserpwd", gju.getGjuserpwd());
        	%>
        			<tr align="center">
        				<td>${gjid }</td>
        				<td>${gjusername }</td>
        				<td>
        				<!-- <a href="gjuser.do?method=updatpwd">编辑</a>  -->
        				<a href="gjuser.do?method=delgjuser&id=${gjid }">删除</a> 
        				</td>
        			</tr>
        	<%
        			 }
        		}else{
        	%>
        		<tr>
        			<td colspan="3" align="center">没有用户</td>
        		</tr>
        	<%} }%>
        </TABLE>
    </center>
  </body>
</html:html>
