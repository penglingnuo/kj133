<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="历史信息查询"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
<html:base />
<title>历史信息查询</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
<script language="JavaScript">
		  document.oncontextmenu=new Function("event.returnValue=false;"); 
    </script>
<script language="javascript" src="js/page.js"></script>
<link rel="stylesheet" href="js/jqueryui/cupertino/jquery-ui.css"
	type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui-min.js"></script>
<script language="javascript" src="js/StafferORName.js"></script>
<script type="text/javascript" src="js/showbackupaner.js"></script>
<script type="text/javascript" src="js/dateJs/WdatePicker.js"
	defer="defer"></script>
<script language="javascript" src="js/export_xls.js"></script>
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
		    //WebBrowser1.ExecWB(7, 1); //预览
		}
		</SCRIPT>
<SCRIPT language="JavaScript">
        function check()
          {
               var start=document.all['search_workatt.starttime'].value;
               var end=document.all['search_workatt.endtime'].value;
	           if(start>end){
	                 alert('起始时间不能大于截止时间，请重新输入');
	                 return false;
	               }
               return true;
          }
    </SCRIPT>

<script type="text/javascript">
    	function gjdetail(cardid,downtime,uptime){
			$.ajax({
			  type: "POST",
			  data:{cardid:cardid,downtime:downtime,uptime:uptime},
			  url: "/kj133/workatthis.do?method=getAreaList",
			  dataType:"json",
			  success: function(data){
					$(data.workatt_list_staffer_his).each(function(){
						$("#table1 > tbody").empty();
						$("#table1 > tbody").append('<tr>'
							+'<td >员工号：'+this.stafferid+'</td>'
							+'<td >姓名：'+this.name+'</td>'
							+'<td >卡号：'+this.cardid+'</td>'
							+'<td >部门：'+this.department+'</td>'
							+'<td >职务：'+this.occupation+'</td>'
							+'<td >下井时间：'+this.downtime+'</td>'
							+'<td >升井时间：'+this.uptime+'</td>'
							+'<td >工作时长：'+this.worktime+'</td>'
							+'</tr>');
					});
					$("#table2 > tbody").empty();
					$(data.area_list).each(function(){
						$("#table2 > tbody").append('<tr>'
							+'<td align="center">'+this.starttime+'</td>'
							+'<td align="center">'+this.endtime+'</td>'
							+'<td align="center">'+this.stayinterval+'</td>'
							+'<td align="center">'+this.crname+'</td>'
							+'</tr>');
					});
			   $("#detail").dialog("open");
			  }
			});
		
		}
		
	 $(function(){
	 
	 	$("#detail").dialog({
	          		title: "轨迹明细",
	          		width: 950,
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
<link href="Css/TestDate.css" rel="stylesheet">
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
</head>

<body bgColor="white" background="Image/right.gif">
	<logic:messagesPresent message="true">
		<html:messages id="message" message="true">
			<font color="red"><bean:write name="message" /> </font>
		</html:messages>
	</logic:messagesPresent>

	<html:form action="/workatthis?method=getList"
		focus="search_workatt.cardid" onsubmit="return check()">

		<table width="100%" align="center">
			<tr>
				<th>员&nbsp;工：</th>
				<th><html:text property="search_workatt.cardid" styleId="name" style="width: 125px;">
				</html:text>
		</th>
		<th>&nbsp;</th>
		<th>部&nbsp;门：</th>
		<th>
			<div style="position: relative;">
				<span style="margin-left: 82px; width: 18px; overflow: hidden;">
					<select style="width: 118px; margin-left: -100px;height: 21px;" name="search_workatt.group">
						<logic:present name="gro_list">
							<option value=""></option>
							<logic:iterate id="aa" name="gro_list">
								<option value='<bean:write name="aa" property="wordvalue"/>'>
									<bean:write name="aa" property="wordvalue" />
								</option>
							</logic:iterate>
						</logic:present>
				</select> </span>
			</div></th>

		<th align="right">开始日期:</th>
		<th><html:text property="search_workatt.starttime" size="15"
				styleId="file9" disabled="false"
				onfocus="WdatePicker({skin:'whyGreen',isShowClear:true,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_shuaka.starttime')})" />
		</th>
		<th align="right">结束日期:</th>
		<th><html:text property="search_workatt.endtime" size="15"
				styleId="file9" disabled="false"
				onfocus="WdatePicker({skin:'whyGreen',isShowClear:true,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_shuaka.endtime')})" />
		</th>


		<th align="right"><html:submit>查 询</html:submit>
		</th>

		<th align="right"><logic:equal name="listCount" value="0">
				<input type="button" value="打印" disabled>
			</logic:equal> <logic:notEqual name="listCount" value="0">
				<input type="button" id="print" value="打印" class="button_bak"
					onclick="javascript:printdiv();" />
			</logic:notEqual></th>
		<th><logic:equal name="listCount" value="0">
				<input type="button" value="导出excel" disabled>
			</logic:equal> <logic:notEqual name="listCount" value="0">
				<input type="button" value="导出excel"
					onclick="ExportXls('workatthis.do?method=doOpenExcel')">

			</logic:notEqual></th>

		</tr>
		</table>
		<div id="printdiv">
			<table width="100%" cellspacing="1" cellpadding="1" bgcolor="#6CA6CD"
				border="0" align="center"
				style="border-collapse: collapse; TABLE-LAYOUT: fixed; FONT-SIZE: 13px;">
				<tr>
					<td width="8%" align="center" bgcolor="#B0C4DE">姓名</td>
					<td width="5%" align="center" bgcolor="#B0C4DE">卡号</td>
					<td width="15%" align="center" bgcolor="#B0C4DE">部门</td>
					<td width="10%" align="center" bgcolor="#B0C4DE">职位</td>
					<td width="17%" align="center" bgcolor="#B0C4DE">下井时间</td>
					<td width="17%" align="center" bgcolor="#B0C4DE">出井时间</td>
					<td width="17%" align="center" bgcolor="#B0C4DE">下井时长</td>
					<td width="11%" align="center" bgcolor="#B0C4DE">轨迹列表</td>
				</tr>
				<logic:present name="workatthis_list" scope="request">
					<logic:iterate name="workatthis_list" id="dw" indexId="index">
						<tr>
							<td align="center" bgcolor="#E6E6FA"><bean:write name="dw"
									property="name" />
							</td>
							<td align="left" bgcolor="#E6E6FA"><bean:write name="dw"
									property="cardid" />
							</td>
							<td align="left" bgcolor="#E6E6FA"><bean:write name="dw"
									property="group" />
							</td>
							<td align="left" bgcolor="#E6E6FA"><bean:write name="dw"
									property="occupation" />
							</td>
							<td align="center" bgcolor="#E6E6FA"><bean:write name="dw"
									property="downtime" />
							</td>
							<td align="center" bgcolor="#E6E6FA"><bean:write name="dw"
									property="uptime" />
							</td>
							<td align="center" bgcolor="#E6E6FA"><bean:write name="dw"
									property="worktime" />
							</td>
							<td align="center" bgcolor="#E6E6FA"><html:link
									onclick="gjdetail(${dw.cardid},'${dw.downtime}','${dw.uptime}')"
									href="javascript:void(0)">明细</html:link></td>
						</tr>
					</logic:iterate>
				</logic:present>

				<logic:present name="pagination">
					<tr>
						<td colspan="8" align="center" bgcolor="#E6E6FA"><page:pagination
								path="workatthis.do?method=getList" parameter="page"
								formName="Search_WorkAtt_Form" /></td>
					</tr>
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
