<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="根据标题得到详细信息"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html lang="true">
  <head>
    <html:base />
    
    <title>transitTrcticInfo.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script type="text/javascript" src="js/dateJs/WdatePicker.js" defer="defer"> </script>
<script type="text/javascript">
        function check(){
            var cap=document.all('ser_transitTrctic.caption').value;
            if(cap==""){
               alert('标题不能为空！');
               document.all('ser_transitTrctic.caption').focus();
               return false;
            }
            var  cxLength=document.getElementsByName("ser_transitTrctic.cardreaderx"); 
            var  check1=new Array();
            for(var i =0;i<cxLength.length;i++){
                 if (cxLength[i].checked){
                       check1[check1.length]=cxLength[i].value;
                    }
               }
            if(check1.length <1) {
              alert("请至少选择一个分站！");
              return   false; 
            }
            
//            var  sxLength=document.getElementsByName("ser_transitTrctic.stafferx");
            var  sxLength=document.getElementsByName("ser_transitTrctic.stafferx");
            var  check2=new Array();
            for(var k =0;k<sxLength.length;k++){
                 if (sxLength[k].checked){
                       check2[check2.length]=sxLength[k].value;
                    }
               }
            if(check2.length <1) {
              alert("请至少选择一个卡号！");
              return   false; 
            } 
            return true;
        }
          function CheckAll(form)
			{
				for (var i=0;i<form.elements.length;i++){
					var e = form.elements[i];
					if (e.name == 'ser_transitTrctic.stafferx')
					e.checked = form.checkall.checked;
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
  
      详细信息：
      <table  width="1060" cellspacing="1" cellpadding="1"  bgcolor="#6CA6CD" border="0">
          <tr>
             <td width="60" align="left" bgcolor="#B0C4DE" >卡号</td>
             <td width="60" align="left" bgcolor="#B0C4DE" >分站号</td>
             <td width="150" align="left" bgcolor="#B0C4DE" >分站名称</td>
             <%--<td width="80" align="left" bgcolor="#B0C4DE" >定位器号</td>
             <td width="150" align="left" bgcolor="#B0C4DE" >定位器名称</td>
             --%><td width="100" align="left" bgcolor="#B0C4DE" >允许或禁止</td>
             <td width="100" align="left" bgcolor="#B0C4DE" >时间限定</td>
             <td width="100" align="left" bgcolor="#B0C4DE" >起始日期</td>
             <td width="80" align="left" bgcolor="#B0C4DE" >起始时间</td> 
             <td width="100" align="left" bgcolor="#B0C4DE" >终止日期</td>
             <td width="80" align="left" bgcolor="#B0C4DE" >终止时间</td> 
          </tr>
          <logic:present name="infoList" scope="request">
                 <logic:iterate name="infoList" id="info">
                     <tr>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="cardid" /></td>
                       <logic:equal name="info" property="cardreaderid"  value="0">
                           <td align="left" bgcolor="#E6E6FA"> </td>
                           <td align="left" bgcolor="#E6E6FA"> </td>
                       </logic:equal>
                       <logic:notEqual name="info" property="cardreaderid" value="0">
                          <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="cardreaderid" /></td>
                          <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="shortname" /></td>
                       </logic:notEqual>
                       <%--<logic:equal name="info" property="locatorid" value="0">
                            <td align="left" bgcolor="#E6E6FA"> </td>
                            <td align="left" bgcolor="#E6E6FA"> </td>
                       </logic:equal>
                       <logic:notEqual name="info" property="locatorid" value="0">
                            <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="locatorid" /></td>
                            <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="lname" /></td>
                       </logic:notEqual>
                       --%><td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="alloworestop" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="limit" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="sriqi" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="stime" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="eriqi" /></td>
                       <td align="left" bgcolor="#E6E6FA"><bean:write name="info" property="etime" /></td>
                     </tr>
                 </logic:iterate>
           </logic:present>
      </table>
      <br>
      <html:form action="transitTrctic?method=update"  onsubmit="return check()" target="leftFrame" >
         <table >
         
           <tr>
               <td>
                 <div  style="overflow:auto;width:150;height:430;">   
                   <table border="1">
                       <tr>
                           <td>分站号</td>
                           <td>分站名称</td>
                       </tr>
                       <logic:present name="Clist" scope="request">
                          <logic:iterate name="Clist" id="cl">
                              <tr>
                                  <td>
                                      <html:multibox property="ser_transitTrctic.cardreaderx" ><bean:write name='cl' property='cardreaderid'/></html:multibox>
                                      <bean:write name="cl" property="cardreaderid"/>
                                  </td>
                                  <td><bean:write name="cl" property="shortname"/></td>
                              </tr>
                          </logic:iterate>
                       </logic:present>
                   </table>
                   </div>
               </td>
               <%--
               <td>
                <div  style="overflow:auto;width:250;height:430;">  
                   <table border="1">
                       <tr>
                           <td>定位器号</td>
                           <td>定位器名称</td>
                       </tr>
                       <logic:present name="Llist" scope="request">
                          <logic:iterate name="Llist" id="ll">
                              <tr>
                                  <td>
                                      <html:multibox property="ser_transitTrctic.locatorx" ><bean:write name='ll' property='locatorid'/></html:multibox>
                                      <bean:write name="ll" property="locatorid"/>
                                  </td>
                                  <td><bean:write name="ll" property="lshortname"/></td>
                              </tr>
                          </logic:iterate>
                       </logic:present>
                   </table>
                 </div>
               </td>
               --%>
               <td>
                  <div  style="overflow:auto;width:190;height:480;">  
                   <table border="1">
                       <tr>
                           <td>卡号<input type="checkbox" name="checkall" value="checkbox" onClick="CheckAll(Search_TransitTrctic_Form);" >(全选)</td>
                           <td>姓名</td>
                           <td>工种</td>
                       </tr>
                       <logic:present name="Slist" scope="request">
                          <logic:iterate name="Slist" id="sl">
                              <tr>
                                  <td>
                                  
                                      <html:multibox property="ser_transitTrctic.stafferx"  ><bean:write name='sl' property='cardid'/></html:multibox>
                                      
                                      <%--<input type="checkbox" name="ser_transitTrctic.stafferx"  value="<bean:write name="sl" property="cardid"/>" />
                                      --%><bean:write name="sl" property="cardid"/>
                                  </td>
                                  <td><bean:write name="sl" property="username"/></td>
                                  <td><bean:write name="sl" property="worktype"/></td>
                              </tr>
                          </logic:iterate>
                       </logic:present>
                   </table>
                 </div>
                </td>
                <%-- 当staffer中的cardid有重复的时候，显示就有问题。 --%>
               <td>
                     <div  style="overflow:auto;width:185;height:430;">  
                       <table border="0">
                           <tr>
                               <td>标题：</td>
                           </tr>
                           <tr>
                                <td> <html:textarea property="ser_transitTrctic.caption"  rows="8"  cols="15"></html:textarea></td>
                           </tr>
                           <tr>
                               <td>
                               <html:radio property="ser_transitTrctic.pass" value="1" />禁止通行
                           </tr>
                           <tr>
                              <td>&nbsp;</td>
                           </tr>
                           <tr>
                              <td>
                                       时间设定：<br/>
                                  <html:radio property="ser_transitTrctic.passmode" value="0" />不限<br/>
                                  <html:radio property="ser_transitTrctic.passmode" value="1" />仅指定时间<br/>
                                  <html:radio property="ser_transitTrctic.passmode" value="2" />指定时间和日期
                              </td>
                           </tr>
                           <tr>
                              <td>&nbsp;</td>
                           </tr>
                           <tr>
                              <td>起始时间：
                              <html:text property="ser_transitTrctic.stime" size="18" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_transitTrctic.stime')})"/>
                              </td>
                           </tr>
                           <tr>
                              <td>截止时间：
                              <html:text property="ser_transitTrctic.etime" size="18" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',el:$dp.$('ser_transitTrctic.etime')})"/>
                              </td>
                           </tr>
                           <tr>
                             <td>
                                 <html:submit>修 改</html:submit>&nbsp;&nbsp;&nbsp;
                                 <html:reset>取 消</html:reset>
                             </td>
                           </tr>
                       </table>
                     </div>
               </td>
           </tr>
         </table>
        </html:form>
  </body>
</html:html>
