<%@ page contentType="application/pdf;charset=GB2312"%>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.engine.data.*" %>
<%@ page import="com.kj133.entity.Search_emphases_check_report" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
    <%
      //�������֮�����ɵ�.jasper �ļ��Ĵ��λ��
      response.reset();
      File reportFile = new File(application.getRealPath("reports/emphases_check_report.jasper"));
      //���ݱ������õ��Ĳ���ֵ
      Search_emphases_check_report jf = (Search_emphases_check_report)request.getSession().getAttribute("emphases_check_report");
      String chooseTime = jf.getIsChoose();
      
      Map parameters = new HashMap();     
      
      if(chooseTime.length()<8){
      parameters.put("sstime",jf.getMonthtime());
      }else{
      parameters.put("sstime",jf.getDaytime());
      }
          
      List dyList = (ArrayList)request.getSession().getAttribute("emphases_check_reportPrint");
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
