<%@ page contentType="application/pdf;charset=GB2312"%>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.engine.data.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

    <%
      //�������֮�����ɵ�.jasper �ļ��Ĵ��λ��
      response.reset();
      File reportFile = new File(application.getRealPath("reports/note.jasper"));
      //���ݱ������õ��Ĳ���ֵ
      String ktime = request.getSession().getAttribute("ktime").toString();
      String jtime = request.getSession().getAttribute("jtime").toString();
      
      Map parameters = new HashMap();     
      parameters.put("ktime",ktime);
      parameters.put("jtime",jtime);
      
      List dyList = (ArrayList)request.getSession().getAttribute("Note_ListPrint");
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
  ��ӡ����
</title>
<script language="javascript">
 alert('��ӡ�ɹ�!');
 window.close();
</script>
</head>
<body bgcolor="#ffffff">

</body>
</html>