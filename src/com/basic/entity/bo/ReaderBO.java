package com.basic.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.basic.entity.vo.CardReaderVo;
import com.kj133.entity.CardReader1;

public class ReaderBO {
	
	private Logger log=Logger.getLogger(this.getClass());
    public ReaderBO(){
    }
    
    public List init(String cid,String checkreader)throws Exception{
    	return init(cid, checkreader, null);
    }
    /**
     * 查询所有分站
     * */
   @SuppressWarnings("unchecked")
	public List init(String cid,String checkreader,Pagination pagination)throws Exception{
   	List relist=null;
   	List param=new ArrayList();
   	Engine engine=null;
   	StringBuffer sb=new StringBuffer();

   	sb.append(" select * from (select CardReaderId,CRName,r.ShortName,r.webx x,r.weby y,convert(varchar,r.RegDate,20) RegDate," );
   	sb.append("r.Mapid,ignoretimes,ignorelocator,locator.shortname sname,locatorignoretimes,r.state," );
   	sb.append("convert(varchar,r.modifydate,20) modifydate,case r.ground when 1 then '井上设备' when 0 then '井下设备' else '不区分' end as ground,");
    sb.append("case checkreader when 1 then '是' else '否' end as checkreader,");
   	sb.append("case when useantenna&1>0 then 'A' else '' end + case when useantenna&2>0 ");
   	sb.append("then 'B' else '' end + case when useantenna&4>0 then 'C' else '' end as useantenna1,");
   	sb.append("useantenna,case IfAlarm when 1 then '使用' else '不使用'  end as IfAlarm ");
   	sb.append("from cardreader as r left join locator on r.ignorelocator=locator.locatorid ");
   	sb.append("where cardreaderID<>0  ");
   	
    if(cid!= null && !cid.equals("")){
    	sb.append("and  r.cardreaderID=? ");
        param.add(cid);
    }
    if(checkreader!= null && !checkreader.equals("")){
    	sb.append("and  r.state=? ");
        param.add(checkreader);
    }
   	
   	
   	sb.append(" ) as QueryTable  order by  QueryTable.state desc, QueryTable.CardReaderId  ");
   	 
        try{
   		engine=EngineFactory.getEngine("test");
   		Query query=engine.getQuery();
   		if(pagination != null)
   			relist=query.getResults(sb.toString(),param.toArray(),CardReader1.class,pagination);
   		else
   			relist = query.getResults(sb.toString(),param.toArray(),CardReader1.class);
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

   //导出Execl
   @SuppressWarnings("unchecked")
	public List initPrint()throws Exception{
  	List relist=null;
  	List param=new ArrayList();
  	Engine engine=null;
  	StringBuffer sb=new StringBuffer();

  	sb.append(" select * from (select CardReaderId,CRName,r.ShortName,r.webx x,r.weby y,r.RegDate,r.Mapid,ignoretimes,ignorelocator,locator.shortname sname,locatorignoretimes,r.state,r.modifydate,case r.ground when 1 then '井上设备' when 0 then '井下设备' else '不区分' end as ground,case checkreader when 1 then '是' else '否' end as checkreader,case when useantenna&1>0 then 'A' else '' end + case when useantenna&2>0 then 'B' else '' end + case when useantenna&4>0 then 'C' else '' end as useantenna1,useantenna,case IfAlarm when 1 then '使用' else '不使用'  end as IfAlarm from cardreader as r left join locator on r.ignorelocator=locator.locatorid where cardreaderID<>0 ");
  	sb.append(" ) as QueryTable  order by  QueryTable.state desc, QueryTable.CardReaderId  ");
  	 
       try{
  		engine=EngineFactory.getEngine("test");
  		Query query=engine.getQuery();
  	    relist=query.getResults(sb.toString(),param.toArray(),CardReaderVo.class);
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
