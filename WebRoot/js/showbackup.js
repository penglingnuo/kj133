var markers;
var map, tiled;
	        
function getMap(mapid) {
	if(map) {
		map.destroy();	
	}
	
	map = new OpenLayers.Map('map', options_450); 
		tiled = new OpenLayers.Layer.WMS("450",
              "http://"+ip+"/geoserver/wms", {layers: 'qd'} );
	
   	map.addLayers([tiled]);
   	
   	map.zoomToExtent(_450);
	map.setCenter(new OpenLayers.LonLat(-36.88365,-1.70506),0);
	
	//伸缩控件       
    map.addControl(new OpenLayers.Control.PanZoomBar({
        position: new OpenLayers.Pixel(2, 15)
    }));
    map.addControl(new OpenLayers.Control.Navigation());
    
    //坐标
    map.addControl(new OpenLayers.Control.MousePosition());
    map.addControl(new OpenLayers.Control.LayerSwitcher());
}

$(function(){
	$("#searchButton").click(function(){
		$("#downTime option").remove();
		$("#downTime").hide();
		
		$.ajax({
			type: "POST",
			url: "showbackup.do?method=searchHistoryByCardidDate&tempId=" + Math.random() * 1000000,
			data: "c=" + $("#stafferSelect").val() + "&d=" + $("#realtime").val(),
			error: function() {alert("Loading Error");},
			success: function(msg){
				var json = eval('('+ msg +')'); 
				
				if(json.list.length === 0) {
					alert("没有下井记录，请重新选择日期");
				}else {
					$(json.list).each(function(i, n){
						//用于考勤表(workattendanceex)未做分区使用
						$("#downTime").append("<option value='" + n.recordid + "'>" + n.starttime + "</option>");
						
						//用于考勤表(workattendanceex)做分区使用，workattendanceex表中没有recordid字段
						//$("#downTime").append("<option value='" + n.starttime + "'>" + n.starttime + "</option>");
					});
					
					$("#downTime").show();
						
					if(json.list.length === 1){
						getData();
					}else {
						$("#downTime").change(function(){
							getData();
						});
					}
				}
			}
		});
	});
});

function getData() {
		if($("#keyWord").val().length === 0) {
			alert("请输入查询内容");
			return false;
		}
	
		$("#dataTable tr[r='o']").remove();
		
		var tempRow = $("#loadingGif").clone();
		$("#dataTable").append(tempRow);
	
		$.ajax({
			type: "POST",
			url: "showbackup.do?method=search&tempId=" + Math.random() * 1000000,
			data: "c=" + $("#stafferSelect").val()+"&r=" + $("#downTime").val(),
			error: function() {alert("没有这个人的考勤记录");tempRow.remove();},
			success: function(msg){
				var json = eval('('+ msg +')'); 
				
				tempRow.remove();
				
				getMap(json.data[0].mapid);
				
				markers = new OpenLayers.Layer.Markers("分站");
				map.addLayer(markers);
				
				/**
				 * 地图画线样式
				 */
				var styles = new OpenLayers.StyleMap({
					"default": new OpenLayers.Style(null, {//定义默认状态下的图形的样式
						rules: [
							new OpenLayers.Rule({
								symbolizer: {
									"Line": {
										strokeWidth: 3,//宽度
										strokeOpacity: 0.5,//透明度
										strokeColor: "blue"//颜色
									}
								}
							})
						]
					})
				});
				//初始化用户编辑图层
				var editorLayer=new OpenLayers.Layer.Vector('Line', {			
					styleMap: styles
				});
				map.addLayer(editorLayer);
				
				
				var pointArray = new Array();
				
				$(json.data).each(function(i, n){
					var point = new OpenLayers.Geometry.Point(n.webx, n.weby);
					pointArray.push(point);
					
					var row = $("#dataTemplate").clone();
					row.attr("cardreaderid", n.cardreaderid);
					row.find("#0").html(i + 1);
					row.find("#1").html(n.starttime);
					row.find("#2").html(n.cardreaderid);
					row.find("#3").html(n.crname);
					row.find("#4").html(n.betweentime);
					row.find("#5").html(n.endtime);
				
					if(i % 2 === 0) {
						row.css("background-color","#DBE2F5");
					}
					row.css("cursor", "hand");
					row.hover(function(){
						row.css("background-color","#C5D6FC");
					},function(){
						if(i % 2 == 0) {
							row.css("background-color","#DBE2F5");
						}else {
							row.css("background-color","");
						}
					}).click(function(){
						getMap(n.mapid);
						
						isContainFenZhan = 0;
						
						for(var i = map.layers.length - 1; i >= 0; i --) {
					 		if(map.layers[i].name == "分站") {
					 			isContainFenZhan = 1;
					 			break;
					 		}
					 	}
					 	
					 	if(isContainFenZhan == 0) {
					 		markers = new OpenLayers.Layer.Markers("分站");
							map.addLayer(markers);
					 	}
					 	
					 	var size = new OpenLayers.Size(20,20);
						var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
						var jz = new OpenLayers.Icon('Image/temp/jz.bmp',size,offset);
						
						var feature1 = new OpenLayers.Feature(markers,new OpenLayers.LonLat(n.webx, n.weby),{'icon': jz});
						
						marker1 = feature1.createMarker();
						markers.addMarker(marker1);
						var popup=new OpenLayers.Popup("分站名称",
								feature1.lonlat,
				                new OpenLayers.Size(150,30),n.crname,
				                false);
					    popup.setBackgroundColor("transparent");
						map.addPopup(popup);
					});
					
					row.show();
					row.attr("r", "o");
					$("#dataTable").append(row);
					
					//开始
					
					
					
					var size = new OpenLayers.Size(20,20);
					var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
					var jz = new OpenLayers.Icon('Image/temp/jz.bmp',size,offset);
					
					var feature1 = new OpenLayers.Feature(markers,new OpenLayers.LonLat(n.webx, n.weby),{'icon': jz});
					
					marker1 = feature1.createMarker();
					markers.addMarker(marker1);
					var popup=new OpenLayers.Popup("分站名称",
							feature1.lonlat,
			                new OpenLayers.Size(150,30),n.crname,
			                false);
				    popup.setBackgroundColor("transparent");
					map.addPopup(popup);
					
					//结束
					
				});
				
				var line=new OpenLayers.Geometry.LineString(pointArray);
				var feature = new OpenLayers.Feature.Vector(line);
				
				editorLayer.addFeatures(feature);
				
				$("#stafferInfo").find("#1").html(json.sinfo.cardid);
				$("#stafferInfo").find("#2").html(json.sinfo.stafferid);
				$("#stafferInfo").find("#3").html(json.sinfo.staffername);
				$("#stafferInfo").find("#4").html(json.sinfo.group);
				
				if(json.sinfo.downtime.length == 0) {
					$("#stafferInfo").find("#5").html(" ");
				}else {
					$("#stafferInfo").find("#5").html(json.sinfo.downtime);
				}
				if(json.sinfo.uptime.length == 0) {
					$("#stafferInfo").find("#6").html(" ");
				}else {
					$("#stafferInfo").find("#6").html(json.sinfo.uptime);
				}
				if(json.sinfo.betweentime.length == 0) {
					$("#stafferInfo").find("#7").html(" ");
				}else {
					$("#stafferInfo").find("#7").html(json.sinfo.betweentime);
				}
			}
		}); 
}

$(function(){
	$("#keyWord").bind("propertychange", function(){
		$.ajax({
			type: "POST",
			url: "common.do?method=staffer&tempId=" + Math.random() * 1000000,
			data: "c=" + $("#keyWord").val(),
			success: function(msg){
				var json = eval('('+ msg +')'); 
				
				$("#stafferSelect option").remove();
				
				$(json.staffer).each(function(i, n){
					$("#stafferSelect").append("<option value='"+ n.cardId +"'>" + n.name + " - "+ n.department + "</option>"); 
				});
			}
		});
	});
});