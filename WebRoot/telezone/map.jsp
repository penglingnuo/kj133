<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
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

		<title>map.jsp</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">

		<script type="text/javascript" src="js/OpenLayers.js" charset="UTF-8"></script>
		<script type="text/javascript" src="js/openlayers_extend.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.timers.js"></script>
		<script type="text/javascript" src="js/mapconfig.js"></script>
		<script type="text/javascript" src="js/map.js" charset="UTF-8"></script>
		<script type="text/javascript" src="js/Polygon.js" charset="UTF-8"></script>

		<link rel="stylesheet" href="Css/openlayers/theme/default/style.css"
			type="text/css" />
		<link rel="stylesheet" href="Css/openlayers_extend.css"
			type="text/css" />

		<style> 
  			.black_overlay{  
  				display: none;  
  				position: absolute;  
  				top: 0%;  
  				left: 0%; 
  				width: 100%;  
  				height: 100%;  
  				background-color: black;  
  				z-index:1001;  
  				-moz-opacity: 0.8;  
  				opacity:.80;  
  				filter: alpha(opacity=80);  
  			}  
  			.white_content {  
  				display: none;  
  				position: absolute;  
  				top: 25%;  
  				left: 20%;  
  				width: 60%;  
  				height: 50%;  
  				padding: 16px;  
  				border: 2px solid orange;  
  				background-color: white;  
  				z-index:1002;  
  				overflow: auto;  
  			}  
  		</style>

		<script type="text/javascript">
			var ip = "<%= request.getServerName() + ":" + request.getServerPort() %>";
			var cardid = "<%=request.getParameter("cardid") == null ? 0
					: request.getParameter("cardid")%>";
			var mapid = "<%=request.getParameter("mapid") == null ? 1
					: request.getParameter("mapid")%>";
			//0、更换地图；1、定位；2、跟踪
			var type = "<%=request.getParameter("type") == null ? 0 : request
					.getParameter("type")%>";
			var stafferid = "<%=request.getParameter("stafferid") == null ? 0
					: request.getParameter("stafferid")%>";
			var x = "<%=request.getParameter("x") == null ? 0
					: request.getParameter("x")%>";
			var y = "<%=request.getParameter("y") == null ? 0
					: request.getParameter("y")%>";
			var islogin = "<%=request.getParameter("islogin") == null ? 0
					: request.getParameter("islogin")%>";
			
			var markers,marker1;
			var map, tiled;
			$(function(){
				map = new OpenLayers.Map('map', options_450); 
				
				var url = "http://"+ip+"/geoserver/wms";
				
				tiled = new OpenLayers.Layer.WMS("矿图", url, {layers: 'qd'} );
				
	            map.addLayers([tiled]);
	            //伸缩控件       
	            map.addControl(new OpenLayers.Control.PanZoomBar({
	                position: new OpenLayers.Pixel(2, 15)
	            }));
	            map.addControl(new OpenLayers.Control.Navigation());
	            
	            //坐标
	            map.addControl(new OpenLayers.Control.MousePosition());
	            map.addControl(new OpenLayers.Control.LayerSwitcher());
	            map.zoomToExtent(_450);
	            map.setCenter(new OpenLayers.LonLat(-36.88365,-1.70506),0);
	            
	            TelezoneMap.getMapInfo();
	            
	          	//定时刷新
	          	$('body').everyTime('120s', 'CurrentDataInMap', function(){
	          		TelezoneMap.getMapInfo();
	          	},0,true);
			});
		</script>
	</head>

	<body style="margin: 2px;">
		<div id="map" class="main_map">
			<div id="showContent" style="position: absolute; left: 10px; bottom: 10px; background-color: #FF8000; width: 150px; height: 20px; font-size: 14px; font-weight: bold; color: white; padding: 5px; z-index: 999999;">
			</div>
		</div>

		<div id="stafferInMineHiddenDiv" style="display: none;">
			<table width="100%" border="1" cellpadding="1" cellspacing="0"
				style="font-size: 12px; text-align: center;">
				<tr>
					<td>
						类型：
					</td>
					<td id="type">
						&nbsp;
					</td>
					<td>
						编号：
					</td>
					<td id="stafferIn">
						&nbsp;
					</td>
					<td>
						名称：
					</td>
					<td id="name">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						基站下人数：
					</td>
					<td id="totalStaffer" colspan="5">
					</td>
				</tr>
				<tr>
					<td colspan="6">
						详细信息
					</td>
				</tr>
				<tr>
					<td>
						姓名
					</td>
					<td>
						天线
					</td>
					<td>
						定位
					</td>
					<td>
						班组
					</td>
					<td colspan="2">
						卡状态
					</td>
				</tr>
				<tr id="templateTR">
					<td id="1"></td>
					<td id="2"></td>
					<td id="3"></td>
					<td id="4"></td>
					<td id="5" colspan="2"></td>
				</tr>
			</table>
		</div>

		<div id="stafferInfo" style="display: none;">
			<table cellpadding="0" cellspacing="0" border="1" style="width: 100%; font-size: 12px;">
		    	<tr>
		    		<td colspan="4" style="padding-left: 3px;">详细信息<br></td>
		    	</tr>
		    	<tr>
		    		<td>姓名：<br></td>
		    		<td id="name"><br></td>
		    		<td>卡号：<br></td>
		    		<td id="cardid"><br></td>
		    	</tr>
		    	<tr>
		    		<td>下井时间<br></td>
		    		<td id="downtime"><br></td>
		    		<td>时长<br></td>
		    		<td id="betweentime"><br></td>
		    	</tr>
		    	<tr>
		    		<td>区域<br></td>
		    		<td id="cardreader"><br></td>
		    		<td>位置<br></td>
		    		<td id="locator"><br></td>
		    	</tr>
		    	<tr>
		    		<td>进入时间<br></td>
		    		<td id="incardreadertime"><br></td>
		    		<td>时长<br></td>
		    		<td id="betweentimein"><br></td>
		    	</tr>
		    	<tr>
		    		<td>其他信息<br></td>
		    		<td id="otherinfo" colspan="3">l<br></td>
		    	</tr>
		    </table>
		</div>
		<div id="fade" class="black_overlay">
	</body>
</html>
