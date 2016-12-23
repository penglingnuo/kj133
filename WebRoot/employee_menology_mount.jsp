<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="员工考勤设置"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>employee_menology_mount.jsp</title>
	<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<link href="Css/TestDate.css" rel="stylesheet">
	<%--背景--%>
	<script language="javascript" src="js/StafferORName.js"></script>
	<SCRIPT language="JavaScript">
       function CheckAll(form)
			{
				for (var i=0;i<form.elements.length;i++){
					var e = form.elements[i];
					if (e.name == 'id')
					e.checked = form.checkall.checked;
				}
			}
			
			function DeleteSomeLine(form)
			{
			    if (SelectedCounts(form,"id")>0){
				    if(confirm("删除已选中的记录,将无法恢复！\n     确定要这样做吗？"))
				    {
				     var str="";
				     var init=document.getElementsByName('id');
				     for(var i=0;i<init.length;i++)
				       {
				         if(init[i].checked)
				             str+=(','+init[i].value);       
				       }
				     location.href="employee_menology_mount.do?method=delete&sid="+str.substring(1);   
					 return true;
				    }
			   }else{
				    alert("请先选择要删除的记录!");
				    return false;
			    }
			}
			//扫描已选复选框的个数
			//参数:表单名,元素名
			function SelectedCounts(form,ItemID)
			{
			  var SelectedCounts=0;  //初始选中个数为0
			  var f=form;
			  for (i=0;i<f.elements.length;i++)
			    if (f.elements[i].name==ItemID && f.elements[i].checked==true)
				    {
			          SelectedCounts++;
					 }
			  return SelectedCounts;
			}
			
			function add(){
			  window.location.href='employee_menology_mount.do?method=loadAdd';
			}
    </SCRIPT>
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
</head>

<body bgColor="white" background="Image/right.gif">
	<html:form action="employee_menology_mount?method=getList">

		<table border="0">
			<tr>
				<th>
					卡号：
				</th>
				<th>
					<html:text property="mount.sid" size="16" styleId="names2"
						onkeyup="findNames2();" />
					<div style="position:absolute;" id="popup2">
						<table id="name_table2" bgcolor="#FFFAFA" border="0"
							cellspacing="0" cellpadding="0" />
							<tbody id="name_table_body2"></tbody>
						</table>
					</div>
				</th>
				<th>
					&nbsp;
				</th>
				<th>
					工种：
				</th>
				<th>
					<html:select property="mount.worktype" style="width:90px">
						<html:option value=""></html:option>
						<html:options collection="workType_list" property="wordvalue"
							labelProperty="wordvalue" />
					</html:select>
				</th>
				<th>
					&nbsp;
				</th>
				<th>
					<html:submit>查  询</html:submit>
				</th>
				<th>
					&nbsp;
				</th>
				<th>
					<input type="button" value="删  除"
						onclick="DeleteSomeLine(Search_Employee_menology_mount_Form)" />
				</th>
				<th>
					&nbsp;
				</th>
				<th>
					<input type="button" value="设  置" onclick="add()" />
				</th>
			</tr>
		</table>
		<br>

		<table cellspacing="1" cellpadding="1" bgcolor="#6CA6CD" border="0">
			<tr>
				<td width="130" align="left" bgcolor="#B0C4DE">
					员工编号
					<input type="checkbox" name="checkall" value="checkbox"
						onClick="CheckAll(Search_Employee_menology_mount_Form);">
					(全选)
				</td>
				<td width="65" align="left" bgcolor="#B0C4DE">
					姓名
				</td>
				<td width="65" align="left" bgcolor="#B0C4DE">
					卡号
				</td>
				<td width="105" align="left" bgcolor="#B0C4DE">
					最小工作时间
				</td>
				<td width="75" align="left" bgcolor="#B0C4DE">
					工种
				</td>
				<td width="85" align="left" bgcolor="#B0C4DE">
					班组
				</td>
			</tr>
			<logic:present name="mountList" scope="request">
				<logic:iterate name="mountList" id="mount">
					<tr>
						<td align="left" bgcolor="#E6E6FA">
							<input type="checkbox" name="id"
								value="<bean:write name='mount' property='stafferid'/>" />
							<bean:write name="mount" property="stafferid" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="mount" property="name" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="mount" property="cardid" />
						</td>
						
		                  <logic:equal    name="mount" property="minworktime"  value="0">
		                      <td align="left" bgcolor="#E6E6FA"><FONT color="#F000000">空</FONT></td>
		                  </logic:equal>
		                  
						<logic:notEqual name="mount" property="minworktime" value="0">
							<td align="left" bgcolor="#E6E6FA">
								<bean:write name="mount" property="minworktime" />
							</td>
						</logic:notEqual>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="mount" property="worktype" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="mount" property="group" />
						</td>
					</tr>
				</logic:iterate>
			</logic:present>
		</table>
	</html:form>
</body>
</html:html>
