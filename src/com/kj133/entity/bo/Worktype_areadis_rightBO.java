package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.vo.Worktype_areadis_rightVO;

/**
 * 工种区域分布表---区域分布
 * 
 * @author wang
 * 
 */

public class Worktype_areadis_rightBO {

	private final Logger log = Logger.getLogger(this.getClass());

	public Worktype_areadis_rightBO() {

	}

	@SuppressWarnings("unchecked")
	public List check(String atime, String aid) throws Exception {
		Engine engine = null;
		List relist = null;
		List param = new ArrayList();
		StringBuffer sb = new StringBuffer();

		sb.append(" select 工种 worktype,sum(次数) cishu,count(*) mans from( ");
		sb.append(" select 工种,姓名,count(*) 次数 from(  ");
		sb.append(" select bb.areaname 区域名称,aa.*,cc.ShortName 进入方向,dd.ShortName 离开方向 from ( ");
		sb.append(" select a.areaid as 区域号,a.cardid as 卡号,a.enterreader,a.leavereader,b.stafferid as 员工编号,b.[name] as 姓名,b.worktype as 工种,b.[group] as 组别,a.starttime as 进入区域时间, ");
		sb.append(" a.endtime as 离开区域时间,c.downtime as 入井时间,c.uptime as 升井时间  ");
		sb.append(" from arealocatedata a,staffer b,workattendanceex c where a.cardid=b.cardid and c.stafferid=b.stafferid ");
		sb.append(" and a.starttime<=? and (a.endtime >=? or a.endtime is null) and c.downtime<=? and (c.uptime>=? or c.uptime is null) ");
		param.add(atime);
		param.add(atime);
		param.add(atime);
		param.add(atime);
		sb.append(" and a.areaid=? ");
		param.add(aid);
		sb.append(" ) aa left join v_areainfo bb on aa.区域号=bb.AreaID ");
		sb.append(" left join (select * from cardreader where cardreaderid<>0) cc on aa.enterreader=cc.cardreaderid ");
		sb.append(" left join (select * from cardreader where cardreaderid<>0) dd on aa.leavereader=dd.cardreaderid ");

		sb.append(" ) aa group by 工种,姓名 ");
		sb.append(" ) bb group by 工种 ");

		
		try {
			engine = EngineFactory.getEngine("test");
			Query query = engine.getQuery();
			relist = query.getResults(sb.toString(), param.toArray(),
			Worktype_areadis_rightVO.class);
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
