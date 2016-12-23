<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>personInMineAreaNumber.jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/thickbox/thickbox.js"></script>
	<script type="text/javascript" src="js/jquery.timers.js"></script>
	<link rel="stylesheet" href="js/thickbox/thickbox.css" type="text/css" media="screen" />
	
	<style type="css/text">
		.showDataTable{
			BORDER: #b9b8be 1px solid;
		}
	</style>
	
	<script type="text/javascript">
		$(function() {
			$("#dataTable tr:gt(1)").find("td:gt(1)").find("a").bind("click",function(){
				getData($(this));
			}).hover(function(){
				$("this").css("background-color","#DBE2F5");
			}, function(){
				$("this").css("background-color","");
			});
        });
        
        //各行变色
        $(function(){
        	$("#dataTable tr:even").css("background-color","#DBE2F5");
        });
        
        function getData(obj) {
        	//获得基站信息
			var cardreader = $("#dataTable tr:eq(0)").find("td:eq("+ $(obj).parent().attr("i") +")").attr("cri");
			//获得部门信息
			var department = $(obj).parent().parent().find("td:eq(0)").html();
			
			department = department.Trim();
			
			$("#showDataTable tr[r='o']").remove();
        
        	$.ajax({
				type: "GET",
				url: "workperson.do?method=detailedData&tempId=" + Math.random() * 1000,
//				data : "c=" + cardreader + "&d=" + encodeURIComponent(department),
				data : "c=" + cardreader + "&d=" + department,
				dataType : "text",
				error : function(){
					getData(obj);
				},
				beforeSend : function(){
					//debugger;
				},
				success: function(msg){
					var json = eval('('+ msg +')'); 
					
					$(json.data).each(function(i,n){
						var row = $("#templateTR").clone();
						
						row.removeAttr("id");
						row.attr("r","o");
						
						row.find("#0").text(n.cardid);
						row.find("#1").text(n.stafferid);
						row.find("#2").text(n.staffername);
						row.find("#3").text(n.stafferdepartment);
						row.find("#4").text(n.downtime);
						row.find("#5").text(n.betweentime);
						row.find("#6").text(n.cardreadername);
						
						row.show();
						row.css("cursor", "hand");
						
						if(i % 2 == 0) {
							row.css("background-color","#DBE2F5");
						}
						
						row.bind("click", function(){
							var url = "telezone/map.jsp";
							parent.rightFrame.setCardID(n.cardid);
								
							if(n.staffername == "未绑定") {
								url += "?mapid="+n.map+"&type=1&cardid=" + n.cardid;
							}else {
								url += "?mapid="+n.map+"&type=1&cardid=" + n.cardid+"&stafferid="+n.stafferid;
							}
							
							if(n.cwebx == 0 && n.lwebx != 0) {
								url += "&x="+n.lwebx+"&y="+n.lweby;
							}else {
								url += "&x="+n.cwebx+"&y="+n.cweby;
							}
							
							parent.frames["content"].location.href = url;
						});
						
						row.hover(function(){
							row.css("background-color","#C5D6FC");
						},function(){
							if(i % 2 == 0) {
								row.css("background-color","#DBE2F5");
							}else {
								row.css("background-color","");
							}
						});
						
						$("#showDataTable").append(row);
					}); 
			   }
			}); 
			
        }
        
        String.prototype.Trim = function() { 
			return this.replace(/(^\s*)|(\s*$)/g, ""); 
		} 
	</script>
  </head>
  
  <body bgColor="white" background="Image/right.gif" style="width: 100%;">
  	<div style="font-weight: bold; font-size: 13px; background-repeat: repeat; background-image: url(Image/title.bmp);">
  		&gt;&gt;当前位置：当前井下人员汇总
  	</div>
  	<div id="showData">
  		<table cellpadding="0" cellspacing="0" border="1" style="border: 1px solid #B9B8BE; font-size: 12px; text-align: center; width: 100%;" id="dataTable">
  			<tr style="font-weight: bold;">
  				<td style="width: 10%;">部门名称</td>
  				<td style="width: 5%;">合计</td>
  				<c:forEach items="${ CardReader }" var="cr" varStatus="status">
	  				<td cri="${ cr.cardreaderid }" style="width: 5%;">${ cr.crname }</td>
  				</c:forEach>
  			</tr>
  			<tr style="font-weight: bold;">
  				<c:forEach items="${ personInCardReaderList }" var="cr">
	  				<td>${ cr }</td>
  				</c:forEach>
  			</tr>
  			<c:forEach items="${ resultList }" var="cr">
  				<tr>
  					<c:forEach items="${ cr }" var="cr1" varStatus="status">
  						<c:if test="${ status.index < 2 }">
  							<td i="${ status.index }">
		  						${ cr1 }
		  					</td>
  						</c:if>
  						<c:if test="${ status.index >= 2 }">
  							<td i="${ status.index }">
		  						<a class="thickbox" style="width: 100%; height: 100%; cursor: hand;"
		  						href="#TB_inline?height=400&width=700&inlineId=showDataHideDiv" title="人员详细信息">
			  						${ cr1 }
		  						</a>
		  					</td>
  						</c:if>
  					</c:forEach>
  				</tr>
			</c:forEach>
  		</table>
  	</div>
  	
  	
  	<div id="showDataHideDiv" style="display: none;">
  		<table id="showDataTable" cellpadding="0" cellspacing="0" border="1" style="width: 100%; text-align: center; font-size: 12px; overflow: auto;" class="showDataTable">
  			<tr id="templateTR" style="display: none; width: 100%">
  				<td id="0"></td>
  				<td id="1"></td>
  				<td id="2"></td>
  				<td id="3"></td>
  				<td id="4"></td>
  				<td id="5"></td>
  				<td id="6"></td>
  			</tr>
  			<tr>
  				<td>卡号</td>
  				<td>员工号</td>
  				<td>姓名</td>
  				<td>部门</td>
  				<td>下井时间</td>
  				<td>下井时长</td>
  				<td>所属基站</td>
  			</tr>
  		</table>
  	</div>
  </body>
</html>
