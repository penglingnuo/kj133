package com.kj133.entity.bo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
  
 

public class Fix_PositionBO {//定位跟踪

	private   DataInputStream   in;   
    private   DataOutputStream   out;  
    private   String keyWords;//关键字
    private   Long length;//长度
    private   int readerCount;//分站总数
    private   Long readerID;//分站ID
    private   String temperature;//温度
    private   String  status;//分站状态
    private   Long cardCount;//卡的总数
    private   Long cardID;//卡ID
    private   Long locatorID;//定位器ID
    private   Integer state;//报警状态
    private   String antenna;//天线号
    
public  void socketShow()   
{
	  
	 
	 try
	  { 
	    /*int length=22;//长度
       int keyWords=15377;//关键字 3C11(16进制) 3C->10=60 11->10=17 
       String userId="Jack";*/
       byte[] send=new byte[24];	 
           send[0]=22; //单个字节的最高位是127
           send[1]=(byte)240;//240转换成二进制1111 0000(82，其中8是高位，但是在电脑里面2是高位)
           send[2]=17;
           send[3]=60;//之所先先写，17是因为，keyWords是word类型，他读的时候是从后往前面读
           send[4]=74; //'j' ascii码
           send[5]=97;
           send[6]=99;
           send[7]=107;
           
	   Socket socket = new Socket("192.168.0.122", 34567);
       out =new DataOutputStream(socket.getOutputStream());   
       in=new  DataInputStream(socket.getInputStream());   
       out.write(send);   
       out.flush();   
           
        int c;
        byte[] receive =new byte[900000]; //定义一个长度为9000000的字节数组，因为不知道要读字节的长度是多少，所以是尽量的写大
        while( (c  = in.read(receive,0, 900000))>=0)  //从0开始读，读到9000000
            {     
                 for(int i=0;i<c;i++)   
                    {    

           	             System.out.println("▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ 开始接收  ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌ ▌"); 
                         keyWords=Integer.toHexString(receive[1])+Integer.toHexString(receive[0]);
                     	 System.out.println("  关键字== "+Long.parseLong(keyWords, 16));
         	  		     
                     	 int receive3=add256(Integer.parseInt(String.valueOf(receive[3])));
                     	 int receive2=add256(Integer.parseInt(String.valueOf(receive[2])));
                     	 String byteLength=Integer.toHexString(receive3)+Integer.toHexString(receive2);
         	  			 
         	  			length=Long.parseLong(byteLength, 16);
         	  			 System.out.println("  长度== "+length);
         				 
         	  		     readerCount=Integer.parseInt(String.valueOf(receive[4]));
         				 System.out.println("  分站数== "+readerCount);
         				 
         				 String time=Integer.toHexString(receive[5]);
         				 System.out.println("  时间== "+Double.longBitsToDouble(Long.parseLong(time, 16)));

             			 int sum=13;	 
             		     for(int k=0;k<readerCount;k++){//分站循环
             			     
             		    	 System.out.println("==================分站循环开始==================  第"+k+"次分站循环"+"   "+sum);  
        					 String rID=Integer.toHexString(receive[sum+1])+Integer.toHexString(receive[sum]);
   
        					 readerID=Long.parseLong(rID, 16);
            		         System.out.println("███分站ID== "+readerID);
            				 
            				 temperature=String.valueOf(receive[sum+2]);
        	                 System.out.println("  温度是== "+temperature);
        	                 
        	                 status=String.valueOf(receive[sum+3]);
        	                 System.out.println("  分站状态== "+status);
        	                 
        	                 String card=Integer.toHexString(receive[sum+5])+Integer.toHexString(receive[sum+4]);
        	                 cardCount=Long.parseLong(card, 16);
        	                 System.out.println("  卡的总数是== "+cardCount);
            	                 System.out.println("==================分站循环开始==================");
            	             sum=sum+6;
            	          
         	                 for(int j=0;j<cardCount;j++){//按卡数循环
             	                	 System.out.println("++++++++++++++++++卡数循环开始+++++++++++++++++  第"+j+"次卡循环开始"+"  "+sum);
             	                	 Integer cardOne=Integer.parseInt(String.valueOf(receive[sum]));   
             	                	  if(  cardOne < 0 ){//只要有一个数为负数，转换就有错误，比如 42 -9 =》102245552 那么就应该把-9转换成-9+256
             	                		    cardOne=cardOne+256;
             	                            if( cardOne<10 ) //03 48 转换后是3和48,所以需要加"0"
             	                                 {                            	   
             	                            	    String cID=Integer.toHexString(receive[sum+1])+"0"+Integer.toHexString(cardOne);
             	                            	    cardID=Long.parseLong(cID, 16);
             	                            	   System.out.println("  卡号是== "+cardID); 
             	                            }else{
             	                            	    String cID=Integer.toHexString(receive[sum+1])+Integer.toHexString(cardOne);
             	                            	   cardID=Long.parseLong(cID, 16);
           	                            	    System.out.println("  卡号是== "+cardID);  
             	                            }
             	                	  }else{ 
             	                		   if( cardOne < 16 )//15转换成16进制是F
             	                		        {
   	        	                            	 String cID=Integer.toHexString(receive[sum+1])+"0"+Integer.toHexString(cardOne);
   	        	                            	 cardID=Long.parseLong(cID, 16);
   	          	                             System.out.println("  卡号是== "+cardID); 
           	                            }else{
   				          	                	 String cID=Integer.toHexString(receive[sum+1])+Integer.toHexString(receive[sum]);
   				          	                	 cardID=Long.parseLong(cID, 16);
             	                            	    System.out.println("  卡号是== "+cardID); 
   		          	    	  			     }
             	                	      }
             	                	  
             	                	  String locaID=Integer.toHexString(receive[sum+3])+Integer.toHexString(receive[sum+2]);
             	                	  locatorID=Long.parseLong(locaID, 16);
             	                	  System.out.println("  定位器号是== "+locatorID);
             	    	  			   
             	                	  state=add256(Integer.parseInt(String.valueOf(receive[sum+4])));
             	    	  			  System.out.println("  卡状态== "+state);
             	    	  			  
             	    	  			  antenna=String.valueOf(receive[sum+5]);
             	    	  			  System.out.println("  天线号== "+antenna);

             	    	  		   System.out.println("  卡的最后信号时间== "+String.valueOf(receive[sum+6])+String.valueOf(receive[sum+7])+String.valueOf(receive[sum+8])+String.valueOf(receive[sum+9]));
             	    	  			   System.out.println("  最后记录分站时间== "+String.valueOf(receive[sum+10])+String.valueOf(receive[sum+11])+String.valueOf(receive[sum+12])+String.valueOf(receive[sum+13]));
             	    	  		   System.out.println("  入井时间== "+String.valueOf(receive[sum+14])+String.valueOf(receive[sum+15])+String.valueOf(receive[sum+16])+String.valueOf(receive[sum+17]));
             	    	  			  sum=sum+18;
             	    	  			  
             	    	  			  
                                       }//按卡数循环
	              	                 System.out.println("++++++++++++++++++卡数循环OVER+++++++++++++++++");
	              		         }   
                           }      
               }//while             
	    }
		   catch (IOException e){
			    System.err.print("------连接采集机错误!------");
			    e.printStackTrace();
		    } catch (NumberFormatException e) {
			  e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
		 }
      
		 public  int add256(int b)throws Exception {//一个字节的最高位是127
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
