<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="修改权限" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>addPopedom.jsp</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
     <SCRIPT language="JavaScript">
         function check(){
            var  boxe1  =document.getElementsByName("search_popedom.check_pope"); 
	        var  check1  =new  Array();  
	        for  (var i=0;i<boxe1.length;i++)  
	             {  
	                   if(boxe1[i].checked)  
	                       {  
	                          check1[check1.length]=boxe1[i].value;  
	                       }  
	               }  
	       if(check1.length  <1)  
	         {  
	             alert(  "请至少选择一个权限");  
	             return  false;  
             }
             return true;
          }
	    function  chkUserManager()   { //用户管理  
		    var thisform=document.forms[0];   
		    for(var i =0;i<=5;i++){   
		          var e=thisform.elements[i];
		            if(e.type =="checkbox"){   
		               e.checked  =thisform.checkUser.checked; 
		         }
		    }   
	     }
	     function  chkSystem()   { //实时信息  
		    var thisform=document.forms[0];   
		    for(var i =6;i<=8;i++){   
		          var e=thisform.elements[i];
		            if(e.type =="checkbox"){   
		               e.checked  =thisform.checkSys.checked; 
		         }
		    }   
	     }
	     function  chkInformation()   {  //数据查询 
		    var thisform=document.forms[0];  
		    for(var i =9;i<=13;i++){   
		          var e=thisform.elements[i];
		            if(e.type =="checkbox"){   
		               e.checked  =thisform.checkInfo.checked; 
		         }
		    }   
	     }
	     function  chkOrientation()   {  //人员定位
		    var thisform=document.forms[0];  
		    for(var i =14;i<=17;i++){   
		          var e=thisform.elements[i];
		            if(e.type =="checkbox"){   
		               e.checked  =thisform.checkOrie.checked; 
		         }
		    }   
	     }
	      function chkCall_police() { //报警查询
		    var thisform=document.forms[0];  
		    for(var i =18;i<=24;i++){   
		          var e=thisform.elements[i];
		            if(e.type =="checkbox"){   
		               e.checked =thisform.checkCall.checked; 
		         }
		    }   
	     }
	    function chkCheck_on() {  //考勤报表
		    var thisform=document.forms[0];  
		    for(var i =25;i<=29;i++){   
		          var e=thisform.elements[i];
		            if(e.type =="checkbox"){   
		               e.checked =thisform.checkOn.checked; 
		         }
		    }   
	     }
    </SCRIPT>
  </head>
  
  <body bgColor="white" background="Image/right.gif" >
  <html:form action="popedom.do?method=updateCheck"  onsubmit="return check()">
   <table border="0">
       <tr>
          <td align="center">
              <font size="4">修 改 权 限 (<font color="red"><%= request.getAttribute("uid").toString()  %></font>)</font>
              <input type="hidden" name="uid" value=<%= request.getAttribute("uid").toString()  %>>
          </td>
       </tr>
       <tr>
          <td><font color="red">用户管理</font>&nbsp;&nbsp;<input type="checkbox" name="checkUser" value="checkbox" onClick="chkUserManager()" >(全选)</td>
       </tr>
       <tr>
          <td>&nbsp;&nbsp;&nbsp;
              <html:multibox property="search_popedom.check_pope" value="1" />账户管理&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  
              <html:multibox property="search_popedom.check_pope" value="2" />修改密码&nbsp;&nbsp; &nbsp; &nbsp;   
              <html:multibox property="search_popedom.check_pope" value="3" />权限管理&nbsp; &nbsp; &nbsp; 
              <html:multibox property="search_popedom.check_pope" value="4" />报表权限&nbsp;&nbsp; &nbsp; &nbsp;
          </td>
       </tr>
       <tr><td><hr></td></tr>
       <tr>
          <td><font color="red">实时信息</font>&nbsp;&nbsp;<input type="checkbox" name="checkSys" value="checkbox" onClick="chkSystem()" >(全选)</td>
       </tr>
       <tr>
          <td><br>&nbsp;&nbsp;&nbsp;
              <html:multibox property="search_popedom.check_pope" value="5" />实时信息查询&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
              <html:multibox property="search_popedom.check_pope" value="6" />实时干部查询&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
          </td>
       </tr>
       <tr><td><hr></td></tr>
       <tr> 
          <td><font color="red">数据查询</font>&nbsp;&nbsp;<input type="checkbox" name="checkInfo" value="checkbox" onClick="chkInformation()" >(全选)</td>
       </tr> 
       <tr>
          <td>&nbsp;&nbsp;&nbsp;
              <html:multibox property="search_popedom.check_pope" value="7" />人员信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
              <html:multibox property="search_popedom.check_pope" value="8" />分站信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
              <html:multibox property="search_popedom.check_pope" value="9" />部门考勤月报&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <html:multibox property="search_popedom.check_pope" value="10" />历史信息查询&nbsp;&nbsp;
          </td>
       </tr> 
        <tr><td><hr></td></tr>
       <tr> 
          <td><font color="red">人员定位</font>&nbsp;&nbsp;<input type="checkbox" name="checkOrie" value="checkbox" onClick="chkOrientation()" >(全选)</td>
       </tr> 
       <tr>
          <td>&nbsp;&nbsp;&nbsp; 
          	 <html:multibox property="search_popedom.check_pope" value="11" />实时监控图&nbsp;&nbsp;&nbsp;&nbsp;
             <html:multibox property="search_popedom.check_pope" value="12" />人员搜索&nbsp;&nbsp;&nbsp;&nbsp;
             <html:multibox property="search_popedom.check_pope" value="13" />人员轨迹&nbsp;&nbsp;&nbsp;&nbsp;
           </td>
       </tr>
        <tr><td><hr></td></tr>
        <tr> 
          <td><font color="red">报警信息</font>&nbsp;&nbsp;<input type="checkbox" name="checkCall" value="checkbox" onClick="chkCall_police()" >(全选)</td>
       </tr> 
       <tr>
          <td>&nbsp;&nbsp;&nbsp; 
          	 <html:multibox property="search_popedom.check_pope" value="14" />卡呼救报警&nbsp;&nbsp;&nbsp;&nbsp;
             <html:multibox property="search_popedom.check_pope" value="15" />卡电池报警&nbsp;&nbsp;&nbsp;&nbsp;
             <html:multibox property="search_popedom.check_pope" value="16" />超时报警&nbsp;&nbsp;&nbsp;&nbsp;
             <html:multibox property="search_popedom.check_pope" value="17" />分站报警&nbsp;&nbsp;&nbsp;&nbsp;
             <html:multibox property="search_popedom.check_pope" value="18" />定位器电池报警&nbsp;&nbsp;&nbsp;&nbsp;
             <html:multibox property="search_popedom.check_pope" value="19" />定位器无信号查询&nbsp;&nbsp;&nbsp;&nbsp;
           </td>
       </tr>
        <tr><td><hr></td></tr>
        <tr> 
          <td><font color="red">考勤管理</font>&nbsp;&nbsp;<input type="checkbox" name="checkOn" value="checkbox" onClick="chkCheck_on()" >(全选)</td>
       </tr> 
       <tr>
          <td>&nbsp;&nbsp;&nbsp; 
          	 <html:multibox property="search_popedom.check_pope" value="20" />考勤记录查询&nbsp;&nbsp;&nbsp;&nbsp;
             <html:multibox property="search_popedom.check_pope" value="21" />新增考勤记录&nbsp;&nbsp;&nbsp;&nbsp;
             <html:multibox property="search_popedom.check_pope" value="22" />修改考勤记录&nbsp;&nbsp;&nbsp;&nbsp;
             <html:multibox property="search_popedom.check_pope" value="23" />删除考勤记录&nbsp;&nbsp;&nbsp;&nbsp;
           </td>
       </tr>
        <tr><td><hr></td></tr>
       
        <tr>
          <td align="center">
              <html:submit>确 定</html:submit>&nbsp;&nbsp;&nbsp;&nbsp;
              <html:reset>取 消</html:reset>&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" value="返 回" onClick="history.back()">
          </td>
        </tr>          
    </table>
    </html:form>
    <script type="text/javascript">
        document.forms[0].checkUser.checked=true;
        document.forms[0].checkSys.checked=true;
        document.forms[0].checkInfo.checked=true;
        document.forms[0].checkOrie.checked=true;
        document.forms[0].checkCall.checked=true;
        document.forms[0].checkOn.checked=true;
    </script>
  </body>
</html:html>
