<%@ page language="java" import="com.kj133.entity.*,javax.servlet.http.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%
String path = request.getContextPath();

%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />

    <title>menu_left_back.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	  <style type="text/css">
body {font:13px Tahoma, sans-serif;color:#4D4D4D;margin:0px; text-align:left}
a{color:#4D4D4D;text-decoration:none}
a:hover{color:#AD0000;text-decoration:none}
#menu {width:160px;margin:0px;padding:0px;text-align:left;list-style:none;border:1px solid #B0D4ED}
#menu .item {margin:0px;padding:0px;list-style:none; text-align:center}
a.title:link,a.title:visited,a.title:hover {display:block;height:30px;line-height:30px; margin-top:1px;background:#E1F2FD;border-top:1px solid 	#B0D4ED;border-bottom:1px solid #B0D4ED;font-weight:bold;}
a.title1:link,a.title1:visited,a.title1:hover {display:block;height:30px;line-height:30px; margin-top:1px;background:#E1F2FD;border-top:1px solid 	#B0D4ED;border-bottom:1px solid #B0D4ED;font-weight:bold;}
#menu .item ul {margin:0;list-style:none;display:none;text-align:center}
#menu .item ul li {height:24px;line-height:22px;border-bottom:1px solid #BFDBEE;margin:0 20px}

</style>

<script type="text/javascript" src="js/jquery.js"></script>

<script language="javascript" type="text/javascript">
$(function(){
	$("#menu li").each(function(i, n){
		if($(this).find("table tr").length === 0){
			$(this).hide();
		}
	});
	$("#menu li").eq($("#menu li").length - 1).show();
});
// --- 获取ClassName
document.getElementsByClassName = function(cl) {
	var retnode = [];
	var myclass = new RegExp('\\b'+cl+'\\b');
	var elem = this.getElementsByTagName('*');
	for (var j = 0; j < elem.length; j++) {
		var classes = elem[j].className;
		if (myclass.test(classes)) retnode.push(elem[j]);
	}
	return retnode;
}
// --- 隐藏所有
function HideAll() {
	var items = document.getElementsByClassName("optiton");
	for (var j=0; j<items.length; j++) {
		items[j].style.display = "none";
	}
}
// --- 设置cookie
function setCookie(sName,sValue,expireHours) {
	var cookieString = sName + "=" + escape(sValue);
	//;判断是否设置过期时间
	if (expireHours>0) {
		 var date = new Date();
		 date.setTime(date.getTime + expireHours * 3600 * 1000);
		 cookieString = cookieString + "; expire=" + date.toGMTString();
	}
	document.cookie = cookieString;
}
//--- 获取cookie
function getCookie(sName) {
  var aCookie = document.cookie.split("; ");
  for (var j=0; j < aCookie.length; j++){
	var aCrumb = aCookie[j].split("=");
	if (escape(sName) == aCrumb[0])
	  return unescape(aCrumb[1]);
  }
  return null;
}
window.onload = function() {
	var show_item = "opt_1";
	if (getCookie("show_item") != null) {
		 show_item= "opt_" + getCookie("show_item");
	}
	document.getElementById(show_item).style.display = "block";
	var items = document.getElementsByClassName("title");
	for (var j=0; j<items.length; j++) {
		items[j].onclick = function() {
			var o = document.getElementById("opt_" + this.name);
			
			if (o.style.display != "block") {
				HideAll();
				o.style.display = "block";
				setCookie("show_item",this.name);
			}
			else {
				o.style.display = "none";
			}
		}
	}
}
function linkcolorchange(objLink)
		{
			for(var i=0;i<document.links.length;i++)
			{
				document.links[i].style.color = "" ;
			}
				objLink.style.color = "red" ;
		}
		
		//定位跟踪方法
		function locationClick(objLink) {
			for(var i=0;i<document.links.length;i++)
			{
				document.links[i].style.color = "" ;
			}
			objLink.style.color = "red" ;
			
			//更改页面布局
			parent.frame.cols="161,10,*,0";
//			alert(url);
			//parent.frames["content"].location.href = url;
		}
</script>
  </head>
  
 <%--<body   bgColor="white"  style="overflow:hidden"> 
 --%><body   bgColor="white"> 

	  <ul id="menu">
	<li class="item"><a href="javascript:void(0)" class="title" name="1">用户管理(<%= request.getAttribute("userid")%>)</a>
	  <ul id="opt_1" class="optiton">
	     <table>
		   <logic:present name="resulta">
		     <logic:iterate id="result" name="resulta">
		         <tr>
		           <td><a onClick="linkcolorchange(this)" href="<bean:write name="result" property="phref" /> " target="content"><FONT size="2"><bean:write name="result" property="pname"/></FONT></a></td>
		         </tr>	         
		     </logic:iterate>	   
		   </logic:present>
		</table>
	  </ul>
	</li>
	<!--<li class="item" ><a href="javascript:void(0)" class="title" name="2">实时信息</a>
	  <ul id="opt_2" class="optiton">
	     <table>
		   <logic:present name="resultg">
		     <logic:iterate id="result" name="resultg">
		         <tr>
		           <td><a onClick="linkcolorchange(this)" href="<bean:write name="result" property="phref" /> " target="content" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''"><FONT size="2"><bean:write name="result" property="pname"/></FONT></a></td>
		         </tr>	         
		     </logic:iterate>	   
		   </logic:present>
		</table>
	  </ul>
	</li>-->
	 <li class="item"><a href="javascript:void(0)" class="title" name="2">实时信息</a>
	  <ul id="opt_2" class="optiton">
		 <table>
		   <logic:present name="resultb">
		     <logic:iterate id="result" name="resultb">
		         <tr>
		           <td><a onClick="linkcolorchange(this)" href="<bean:write name="result" property="phref" /> " target="content"><FONT size="2" ><bean:write name="result" property="pname"/></FONT></a></td>
                 </tr>	         
		     </logic:iterate>	   
		   </logic:present>
		</table>
	  </ul>
	</li>
	<li class="item"><a href="javascript:void(0)" class="title" name="3">数据查询</a>
	  <ul id="opt_3" class="optiton">
		 <table>
		   <logic:present name="resultc">
		     <logic:iterate id="result" name="resultc">
		         <tr>
		           <td><a onClick="linkcolorchange(this)" href="<bean:write name="result" property="phref" />" target="content"><FONT size="2" ><bean:write name="result" property="pname"/></FONT></a></td>
		         </tr>	         
		     </logic:iterate>	   
		   </logic:present>
		</table>
		   
	  </ul>
	</li>
	<li class="item"><a href="javascript:void(0)" class="title" name="4">人员定位</a>
	  <ul id="opt_4" class="optiton">
		 <table>
		   <logic:present name="resultd">
		     <logic:iterate id="result" name="resultd">
		         <tr>
		           <td><a onClick="linkcolorchange(this)" href="<bean:write name="result" property="phref" /> " target="content"><FONT size="2" ><bean:write name="result" property="pname"/></FONT></a></td>
		         </tr>	         
		     </logic:iterate>	   
		   </logic:present>
		</table>
	  </ul>
	</li>
	<li class="item"><a href="javascript:void(0)" class="title" name="5">报警查询</a>
	  <ul id="opt_5" class="optiton">
		<table>
		   <logic:present name="resulte">
		     <logic:iterate id="result" name="resulte">
		         <tr>
		            <td><a onClick="linkcolorchange(this)" href="<bean:write name="result" property="phref" /> " target="content"><FONT size="2" ><bean:write name="result" property="pname"/></FONT></a></td>
		         </tr>
		         	         
		     </logic:iterate>	   
		   </logic:present>
		</table>
	  </ul>
	</li>
	<li class="item"><a href="javascript:void(0)" class="title" name="6">考勤报表</a>
	  <ul id="opt_6" class="optiton">
		 <table>
		   <logic:present name="resultf">
		     <logic:iterate id="result" name="resultf">
		         <tr>
		           <td><a onClick="linkcolorchange(this)" href="<bean:write name="result" property="phref" /> " target="content"><FONT size="2" ><bean:write name="result" property="pname"/></FONT></a></td>
		         </tr>	         
		     </logic:iterate>	   
		   </logic:present>
		</table>
	  </ul>
	</li>
	<li class="item">
		<a href="login.do?method=userLogOut" class="title1" id="exit"  target="_parent">退出系统</a>
	</li>
	<!-- 
		<li class="item"><a href="javascript:void(0)" class="title" name="8">车辆管理</a>
		  <ul id="opt_8" class="optiton">
			 <table>
			   <logic:present name="resulth">
			     <logic:iterate id="result" name="resulth">
			         <tr>
			           <td><a onClick="linkcolorchange(this)" href="<bean:write name="result" property="phref" /> " target="content" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''"><FONT size="2" ><bean:write name="result" property="pname"/></FONT></a></td>
			         </tr>	         
			     </logic:iterate>	   
			   </logic:present>
			</table>
		  </ul>
	</li>
	 -->
</ul>
	
</body>
</html:html>
