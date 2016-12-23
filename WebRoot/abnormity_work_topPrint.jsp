<%@ page contentType="application/pdf;charset=GB2312"%>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.data.*" %>
<%@ page import="com.kj133.entity.Search_abnormity_work_top" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
    <%
      //报表编译之后生成的.jasper 文件的存放位置
      response.reset();
      File reportFile = new File(application.getRealPath("reports/abnormity_work_top.jasper"));
      //传递报表中用到的参数值
      Search_abnormity_work_top jf = (Search_abnormity_work_top)request.getSession().getAttribute("abnormity_work_top");
      
      Map parameters = new HashMap();     
      parameters.put("sstime",jf.getStime());
      parameters.put("eetime",jf.getEtime());  
        
      List dyList = (ArrayList)request.getSession().getAttribute("abnormity_work_topPrint");
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

    
    <title>打印报表</title>
    <script language="javascript">
 alert('打印成功!');
 window.close();
</script>


  </head>
  
  <body bgcolor="#ffffff">
    
  </body>
</html>
