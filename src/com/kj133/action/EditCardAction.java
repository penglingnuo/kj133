//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.kj133.action;



import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.kj133.entity.Search_eidtCard;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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
import com.kj133.entity.bo.EditCardBO;
import com.kj133.entity.bo.WordlibBO;
import com.kj133.entity.vo.EditCardVO;
/** 
 * MyEclipse Struts
 * Creation date: 01-29-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class EditCardAction extends DispatchAction {
	private final Logger  log=Logger.getLogger(this.getClass());
	/** 
	 *  ��ȡ�༭������
	 */
	public ActionForward getList(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
	    DynaActionForm dy=(DynaActionForm)form;
		EditCardBO bo=new EditCardBO();
		WordlibBO lib=new WordlibBO();
		Pagination pagination = new Pagination();//��ҳ
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	      Date date = new java.util.Date();
	      Calendar c = Calendar.getInstance();
	      c.add(Calendar.DATE, -7);   //�˴����Ա���꣬�£��գ��ֵ�
	      String stime = format.format( c.getTime());
	      String etime = format.format(date);
		String page =(String)request.getParameter("page");//��ȡҳ��
		try{
				  Search_eidtCard card=(Search_eidtCard)dy.get("ser_eidtCard");
				  
				  pagination.setCount(50);//ÿҳ��ʾ50����¼
			      if (page != null && page.length() > 0) {
			        pagination.setPage(Integer.parseInt(page));
			      }else {
			        pagination.setPage(1);
			      }//��ҳ
			    List list=bo.init(card,pagination);
			    List relist=bo.initPrint(card);
			    request.setAttribute("listCount", list.size());//������ҳ�����жϣ���ӡԤ���Ƿ����
			    if(list.size()==0){
			    	   ActionMessages messages=new ActionMessages();
				       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("no.count"));
				       this.saveMessages(request,messages);       
			    }else{			    
			    	   request.setAttribute("editCardList",list);
			    	   request.getSession().setAttribute("editCard_topPrint", relist);
			    }
			    
			    if(card.getStime()==null){
			    	card.setStime(stime);
				}else{
					card.setStime(card.getStime());
					request.setAttribute("name4", "true");
				}if(card.getEtime()==null){
					card.setEtime(etime);
				}else{
					card.setEtime(card.getEtime());
					request.setAttribute("name3", "true");
				}if(card.getMinstime()==null){
					card.setMinstime("15:47:51");
				}else{
					card.setMinstime(card.getMinstime());
					request.setAttribute("name1", "true");
				}if(card.getMaxstime()==null){
					card.setMaxstime("15:47:51");
				}else{
					card.setMaxstime(card.getMaxstime());
					request.setAttribute("name2", "true");
				}  
			    List list2=lib.workType();
				request.setAttribute("workType_list",list2);
			    request.setAttribute("pagination", pagination);//��ҳ
			    dy.set("ser_eidtCard",card);//���°�ֵ���û�ҳ�� 
			}catch(Exception e){
				log.error("==�༭����Ϣ--��ѯ��==",e);
			}
			return mapping.findForward("editCard");
	}
	
//	����Excel
	public ActionForward doOpenExcel(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response
			){
		
			try{
				//���������ʽ���ļ�
				OutputStream os=response.getOutputStream();
				// ��������
				response.reset();
				// ������Ӧͷ�����ر�����ļ���
				response.setHeader("content-disposition",
						"attachment;filename=bianyikaxinxi.xls");
				// �����������
				response.setContentType("APPLICATION/msexcel");		
				WritableWorkbook wwb=Workbook.createWorkbook(os);
				WritableSheet ws =wwb.createSheet("���뿨��Ϣ",10);
				//	���ò����еĿ��
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 20);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 20);
				ws.setColumnView(6, 35);
				//	���ñ�ͷ
				ws.addCell(new Label(0,0,"����"));
				ws.addCell(new Label(1,0,"���ͺ�"));
				ws.addCell(new Label(2,0,"����"));
				ws.addCell(new Label(3,0,"����"));
				ws.addCell(new Label(4,0,"����"));
				ws.addCell(new Label(5,0,"��״̬"));
				ws.addCell(new Label(6,0,"ע��ʱ��"));
				
				List list = (ArrayList)request.getSession().getAttribute("editCard_topPrint");
				//	�������
				if(list.size()>0){
					for(int i=0;i<list.size();i++){
						EditCardVO  v= (EditCardVO)list.get(i);
						ws.addCell(new Label(0,i+1,v.getCardid()));
						ws.addCell(new Label(1,i+1,v.getCardmode()));
						ws.addCell(new Label(2,i+1,v.getWorktype()));
						ws.addCell(new Label(3,i+1,v.getName()));
						ws.addCell(new Label(4,i+1,v.getGro()));
						ws.addCell(new Label(5,i+1,v.getCardstate()));
						ws.addCell(new Label(6,i+1,v.getRegdate()));
					}
				}
				wwb.write();
				wwb.close();
				os.close();
				
			}
			catch(RowsExceededException e){
				log.error("==�༭����Ϣ--����excel==",e);
			}
			catch(WriteException ex){
				log.error("==�༭����Ϣ--����excel==",ex);
			}
			catch(IOException e){
				log.error("==�༭����Ϣ--����excel==",e);
			}
		return null;
	}
	
	/**
	 * ��ʼ��ʱ���ְ��
	 * */
	public ActionForward getWorktype(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		    DynaActionForm dy=(DynaActionForm) form;
			WordlibBO lib=new WordlibBO();
			try{
				 Search_eidtCard card=(Search_eidtCard)dy.get("ser_eidtCard");
				 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			      Date date = new java.util.Date();
			      Calendar c = Calendar.getInstance();
			      c.add(Calendar.DATE, -7);   //�˴����Ա���꣬�£��գ��ֵ�
			      String stime = format.format( c.getTime());
			      String etime = format.format(date);
			      card.setStime(stime);
			      card.setEtime(etime);
			      card.setMinstime("15:47:51");
			      card.setMaxstime("15:47:51");
				 List gro=lib.workType();
				 request.setAttribute("workType_list",gro);
				 request.setAttribute("listCount", "0");//��һ�ε�ʱ�򣬴�ӡԤ���ǲ����õ�
				 dy.set("ser_eidtCard",card);
			 }catch(Exception e){
				 log.error("==�༭����Ϣ--��ʼ��==",e);
			 }
			 return mapping.findForward("worktype");
		}
	
	/**
	 * ��ӡԤ��
	 * */
	@SuppressWarnings("unchecked")
	public ActionForward viewPrint(
			ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		    ServletContext context =this.getServlet().getServletConfig().getServletContext();
		    response.setContentType("text/html"); 
			try{
				 List list=(List)request.getSession().getAttribute("editCardListView");   
		    	 File reportFile = new File(context.getRealPath("/reports/editCard.jasper"));
	 			 JasperReport  jasperReport= (JasperReport)JRLoader.loadObject(reportFile.getPath());
	 			 JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
	 			 HashMap map=new HashMap();
	 			/* map.put("title", "�༭��");
	 			 map.put("cardid", "����");
	 			 map.put("cardmode", "��ģ��");
	 			 map.put("worktype", "����");
	 			 map.put("name", "����");
	 			 map.put("gro", "����");
	 			 map.put("cardstate", "��״̬");
	 			 map.put("regdate", "ʱ��");*/

	 			 JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,map,dataSource);
	 			 request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
	    		 
	 			 JRHtmlExporter exporter = new JRHtmlExporter();
	 			 int pageIndex = 0;
	 			 int lastPageIndex = 0;
	 			 if (jasperPrint.getPages() != null)
	 			 {
	 				lastPageIndex = jasperPrint.getPages().size() - 1;
	 			 }
	 			 String pageStr = request.getParameter("page");
	 			 try
	 			 {
	 				pageIndex = Integer.parseInt(pageStr);
	 			 }
	 			 catch(Exception e)
	 			 {
	 				 
	 			 }	 	 		
	 			 if (pageIndex < 0)
	 			 {
	 				pageIndex = 0;
	 			 }
	 			 if (pageIndex > lastPageIndex)
	 			 {
	 			 	pageIndex = lastPageIndex;
	 			 }
	 			 request.setAttribute("pageIndex", pageIndex);
	 			 request.setAttribute("lastPageIndex", lastPageIndex);
	 			
	    		 StringBuffer sbuffer = new StringBuffer();
	    		 request.setAttribute("sb", sbuffer);
	    		  
	    		 exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);//�Ե���ģ����в���
	    	     exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
	    	     //exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "imagesĿ¼��·��?image=");
                 //������htmlԤ����ʱ�������һ��px�ļ��������images_uri��·�������Ǹ�px��·�������鲻�ã��������� �����Ϳ����ˡ������� 
	    	     //��д�Ļ���ҳ���Ͼͻ���ʾ�ܶ�ͼƬ������������ͼƬ���ز��ɹ���״̬
	    	     exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
	    	     exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(pageIndex));
      	         exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
                 exporter.exportReport();
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
	        return mapping.findForward("vprint");
	}
	
	/**
	 *��ӡ
	 * */
	@SuppressWarnings("unchecked")
	public ActionForward printEditCard(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
	     	ServletContext context = this.getServlet().getServletConfig().getServletContext();
	   	    File reportFile = new File(context.getRealPath("/reports/editCard.jasper"));
	   	    HashMap map=new HashMap();
			JasperPrint jasperPrint = null;
		    try
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				List list=(List)request.getSession().getAttribute("editCardListView");
				JRBeanCollectionDataSource  dataSource = new JRBeanCollectionDataSource(list);
				jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource );
			}
			catch (Exception e)
			{
			  e.printStackTrace();
			}
			if (jasperPrint != null)
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
				out.println("<title>JasperReports - Web Application Sample</title>");
				out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
				out.println("</head>");
				out.println("<body bgcolor=\"white\">");
				out.println("<span class=\"bold\">EidtCard.Jasper�ļ������ڻ��������</span>");
				out.println("</body>");
				out.println("</html>");
			}			
		 return null;
	  }
}

