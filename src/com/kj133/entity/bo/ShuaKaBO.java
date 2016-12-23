package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;
import com.kj133.entity.Search_ShuaKa;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.vo.ShuaKaVO;

public class ShuaKaBO {
private final Logger log=Logger.getLogger(this.getClass());
	
	public ShuaKaBO(){		
	}

	/**
	 * 验卡记录查询
	 * */
	@SuppressWarnings("unchecked")
	public List init(Search_ShuaKa shuak,Pagination pagin)throws Exception {
		List relist=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
	    sb.append(" select * from (select staffer.cardid as cardid,isnull(staffer.stafferid,'未绑定') as stafferid,[name] as username, ");
	    sb.append(" [group] as gro,worktype as worktype,convert(varchar(200),recorddate,20) as recorddate,cardreaderid as cardreaderid,locatorid as locatorid  ");
	    sb.append(" from checkattendance left join staffer on checkattendance.stafferid=staffer.stafferid ) as QueryTable   where  ");
	    sb.append(" recorddate>= ? and recorddate<convert(datetime,? )+1   ");
	     
		String sid=shuak.getSid();    System.out.println("sid=="+sid);
		String gro=shuak.getGro();    
		String type=shuak.getType();  
		
		String stime=shuak.getStime();  
		String s1=stime.substring(0,10);  
		String s2=stime.substring(11,stime.length());  
     
		String etime=shuak.getEtime();  
		String e1=etime.substring(0,10);  
		String e2=etime.substring(11,etime.length());  
		
		param.add(s1);
		param.add(e1);
		
		if( sid!=null && !sid.equals("")){//员工号
			sb.append(" and rtrim(stafferid) like  ? ");
			param.add(sid+"%");
		}if( gro!=null && !gro.equals("")){//组 别
			sb.append(" and  gro= ? ");
			param.add(gro);
		}if( type!=null && !type.equals("")){//工 种
			sb.append(" and worktype= ?");
			param.add(type);
		 } 
	    try{
	    	sb.append(" and convert(char(8),recorddate,8)>=? and convert(char(8),recorddate,8)<=?  ");
	    	param.add(s2);
	    	param.add(e2);
	   
	    	engine=EngineFactory.getEngine("test");
	    	Query query=engine.getQuery();
	    	relist=query.getResults(sb.toString(),param.toArray(),ShuaKaVO.class,pagin);
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
