
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="区域设置-增加一级区域设置"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>page_range_topleftadd.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>

    <script language="JavaScript">
		document.oncontextmenu=new Function("event.returnValue=false;");
    </script> 
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
    <script language="javascript">
    
    			function check()
			{
			  var name=document.all['ser_pagerangetopleft.areaname'].value;
			  var type=document.all['ser_pagerangetopleft.areatype'].value;
			  
			  if( name == ""){
			      alert('区域名称不能为空');
			      document.all['ser_pagerangetopleft.areaname'].focus();
			      return false;
			  }if( type == ""){
			      alert('区域类型不能为空');
			      document.all['ser_pagerangetopleft.areatype'].focus();
			      return false;
			  }			  
			  return true;
			}
	</script>		

  </head>
  
  <body bgColor="white" background="Image/right.gif">
     <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <font color="red"><bean:write name="message"/></font>
        </html:messages>
     </logic:messagesPresent>
     <html:form action="/page_range_topleft?method=add" target="topright" onsubmit="return check()" focus="ser_pagerangetopleft.areaname">
     <td ><font size="2" color="red">增加一级区域:</font></td>
     <table >
     <tr>
           <td>&nbsp;&nbsp;区域名称：</td>
           <td>
           <html:text property="ser_pagerangetopleft.areaname" size="12"  />
           </td>
     </tr>
     
     <tr>
           <td>&nbsp;&nbsp;区域类型：</td>
           <td>
           <html:select property="ser_pagerangetopleft.areatype"  style="width:95px">
               <html:option value=""></html:option>
               <html:option value="重点区域">重点区域</html:option>
               <html:option value="采掘区域">采掘区域</html:option>
               <html:option value="限制区域">限制区域</html:option>
               
             </html:select>
            </td>
      </tr>
     
      <tr>
          <td>&nbsp;&nbsp;&nbsp;&nbsp;定员数：</td>
          <td>
          <html:text property="ser_pagerangetopleft.maxsum" size="12"   onkeyup="this.value=this.value.replace(/\D/g,'')"/>&nbsp;&nbsp;&nbsp;
          </td>
      </tr> 
        
      <tr>     
           <td>采掘定员数：</td>
           <td>
           <html:text property="ser_pagerangetopleft.caijuesum"  size="12"  onkeyup="this.value=this.value.replace(/\D/g,'')"/>
           </td>
	  </tr>   
      <tr>     
           <td>超时时间(分)</td>
           <td>
           <html:text property="ser_pagerangetopleft.stayminute" size="12"  onkeyup="this.value=this.value.replace(/\D/g,'')"/>
           </td>
	  </tr>   
      <tr>     
           <td>&nbsp;&nbsp;区域说明：</td>
           <td>
           <html:text property="ser_pagerangetopleft.areainfo" size="12" maxlength="100" />
           </td>
	  </tr> 
	  <TR>
                <TD colspan="4" align="center">
                   <html:submit>增 加</html:submit>&nbsp;&nbsp;
                </TD>
             </TR>  
	 </table>      

      </html:form>
  </body>
</html:html>
