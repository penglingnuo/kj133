<%@ page contentType="application/pdf;charset=GB2312"%>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.engine.data.*" %>
<%@ page import="com.kj133.entity.Search_Department_daily" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
    <%
      //��������֮�����ɵ�.jasper �ļ��Ĵ��λ��
      response.reset();
      File reportFile = new File(application.getRealPath("reports/department_daily.jasper"));
      //���ݱ������õ��Ĳ���ֵ
      Search_Department_daily jf = (Search_Department_daily)request.getSession().getAttribute("department");
      
      Map parameters = new HashMap();     
      parameters.put("sstime",jf.getStime());
    
      List dyList = (ArrayList)request.getSession().getAttribute("department_dailyPrint");
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

    
    <title>��ӡ����</title>
    <script language="javascript">
 alert('��ӡ�ɹ�!');
 window.close();
</script>


  </head>
  
  <body bgcolor="#ffffff">
    
  </body>
</html>