<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="带卡查询"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>stripclip_query_right.jsp</title>
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
	

	<html:form action="stripclip_query_right?method=init" onsubmit="return check()">
       
		<%-- 
          <div   style="overflow:auto;width:825;height:500;"> 
          --%>
         <br> 
         <br>
         <br>
         <br>
          
		<table cellspacing="1" cellpadding="1" bgcolor="#6CA6CD" border="0"
		 style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
			<tr>
				<td width="30" align="left" bgcolor="#B0C4DE">
					组数
				</td>
				<td width="50" align="left" bgcolor="#B0C4DE">
					卡号A
				</td>
				<td width="50" align="left" bgcolor="#B0C4DE">
					卡号B
				</td>
				<td width="50" align="center" bgcolor="#B0C4DE">
					员工号A
				</td>
				<td width="50" align="center" bgcolor="#B0C4DE">
					员工号B
				</td>
				<td width="60" align="center" bgcolor="#B0C4DE">
					姓名A
				</td>
				<td width="60" align="center" bgcolor="#B0C4DE">
					姓名B
				</td>
				<td width="60" align="center" bgcolor="#B0C4DE">
					相似度%
				</td>
				<td width="150" align="center" bgcolor="#B0C4DE">
					下井时间A
				</td>
				<td width="150" align="center" bgcolor="#B0C4DE">
					下井时间B
				</td>
				
				
				

			</tr>
			<logic:present name="relist" scope="request">
				<logic:iterate name="relist" id="relist">
				<tr onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
					
						<td align="center" bgcolor="#E6E6FA">
							<bean:write name="relist" property="teams" />
						
						</td>
						<td align="left" bgcolor="#E6E6FA">
						<%--<a href="areatime_query_right.do?method=init&areaid=<bean:write name="relist" property="areaid"/>&recordtime=<bean:write name="relist" property="recordtime" />" title="区域人员统计" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="top_right" >
							<bean:write name="relist" property="cardid"/>
							</a>
							--%><bean:write name="relist" property="cardidA"/>
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="cardidB" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="stafferidA" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="stafferidB" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="nameA" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="nameB" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
						<bean:write name="relist" property="near" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
						<bean:write name="relist" property="downtimeA" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
						<bean:write name="relist" property="downtimeB" />
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

