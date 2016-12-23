<%@ page language="java"    pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>event.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	 <SCRIPT   LANGUAGE="VBScript"   FOR="UserControl1"   EVENT="get_data(datacount,id,id_value)">   
	     dim cou
         cou=datacount
         idstr=join(id,",")
         id_valuestr=join(id_value,",")
         document.location="/kj133/showOcx.do?method=ocxEvent&id="+idstr+"&id_value="+id_valuestr 
	  </SCRIPT>  
	  <!-- 
	     http://192.168.1.24UTF-8/kj133这样就不行，因为这样的话，session就清空了，写了个 Session监听器
	   -->
  </head>
  
    <OBJECT
      id="UserControl1"
	  classid="clsid:9295F813-51D4-43A5-918B-4A82B6410176"
	  codebase="http://192.168.1.24UTF-8/kj133/ocx/eventProj1.ocx#version=1,1,20,8"
	  width=350
	  height=250
	  align=center
	  hspace=0
	  vspace=0
   >
  <body>
     
  </body>

</html:html>
