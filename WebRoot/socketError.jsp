<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page info="采集机连接错误"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    <title>错误消息</title>
<jsp:include flush="true" page="/checkUser.jsp"></jsp:include>
    <link href="Css/TestDate.css" rel="stylesheet"><%--背景--%>
  </head>
  
 <STYLE id=L_10060_1>A {
	FONT-WEIGHT: bold; FONT-SIZE: 9pt; COLOR: #005a80; FONT-FAMILY: 宋体
	}
	A:hover {
		FONT-WEIGHT: bold; FONT-SIZE: 9pt; COLOR: #0d3372; FONT-FAMILY: 宋体
	}
	TD {
		FONT-SIZE: 9pt; FONT-FAMILY: 宋体
	}
	TD.titleBorder {
		BORDER-RIGHT: #955319 1px solid; BORDER-TOP: #955319 1px solid; PADDING-LEFT: 8px; FONT-WEIGHT: bold; FONT-SIZE: 12pt; VERTICAL-ALIGN: middle; BORDER-LEFT: #955319 0px solid; COLOR: #955319; BORDER-BOTTOM: #955319 1px solid; FONT-FAMILY: 宋体; HEIGHT: 35px; BACKGROUND-COLOR: #d2b87a; TEXT-ALIGN: left
	}
	TD.titleBorder_x {
		BORDER-RIGHT: #955319 0px solid; BORDER-TOP: #955319 1px solid; PADDING-LEFT: 8px; FONT-WEIGHT: bold; FONT-SIZE: 12pt; VERTICAL-ALIGN: middle; BORDER-LEFT: #955319 1px solid; COLOR: #978c79; BORDER-BOTTOM: #955319 1px solid; FONT-FAMILY: tahoma; HEIGHT: 35px; BACKGROUND-COLOR: #d2b87a; TEXT-ALIGN: left
	}
	.TitleDescription {
		FONT-WEIGHT: bold; FONT-SIZE: 12pt; COLOR: black; FONT-FAMILY: 宋体
	}
	SPAN.explain {
		FONT-WEIGHT: normal; FONT-SIZE: 9pt; COLOR: #934225
	}
	SPAN.TryThings {
		FONT-WEIGHT: normal; FONT-SIZE: 9pt; COLOR: #934225
	}
	.TryList {
		MARGIN-TOP: 5px; FONT-WEIGHT: normal; FONT-SIZE: 9pt; COLOR: black; FONT-FAMILY: 宋体
	}
	.X {
		BORDER-RIGHT: #955319 1px solid; BORDER-TOP: #955319 1px solid; FONT-WEIGHT: normal; FONT-SIZE: 12pt; BORDER-LEFT: #955319 1px solid; COLOR: #7b3807; BORDER-BOTTOM: #955319 1px solid; FONT-FAMILY: 宋体; BACKGROUND-COLOR: #d1c2b4
	}
	.adminList {
		MARGIN-TOP: 2px
	}
	</STYLE>
 
<BODY bgColor=#f3f3ed>
<TABLE cellSpacing=0 cellPadding=0 width="100%">
  <TBODY>
  <TR>
    <TD class=titleborder_x width=30>
      <TABLE height=25 cellSpacing=2 cellPadding=0 width=25 bgColor=black>
        <TBODY>
        <TR>
          <TD class=x vAlign=center align=middle>X</TD>
        </TR>
        </TBODY>
      </TABLE>
    </TD>
    <TD class=titleBorder id=L_10060_2>网络访问消息:<SPAN class=TitleDescription>: 不能显示此页</SPAN> </TD>
  </TR>
  </TBODY>
</TABLE>

<TABLE id=spacer>
   <TR>
     <TD>&nbsp;</TD>
   <TR>
  <TR>
    <TD noWrap width=25></TD>
    <TD width=800 ><SPAN class=explain><B>解释:</B></SPAN> 在页面检索完成之前，请求超时。<BR><BR>
    <B><SPAN class=tryThings><B>尝试下列:</B></SPAN></B> 
      <UL class=TryList>
        <LI id=L_10060_6><B>网络连接是否正常:</B><br>
          &nbsp;&nbsp;&nbsp;所连接采集服务器的Ip地址是：<%= request.getAttribute("ipAddress")%>,请检查网络连接是否正常。<br>
          &nbsp;&nbsp;&nbsp;
        <LI id=L_10060_7><B>采集服务器是否开启:</B><br>
          &nbsp;&nbsp;&nbsp;请检查您的采集服务器是开启状态。
      </UL>
      如果您仍然看不到请求的页面，请与技术支持联系。<BR><BR>
    </TD>
   </TR>
</TABLE>

<TABLE id=spacer><TBODY><TR><TD height=15></TD></TR></TBODY></TABLE>
 

</BODY>
</html:html>
