package com.kj133.entity.bo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
  
 

public class Fix_PositionBO {//��λ����

	private   DataInputStream   in;   
    private   DataOutputStream   out;  
    private   String keyWords;//�ؼ���
    private   Long length;//����
    private   int readerCount;//��վ����
    private   Long readerID;//��վID
    private   String temperature;//�¶�
    private   String  status;//��վ״̬
    private   Long cardCount;//��������
    private   Long cardID;//��ID
    private   Long locatorID;//��λ��ID
    private   Integer state;//����״̬
    private   String antenna;//���ߺ�
    
public  void socketShow()   
{
	  
	 
	 try
	  { 
	    /*int length=22;//����
       int keyWords=15377;//�ؼ��� 3C11(16����) 3C->10=60 11->10=17 
       String userId="Jack";*/
       byte[] send=new byte[24];	 
           send[0]=22; //�����ֽڵ����λ��127
           send[1]=(byte)240;//240ת���ɶ�����1111 0000(82������8�Ǹ�λ�������ڵ�������2�Ǹ�λ)
           send[2]=17;
           send[3]=60;//֮������д��17����Ϊ��keyWords��word���ͣ�������ʱ���ǴӺ���ǰ���
           send[4]=74; //'j' ascii��
           send[5]=97;
           send[6]=99;
           send[7]=107;
           
	   Socket socket = new Socket("192.168.0.122", 34567);
       out =new DataOutputStream(socket.getOutputStream());   
       in=new  DataInputStream(socket.getInputStream());   
       out.write(send);   
       out.flush();   
           
        int c;
        byte[] receive =new byte[900000]; //����һ������Ϊ9000000���ֽ����飬��Ϊ��֪��Ҫ���ֽڵĳ����Ƕ��٣������Ǿ�����д��
        while( (c  = in.read(receive,0, 900000))>=0)  //��0��ʼ��������9000000
            {     
                 for(int i=0;i<c;i++)   
                    {    

           	             System.out.println("�� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��ʼ����  �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ��"); 
                         keyWords=Integer.toHexString(receive[1])+Integer.toHexString(receive[0]);
                     	 System.out.println("  �ؼ���== "+Long.parseLong(keyWords, 16));
         	  		     
                     	 int receive3=add256(Integer.parseInt(String.valueOf(receive[3])));
                     	 int receive2=add256(Integer.parseInt(String.valueOf(receive[2])));
                     	 String byteLength=Integer.toHexString(receive3)+Integer.toHexString(receive2);
         	  			 
         	  			length=Long.parseLong(byteLength, 16);
         	  			 System.out.println("  ����== "+length);
         				 
         	  		     readerCount=Integer.parseInt(String.valueOf(receive[4]));
         				 System.out.println("  ��վ��== "+readerCount);
         				 
         				 String time=Integer.toHexString(receive[5]);
         				 System.out.println("  ʱ��== "+Double.longBitsToDouble(Long.parseLong(time, 16)));

             			 int sum=13;	 
             		     for(int k=0;k<readerCount;k++){//��վѭ��
             			     
             		    	 System.out.println("==================��վѭ����ʼ==================  ��"+k+"�η�վѭ��"+"   "+sum);  
        					 String rID=Integer.toHexString(receive[sum+1])+Integer.toHexString(receive[sum]);
   
        					 readerID=Long.parseLong(rID, 16);
            		         System.out.println("��������վID== "+readerID);
            				 
            				 temperature=String.valueOf(receive[sum+2]);
        	                 System.out.println("  �¶���== "+temperature);
        	                 
        	                 status=String.valueOf(receive[sum+3]);
        	                 System.out.println("  ��վ״̬== "+status);
        	                 
        	                 String card=Integer.toHexString(receive[sum+5])+Integer.toHexString(receive[sum+4]);
        	                 cardCount=Long.parseLong(card, 16);
        	                 System.out.println("  ����������== "+cardCount);
            	                 System.out.println("==================��վѭ����ʼ==================");
            	             sum=sum+6;
            	          
         	                 for(int j=0;j<cardCount;j++){//������ѭ��
             	                	 System.out.println("++++++++++++++++++����ѭ����ʼ+++++++++++++++++  ��"+j+"�ο�ѭ����ʼ"+"  "+sum);
             	                	 Integer cardOne=Integer.parseInt(String.valueOf(receive[sum]));   
             	                	  if(  cardOne < 0 ){//ֻҪ��һ����Ϊ������ת�����д��󣬱��� 42 -9 =��102245552 ��ô��Ӧ�ð�-9ת����-9+256
             	                		    cardOne=cardOne+256;
             	                            if( cardOne<10 ) //03 48 ת������3��48,������Ҫ��"0"
             	                                 {                            	   
             	                            	    String cID=Integer.toHexString(receive[sum+1])+"0"+Integer.toHexString(cardOne);
             	                            	    cardID=Long.parseLong(cID, 16);
             	                            	   System.out.println("  ������== "+cardID); 
             	                            }else{
             	                            	    String cID=Integer.toHexString(receive[sum+1])+Integer.toHexString(cardOne);
             	                            	   cardID=Long.parseLong(cID, 16);
           	                            	    System.out.println("  ������== "+cardID);  
             	                            }
             	                	  }else{ 
             	                		   if( cardOne < 16 )//15ת����16������F
             	                		        {
   	        	                            	 String cID=Integer.toHexString(receive[sum+1])+"0"+Integer.toHexString(cardOne);
   	        	                            	 cardID=Long.parseLong(cID, 16);
   	          	                             System.out.println("  ������== "+cardID); 
           	                            }else{
   				          	                	 String cID=Integer.toHexString(receive[sum+1])+Integer.toHexString(receive[sum]);
   				          	                	 cardID=Long.parseLong(cID, 16);
             	                            	    System.out.println("  ������== "+cardID); 
   		          	    	  			     }
             	                	      }
             	                	  
             	                	  String locaID=Integer.toHexString(receive[sum+3])+Integer.toHexString(receive[sum+2]);
             	                	  locatorID=Long.parseLong(locaID, 16);
             	                	  System.out.println("  ��λ������== "+locatorID);
             	    	  			   
             	                	  state=add256(Integer.parseInt(String.valueOf(receive[sum+4])));
             	    	  			  System.out.println("  ��״̬== "+state);
             	    	  			  
             	    	  			  antenna=String.valueOf(receive[sum+5]);
             	    	  			  System.out.println("  ���ߺ�== "+antenna);

             	    	  		   System.out.println("  ��������ź�ʱ��== "+String.valueOf(receive[sum+6])+String.valueOf(receive[sum+7])+String.valueOf(receive[sum+8])+String.valueOf(receive[sum+9]));
             	    	  			   System.out.println("  ����¼��վʱ��== "+String.valueOf(receive[sum+10])+String.valueOf(receive[sum+11])+String.valueOf(receive[sum+12])+String.valueOf(receive[sum+13]));
             	    	  		   System.out.println("  �뾮ʱ��== "+String.valueOf(receive[sum+14])+String.valueOf(receive[sum+15])+String.valueOf(receive[sum+16])+String.valueOf(receive[sum+17]));
             	    	  			  sum=sum+18;
             	    	  			  
             	    	  			  
                                       }//������ѭ��
	              	                 System.out.println("++++++++++++++++++����ѭ��OVER+++++++++++++++++");
	              		         }   
                           }      
               }//while             
	    }
		   catch (IOException e){
			    System.err.print("------���Ӳɼ�������!------");
			    e.printStackTrace();
		    } catch (NumberFormatException e) {
			  e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
		 }
      
		 public  int add256(int b)throws Exception {//һ���ֽڵ����λ��127
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
