package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;



import com.kj133.entity.vo.Overfreight_call_downVO;


/**
 * ÇøÓò³¬Ê±Ã÷Ï¸
 * @author wang
 *
 */

public class Overfreight_call_downBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public Overfreight_call_downBO(){	
    
	}
	

	@SuppressWarnings("unchecked")
	public List check(String aid,String btime,String atime) throws Exception {
		Engine engine = null;
		List relist = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select aa.areaid,aa.recordtime,aa.maxsum,aa.caijuesum,aa.peoplecount,aa.minercount,aa.allchaomans,aa.chaomans,bb.areaname aname from ( ");
		sb.append(" select a.areaid,a.recordtime,b.maxsum,b.caijuesum, ");
		sb.append(" a.peoplecount,a.minercount, ");
		sb.append(" case when a.peoplecount-b.maxsum<0 then 0 else a.peoplecount-b.maxsum end as allchaomans, ");
		sb.append(" case when a.minercount-b.caijuesum<0 then 0 else a.minercount-b.caijuesum end as chaomans from areadata a,v_areainfo b where a.areaid=b.areaid and (a.peoplecount>b.maxsum or a.minercount>b.caijuesum) "); 
		sb.append(" and a.areaid=? ");
		param.add(aid);
		sb.append(" and a.recordtime>=? ");
		param.add(btime);
		sb.append(" and a.recordtime<=? ");
		param.add(atime);
		sb.append(" ) aa left join v_areainfo bb on aa.areaid=bb.areaid ");

		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),Overfreight_call_downVO.class);
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
