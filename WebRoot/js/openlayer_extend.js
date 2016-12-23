var formats;

/**
*初始化用户编辑图层
*@map			已经初始完毕的地图
*@layName		用户编辑图层的名字
*@geoType		允许画的图形的类型
*
*@return		初始完毕的用户编辑图层
*/
function initEditableLayer(map,layerName,geoType){
	function updateFormats(map){//解析(或重构)图形时候用到的 解析规则
	    var in_options = {
	        'internalProjection': map.baseLayer.projection,
	        'externalProjection': new OpenLayers.Projection('EPSG:4326')
	    };   
	    var out_options = {
	        'internalProjection': map.baseLayer.projection,
	        'externalProjection': new OpenLayers.Projection('EPSG:4326')
	    };
	    formats = {
	      'in': {
	        geojson: new OpenLayers.Format.GeoJSON(in_options)
	      }, 
	      'out': {
	        geojson: new OpenLayers.Format.GeoJSON(out_options)
	      }
	    };
	}
	updateFormats(map);//初始化解析规则
	
	var styles = new OpenLayers.StyleMap({
	    "default": new OpenLayers.Style(null, {//定义默认状态下的图形的样式
	        rules: [
	            new OpenLayers.Rule({
	                symbolizer: {
	                    "Point": {
	                        pointRadius: 6,
	                        graphicName: "star",
	                        fillColor: "green",
	                        fillOpacity: 0.25,
	                        strokeWidth: 1,
	                        strokeOpacity: 1,
	                        strokeColor: "#333333"
	                    },
	                    "Line": {
	                        strokeWidth: 2,
	                        strokeOpacity: 1,
	                        strokeColor: "#666666"
	                    },
	                    "Polygon": {
	                        strokeWidth: 2,
	                        strokeOpacity: 1,
	                        strokeColor: "#666666"
	                    }
	                }
	            })
	        ]
	    }),
	    "select": new OpenLayers.Style({
	        strokeColor: "#00ccff",
	        strokeWidth: 4
	    }),
	    "temporary": new OpenLayers.Style(null, {//定义被选中状态下的图形的样式
	        rules: [
	            new OpenLayers.Rule({
	                symbolizer: {
	                    "Point": {
	                        pointRadius: 6,
	                        graphicName: "star",
	                        fillColor: "white",
	                        fillOpacity: 0.25,
	                        strokeWidth: 1,
	                        strokeOpacity: 1,
	                        strokeColor: "#333333"
	                    },
	                    "Line": {
	                        strokeWidth: 3,
	                        strokeOpacity: 1,
	                        strokeColor: "#00ccff"
	                    },
	                    "Polygon": {
	                        strokeWidth: 3,
	                        strokeOpacity: 1,
	                        strokeColor: "#00ccff"
	                    }
	                }
	            })
	        ]
	    })
	});
	//初始化用户编辑图层
	var editorLayer=new OpenLayers.Layer.Vector(layerName, {
	    // strategies: [new OpenLayers.Strategy.BBOX(), saveStrategy],
	    strategies: [new OpenLayers.Strategy.BBOX()],
	    projection: new OpenLayers.Projection("EPSG:4326"),
	    styleMap: styles
	});
	map.addLayer(editorLayer);
	
	//定义用户选中图形的相关操作
	var options_select = {
        click: true,
        onSelect: serialize
    };
	var select = new OpenLayers.Control.SelectFeature(editorLayer, options_select);	
	map.addControl(select);
    select.activate();    
    
    //接收geoType类型的函数，并初始化draw控件
    if('MultiLineString'==geoType){
    	geoType=OpenLayers.Handler.Path;
    }else if('MultiPoint'==geoType){
    	geoType=OpenLayers.Handler.Point;
    }else if('MultiPolygon'==geoType){
    	geoType=OpenLayers.Handler.Polygon;
    }else{
    	geoType=OpenLayers.Handler.Point;
    }
    var draw = new OpenLayers.Control.DrawFeature(
        editorLayer, geoType,
        {
            title: "折线",
            displayClass: "olControlDrawFeaturePoint",                    
            handlerOptions: {multi: true},
            featureAdded: serialize
        }
    );
    
    var modify = new OpenLayers.Control.ModifyFeature(
        editorLayer, 
        {
	        title:"修改"
	    }
    );
	var DeleteFeature = OpenLayers.Class(OpenLayers.Control, {
       initialize: function(layer, options) {
           OpenLayers.Control.prototype.initialize.apply(this, [options]);
           this.layer = layer;
           this.handler = new OpenLayers.Handler.Feature(
               this, layer, {click: this.clickFeature}
           );
       },
       clickFeature: function(feature) {
           // if feature doesn't have a fid, destroy it
           if(feature.fid == undefined) {
               this.layer.destroyFeatures([feature]);
           } else {
               feature.state = OpenLayers.State.DELETE;
               this.layer.events.triggerEvent("afterfeaturemodified", 
                                              {feature: feature});
               feature.renderIntent = "select";
               this.layer.drawFeature(feature);
           }
       },
       setMap: function(map) {
           this.handler.setMap(map);
           OpenLayers.Control.prototype.setMap.apply(this, arguments);
       },
       CLASS_NAME: "OpenLayers.Control.DeleteFeature"
   	});
	var del = new DeleteFeature(editorLayer, {title: "删除"});
	
	var save = new OpenLayers.Control.Button({
       title: "保存",
       trigger: function() {
       		alert("传递数据到后台");
       },
       displayClass: "olControlSaveFeatures"
   	});
	
	var panel = new OpenLayers.Control.Panel(
        {displayClass: 'customEditingToolbar'}
    );	
    panel.addControls([
        new OpenLayers.Control.Navigation({title:'选择'}),
        save, del, modify, draw
    ]);    
    panel.defaultControl = panel.controls[0];
    map.addControl(panel);    
    
	return editorLayer;
}

//把用户画的图形解析为坐标形式
function serialize(feature){
	//get the feature (in a string) of what you drawn
    var geoStr = formats['out']['geojson'].write(feature, false);
    geoStr = geoStr.replace(/,/g, ', ');
    //save the string to a div called 'geoStr'
    document.getElementById("geoStr").innerHTML=geoStr;
    //solve the string to coordinates we need
    geoStr=eval("(" + geoStr + ")");
    
    //print the result
    if(geoStr.geometry.type=='MultiLineString'){
    	for(var i=0;i<geoStr.geometry.coordinates[0].length;i++){
    		alert("Point12 "+i+": x="+geoStr.geometry.coordinates[0][i][0]+", y="+geoStr.geometry.coordinates[0][i][1]);
    	}
    }else if(geoStr.geometry.type=='MultiPoint'){
    	alert("Point23: x="+geoStr.geometry.coordinates[0][0]+", y="+geoStr.geometry.coordinates[0][1]);
    }else if(geoStr.geometry.type=='MultiPolygon'){
    	for(var i=0;i<geoStr.geometry.coordinates[0][0].length;i++){
    		alert("Point34 "+i+": x="+geoStr.geometry.coordinates[0][0][i][0]+", y="+geoStr.geometry.coordinates[0][0][i][1]);
    	}
    }
}

//在地图上绘制点
function drawPoint(map,point){
	drawGeo(map,point);
}

//在地图上绘制折线

function drawLine(map,line){
	drawGeo(map,line);
}

//在地图上绘制区域
function drawPolygon(map,polygon){
	drawGeo(map,polygon);
}

//在地图上绘制图形
function drawGeo(map,geo){
	//graphics = ["star", "cross", "x", "square", "triangle", "circle", "lightning", "rectangle"];
	var tower;
	var add=false;//是否需要新增名字是 "基站图层"  的图层
   	for(var i=0;i<map.layers.length;i++){   
   		if(map.layers[i].name=='基站图层'){
   			add=false;
   			tower=map.layers[i];
   			break;
   		}else{
   			add=true;
   		}
   	}
   	if(add){
	   	tower = new OpenLayers.Layer.Vector("基站图层", {
	        styleMap: new OpenLayers.StyleMap({'default':{
				strokeColor: "#000000",
	            strokeOpacity: 1,
	            strokeWidth: 1,
	            fillColor: "#000000",
	            fillOpacity: 0.8,
	            graphicName:"star",
	            pointRadius: 12,
				externalGraphic: "images/reader.bmp",
				label : "ID: ${id}",
				fontColor: "#000000",
				fontSize: "10px",
				labelAlign:"lb"
				}})
	        }//自定义的基站样式,可以删除这个样式，
	   	);
	   	map.addLayer(tower);
    }
	 
     var feature = new OpenLayers.Feature.Vector(geo);
     feature.attributes = {
         id: '1'
     };
     var features=[feature];
     tower.addFeatures(features);//这里可增加多个图形
	
}

