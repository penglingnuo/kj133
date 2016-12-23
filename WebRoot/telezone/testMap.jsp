<%@ page language="java" pageEncoding="UTF-8"%>
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
		<title>testMap.jsp</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript" src="js/OpenLayers.js" charset="UTF-8"></script>
		<script type="text/javascript" src="js/openlayers_extend.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>

		<link rel="stylesheet" href="Css/openlayers/theme/default/style.css"
			type="text/css" />
		<link rel="stylesheet" href="Css/openlayers_extend.css"
			type="text/css" />

		<script type="text/javascript">
        var _all = new OpenLayers.Bounds(
           300.0,-44.0,4075.0,2047.0
       	);
         var options_all = {
	            controls: [],
	            maxExtent: _all, //layer能显示出的最大范围
	            maxResolution: 4.5234375, //layer能够显示的最大解析度
	            projection: "EPSG:4326",
	            numZoomLevels: 36 //缩放级别的总数
	        };
        
		$(function(){
			var map = new OpenLayers.Map('map', options_all); 
			
			var url = "http://localhost:80/geoserver/wms";
			
			var tiled = new OpenLayers.Layer.WMS("all", url, {layers: 'all'} );
			
            map.addLayers([tiled]);
            //伸缩控件       
            map.addControl(new OpenLayers.Control.PanZoomBar({
                position: new OpenLayers.Pixel(2, 15)
            }));
            map.addControl(new OpenLayers.Control.Navigation());
            
            //坐标
            map.addControl(new OpenLayers.Control.MousePosition());
            map.addControl(new OpenLayers.Control.LayerSwitcher());
            map.zoomToExtent(_all);
            map.setCenter(new OpenLayers.LonLat(-36.88365,-1.70506),0);
		});
	</script>
	</head>

	<body>
		<div id="map" class="main_map">
		</div>
	</body>
</html>
