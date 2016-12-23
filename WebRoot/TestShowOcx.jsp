<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>TestShowOcx.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
<SCRIPT   LANGUAGE="javascript"   FOR="UserControl1"   EVENT="ondata()">   
           <!--   
            UserControl1_ScanOK();   
          -->   
       </SCRIPT>  
       <SCRIPT   ID="clientEventHandlersJS"   LANGUAGE="javascript">
          function   UserControl1_ScanOK()   
          {   
             var xml=UserControl1.get_xml();
             document.writeln(xml+"<br>"); 
             var url="showOcx.do?method=ocxEvent&xml="+UserControl1.get_xml();
             document.write(url);                                                                             
             //window.location.href=
          }   
       </script>
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
