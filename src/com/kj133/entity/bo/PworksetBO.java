package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import com.kj133.entity.Search_Pworkset;
import com.kj133.entity.Staffer;
import com.kj133.entity.WorkattendanceEx;
import com.kj133.entity.vo.PworksetVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;

public class PworksetBO {


	/**
	 * 员工考勤设置
	 */
	
	private Logger log=Logger.getLogger(this.getClass());
	public PworksetBO(){
		
	}
	
	
    /***
     * 
     * @param mou
     * @return 查询
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
//	public List init(Search_Pworkset mou)throws Exception{
	public List init()throws Exception{
		List list=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
	
		sb.append(" select stafferid,[name],cardid,worktype,pinyin,[group] from staffer ");
	   
	    sb.append(" order by stafferid ");
		try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
		     list=query.getResults(sb.toString(),param.toArray(),PworksetVO.class); 
		     engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;			
		}finally{
			 engine.closeEngine();
		}
		return list;
	}
	public List init1(Search_Pworkset mou)throws Exception{
		List list=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
			
		sb.append(" select stafferid,[name],cardid,worktype,pinyin,[group] from staffer where 1=1 ");
		
		if(mou.getEm() !=null && !mou.getEm().equals("")){
			Global  gobal=new Global();
 		    List list1=gobal.SuggestEmployees(mou.getEm());
			if(list1.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
				sb.append(" and stafferid=? ");
				param.add(vo.getStafferid());
			}else{//没有卡号或用户名
				sb.append(" and [name]=? ");
				param.add(mou.getEm());
			}

	    }if(mou.getWorktype() != null && !mou.getWorktype().equals("")){
	    	sb.append(" and worktype  = ? ");
	    	param.add(mou.getWorktype());
	    }
		sb.append(" order by stafferid ");
		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			list=query.getResults(sb.toString(),param.toArray(),PworksetVO.class); 
			engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;			
		}finally{
			engine.closeEngine();
		}
		return list;
	}
	public List siteinit(String sid)throws Exception{
		List list=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		
//		sb.append(" select stafferid,[name],cardid,worktype,pinyin,[group] from staffer where 1=1 ");
		sb.append(" select a.stafferid,name,IDType,Appointid [ID],case when IDType='分站' then b.shortname else c.shortname end shortnamea,AppointMinute from stafferworkset a  ");
		sb.append(" left join  cardreader b on a.Appointid=b.cardreaderid  ");
		sb.append(" left join  locator c on a.Appointid=c.locatorid  ");
//		sb.append(" left join  staffer e on a.stafferid=e.stafferid order by a.stafferid ");
		sb.append(" left join  staffer e on a.stafferid=e.stafferid where a.stafferid=? order by a.stafferid ");
		param.add(sid);

//		if(mou.getSid() !=null && !mou.getSid().equals("")){
//			sb.append(" and stafferid= ?");
//			param.add(mou.getSid());
//		}if(mou.getWorktype() != null && !mou.getWorktype().equals("")){
//			sb.append(" and worktype  = ? ");
//			param.add(mou.getWorktype());
//		}
//		sb.append(" order by a.stafferid ");
		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			list=query.getResults(sb.toString(),param.toArray(),PworksetVO.class); 
			engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;			
		}finally{
			engine.closeEngine();
		}
		return list;
	}
	public List siteinit1()throws Exception{
		List list=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		
//		sb.append(" select stafferid,[name],cardid,worktype,pinyin,[group] from staffer where 1=1 ");
		sb.append(" select a.stafferid,name,IDType,Appointid [ID],case when IDType='分站' then b.shortname else c.shortname end shortnamea,AppointMinute from stafferworkset a  ");
		sb.append(" left join  cardreader b on a.Appointid=b.cardreaderid  ");
		sb.append(" left join  locator c on a.Appointid=c.locatorid  ");
		sb.append(" left join  staffer e on a.stafferid=e.stafferid order by a.stafferid ");

		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			list=query.getResults(sb.toString(),param.toArray(),PworksetVO.class); 
			engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;			
		}finally{
			engine.closeEngine();
		}
		return list;
	}
	
	
	public List timeinit1()throws Exception{
		List list=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		List relist = new ArrayList();
	
		
		sb.append(" select a.stafferid,name,s.worktype,case when isnull(Bantypeid,0)=1 then '四六制' when isnull(Bantypeid,0)=2 then '大班制' else '三八制' end bantype,MinTime,MaxTime  from workovertime a ");
		sb.append(" left join staffer s on a.stafferid=s.stafferid ");
		

		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			list=query.getResults(sb.toString(),param.toArray(),PworksetVO.class); 

			engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;			
		}finally{
			engine.closeEngine();
		}
		return list;
	}
	public List timeinit(String sid)throws Exception{
		List list=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		List relist = new ArrayList();
		
		
		sb.append(" select a.stafferid,name,s.worktype,case when isnull(Bantypeid,0)=1 then '四六制' when isnull(Bantypeid,0)=2 then '大班制' else '三八制' end bantype,MinTime,MaxTime  from workovertime a ");
		sb.append(" left join staffer s on a.stafferid=s.stafferid where a.stafferid = ? ");
		param.add(sid);
		
		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			list=query.getResults(sb.toString(),param.toArray(),PworksetVO.class); 
			
			engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;			
		}finally{
			engine.closeEngine();
		}
		return list;
	}
	
	
	
    /**
     * LocatorBO
     * 删除
     */
    public void deleteSite(String sid,String type,String id)throws Exception{
   	 Engine engine=null;
   	 WorkattendanceEx locator=null;
   	 StringBuffer sb=new StringBuffer();
//   	 sb.append(" delete workattendanceex where stafferid=? and downtime=? ");
 		 sb.append(" delete stafferworkset where stafferid=? and IDType=? and AppointId=? ");
 		 List param=new ArrayList();
 		 param.add(sid);
 		 param.add(type);
 		 param.add(id);
   	 try{
   		 engine=EngineFactory.getEngine("test");
   		 engine.executeSpecialSQL(sb.toString(),param.toArray());
   		 
//   		 locator=(WorkattendanceEx)engine.load(WorkattendanceEx.class,id);
//   		 engine.delete(locator);
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
     * LocatorBO
     * 删除
     */
    public void deleteTime(String sid)throws Exception{
    	Engine engine=null;
    	WorkattendanceEx locator=null;
    	StringBuffer sb=new StringBuffer();
    	List param=new ArrayList();
//   	 sb.append(" delete workattendanceex where stafferid=? and downtime=? ");
    	sb.append(" delete workovertime where stafferid=? ");
    	param.add(sid);
    	try{
    		engine=EngineFactory.getEngine("test");
    		engine.executeSpecialSQL(sb.toString(),param.toArray());
    		
//   		 locator=(WorkattendanceEx)engine.load(WorkattendanceEx.class,id);
//   		 engine.delete(locator);
    		engine.commit();
    	}catch(Exception e){
    		engine.rollback();
    		log.error(e);
    		throw e;
    	}finally{
    		engine.closeEngine();
    	}
    }
	 
	 /***
	  * 
	  * @param id
	  * @param miniworktime  
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	 public void addSite(String id,String name1,String name2,String name3)throws Exception{
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" insert into stafferworkset(stafferid,IDType,AppointID,AppointMinute) values (?,?,?,?) ");
//		   sb.append(" insert into attendanceSet(stafferid,miniworktime) values (?,?) ");
		 param.add(id);
		 param.add(name1);
		 param.add(name2);
		 param.add(name3);
		 
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
	 /***
	  * 
	  * @param id
	  * @param miniworktime  
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	 public void addTime(Search_Pworkset pw,String id)throws Exception{
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 String min = pw.getMintime();
		 String max = pw.getMaxtime();
		 String bantype = pw.getBantype();
		 String a = "0";
		 String b = "1";
		 String c = "2";
		 
		 sb.append(" insert into workovertime(stafferid,bantypeid,mintime,maxtime) values (?,?,?,?) ");
//		 sb.append(" insert into attendanceSet(stafferid,miniworktime) values (?,?) ");
		 param.add(id);
		 if(bantype.equals("三八制")){
			 param.add(a);
		 }if(bantype.equals("四六制")){
			 param.add(b);
		 }if(bantype.equals("大班制")){
			 param.add(c);
		 }
		 
		 param.add(min);
		 param.add(max);
		 
		 
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
	 public void addgzTime(Search_Pworkset pw)throws Exception{
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 WordlibBO wo = new WordlibBO();
		 String min = pw.getMintime();
		 String max = pw.getMaxtime();
		 String bantype = pw.getBantype();
		 String worktype = pw.getWorktype();
		 String a = "0";
		 String b = "1";
		 String c = "2";
		 if(worktype.equals("全部工种")){
			 
//			 wo.getstaffer1();
			 sb.append(" insert into workovertime(stafferid,bantypeid,mintime,maxtime)  ");
			 sb.append(" select stafferid,?,?,? from ( ");
			 if(bantype.equals("三八制")){
				 param.add(a);
			 }if(bantype.equals("四六制")){
				 param.add(b);
			 }if(bantype.equals("大班制")){
				 param.add(c);
			 }
			 param.add(min);
			 param.add(max);
			 sb.append(" select * from (select stafferid,pinyin,[name],cardid,[group],worktype from staffer) b ");
			 
			 sb.append(" ) a ");
		 
		 }else{
//			 sb.append(" delete workovertime where stafferid in (select stafferid from ( ");
//			 sb.append(" select * from (select stafferid,pinyin,[name],cardid,[group],worktype from staffer) b ");
////			 sb.append(" where worktype like '队级干部%' ");
//			 sb.append(" where worktype =? ");
//			 param.add(worktype);
//			 sb.append(" ) a ) ");
//			 wo.getstaffer(worktype);
			 sb.append(" insert into workovertime(stafferid,bantypeid,mintime,maxtime)  ");
			 sb.append(" select stafferid,?,?,? from ( ");
			 if(bantype.equals("三八制")){
				 param.add(a);
			 }if(bantype.equals("四六制")){
				 param.add(b);
			 }if(bantype.equals("大班制")){
				 param.add(c);
			 }
			 param.add(min);
			 param.add(max);
			 sb.append(" select * from (select stafferid,pinyin,[name],cardid,[group],worktype from staffer) b ");
			 sb.append(" where worktype = ? ");
			 param.add(worktype);
			 sb.append(" ) a ");
			
		} 
		 
		 /*if(worktype.equals("全部工种")){
			 
			 sb.append(" update workovertime set mintime=?,maxtime=? ");
			 param.add(min);
			 param.add(max);
		 
		 }else{
			 sb.append(" update workovertime set bantypeid=?,mintime=?,maxtime=? from (select stafferid from staffer where worktype=?) a where workovertime.stafferid=a.stafferid ");
			 if(bantype.equals("三八制")){
				 param.add(a);
			 }if(bantype.equals("四六制")){
				 param.add(b);
			 }if(bantype.equals("大班制")){
				 param.add(c);
			 }
			 param.add(min);
			 param.add(max);
			 param.add(worktype);
			 
		 }*/
		 ////////////////////////////////////////////////////
		 
//		 sb.append(" insert into workovertime(stafferid,bantypeid,mintime,maxtime) values (?,?,?,?) ");
////		 sb.append(" insert into attendanceSet(stafferid,miniworktime) values (?,?) ");
//		 param.add(id);
//		 if(bantype.equals("三八制")){
//			 param.add(a);
//		 }if(bantype.equals("四六制")){
//			 param.add(b);
//		 }if(bantype.equals("大班制")){
//			 param.add(c);
//		 }
//		 
//		 param.add(min);
//		 param.add(max);
		 
		 
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
	 /***
	  * 
	  * @param id
	  * @param miniworktime  
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	public void add(String id,String miniworktime)throws Exception{
		   Engine engine=null;
		   List param=new ArrayList();
		   StringBuffer sb=new StringBuffer();
		   sb.append(" insert into attendanceSet(stafferid,minworktime) values (?,?) ");
//		   sb.append(" insert into attendanceSet(stafferid,miniworktime) values (?,?) ");
		   param.add(id);
		   param.add(miniworktime);
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
	  * 
	  * @param miniworktime
	  * @param stafferid
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	public void updateTime(Search_Pworkset pw,String id)throws Exception{
		   Engine engine=null;
		   List param=new ArrayList();
		   StringBuffer sb=new StringBuffer();
		   String min = pw.getMintime();
		   String max = pw.getMaxtime();
		   String bantype = pw.getBantype();		   
		   String a = "0";
		   String b = "1";
		   String c = "2";
		   sb.append(" update workovertime set bantypeid=?,mintime=?,maxtime=? where stafferid=? ");
			 if(bantype.equals("三八制")){
				 param.add(a);
			 }if(bantype.equals("四六制")){
				 param.add(b);
			 }if(bantype.equals("大班制")){
				 param.add(c);
			 }
			 param.add(pw.getMintime());
			 param.add(pw.getMaxtime());
			 param.add(id);
//		   param.add(miniworktime);
//		   param.add(stafferid);
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
}
