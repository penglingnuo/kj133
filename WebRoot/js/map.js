/**
* LYee 地图的重构Javascript类库
* 
* Date 2010 - 06 - 30
* 
* 地图中的方法的封装
* 
* */
var json;
var wfs_lineStaff = null;//画线对象，用于清除画线对象使用
//区域的坐标信息
var AreaPointArray = new Array();
var isShowCardreader = 1;
var isShowLocator = 1;

var TelezoneMap = {
	/**
	 * 切换地图函数
	 * 
	 * 参数： 地图ID, 是否是切换地图
	 * 
	 * 如果是基本的切换则不加载具体的人员信息(true)，如果点击人员切换则切换地图以后加载人员信息(false)
	 * 
	 */
	changeMap : function(_mapid){
		if(map) {
			map.destroy();	
		}
		mapid = _mapid;
		
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
	            
		TelezoneMap.getMapInfo();
		
		//定时刷新
      	$('body').everyTime('120s', 'CurrentDataInMap', function(){
      		TelezoneMap.getMapInfo();
      	},0,true);
	},
	/**
	 * 加载地图基本信息
	 * 
	 * 参数：地图ID
	 * 
	 * 加载地图的基本信息，即：基站、分站信息和对应下的人员信息
	 */
	getMapInfo : function() {
		$.ajax({
			type: "POST",
			url: "maplistC.do?method=getcald&tempId=" + Math.random() * 100000,
			data: "id=" + mapid , 
			dataType: "text",
			error: function(){
			},
			beforeSend : function(){
			},
			success: function(msg){
				for(var i = map.layers.length - 1; i >= 0; i --) {
			 		if(map.layers[i].name == "分站" || map.layers[i].name == "分站标签" || map.layers[i].name == "定位器" || map.layers[i].name == "定位器标签") {
			 			map.removeLayer(map.layers[i]);
			 		}
			 	}
			 	
				if(map.popups.length > 0) {
					while(map.popups.length > 0) {
						map.removePopup(map.popups[map.popups.length - 1]);
					}					
				}
				
				json = eval('('+ msg +')'); 
				
//				var mapLevel = json.ml.maplevel;
				
				if(isShowCardreader == 1) {
					var tower = new OpenLayers.Layer.Vector("分站标签", {
						styleMap: new OpenLayers.StyleMap({'default':{
							strokeColor: "#000000",
							strokeOpacity: 1,
							strokeWidth: 1,
							fillColor: "#000000",
							fillOpacity: 0,
							pointRadius: 0,
							label : "  ${name}",//下面的三个关于字体样式的设置要放在stylemap的default中设置
							fontColor: "#00008B",
							fontSize: "16px",
							labelAlign:"lm",
							fontWeight:"bold"
							}})
						}
					);
					map.addLayer(tower);
					
					markers = new OpenLayers.Layer.Markers("分站");
					map.addLayer(markers);
					
					$(json.crl).each(function(i,n){
						var size = new OpenLayers.Size(20,20);
						var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
						var zd = new OpenLayers.Icon('Image/temp/zd.bmp',size,offset);
						var jz = new OpenLayers.Icon('Image/temp/jz.bmp',size,offset);
						
						if(n.cardreaderstate == "0"){
//							if(mapLevel == "0") {
//								feature1 = new OpenLayers.Feature(markers,new OpenLayers.LonLat(n.webxall, n.webyall),{'icon': jz});
//							}else {
								feature1 = new OpenLayers.Feature(markers,new OpenLayers.LonLat(n.webx, n.weby),{'icon': jz});
//							}
						}else if(n.cardreaderstate == "1" || n.cardreaderstate == "2"){
//							if(mapLevel == "0") {
//								feature1 = new OpenLayers.Feature(markers,new OpenLayers.LonLat(n.webxall, n.webyall),{'icon': zd});
//							}else {
								feature1 = new OpenLayers.Feature(markers,new OpenLayers.LonLat(n.webx, n.weby),{'icon': zd});
//							}
						}
						
						marker1 = feature1.createMarker();
						markers.addMarker(marker1);
						
						//以下是添加鼠标点击图片的事件
						var popup1;
						marker1.events.register("mousedown", marker1, function(evt){
							var globalX;
							var globalY;
							
//							if(mapLevel == "0") {
//								globalX = n.webxall;
//								globalY = n.webyall;
//							}else {
								globalX = n.webx;
								globalY = n.weby;
//							}
							
							if (popup1 == null){
								$.ajax({
									type: "POST",
									url: "workperson.do?method=getStafferInCardReader&tempId=" + Math.random() * 100000,
									data: "id=" + n.cardreaderid +"&ground=" + n.ground + "&type=1", 
									error: function() {alert("系统忙，请稍后再试!");},
									dataType: "text",
									beforeSend : function(){
									},
									success: function(msg){
										var json = eval('('+ msg +')');
										
										var obj = $("#stafferInMineHiddenDiv").clone();
										
										$(json.cr).each(function(i, n){
											$(obj).find("#type").html("分站");
											$(obj).find("#stafferIn").html(n.cardreaderid);
											$(obj).find("#name").html(n.crname);
										});
										
										$(obj).find("#totalStaffer").html(json.list.length);
										
										$(json.list).each(function(i, n){
											var row = $(obj).find("#templateTR").clone();
											row.find("#1").html(n.staffername);
											var antenna = "";
											
											if(n.antenna == '1'){
												antenna = "A";
											}else if(n.antenna == '2'){
												antenna = "B";
											}else if(n.antenna == '3'){
												antenna = "C";
											}
											row.find("#2").html(antenna);
											
											if(n.locatorname == "无") {
												row.find("#3").html(n.locatorname);
											}else {
												row.find("#3").html(n.locatorname+"附近");
											}
											
											row.find("#4").html(n.staffergroup);
											row.find("#5").html(n.state);
	
											row.removeAttr("id");										
											row.show();
											
											if(i % 2 == 0) {
												row.css("background-color","#DBE2F5");
											}else {
												row.css("background-color","white");
											}
											
											row.css("cursor", "hand");
											
											row.attr("r", "o");
											$(obj).find("table").append(row);
										});
										
										var html = $(obj).html();
								
						                popup1=new OpenLayers.Popup("popup1",
								                   new OpenLayers.LonLat(globalX, globalY),
								                   new OpenLayers.Size(500,300),
								                   html,
								                   true);
										
										popup1.setBackgroundColor("#D4D0C8");
						                map.addPopup(popup1);
									}
								});
				            }else{
				                popup1.toggle();
				                popup1 = null;
				            }
				            OpenLayers.Event.stop(evt);
						});
						
						var point11;
						
//						if(mapLevel == "0") {
//							point11 = new OpenLayers.Geometry.Point(n.webxall, n.webyall);
//						}else {
							point11 = new OpenLayers.Geometry.Point(n.webx, n.weby);
//						}
						
						var feature11 = new OpenLayers.Feature.Vector(point11);
					    feature11.attributes = {
							 name:n.readerCode+"人"
						};
						tower.addFeatures(feature11);
					});
				}
							
				if(isShowLocator == 1) {
					var tower = new OpenLayers.Layer.Vector("定位器标签", {
						styleMap: new OpenLayers.StyleMap({'default':{
							strokeColor: "#000000",
							strokeOpacity: 1,
							strokeWidth: 1,
							fillColor: "#000000",
							fillOpacity: 0,
							pointRadius: 0,
							label : "${name}",//下面的三个关于字体样式的设置要放在stylemap的default中设置
							fontColor: "#00008B",
							fontSize: "8px",
							labelAlign:"lm",
							fontWeight:"bold"
							}})
						}
					);
					map.addLayer(tower);
					
					markers = new OpenLayers.Layer.Markers("定位器");
					map.addLayer(markers);
					
					$(json.ld).each(function(i,n){
						var size = new OpenLayers.Size(10, 8);
						var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
						var dwq = new OpenLayers.Icon('Image/temp/dwq.bmp',size,offset);
						
						var feature1;
//						if(mapLevel == "0") {
//							feature1 = new OpenLayers.Feature(markers,new OpenLayers.LonLat(n.webxall, n.webyall),{'icon': dwq});
//						}else {
							feature1 = new OpenLayers.Feature(markers,new OpenLayers.LonLat(n.webx, n.weby),{'icon': dwq});
//						}
						
						marker1 = feature1.createMarker();
						markers.addMarker(marker1);
						
						var point11
//						if(mapLevel == "0") {
//							point11 = new OpenLayers.Geometry.Point(n.webxall, n.webyall);
//						}else {
							point11 = new OpenLayers.Geometry.Point(n.webx, n.weby);
//						}
						
						var feature11 = new OpenLayers.Feature.Vector(point11);
					    feature11.attributes = {
							 name:n.personinminenumber + "人"
						};
						
						tower.addFeatures(feature11);
					});
				}
							
				$("#showContent").html("总人数：" + json.as + "人");
				
				if(type == 1 || type == 2) {
					TelezoneMap.setParamenter(cardid, mapid, type, stafferid);
					TelezoneMap.locationStaffer(x, y);
				}
			}
		}); 
	},
	/**
	 * Function: addMarker
	 * Add a new marker to the markers layer given the following lonlat, 
	 *     popupClass, and popup contents HTML. Also allow specifying 
	 *     whether or not to give the popup a close box.
	 * 
	 * Parameters:
	 * ll - {<OpenLayers.LonLat>} Where to place the marker
	 * popupClass - {<OpenLayers.Class>} Which class of popup to bring up 
	 *     when the marker is clicked.
	 * popupContentHTML - {String} What to put in the popup
	 * closeBox - {Boolean} Should popup have a close box?
	 * overflow - {Boolean} Let the popup overflow scrollbars?
	 */
	addMarker : function(ll, popupClass, closeBox, overflow, imageURL) {
		var feature = new OpenLayers.Feature(markers, ll); 
		feature.closeBox = closeBox;
		feature.popupClass = popupClass;
		feature.data.overflow = (overflow) ? "auto" : "hidden";
		        
		var marker = feature.createMarker();
		marker.setUrl(imageURL);
		marker.display(true); 
		
		markers.addMarker(marker);
	},
	/**
	 * Function: addMarker
	 * Add a new marker to the markers layer given the following lonlat, 
	 *     popupClass, and popup contents HTML. Also allow specifying 
	 *     whether or not to give the popup a close box.
	 * 
	 * Parameters:
	 * ll - {<OpenLayers.LonLat>} Where to place the marker
	 * popupClass - {<OpenLayers.Class>} Which class of popup to bring up 
	 *     when the marker is clicked.
	 * popupContentHTML - {String} What to put in the popup
	 * closeBox - {Boolean} Should popup have a close box?
	 * overflow - {Boolean} Let the popup overflow scrollbars?
	 */
	addMarkerOfStaffer : function(ll, popupClass, popupContentHTML, closeBox, overflow, imageURL, x, y) {
		var feature = new OpenLayers.Feature(markers, ll); 
		feature.closeBox = closeBox;
		feature.popupClass = popupClass;
		feature.data.popupContentHTML = popupContentHTML;
		feature.data.overflow = (overflow) ? "auto" : "hidden";
		        
		var marker = feature.createMarker();
		marker.setUrl(imageURL);
		marker.display(true); 
		
		var popup2;
		var markerClick = function (evt) {
		    if (popup2 == null) {
				popup2=new OpenLayers.Popup("popup2",
	                new OpenLayers.LonLat(x, y),
	                new OpenLayers.Size(400,120),
	                popupContentHTML,
	                true);
		        map.addPopup(popup2);
		    } else {
		        popup2.toggle();
		    }
		    currentPopup = popup2;
		    OpenLayers.Event.stop(evt);
		};
		marker.events.register("mousedown", feature, markerClick);
		
		markers.addMarker(marker);
	},
	
	/**
	 * 点击人员信息在地图上定位
	 * 人员定位
	 * 
	 * 参数: X：x坐标；Y：y坐标；mapid：地图的ID；stafferid：人员ID；isGenZong：判断跟踪还是定位
	 */
	locationStaffer : function(x, y) {
	 	if(type == 1) {//地图人员定位，本图和其他地图
	 		var center = new OpenLayers.LonLat(x, y);
			map.panTo(center);
			
	 		TelezoneMap.locate(x, y);
	 	}else if(type == 2){//地图人员跟踪
	 		TelezoneMap.showWayOfStaffer(x, y);
	 	}
	 },
	 
	/**
	 * 加载人员定位信息(同一地图和不同地图的加载方法)，定位方法，跟踪方法另外写
	 * 
	 * 参数: X：x坐标；Y：y坐标；stafferID：人员ID
	 */
	locate : function(x, y) {
		for(var i = 0; i < map.layers.length; i ++) {
			var layersName = map.layers[i].name;
			
			if(layersName == parent.rightFrame.getCardID()+"卡") {
				map.removeLayer(map.layers[i]);
			}
		}
		
		markers = new OpenLayers.Layer.Markers(parent.rightFrame.getCardID()+"卡");
		map.addLayer(markers);
		
		var ll = new OpenLayers.LonLat(x, y);
		var popupClass = OpenLayers.Popup.Anchored;
		//ajax获得这个人的信息
		
		if(cardid == 0) {
			cardid = parent.rightFrame.getCardID()
		}
		
		$.ajax({
			type: "POST",
			url: "workperson.do?method=getStafferInfo&tempId=" + Math.random() * 100000,
			data: "id=" + cardid, 
			error: function() {alert("系统忙，请稍后再试!");},
			dataType: "text",
			beforeSend : function(){
			},
			success: function(msg){
				var json = eval('('+ msg +')');
				popupContentHTML = "";
				
				var row = $("#stafferInfo").clone();
				row.find("#name").html(json.info.staffername);
				row.find("#cardid").html(json.info.cardid);
				row.find("#downtime").html(json.info.downtime);
				row.find("#betweentime").html(json.info.betweentime);
				row.find("#cardreader").html(json.info.crname);
				row.find("#locator").html(json.info.lname);
				row.find("#incardreadertime").html(json.info.intime);
				row.find("#betweentimein").html(json.info.betweentimein);
				row.find("#otherinfo").html(json.info.state);

				popupContentHTML = $(row).html();
				
				TelezoneMap.addMarkerOfStaffer(ll, popupClass, popupContentHTML, true, true, "js/img/people.gif", x, y);
			}
		});
	},
	  
	/**
	 * 跟踪某个卡的路径
	 * 
	 * 参数：stafferID：人员ID；mapID：地图ID
	 */
	showWayOfStaffer : function(x, y) {
		var layersLength = map.layers.length;
		
		$.ajax({
			type: "POST",
			url: "workperson.do?method=getWayOfStaffera&tempId=" + Math.random() * 100000,
			data: "id=" + mapid +"&cid="+ parent.rightFrame.getCardID(), 
			error: function() {alert("系统忙，请稍后再试!");},
			dataType: "text",
			beforeSend : function(){
			},
			success: function(msg){
				var json = eval('('+ msg +')');
				
				var WayOfStafferPointDataArray = new Array();
				
				$(json.data).each(function(i, n){
					var _tArray = new Array();
					
					_tArray.push(n.crx);
					_tArray.push(n.cry);
					
					WayOfStafferPointDataArray.push(_tArray);
				});
				
				var _tArray = new Array();
					
				_tArray.push(x);
				_tArray.push(y);
				
				WayOfStafferPointDataArray.push(_tArray);
				
				TelezoneMap.drawLineOfStaffer(WayOfStafferPointDataArray, x, y);
			}
		});
	},
	
	/**
	 * 人员跟踪的画线方法
	 */
	drawLineOfStaffer : function(array, x, y) {
		TelezoneMap.cleanLineOfStaffer();
		
		wfs_lineStaff = new OpenLayers.Layer.Vector("人员跟踪", {
	        projection: new OpenLayers.Projection("EPSG:4326")
	    });
	    
	    map.addLayer(wfs_lineStaff);
	    
		var points=new Array();
		
		for(var i = 0; i < array.length; i ++) {
			points.push(new OpenLayers.Geometry.Point(array[i][0], array[i][1]));
		}

		var line_using=new OpenLayers.Geometry.LineString(points);
		wfs_lineStaff.addFeatures([new OpenLayers.Feature.Vector(line_using)]);
		
		markers = new OpenLayers.Layer.Markers(parent.rightFrame.getCardID()+"卡跟踪");
		map.addLayer(markers);
		
		var ll = new OpenLayers.LonLat(x, y);
		var popupClass = OpenLayers.Popup.Anchored;
		
		TelezoneMap.addMarker(ll, popupClass, true, true, "Image/temp/gz.jpg");
    },
	
	/**
	 * 取消人员跟踪的画线方法
	 */
	cleanLineOfStaffer : function() {
		if(wfs_lineStaff != null) {
			map.removeLayer(wfs_lineStaff);
			wfs_lineStaff = null;			
		}
		
	 	for(var i = map.layers.length - 1; i >= 0; i --) {
	 		if(map.layers[i].name.indexOf("卡跟踪") > 0) {
	 			map.removeLayer(map.layers[i]);
	 		}
	 	}
	},
	
	/**
	 * 取消定位或跟踪
	 */	 
	 cancle : function() {
	 	//传输前清空地图上的人员信息
	 	var layersLength = map.layers.length;
	 	
	 	for(var i = layersLength - 1; i >= 0; i --) {
	 		if(map.layers[i].name.indexOf("卡") > 0 && map.layers[i].name.indexOf("验卡") < 0) {
	 			map.removeLayer(map.layers[i]);
	 		}
	 	}
		
		TelezoneMap.cleanLineOfStaffer();
		
		type = 0;
	 },
	 /**
	  * 设置cardid  mapid  type  stafferid
	  */
	 setParamenter : function(cardId, mapId, Type, stafferId) { 
	 	if(cardId.length > 0) {
	 		cardid = cardId;
	 	}
	 	if(mapId.length > 0) {
	 		mapid = mapId;
	 	}
	 	if(Type.length > 0) {
	 		type = Type;
	 	}
	 	if(stafferId.length > 0) {
	 		stafferid = stafferId;
	 	}
	 }
};

//扩展数组，清除指定位置的数据
Array.prototype.remove = function(dx) {
    if(isNaN(dx)||dx>this.length) {
    	return false;
    }
    for(var i=0,n=0;i<this.length;i++) {
		if(this[i]!=this[dx]) {
			this[n++]=this[i];
		}
	}
	this.length-=1
}

//清空数组
Array.prototype.clean = function() {
	this.length = 0;
	return this;	
}
