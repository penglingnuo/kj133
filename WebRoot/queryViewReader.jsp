
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="查询分站"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<%
String name1=(String)request.getAttribute("name1");
String name2=(String)request.getAttribute("name2");
String name3=(String)request.getAttribute("name3");
String name4=(String)request.getAttribute("name4");
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>queryViewReader.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
     <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
     </script> 
     <script language="javascript" src="js/page.js"></script>
     <script language="JavaScript" src="js/calendar.js"></script><!--分秒的 -->
     <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
     <script language="javascript" src="js/Cardreader.js"></script>
     <script language="javascript" src="js/jquery.js"></script>
     <script language="javascript" src="js/export_xls.js"></script>
    
     <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
         <SCRIPT language="JavaScript">
       function check()
       {
         var stime=document.all['ser_viewreader.stime'].value;
         var etime=document.all['ser_viewreader.etime'].value;
         var name1 = document.getElementById("name1").checked;
         var name3 = document.getElementById("name3").checked;
         
         if(name1 && name3){
         
         if(stime>etime){
           alert('起始时间不能大于截止时间');
           return false;
         }
         return true;
         }
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
	     <logic:messagesPresent message="true">
			  <html:messages id="message" message="true">
			     <font color="red"><bean:write name="message" /></font>
			  </html:messages>
           </logic:messagesPresent>
      <%-- --%>
     <html:form action="ReaderQuery.do?method=getList">
          <table width="100%">
          <tr>
          		 <th align="left">分站:</th>
                 <th align="left">
                    <html:text property="ser_viewreader.cid" size="16"  styleId="names3" onkeyup="findNames3();"   />
                     <div style="position:absolute;" id="popup3">
				        <table id="name_table3" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="name_table_body3"></tbody>
				        </table>  
                      </div>
                </th>
                 <th>&nbsp;</th>
                 <th align=" left">
					状&nbsp;态：
				</th>
				<th align="left">
					<div style="position: relative;">
						<span style="margin-left: 82px; width: 18px; overflow: hidden;">
							<select style="width: 118px; margin-left: -100px;height: 21px;" name="ser_viewreader.checkreader">
								<option value='全部'>全部</option>
								<option value='正常'>正常</option>
								<option value='停用'>停用</option>
							</select> </span>
					</div>
				</th>
				<th>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</th>
                  <th><html:submit>查 询</html:submit>&nbsp;</th>
                  <th  align="right">
                     <logic:equal  name="listCount" value="0">
                         <input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>
	                 </logic:equal>
	                 <logic:notEqual name="listCount" value="0"> 
	                 	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>  
	                 </logic:notEqual>
	                 &nbsp;
                 </th>
              <th  align="right"> 
                <logic:equal name="listCount" value="0">
						<input type="button" value="导出excel" disabled>
					</logic:equal>
					<logic:notEqual name="listCount" value="0">
						<input type="button" value="导出excel" onclick="ExportXls('ReaderQuery.do?method=doOpenExcel')">
					</logic:notEqual>
				</th>
              </tr>
	</table><br/>
	<div id = "printdiv">
         <TABLE width="100%" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" style="border-collapse:collapse;TABLE-LAYOUT:   fixed;   FONT-SIZE:   13px;" >
          <TR>
             <TD width="11%" align="left" bgcolor="#B0C4DE">分站号</TD>
             <TD width="15%" align="left" bgcolor="#B0C4DE">简称</TD>
             <TD width="11%"  align="left" bgcolor="#B0C4DE">地图X坐标</TD>
             <TD width="11%"  align="left" bgcolor="#B0C4DE">地图Y坐标</Td>
             <TD width="15%" align="left" bgcolor="#B0C4DE">注册时间</TD>
             <TD width="12%"  align="left" bgcolor="#B0C4DE">使用状态</TD>
             <TD width="15%" align="left" bgcolor="#B0C4DE">最后修改时间</TD>
           </TR>
         <logic:present name="ViewReaderList" scope="request">
            <logic:iterate name="ViewReaderList" id="vr">
                  <TR> 
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name='vr' property='cardreaderid' /></TD>
                      <TD align="left" bgcolor="#E6E6FA">
                      <html:link target= "_black" page="/setxy.do?method=setxy&cardid=${vr.cardreaderid}">
                      <bean:write name="vr" property="shortname" />
                      </html:link>
                      </TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="x" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="y" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="regdate" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="state" /></TD>
                      <TD align="left" bgcolor="#E6E6FA"><bean:write name="vr" property="modifydate" /></TD>
                  </TR>  
            </logic:iterate>
         </logic:present>
         <logic:present name="pagination" >
               <TR>
		           <TD colspan="7" align="center" bgcolor="#E6E6FA">
		                  <page:pagination path="ReaderQuery.do?method=getList" parameter="page"  formName="Search_ViewReader_Form" />
		           </TD>  
              </TR> 
            </logic:present>   
         </TABLE></div>
       </html:form>        
  </body>
</html:html>
