package com.kj133.util;

  
import java.text.ParseException;
import java.text.SimpleDateFormat;
import   java.util.*;  

import   org.jdom.*;  
import   org.jdom.input.*;  
import   org.jdom.xpath.*;  


public   class   Test{  
     /* public   static   void   main(String[]   args)   throws   Exception{  
        	  /*String s="<list><user><username>a</username><password>a</password></user><user><username>b</username><password>b</password></user></list>";
              SAXBuilder   builder   =   new   SAXBuilder();  
              Document   doc   =   builder.build(s);  
              XPath   xpath   =   XPath.newInstance("//user[./username='b']");  
              List   user=xpath.selectNodes(doc);  
              Iterator   iter   =user.iterator();  
              while   (iter.hasNext())   {  
              Element   item   =   (Element)   iter.next(); 
              System.out.println(item.getText());  
         }   
       
        
        
  }  */
	public int getDaysBetween (String beginDate, String endDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date bDate = format.parse(beginDate);
		Date eDate = format.parse(endDate);
		Calendar d1 = new GregorianCalendar();
		d1.setTime(bDate);
		Calendar d2 = new GregorianCalendar();
		d2.setTime(eDate);
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);//得到当年的实际天数
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
           
        }
        return days;
        
    }
	
	public int getTimesBetween1(String beginTime,String endTime) throws ParseException {
		int result = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		
		Date btime = format.parse(beginTime);
		Date etime = format.parse(endTime);
		
		long bt = btime.getTime();
		long et = etime.getTime();
		result = (int) ((et-bt)/(1000*3600));
		return result;
	}
	
	public static  void main(String[] args)throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new java.util.Date();
	      Calendar c = Calendar.getInstance();
	      c.add(Calendar.DATE, +1);   //此处可以变成年，月，日，分等
	      String day = format.format( c.getTime());
		String a = "1982-2-13 12:20:30";
		String b = "1982-2-14 18:20:30";
		Test t = new Test();
		int cc = t.getTimesBetween1(a,b);
		System.out.println(t.getDaysBetween(a,b));
		System.out.println(cc);
		System.out.println(day);
		
	}
}   

