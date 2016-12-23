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
    
    <title>编辑分站信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" href="Css/openlayers/theme/default/style.css"
			type="text/css" />
		<link rel="stylesheet" href="Css/openlayers_extend.css"
			type="text/css" />
	
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/OpenLayers.js"></script>
	<script type="text/javascript" src="js/mapconfig.js"></script>
	<script type="text/javascript" src="js/setCoordinate.js"></script>
  	<script type="text/javascript">
  		var ip = "<%= request.getServerName() +":"+request.getServerPort() %>";
  	</script>
  	<script type="text/javascript">
  	 $(function(){
  	 //关闭当前窗口
  	 $("#closeButton").click(function(){
  	 	if(confirm("您确定要关闭此窗口吗？")){
  	 		window.top.opener = null; 
  			window.close();
  	 	}else{
  	 		 return false;
  	 	}
  	});
  	 //计时器：显示分站名称
  	 setTimeout("$(\"#cardreader option[value='${cardid }']\").attr(\"selected\",\"selected\");", 500);
  	 
  	 	$("#cardreader").change(function(){
	  	    $("#uploadData #webx").val("");
			$("#uploadData #weby").val("");
  	 	});
  	 	
  	 /**
  	 在地图上显示分站图标和分站名称
  	 */
  	 	var markers = new OpenLayers.Layer.Markers( "分站" ); 
		map.addLayer(markers); 
		var size = new OpenLayers.Size(21,24); 
		var offset = new OpenLayers.Pixel(-(size.w/2), -(size.h)); 
		var icon = new OpenLayers.Icon('Image/temp/jz.bmp',size,offset);
		feature=new OpenLayers.Feature(markers,new OpenLayers.LonLat($("#webx").val(), $("#weby").val()),{'icon': icon}).createMarker();
		markers.addMarker(feature);  
		popup=new OpenLayers.Popup("分站名称",
	                feature.lonlat,
	                new OpenLayers.Size(150,30),"${cardReader.crname}",
	                false);
	    popup.setBackgroundColor("transparent");
		map.addPopup(popup);
  	 });
  	</script>
  </head>
  
  <body bgColor="white" background="Image/right.gif" style="width: 100%;">
    <div style="font-weight: bold; font-size: 13px; background-repeat: repeat; background-image: url(Image/title.bmp);">
  		&gt;&gt;当前位置：设置WEB基站、定位器坐标
  	</div>
  	<div>
  		<span id="cardreaderOrLocator" style="font-size: 12px;">
  			<input type="radio" name="check" value="0" checked="checked">分站
  			<input type="radio" name="check" value="1">定位器
  		</span>
  		<span>
  			<select id="cardreader" class="selector" style="">
  				<c:forEach items="${ cr }" var="cr">
  					<option value="${ cr.cardreaderid }">${ cr.crname }</option>
  				</c:forEach>
  			</select>
  		</span>
  		<span>
  			<select id="locator" disabled="disabled">
  				<c:forEach items="${ l }" var="l">
	  				<option value="${ l.locatorId }">${ l.lname }</option>
  				</c:forEach>
  			</select>
  		</span>
  		<span>
  			<select id="maplist">
  				<c:forEach items="${ ml }" var="ml">
	  				<option value="${ ml.mapid }">${ ml.mapname }</option>
  				</c:forEach>
  			</select>
  		</span>
  	</div>
  	<div id="uploadData">
  		<form action="" onsubmit="javascript:check();return false;" style="font-size: 12px;">
  			<input id="type" name="type" type="hidden" value="1">
  			ID：<input id="id" name="id" readonly="readonly" size="5" type="text" value="${cardReader.cardreaderid }"/>
  			名称：<input id="name" name="name" readonly="readonly" size="10" type="text" value="${cardReader.crname }"/>
  			X坐标：<input id="webx" name="webx" readonly="readonly" size="10" type="text" value="${cardReader.webx }"/>
  			Y坐标：<input id="weby" name="weby" readonly="readonly" size="10" type="text" value="${cardReader.weby }"/>
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  			<input type="submit" id="submitButton" value="保存">
  			<input type="button" id="closeButton" value="关闭">
  		</form>
  	</div>
  	<div>
  		<hr style="width: 100%;">
  	</div>
  	<div id="map"></div>
  </body>
</html>
