<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="实时信息"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
<title>downwell_menology.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
<script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script>
<link href="Css/TestDate.css" rel="stylesheet">
<link href="Css/calendar-blue.css" rel="stylesheet">
<%--背景--%>
<link rel="stylesheet" href="js/jqueryui/cupertino/jquery-ui.css"
	type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui-min.js"></script>
<script type="text/javascript" src="js/jquery.timers.js"></script>
<script language="javascript" src="js/page.js"></script>
<script type="text/javascript" src="js/dateJs/WdatePicker.js"
	defer="defer"></script>
<script language="javascript" src="js/export_xls.js"></script>

<style type="text/css">
#table2,#table1 {
	border-collapse: collapse;
}

#table2 td {
	border: 1px solid #6CA6CD;
	background-color: #B0C4DE;
	border-top: none;
}

#table2 th {
	border: 1px solid #6CA6CD;
	background-color: #B0C4DE;
}

#table1 td,#table1 th {
	border: 1px solid #6CA6CD;
	background-color: #6CA6CD;
	border-bottom: none;
}
</style>

<script type="text/javascript">
    function OpenExcel(){
			  window.parent.location.href="workatt.do?method=doOpenExcel";
			     return true;
		    }
    </script>
<script type="text/javascript">
    	function refresh(){
		window.location.href="workatt.do?method=getList";
	}
	function gjdetail(cardid,downtime){
			$.ajax({
			  type: "POST",
			  data:{cardid:cardid,downtime:downtime},
			  url: "/kj133/workatt.do?method=getAll",
			  dataType:"json",
			  success: function(data){
			  	if(data.note==""){
			  		$(data.workatt_list_staffer).each(function(){
						$("#table1 > tbody").empty();
						$("#table1 > tbody").append('<tr>'
							+'<td >员工号：'+this.stafferid+'</td>'
							+'<td >姓名：'+this.name+'</td>'
							+'<td >卡号：'+this.cardid+'</td>'
							+'<td >部门：'+this.department+'</td>'
							+'<td >职务：'+this.occupation+'</td>'
							+'<td >下井时间：'+this.downtime+'</td>'
							+'<td >工作时长：'+this.worktime+'</td>'
							+'</tr>');
					});
					$("#table2 > tbody").empty();
					$(data.workatt_list).each(function(){
						$("#table2 > tbody").append('<tr>'
							+'<td align="center">'+this.starttime+'</td>'
							+'<td align="center">'+this.endtime+'</td>'
							+'<td align="center">'+this.stayinterval+'</td>'
							+'<td align="center">'+this.crname+'</td>'
							+'</tr>');
					});
			   $("#detail").dialog("open");
			  	}else{
			  		alert(data.note);
			  	}
			  }
			});
		}
	 $(document).ready(function(){
	 	$("#detail").dialog({
		          		title: "轨迹明细",
		          		width: 800,
		          		height: 460,
		          		position: "center",
		          		autoOpen: false
		          	});
		//用户名模糊查询
   	 	$("#name").autocomplete({
    	source: function(request, response) {
                $.ajax({
                    url: "common.do?method=staffer",
                    dataType: "json",
                    data: {
                    	c:$("#name").val()
                    },
                    success: function(data) {
                       var names=[];
                       $(data.staffer).each(function(i, n){
                       names.push(this.cardId+"--"+this.name);
					   });
					
						response(names);
                    }
                });
            }
	}); 
}); 
    </script>
<SCRIPT language="JavaScript">
    	//打印功能
		function printdiv() {
		    var newWin = window.open('printer', '', '');
		    var titleHTML = document.getElementById("printdiv").innerHTML;
		    titleHTML = titleHTML.toString().replace("border=0", "border=1");
		    titleHTML = titleHTML.toString().replace("cellSpacing=1", "cellSpacing=0");
		    newWin.document.write(titleHTML);
		    newWin.document.location.reload();
		    newWin.print();
		    newWin.close();
		}

	</SCRIPT>
</head>
<body bgColor="white" background="Image/right.gif">
	<logic:messagesPresent message="true">
		<html:messages id="message" message="true">
			<font color="red"><bean:write name="message" /> </font>
		</html:messages>
	</logic:messagesPresent>
	<html:form action="workatt.do?method=getList"
		focus="search_workatt.cardid">
		<table width="100%">
			<tr>
				<th align="left">员&nbsp;工：</th>
				<th align="left">
				<html:text property="search_workatt.cardid" styleId="name" style="width: 125px;">
				</html:text>
				</th>
		<th>&nbsp;&nbsp;</th>
		<th align="left">部门:</th>
		<th align="left">
			<div style="position:relative;">
				<span style="margin-left:100px;width:18px;overflow:hidden;">
					<select style="width:118px;margin-left:-100px" property="search_workatt.group"
					 onchange="this.parentNode.nextSibling.value=this.value" >

						<logic:present name="gro_list">
							<option value=""></option>
							<logic:iterate id="aa" name="gro_list">
								<option value='<bean:write name="aa" property="wordvalue"/>'><bean:write name="aa" property="wordvalue" /></option>
							</logic:iterate>
						</logic:present>
				</select> </span>
				<html:text property="search_workatt.group"
					style="width:100px;position:absolute;left:0px;"></html:text>
			</div>
		</th>

		<th>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			当前井下总人数：&nbsp;&nbsp;<font color="red" size=6>${countall}</font>&nbsp;&nbsp;人
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</th>
		<th><a href="javascript:void(0)" onclick="refresh()"
			style="text-decoration:none;"><font color="blue">刷新</font> </a>
		</th>
		<th><html:submit>查询</html:submit></th>
		<th>&nbsp;&nbsp;</th>
		<th><logic:equal name="listCount" value="0">
				<input type="button" id="print" value="打印" class="button_bak"
					onclick="javascript:printdiv();" />
			</logic:equal> <logic:notEqual name="listCount" value="0">
				<input type="button" id="print" value="打印" class="button_bak"
					onclick="javascript:printdiv();" />
			</logic:notEqual>
		</th>
		<th><input type="button" value="导出excel"
			onclick="ExportXls('workatt.do?method=doOpenExcel')">
		</th>
		</tr>
		</table>
		<br />
		<div id="printdiv">
			<table width="100%" cellspacing="1" cellpadding="1" bgcolor="#6CA6CD"
				border="0"
				style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
				<tr>
					<th><font style="font-family:verdana" color="red">*红色为呼救报警</font>
					</th>
					<th><font style="font-family:verdana" color="blue">*蓝色为超时报警</font>
					</th>
				</tr>
				<tr>
					<td width="10%"  align="left" bgcolor="#B0C4DE">姓名</td>
					<td width="10%" align="left" bgcolor="#B0C4DE">卡号</td>
					<td width="10%" align="left" bgcolor="#B0C4DE">部门</td>
					<td width="10%" align="left" bgcolor="#B0C4DE">职务</td>
					<td width="15%" align="left" bgcolor="#B0C4DE">下井时间</td>
					<td width="10%" align="left" bgcolor="#B0C4DE">工作时长</td>
					<td width="10%" align="left" bgcolor="#B0C4DE">当前分站</td>
					<td width="15%" align="left" bgcolor="#B0C4DE">进入分站时间</td>
					<td width="10%" align="left" bgcolor="#B0C4DE">轨迹列表</td>
				</tr>
				<logic:present name="workatt_list">
					<logic:iterate name="workatt_list" id="workatt">
						<tr>

							<%--  呼救+ 超时     --%>
							<logic:equal name="workatt" property="overstate" value="2">
								<logic:equal name="workatt" property="state" value="0">
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="name" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="cardid" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="group" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="occupation" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="downtime" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="worktime" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="crname" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="starttime" /> </FONT></td>
									<td align="center" bgcolor="#E6E6FA"><FONT color="red"><html:link
												onclick="gjdetail(${workatt.cardid},'${workatt.downtime}')"
												href="javascript:void(0)">明细</html:link> </FONT>
									</td>
								</logic:equal>
							</logic:equal>

							<%--  只 呼救      --%>
							<logic:equal name="workatt" property="state" value="0">
								<logic:equal name="workatt" property="overstate" value="3">
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="name" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="cardid" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="group" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="occupation" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="downtime" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="worktime" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="crname" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="red"><bean:write
												name="workatt" property="starttime" /> </FONT></td>
									<td align="center" bgcolor="#E6E6FA"><FONT color="red"><html:link
												onclick="gjdetail(${workatt.cardid},'${workatt.downtime}')"
												href="javascript:void(0)">明细</html:link> </FONT>
									</td>
								</logic:equal>
							</logic:equal>
							<%--  只 超时     --%>
							<logic:equal name="workatt" property="overstate" value="2">
								<logic:equal name="workatt" property="state" value="1">
									<td align="left" bgcolor="#E6E6FA"><FONT color="blue"><bean:write
												name="workatt" property="name" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="blue"><bean:write
												name="workatt" property="cardid" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="blue"><bean:write
												name="workatt" property="group" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="blue"><bean:write
												name="workatt" property="occupation" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="blue"><bean:write
												name="workatt" property="downtime" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="blue"><bean:write
												name="workatt" property="worktime" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="blue"><bean:write
												name="workatt" property="crname" /> </FONT></td>
									<td align="left" bgcolor="#E6E6FA"><FONT color="blue"><bean:write
												name="workatt" property="starttime" /> </FONT></td>
									<td align="center" bgcolor="#E6E6FA"><FONT color="blue"><html:link
												onclick="gjdetail(${workatt.cardid},'${workatt.downtime}')"
												href="javascript:void(0)">明细</html:link> </FONT>
									</td>
								</logic:equal>
							</logic:equal>

							<%--   正常   --%>
							<logic:equal name="workatt" property="overstate" value="3">
								<logic:equal name="workatt" property="state" value="1">
									<td align="left" bgcolor="#E6E6FA"><bean:write
											name="workatt" property="name" /></td>
									<td align="left" bgcolor="#E6E6FA"><bean:write
											name="workatt" property="cardid" /></td>
									<td align="left" bgcolor="#E6E6FA"><bean:write
											name="workatt" property="group" /></td>
									<td align="left" bgcolor="#E6E6FA"><bean:write
											name="workatt" property="occupation" /></td>
									<td align="left" bgcolor="#E6E6FA"><bean:write
											name="workatt" property="downtime" /></td>
									<td align="left" bgcolor="#E6E6FA"><bean:write
											name="workatt" property="worktime" /></td>
									<td align="left" bgcolor="#E6E6FA"><bean:write
											name="workatt" property="crname" /></td>
									<td align="left" bgcolor="#E6E6FA"><bean:write
											name="workatt" property="starttime" /></td>
									<td align="center" bgcolor="#E6E6FA"><html:link
											onclick="gjdetail(${workatt.cardid},'${workatt.downtime}')"
											href="javascript:void(0)">明细</html:link></td>
								</logic:equal>
							</logic:equal>
						</tr>
					</logic:iterate>
				</logic:present>
				<logic:present name="pagination">
					<TR>
						<TD colspan="9" align="center" bgcolor="#E6E6FA"><page:pagination
								path="workatt.do?method=getList" parameter="page"
								formName="Search_WorkAtt_Form" />
						</TD>
					</TR>
				</logic:present>
			</table>
		</div>

	</html:form>

	<div id="detail">
		<h3 align="center">轨迹明细</h3>
		<table id="table1" border="0" cellspacing="0" cellpadding="0"
			width="100%">
			<tbody></tbody>
		</table>
		<table id="table2" border="0" cellspacing="0" cellpadding="0"
			width="100%">
			<thead>
				<tr>
					<th align="center" width="25%">进入时间</th>
					<th align="center" width="25%">离开时间</th>
					<th align="center" width="25%">停留时间</th>
					<th align="center" width="25%">停留分站</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
</body>
</html:html>
