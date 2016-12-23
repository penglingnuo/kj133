package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_NoSingnal;
import com.kj133.entity.vo.NoSignalVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;

public class NoSignalBO {
	
	private Logger log=Logger.getLogger(this.getClass());
	public  NoSignalBO(){
	}
	
	@SuppressWarnings("unchecked")
	public List init(Search_NoSingnal nosingnal,Pagination pagin)throws Exception{
		List list=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		sb.append("  select * from (select a.locatorid as lid,a.shortname as sname,b.lasttime as times from  ");
		sb.append("  (select * from locator where locatorid not in (select distinct locatorid from v_locatedata  ");
		sb.append("  where starttime>getdate()-? )) as a left join (select locatorid,max(starttime) as lasttime ");
		sb.append("  from v_locatedata group by locatorid ) as b on a.locatorid=b.locatorid ) as QueryTable where 1=1 ");
		String day=nosingnal.getDay(); 
		param.add(new Integer(day));
		
		String locatorid=nosingnal.getLocatorid();
		if( locatorid != null && !locatorid.equals("")){
			Global  gobal=new Global();
 		    List list1=gobal.SuggestLocators(locatorid);
 		    
			if(list1.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
				sb.append(" and lid= ? ");
				param.add(vo.getLocatorid());
			}else{//没有卡号或用户名
				sb.append(" and sname=? ");
				param.add(locatorid);
			}
//			 sb.append(" where lid= ? ");
//			 param.add(locatorid);
		}
		try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
		     list=query.getResults(sb.toString(),param.toArray(),NoSignalVO.class,pagin); 
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
	public List initPrint(Search_NoSingnal nosingnal)throws Exception{
		List list=null;
		Engine engine=null;
		List param=new ArrayList();
		StringBuffer sb=new StringBuffer();
		sb.append("  select * from (select a.locatorid as lid,a.shortname as sname,b.lasttime as times from  ");
		sb.append("  (select * from locator where locatorid not in (select distinct locatorid from v_locatedata  ");
		sb.append("  where starttime>getdate()-? )) as a left join (select locatorid,max(starttime) as lasttime ");
		sb.append("  from v_locatedata group by locatorid ) as b on a.locatorid=b.locatorid ) as QueryTable  ");
		String day=nosingnal.getDay(); 
		param.add(new Integer(day));
		
		String locatorid=nosingnal.getLocatorid();
		if( locatorid != null && !locatorid.equals("")){
			Global  gobal=new Global();
 		    List list1=gobal.SuggestLocators(locatorid);
			if(list1.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
				sb.append(" where lid= ? ");
				param.add(vo.getLocatorid());
			}else{//没有卡号或用户名
				sb.append(" where sname=? ");
				param.add(locatorid);
			}
//			 sb.append(" where lid= ? ");
//			 param.add(locatorid);
		}
		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			list=query.getResults(sb.toString(),param.toArray(),NoSignalVO.class); 
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
}
