<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>EscapeLine.jsp</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript" src="js/OpenLayers.js" charset="UTF-8"></script>
		<script type="text/javascript" src="js/openlayers_extend1.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<link rel="stylesheet" href="Css/openlayers/theme/default/style.css"
			type="text/css" />
		<link rel="stylesheet" href="Css/openlayers_extend.css"
			type="text/css" />
	
		<script type="text/javascript">
			var ip = "<%= request.getServerName() %>:<%= request.getServerPort() %>";
			var markers;
			var map, tiled, editorLayer, mapId, pointJson;
	        
			$(function(){
				mapId = parseInt($("#mapid").val(), 10);
			
				if($("#mapid").val().length == 0){
					mapId = 2;
				}
			
				getMap(""+mapId+"");
	            
	            editorLayer = initEditableLayer(map,"用户编辑图层",'MultiLine');	
	            
	            var arr = new Array();
				var str = $("#point").val().split(",");
				
				var styles = new OpenLayers.StyleMap({
					"default": new OpenLayers.Style(null, {//定义默认状态下的图形的样式
						rules: [
							new OpenLayers.Rule({
								symbolizer: {
									"Line": {
										strokeWidth: 4,//宽度
										strokeOpacity: 0.5,//透明度
										strokeColor: "blue"//颜色
									}
								}
							})
						]
					})
				});
		
				var wfs_lineStaff = new OpenLayers.Layer.Vector("避灾路线", {
			        projection: new OpenLayers.Projection("EPSG:4326"),
			        styleMap: styles
			    });
			    
			    map.addLayer(wfs_lineStaff);
			    
				var points=new Array();
				
				for(var i = 0; i < str.length; i = i + 2) {
					points.push(new OpenLayers.Geometry.Point(str[i], str[i + 1]));
				}
		
				var line_using=new OpenLayers.Geometry.LineString(points);
				wfs_lineStaff.addFeatures([new OpenLayers.Feature.Vector(line_using)]);
			});
		</script>
		<script type="text/javascript">
			var ld = new OpenLayers.Bounds(
	            3.9,3853313.1,3.9,3857538.3
	        );
	        var options_ld = {
	            controls: [],
	            maxExtent: ld, //layer能显示出的最大范围
	            maxResolution: 14.248194258984375, //layer能够显示的最大解析度
	            projection: "EPSG:4326",
	            numZoomLevels: 6 //缩放级别的总数
	        };
	        
			function getMap(mapid) {
				if(map) {
					map.destroy();	
				}
				mapId = mapid;
				
				map = new OpenLayers.Map('map', options_ld); 
				tiled = new OpenLayers.Layer.WMS("qd",
		              "http://"+ip+"/geoserver/wms", {layers: 'qd'} );
				
		       	map.addLayers([tiled]);
		       	
		       	map.zoomToExtent(qd);
		    	map.setCenter(new OpenLayers.LonLat(-36.88365,-1.70506),0);
				
				//伸缩控件       
			    map.addControl(new OpenLayers.Control.PanZoomBar({
			        position: new OpenLayers.Pixel(2, 15)
			    }));
			    map.addControl(new OpenLayers.Control.Navigation());
			    
			    //坐标
			    map.addControl(new OpenLayers.Control.MousePosition());
			    map.addControl(new OpenLayers.Control.LayerSwitcher());
			    
			    editorLayer = initEditableLayer(map,"用户编辑图层",'MultiLine');	
			}
			
			function savedata() {
				var name = window.parent.frames['leftFrame'].getName();
				var info = window.parent.frames['leftFrame'].getInfo();
				var isShow = window.parent.frames['leftFrame'].getIsShow();
				
				if(typeof pointJson == "undefined") {
					alert("请划线");
					return false;
				}
				if(name.length == 0 || info.length == 0) {
					alert("请输入名称和说明");
					return false;
				}
				
				$.ajax({
					type: "POST",
					url: "escapeline.do?method=save&tempId=" + Math.random() * 100000,
					data: "n=" + name + "&i=" + info + "&p=" + pointJson + "&m=" + mapId + "&s=" + isShow, 
					dataType: "text",
					error: function(){
					},
					beforeSend : function(){
					},
					success: function(msg){
						var json = eval('('+ msg +')'); 
						
						if(json.suc == "1") {
							alert("添加成功");
							window.parent.location.reload();
						}else if(json.suc == "-1") {
							alert("名字重复");
						}
					}
				});
			}
		</script>
	</head>

	<body>
		<div id="map" class="main_map">
		</div>
		<div id="geoStr" style="display: none;"></div>
		<input type="hidden" id="point" value='<c:out value="${ list.point }"/>'>
		<input type="hidden" id="mapid" value='<c:out value="${ list.mapid }"/>'>
	</body>
</html>
