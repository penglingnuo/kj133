<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="员工考勤设置（设置按钮）"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>employee_menology_mountAdd.jsp</title>
    <jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript" src="js/StafferORName.js"></script>
     
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
  
  <body bgColor="white" background="Image/right.gif" onload="rad(0)">
      <html:form action="employee_menology_mount?method=add" focus="mount.sid" onsubmit="return check()">
       <table border="0">
             <tr>
                 <td>工种：</td>
                 <td>
                      <html:select property="mount.worktype" styleId="g" style="width:125px">
                          <html:option value="" ></html:option>
                          <html:options collection="workType_list" property="wordvalue" labelProperty="wordvalue"/>
                      </html:select>
                 </td>
                 <td><span id="messageG"></span></td>
                 
             
                 <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;员工：</td>
                 <td>
                     <html:text property="mount.sid"   size="16" styleId="names2" onkeyup="findNames2();" />
                     <div style="position:absolute;" id="popup2">
				        <table id="name_table2" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0"/>            
				            <tbody id="name_table_body2"></tbody>
				        </table>  
                      </div>
                 </td>
                 <td><span id="messageY"></span></td>
             </tr>
             
              <tr>
                 <td>最小工作时间：</td>
                 <td>
                     <html:text property="mount.mintime" styleId="minitime" size="16" maxlength="1" onkeyup="this.value=this.value.replace(/\D/g,'')"></html:text>
                 </td>
                 <td><font color="red">不可为空</font></td>
             </tr>
             
             <tr>
                 <td colspan="2" align="center">
                     <input type="radio" id="r" name="cho" value="0"  checked  onClick="rad(this.value)">个人
                     <input type="radio" id="g" name="cho" value="1"  onClick="rad(this.value)">工种
                 </td>
             </tr>
             <tr>
                <td colspan="2" align="center">
                    <html:submit>确 定</html:submit>&nbsp;&nbsp;&nbsp; 
                    <input type="button" value="返 回" onClick="go()">
                </td>
             </tr>
         </table>
       </html:form> 
      <script type="text/javascript">
             function rad(value){
                var v=value;
                if(v==0){ //个人
                   document.getElementById("names2").disabled=false;
                   document.getElementById("g").value="";
                   document.getElementById("messagey").innerHTML ="";
                   document.getElementById("messageG").innerHTML ="  禁 用 ";
                   document.getElementById("messageG").style.color="#ff0000";   
                   document.getElementById("g").disabled=true; 
                   
                }else{ //工种
                   document.getElementById("g").disabled=false; 
                   document.getElementById("names2").value="";
                   document.getElementById("messageG").innerHTML ="";
                   document.getElementById("messagey").innerHTML ="  禁 用 ";
                   document.getElementById("messagey").style.color="#ff0000";   
                   document.getElementById("names2").disabled=true;
                   
                }
             }
             
             function go(){
               window.history.go(-1);
             }
             
            function check(){
                var mini=document.getElementById("minitime").value;
                if(mini==""){
                    alert("最小工作时间不能为空");
                    document.getElementById("minitime").focus();
                    return false;
                }if(mini>7){
                    alert("最小工作时间不能大于7");
                    document.getElementById("minitime").value="";
                    document.getElementById("minitime").focus();
                    return false;
                }
                return true;
             }
    
      </script>        
  </body>
</html:html>
