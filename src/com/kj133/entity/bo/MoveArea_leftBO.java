package com.kj133.entity.bo;


import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Activerange;
import com.kj133.entity.Search_MoveArea;
import com.kj133.entity.vo.MoveArea_particularVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;


public class MoveArea_leftBO {
	
	private final Logger log=Logger.getLogger(this.getClass());
	public MoveArea_leftBO(){
		
	}
    /**
     * 活动区域查询
     * */
	@SuppressWarnings("unchecked")
	public List init(String userid,Search_MoveArea movearea)throws Exception{
		List relist=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from (select stafferID ,cardname,cardreaderid ,crname , ");
		sb.append("  appeartimes ,staytime,mapid ,mapname  from activerange where operator= ? "); 
		param.add(userid);
		
		sb.append("  ) as QueryTable where 1=1 ");

		
		String sid=movearea.getSid();  
		String maxcount=movearea.getMaxcount();  
		String mincount=movearea.getMincount();  
		String stime=movearea.getStime(); 
		String etime=movearea.getEtime(); 
	    
		if( maxcount!=null && !maxcount.equals("") ){
			sb.append(" and appeartimes >  ? ");
			param.add(maxcount);
		}if( mincount!=null && !mincount.equals("") ){
			sb.append(" and appeartimes <  ? ");
			param.add(mincount);
		}
		try{
			sb.append(" order by QueryTable.cardreaderid ");
			engine=EngineFactory.getEngine("test");
			Global  gobal=new Global();
	  	    List list=gobal.SuggestEmployees(sid);
	  	   if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
	  		SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
	  		engine.executeProcedureCall("{ call calactiverange(?,?,?,?) }",new Object[]{userid,vo.getStafferid(),stime,etime});
	  		}else{
	  			engine.executeProcedureCall("{ call calactiverange(?,?,?,?) }",new Object[]{userid,sid,stime,etime});
	  		}
			
			Query query=engine.getQuery();
		    relist=query.getResults(sb.toString(),param.toArray(),Activerange.class);
		    engine.commit();
		}catch(Exception e){
			engine.rollback();
		    log.error(e);
		    throw e;
		}
		return relist;
	}
	public List init1(String userid,Search_MoveArea movearea,Pagination pagin)throws Exception{
		List relist=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		sb.append(" select * from (select stafferID ,cardname,cardreaderid ,crname , ");
		sb.append("  appeartimes ,staytime,mapid ,mapname  from activerange where operator= ? "); 
		param.add(userid);
		
		sb.append("  ) as QueryTable where 1=1 ");
		
		
		String sid=movearea.getSid();  
		String maxcount=movearea.getMaxcount();  
		String mincount=movearea.getMincount();  
		String stime=movearea.getStime(); 
		String etime=movearea.getEtime(); 
		
		if( maxcount!=null && !maxcount.equals("") ){
			sb.append(" and appeartimes >  ? ");
			param.add(maxcount);
		}if( mincount!=null && !mincount.equals("") ){
			sb.append(" and appeartimes <  ? ");
			param.add(mincount);
		}
		try{
			sb.append(" order by QueryTable.cardreaderid ");
			engine=EngineFactory.getEngine("test");
			Global  gobal=new Global();
			List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				engine.executeProcedureCall("{ call calactiverange(?,?,?,?) }",new Object[]{userid,vo.getStafferid(),stime,etime});
			}else{
				engine.executeProcedureCall("{ call calactiverange(?,?,?,?) }",new Object[]{userid,sid,stime,etime});
			}
			
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),Activerange.class,pagin);
			engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;
		}
		return relist;
	}
	
    /**
     *活动区域的详细信息 
     *  */	
      @SuppressWarnings("unchecked")
	public List particular(String name,String cid)throws Exception{
    	  List relist=null;
    	  List param=new ArrayList();
    	  Engine engine=null;
    	  StringBuffer sb=new StringBuffer();
    	  sb.append(" select * from (select cardreaderid as cardreaderid,a.locatorid as lid,isnull(b.shortname,'未注册或已删除') as shortname,");
    	  sb.append(" convert(varchar(19),starttime,20) as stime,convert(varchar(19),endtime,20) as etime from activerangedata as a left join locator as b on a.locatorid=");
    	  sb.append(" b.locatorid where operator= ? ) as QueryTable where cardreaderid=? ");
    	  param.add(name);
    	  param.add(cid);
    	  
    	  try{
    		  engine=EngineFactory.getEngine("test");
    		  Query query=engine.getQuery();
    	      relist=query.getResults(sb.toString(),param.toArray(),MoveArea_particularVO.class);
    	      engine.commit();
    	  }catch(Exception e){
    		  engine.rollback();
    		  log.error(e);
    		  throw e;
    	  }
    	  return relist;
      }
      public List particular1(String name,String cid,Pagination pagin)throws Exception{
    	  List relist=null;
    	  List param=new ArrayList();
    	  Engine engine=null;
    	  StringBuffer sb=new StringBuffer();
    	  sb.append(" select * from (select cardreaderid as cardreaderid,a.locatorid as lid,isnull(b.shortname,'未注册或已删除') as shortname,");
    	  sb.append(" convert(varchar(19),starttime,20) as stime,convert(varchar(19),endtime,20) as etime from activerangedata as a left join locator as b on a.locatorid=");
    	  sb.append(" b.locatorid where operator= ? ) as QueryTable where cardreaderid=? ");
    	  param.add(name);
    	  param.add(cid);
    	  
    	  try{
    		  engine=EngineFactory.getEngine("test");
    		  Query query=engine.getQuery();
    		  relist=query.getResults(sb.toString(),param.toArray(),MoveArea_particularVO.class,pagin);
    		  engine.commit();
    	  }catch(Exception e){
    		  engine.rollback();
    		  log.error(e);
    		  throw e;
    	  }
    	  return relist;
      }
    
	     
}
