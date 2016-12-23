package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_manamount_query;
import com.kj133.entity.vo.Manamount_queryVO;


public class Manamount_queryBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public Manamount_queryBO(){	
    
	}
	
	@SuppressWarnings("unchecked")
	public List init(Search_manamount_query manamount_query)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		String an = manamount_query.getAreaname();
		String stime=manamount_query.getStime();  
		String etime=manamount_query.getEtime(); 

		sb.append("select a.recordtime,a.peopleCount,b.caijuesum from areadata as a,v_areainfo as b where a.areaid=b.AreaID and b.areaname=? and recordtime>? and recordtime<? order by recordtime ");
		param.add(an);
		param.add(stime);
		param.add(etime);

		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),Manamount_queryVO.class);
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
