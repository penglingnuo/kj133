package com.basic.entity.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.basic.entity.Search_WorkAtt;
import com.basic.entity.vo.WorkAttAreaAddVO;
import com.basic.entity.vo.WorkAttAreaVO;
import com.basic.entity.vo.WorkAttLeaderAreaVO;
import com.basic.entity.vo.WorkAttLeaderVO;

public class WorkAttLeaderBO {

	private Logger log=Logger.getLogger(this.getClass());
	 
	 public WorkAttLeaderBO(){
		 
	 }
	 
	 /**根据员工卡号和入井时间查询员工信息*/
		
		@SuppressWarnings("unchecked")
		public List getStafferList(String cardid,String downtime) throws Exception{
			List relist=null;
			  List param=new ArrayList();
			  Engine engine=null;
			  StringBuffer sb=new StringBuffer();
			 sb.append(" select top 1 st.stafferID,st.Name,st.CardID,st.Department,st.occupation,convert(varchar,we.Downtime,20) Downtime, ");
			 sb.append(" substring(rtrim(100+(datediff(mi,we.Downtime,getdate()))/60),2,2)+'时'+ ");
	         sb.append(" substring(rtrim(100+(datediff(mi,we.Downtime,getdate()))%60),2,2)+'分' as worktime ");
	         sb.append(" from WorkattendanceEx we ,staffer st ");
	         sb.append(" where st.CardID=we.cardid ");
	         
	         if(downtime!= null && !downtime.equals("")){
			         sb.append(" and we.Downtime=? ");
			         param.add(downtime);
	         }
	         
	         if(cardid!= null && !cardid.equals("")){
	      			sb.append(" and we.cardid=? ");
	      			param.add(cardid);
	      			
	     	}

	         try{
				  engine=EngineFactory.getEngine("test");
				  Query query=engine.getQuery();
//				  System.out.println("sb=" +sb.toString());
				  relist=query.getResults(sb.toString(),param.toArray(),WorkAttLeaderAreaVO.class);
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
		
		/**井下领导统计*/
		
		@SuppressWarnings("unchecked")
		public List count(Search_WorkAtt workatt) throws Exception{
			  List relist=null;
			  List param=new ArrayList();
			  Engine engine=null;
			  StringBuffer sb=new StringBuffer();
			sb.append(" SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED  ");
			sb.append(" select count(a.name) total from( ");
	        sb.append(" select isnull(s.name,'未绑定')name,w.cardid,isnull(s.[group],'未绑定')[group],isnull(s.occupation,'未绑定')occupation,s.department,w.downtime, ");
	        sb.append(" substring(rtrim(100+(datediff(mi,downtime,getdate()))/60),2,2)+'时'+ ");
	        sb.append(" substring(rtrim(100+(datediff(mi,downtime,getdate()))%60),2,2)+'分' as worktime,c.crname,le.starttime, ");
	        sb.append(" case when  datediff(mi,downtime,getdate()) >= (wo.MinTime) then '2'  else '3' end as overstate, ");
	        sb.append(" case when (le.state&0x08)=0 then 1 else 0  end as state ");
	        sb.append(" from workattendanceex w ");
	        sb.append(" left join staffer s  on s.cardid=w.cardid ");
	        sb.append(" left join Workovertime wo on  wo.stafferid=w.StafferID  ");
	        sb.append(" left join locatedata_everyTimeMod le on le.cardid=s.cardid and le.starttime>=getdate()-2  ");
	        sb.append(" left join cardreader c on le.cardreaderid = c.cardreaderid ");
	        sb.append(" where w.downtime>=getdate()-2   and w.uptime is null and s.occupation<>'工人')a,reportpopedom rp ");
	        sb.append(" where rp.department=a.department and rp.userid=? ");
	        param.add(workatt.getUserid());

	        try{
				  engine=EngineFactory.getEngine("test");
				  Query query=engine.getQuery();
				  relist=query.getResults(sb.toString(),param.toArray(),WorkAttLeaderVO.class);
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
	
	/**无参查询*/
	
	@SuppressWarnings("unchecked")
	public List init(Search_WorkAtt workatt, Pagination pagination) throws Exception{
		  List relist=null;
		  List param=new ArrayList();
		  Engine engine=null;
		  StringBuffer sb=new StringBuffer();
		sb.append(" SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED  ");
		sb.append(" select a.name,a.cardid,a.[group],a.occupation,convert(varchar(20),a.downtime,120)downtime,a.worktime,a.crname,convert(varchar(20),a.starttime,120)starttime, a.overstate,a.state from( ");
        sb.append(" select isnull(s.name,'未绑定')name,w.cardid,isnull(s.[group],'未绑定')[group],isnull(s.occupation,'未绑定')occupation,s.department,w.downtime, ");
        sb.append(" substring(rtrim(100+(datediff(mi,downtime,getdate()))/60),2,2)+'时'+ ");
        sb.append(" substring(rtrim(100+(datediff(mi,downtime,getdate()))%60),2,2)+'分' as worktime,c.crname,le.starttime, ");
        sb.append(" case when  datediff(mi,downtime,getdate()) >= (wo.MinTime) then '2'  else '3' end as overstate, ");
        sb.append(" case when (le.state&0x08)=0 then 1 else 0  end as state ");
        sb.append(" from workattendanceex w ");
        sb.append(" left join staffer s  on s.cardid=w.cardid ");
        sb.append(" left join Workovertime wo on  wo.stafferid=w.StafferID  ");
        sb.append(" left join locatedata_everyTimeMod le on le.cardid=s.cardid and le.starttime>=getdate()-2  ");
        sb.append(" left join cardreader c on le.cardreaderid = c.cardreaderid ");
        sb.append(" where w.downtime>=getdate()-2   and w.uptime is null and s.occupation<>'工人')a,reportpopedom rp ");
        sb.append(" where rp.department=a.department and rp.userid=? ");
        sb.append(" order by state,overstate  ");
        param.add(workatt.getUserid());

        try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
			  relist=query.getResults(sb.toString(),param.toArray(),WorkAttLeaderVO.class,pagination);
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
	
/**有参查询*/
	
	@SuppressWarnings("unchecked")
	public List getList(Search_WorkAtt workatt, Pagination pagination) throws Exception{
		  List relist=null;
		  List param=new ArrayList();
		  Engine engine=null;
		  StringBuffer sb=new StringBuffer();
		  String idname=workatt.getCardid();
		  String cardidid=null;
		  if(idname!=null&&!idname.equals("")){
			  String[] idnames=idname.split("--");
			  cardidid=idnames[0];
		  }
		 sb.append(" SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED  ");
		 sb.append(" select a.name,a.cardid,a.[group],a.occupation,convert(varchar(20),a.downtime,120)downtime,a.worktime,a.crname,convert(varchar(20),a.starttime,120)starttime, a.overstate,a.state from( ");
        sb.append(" select isnull(s.name,'未绑定')name,w.cardid,isnull(s.[group],'未绑定')[group],isnull(s.occupation,'未绑定')occupation,s.department,w.downtime, ");
        sb.append(" substring(rtrim(100+(datediff(mi,downtime,getdate()))/60),2,2)+'时'+ ");
        sb.append(" substring(rtrim(100+(datediff(mi,downtime,getdate()))%60),2,2)+'分' as worktime,c.crname,le.starttime, ");
        sb.append(" case when  datediff(mi,downtime,getdate()) >= (wo.MinTime) then '2'  else '3' end as overstate, ");
        sb.append(" case when (le.state&0x08)=0 then 1 else 0  end as state ");
        sb.append(" from workattendanceex w ");
        sb.append(" left join staffer s  on s.cardid=w.cardid ");
        if(workatt.getCardid() != null && !workatt.getCardid().equals("")){
 			sb.append(" and s.cardid=? ");
 			param.add(cardidid);
 			
        }if(workatt.getGroup() != null && workatt.getGroup().length() >0 ){
			sb.append(" and  s.[group] = ?");
			param.add(workatt.getGroup());
		  }
        
        sb.append(" left join Workovertime wo on  wo.stafferid=w.StafferID  ");
        sb.append(" left join locatedata_everyTimeMod le on le.cardid=s.cardid and le.starttime>=getdate()-2  ");
        sb.append(" left join cardreader c on le.cardreaderid = c.cardreaderid ");
        sb.append(" where w.downtime>=getdate()-2   and w.uptime is null and s.occupation<>'工人')a,reportpopedom rp ");
        sb.append(" where rp.department=a.department and rp.userid=? ");
        sb.append(" order by state,overstate  ");
        param.add(workatt.getUserid());

       
        try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
			  relist=query.getResults(sb.toString(),param.toArray(),WorkAttLeaderVO.class,pagination);
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
	
/**导出Execl*/
	
	@SuppressWarnings("unchecked")
	public List getListExecl(Search_WorkAtt workatt) throws Exception{
		  List relist=null;
		  List param=new ArrayList();
		  Engine engine=null;
		  StringBuffer sb=new StringBuffer();
		  String idname=workatt.getCardid();
		  String cardidid=null;
		  if(idname!=null&&!idname.equals("")){
			  String[] idnames=idname.split("--");
			  cardidid=idnames[0];
		  }
		 sb.append(" SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED  ");
		 sb.append(" select a.name,a.cardid,a.[group],a.occupation,convert(varchar(20),a.downtime,120)downtime ,a.worktime,a.crname,convert(varchar(20),a.starttime,120)starttime, a.overstate,a.state from( ");
        sb.append(" select isnull(s.name,'未绑定')name,w.cardid,isnull(s.[group],'未绑定')[group],isnull(s.occupation,'未绑定')occupation,s.department,w.downtime, ");
        sb.append(" substring(rtrim(100+(datediff(mi,downtime,getdate()))/60),2,2)+'时'+ ");
        sb.append(" substring(rtrim(100+(datediff(mi,downtime,getdate()))%60),2,2)+'分' as worktime,c.crname,le.starttime, ");
        sb.append(" case when  datediff(mi,downtime,getdate()) >= (wo.MinTime) then '2'  else '3' end as overstate, ");
        sb.append(" case when (le.state&0x08)=0 then 1 else 0  end as state ");
        sb.append(" from workattendanceex w ");
        sb.append(" left join staffer s  on s.cardid=w.cardid ");
        if(workatt.getCardid() != null && !workatt.getCardid().equals("")){
 			sb.append(" and s.cardid=? ");
 			param.add(cardidid);
 			
        }if(workatt.getGroup() != null && workatt.getGroup().length() >0 ){
			sb.append(" and  s.[group] = ?");
			param.add(workatt.getGroup());
		  }
        
        sb.append(" left join Workovertime wo on  wo.stafferid=w.StafferID  ");
        sb.append(" left join locatedata_everyTimeMod le on le.cardid=s.cardid and le.starttime>=getdate()-2  ");
        sb.append(" left join cardreader c on le.cardreaderid = c.cardreaderid ");
        sb.append(" where w.downtime>=getdate()-2   and w.uptime is null and s.occupation<>'工人')a,reportpopedom rp ");
        sb.append(" where rp.department=a.department and rp.userid=? ");
        sb.append(" order by state,overstate  ");
        param.add(workatt.getUserid());

       
        try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
			  relist=query.getResults(sb.toString(),param.toArray(),WorkAttLeaderVO.class);
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

	
	/** 轨迹列表 */
	@SuppressWarnings("unchecked")
	public List getAll(String cardid,String downtime) throws Exception{
		  List relist=null;
		  List param=new ArrayList();
		  Engine engine=null;
		  StringBuffer sb=new StringBuffer();
		 sb.append(" SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED  ");
//		 sb.append(" select convert(varchar(20),vl.starttime,120) starttime,'' as endtime, ");
//        sb.append(" substring(rtrim(100+(datediff(mi,starttime,getdate()))/60),2,2)+'时'+ ");
//        sb.append(" substring(rtrim(100+(datediff(mi,starttime,getdate()))%60),2,2)+'分' as stayinterval,cr.crname  ");
//        sb.append(" from locatedata_everyTimeMod vl ");
//        sb.append(" left join cardreader cr on vl.cardreaderid = cr.cardreaderid ");
//        sb.append(" where  vl.starttime<=getdate() ");
//        
//        if(downtime!= null && !downtime.equals("")){
//		         sb.append(" and vl.starttime>=? ");
//		         param.add(downtime);
//        }
//        
//        if(cardid!= null && !cardid.equals("")){
//     			sb.append(" and vl.cardid=? ");
//     			param.add(cardid);
//     			
//    	}
//        
//        sb.append(" union ");
        sb.append(" select convert(varchar(20),vl.intime,120) startTime,convert(varchar(20),vl.outtime,120) endtime,stayinterval,cr.crname crname from v_stayinterval vl ");
        sb.append(" left join cardreader cr on vl.cardreaderid = cr.cardreaderid ");
        sb.append(" where  vl.intime<=getdate()+0.2 ");
        
        if(downtime!= null && !downtime.equals("")){
	         sb.append(" and vl.intime>=? ");
	         param.add(downtime);
        }

        if(cardid!= null && !cardid.equals("")){
  			sb.append(" and vl.cardid=? ");
  			param.add(cardid);
  			
        }
        
        sb.append(" order by startTime ");
        try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
//			  System.out.println("sb=" +sb.toString());
			  relist=query.getResults(sb.toString(),param.toArray(),WorkAttAreaVO.class);
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
	/** 轨迹列表-实时 */
	@SuppressWarnings("unchecked")
	public List getAllAdd(String cardid,String downtime) throws Exception{
		  List relist=null;
		  List param=new ArrayList();
		  Engine engine=null;
		  StringBuffer sb=new StringBuffer();
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		  Calendar cal=Calendar.getInstance();
		  cal.setTime(df.parse(downtime)); 
		  cal.add(Calendar.MINUTE, -60);
		  String dt=df.format(cal.getTime());
		 sb.append(" SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED  ");
		 sb.append(" select cr.cardreaderid,cr.crname, ");
		 sb.append(" (select convert(varchar(20),MAX(endtime),120) from v_locatedata ld ");
         sb.append(" where ld.cardreaderid=vl.cardreaderid and endtime<=getdate()+0.2 ");
         sb.append(" and ld.starttime>='"+downtime+"' and ld.cardid='"+cardid+"' )as endtime ");
         sb.append(" from locatedata_everyTimeMod vl ");
         sb.append(" left join cardreader cr on vl.cardreaderid = cr.cardreaderid ");
         sb.append(" where  vl.starttime<=getdate()+0.2 ");
         
         if(dt!= null && !dt.equals("")){
		         sb.append(" and vl.starttime>=? ");
		         param.add(dt);
         }
         
         if(cardid!= null && !cardid.equals("")){
      			sb.append(" and vl.cardid=? ");
      			param.add(cardid);
     	}
        
         try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
//			  System.out.println("sb=" +sb.toString());
			  relist=query.getResults(sb.toString(),param.toArray(),WorkAttAreaAddVO.class);
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

}
