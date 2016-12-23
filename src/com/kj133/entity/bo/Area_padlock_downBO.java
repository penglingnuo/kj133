package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.vo.Area_padlock_downVO;


/**
 * ÇøÓò³¬Ê±Ã÷Ï¸
 * @author wang
 *
 */

public class Area_padlock_downBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public Area_padlock_downBO(){	
    
	}

	@SuppressWarnings("unchecked")
	public List check(String aid,String btime,String atime) throws Exception {
		Engine engine = null;
		List relist = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();

		sb.append(" select a.AreaID aid,b.AreaName aname,c.name,a.Cardid cid,c.[group] gro,c.worktype,a.starttime,a.Stayinterval stl,a.endtime from ");
		sb.append(" (select * from arealocatedata where areaid=? and starttime>=? and starttime<=? and (endtime>=? or endtime is null) ");
		
		param.add(aid);
		param.add(btime);
		param.add(atime);
		param.add(atime);
		

		sb.append(" ) a left join AreaInfo b on a.AreaID=b.AreaID ");
		sb.append(" left join Staffer c on a.cardid=c.cardid ");

		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),Area_padlock_downVO.class);
			engine.commit();
		} catch (Exception e) {
			engine.rollback();
			log.error(e);
			throw e;
		} finally {
			engine.closeEngine();
		}
		return relist;
	}


}
