<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>index_right.jsp</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.timers.js"></script>
		<script type="text/javascript" src="js/jTabs/jquery.tabs.js"></script>
		<script type="text/javascript" src="js/map.js"></script>
		<link type="text/css" rel="stylesheet"
			href="js/jTabs/jquery.tabs-ie.css" media="print, projection, screen">
		<link type="text/css" rel="stylesheet" href="js/jTabs/jquery.tabs.css"
			media="print, projection, screen">
		<link type="text/css" rel="stylesheet" href="Css/index_right.css"
			media="print, projection, screen">

		<script type="text/javascript">
		var tabs,cardID;
		
		$(function() {
			tabs = $('#container-1').tabs({ fxFade: true, fxSpeed: 'fast' });//Tabs标签卡
			
			//更改地图事件
			$("#mapData").bind("change",function(){
				if($(this).attr("value") != 0) {
					if(parent.frames["content"].location.href.indexOf("map.jsp") > 0) {
						parent.content.TelezoneMap.changeMap(""+$(this).attr("value")+"");
					}else {
						parent.frames["content"].location.href = "telezone/map.jsp?mapid=" + $(this).attr("value");
					}
				}
			});
			
			//加载报警信息
			$('body').everyTime('50s', 'AlarmInfo', AlarmInfo,0,true);
			$('body').everyTime('50s', 'GasAlarmInfo', GasAlarmInfo,0,true);
			
			AlarmInfo();
			GasAlarmInfo();
			AreaInfo();
			StafferInMine();
			
			$("ul.tabs-nav span").click(function(){
				var id = $(this).attr("id");
				if(id == 1) {
					$('body').stopTime('AreaInfo'); 
					$('body').stopTime('StafferInMine'); 
				
					$('body').everyTime('50s','GasAlarmInfo', GasAlarmInfo,0,true);
				}else if(id == 2) {
					$('body').stopTime('GasAlarmInfo'); 
					$('body').stopTime('StafferInMine'); 
				
					$('body').everyTime('50s','AreaInfo', AreaInfo,0,true);
				}else if(id == 3) {
					$('body').stopTime('AreaInfo'); 
					$('body').stopTime('GasAlarmInfo'); 
				
					$('body').everyTime('50s','StafferInMine', StafferInMine,0,true);
				}
			});
			
			//点击第三个标签时触发的事件
			$("#fragment-33").click(function(){
				StafferInMine();
			});
        });
        
        $(function(){
        	$("#cancle").bind("click", function(){
        		if(parent.frames["content"].location.href.indexOf("map.jsp") != -1){
        			parent.content.TelezoneMap.cancle();
				}
        		
        		$("#fragment-3 table tr[ready='o']").find("#5").text("定位");
        	});
        });
        
        function getCardID() {
        	return cardID;
        }
        function setCardID(id) {
        	cardID = id;
        }
        
        //页面右侧的全局报警信息
        function AlarmInfo() {
        	$.ajax({
				type: "POST",
				url: "indexright.do?method=alarminfo&tempId=" + Math.random() * 1000,
				dataType: "text",
				success: function(msg){
					if($("#loadingGif")) {
						$("#loadingGif").remove();
					}

					$("#alarmInfoTable tr[ready=o]").remove();
					
					var json = eval('('+ msg +')'); 
					if(json.CardBettyAlarmInfo) {
						if(json.CardBettyAlarmInfo.length > 0) {
							$(json.CardBettyAlarmInfo).each(function(i,n){
								var temp = $("#templateTR").clone();
								
								if(n.alarmInfo.indexOf("呼叫") > 0) {
									temp.css("color", "red");
								}
								
								temp.find("td:eq(0)").text(n.alarmInfo);
								temp.find("td:eq(1)").text(n.stafferName.toString().length == 0 ? "无" : n.stafferName);
								temp.find("td:eq(2)").text(n.subStation);
								temp.find("td:eq(3)").text(n.place);
								temp.find("td:eq(4)").text(n.state);
								
								temp.removeAttr("id");
								temp.attr("ready","o");
								
								temp.show();
								temp.removeAttr("id");
								
								$("#alarmInfoTable").append(temp);
							});
						}
					}
					
					if(json.SubStationAlarmInfo) {
						if(json.SubStationAlarmInfo.length > 0) {
							$(json.SubStationAlarmInfo).each(function(i,n){
								var temp = $("#templateTR").clone();
								
								if(n.alarmInfo.indexOf("呼叫") > 0) {
									temp.css("color", "red");
								}
								
								temp.find("td:eq(0)").text(n.alarmInfo);
								temp.find("td:eq(1)").text(n.stafferName+"号分站");
								temp.find("td:eq(2)").text(n.subStation);
								temp.find("td:eq(3)").text("无");
								temp.find("td:eq(4)").text("无");
								
								temp.removeAttr("id");
								temp.attr("ready","o");
								
								temp.show();
								temp.removeAttr("id");
								
								$("#alarmInfoTable").append(temp);
							});
						}
					}
					
					if(json.OverTime) {
						if(json.OverTime.length > 0) {
							var temp = $("#templateTR").clone();
							temp.find("td:eq(0)").text("井下超时");
							temp.find("td:eq(1)").text("查看明细");
							temp.find("td:eq(2)").text(json.OverTime.length+"人");
							temp.find("td:eq(3)").text("无");
							temp.find("td:eq(4)").text("超时人数:"+json.OverTime.length);
							
							temp.removeAttr("id");
							temp.attr("ready","o");
							
							temp.show();
							temp.removeAttr("id");
							
							temp.bind("dblclick", function(){
								parent.frames["content"].location.href = "workperson.do?method=getOutOfTimeCard&tempId=" + Math.random() * 10000;
							});
							
							$("#alarmInfoTable").append(temp);
						}else {
							if(parent.frames["content"].location.href.indexOf("getOutOfTimeCard") != -1){
								parent.frames["content"].location.href = "telezone/map.jsp";
							}
						}
					}
					
					if(json.OverTime) {
						if(json.overMaxPerson.length > 0) {
							$(json.overMaxPerson).each(function(i,n){
								var temp = $("#templateTR").clone();
								temp.find("td:eq(0)").text("区域超员");
								temp.find("td:eq(1)").text("区域人数："+n.stafferinarea.length);
								temp.find("td:eq(2)").text("区域名："+n.areaname);
								temp.find("td:eq(3)").text("无");
								temp.find("td:eq(4)").text("额定："+n.maxsum+"人");
								
								temp.removeAttr("id");
								temp.attr("ready","o");
								
								temp.show();
								temp.removeAttr("id");
								
								$("#alarmInfoTable").append(temp);
							});
						}
					}
					
					if(json.stopArea) {
						if(json.stopArea.length > 0) {
							$(json.stopArea).each(function(i,n){
								var temp = $("#templateTR").clone();
								temp.find("td:eq(0)").text("区域禁入");
								temp.find("td:eq(1)").text("区域人数："+n.stafferinarea.length);
								temp.find("td:eq(2)").text("区域名："+n.areaname);
								temp.find("td:eq(3)").text("无");
								temp.find("td:eq(4)").text("无");
								
								temp.removeAttr("id");
								temp.attr("ready","o");
								
								temp.show();
								temp.removeAttr("id");
								
								$("#alarmInfoTable").append(temp);
							});
						}
					}
					if(json.overMaxPersonInMine != '0') {
							var temp = $("#templateTR").clone();
							temp.find("td:eq(0)").text("井下超员");
							temp.find("td:eq(1)").text("超员人数：" + json.overMaxPersonInMine);
							temp.find("td:eq(2)").text(" ");
							temp.find("td:eq(3)").text("无");
							temp.find("td:eq(4)").text("无");
							
							temp.removeAttr("id");
							temp.attr("ready","o");
							
							temp.show();
							temp.removeAttr("id");
							
							$("#alarmInfoTable").append(temp);
					}
					
					if(json.LocatorAlarm.length > 0) {
						$(json.LocatorAlarm).each(function(i,n){
							var temp = $("#templateTR").clone();
							temp.find("td:eq(0)").text(n.state);
							temp.find("td:eq(1)").text("无");
							temp.find("td:eq(2)").text("区域名："+n.lname);
							temp.find("td:eq(3)").text("无");
							temp.find("td:eq(4)").text("无");
							
							temp.removeAttr("id");
							temp.attr("ready","o");
							
							temp.show();
							temp.removeAttr("id");
							
							$("#alarmInfoTable").append(temp);
						});
					}
					
					$("#alarmInfoTable tr:gt(0)").hover(function(){//鼠标移上变色
						$(this).css("background-color","#C5D6FC");
					},function(){
						$(this).css("background-color","");
					}).bind("click",function(){//点击事件
						//alert();
					});
					
					if($("#openAlarmSound").attr("checked")) {
					
						$("#alarmInfo").html("<embed src='alarmSound/Audio.wav'></embed>");
						//document.getElementById("wav").play();
						
					}else {
						$("#alarmInfo").html("");
					}
				}
			}); 
        }
        
		//加载瓦斯报警信息
		function GasAlarmInfo() {
			$.ajax({
				type: "POST",
				url: "gas.do?method=gasAlarmInfo",
				//error: function() {},
				dataType: "text",
				success: function(msg){
					var json = eval('('+ msg +')'); 
					
					if(json.data.length == 0) {
						$("#fragment-11 span").css("color","black");
						
						$("#fragment-1 table tr[ready='o']").remove();
						return false;
					}else {
						$("#fragment-11 span").css("color","red");
						
						$("#fragment-1 table tr[ready='o']").remove();
						
						$(json.data).each(function(i,n){
							var row = $("#templateTR2").clone();
							row.find("#1").text(n.cardreaderid);
							row.find("#2").text(n.gasover);
							row.find("#3").text(n.gasConcentration);
							row.find("#4").text(n.state);
							
							row.attr("ready", "o");
							
							row.show();
							row.removeAttr("id");
							
							$("#fragment-1 table").append(row);
						});
					}
				}
			});
		}
		
		//区域信息
		function AreaInfo() {
			$.ajax({
				type: "POST",
				url: "area.do?method=areaInfoOfIndexRight",
				//error: function() {},
				dataType: "text",
				success: function(msg){
					var json = eval('('+ msg +')');
					
					$("#fragment-2 table tr[r='o']").remove();
					
					$(json.Data).each(function(i, n){
						var row = $("#fragment-2 #templateTR3").clone();
						
						row.find("#1").text(n.areaname);
						row.find("#2").text(n.cardreaderAndLocator.length);
						row.find("#3").text(n.stafferOfCardreaderAndLocator);
						row.find("#4").text(n.areatype);
						
						var cardreader="";
						var locator="";
						
						$(n.cardreaderAndLocator).each(function(i, n1){
							if(n1.type == "分站") {
								cardreader += n1.pointid + ",";
							}else if(n1.type == "定位器") {
								locator += n1.pointid + ",";
							}
						});
						
						cardreader = cardreader.substring(0, cardreader.length - 1);
						locator = locator.substring(0, cardreader.length - 1);
						
						row.data("c", cardreader);
						row.data("l", locator);
						row.removeAttr("id");
						row.attr("r", "o");
						row.css("cursor", "hand");
						row.show();
						
						row.bind("click", function(){
							parent.frames["content"].location.href = "area.do?method=areaDetail&c="+$(this).data("c")+"&l="+$(this).data("l")+"&n="+ n.areaname +"&tempId="+Math.random()*10000;
						}).hover(function(){
							$(this).css("background-color","#C5D6FC");
						},function(){
							$(this).css("background-color","");
						});
						
						$("#fragment-2 table").append(row);
					});
				}
			});
		}
		
		//人员定位
		function StafferInMine() {
			$.ajax({
				type: "POST",
				url: "workperson.do?method=getLocationData&tempid=" + Math.random() * 10000,
				dataType : "text",
				error : function(){},
				beforeSend : function(){
					
				},
				success: function(msg){
					$("#fragment-3 table tr[ready='o']").remove();
		
					var json = eval('('+ msg +')'); 
					
					$(json.data).each(function(i, n){
						var row = $("#templateTR1").clone();
						
						row.data("crx", n.crx);
						row.data("cry", n.cry);
						row.data("crid", n.cardreaderid);
						row.data("lx", n.lx);
						row.data("lx", n.lx);
						row.data("lid", n.locatorid);
						
						if(typeof n.staffername == 'undefined') {
							row.find("#1").text("未绑定");
						}else {
							row.find("#1").text(n.staffername);
						}
		
						row.find("#2").text(n.cardid);
						row.find("#3").text(n.crname);
						row.find("#4").text(n.lname);
		
						row.click(function(){
							var type = '1';
							cardID = n.cardid;
							
							if($("#isGenZong").attr("checked")) {
								type = '2';
								$("#fragment-3 table tr[ready='o']").find("#5").text("定位");
								
								row.find("#5").text("跟踪");
							}else {
								$("#fragment-3 table tr[ready='o']").find("#5").text("定位");
							}
							
							var url = "telezone/map.jsp";
							
							if(n.staffername == "未绑定") {
								url += "?mapid="+n.mapid+"&type="+type+"&cardid=" + n.cardid;
							}else {
								url += "?mapid="+n.mapid+"&type="+type+"&cardid=" + n.cardid+"&stafferid="+n.stafferid;
							}
							
							if(n.crx == 0 && n.lx != 0) {
								url += "&x="+n.lx+"&y="+n.ly;
							}else {
								url += "&x="+n.crx+"&y="+n.cry;
							}
							
							parent.frames["content"].location.href = url;
							
							$("#mapData option").removeAttr("selected");
							$("#mapData option[value="+ n.mapid +"]").attr("selected", "selected");
						});
		
						row.css("cursor", "hand");
						row.attr("ready", "o");
						
						row.hover(function(){//鼠标移上变色
							$(this).css("background-color","#316AC5");
						},function(){
							$(this).css("background-color","");
						});
						
						row.show();
						row.removeAttr("id");
						
						$("#fragment-3 table").append(row);
					});
				}
			});
		}
		
		//班组信息
		function GroupInfo() {
			$.ajax({
				type: "POST",
				url: "workperson.do?method=getGroupData&tempid=" + Math.random() * 10000,
				dataType : "text",
				error : function(){},
				beforeSend : function(){
					
				},
				success: function(msg){
					
				}
			});
		}
	</script>
	</head>

	<body>
		<div style="width:100%;height:30%;overflow:auto; ">
			<table id="alarmInfoTable" cellspacing="0" cellpadding="0"
				style="text-align: center; width: 100%; padding: 2px;">
				<tr style="font-size: 12px; text-align: left;">
					<td colspan="5">
						<input type="checkbox" id="openAlarmSound">
						开启报警声音
					</td>
				</tr>
				<tr>
					<td class="tabletd_top_left" align="center" NOWRAP>
						报警内容
					</td>
					<td class="tabletd_top_left" align="center" NOWRAP>
						员工
					</td>
					<td class="tabletd_top_left" align="center" NOWRAP>
						分站
					</td>
					<td class="tabletd_top_left" align="center" NOWRAP>
						位置
					</td>
					<td class="tabletd_top_right" align="center" NOWRAP>
						状态信息
					</td>
				</tr>
				<tr id="loadingGif">
					<td colspan="5" style="width: 50px; height: 50px;">
						<img src="images/loading.gif">
					</td>
				</tr>
				<tr id="templateTR" style="display: none;">
					<td NOWRAP class="tabletd_left"></td>
					<td NOWRAP class="tabletd_center"></td>
					<td NOWRAP class="tabletd_center"></td>
					<td NOWRAP class="tabletd_center"></td>
					<td NOWRAP class="tabletd_right"></td>
				</tr>
			</table>
		</div>
		<div style="height: 5%;">
			<!--   	地图切换|当前井下人员信息 -->
			<select id="mapData">
				<c:forEach items="${mapList}" var="m" varStatus="index">
					<c:choose>
						<c:when test="${m.mapid=='3'}">
							<option value="${ m.mapid }" selected="selected" l="${ m.maplevel }">
								 ${m.mapname}
							</option>
						</c:when>
						<c:otherwise>
							<option value="${ m.mapid }" l="${ m.maplevel }">
								${m.mapname}
							</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>
		<div id="container-1" style="height: 60%;">
			<!-- 第三个标签显示的是功能，功能标签 -->
			<UL class=tabs-nav>
				<LI class="">
					<A href="#fragment-1" id="fragment-11"> <span id="1">瓦斯信息</span>
					</A>
				</LI>
				<!-- 
					<LI class="">
						<A href="#fragment-2" id="fragment-22"> <span id="2">区域信息</span>
						</A>
					</LI>
				 -->
				<LI class="">
					<A href="#fragment-3" id="fragment-33"> <span id="3">定位跟踪</span>
					</A>
				</LI>
				
			</UL>
			<DIV id=fragment-1>
				<table cellpadding="0" cellspacing="0" border="1"
					style="font-size: 12px; width: 100%; text-align: center;">
					<tr style="background-color: #F2C786;">
						<td>
							基站号
						</td>
						<td>
							上限
						</td>
						<td>
							现值
						</td>
						<td>
							状态
						</td>
					</tr>
					<tr id="templateTR2" style="display: none;">
						<td id="1"></td>
						<td id="2"></td>
						<td id="3"></td>
						<td id="4"></td>
					</tr>
				</table>
			</DIV>
			<DIV id=fragment-2>
				<table cellpadding="0" cellspacing="0" border="1"
					style="font-size: 12px; width: 100%; text-align: center;">
					<tr style="background-color: #F2C786;">
						<td>
							名称
						</td>
						<td>
							基站、定位器
						</td>
						<td>
							区域人数
						</td>
						<td>
							类型
						</td>
					</tr>
					<tr id="templateTR3" style="display: none;">
						<td id="1"></td>
						<td id="2"></td>
						<td id="3"></td>
						<td id="4"></td>
					</tr>
				</table>
			</DIV>
			<DIV id=fragment-3>
				<span>
					<!-- <input type="checkbox" id="isGenZong" title="是否跟踪">
						是否跟踪
					 -->
					<input type="button" value="取消定位跟踪" id="cancle">
				</span>
				
				<table cellpadding="0" cellspacing="0" border="1"
					style="font-size: 12px; width: 100%; text-align: center;">
					<tr style="background-color: #F2C786;">
						<td>
							名称
						</td>
						<td>
							卡号
						</td>
						<td>
							基站
						</td>
						<td>
							定位器
						</td>
						<td>
							状态
						</td>
					</tr>
					<tr id="templateTR1" style="display: none;">
						<td id="1" nowrap="nowrap"></td>
						<td id="2" nowrap="nowrap"></td>
						<td id="3" nowrap="nowrap"></td>
						<td id="4" nowrap="nowrap"></td>
						<td id="5" nowrap="nowrap">
							定位
						</td>
					</tr>
				</table>
			</DIV>
		</div>

		<div id="alarmInfo" style="display: none;">
			<!-- 
				<embed id="wav" hidden="true" autostart="false"
					src='alarmSound/Emergency.wav' type="audio/wav" loop="false"></embed>
			 -->	
		</div>
	</body>
</html>
