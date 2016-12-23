<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>search.jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
  	<link rel="stylesheet" href="js/jqueryui/cupertino/jquery-ui.css"
	type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery-ui-min.js"></script>
  	<script type="text/javascript" src="js/search.js"></script>
  	<script type="text/javascript" src="js/showbackupaner.js"></script>
  	<script type="text/javascript">
  		$(function(){
  			//用户名模糊查询	    
   	 	$("#stafferSelect").autocomplete({
    	source: function(request, response) {
                $.ajax({
                    url: "common.do?method=staffer",
                    dataType: "json",
                    data: {
                    	c:$("#stafferSelect").val()
                    },
                    success: function(data) {
                       var names=[];
                       $(data.staffer).each(function(i, n){
                       names.push(this.cardId+"--"+this.name);
					   });
					
						response(names);
                    }
                });
            }
	}); 
  		});
  	</script>
  	<style type="text/css">
	    .mouseOut {
		    background: #708090;
		    color: #FFFAFA;
	    }
	
	    .mouseOver {
		    background: #FFFAFA;
		    color: #000000;
	    }
    </style>
  </head>
  
  <body bgColor="white" background="Image/right.gif" style="width: 100%;">
  	<div
		style="font-weight: bold; font-size: 13px; background-repeat: repeat; background-image: url(Image/title.bmp);">
		&gt;&gt;当前位置：搜索
	</div>
  	<div style="font-weight: bold; font-size: 13px; background-repeat: repeat;">
  		<span id="searchInput">
  			
					员&nbsp;工：
					<input id="stafferSelect"type="text" style="width:125px;">
  			<input id="searchButton" type="button" value="查找"></input>
  			&nbsp;&nbsp;&nbsp;
  			<!-- 
  			<select id="selectOption">
  				<option value="1">定位</option>
  				<option value="2">跟踪</option>
  			</select>
  			 -->
  		</span>
  	</div>
  	<div style="overflow: auto;">
  		<table id="dataTable" width="100%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
  			<tr >
  				<td  align="left" bgcolor="#B0C4DE">姓名</td>
  				<td  align="left" bgcolor="#B0C4DE">班组</td>
  				<td  align="left" bgcolor="#B0C4DE">卡号</td>
  				<td  align="left" bgcolor="#B0C4DE">下井时间</td>
  				<td  align="left" bgcolor="#B0C4DE">时长</td>
				<td  align="left" bgcolor="#B0C4DE">区域</td>
	    		<td  align="left" bgcolor="#B0C4DE">位置</td>
	    		<td  align="left" bgcolor="#B0C4DE">进入时间</td>
	    		<td  align="left" bgcolor="#B0C4DE">时长</td>
	    		<td  align="left" bgcolor="#B0C4DE">其他信息</td>
  			</tr>
  			<tr id="dataTemplate" style="display: none;">
  				<td id="1" align="left" bgcolor="#E6E6FA" nowrap="nowrap"></td>
  				<td id="2" align="left" bgcolor="#E6E6FA" nowrap="nowrap"></td>
  				<td id="3" align="left" bgcolor="#E6E6FA" nowrap="nowrap"></td>
  				<td id="4" align="left" bgcolor="#E6E6FA" nowrap="nowrap"></td>
  				<td id="5" align="left" bgcolor="#E6E6FA" nowrap="nowrap"></td>
  				<td id="6" align="left" bgcolor="#E6E6FA" nowrap="nowrap"></td>
  				<td id="7" align="left" bgcolor="#E6E6FA" nowrap="nowrap"></td>
  				<td id="8" align="left" bgcolor="#E6E6FA" nowrap="nowrap"></td>
  				<td id="9" align="left" bgcolor="#E6E6FA" nowrap="nowrap"></td>
  				<td id="10" align="left" bgcolor="#E6E6FA" nowrap="nowrap"></td>
  			</tr>
  		</table>
  	</div>
  	<table style="display: none;">
  		<tr id="loadingGif">
			<td colspan="10" style="width: 100%; height: 50px;">
				<img src="images/loading.gif">
			</td>
		</tr>
  	</table>
  </body>
</html>
