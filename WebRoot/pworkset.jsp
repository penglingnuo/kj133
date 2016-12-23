<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="人员工作设置"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>pworkset.jsp</title>
	<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<link href="Css/TestDate.css" rel="stylesheet">
	<%--背景--%>
	<script language="javascript" src="js/StafferORName.js"></script>
	<style TYPE="text/css">
		<!--
		 A:link{color: #6699CC;text-decoration:none}
		 A:hover{color:#FF0000;}
		 -->
    </style>

	<%--<style type="text/css">
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
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
	<html:form action="pworkset?method=getList"  target="aleftFrame">
	<td ><font size="2" color="red">提示:</font><font size="2" color="blue">点员工编号对地点进行设置,姓名对时间设置!</font></td>

		<table border="0">
			
				<tr>
				<td>
					工种：
				</td>
				<td>
                 <div style="position:relative;">
                 <span style="margin-left:82px;width:18px;overflow:hidden;">
                 <select  style="width:100px;margin-left:-82px" onchange="this.parentNode.nextSibling.value=this.value">
                 <logic:present name="workType_list">
              		<option value=""></option>
                 	<logic:iterate id="aa" name="workType_list">
                 		<option value='<bean:write name="aa" property="wordvalue"/>'><bean:write name="aa" property="wordvalue"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
                 <html:text property="pworkset.worktype" style="width:82px;position:absolute;left:0px;"></html:text>
				</div> 
                 </td>
				
				<th>
					&nbsp;
				</th>
				<th>
					<html:submit>查 询</html:submit>
				</th>
			</tr>
			<tr>
				<td>
					员工：
				</td>
				<td>
				<html:text property="pworkset.em" size="12"  styleId="names2" onkeyup="findNames2();"  />
                       <div style="position:absolute;" id="popup2">
				         <table id="name_table2" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="name_table_body2"></tbody>
				         </table>  
                      </div>
				</td>
				</tr>
		</table>
		<br>

		<table cellspacing="1" cellpadding="1" bgcolor="#6CA6CD" border="0" width="100%">
			<tr>
				<td width="60" align="left" bgcolor="#B0C4DE">
					员工编号
					
				</td>
				<td width="65" align="left" bgcolor="#B0C4DE">
					姓名
				</td>
				<td width="65" align="left" bgcolor="#B0C4DE">
					卡号
				</td>
				
				<td width="75" align="left" bgcolor="#B0C4DE">
					工种
				</td>
				</tr>
			<logic:present name="mountList" scope="request">
				<logic:iterate name="mountList" id="mount">
					<tr>
						<td align="left" bgcolor="#E6E6FA">
							<a href="pworkset.do?method=siteinit&stafferid=<bean:write name="mount" property="stafferid" />&name=<bean:write name="mount" property="name" />&worktype=<bean:write name="mount" property="worktype" />" title="地点设置" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="topFrame">
							<bean:write name="mount" property="stafferid" />
							</a>
						</td>
						<td align="left" bgcolor="#E6E6FA">
						
						<a href="pworkset.do?method=timeinit&stafferid=<bean:write name="mount" property="stafferid" />&name=<bean:write name="mount" property="name" />&worktype=<bean:write name="mount" property="worktype" />" title="时间设置" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="downFrame">
							<bean:write name="mount" property="name" />
						</a>
							
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="mount" property="cardid" />
						</td>
						
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="mount" property="worktype" />
						</td>
						</tr>
				</logic:iterate>
			</logic:present>
		</table>
	</html:form>
</body>
</html:html>
