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

	<title>stripclip_query_top.jsp</title>
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
		    WebBrowser1.ExecWB(7, 1); //预览
		}

	</SCRIPT>


	<script language="javascript" src="js/StafferORName.js"></script>
	<SCRIPT language="JavaScript">
       function check()
         {
	         var stime=document.all['stripclip_query_top.stime'].value;
	         var etime=document.all['stripclip_query_top.etime'].value;

	         if( stime >= etime){
	            alert('起始时间不能大于或等于截止时间');
	            return false;
             }else{
             
             return true;
             }

        }

 
    </SCRIPT>
	<%--
    <%
		
	  String msg=(String)request.getAttribute("msg");
	    
	  if(msg!=null)
	  out.println(msg);
	%>   
        --%>
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
			<font color="red"><bean:write name="message" /> </font>
		</html:messages>
	</logic:messagesPresent>


	<html:form action="stripclip_query_top?method=init"
		onsubmit="return check()">

<%--		<div style="overflow:auto;width:600;height:0;">--%>

			<table>
				<tr>
					<td colspan="2">
						<table>
							<tr>
								<th nowrap>
									下井开始日期：
								</th>
								<th>
									<html:text property="stripclip_query_top.stime" size="18"
										onfocus="show_cele_date('','',Search_stripclip_query_top_Form['stripclip_query_top.stime'])" />
								</th>

								<th>
									&nbsp;&nbsp;
								</th>
								<th nowrap>
									下井时间间隔：
								</th>
								<th>
									<html:text property="stripclip_query_top.space" size="5" />
									分
								</th>
								<th>
									&nbsp;&nbsp;
								</th>
								<th nowrap>
									 比较点数：
								</th>
								<th>
									<html:text property="stripclip_query_top.copnum" size="5" />
									点
								</th>
				
							</tr>
							<tr>
								
								<th nowrap>
									下井结束日期：
								</th>
								<th>
									<html:text property="stripclip_query_top.etime" size="18"
										onfocus="show_cele_date('','',Search_stripclip_query_top_Form['stripclip_query_top.etime'])" />
								</th>
								<th nowrap>
									&nbsp;&nbsp;
								</th>
								<th nowrap>
									最小停留时间：
								</th>
								<th nowrap>
									<html:text property="stripclip_query_top.minitime" size="5" />
									时
								</th>
								<th nowrap>
									&nbsp;&nbsp;
								</th>
								<th nowrap>
									最小相似度：
								</th>
								<th nowrap>
									<html:text property="stripclip_query_top.conform" size="5" />
									%
								</th>
								<th>
									&nbsp;&nbsp;
								</th>
								<th>
									<html:submit>查  询</html:submit>
								</th>
							</tr>
						</table>
					</td>
				</tr>
				<%--	<tr>

				<th>
					&nbsp;&nbsp;
				</th>
				<th>
					&nbsp;&nbsp;
				</th>

				<th>
					&nbsp;&nbsp;
				</th>
				
                 <th  align="left">
                   
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0">  
	                 	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                     <input type="button"  value="打印预览"  onclick="javascript:window.location.href='downwell.do?method=viewPrint'">
	               </logic:notEqual>
                 </th>
              
			</tr> --%>

			<table>
				
				<tr>
					<td>
						<table cellspacing="1" border="0" cellpadding="1" bgcolor="#6CA6CD"
							
							style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
							<tr>
								<td width="30" align="center" bgcolor="#B0C4DE">
									组数
								</td>
								<td width="50" align="center" bgcolor="#B0C4DE">
									卡号
								</td>
								<td width="50" align="center" bgcolor="#B0C4DE">
									员工号
								</td>
								<td width="60" align="center" bgcolor="#B0C4DE">
									姓名
								</td>
								<td width="150" align="center" bgcolor="#B0C4DE">
									下井时间
								</td>
							</tr>
							<logic:present name="relist" scope="request">
								<logic:iterate name="relist" id="relist">
									<tr >
										<td align="center" bgcolor="#E6E6FA">
											<bean:write name="relist" property="teams" />
										</td>
										<td align="left" bgcolor="#E6E6FA">
											<%--<a href="areatime_query_right.do?method=init&areaid=<bean:write name="relist" property="areaid"/>&recordtime=<bean:write name="relist" property="recordtime" />" title="区域人员统计" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="top_right" >
							<bean:write name="relist" property="cardid"/>
							</a>
							--%>
											<bean:write name="relist" property="cardid" />
										</td>
										<td align="left" bgcolor="#E6E6FA">
											<bean:write name="relist" property="stafferid" />
										</td>
										<td align="left" bgcolor="#E6E6FA">
											<bean:write name="relist" property="name" />
										</td>
										<td align="left" bgcolor="#E6E6FA">
											<bean:write name="relist" property="downtime" />
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
							<%--<logic:present name="pagination">
								<tr>
									<td colspan="5" align="left" bgcolor="#E6E6FA">
										<page:pagination path="stripclip_query_top.do?method=init"
											parameter="page" formName="Search_stripclip_query_top_Form" />
									</td>
								</tr>
							</logic:present>


						--%></table>
					</td>
				
					<td>
						<table cellspacing="1" border="0" cellpadding="1" bgcolor="#6CA6CD"
							style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
							<tr>
								<td width="30" align="center" bgcolor="#B0C4DE">
									组数
								</td>
								<td width="50" align="center" bgcolor="#B0C4DE">
									卡号A
								</td>
								<td width="50" align="center" bgcolor="#B0C4DE">
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
							<logic:present name="relist1" scope="request">
								<logic:iterate name="relist1" id="relist1">
									<tr>

										<td align="center" bgcolor="#E6E6FA">
											<bean:write name="relist1" property="teams1" />

										</td>
										<td align="left" bgcolor="#E6E6FA">
											<%--<a href="areatime_query_right.do?method=init&areaid=<bean:write name="relist" property="areaid"/>&recordtime=<bean:write name="relist" property="recordtime" />" title="区域人员统计" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="top_right" >
							<bean:write name="relist" property="cardid"/>
							</a>
							--%>
											<bean:write name="relist1" property="cardidA" />
										</td>
										<td align="left" bgcolor="#E6E6FA">
											<bean:write name="relist1" property="cardidB" />
										</td>
										<td align="left" bgcolor="#E6E6FA">
											<bean:write name="relist1" property="stafferidA" />
										</td>
										<td align="left" bgcolor="#E6E6FA">
											<bean:write name="relist1" property="stafferidB" />
										</td>
										<td align="left" bgcolor="#E6E6FA">
											<bean:write name="relist1" property="nameA" />
										</td>
										<td align="left" bgcolor="#E6E6FA">
											<bean:write name="relist1" property="nameB" />
										</td>
										<td align="left" bgcolor="#E6E6FA">
											<bean:write name="relist1" property="near" />
										</td>
										<td align="left" bgcolor="#E6E6FA">
											<bean:write name="relist1" property="downtimeA" />
										</td>
										<td align="left" bgcolor="#E6E6FA">
											<bean:write name="relist1" property="downtimeB" />
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
							<%--
			<logic:present name="pagination">
				<tr>
					<td colspan="7" align="left" bgcolor="#E6E6FA">
						<page:pagination path="car_move_log.do?method=getList"
							parameter="page" formName="Search_car_move_log_Form" />
					</td>
				</tr>
			</logic:present>
		--%>
						</table>
					</td>
				</tr>
			</table>
			</table>


	</html:form>

</body>

</html:html>

