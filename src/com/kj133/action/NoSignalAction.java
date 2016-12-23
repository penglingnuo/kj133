//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_NoSingnal;
import com.kj133.entity.bo.NoSignalBO;
import com.kj133.entity.vo.NoSignalVO;

/**
 * MyEclipse Struts Creation date: 02-05-2007
 * 
 * XDoclet definition:
 * 
 * @struts.action scope="request" validate="true"
 */
public class NoSignalAction extends DispatchAction
{

	// --------------------------------------------------------- Instance
	// Variables

	// --------------------------------------------------------- Methods
	private final Logger	log	= Logger.getLogger(this.getClass());

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 *            无信号报警
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward getList(ActionMapping mapping, ActionForm form,
	        HttpServletRequest request, HttpServletResponse response)
	{
		DynaActionForm dy = (DynaActionForm) form;
		NoSignalBO bo = new NoSignalBO();
		Pagination pagination = new Pagination();// 分页
		String page = (String) request.getParameter("page");// 获取页号F
		try
		{
			Search_NoSingnal nosin = (Search_NoSingnal) dy.get("ser_nosingal");// 取出formbean,也就是查询的字段
			// 分页
			pagination.setCount(200);// 分页
			if(page != null && page.length() > 0)
			{
				pagination.setPage(Integer.parseInt(page));
			}
			else
			{
				pagination.setPage(1);
			}// 分页
			List list = bo.init(nosin, pagination);
			List relist = bo.initPrint(nosin);
			request.setAttribute("listCount", list.size());
			if(list.size() == 0)
			{
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				        "no.count"));
				this.saveMessages(request, messages);
			}
			else
			{
				request.setAttribute("Nosignal_List", list);
				request.getSession().setAttribute("Nosignal_ListPrint", relist);
			}

			request.setAttribute("pagination", pagination);// 分页
			dy.set("ser_nosingal", nosin);// 重新把值设置回页面
		}
		catch (Exception e)
		{
			log.error("==定位器无信号查询==", e);
		}
		return mapping.findForward("show");
	}
	
	/**
	 * 導出無信號報警查詢結果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward export(ActionMapping mapping, ActionForm form,
	        HttpServletRequest request, HttpServletResponse response)
	{
		DynaActionForm dy = (DynaActionForm) form;
		NoSignalBO bo = new NoSignalBO();
		try
		{
			Search_NoSingnal nosin = (Search_NoSingnal) dy.get("ser_nosingal");// 取出formbean,也就是查询的字段
			List list = bo.initPrint(nosin);
			
			//创建导入格式表文件
			OutputStream os=response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设置响应头和下载保存的文件名
//			response.setHeader("content-disposition","attachment;filename=bianyiyuangongxinxi.xls");
			response.setHeader("content-disposition","attachment;filename="+URLEncoder.encode("定位器无信号查询","UTF-8")+".xls");
			// 定义输出类型
			response.setContentType("APPLICATION/msexcel");		
			WritableWorkbook wwb=Workbook.createWorkbook(os);
			WritableSheet ws =wwb.createSheet("定位器无信号查询",8);
			//	设置部分列的宽度
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 10);
			ws.setColumnView(3, 30);
			ws.setColumnView(4, 15);
			ws.setColumnView(5, 25);
			ws.setColumnView(6, 10);
			ws.setColumnView(7, 20);
			ws.setColumnView(8, 25);
			//	设置表头
			ws.addCell(new Label(0,0,"定位器号"));
			ws.addCell(new Label(1,0,"定位器名称"));
			ws.addCell(new Label(2,0,"最后信号时间"));

			//	填充数据
			if(list.size()>0){
				for(int i=0;i<list.size();i++){
					NoSignalVO v=(NoSignalVO) list.get(i);
					ws.addCell(new Label(0,i+1,v.getLid()));
					ws.addCell(new Label(1,i+1,v.getSname()));
					ws.addCell(new Label(2,i+1,v.getTimes()));
				}
			}
			wwb.write();
			wwb.close();
			os.close();
		}
		catch (Exception e)
		{
			log.error("==定位器无信号查询==", e);
		}
		return null;
	}

	/**
	 * 初始化时间
	 */
	public ActionForward initialization(ActionMapping mapping, ActionForm form,
	        HttpServletRequest request, HttpServletResponse response)
	{
		DynaActionForm dy = (DynaActionForm) form;
		try
		{
			Search_NoSingnal nosin = (Search_NoSingnal) dy.get("ser_nosingal");
			nosin.setDay("7");
			dy.set("ser_nosingal", nosin);
			request.setAttribute("listCount", "0");
		}
		catch (Exception e)
		{
			log.error("==定位器无信号查询==", e);
		}
		return mapping.findForward("show");
	}

	/**
	 * 打印预览
	 */
	@SuppressWarnings("unchecked")
	public ActionForward viewPrint(ActionMapping mapping, ActionForm form,
	        HttpServletRequest request, HttpServletResponse response)
	        throws IOException
	{
		ServletContext context = this.getServlet().getServletConfig()
		        .getServletContext();
		response.setContentType("text/html");
		try
		{
			List list = (List) request.getSession().getAttribute(
			        "Nosignal_ListPrint");
			File reportFile = new File(context
			        .getRealPath("/reports/noSignal.jasper"));
			JasperReport jasperReport = (JasperReport) JRLoader
			        .loadObject(reportFile.getPath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
			        list);
			HashMap map = new HashMap();

			JasperPrint jasperPrint = JasperFillManager.fillReport(
			        jasperReport, map, dataSource);
			request.getSession().setAttribute(
			        ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
			        jasperPrint);

			JRHtmlExporter exporter = new JRHtmlExporter();
			int pageIndex = 0;
			int lastPageIndex = 0;
			if(jasperPrint.getPages() != null)
			{
				lastPageIndex = jasperPrint.getPages().size() - 1;
			}
			String pageStr = request.getParameter("page");
			try
			{
				pageIndex = Integer.parseInt(pageStr);
			}
			catch (Exception e)
			{

			}
			if(pageIndex < 0)
			{
				pageIndex = 0;
			}
			if(pageIndex > lastPageIndex)
			{
				pageIndex = lastPageIndex;
			}
			request.setAttribute("pageIndex", pageIndex);
			request.setAttribute("lastPageIndex", lastPageIndex);

			StringBuffer sbuffer = new StringBuffer();
			request.setAttribute("sb", sbuffer);

			exporter
			        .setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);// 对单个模板进行操作
			exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER,
			        sbuffer);
			// exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
			// "images目录的路径?image=");
			// 在生成html预览的时候会生成一个px文件，而这个images_uri的路径就是那个px的路径，建议不用，就用下面
			// 的语句就可以了····
			// 不写的话，页面上就会显示很多图片，并且是那种图片加载不成功的状态
			exporter.setParameter(
			        JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
			        Boolean.FALSE);
			exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(
			        pageIndex));
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,
			        "");
			exporter.exportReport();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return mapping.findForward("vprint");
	}

	/**
	 * 打印
	 */
	@SuppressWarnings("unchecked")
	public ActionForward print(ActionMapping mapping, ActionForm form,
	        HttpServletRequest request, HttpServletResponse response)
	        throws IOException
	{
		ServletContext context = this.getServlet().getServletConfig()
		        .getServletContext();
		File reportFile = new File(context
		        .getRealPath("/reports/noSignal.jasper"));
		HashMap map = new HashMap();
		/*
		 * map.put("lid", "定位器号"); map.put("sname", "定位器名"); map.put("times",
		 * "最后信号时间");
		 */

		JasperPrint jasperPrint = null;
		try
		{
			JasperReport jasperReport = (JasperReport) JRLoader
			        .loadObject(reportFile.getPath());
			List list = (List) request.getSession().getAttribute(
			        "Nosignal_ListPrint");
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
			        list);
			jasperPrint = JasperFillManager.fillReport(jasperReport, map,
			        dataSource);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if(jasperPrint != null)
		{
			response.setContentType("application/octet-stream");
			ServletOutputStream ouputStream = response.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(ouputStream);
			oos.writeObject(jasperPrint);
			oos.flush();
			oos.close();
			ouputStream.flush();
			ouputStream.close();
		}
		else
		{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out
			        .println("<title>JasperReports - Web Application Sample</title>");
			out
			        .println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
			out.println("</head>");
			out.println("<body bgcolor=\"white\">");
			out
			        .println("<span class=\"bold\">noSignal.jasper文件不存在或解析错误！</span>");
			out.println("</body>");
			out.println("</html>");
		}
		return null;
	}

	/***************************************************************************
	 * pdf 打印
	 **************************************************************************/
	public ActionForward pdfPrint(ActionMapping mapping, ActionForm form,
	        HttpServletRequest request, HttpServletResponse response)
	        throws IOException
	{
		ServletOutputStream servletOutputStream = response.getOutputStream();
		InputStream reportStream = this.getServlet().getServletConfig()
		        .getServletContext().getResourceAsStream(
		                "/reports/noSignal.jasper");
		try
		{
			List list = (List) request.getSession().getAttribute(
			        "Nosignal_ListPrint");
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
			        list);
			JasperRunManager.runReportToPdfStream(reportStream,
			        servletOutputStream, new HashMap(), dataSource);
			response.setContentType("application/pdf");
			// servletOutputStream.flush();
			// servletOutputStream.close();
		}
		catch (Exception e)
		{
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			response.setContentType("text/plain");
			response.getOutputStream().print(stringWriter.toString());
		}

		return mapping.findForward("dd");
	}
}
