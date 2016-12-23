package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;


import com.kj133.entity.Wordlib;

import com.kj133.entity.vo.PageRangeDownrightVO;
public class PageRangeDownrightBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public PageRangeDownrightBO(){	
    
	}
	
	@SuppressWarnings("unchecked")
	public List init(String areaname,String areaorder)throws Exception{
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		 
		sb.append(" select ? areaorder,AreaName,a.type,pointID as [ID],b.shortname from AreaDetail as a,(select '·ÖÕ¾' as type,shortname,cardreaderid as [ID] from cardreader union all select '¶¨Î»Æ÷' as type,shortname,locatorid as [ID] from locator)as b where a.type=b.type and a.pointID=b.[ID] and AreaName=? ");
		param.add(areaorder);
		param.add(areaname);

		
		try{
		   
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sb.toString(),param.toArray(),PageRangeDownrightVO.class);
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
