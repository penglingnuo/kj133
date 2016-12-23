<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>moves_path_showResult.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
 <script type="text/javascript">
      function init(){
        loadMoves.MoveInitialize('<%= request.getAttribute("strCon") %>','<%= request.getAttribute("userId")%>');
        loadMoves.Replydate('<%= request.getAttribute("cid") %>','<%= request.getAttribute("stime") %>','<%= request.getAttribute("etime") %>',1,10,1);
      }
      
    </script>
  </head>

  <body onload="init()">
      <div>
          <input type="button"  value="选择员工下井时间" onclick="window.location.href='Moves_path.do?method=moves_path_downWell'" />
      </div>
      <div>
          <OBJECT
		      id="loadMoves"
			  classid="clsid:35B30F1A-CFB6-4588-A943-9188D45E0E5F"
			  codebase="http://192.168.1.24UTF-8/kj133/ocx/Moving.ocx#version=1,0,0,1"
			  width=821
			  height=540
			  align=left
			  hspace=0
			  vspace=0
          >
  </OBJECT>
      </div>
  </body>
</html:html>
