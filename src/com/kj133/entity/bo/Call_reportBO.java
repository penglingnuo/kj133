package com.kj133.entity.bo;

import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_Call_Report;
import com.kj133.entity.vo.Call_reportVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;
public class Call_reportBO {
	private final Logger log=Logger.getLogger(this.getClass());
    public Call_reportBO(){
    	
    }
    /**
     * 呼救历史查询
     * */
    @SuppressWarnings("unchecked")
	public List init(Search_Call_Report call,Pagination pagin)throws Exception{
        List relist=null;
        Engine engine=null; 
        List param=new ArrayList();
        StringBuffer sb=new StringBuffer();
        
        String gro=call.getGro(); 
        String sid=call.getSid(); 
        String minstime=call.getMinstime();//截取注册时间大
        String maxstime=call.getMaxstime();//截取注册时间大
        String stime=call.getStime();//截取注册时间大
   		String etime=call.getEtime();    //截取注册时间小于
        sb.append(" declare @ds datetime,@de datetime,@dss datetime select @ds=convert(datetime,?), ");
        sb.append(" @de=convert(datetime,?)+1,@dss=@ds-3 select * from (select a.stafferID as stafferid, ");
        sb.append(" [Name] as username,[group] as gro,worktype as type,'呼救' as info,MaxTime as etime,Mintime as  ");
        sb.append(" stime,@ds as startdate,@de as enddate from( select cardid,min(starttime) as mintime,max(endtime)  ");
        sb.append(" as maxtime,stafferID from v_locatedata where starttime<@de and starttime>@dss and endtime>=@ds  ");
        sb.append(" and state&0x08>0 group by cardid,stafferID )as a,staffer  where a.stafferID=staffer.stafferID ");
        sb.append(" ) as QueryTable  where  1=1 ");
   		param.add(stime);
   		param.add(etime);

   		if( sid !=null && !sid.equals("")){
   			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and stafferid=? ");
				param.add(vo.getStafferid());
			}else{//没有卡号或用户名
				sb.append(" and username=? ");
				param.add(sid);
			}
//   			sb.append(" and stafferid = ? ");
//   			param.add(sid);
   		}if( gro != null && !gro.equals("")){
   			sb.append(" and gro = ? ");
   			param.add(gro);
   		}if( minstime != null && !minstime.equals("")){
   			sb.append(" and  convert(char(8),etime,8)>= ? ");
   			param.add(minstime);
   		}if( maxstime != null && !maxstime.equals("")){
   			sb.append(" and  convert(char(8),stime,8)<? ");
   			param.add(maxstime);
   		}
   		  
   		
   		
        try{
        	engine=EngineFactory.getEngine("test");
        	Query query=engine.getQuery();
        	relist=query.getResults(sb.toString(),param.toArray(),Call_reportVO.class,pagin);
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
    public List initPrint(Search_Call_Report call)throws Exception{
    	List relist=null;
    	Engine engine=null; 
    	List param=new ArrayList();
    	StringBuffer sb=new StringBuffer();
    	
        String gro=call.getGro(); 
        String sid=call.getSid(); 
        String minstime=call.getMinstime();//截取注册时间大
        String maxstime=call.getMaxstime();//截取注册时间大
        String stime=call.getStime();//截取注册时间大
   		String etime=call.getEtime();    //截取注册时间小于
        sb.append(" declare @ds datetime,@de datetime,@dss datetime select @ds=convert(datetime,?), ");
        sb.append(" @de=convert(datetime,?)+1,@dss=@ds-3 select * from (select a.stafferID as stafferid, ");
        sb.append(" [Name] as username,[group] as gro,worktype as type,'呼救' as info,MaxTime as etime,Mintime as  ");
        sb.append(" stime,@ds as startdate,@de as enddate from( select cardid,min(starttime) as mintime,max(endtime)  ");
        sb.append(" as maxtime,stafferID from v_locatedata where starttime<@de and starttime>@dss and endtime>=@ds  ");
        sb.append(" and state&0x08>0 group by cardid,stafferID )as a,staffer  where a.stafferID=staffer.stafferID ");
        sb.append(" ) as QueryTable  where  1=1 ");
   		param.add(stime);
   		param.add(etime);

   		if( sid !=null && !sid.equals("")){
   			Global  gobal=new Global();
 		    List list=gobal.SuggestEmployees(sid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and stafferid=? ");
				param.add(vo.getStafferid());
			}else{//没有卡号或用户名
				sb.append(" and username=? ");
				param.add(sid);
			}
   		}if( gro != null && !gro.equals("")){
   			sb.append(" and gro = ? ");
   			param.add(gro);
   		}if( minstime != null && !minstime.equals("")){
   			sb.append(" and  convert(char(8),etime,8)>= ? ");
   			param.add(minstime);
   		}if( maxstime != null && !maxstime.equals("")){
   			sb.append(" and  convert(char(8),stime,8)<? ");
   			param.add(maxstime);
   		}
    	
    	try{
    		
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    		relist=query.getResults(sb.toString(),param.toArray(),Call_reportVO.class);
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
