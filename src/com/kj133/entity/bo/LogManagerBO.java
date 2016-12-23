package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_Account;
import com.kj133.entity.Search_AreaCheckList;
import com.kj133.entity.Search_AreaCheckStat;
import com.kj133.entity.Search_LogManager;
import com.kj133.entity.Search_ShuaKa;
import com.kj133.entity.Ssearch_LogManager;
import com.kj133.entity.vo.AreaCheckListVO;
import com.kj133.entity.vo.AreaCheckStatVO;
import com.kj133.entity.vo.DownWellVO;
import com.kj133.entity.vo.LogManagerVO;
public class LogManagerBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public LogManagerBO(){	
    
	}
	
	@SuppressWarnings("unchecked")
	public List init(Search_LogManager logmanager,Pagination pagin)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		 
//		String banci=areachecklist.getBanci();
		String name = logmanager.getName();
		String userid = logmanager.getUserid();
		 
		String minstime=logmanager.getMinstime();  
		String maxstime=logmanager.getMaxstime();  
		String stime=logmanager.getStime();  
		String etime=logmanager.getEtime(); 

		sb.append(" select * from (select a.Recordid,mydate,a.userid,isnull(b.oname,'用户被删除') as name,myaction,loginfo from logtable as a left join ouser b on a.userid=b.userid ");
		sb.append(" ) as QueryTable  where 1=1   "); 
		if(stime!=null && !stime.equals("")){
			sb.append(" and convert(varchar(10),mydate,20)>=? ");
			param.add(stime);
		}if(minstime!=null && !minstime.equals("")){
			sb.append(" and  convert(char(8),mydate,8)>=?  ");
			param.add(minstime);
		}if(etime!=null && !etime.equals("")){
			sb.append(" and  convert(varchar(10),mydate,20)<=? ");
			param.add(etime);
		}if(maxstime!=null && !maxstime.equals("")){
			sb.append(" and  convert(char(8),mydate,8)<=? ");
			param.add(maxstime);
		}if(userid!=null && !userid.equals("")){
			sb.append(" and userid =? ");
			param.add(userid);
		}if(name!=null && !name.equals("")){
			sb.append("  and  name =? ");
			param.add(name);
		}
		sb.append(" order by mydate desc ");
		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),LogManagerVO.class,pagin);
			engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;
		}finally{
			engine.closeEngine();
		}
		return relist;
	}	
	public List init1(Search_LogManager logmanager)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		
//		String banci=areachecklist.getBanci();
		String name = logmanager.getName();
		String userid = logmanager.getUserid();
		
		String minstime=logmanager.getMinstime();  
		String maxstime=logmanager.getMaxstime();  
		String stime=logmanager.getStime();  
		String etime=logmanager.getEtime(); 
		
		sb.append(" select * from (select a.Recordid,mydate,a.userid,isnull(b.oname,'用户被删除') as name,myaction,loginfo from logtable as a left join ouser b on a.userid=b.userid ");
		sb.append(" ) as QueryTable  where 1=1   "); 
		if(stime!=null && !stime.equals("")){
			sb.append(" and convert(varchar(10),mydate,20)>=? ");
			param.add(stime);
		}if(minstime!=null && !minstime.equals("")){
			sb.append(" and  convert(char(8),mydate,8)>=?  ");
			param.add(minstime);
		}if(etime!=null && !etime.equals("")){
			sb.append(" and  convert(varchar(10),mydate,20)<=? ");
			param.add(etime);
		}if(maxstime!=null && !maxstime.equals("")){
			sb.append(" and  convert(char(8),mydate,8)<=? ");
			param.add(maxstime);
		}if(userid!=null && !userid.equals("")){
			sb.append(" and userid =? ");
			param.add(userid);
		}if(name!=null && !name.equals("")){
			sb.append("  and  name =? ");
			param.add(name);
		}
		sb.append(" order by mydate desc ");
		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),LogManagerVO.class);
			engine.commit();
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
	  * 删除
	  * */
	 @SuppressWarnings("unchecked")
	public void Delete(Ssearch_LogManager logmanager)throws Exception{
		 Engine engine=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 String name = logmanager.getName();
		 String userid = logmanager.getUserid();
		 String minstime=logmanager.getMinstime();  
		 String maxstime=logmanager.getMaxstime();  
		 String stime=logmanager.getStime();  
		 String etime=logmanager.getEtime(); 
		 
			 
		 sb.append(" delete from logtable where recordid in(select Recordid from (select a.Recordid,mydate,a.userid,isnull(b.oname,'用户被删除') as name,myaction,loginfo from logtable as a left join ouser b on a.userid=b.userid ");
		 sb.append(" ) as deltable  where 1=1  ");
		 
		 if(stime!=null && !stime.equals("")){
			 sb.append(" and mydate>=? ");
			 param.add(stime);
		 }if(etime!=null && !etime.equals("")){
			 sb.append(" and  mydate<=? ");
			 param.add(etime);
		 }if(minstime!=null && !minstime.equals("")){
			 sb.append(" and  convert(char(8),mydate,8)>=?  ");
			 param.add(minstime);
		 }if(maxstime!=null && !maxstime.equals("")){
			 sb.append(" and  convert(char(8),mydate,8)<=? ");
			 param.add(maxstime);
		 }if(userid!=null && !userid.equals("")){
			 sb.append(" and userid =? ");
			 param.add(userid);
		 }if(name!=null && !name.equals("")){
			 sb.append("  and  name =? ");
			 param.add(name);
		 }
		 
		 
		
		 sb.append("  )");
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
