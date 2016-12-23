<%@ page contentType="application/pdf;charset=GB2312"%>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.engine.data.*" %>
<%@ page import="com.kj133.entity.Search_AreaStick" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
    <%
      //报表编译之后生成的.jasper 文件的存放位置
      response.reset();
      File reportFile = new File(application.getRealPath("reports/areaStick.jasper"));
      //传递报表中用到的参数值
      Search_AreaStick jf = (Search_AreaStick)request.getSession().getAttribute("stick");
      
      Map parameters = new HashMap();     
      parameters.put("ktime",jf.getStime());
      parameters.put("jtime",jf.getEtime());
      
      List dyList = (ArrayList)request.getSession().getAttribute("AreaStick_ListPrint");
      JRDataSource jrdsMain = new JRBeanCollectionDataSource(dyList);
      byte[] bytes=JasperRunManager.runReportToPdf(reportFile.getPath(),parameters,jrdsMain);
//      JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), parameters, jrdsMain);
//      JasperPrintManager.printReport(jasperPrint, false);
      response.setContentType("application/pdf");
      response.setContentLength(bytes.length);

      ServletOutputStream ouputStream = response.getOutputStream();
      ouputStream.write(bytes, 0, bytes.length);
      ouputStream.flush();
      ouputStream.close();
      out.clear();
      out = pageContext.pushBody();
    %>
<html>
<head>

<title>
  打印报表
</title>

<script language="javascript">
 alert('打印成功!');
 window.close();
</script>
</head>
<body bgcolor="#ffffff">
</body>
</html>




    
