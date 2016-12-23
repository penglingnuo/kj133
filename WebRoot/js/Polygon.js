/**
*初始化用户编辑图层
*@map			已经初始完毕的地图
*@layName		用户编辑图层的名字
*@geoType		允许画的图形的类型
*
*@return		初始完毕的用户编辑图层
*/
function initEditableLayer(map,layerName){
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
	
	//初始化用户编辑图层
	var editorLayer=new OpenLayers.Layer.Vector(layerName, {
	    strategies: [new OpenLayers.Strategy.BBOX()],
	    projection: new OpenLayers.Projection("EPSG:4326")
	});
	
	map.addLayer(editorLayer);
	
	//editorLayer.setVisibility(true);

   
    var draw = new OpenLayers.Control.DrawFeature(
        editorLayer, OpenLayers.Handler.Polygon,
        {
            title: "折线",
            displayClass: "olControlDrawFeaturePoint",                    
            handlerOptions: {multi: true},
            featureAdded: serialize1
        }
    );
   
	var panel = new OpenLayers.Control.Panel(
        {id:"controlPanel",displayClass: 'customEditingToolbar'}
    );
    panel.addControls([
        new OpenLayers.Control.Navigation({title:'选择'}),
		draw
    ]);    
    panel.defaultControl = panel.controls[0];
    map.addControl(panel);    
    
    document.getElementById("controlPanel").lastChild.onclick=function(){
		for(var i=0;i<map.layers.length;i++){
			if(map.layers[i].name=='TelezoneMap'){
				map.layers[i].setVisibility(!(map.layers[i].getVisibility()));
			}	
		}
	};
    document.getElementById("controlPanel").firstChild.onclick=function(){
		for(var i=0;i<map.layers.length;i++){
			if(map.layers[i].name=='TelezoneMap'){
				map.layers[i].setVisibility(!(map.layers[i].getVisibility()));
			}	
		}
	};
    
	return editorLayer;
}

var _tempFeature = null;
//把用户画的图形解析为坐标形式
function serialize1(feature){
    var geoStr = formats['out']['geojson'].write(feature, false);
    geoStr = geoStr.replace(/,/g, ', ');
	geoStr=eval("(" + geoStr + ")");
    
    //print the result
    if(geoStr.geometry.type=='MultiLineString'){
    	for(var i=0;i<geoStr.geometry.coordinates[0].length;i++){
    		alert("Point "+i+": x="+geoStr.geometry.coordinates[0][i][0]+", y="+geoStr.geometry.coordinates[0][i][1]);
    	}
    }else if(geoStr.geometry.type=='MultiPoint'){
    	alert("Point: x="+geoStr.geometry.coordinates[0][0]+", y="+geoStr.geometry.coordinates[0][1]);
    }else if(geoStr.geometry.type=='MultiPolygon'){
    	AreaPointArray.clean();
    	
    	for(var i=0;i<geoStr.geometry.coordinates[0][0].length;i++){
    		//alert("Point "+i+": x="+geoStr.geometry.coordinates[0][0][i][0]+", y="+geoStr.geometry.coordinates[0][0][i][1]);
    		var _t = new Array();
    		_t.push(geoStr.geometry.coordinates[0][0][i][0]);
    		_t.push(geoStr.geometry.coordinates[0][0][i][1]);
    		
    		AreaPointArray.push(_t);
    	}
    	
    	_tempFeature = feature;
    	
    	if(window.confirm("确定此区域吗?")) {
    		document.getElementById('areaDiv').style.display='block';
    		document.getElementById('fade').style.display='block';
    		
    		$("#CareReaderTd").html("");
    		$("#LocatorTd").html("");
    		$("#areaDiv input[type='text']").val("");
    	}else {
    		for(var i = 0; i < map.layers.length; i ++) {
    			var layersName = map.layers[i].name;
    			
    			if(layersName == "TelezoneMap") {
    				map.layers[i].destroyFeatures(feature);
    			}
    		}
    	}
    }
}

var areaType = 1;
/*
 * 保存区域信息
 */
$(function(){
	//保存
	$("#areaSaveButton").bind("click", function(){
		var areaName = encodeURIComponent($("#areaName").val());
		var areaMaxPerson = $("#areaMaxPerson").val();
		var areaOverTime = $("#areaOverTime").val();
		var areaCaiJuePerson = $("#areaCaiJuePerson").val();
		
		if(areaName.length == 0) {
			alert("请输入区域的名称");
			return false;	
		}
		
		if(areaMaxPerson.length > 0) {
			if(!checknumber(areaMaxPerson)) {
				alert("请在'定员数'输入框内输入数字");
				return false;	
			}
		}else {
			alert("请在'定员数'输入框内输入内容");
			return false;	
		}
		
		if(areaOverTime.length > 0) {
			if(!checknumber(areaOverTime)) {
				alert("请在'超时分钟'输入框内输入数字");
				return false;
			}
		}else {
			alert("请在'超时分钟'输入框内输入内容");
			return false;	
		}
		
		if(areaCaiJuePerson.length > 0) {
			if(!checknumber(areaCaiJuePerson)) {
				alert("请在'采掘人员数'输入框内输入数字");
				return false;	
			}
		}else {
			alert("请在'采掘人员数'输入框内输入内容");
			return false;	
		}
		
		var areaBorder = new Array();
		for(var i=0;i<AreaPointArray.length;i++){
			var point=new OpenLayers.Geometry.Point(AreaPointArray[i][0],AreaPointArray[i][1]);
			areaBorder.push(point);
		}
		
		var lString=new OpenLayers.Geometry.LinearRing(areaBorder);
		var polygon=new OpenLayers.Geometry.Polygon(lString);
		
		if($("#CareReaderTd").html().length == 0) {
			$(json.crl).each(function(i, n){
				var point=new OpenLayers.Geometry.Point(n.x,n.y);
				if(polygon.intersects(point)){
					if($("#CareReaderTd").html().length == 0) {
						$("#CareReaderTd").append(n.cardreaderid);	
					}else{
						$("#CareReaderTd").append(","+n.cardreaderid);
					}
				}
			});
		}
		
		if($("#LocatorTd").html().length == 0) {
			$(json.ld).each(function(i,n){
				var point=new OpenLayers.Geometry.Point(n.x,n.y);
				if(polygon.intersects(point)){
					if($("#LocatorTd").html().length == 0) {
						$("#LocatorTd").append(n.locatorId);
					}else{
						$("#LocatorTd").append(","+n.locatorId);
					}
				}
			});
		}
		
		$.ajax({
			type: "POST",
			url: "area.do?method=addArea",
			data: "areaName=" + areaName +"&areaType="+ areaType +"&areaOverTime="+ areaOverTime +"&CareReaderTd="+ $("#CareReaderTd").html() +"&LocatorTd="+ $("#LocatorTd").html()+"&areaMaxPerson="+ areaMaxPerson +"&areaCaiJuePerson="+ areaCaiJuePerson, 
			error: function() {alert("Loading Error!");},
			dataType: "text",
			beforeSend : function(){
				$("#areaSaveButton").attr("disabled","true");
			},
			success: function(msg){
				var json = eval('('+ msg +')'); 
				
				if(json.flag.length > 0) {
					alert(json.flag);
					$("#areaSaveButton").removeAttr("disabled");
				}else {
					if(json.data == 1) {
						alert("保存成功");
						$("#areaSaveButton").removeAttr("disabled");
						
						document.getElementById('areaDiv').style.display='none';
						document.getElementById('fade').style.display='none';
						
						$("#CareReaderTd").html("");
			    		$("#LocatorTd").html("");
			    		$("#areaDiv input[type='text']").val("");
					}
				}
			}
		});
	});
	//取消
	$("#areaCancle").bind("click", function(){
		if(_tempFeature != null) {
			for(var i = 0; i < map.layers.length; i ++) {
    			var layersName = map.layers[i].name;
    			
    			if(layersName == "TelezoneMap") {
    				map.layers[i].destroyFeatures(_tempFeature);
    			}
    		}
		}
		
		_tempFeature = null;
		
		document.getElementById('areaDiv').style.display='none';
		document.getElementById('fade').style.display='none';
		
		$("#areaSaveButton").removeAttr("disabled");
	});
});

function selectChange(obj) {
	areaType = obj.value;
}

function checknumber(String) {
	var Letters = "1234567890";
	var c, flag = true;
	
	for( var i = 0; i < String.length; i ++ ) {
		c = String.charAt( i );
		if (Letters.indexOf( c ) == -1) {
			flag = false;
			
			break;
		}
	}
	
	return flag;
} 