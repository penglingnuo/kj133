
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>fenzhandisleft.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script>  
     <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
     <link rel="StyleSheet" href="js/dtree.css" type="text/css" />
	 <script type="text/javascript" src="js/dtree.js"></script>
	 <style TYPE="text/css">
	  <!--
		A:link{text-decoration:none}
		A:visited{text-decoration:none}
		A:hover {color: #CCCCFF;text-decoration:none}
	   -->
	 </style>
  </head>
   
  <body bgColor="white" background="Image/right.gif">
	    <logic:messagesPresent message="true">
		  <html:messages id="message" message="true">
			   <font color="red"><bean:write name="message" /></font><br />
		  </html:messages>
        </logic:messagesPresent>
        <div class="dtree">
       
       <p><a href="javascript: tree.openAll();">全部打开</a> | <a href="javascript: tree.closeAll();">全部关闭</a></p>
           <script type="text/javascript">
               tree = new dTree('tree');
	              tree.add(0,'-1','区域分布');
	                   <logic:present name="tree">
		                   <logic:iterate id="list" name="tree" scope="request">
			                      tree.add('<bean:write name="list" property="id" />','<bean:write name="list" property="pid" />',
				                  '<bean:write name="list" property="name" />','<bean:write name="list" property="link" />',
				                  '','mainFrame');
		                    </logic:iterate>
	                   </logic:present>
	            document.write(tree); 
             </script> 
       </div>
  </body>
</html:html>