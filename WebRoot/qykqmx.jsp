<%@ page contentType="application/pdf;charset=GB2312"%>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.engine.data.*" %>
<%@ page import="com.kj133.entity.Search_AreaCheckList" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
    <%
      //报表编译之后生成的.jasper 文件的存放位置
      response.reset();
      File reportFile = new File(application.getRealPath("reports/qykqmx.jasper"));
      //传递报表中用到的参数值
      Search_AreaCheckList jf = (Search_AreaCheckList)request.getSession().getAttribute("areachecklist");
      Map parameters = new HashMap();     
      parameters.put("sstime",jf.getStime());
      parameters.put("eetime",jf.getEtime());   
      List dyList = (ArrayList)request.getSession().getAttribute("queryList");
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
