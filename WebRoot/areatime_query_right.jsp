<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="区域人数小计"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>areatime_query_right.jsp</title>
	<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script>
	<script language="javascript" src="js/page.js"></script>
	<%--分页--%>
	<script language="JavaScript" src="js/calendar.js"></script>
	<%--时间--%>
	<link href="Css/TestDate.css" rel="stylesheet">
	<%--背景--%>
	<script language="javascript" src="js/CardIdORName.js"></script>
	<link href="Css/calendar-blue.css" rel="stylesheet">

	<script language="javascript" src="js/StafferORName.js"></script>

   
        <style TYPE="text/css">
		<!--
		 A:link{color: #6699CC;text-decoration:none}
		 A:hover{color:#FF0000;}
		 -->
    </style>
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
 

<body bgColor="white" background="Image/right.gif">
	

	<html:form action="areatime_query_right?method=init" onsubmit="return check()">
       
		<%-- 
          <div   style="overflow:auto;width:825;height:500;"> 
          --%>
         <br> 
         <br>
         <br>
         <br>
          <td ><font size="2" color="red">区域人员次数统计:</font></td>
		<table cellspacing="1" cellpadding="1" bgcolor="#6CA6CD" border="0"
		 style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
			<tr>
				<td width="80" align="center" bgcolor="#B0C4DE">
					工种
				</td>
				<td width="50" align="center" bgcolor="#B0C4DE">
					人数
				</td>
				<td width="50" align="center" bgcolor="#B0C4DE">
					次数
				</td>
				
				
				
				

			</tr>
			<logic:present name="relist" scope="request">
				<logic:iterate name="relist" id="relist">
				<tr onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
					
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="worktype" />						
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="mans"/>
							
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="cishu" />
						</td>
						

					</tr>
				</logic:iterate>
			</logic:present><%--
			<logic:present name="pagination">
				<tr>
					<td colspan="7" align="left" bgcolor="#E6E6FA">
						<page:pagination path="car_move_log.do?method=getList"
							parameter="page" formName="Search_car_move_log_Form" />
					</td>
				</tr>
			</logic:present>
		--%></table>
	</html:form>
</body>
</html:html>

