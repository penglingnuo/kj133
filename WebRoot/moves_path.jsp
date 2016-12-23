<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>moves_path.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script type="text/javascript">
      function init(){
        loadMoves.MoveInitialize('<%= request.getAttribute("strCon") %>','<%= request.getAttribute("userId")%>');
        
      }
      
<%--      function test(){--%>
<%--           loadMoves.Replydate('021002','2008-03-01 08:32:17','2008-03-01 11:04:53',1,10,1);--%>
<%--      }--%>
      
    </script>
  </head>

  <body onload="init()" background="Image/right.gif">
<%--      <div>--%>
<%--          <input type="button" value="选择员工下井时间" onclick="window.location.href='Moves_path.do?method=moves_path_downWell'" />--%>
<%--      </div>--%>
      <div>
          <OBJECT
		      id="loadMoves"
			  classid="clsid:802D470E-2B1C-421D-91DD-539C3FEA6678"
			  width="100%"
		  	  height="100%"
			  align=left
			  hspace=0
			  vspace=0
          >
		</OBJECT>
      </div>
  </body>
</html:html>
