<%@ page language="java" import="java.text.*,java.util.*,com.kj133.action.*" pageEncoding="UTF-8"%>
<%@ page info="区域人数表"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>areatime_query_top.jsp</title>
	<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
	<script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script>
	<script language="javascript" src="js/page.js"></script>
	<%--分页--%>
	<script language="JavaScript" src="js/calendar.js"></script>
	<%--时间--%>
	<script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
	<link href="Css/TestDate.css" rel="stylesheet">
	<%--背景--%>
	<script language="javascript" src="js/CardIdORName.js"></script>
	<link href="Css/calendar-blue.css" rel="stylesheet">
	

	<script language="javascript" src="js/StafferORName.js"></script>
	<SCRIPT language="JavaScript">
       function check()
         {
	         var stime=document.all['areatime_query_top.stime'].value;
	         var etime=document.all['areatime_query_top.etime'].value;
//	         var stime = new Date("2008","06","11","08","30","40"); 
//             var etime = new Date("2008","06","13","12","40","50"); 
	         var stime1 = new Date(stime.substring(0,4),stime.substring(5,7),stime.substring(8,10),stime.substring(11,13),stime.substring(14,16),stime.substring(17,19)); 
	         var etime1 = new Date(etime.substring(0,4),etime.substring(5,7),etime.substring(8,10),etime.substring(11,13),etime.substring(14,16),etime.substring(17,19)); 
            

            var DyMilli = 1000 * 60 * 60 * 24 

             var days = Math.round((etime1.getTime()-stime1.getTime()) / DyMilli); 

	         if( stime > etime){
	            alert('起始时间不能大于截止时间');
	            return false;

             }if(days>7){
                alert('起始时间与截止时间不能超过7天');
                return false;
             }else{
             
             return true;
             }

        }
        

        
          //判断日期date1是否大于date2     months个月，可跨年度   
//      function     monthsOfDates(date1,date2,months){   
//            var   temp1=date1.split("-")   
//            var   temp2=date2.split("-")   
//            var   _months=(parseInt(temp2[0])-parseInt(temp1[0])+1)*12-(parseInt(temp1[1])+(12-parseInt(temp2[1])))   
//            if((_months>months)||(_months==months&&(parseInt(temp2[2])-parseInt(temp1[2])>-1)))return   true   
//                return   false   
//            }   
      //测试数据   
//       alert(   monthsOfDates("2004-4-30","2004-10-29",6))     f
//       alert(   monthsOfDates("2004-4-30","2004-10-30",6))     t
//       alert(   monthsOfDates("2004-4-30","2004-10-31",6))     t
//       alert(   monthsOfDates("2003-4-30","2004-10-29",6))     t
//       alert(   monthsOfDates("2003-12-30","2004-10-29",6))    t


       function topPrint(){
       	    
//			   window.parent.frames['top_left'].location.href="areatime_query_top.do?method=initPrint";
			   window.open('areatime_query_top.do?method=initPrint');
			  return false;
			}
 

 
    </SCRIPT>
    <SCRIPT language="JavaScript">
    	//打印功能
		function printdiv() {
		    var newWin = window.open('printer', '', '');
		    var titleHTML = document.getElementById("printdiv").innerHTML;
		    titleHTML = titleHTML.toString().replace("border=0", "border=1");
		    titleHTML = titleHTML.toString().replace("cellSpacing=1", "cellSpacing=0");
		    newWin.document.write(titleHTML);
		    newWin.document.location.reload();
		    newWin.print();
		    newWin.close();
		    WebBrowser1.ExecWB(7, 1); //预览
		}

	</SCRIPT>
    <%--
    <%
		
	  String msg=(String)request.getAttribute("msg");
	    
	  if(msg!=null)
	  out.println(msg);
	%>   
        --%><style TYPE="text/css">
		<!--
		 A:link{color: #6699CC;text-decoration:none}
		 A:hover{color:#FF0000;}
		 -->
    </style>
	<%--
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
    
  --%>
</head>

<body bgColor="white" background="Image/right.gif">
	<logic:messagesPresent message="true">
		<html:messages id="message" message="true">
			<font color="red"><bean:write name="message" />
			</font>
		</html:messages>
	</logic:messagesPresent>


	<html:form action="areatime_query_top?method=init" onsubmit="return check()">
		<table>
			<tr>
				<th>
					开始时间：
				</th>
				<th align="left">
                 <html:text property="areatime_query_top.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('areatime_query_top.stime')})"/>
                 </th>
				<th>
					&nbsp;&nbsp;
				</th>
				<th>
					&nbsp;&nbsp;
				</th>
				<th>
				    区 域：
				</th>
				<th align="left">
					<html:select property="areatime_query_top.areaid" style="width:100px">&nbsp;&nbsp;&nbsp;
		                <html:option value=""></html:option>
						<html:options collection="areaid_list" property="areaid"
							labelProperty="id_name" />
					</html:select>
	            <%--<th>
					<html:select property="areatime_query_top.areaid">&nbsp;&nbsp;&nbsp;
		                <html:option value=""></html:option>
						<html:options collection="areaid_list" property="areaid"
							labelProperty="areaid" />
					</html:select>
				</th>
				--%>
				<tr>
				<th>
					结束时间：
				</th>
				<th align="left">
                 <html:text property="areatime_query_top.etime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('areatime_query_top.etime')})"/>
                 </th>
				
				<th>
					&nbsp;&nbsp;
				</th>
				<th>
					&nbsp;&nbsp;
				</th>
				
				
				
				<th>
					<html:submit>查 询</html:submit>
				</th>
				<th>
					&nbsp;&nbsp;
				</th>
				<th >
                   
                     <logic:equal  name="listCount" value="0">
                         <input type="button"  value="打印预览"  disabled>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0">  
	                 <input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
	                     <%--<input type="button"  value="打印预览"  onclick="javascript:window.open('areatime_query_topPrint.jsp')">
	               --%>
	               <input type="button"  value="打印预览"  onclick="topPrint()">
	               </logic:notEqual>
                 </th>

			</tr>
		
		</table>
		<td ><font size="2" color="red">区域人数表:</font></td>
		<%-- 
          <div   style="overflow:auto;width:825;height:500;"> 
          --%>
          
<div id = "printdiv">
		<table cellspacing="1" cellpadding="1" bgcolor="#6CA6CD" border="0"
			style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;">
			<tr>
				<td width="67" align="left" bgcolor="#B0C4DE">
					区域号
				</td>
				<td width="100" align="center" bgcolor="#B0C4DE">
					区域名称
				</td>
				<td width="150" align="center" bgcolor="#B0C4DE">
					记录时间
				</td>
				<td width="80" align="center" bgcolor="#B0C4DE">
					外来人员数量
				</td>
				<td width="80" align="center" bgcolor="#B0C4DE">
					采掘人员数量
				</td>
				<td width="70" align="center" bgcolor="#B0C4DE">
					区域总人数
				</td>
				
				

			</tr>
			<logic:present name="relist" scope="request">
				<logic:iterate name="relist" id="relist">
				<tr onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
					
						<td align="center" bgcolor="#E6E6FA">
							<a href="areatime_query_down.do?method=init&areaid=<bean:write name="relist" property="areaid"/>&recordtime=<bean:write name="relist" property="recordtime" />" title="区域人员明细表" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="down" >
							<bean:write name="relist" property="areaid" />
							</a>
							
							</td>
						<td align="left" bgcolor="#E6E6FA">
						<a href="areatime_query_right.do?method=init&areaid=<bean:write name="relist" property="areaid"/>&recordtime=<bean:write name="relist" property="recordtime" />" title="区域人员统计" onmousemove="javascript:window.status=''"  onmouseout="javascript:window.status=''" class="A:link"  target="top_right" >
							<bean:write name="relist" property="areaname"/>
							</a>
							
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="recordtime" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
							<bean:write name="relist" property="visitorcount" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
						<bean:write name="relist" property="minercount" />
						</td>
						<td align="left" bgcolor="#E6E6FA">
						<bean:write name="relist" property="peoplecount" />
						</td>
						
						

					</tr>
				</logic:iterate>
			</logic:present>
			<logic:present name="pagination">
				<tr>
					<td colspan="6" align="left" bgcolor="#E6E6FA">
						<page:pagination path="areatime_query_top.do?method=init"
							parameter="page" formName="Search_areatime_query_top_Form" />
					</td>
				</tr>
			</logic:present>
		
		
		</table></div>
		
	</html:form>

</body>

</html:html>

