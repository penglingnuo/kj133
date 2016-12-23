package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.vo.Abnormity_work_downVO;


/**
 * 区域超时明细
 * @author wang
 *
 */

public class Abnormity_work_downBO {
    
	private final  Logger log=Logger.getLogger(this.getClass());
	
	public Abnormity_work_downBO(){	
    
	}
	

	@SuppressWarnings("unchecked")
	public List check(String userid,String stafferid,String downtime) throws Exception {
		Engine engine = null;
		List relist = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();
		
		

		sb.append(" select a.stafferid,b.name,b.Cardid ");
		sb.append(",case when IDType='分站' then '分站：'+c.ShortName  else '定位器：'+d.ShortName end locus,convert(varchar(19),requiretime,20) requiretime,convert(varchar(19),reachtime,20) reachtime ");
		sb.append(",convert(varchar(19),a.downtime,20) downtime,convert(varchar(19),uptime,20) uptime,b.[group] gro,b.worktype,b.occupation ");
		sb.append(" from (select * from errorwork where userid=? and stafferid=? and convert(varchar(19),downtime,20)=? ) a ");
		param.add(userid);
		param.add(stafferid);
		param.add(downtime);
		sb.append(" left join staffer b on a.stafferid=b.stafferid ");
		sb.append(" left join Cardreader c on a.AppointID=c.Cardreaderid ");
		sb.append(" left join locator d on a.AppointID=d.locatorid ");

		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),Abnormity_work_downVO.class);
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
