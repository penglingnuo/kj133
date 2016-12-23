package com.kj133.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.vo.Employee_menology_mountVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
 
public class Global {
  
	 private static final Logger log=Logger.getLogger(Global.class);
	
	 /**
	 * �ж����ӵĻ����޸ĵĺ����ݿ������һ��
	 * */  
	 @SuppressWarnings("unchecked")
	public List juede(String sql,String id)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(sql);
		 param.add(id);
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
             relist=query.getResults(sb.toString(),param.toArray(),null);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist;
	 }
	 
	 /**
	  * ɾ��
	  * */
	 @SuppressWarnings("unchecked")
	public void delete(String sql, String name)throws Exception {
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(sql);
		 param.add(name);
		 try{
			 engine=EngineFactory.getEngine("test");
			 engine.executeSpecialSQL(sb.toString(),param.toArray());
			 engine.commit();
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
	 }
	 /**
	  * �ж����ӵĻ����޸ĵĺ����ݿ������һ��
	  * */  
	 @SuppressWarnings("unchecked")
	 public List juede1(String sql,String id,String name)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(sql);
		 param.add(id);
		 param.add(name);
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
			 relist=query.getResults(sb.toString(),param.toArray(),null);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist;
	 }
	 /**
	  * �ж����ӵĻ����޸ĵĺ����ݿ������һ��
	  * */  
	 @SuppressWarnings("unchecked")
	 public List juede2(String sql,String id,String stime,String etime)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(sql);
		 param.add(id);
		 param.add(stime);
		 param.add(etime);
		 param.add(stime);
		 param.add(etime);
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
			 relist=query.getResults(sb.toString(),param.toArray(),null);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist;
	 }
	 /**
	  * �ж����ӵĻ����޸ĵĺ����ݿ������һ��
	  * */  
	 @SuppressWarnings("unchecked")
	 public List juede3(String sql,String name,String name1,String name2)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(sql);
		 param.add(name);
		 param.add(name1);
		 param.add(name2);
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
			 relist=query.getResults(sb.toString(),param.toArray(),null);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist;
	 }

	 
     /**   
      *   ��ʽ��double��   
      *   @param   dNumber   double   
      *   @param   iFrac   int   ����С�����λ;   
      *   @return   String   
      */   

	   public  String   RoundFracToString(double   dNumber,   int   iScale)   
	   {   
	       String   sReturn = "";   
	       NumberFormat   format = NumberFormat.getNumberInstance();   
	       if   (iScale >= 0)   
	       {   
	           format.setGroupingUsed(true);                         //�Ƿ������ʾ,��:1,400.00   
	           format.setMaximumFractionDigits(iScale);   
	           format.setMinimumFractionDigits(iScale);   
	           sReturn = format.format(dNumber);   
	       }   
	       return   sReturn;   
	   }  
	 /***
		 * 
		 * ˵�����õ�����ʱ������Сʱ��
		 * 
		 * 

		 */
	  	public int getTimesBetween(String beginTime,String endTime) throws ParseException {
			int result = 0;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 
			
			Date btime = format.parse(beginTime);
			Date etime = format.parse(endTime);
			
			long bt = btime.getTime();
			long et = etime.getTime();
			result = (int) ((et-bt)/(1000*60));
			return result;
		}
	 
	 
	 
	    /**
		 * ����ÿ�µ�����
		 * */
		public int getDaysOfMonth(String month_) {
		    Calendar cal = Calendar.getInstance();
		    cal.setTimeInMillis(System.currentTimeMillis());
		    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
		    Date d = null;
		    try {
		      d = sf.parse(month_);
		    }
		    catch (ParseException ex) {
		       
		    }
		    cal.setTime(d);
		    int month = cal.get(Calendar.MONTH) + 1;
		    int days = 0;
		    if (month != 2) {
		      if (month < 8) {
		        if (month % 2 != 0) {
		          days = 31;
		        }
		        else {
		          days = 30;
		        }
		      }
		      else {
		        if (month % 2 == 0) {
		          days = 31;
		        }
		        else {
		          days = 30;
		        }
		      }
		    }
		    else {
		    	if ( ( (GregorianCalendar) cal).isLeapYear(cal.get(Calendar.YEAR))){
		        days = 29;
		      }
		      else {
		        days = 28;
		      }
		    }
		    return days;
		} 
		  /**
		 * �õ��������ڼ��������
		 */
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
	                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);//�õ������ʵ������
	                d1.add(Calendar.YEAR, 1);
	            } while (d1.get(Calendar.YEAR) != y2);
	           
	        }
	        return days;
	        
	    }
		/**
		 * ������������Եõ���ǰ�µ�ǰһ���������»��ߺ�һ���������£�N���£�����Ϊ������ģ�
		 * ����Ϊ��ǰ��ǰ�ģ��죬��Сʱ���ȵ�
		 * (��ǰʱ���ǰNСʱ��ʱ��)
		 */
		public void beforeHours()
		{
			Date now = new Date(); 
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm s");
			String nowTime = format.format(now);
				
			java.util.Calendar c = Calendar.getInstance();
			c.add(Calendar.HOUR, -12);
			String nowTimeBefore5Hours = format.format( c.getTime());	
		}
      /**
	  * ��ȡ��ǰʱ���ǰi��
	  * */
		public String getDay(String time,int i){
			   String[] date = time.split("-"); //��Ҫת���������ַ�����ֳ�������
		       int year, month, day;
		       year = Integer.parseInt(date[0]);
		       month = Integer.parseInt(date[1]) - 1;
		       day = Integer.parseInt(date[2]);
		       GregorianCalendar d = new GregorianCalendar(year, month, day);
		       d.add(Calendar.DATE,i);//��7��
		       Date dd = d.getTime();
		       DateFormat df = DateFormat.getDateInstance();
		       String enddate = df.format(dd);
		       
		       String endmonth="",endday="";
		       String yea=enddate.substring(0,4);  		        
		       String moncoding=enddate.substring(6,7);
		       
		       if( moncoding.equals("-") ){//���� 
		    	   String mon=enddate.substring(5,6);
		    	   endmonth="0"+mon;
		    	   if(enddate.length() == 8){//���� 2007-5-1
		    		   String da=enddate.substring(7,8);
		    		   endday="0"+da; 
		    	   }else{//˫�� 2007-4-30
		    		   String da=enddate.substring(7,9);
		    		   endday=da; 
		    	   }
		       }else{//˫�� 
		    	   String mon=enddate.substring(5,7);
		    	   endmonth=mon; 
		    	   if(enddate.length() == 9){//���� 2007-10-1
		    		   String da=enddate.substring(8,9);
		    		   endday="0"+da; 
		    	   }else{//˫�� 2007-10-30
		    		   String da=enddate.substring(8,10);
		    		   endday=da; 
		    	   }
		       }
		       String stime=yea+"-"+endmonth+"-"+endday;
		       return stime;
		} 
	    
		/***
		 * 
		 * @param str
		 * @return ���ݹ��ֵõ�Ա����
		 * @throws Exception
		 */
		@SuppressWarnings("unchecked")
		public List getStafferid(String str)throws Exception{
			 Engine engine=null;
			 List relist=null;
			 List param=new ArrayList();
			 StringBuffer sb=new StringBuffer();
			 sb.append(" select stafferid from staffer where worktype= ?   ");
			 param.add(str);
			 try{
				 engine=EngineFactory.getEngine("test");
				 Query query=engine.getQuery();
	             relist=query.getResults(sb.toString(),param.toArray(),Employee_menology_mountVO.class);
			 }catch(Exception e){
				 engine.rollback();
				 log.error(e);
				 throw e;
			 }finally{
				 engine.closeEngine();
			 }
			 return relist; 
		 }
		
		
		
	    /**  
		 *   ��Google��ʾ��Ա���ţ����ţ�������ƴ����
		*/
	 @SuppressWarnings({ "unused", "unchecked" })
	public List SuggestEmployee(String str)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select  top 10  stafferID as stafferid ,[Name] as username,isnull(rtrim(cardid),'δ����') as cardid   ");
		 sb.append(" from staffer where (stafferid like ?   or rtrim(cardid) like ?   or [name] like ?  or pinyin like  ?)");
		 param.add(str+"%");
		 param.add(str+"%");
		 param.add(str+"%");
		 param.add(str+"%");
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
             relist=query.getResults(sb.toString(),param.toArray(),SuggestEmployeeVO.class);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist; 
	 }
	 /**  
	  *   ���ݣ�Ա���ţ����ţ�������ƴ����
	  *   ��ѯ��ȫ��
	  */
	 @SuppressWarnings({ "unused", "unchecked" })
	 public List SuggestEmployees(String str)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select   stafferID as stafferid ,[Name] as username,isnull(rtrim(cardid),'δ����') as cardid   ");
		 sb.append(" from staffer where (stafferid like ?   or rtrim(cardid) like ?   or [name] like ?  or pinyin like  ?)");
		 param.add(str);
		 param.add(str);
		 param.add(str);
		 param.add(str);
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
			 relist=query.getResults(sb.toString(),param.toArray(),SuggestEmployeeVO.class);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist; 
	 }
	 
	 
	 /**
	  * 
	  * @param str
	  * @return ��վ�źͷ�վ���
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	public List SuggestCardreader(String str)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select top 10 cardreaderid,shortname  from  cardreader where   (cardreaderid like ? or shortname like  ? )and cardreaderid>0  ");
		 param.add(str+"%");
		 param.add(str+"%");
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
             relist=query.getResults(sb.toString(),param.toArray(),SuggestEmployeeVO.class);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist; 
	 }
	 /**
	  * 
	  * @param str
	  * @return ��վ�źͷ�վ���
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	 public List SuggestCardreaders(String str)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select  cardreaderid,shortname  from  cardreader where   (cardreaderid like ? or shortname like  ? )and cardreaderid>0  ");
		 param.add(str);
		 param.add(str);
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
			 relist=query.getResults(sb.toString(),param.toArray(),SuggestEmployeeVO.class);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist; 
	 }
	 
	 
	 /**
	  * 
	  * @param str
	  * @return ��λ���źͶ�λ�������
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	public List SuggestLocator(String str)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select  top 10 locatorid,shortname as lshortname from locator where locatorid>0 and  locatorid like ? or shortname like ? ");
		 param.add(str+"%");
		 param.add(str+"%");
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
             relist=query.getResults(sb.toString(),param.toArray(),SuggestEmployeeVO.class);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist; 
	 }	 
	 /**
	  * 
	  * @param str
	  * @return ��λ���źͶ�λ�������
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	 public List SuggestLocators(String str)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select locatorid,shortname as lshortname from locator where locatorid>0 and  locatorid like ? or shortname like ? ");
		 param.add(str);
		 param.add(str);
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
			 relist=query.getResults(sb.toString(),param.toArray(),SuggestEmployeeVO.class);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist; 
	 }	 
	 
	 
	 /**
	  * 
	  * @return ��ȡ��վ�źͷ�վ����(���)
	  * @throws Exception
	  */
	 public List GetCardreader()throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select cardreaderid,shortname  from  cardreader   where  state='����' and cardreaderid<>0 ");
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
             relist=query.getResults(sb.toString(),param.toArray(),SuggestEmployeeVO.class);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist; 
	 }	  
	 
	 /**
	  * 
	  * @return ��ȡ��λ���źͶ�λ������(���)
	  * @throws Exception
	  */
	 public List GetLocator()throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select locatorid,shortname as lshortname from locator where locatorid<>0 ");
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
             relist=query.getResults(sb.toString(),param.toArray(),SuggestEmployeeVO.class);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist; 
	 }	  
	 
	 /**
	  * 
	  * @return ��ȡԱ����Ϣ
	  * @throws Exception
	  */
	 public List GetStaffer()throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select  distinct cardid,name as username ,worktype  from staffer ");
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
             relist=query.getResults(sb.toString(),param.toArray(),SuggestEmployeeVO.class);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist; 
	 }	  
	 /**
	  * 
	  * @return ��ȡԱ����Ϣ
	  * @throws Exception
	  */
	 public List GetStaffer1()throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select cardid,cardname  as username,cardtypename  as worktype from recog where cardid<>0 ");
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
			 relist=query.getResults(sb.toString(),param.toArray(),SuggestEmployeeVO.class);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist; 
	 }	  
	 
	 /**
	  * �ж����ӵĻ����޸ĵĺ����ݿ������һ�� add by YeZ
	  * */  
	 @SuppressWarnings("unchecked")
	 public List juede22(String sql,String id,String stime,String etime,String stimenew)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(sql);
		 param.add(id);
		 param.add(stime);
		 param.add(etime);
		 param.add(stime);
		 param.add(etime);
		 param.add(stimenew);
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
			 relist=query.getResults(sb.toString(),param.toArray(),null);
		 }catch(Exception e){
			 engine.rollback();
			 log.error(e);
			 throw e;
		 }finally{
			 engine.closeEngine();
		 }
		 return relist;
	 }
	 /**
		 * �õ��������ڼ����ʱ��
		 */
		public String getTimeBetween (String beginDate, String endDate) throws ParseException {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Date bDate = df.parse(beginDate);
			Date eDate = df.parse(endDate);
//			java.util.Date now = df.parse("2004-03-26 13:31:40");   
//			java.util.Date date=df.parse("2004-01-02 11:30:24");   
			long l=bDate.getTime()-eDate.getTime();   
			long day=l/(24*60*60*1000);   
			long hour=(l/(60*60*1000)-day*24);   
			long min=((l/(60*1000))-day*24*60-hour*60);   
			long s=(l/1000-day*24*60*60-hour*60*60-min*60); 
			String hours="";
			String mins="";
			String ss="";
			if(hour<10){
				hours = "0";
			}if(min<10){
				mins="0";
			}
			if(s<10){
				ss="0";
			}
			String times = ""+day+"��"+hours+hour+":"+mins+min+":"+ss+s+"";
	        return times;
	        
	    }
}
