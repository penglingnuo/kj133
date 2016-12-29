<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="查询员工信息"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />
	<title>queryEmployee.jsp</title>
	<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<script language="JavaScript">
		  document.oncontextmenu=new Function("event.returnValue=false;"); 
    </script>
	<script language="javascript" src="js/page.js"></script>
	<link rel="stylesheet"  href="js/jqueryui/cupertino/jquery-ui.css" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery-ui-min.js"></script>
	<script language="javascript" src="js/StafferORName.js"></script>
	<script type="text/javascript" src="js/showbackupaner.js"></script>
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
	<link href="Css/TestDate.css" rel="stylesheet">
	<%--背景--%>
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

	<script type="text/javascript">
    function OpenExcel(){
			  window.parent.location.href="EmployeeQuery.do?method=doOpenExcel";
			     return true;
		    }
	$(function(){
		
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

</head>

<body bgColor="white" background="Image/right.gif">
	<br>
	<logic:messagesPresent message="true">
		<html:messages id="message" message="true">
			<font color="red"><bean:write name="message" /> </font>
		</html:messages>
	</logic:messagesPresent>

	<html:form action="/EmployeeQuery?method=getStaffer"
		focus="search_employee.cardid">
		<table width="100%">
			<tr>
				<th align="left">
					员&nbsp;工：
				</th>
				<th align="left">
					<html:text property="search_employee.cardid" styleId="name" style="width: 125px;">
				</html:text>
				</th>
				<th>
					&nbsp;
				</th>
				<th align="left">
					部&nbsp;门：
				</th>
				<th align="left">
			<div style="position:relative;">
				<span style="margin-left:100px;width:18px;overflow:hidden;">
					<select style="width:118px;margin-left:-100px;height: 21px;" name="search_employee.group">
						<logic:present name="gro_list">
							<option value=""></option>
							<logic:iterate id="aa" name="gro_list">
								<option value='<bean:write name="aa" property="wordvalue"/>'>
									<bean:write name="aa" property="wordvalue" />
								</option>
							</logic:iterate>
						</logic:present>
				</select> </span>
			</div>
				</th>
				<th>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				</th>
				<th>
					<html:submit>查 询</html:submit>
					&nbsp;
						<%-- 
			             &nbsp;<input type="button"  value="打印预览"  onclick="javascript:window.open('queryEmployeePrint.jsp')">
			         --%>
				</th>
				<th>
					<logic:equal name="listCount" value="0">
			              &nbsp;<input type="button" value="打印预览" disabled>
					</logic:equal>
					<logic:notEqual name="listCount" value="0">
						<input type="button" id="print" value="打印" class="button_bak"
							onclick="javascript:printdiv();" />
					</logic:notEqual>
				</th>
				<th>
					<logic:equal name="listCount" value="0">
						<input type="button" value="导出excel" disabled>
					</logic:equal>
					<logic:notEqual name="listCount" value="0">
						<input type="button" value="导出excel" onclick="OpenExcel()">

					</logic:notEqual>
				</th>
			</tr>
		</table>
		<br>
		<div id="printdiv">
			<TABLE width="100%" cellspacing="1" cellpadding="1" bgcolor="#6CA6CD" border="0"
				style="border-collapse: collapse; TABLE-LAYOUT: fixed; FONT-SIZE: 13px;">
				<TR>
					<TD align="left" width="15%" bgcolor="#B0C4DE">
						姓名
					</TD>
					<TD align="left" width="15%" bgcolor="#B0C4DE">
						卡号
					</TD>
					<TD align="left" width="15%" bgcolor="#B0C4DE">
						部门
					</TD>
					<TD align="left" width="25%" bgcolor="#B0C4DE">
						班组
					</TD>
					<TD align="left" width="15%" bgcolor="#B0C4DE">
						工种
					</TD>
					<TD align="left" width="15%" bgcolor="#B0C4DE">
						职务
					</TD>
				</TR>
				<logic:present name="stafferList" scope="request">
					<logic:iterate id="stafferList" name="stafferList">
						<TR>
							<TD bgcolor="#E6E6FA">
								<bean:write name="stafferList" property="username" />
							</TD>
							<logic:equal name="stafferList" property="cardid" value="">
								<TD bgcolor="#696969"></TD>
							</logic:equal>
							<logic:notEqual name="stafferList" property="cardid" value="">
								<TD bgcolor="#E6E6FA">
									<bean:write name="stafferList" property="cardid" />
								</TD>
							</logic:notEqual>
							<TD bgcolor="#E6E6FA">
								<bean:write name="stafferList" property="department" />
							</TD>
							<TD bgcolor="#E6E6FA">
								<bean:write name="stafferList" property="gro" />
							</TD>
							<TD bgcolor="#E6E6FA">
								<bean:write name="stafferList" property="worktype" />
							</TD>
							<TD bgcolor="#E6E6FA">
								<bean:write name="stafferList" property="occupation" />
							</TD>
						</TR>
					</logic:iterate>
				</logic:present>
				<logic:present name="pagination">
					<TR>
						<TD colspan="6" align="center" bgcolor="#E6E6FA">
							<page:pagination path="EmployeeQuery.do?method=getStaffer"
								name="pagination" parameter="page"
								formName="Search_Employee_Form" />
						</TD>
					</TR>
				</logic:present>
			</TABLE>
		</div>
	</html:form>
</body>
</html:html>
