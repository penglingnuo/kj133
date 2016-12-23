$(function(){
	var which = 1;
	
	$("#cardreaderOrLocator input").click(function(){
		if($(this).val() == 1) {
			$("#cardreader").attr("disabled", "disabled");
			$("#locator").removeAttr("disabled");
			
			which = 0;
			$("#uploadData #type").val(which);
			
			$(this).attr("checked", "checked");
		}else if($(this).val() == 0){
			$("#cardreader").removeAttr("disabled");
			$("#locator").attr("disabled", "disabled");
			
			which = 1;
			$("#uploadData #type").val(which);
			
			$(this).attr("checked", "checked");
		}
	});
	
	$("#cardreader").mousemove(function(){
		var value = $(this).find("option[selected]").val();
		var name = $(this).find("option[selected]").text();
		
		$("#uploadData #id").val(value);
		$("#uploadData #name").val(name);
	});
	
	$("#locator").change(function(){
		var value = $(this).find("option[selected]").val();
		var name = $(this).find("option[selected]").text();
		
		$("#uploadData #id").val(value);
		$("#uploadData #name").val(name);
	});
	
	
	$("#maplist").change(function(){
		var mapid = $(this).val();
		
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
        
        var click = new OpenLayers.Control.Click();
	    map.addControl(click);
	    click.activate();
	});
	
	
	
});

var markers,marker1;
var map, tiled;
var feature;
var popup;
$(function(){
		map = new OpenLayers.Map('map', options_450); 
		tiled = new OpenLayers.Layer.WMS("450",
              "http://"+ip+"/geoserver/wms", {layers: 'qd'} );

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
    
    var click = new OpenLayers.Control.Click();
    map.addControl(click);
    click.activate();
});

OpenLayers.Control.Click = OpenLayers.Class(OpenLayers.Control, {                
	defaultHandlerOptions: {
	    'single': true,
	    'double': false,
	    'pixelTolerance': 0,
	    'stopSingle': false,
	    'stopDouble': false
	},
	
	initialize: function(options) {
	    this.handlerOptions = OpenLayers.Util.extend(
	        {}, this.defaultHandlerOptions
	    );
	    OpenLayers.Control.prototype.initialize.apply(
	        this, arguments
	    ); 
	    this.handler = new OpenLayers.Handler.Click(
	        this, {
	            'click': this.trigger
	        }, this.handlerOptions
	    );
	}, 
	
	trigger: function(e) {
	    var lonlat = map.getLonLatFromViewPortPx(e.xy);
//	    alert("You clicked near " + lonlat.lon + " webx, " +
//	                              + lonlat.lat + " weby");
	                              
	    $("#uploadData #webx").val(lonlat.lon);
	    $("#uploadData #weby").val(lonlat.lat);
	    /**
	     * 在修改后的位置显示分站
	     */
		feature.moveTo(map.getLayerPxFromViewPortPx(e.xy));
		popup.moveTo(map.getLayerPxFromViewPortPx(e.xy));
		}
});


function check() {
	var type = $("#uploadData #type").val();
	var id = $("#uploadData #id").val();
	var mapid = $("#maplist").val();
	
	if(id == "0" || id.length == 0) {
		alert("这不是一个有效的分站或者定位器，请重新选择");
		return false;
	}
	
	var webx = parseInt($("#uploadData #webx").val(), 10);
	var weby = parseInt($("#uploadData #weby").val(), 10);

	if($("#uploadData #webx").val().length == 0 || $("#uploadData #webx").val().length == 0) {
		alert("这不是一个有效的坐标，请重新选择");
		return false;
	}
	
	$.ajax({
		type: "POST",
		url: "setxy.do?method=savexy&tempId=" + Math.random() * 100000,
		data: "type=" + type + "&id=" + id + "&webx=" + webx + "&weby=" + weby + "&mapid=" + mapid, 
		error: function() {alert("系统忙，请稍后再试!");},
		dataType: "text",
		beforeSend : function(){
		},
		success: function(msg){
			json = eval('('+ msg +')'); 
			
			if(json.data == "1") {
				alert("修改成功");
				//window.location.reload();
			}
		}
	});
}