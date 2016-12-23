
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="删除考勤记录" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/speed-pagination.tld" prefix="page" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>Depdetail.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <script language="javascript" src="js/page.js"></script>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/StafferORName1.js"></script>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"></script>
    <link rel="stylesheet" href="js/jqueryui/cupertino/jquery-ui.css"
	type="text/css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery-ui-min.js"></script>
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
         var time=date2str(new Date());
         if(stime==null || stime==""){
            alert('请选择时间');
            return false;
         }if( stime >= time){
            alert('考勤结束时间不应该大于当前时间');
            return false;
         }
         return true;
       
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
		<script type="text/javascript">
			$(function(){
				//用户名模糊查询
		   	 	$("#name").autocomplete({
		    	source: function(request, response) {
		                $.ajax({
		                    url: "common.do?method=staffer",
		                    dataType: "json",
		                    data: {
		                    	c:$("#name").val()
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
  </head>
  
  <body bgColor="white" background="Image/right.gif" >
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     
      <html:form action="/addcheck_log?method=getlistworkattdel" onsubmit="return check()">
          <table width="100%">
               <tr>
                <th align="left">开始时间： </th>
                 <th>
                 <html:text property="ser_addchecklog.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_addchecklog.stime')})"/>
                 </th>
                 <th align="left">结束时间： </th>
                 <th>
                 <html:text property="ser_addchecklog.etime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd',el:$dp.$('ser_addchecklog.etime')})"/>
                 </th>
                 <th align="left"> 员工：
                 <html:text property="ser_addchecklog.stafferid" styleId="name" style="width: 125px;">
				 </html:text>
                 </th> 
                 <th align="left">部 门:</th>
                 <th align="left">
                 <div style="position:relative;">
                 <span style="margin-left:82px;width:18px;overflow:hidden;">
                 <select  style="width:118px;margin-left:-100px"  onchange="this.parentNode.nextSibling.value=this.value">
                 <logic:present name="gro_list">
              		<option value=""></option>
                 	<logic:iterate id="aa" name="gro_list">
                 		<option value='<bean:write name="aa" property="wordvalue"/>'><bean:write name="aa" property="wordvalue"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
                 <html:text property="ser_addchecklog.gro" style="width:82px;position:absolute;left:0px;"></html:text>
				</div>
                 </th>
                 <th align="left">班次:</th>
                 <th align="left">
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
				</div>
                 </th> 
                 
                 <th align="right"> 
	                  <html:submit  value="查 询" />&nbsp; 
	                   <input type="button" value="删 除"  onclick="DeleteSomeLine(Search_addchecklog_Form)" />
	                 </th> 
                 <!--  <th align="right">班制:</th>
                 <th align="left">
                 <div style="position:relative;">
                 <span style="margin-left:82px;width:18px;overflow:hidden;">
                 <select  style="width:118px;margin-left:-100px"  onchange="this.parentNode.nextSibling.value=this.value">
                 <logic:present name="banz_list">
              		<option value=""></option>
                 	<logic:iterate id="bb" name="banz_list">
                 		<option value='<bean:write name="bb" property="bantypeid"/>'><bean:write name="bb" property="bantypename"/></option>
                 	</logic:iterate>
                 </logic:present>
                 </select>
                 </span>
                 <html:text property="ser_addchecklog.bantypename" style="width:82px;position:absolute;left:0px;"></html:text>
				</div>
                 </th>-->
              </tr>
              <tr>
                 <!-- <th align="left">结束时间： </th>
                 <th>
                 <html:text property="ser_addchecklog.etime" size="18" onfocus="WdatePicker({skin:'whyGreen',isShowClear:false,dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_addchecklog.etime')})"/>
                 </th>-->
                 </tr>
       		</table><br/>
          
         <table width="100%"  cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0" >
             <tr>
	           <td width="8%" align="left"  bgcolor="#B0C4DE">编号&nbsp;<br/><input type="checkbox" name="checkall" value="checkbox" onClick="CheckAll(Search_addchecklog_Form);" >(全选)</td>
	           <td width="3%"  align="left"  bgcolor="#B0C4DE">卡号</td>
	           <td width="5%"  align="left"  bgcolor="#B0C4DE">姓名</td>
	           <td width="8%" align="left"  bgcolor="#B0C4DE">部门</td>
	           <td width="9%" align="left"  bgcolor="#B0C4DE">工种</td>
	           <td width="8%"  align="left"	 bgcolor="#B0C4DE">职务</td>
	           <td width="12%"  align="left"  bgcolor="#B0C4DE">开始时间</td> 
	           <td width="12%"  align="left"  bgcolor="#B0C4DE">结束时间</td> 
	           <td width="10%" align="left"  bgcolor="#B0C4DE">工作时间</td>
	           <td width="3%" align="left"  bgcolor="#B0C4DE">班次</td>
	           <td width="5%"  align="left"  bgcolor="#B0C4DE">修改人</td> 
	           <td width="12%" align="left"  bgcolor="#B0C4DE">修改时间</td>
	           <td width="5%" align="left"  bgcolor="#B0C4DE">修改原因</td>
           </tr>
		    
          <logic:present name="list">
             <logic:iterate name="list" id="list">
                <tr>
                  <td align="left" bgcolor="#E6E6FA">
                        <input type="checkbox" name="checkId" id="idd" value="<bean:write name='list' property='stafferid'/>+$+<bean:write name="list" property="downtime" />" />
                             <bean:write name="list" property="stafferid" /> 
                  </td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="cardid" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="name" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="department" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="gro" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="occupation" /></td>  
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="downtime" /></td>  
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="uptime" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="worktime" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="banname" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="modifyuser" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="modifytime" /></td>
                  <td align="left" bgcolor="#E6E6FA"><bean:write name="list" property="modifyreason" /></td>
                </tr>
             </logic:iterate>
          </logic:present>
               <logic:present name="pagination" >
                  <tr>
					   <td colspan="13" align="center" bgcolor="#E6E6FA">
					        <page:pagination path="addcheck_log.do?method=getlistworkattdel" parameter="page" formName="Search_addchecklog_Form" />
					  </td>
                   </tr> 
                </logic:present> 
         </table>   
        
         </html:form>     
  </body>
</html:html>