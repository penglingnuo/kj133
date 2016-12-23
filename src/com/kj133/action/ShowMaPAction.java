/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.kj133.action;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.kj133.entity.CardReaderState;
import com.kj133.entity.bo.TrackBO;
import com.kj133.entity.vo.TrackVO;

/** 
 * MyEclipse Struts
 * Creation date: 08-25-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ShowMaPAction extends Action {
	/*
	 * Generated Methods
	 */
	 private final  Logger log=Logger.getLogger(this.getClass());
	 private   DataInputStream   in;   
     private   DataOutputStream   out;  
     private   int readerCount;//��վ����
     private   Long readerID;//��վID
     private   Long cardCount;//��������
     private   String ip=null;
     
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	        try{ 
	                byte[] send=new byte[24];	 
		            send[0]=22; //�����ֽڵ����λ��127
		            send[1]=(byte)240;//240ת���ɶ�����1111 0000(82������8�Ǹ�λ�������ڵ�������2�Ǹ�λ)
		            send[2]=17;
		            send[3]=60;//֮������д��17����Ϊ��keyWords��word���ͣ�������ʱ���ǴӺ���ǰ���
		            send[4]=77; //'j' ascii��
		            send[5]=65;
		            send[6]=80;
			            
			        ip="192.168.0.3";  //192.168.0.3 
			        Socket socket = new Socket(ip, 34567);
			        out =new DataOutputStream(socket.getOutputStream());   
			        in=new  DataInputStream(socket.getInputStream());   
			        out.write(send);   
			        out.flush();   
		
			        int c;
			     	TrackBO bo=new TrackBO();
                    List cardreader_List=bo.getCardReader();//��վ
                    List cardreaderList=new ArrayList();//��վ������Ϣ
                    byte[] receive =new byte[65536]; //����һ������Ϊ65536���ֽ����飬��Ϊ��֪��Ҫ���ֽڵĳ����Ƕ��٣������Ǿ�����д��
			        while( (c  = in.read(receive,0, 65535))>=0)  //��0��ʼ��������65535,���̫��Ļ�����ô�ͻ��׳��쳣 Caused by: java.lang.OutOfMemoryError: Java heap space
			             {     
		                 for(int i=0;i<c;i++)   
		                   { 
		        	  		     readerCount=Integer.parseInt(String.valueOf(receive[4]));//��վ��
		        			   
		            			 int sum=13;	 
		            		     for(int k=0;k<readerCount;k++){//��վѭ��
		            		    	 
		            		    	 String rID=Integer.toHexString(receive[sum+1])+Integer.toHexString(receive[sum]);       		    	    
		            		    	 
		            		    	 readerID=Long.parseLong(rID, 16);//��վID
		            		    	 
	                                				 
	                                 /*��ǰ��վ��������Ӧ����Integer.parseInt(String.valueOf(receive[sum+5]))+
	                                                 Integer.parseInt(String.valueOf(receive[sum+4]))
	                                        ���ڵ������ǰ��յ�������ת����int����10���ƣ���ת����16���ƣ�Ȼ����ת����10����
	                                   Integer.toHexString(add256(Integer.parseInt(String.valueOf(receive[sum+5]))))
	                                        ��ô�յ��ķ�վ��2���ֽ������и��������*/
	                                 
			       	                 String card=Integer.toHexString(add256(Integer.parseInt(String.valueOf(receive[sum+5]))))+Integer.toHexString(add256(Integer.parseInt(String.valueOf(receive[sum+4]))));
			       	                 //System.out.println("card=="+card);     //  031
			       	                 cardCount=Long.parseLong(card, 16); //������  16���Ƶ�031ת����10������  49
			       	               	
			       	                 //---------------��ǰ��վ����
				       	             TrackVO cardReaderState=(TrackVO) cardreader_List.get(Integer.parseInt(String.valueOf(readerID)));
				       	             //����ԭ����cardreader_List.get()�����256,���е�ʱ���յ�����readerID��11837
				       	             CardReaderState cardreadrstate=new CardReaderState(cardReaderState.getId(),cardReaderState.getShortname(),null,String.valueOf(cardCount));
				       	             cardreaderList.add(cardreadrstate);
				       	             
				       	             
				       	             int count=0;//������
				       	             int upCount=0;
				       	            
				       	             for(int cou=0;cou<cardreaderList.size();cou++){
				       	            	CardReaderState cs=(CardReaderState)cardreaderList.get(cou);
				       	            	count=count+Integer.parseInt(cs.getReadercount()); 
				       	            	
				       	             } 
				       	             
				       	            request.setAttribute("count", String.valueOf(count));//ͳ��������
				       	              
				       	            if(cardreadrstate.getCardreaderid().equals("1")){
				       	            	request.setAttribute("c1", cardreadrstate.getReadercount().toString());
				       	             }if(cardreadrstate.getCardreaderid().equals("2")){
				       	            	request.setAttribute("c2", cardreadrstate.getReadercount().toString());
				       	             }if(cardreadrstate.getCardreaderid().equals("3")){
				       	            	request.setAttribute("c3", cardreadrstate.getReadercount().toString());
				       	             }if(cardreadrstate.getCardreaderid().equals("4")){
				       	            	request.setAttribute("c4", cardreadrstate.getReadercount().toString());
				       	             }if(cardreadrstate.getCardreaderid().equals("5")){
				       	            	request.setAttribute("c5", cardreadrstate.getReadercount().toString());
				       	             }if(cardreadrstate.getCardreaderid().equals("6")){
				       	            	request.setAttribute("c6", cardreadrstate.getReadercount().toString());
				       	             }if(cardreadrstate.getCardreaderid().equals("7")){
				       	            	request.setAttribute("c7", cardreadrstate.getReadercount().toString());
				       	             }if(cardreadrstate.getCardreaderid().equals("8")){
				       	            	request.setAttribute("c8", cardreadrstate.getReadercount().toString());
				       	             }if(cardreadrstate.getCardreaderid().equals("9")){
				       	            	request.setAttribute("c9", cardreadrstate.getReadercount().toString());
				       	             }if(cardreadrstate.getCardreaderid().equals("10")){
				       	            	request.setAttribute("c10", cardreadrstate.getReadercount().toString());
				       	             }if(cardreadrstate.getCardreaderid().equals("11")){
				       	            	request.setAttribute("c11", cardreadrstate.getReadercount().toString());
				       	             }if(cardreadrstate.getCardreaderid().equals("12")){
				       	            	request.setAttribute("c12", cardreadrstate.getReadercount().toString());
				       	             }if(cardreadrstate.getCardreaderid().equals("13")){
				       	            	request.setAttribute("c13", cardreadrstate.getReadercount().toString());
				       	             }if(cardreadrstate.getCardreaderid().equals("127")){
				       	            	 upCount=Integer.parseInt(cardreadrstate.getReadercount());
				       	            	 request.setAttribute("c14", cardreadrstate.getReadercount().toString());
				       	             }  
				       	             
				       	             request.setAttribute("upCount", upCount);//����������
				       	             int downCount=count-upCount;
				       	             request.setAttribute("downCount", downCount);//����������
			           	             sum=sum+6;
			        	             for(int j=0;j<cardCount;j++){//��������ѭ��
			        	            	       sum=sum+20;
                                           }      
			            		       } 
                                          return mapping.findForward("showmap");	 
			                      }   
		                  } 
                }catch(Exception e){
             	    log.error("--- �ɼ������Ӵ��󣺿�ʾ��ͼ --- ", e);
             	    request.setAttribute("ipAddress", ip);
             	    return mapping.findForward("srror");
				 }
		  return null;
	}
	
     // һ���ֽڵ����λ��127,��Ϊ�е�ʱ���յ�����������-128+256����128
	 public  int add256(int b)throws Exception {
         int a=0; 
		  try{
        	  if(b<0){
 	    		 a=b+256;
 	    	 }else{
 	    		 a=b;
 	    	 }
          }catch(Exception e){
        	  e.printStackTrace();
          }
          return a;
		 }
}