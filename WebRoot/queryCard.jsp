
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="查询卡信息" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<%
String name4=(String)request.getAttribute("name4");
String name3=(String)request.getAttribute("name3");
String name1=(String)request.getAttribute("name1");
String name2=(String)request.getAttribute("name2");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>queryCard.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="javascript" src="js/page.js"></script>
    <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
    <script language="javascript" src="js/CardIdORName.js"></script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
        <style TYPE="text/css">
		<!--
		  A:link{color:#FF0000;text-decoration:none} /*   未访问的链接   */ 
		  A:hover{color:#FF0000;text-decoration:none} /*   鼠标在链接上   */
		  A:visited{color:#FF0000;text-decoration:none}/*   已访问的链接   */ 
		 -->
    </style>
    <SCRIPT language="JavaScript">
       function check()
       {
         var stime=document.all['ser_eidtCard.stime'].value;
         var etime=document.all['ser_eidtCard.etime'].value;
         var name4 = document.getElementById("name4").checked;
         var name3 = document.getElementById("name3").checked;
         
         if(name4 && name3){
         if(stime>etime){
           alert('起始时间不能大于截止时间');
           return false;
         }
         return true;
       }
      } 
      
      function OpenExcel(){
			  window.parent.location.href="editCardQuery.do?method=doOpenExcel";
			     return true;
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

  <body bgColor="white" background="Image/right.gif">
    
	    <logic:messagesPresent  message="true">
		  <html:messages id="message" message="true" > 
		       <font color="red"> <bean:write name="message" /></font>
		   </html:messages>
        </logic:messagesPresent>
     
     <logic:messagesPresent>
		        <html:messages id="error">
		         <font color="red"><bean:write name="error" /></font>
		        <br/>
		        </html:messages>
      </logic:messagesPresent>
		<%-- Error Messages --%>
    <html:form action="editCardQuery?method=getList"  onsubmit="return check()">
       <table width="730">
          <tr>
              <th>注册日期大于:</th>
              <th align="left">
                 <html:text property="ser_eidtCard.stime" size="9" styleId="file4" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_eidtCard.stime')})"/>
              </th>
              <th align="left"><input type="checkbox" name="name4"  onclick="this.checked?file4.disabled=false:file4.disabled=true" />&nbsp;&nbsp;
              </th>
              <%--<th><html:text property="ser_eidtCard.stime" size="18" onfocus="show_cele_date('','',Search_eidtCard_Form['ser_eidtCard.stime'])" />
              </th>
              --%>
              <th align="left">时间大于:</th>
                 
                 <th align="left">
                 <html:text property="ser_eidtCard.minstime" size="8" styleId="file1" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_eidtCard.minstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name1" onclick="this.checked?file1.disabled=false:file1.disabled=true" />&nbsp;
                 </th>
              <th align="right">工 种:</th>
              <th align="left">
                 <div style="position:relative;">
                 <span style="margin-left:82px;width:18px;overflow:hidden;">
                 <select  style="width:118px;margin-left:-100px" onchange="this.parentNode.nextSibling.value=this.value">
                 <logic:present name="workType_list">
              		<option value=""></option>
                 	<logic:iterate id="aa" name="workType_list">
                 		<option value='<bean:write name="aa" property="wordvalue"/>'><bean:write name="aa" property="wordvalue"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
                 <html:text property="ser_eidtCard.worktype" style="width:82px;position:absolute;left:0px;"></html:text>
				</div> 
                 </th>
              
              <th>&nbsp;</th>
              <th><html:submit  value="查 询"/></th>
              
         </tr>
         <tr>
              <th>注册日期小于:</th>
              <th align="left">
                 <html:text property="ser_eidtCard.etime" size="9" styleId="file3" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_eidtCard.etime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name3"  onclick="this.checked?file3.disabled=false:file3.disabled=true" />&nbsp;&nbsp;
                 </th>
              <%--<th><html:text property="ser_eidtCard.etime" size="18" onfocus="show_cele_date('','',Search_eidtCard_Form['ser_eidtCard.etime'])"/></th>
              --%>
              <th align="left">时间小于:</th>
                 
                 <th align="left">
                 <html:text property="ser_eidtCard.maxstime" size="8" styleId="file2" disabled="true" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'HH:mm:ss',el:$dp.$('ser_eidtCard.maxstime')})"/>
                 </th>
                 <th align="left"><input type="checkbox" name="name2"  onclick="this.checked?file2.disabled=false:file2.disabled=true" />&nbsp;
                 </th>
              <th align="right">员 工:</th>
              <th align="left">
                  <html:text property="ser_eidtCard.cardid"  styleId="cardid" onkeyup="cardNames();"  maxlength="6"    size="13" />
                  <div style="position:absolute;" id="cardpopup">
                       <table id="cardname_table" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
                              <tbody id="cardname_table_body"></tbody>
                       </table>  
                  </div>
              </th>
              <%--<th>
                  <html:select property="ser_eidtCard.worktype" style="width:73px"> 
                       <html:option value="" ></html:option>
                       <html:options collection="workType_list" property="wordvalue" labelProperty="wordvalue"/>
                 </html:select>
             </th>           
         --%>
         <th>
              <logic:equal  name="listCount" value="0">
                    <th>
                         &nbsp;<input type="button" value="打印预览"  disabled>
                    </th>
              </logic:equal>
              <logic:notEqual name="listCount" value="0">
                    <th>
                    <input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/> 
                         &nbsp;<input type="button" value="打印预览"  onclick="javascript:window.open('editCardQueryPrint.jsp')">
                    </th>
               </logic:notEqual>
               &nbsp;&nbsp;
         </th>
         
         <th>
                <logic:equal name="listCount" value="0">
						<input type="button" value="导出excel" disabled>
					</logic:equal>
					<logic:notEqual name="listCount" value="0">
						<input type="button" value="导出excel"
							onclick="OpenExcel()">

					</logic:notEqual>
				</th>
          </tr>
       </table><br>
       <div id = "printdiv">
      <table width="580" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" >
          <tr>
            <td width="60"  align="left" bgcolor="#B0C4DE">卡号</td>
            <td width="67"  align="left" bgcolor="#B0C4DE">卡型号</td>
            <td width="67"  align="left" bgcolor="#B0C4DE">工种</td>

            <%--<td width="81" align="left" bgcolor="#B0C4DE">职务类型</td>
            --%>
            <td width="72"  align="left" bgcolor="#B0C4DE">姓名</td>
            <td width="90"  align="left" bgcolor="#B0C4DE">班组</td>
            <td width="70"  align="left" bgcolor="#B0C4DE">卡状态</td>
            <td width="210" align="left" bgcolor="#B0C4DE">注册时间</td>
            <td width="210" align="left" bgcolor="#B0C4DE">最后修改时间</td>
         </tr>
        <logic:present name="editCardQueryList" scope="request">
           <logic:iterate name="editCardQueryList" id="editCard">
              <tr>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="editCard" property="cardid" /></td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="editCard" property="cardmode" /></td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="editCard" property="worktype" /></td>
                    
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="editCard" property="name" /></td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="editCard" property="gro" /></td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="editCard" property="cardstate" /></td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="editCard" property="regdate" /></td>
                    <td align="left" bgcolor="#E6E6FA"><bean:write name="editCard" property="modifydate" /></td>
                </tr>
           </logic:iterate>
       </logic:present>
          <logic:present name="pagination" >
	         <tr>
			   <td colspan="8" align="center" bgcolor="#E6E6FA">
			       <page:pagination path="editCardQuery.do?method=getList" parameter="page" formName="Search_eidtCard_Form" />
			  </td>  
	        </tr> 
          </logic:present> 
      </table></div>
       </html:form>
  </body>
</html:html>
<script  language="javascript">
	if("<%=name4%>"=="true"){
	   	document.getElementById("name4").checked=true;
	   	document.getElementById("file4").disabled=false;
	}if("<%=name3%>"=="true"){
	   	document.getElementById("name3").checked=true;
	   	document.getElementById("file3").disabled=false;
	}if("<%=name1%>"=="true"){
	   	document.getElementById("name1").checked=true;
	   	document.getElementById("file1").disabled=false;
	}if("<%=name2%>"=="true"){
	   	document.getElementById("name2").checked=true;
	   	document.getElementById("file2").disabled=false;
	}
 
</script>