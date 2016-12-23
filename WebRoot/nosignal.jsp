
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="定位器无信号查询" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>nosignal.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/page.js"></script><!--分页 -->
    <script language="JavaScript" src="js/Locator.js"></script>
    <script language="javascript" src="js/jquery.js"></script>
    <script language="javascript" src="js/export_xls.js"></script>
    <SCRIPT language="javascript">
         function check()
          {
               var day=document.all['ser_nosingal.day'].value;
               if(day=="")
               {
                   alert('天数不能为空');
                   document.all['ser_nosingal.day'].focus();
                   return false;
               }
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
		    //WebBrowser1.ExecWB(7, 1); //预览
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
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
       <html:form action="/noSignal?method=getList" onsubmit="return check()">
          <table>
              <tr>
                  <th>定位器：</th>
                  <th>
                      <html:text property="ser_nosingal.locatorid" size="20" maxlength="4" styleId="names4" onkeyup="findNames4();"   />
                      <div style="position:absolute;" id="popup4">
					        <table id="name_table4" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
					            <tbody id="name_table_body4"></tbody>
					        </table>  
                      </div>
                  </th>
                  <th>&nbsp;&nbsp;</th>
                  <th>最   近： </th>
                  <th><html:text property="ser_nosingal.day" size="8" maxlength="3" onkeyup="this.value=this.value.replace(/\D/g,'')" />天内无信号</th>
                  <th>&nbsp;&nbsp;</th>
                  <th><html:submit>查  询</html:submit></th>
                  <th>&nbsp;&nbsp;</th>
                  <th>
	                    <logic:equal  name="listCount" value="0">
	                       <input type="button"  value="打印"  disabled>
	                       <input type="button" value="导出excel" disabled/>
		                </logic:equal>
		                <logic:notEqual name="listCount" value="0"> 
		                	<input type="button" id="print" value="打印" class="button_bak" onclick="javascript:printdiv();"/>  
		                  <!-- <input type="button"  value="打印预览"  onclick="javascript:window.open('nosignalPrint.jsp')"> -->
		                  <input type="button" value="导出excel" onclick="ExportXls('noSignal.do?method=export')"/>
		                </logic:notEqual>
                  </th> 
                  
              </tr>
          </table><br> 
         <div id = "printdiv">
         <TABLE  width="650" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
                <TR>
                  <TD width="70" align="left"  bgcolor="#B0C4DE">定位器号</TD>
                  <TD width="320" align="left" bgcolor="#B0C4DE">定位器名</TD>
                  <TD width="180" align="left" bgcolor="#B0C4DE">最后信号时间</TD> 
                </TR>
               <logic:present name="Nosignal_List">
                   <logic:iterate name="Nosignal_List" id="no">
	                    <TR onmouseover="this.style.background='#CCCCCC'; " onmouseout ="this.style.background=''; this.style.borderColor=''">
		                       <TD  align="left" bgcolor="#E6E6FA"><bean:write name="no" property="lid" /></TD>
		                       <TD  align="left" bgcolor="#E6E6FA"><bean:write name="no" property="sname" /></TD>
		                       <TD  align="left" bgcolor="#E6E6FA"><bean:write name="no" property="times" format="yyyy-MM-dd"/></TD>
	                    </TR>
                   </logic:iterate>
               </logic:present>
                     <logic:present name="pagination" >
		                 <TR>
							  <TD colspan="3" align="center" bgcolor="#E6E6FA">
							          <page:pagination path="noSignal.do?method=getList" name="pagination" parameter="page"  formName="Search_NoSingnal_Form" />
							  </TD>  
		                 </TR>
                     </logic:present> 
           </TABLE></div>
       </html:form>
  </body>
</html:html>
