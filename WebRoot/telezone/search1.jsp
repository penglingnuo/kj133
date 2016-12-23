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
  	<script type="text/javascript" src="js/jquery.js"></script>
  	<script type="text/javascript" src="js/search.js"></script>
  	
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
  			<input id="keyWord" type="text" size="10">
  			<div style="position:absolute;" id="cardpopup">
                <table id="cardname_table" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
                       <tbody id="cardname_table_body"></tbody>
                </table>  
            </div>
  		</span>
  		<span id="searchPath">
  			<select id="searchType" onchange="selectChange(this)">
  				<option value="1">卡号</option>
  				<option value="2">员工编号</option>
  				<option value="3">拼音</option>
  				<option value="4">基站</option>
  				<option value="5">定位器</option>
  			</select>
  		</span>
  		<span id="searchButton">
  			<input id="searchButton" type="button" value="查找"></input>
  		</span>
  	</div>
  	<div>
  		<table id="dataTable" cellpadding="0" cellspacing="0" style="font-size: 12px; width: 100%; text-align: center; border: 1px solid gray;" border="1">
  			<tr style="font-weight: bold; font-size: 14px;">
  				<td>基站名称</td>
  				<td>进入时间</td>
  				<td>离开时间</td>
  			</tr>
  			<tr id="dataTemplate" style="display: none;">
  				<td id="1" style="font-weight: bold;"></td>
  				<td id="2"></td>
  				<td id="3"></td>
  			</tr>
  		</table>
  		<table id="dataTableByCardreader" cellpadding="0" cellspacing="0" style="font-size: 12px; width: 100%; text-align: center; border: 1px solid gray; display: none;" border="1">
  			<tr style="font-weight: bold; font-size: 14px;">
  				<td>卡号</td>
  				<td>进入时间</td>
  				<td>离开时间</td>
  			</tr>
  			<tr id="dataTemplate1" style="display: none;">
  				<td id="1" style="font-weight: bold;"></td>
  				<td id="2"></td>
  				<td id="3"></td>
  			</tr>
  		</table>
  	</div>
  </body>
</html>
