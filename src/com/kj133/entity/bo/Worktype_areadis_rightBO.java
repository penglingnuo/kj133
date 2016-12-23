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
 * ��������ֲ���---����ֲ�
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

		sb.append(" select ���� worktype,sum(����) cishu,count(*) mans from( ");
		sb.append(" select ����,����,count(*) ���� from(  ");
		sb.append(" select bb.areaname ��������,aa.*,cc.ShortName ���뷽��,dd.ShortName �뿪���� from ( ");
		sb.append(" select a.areaid as �����,a.cardid as ����,a.enterreader,a.leavereader,b.stafferid as Ա�����,b.[name] as ����,b.worktype as ����,b.[group] as ���,a.starttime as ��������ʱ��, ");
		sb.append(" a.endtime as �뿪����ʱ��,c.downtime as �뾮ʱ��,c.uptime as ����ʱ��  ");
		sb.append(" from arealocatedata a,staffer b,workattendanceex c where a.cardid=b.cardid and c.stafferid=b.stafferid ");
		sb.append(" and a.starttime<=? and (a.endtime >=? or a.endtime is null) and c.downtime<=? and (c.uptime>=? or c.uptime is null) ");
		param.add(atime);
		param.add(atime);
		param.add(atime);
		param.add(atime);
		sb.append(" and a.areaid=? ");
		param.add(aid);
		sb.append(" ) aa left join v_areainfo bb on aa.�����=bb.AreaID ");
		sb.append(" left join (select * from cardreader where cardreaderid<>0) cc on aa.enterreader=cc.cardreaderid ");
		sb.append(" left join (select * from cardreader where cardreaderid<>0) dd on aa.leavereader=dd.cardreaderid ");

		sb.append(" ) aa group by ����,���� ");
		sb.append(" ) bb group by ���� ");

		
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
