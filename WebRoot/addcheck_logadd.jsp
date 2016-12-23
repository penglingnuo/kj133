
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="增加考勤记录" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>addcheck_log.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
 
    <script language="javascript" src="js/page.js"></script>
    <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <script language="javascript" src="js/StafferORName1.js"></script>
    <script language="JavaScript" src="js/Locator.js"></script>
    <link   href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <style TYPE="text/css">
		<!--
		  A:link{color:#FF0000;text-decoration:none} /*   未访问的链接   */ 
		  A:hover{color:#FF0000;text-decoration:none} /*   鼠标在链接上   */
		  A:visited{color:#FF0000;text-decoration:none}/*   已访问的链接   */ 
		 -->
    </style>
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
    <SCRIPT language="JavaScript">
      function   date2str(d){   
      var   ret=d.getFullYear()+"-"   
      ret+=("00"+(d.getMonth()+1)).slice(-2)+"-"   
      ret+=("00"+d.getDate()).slice(-2)+" "   
      ret+=("00"+d.getHours()).slice(-2)+":"   
      ret+=("00"+d.getMinutes()).slice(-2)+":"   
      ret+=("00"+d.getSeconds()).slice(-2)  
      return   ret   
      }   
      function check()
       {
       var time=date2str(new Date());
         var stime=document.all['ser_addchecklog.stime'].value;
         var etime=document.all['ser_addchecklog.etime'].value;
         
         var banname=document.all['ser_addchecklog.banname'].value;
         var bantypename=document.all['ser_addchecklog.bantypename'].value;
         
         var sid=document.getElementById("stafferid").value;
         var stime1 = new Date(stime.substring(0,4),stime.substring(5,7),stime.substring(8,10),stime.substring(11,13),stime.substring(14,16),stime.substring(17,19)); 
	     var etime1 = new Date(etime.substring(0,4),etime.substring(5,7),etime.substring(8,10),etime.substring(11,13),etime.substring(14,16),etime.substring(17,19)); 
	     
	     var DyMilli = 1000 * 60 * 60 

         var days = Math.round((etime1.getTime()-stime1.getTime()) / DyMilli); 
          if(bantypename==null || bantypename==""){
            alert('请选择班制');
            return false;
         }
          if(banname==null || banname==""){
            alert('请选择班次');
            return false;
         }
         
         if( stime >= etime){
            alert('起始时间不能大于或等于截止时间');
            return false;
         }if( etime >= time){
            alert('考勤结束时间不应该大于当前时间');
            return false;
         }if(sid == ""){
           alert('员工号不能为空');
           return false;
         }if(days>15){
            alert('工作时间不应超过15小时');
            return false;
             }
         return true;
         
       }

       function go(){
               window.history.go(-1);
       }
    </SCRIPT>

  </head>
  
  <body bgColor="white" background="Image/right.gif"  > 
	    <logic:messagesPresent message="true">
	        <html:messages id="message" message="true">
	        <font color="red"><bean:write name="message" /></font>
	        </html:messages>
		</logic:messagesPresent>
    <html:form action="/aaddcheck_log?method=save" onsubmit="return check()">
       <table width="30%">
               <tr>
                 	<td align="left">员工编号：</td>
                    <td><html:text property="ser_addchecklog.stafferid" size="10" maxlength="6"  styleId="stafferid" onkeyup="cardNames();"   />
	                  <div style="position:absolute;" id="cardpopup">
				        <table id="cardname_table" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="cardname_table_body"></tbody>
				        </table>  
                      </div><font color="red">*不可为空</font>
                 	</td>
                 <tr>
                 
                 <tr>
                 	<td align="left">开始时间：</td>
                 	<td align="left">
                 		<html:text property="ser_addchecklog.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_addchecklog.stime')})"/>
                 	</td>
                 </tr>
                 <tr>
                 	<td align="left">结束时间：</td>
                 	<td align="left">
                 		<html:text property="ser_addchecklog.etime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_addchecklog.etime')})"/>
                 	</td>
                 </tr>
                 <tr>
                 	<td align="left">班制:</td>
	                 <td align="left">
		                 <div style="position:relative;">
		                 <span style="margin-left:82px;width:18px;overflow:hidden;">
		                 <select  style="width:118px;margin-left:-100px"  onchange="this.parentNode.nextSibling.value=this.value">
		                 <logic:present name="banz_list">
		                 <option value=""></option>
		                 	<logic:iterate id="bb" name="banz_list">
		                 		<option value='<bean:write name="bb" property="bantypename"/>'><bean:write name="bb" property="bantypename"/></option>
		                 	</logic:iterate>
		                 </logic:present>
		                 </select>
		                 </span>
		                 <html:text property="ser_addchecklog.bantypename" style="width:82px;position:absolute;left:0px;"></html:text>
						<font color="red">*不可为空</font></div>
	                 </td>
                 </tr>
                 <tr>
                 	 <td align="left">班次:</td>
	                 <td align="left">
		                 <div style="position:relative;">
		                 <span style="margin-left:82px;width:18px;overflow:hidden;">
		                 <select  style="width:118px;margin-left:-100px"  onchange="this.parentNode.nextSibling.value=this.value">
		                 <logic:present name="banc_list">
		              		<option value=""></option>
		                 	<logic:iterate id="cc" name="banc_list">
		                 		<option value='<bean:write name="cc" property="banname"/>'><bean:write name="cc" property="banname"/></option>
		                 	</logic:iterate>
		                 </logic:present>
		                 </select>
		                 </span>
		                 <html:text property="ser_addchecklog.banname" style="width:82px;position:absolute;left:0px;"></html:text>
					<font color="red">*不可为空</font></div>
	                 </td> 
                 </tr>
                 <tr>
                 	<td align="left">增加原因：</td>
                 	<td align="left">
                 		<html:text property="ser_addchecklog.modifyreason" size="18" maxlength="20"/>
                 	</td>
                 </tr>
                <tr>
                 	<td> </td>
	              	<td> </td>
	            </tr>
	            <tr>
                 	<td> </td>
	              	<td> </td>
	            </tr>
	            <tr>
                 	<td> </td>
	              	<td> </td>
	            </tr>
	            <tr>
                 	<td> </td>
	              	<td> </td>
	            </tr>
                 <tr>
                 	<td> &nbsp;&nbsp;&nbsp;&nbsp;
	              		<html:submit>增 加</html:submit>&nbsp; 
	              	</td>
	              
	              	<td> &nbsp;&nbsp;&nbsp;&nbsp;
	              		<input type="reset"name="reset"value="重置"/>
	              	</td>
	              <!--  <td>
	                  <input type="button" value="返 回" onClick="go()">
	               </td> -->
	            </tr>
       </table>
     </html:form>
  </body>
</html:html>
