
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page info="查询考勤记录" %>
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
         var stime=document.all['ser_addchecklog.stime'].value;
         var etime=document.all['ser_addchecklog.etime'].value;
         var time=date2str(new Date());
         
         
         
         if( stime >= etime){
            alert('起始时间不能大于或等于截止时间');
            return false;
         }if( etime >= time){
            alert('考勤结束时间不应该大于当前时间');
            return false;
         }
         return true;
       }
       function add()
       {
          window.location.href='aaddcheck_log.do?method=initialization';
       }
    </SCRIPT>
    <script language="javascript">
			function CheckAll(form)
			{
				for (var i=0;i<form.elements.length;i++){
					var e = form.elements[i];
					if (e.name == 'checkId')
					e.checked = form.checkall.checked;
				}
			}
			
			function DeleteSomeLine(form)
			{
				var v1 = document.getElementById("idd").value;
				
			    if (SelectedCounts(form,"checkId")>0){
				    if(confirm("删除已选中的记录,将无法恢复！\n     确定要这样做吗？"))
				    {
				     var str="";
				     var init=document.getElementsByName('checkId');
				     for(var i=0;i<init.length;i++)
				       {
				         if(init[i].checked)
				         {
				             str=str+','+init[i].value;    
				         }
				       }
				     //document.getElementById("frm").action="aaddcheck_log.do?method=delete&lid=''";
				     //document.getElementById("frm").submit();
				     location.href="aaddcheck_log.do?method=delete&lid="+str;   
					 return true;
				    }
				   }else{
					    alert("请先选择要删除的记录!");
					    return false;
				    }
			}
			//扫描已选复选框的个数
			//参数:表单名,元素名
			function SelectedCounts(form,ItemID)
			{
			  var SelectedCounts=0;  //初始选中个数为0
			  var f=form;
			  for (i=0;i<f.elements.length;i++)
			    if (f.elements[i].name==ItemID && f.elements[i].checked==true)
				    {
			          SelectedCounts++;
					 }
			  return SelectedCounts;
			}
		</script>
  </head>
  
  <body bgColor="white" background="Image/right.gif"  > 
	    <logic:messagesPresent message="true">
	        <html:messages id="message" message="true">
	        <font color="red"><bean:write name="message" /></font>
	        </html:messages>
		</logic:messagesPresent>
		<html:form action="/addcheck_log?method=getlist" onsubmit="return check()">
       <table width="450">
             <tr>
                 <th>开始时间： </th>
                 <th>
                 <html:text property="ser_addchecklog.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_addchecklog.stime')})"/>
                 </th>
                 <th>&nbsp;</th>
                 <th align="left">
                          员工：
                      <html:text property="ser_addchecklog.stafferid" size="10" maxlength="6"  styleId="stafferid" onkeyup="cardNames();"   />
	                  <div style="position:absolute;" id="cardpopup">
				        <table id="cardname_table" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="cardname_table_body"></tbody>
				        </table>  
                      </div>
                 </th> 
              </tr>
              <tr>
                 <th>结束时间： </th>
                 <th>
                 <html:text property="ser_addchecklog.etime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_addchecklog.etime')})"/>
                 </th>
                 <th>&nbsp;</th>
                 <th> 
	                  <html:submit  value="查 询" />&nbsp; 
	                    <!--  <input type="button" value="增 加"  onclick="add()">&nbsp; -->
	                  <input type="button" value="删 除"  onclick="DeleteSomeLine(Search_addchecklog_Form)" /><%--&nbsp; 
	                  <logic:equal  name="listCount" value="0">
	                       <input type="button"  value="打印预览"  disabled>
		             </logic:equal>
		             <logic:notEqual name="listCount" value="0">  
		                   <input type="button"  value="打印预览"  onclick="javascript:window.location.href='localizer.do?method=viewPrint'">
		             </logic:notEqual>
                --%></th> 
       </table><br/>
           <table width="900"  cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" >
             <tr>
	           <td width="120" align="left"  bgcolor="#B0C4DE">员工编号&nbsp;<input type="checkbox" name="checkall" value="checkbox" onClick="CheckAll(Search_addchecklog_Form);" >(全选)</td>
	           <td width="70"  align="left"  bgcolor="#B0C4DE">姓名</td>
	           <td width="70" align="left"  bgcolor="#B0C4DE">班组</td>
	           <td width="70"  align="left"	 bgcolor="#B0C4DE">职务类型</td>
	           <td width="160"  align="left"  bgcolor="#B0C4DE">开始时间</td> 
	           <td width="160"  align="left"  bgcolor="#B0C4DE">结束时间</td> 
	           <td width="160" align="left"  bgcolor="#B0C4DE">工作时间</td>
	           
           </tr>
		    
          <logic:present name="list">
             <logic:iterate name="list" id="list">
                <tr>
                  <td align="left" bgcolor="#E6E6FA">
                        <input type="checkbox" name="checkId" id="idd" value="<bean:write name='list' property='stafferid'/>+$+<bean:write name="list" property="downtime" />" />
                             
                             <bean:write name="list" property="stafferid" /> 
                  </td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="name" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="gro" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="occupation" /></td>  
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="downtime" /></td>  
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="uptime" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="worktime" /></td>
                </tr>
             </logic:iterate>
          </logic:present>
               <%--<logic:present name="pagination" >
	               <tr>
			          <td colspan="11" align="center" bgcolor="#E6E6FA">
			               <page:pagination path="localizer.do?method=getlist" parameter="page" formName="Search_locator_Form" />
			          </td>  
	              </tr> 
               </logic:present> 
      --%></TABLE>
      </html:form>
  </body>
</html:html>
