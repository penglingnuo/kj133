
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="修改员工信息" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
 <%
   String id=request.getAttribute("mid").toString();
 
   request.setAttribute("pic_id",id); 
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>updateeEmployee.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script src="js/Calendar2.js"></script><!-- 不带分秒-->
	<link href="Css/calendar-blue.css" rel="stylesheet"> 
    <script language="JavaScript">
      function check()
       { 

        var name=document.all['addEmployee.name'].value;
        var stafferid=document.all['addEmployee.stafferid'].value;
        var depart=document.all['addEmployee.department'].value;
        var gro=document.all['addEmployee.group'].value;   
        var occupation=document.all['addEmployee.occupation'].value;
        var worktype=document.all['addEmployee.worktype'].value;
        if( stafferid == "")
          {
            alert('员工编号不能为空');
            document.all['addEmployee.stafferid'].focus();
            return false;
          }if( name == "")
          {
            alert('姓名不能为空');
            document.all['addEmployee.name'].focus();
            return false;
          }if( depart == "")
           {
             alert('部门不能为空');
             document.all['addEmployee.department'].focus();
             return false;
           }if( gro == "")
           {
             alert('组别不能为空');
             document.all['addEmployee.group'].focus();
             return false;
//           }if( occupation == "")
//            {
//              alert('职务不能为空');
//              document.all['addEmployee.occupation'].focus();
//              return false;
            }if( worktype== "")
            {
              alert('工种不能为空');
              document.all['addEmployee.worktype'].focus();
              return false;
            }
             if(document.all['name1'].checked== true ) 
              {
                 if( document.all['addEmployee.file'].value == "")
                  {
                    alert('请选择相片');
                    document.all['addEmployee.file'].focus();
                    return false;
                  }
              }
           return true;
       }
   
    </script> 
  </head>
  
  <body bgColor="white" background="Image/right.gif">
    <html:form action="/loadEmployee?method=update"  method="post"  enctype="multipart/form-data" focus="addEmployee.name" onsubmit="return check()">
    <html:hidden property="names" value="<%=id %>"/>
     <TABLE>
        <TR>
	          <TD colspan="2">
	            修改员工信息&nbsp;&nbsp;<font color="red">(*为必填内容)</font>
	          <input type="button" value="返 回"  onClick="javascript:history.back()">
	          </TD>
        </TR>
        <TR>
	          <TD>编&nbsp;&nbsp;&nbsp;&nbsp;号：</TD>
	          <%--
	          <TD><html:text property="addEmployee.stafferid"  readonly="true"  style="background-color: #dfdfdf"  /> </TD>
	          --%>
	          <TD><html:text property="addEmployee.stafferid"  onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')"  /> </TD>
        </TR>
        <TR>
	          <TD>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</TD>
	          <TD><html:text property="addEmployee.name" maxlength="5" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /><font color="red">&nbsp;&nbsp;*</font></TD>
        </TR> 
        <TR>
	          <TD>卡&nbsp;&nbsp;&nbsp;&nbsp;号：</TD>
	          <TD><html:text property="addEmployee.cardid" maxlength="5" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /></TD>
        </TR> 
        <TR>
	          <TD>性&nbsp;&nbsp;&nbsp;&nbsp;别：</TD>
	          <TD>
	            <html:radio property="addEmployee.sex"   value="男" />男&nbsp;&nbsp;&nbsp;
	            <html:radio property="addEmployee.sex"  value="女"/>女
	          </TD>
        </TR>
        <TR>
	          <TD>身&nbsp;&nbsp;&nbsp;&nbsp;高：</TD>
	          <TD><html:text property="addEmployee.stature" maxlength="3" onkeyup="this.value=this.value.replace(/\D/g,'')"/>&nbsp;&nbsp;厘米</TD>
        </TR>
        <TR>
	          <TD>体&nbsp;&nbsp;&nbsp;&nbsp;重：</TD>
	          <TD><html:text property="addEmployee.weight" maxlength="3" onkeyup="this.value=this.value.replace(/\D/g,'')"/>&nbsp;&nbsp;公斤</TD>
        </TR>
        <TR>
	          <TD>视&nbsp;&nbsp;&nbsp;&nbsp;力：</TD>
	          <TD><html:text property="addEmployee.eyesight" maxlength="4" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /></TD>
        </TR>
        <TR>
	          <TD>血&nbsp;&nbsp;&nbsp;&nbsp;型：</TD>
	          <TD>
	             <html:select property="addEmployee.bloodtype" style="width:152px">
	               <html:option value=""></html:option>
	               <html:option value="A">A</html:option>
	               <html:option value="B">B</html:option>
	               <html:option value="O">O</html:option>
	               <html:option value="AB">AB</html:option>
	               <html:option value="其他">其他</html:option>
	             </html:select>
	          </TD>
        </TR>
        <TR>
	          <TD>文化程度：</TD>
	          <TD>
	             <html:select property="addEmployee.education" style="width:152px">
	               <html:option value=""></html:option>
	               <html:option value="博士">博士</html:option>
	               <html:option value="硕士">硕士</html:option>
	               <html:option value="学士">学士</html:option>
	               <html:option value="大专">大专</html:option>
	               <html:option value="高中">高中</html:option>
	               <html:option value="中专">中专</html:option>
	               <html:option value="初中">初中</html:option>
	               <html:option value="小学">小学</html:option>
	               <html:option value="文盲">文盲</html:option>
	               <html:option value="其他">其他</html:option>
	             </html:select>
	          </TD>
        </TR>
        <TR>
	          <TD>婚姻状况：</TD>
	          <TD> 
	            <html:select property="addEmployee.marriage" style="width:152px">
	              <html:option value=""></html:option>
	              <html:option value="未婚">未婚</html:option>
	              <html:option value="已婚">已婚</html:option>
	              <html:option value="离异">离异</html:option>
	              <html:option value="丧偶">丧偶</html:option>
	              <html:option value="其他">其他</html:option>
	              <html:option value="保密">保密</html:option>
	            </html:select>
	          </TD>
        </TR>
        <TR>
	           <TD>证件类型：</TD>
	           <TD>
	             <html:select property="addEmployee.certificate" style="width:152px">
	               <html:option value=""></html:option>
	               <html:option value="身份证">身份证</html:option>
	             </html:select>
	           </TD>
        </TR>
        <TR>
	          <TD>证件号码：</TD>
	          <TD><html:text property="addEmployee.certificateid" maxlength="20" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')"/></TD>
        </TR>
        <TR>
	          <TD>出生日期：</TD>
	          <TD>
	            <html:text property="addEmployee.birthday"  size="9" onfocus="this.blur()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	             <A onclick="return showCalendar('addEmployee.birthday', 'y-mm-dd');" href="#"><img src="Image/Button.gif"  id="IMG2"  border="0"/></A>
	          </TD>
        </TR>
        <TR>
	          <TD>电&nbsp;&nbsp;&nbsp;&nbsp;话：</TD>
	          <TD><html:text property="addEmployee.workphone" maxlength="20" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /></TD>
        </TR>
	          <TR>
	          <TD>移动电话：</TD>
	          <TD><html:text property="addEmployee.mobile" maxlength="20" onkeyup="this.value=this.value.replace(/\D/g,'')"/></TD>
        </TR>
        <TR>
	          <TD>邮政编码：</TD>
	          <TD>
	             <html:text property="addEmployee.postzip" maxlength="6" onkeyup="this.value=this.value.replace(/\D/g,'')" />
	               
	          </TD>
        </TR>
        <TR>
	          <TD>家庭住址：</TD>
	          <TD><html:text property="addEmployee.address" maxlength="30" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /></TD>
        </TR>
        <TR>
	          <TD>家庭电话：</TD>
	          <TD><html:text property="addEmployee.homephone" maxlength="20" onkeyup="this.value=this.value.replace(/([\s\u3000]*)|([\s\u3000]*$)/g, '')" /></TD>
        </TR>
       <TR>
	          <TD>职&nbsp;&nbsp;&nbsp;&nbsp;称：</TD>
	          <TD> 
	            <html:select property="addEmployee.technica" style="width:152px" >
				      <html:option value="" ></html:option>
				      <html:options collection="dis_list" property="wordvalue" labelProperty="wordvalue"/>
				</html:select> 
	          </TD>
         </TR>
         <TR>
	          <TD>部&nbsp;&nbsp;&nbsp;&nbsp;门：</TD>
	          <TD> 
	             <html:select property="addEmployee.department" style="width:152px" >
				      <html:option value="" ></html:option>
				      <html:options collection="dept_list" property="wordvalue" labelProperty="wordvalue"/>
				 </html:select><font color="red">&nbsp;&nbsp;*</font>
			 </TD>
			 <TD rowspan="6">
			     <html:img  src="loadEmployee.do?method=show"  paramId="id" paramName="pic_id" width="100" height="120"/>
			 </TD>
         </TR>
         <TR>
	          <TD>组&nbsp;&nbsp;&nbsp;&nbsp;别：</TD>
	          <TD> 
	             <html:select property="addEmployee.group" style="width:152px" >
				      <html:option value="" ></html:option>
				      <html:options collection="gro_list" property="wordvalue" labelProperty="wordvalue"/>
				 </html:select><font color="red">&nbsp;&nbsp;*</font>
		     </TD>
	
         </TR>
         <TR>
	          <TD>职&nbsp;&nbsp;&nbsp;&nbsp;务：</TD>
	          <TD>
	             <html:select property="addEmployee.occupation" style="width:152px" >
				      <html:option value="" ></html:option>
				      <html:options collection="headship_list" property="wordvalue" labelProperty="wordvalue"/>
				 </html:select><%--<font color="red">&nbsp;&nbsp;*</font>
	          --%></TD>
         </TR>
         <TR>
	          <TD>工&nbsp;&nbsp;&nbsp;&nbsp;种：</TD>
	          <TD>
				<html:select property="addEmployee.worktype" style="width:152px" >
				      <html:option value="" ></html:option>
				      <html:options collection="workType_list" property="wordvalue" labelProperty="wordvalue"/>
				</html:select><font color="red">&nbsp;&nbsp;*</font>
	          </TD>
         </TR>
         <TR>
         <TR>
	          <TD>到职日期：</TD>
	          <TD><html:text property="addEmployee.jointime" size="9" onfocus="this.blur()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	             <A onclick="return showCalendar('addEmployee.jointime', 'y-mm-dd');" href="#"><img src="Image/Button.gif"  id="IMG2"  border="0"/></A>
	          </TD>
         </TR>
         <TR>
	          <TD>相&nbsp;&nbsp;&nbsp;&nbsp;片：</TD>
	          <TD> 
	              <html:file  property="addEmployee.file" styleId="file" disabled="true"/>
	          </TD>
	          <TD>
	             <input type="checkbox" name="name1" onclick="this.checked?file.disabled=false:file.disabled=true" />选中则按钮可用  
	          </TD>
          </TR>
         <%--<TR>
	          <TD>卡&nbsp;&nbsp;&nbsp;&nbsp;号：</TD>
	          <TD>
                  <html:select property="addEmployee.cardid" style="width:152px" > 
				      <html:option  value="<%= request.getAttribute("cardid").toString() %>" ><%= request.getAttribute("cardid").toString() %></html:option>
				      <html:options collection="cardid_list" property="cardid" labelProperty="cardid"/>
				      <html:option  value=""></html:option>
			      </html:select>
			  </TD>
         </TR>
         --%><TR>
	          <TD colspan="2" align="left">
	             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<html:submit>修 改</html:submit>&nbsp;&nbsp;&nbsp;
	             <html:reset>取 消</html:reset>
	          </TD>
         </TR>
     </TABLE>
     </html:form>
  </body>
</html:html>
     
