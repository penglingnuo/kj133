<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true"> 
  <head>
    <html:base />
    <title>Test.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
	    
    <script type="text/javascript">
	   var XMLHttpReq; //创建XMLHttpRequest对象       

      function createXMLHttpRequest() {
               if (window.ActiveXObject) {
                XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
            } 
            else if (window.XMLHttpRequest) {
                XMLHttpReq = new XMLHttpRequest();                
            }
         }

        function sendRequest() { //开始接收
              createXMLHttpRequest();
              var url = "<%=request.getContextPath()%>/track.do?method=trackQuery";
              XMLHttpReq.open("GET", url, true);
              XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
              XMLHttpReq.send(null);  // 发送请求
            }

 
	      
         function processResponse() {
                if(XMLHttpReq.readyState == 4){
					  if(XMLHttpReq.status == 200){
					         showPage();
					         // setTimeOut("sendRequest()",5000);(表达式,延时时间)在执行时,是在载入后延迟指定时间后,去执行一次表达式,记住,次数是一次
	                         setInterval('sendRequest();',5000);//(表达式,交互时间)它从载入后,每隔指定的时间就执行一次表达式
						}else{
					         document.getElementById("result").innerHTML = "服务器连接失败......"; 
					      }    
				 }
          }
         function showPage(){
               var xmlDoc = XMLHttpReq.responseXML;
	           var tracks = xmlDoc.getElementsByTagName("track");
	           var result="<table   cellspacing='1' cellpadding='1'  bgcolor='#6CA6CD' border='0'> ";
	           result +="<tr><td width='40' align='left' bgcolor='#B0C4DE'>工种</td>";
	           result +="<td width='40'  align='left' bgcolor='B0C4DE'>卡号</td>";
	           result +="<td width='60'  align='left' bgcolor='B0C4DE'>姓名</td>";
	           result +="<td width='50'  align='left' bgcolor='#B0C4DE'>班组</td>";
	           result +="<td width='60'  align='left' bgcolor='#B0C4DE'>所属分站</td>";
	           result +="<td width='160' align='left' bgcolor='#B0C4DE'>分站名称</td>";
	         
	           result +="<td width='30'  align='left' bgcolor='#B0C4DE'>天线</td>";
	           result +="<td width='140' align='left' bgcolor='#B0C4DE'>状态信息</td></tr>";
	           for(var i=0;i<tracks.length;i++){
		               result +="<tr>";
		               var track=tracks[i];
		               result  +="<td align='left' bgcolor='#E6E6FA'>"+ track.getElementsByTagName('worktype')[0].firstChild.nodeValue+"</td>"
		               result  +="<td align='left' bgcolor='#E6E6FA'>"+ track.getElementsByTagName('cardid')[0].firstChild.nodeValue+"</td>"
		               result  +="<td align='left' bgcolor='#E6E6FA'>"+ track.getElementsByTagName('username')[0].firstChild.nodeValue+"</td>"
		               result  +="<td align='left' bgcolor='#E6E6FA'>"+ track.getElementsByTagName('gro')[0].firstChild.nodeValue+"</td>"
		               result  +="<td align='left' bgcolor='#E6E6FA'>"+ track.getElementsByTagName('cardreaderid')[0].firstChild.nodeValue+"</td>"
		               result  +="<td align='left' bgcolor='#E6E6FA'>"+ track.getElementsByTagName('shortname')[0].firstChild.nodeValue+"</td>"
		              
		               result  +="<td align='left' bgcolor='#E6E6FA'>"+ track.getElementsByTagName('antenna')[0].firstChild.nodeValue+"</td>"
		               result  +="<td align='left' bgcolor='#E6E6FA'>"+ track.getElementsByTagName('state')[0].firstChild.nodeValue+"</td>"
		               result +="</tr>";
	                }
	             result +="</table>";
	             document.getElementById("result").innerHTML=result;
         }      
 </script>

  </head>
  
  <body  bgColor="white" background="Image/right.gif">
        <input type="button" value="开始接收" onclick="sendRequest()"> 
        <input type="button" value="停止接收" onclick="stopRequest()"> 
        <div id="result"></div>
         
</body>    
</html:html>


  