package com.basic.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.basic.entity.Search_WorkAtt;
import com.basic.entity.vo.MineVO;

public class PersonMineBO {
	
	private Logger log=Logger.getLogger(this.getClass());
	 
	 public PersonMineBO(){
		 
	 }
	
/**有参查询*/
	
	@SuppressWarnings("unchecked")
	public List getList(Search_WorkAtt workatt) throws Exception{
		  List relist=null;
		  List param=new ArrayList();
		  Engine engine=null;
		  StringBuffer sb=new StringBuffer();
		 sb.append(" SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED  ");
		 sb.append(" select a.name,a.cardid,a.[group],a.occupation,a.downtime,a.worktime,a.crname,a.starttime,a.staytime,a.info from( ");
		 sb.append(" select isnull(s.name,'未绑定')name,w.cardid,isnull(s.[group],'未绑定')[group],isnull(s.occupation,'未绑定')occupation,s.department,w.downtime, ");
		 sb.append(" substring(rtrim(100+(datediff(mi,downtime,getdate()))/60),2,2)+'小时'+substring(rtrim(100+(datediff(mi,downtime,getdate()))%60),2,2)+'分' as worktime,c.crname,le.starttime, ");
		 sb.append(" substring(rtrim(100+(datediff(mi,starttime,getdate()))/60),2,2)+'小时'+substring(rtrim(100+(datediff(mi,starttime,getdate()))%60),2,2)+'分' as staytime,ai.info ");
		 sb.append(" from workattendanceex w  ");
		 sb.append(" left join staffer s  on s.cardid=w.cardid ");
		 
		 if(workatt.getCardid() != null && !workatt.getCardid().equals("")){
   			sb.append(" and s.cardid=? ");
   			param.add(workatt.getCardid());
   			
		 }
		 
		 sb.append(" left join locatedata_everyTimeMod le on le.cardid=s.cardid and le.starttime>=getdate()-2  ");
		 sb.append(" left join cardreader c on le.cardreaderid = c.cardreaderid ");
		 sb.append(" left join alarminfo ai on ai.state=le.state ");
		 sb.append(" where w.downtime>=getdate()-2   and w.uptime is null)a,reportpopedom rp ");
		 sb.append(" where rp.department=a.department and rp.userid=? ");
		 sb.append(" order by a.downtime ");
         
         param.add(workatt.getUserid());

        
         try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
			  relist=query.getResults(sb.toString(),param.toArray(),MineVO.class);
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
