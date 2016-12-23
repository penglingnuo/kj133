<%@ page language="java" import="java.text.*,java.util.*,com.kj133.action.*" pageEncoding="UTF-8"%>
<%@ page info="工种分布"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>worktype_areadis_top.jsp</title>
	<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script>
	<script language="javascript" src="js/page.js"></script>
	<%--分页--%>
	<link href="Css/TestDate.css" rel="stylesheet">
	<%--背景--%>
	<link href="Css/calendar-blue.css" rel="stylesheet">
	

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
	<logic:messagesPresent message="true">
		<html:messages id="message" message="true">
			<font color="red"><bean:write name="message" />
			</font>
		</html:messages>
	</logic:messagesPresent>
	<html:form action="worktype_areadis_top?method=init" onsubmit="return check()">
		
		<td ><font size="2" color="red">工种区域分布表---工种分布:</font></td>
		<%-- 
          <div   style="overflow:auto;width:825;height:500;"> 
          --%>
		<table cellspacing="1" cellpadding="1" bgcolor="#6CA6CD" border="0"
			>
			<tr>
				<td width="67" align="left" bgcolor="#B0C4DE">
					工种
				</td>
				<td width="100" align="center" bgcolor="#B0C4DE">
					总人数
				</td>
				<td width="150" align="center" bgcolor="#B0C4DE">
					重点区域人数
				</td>
				<td width="80" align="center" bgcolor="#B0C4DE">
					限制区域人数
				</td>
				<td width="80" align="center" bgcolor="#B0C4DE">
					采掘区域人数
				</td>
			</tr>
			<logic:present name="relist" scope="request">
				<logic:iterate name="relist" id="relist">
				<tr onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
					
						<td align="center" bgcolor="#E6E6FA">
							<a href="worktype_areadis_down.do?method=init&areaid=<bean:write name="relist" property="areaid"/>&recordtime=<bean:write name="relist" property="recordtime" />" title="人员明细" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="down" >
							<bean:write name="relist" property="areaid" />
							</a>
							
							</td>
						<td align="left" bgcolor="#E6E6FA">
						<a href="worktype_areadis_right.do?method=init&areaid=<bean:write name="relist" property="areaid"/>&recordtime=<bean:write name="relist" property="recordtime" />" title="区域分布" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="top_right" >
							<bean:write name="relist" property="areaname"/>
							</a>
							
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="recordtime" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="visitorcount" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
						<bean:write name="relist" property="minercount" />
						</td>
						
					</tr>
				</logic:iterate>
			</logic:present>
			<logic:present name="pagination">
				<tr>
					<td colspan="6" align="left" bgcolor="#E6E6FA">
						<page:pagination path="worktype_areadis_top.do?method=init"
							parameter="page" formName="Search_worktype_areadis_top_Form" />
					</td>
				</tr>
			</logic:present>
		
		
		</table>
		
	</html:form>

</body>

</html:html>

