<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="应急帮助" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>fleeHelp.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
	 <script language="JavaScript">
			function showMe(ccname) {
			      if (document.all.item(ccname).style.display == "none"){
			         document.all.item(ccname).style.display = "";
			         }
			      else {
			         document.all.item(ccname).style.display = "none";
			         }
			}
	      function check()
		       {
		         var id=document.all['mapadd.id'].value;
		         var info=document.all['mapadd.info'].value;
		         if( id== ""){
		           alert('图片号码不能为空');
		           document.all['mapadd.id'].focus();
		           return false;
		         }
		         if( info ==""){
		            alert('安全公告不能为空');
		            document.all['mapadd.info'].focus();
		            return false;
		         }
		         return true;
		  }
       
       
    </script>

 
      
  </head>
  
  <body bgColor="white" background="Image/right.gif">
         
       <table border="0">
          <tr>
             <td>
                <div  style="overflow:auto;width:550;height:450;">  
                  <table>
                     <tr>
                        <td>
                          <font color="red">显示说明：</font><br>
                           <html:img  src="fleeHelp.do?method=show"  paramId="id"   width="500" height="400" />
                        </td>
                     </tr>
                  </table>
                </div>
             </td>
             <td>
                <div  style="overflow:auto;width:300;height:400;"> 
                 <table >
                    <tr>
                       <td><font color="red">安全公告：</font></td>
                    </tr>
                    <tr>
                        <td>
                            <textarea rows="8" cols="30" disabled  >
                              <bean:write name="heloBean" property="mapinfo"/>
                            </textarea>
                            
                        </td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                       <td>
                           <html:form action="fleeHelp?method=update"    method="post"  enctype="multipart/form-data" onsubmit="return check()"  >
								      <table>
								          <tr>
								            <td>
										        <input type="button" value="修 改" onClick="javascript:showMe('01')"/>
										   </td>
										  </tr>
										  <tbody id=01 style="display:none">
											  <tr>
										          <td width="80" >图片号码：</TD>
										          <td><html:text property="mapadd.id"  size="18"   maxlength="3" onkeyup="this.value=this.value.replace(/\D/g,'')" /><font color="red">&nbsp; *</font></td>
								             </tr>
											  
											  <tr>
											     <td width="80" >选择图片：</td>
											     <td>
											        <html:file  property="mapadd.file" styleId="file" disabled="true" size="18" />
									                <input type="checkbox" name="name1" onclick="this.checked?file.disabled=false:file.disabled=true" />选中则按钮可用 
											     </td>
											  </tr>
											  <tr>
											     <td width="80" >安全公告：</td>
											     <td>
											        <html:text  property="mapadd.info" size="18"  maxlength="127"   /><font color="red">&nbsp; *</font>
											     </td>
											  </tr>
											  <tr>
											     <td><html:submit>确 认</html:submit></td>
											     <td><html:reset>取 消</html:reset></td>
											  </tr>
										   </tbody>
										 
								      </table>
                           </html:form>
                       </td>
                    </tr>
                 </table>
                 </div>
             </td>
          </tr>
       </table>
  </body>
</html:html>
