package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_Locator_Batteries;
import com.kj133.entity.vo.Locator_batteriesVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;


public class Locator_batteriesBO {
	
	private final Logger log=Logger.getLogger(this.getClass());
	public Locator_batteriesBO(){
		
	}
	
	 @SuppressWarnings("unchecked")
	public List init(Search_Locator_Batteries lbatt,Pagination pagin)throws Exception{
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 Engine engine=null;
		 String lid=lbatt.getLid(); 
		 String minstime = lbatt.getMinstime();
		 String maxstime = lbatt.getMaxstime();
		 String stime=lbatt.getStime();//截取注册时间大
//   		String s1=stime.substring(0,10);  
//   		String s2=stime.substring(11,stime.length()); 
   		String etime=lbatt.getEtime();    //截取注册时间小于
//   		String e1=etime.substring(0,10);  
//   		String e2=etime.substring(11,etime.length());  
		 sb.append(" declare @ds datetime,@de datetime,@dss datetime select @ds=convert(datetime, ? ), " );
		 sb.append(" @de=convert(datetime, ? )+1,@dss=@ds-3 select * from (select a.locatorid as locatorid, ");
		 sb.append(" shortname as shortname,'定位器电池电压低' as info,MaxTime as etime,Mintime as stime,@ds as ");
		 sb.append(" startdate,@de as enddate from( select locatorid,min(starttime) as mintime,max(endtime) as ");
		 sb.append(" maxtime from v_locatedata where starttime<@de and starttime>@dss and endtime>=@ds and state&0x40>0 ");
		 sb.append(" group by locatorid)as a, locator where a.locatorid=locator.locatorid  ) as QueryTable  where 1= 1");
		 param.add(stime);
	   	 param.add(etime);
		 if( minstime !=null && !minstime.equals("")){
	   		   sb.append(" and convert(char(8),stime,8)>= ? ");
	   		   param.add(minstime);
	   	 }if( maxstime !=null && !maxstime.equals("")){
			 sb.append(" and  convert(char(8),etime,8)< ? ");
			 param.add(maxstime);
		 }
		 
   		
   		
   		
/*   		if( lid !=null && !lid.equals("")){//定位器号不为空
   			Global  gobal=new Global();
 		    List list=gobal.SuggestLocators(lid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and locatorid=? ");
				param.add(vo.getLocatorid());
			}else{//没有卡号或用户名
				sb.append(" and shortname=? ");
				param.add(lid);
			}
//   		   sb.append(" and locatorid = ? ");
//   		   param.add(lid);
   		}*/
		 try{
			  engine=EngineFactory.getEngine("test");
			  Query query=engine.getQuery();
			  relist=query.getResults(sb.toString(),param.toArray(),Locator_batteriesVO.class,pagin);
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
	
	 public List initPrint(Search_Locator_Batteries lbatt)throws Exception{
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 Engine engine=null;
		 String lid=lbatt.getLid(); 
		 String minstime = lbatt.getMinstime();
		 String maxstime = lbatt.getMaxstime();
		 String stime=lbatt.getStime();//截取注册时间大
//   		String s1=stime.substring(0,10);  
//   		String s2=stime.substring(11,stime.length()); 
   		String etime=lbatt.getEtime();    //截取注册时间小于
//   		String e1=etime.substring(0,10);  
//   		String e2=etime.substring(11,etime.length());
		 sb.append(" declare @ds datetime,@de datetime,@dss datetime select @ds=convert(datetime, ? ), " );
		 sb.append(" @de=convert(datetime, ? )+1,@dss=@ds-3 select * from (select a.locatorid as locatorid, ");
		 sb.append(" shortname as shortname,'定位器电池电压低' as info,MaxTime as etime,Mintime as stime,@ds as ");
		 sb.append(" startdate,@de as enddate from( select locatorid,min(starttime) as mintime,max(endtime) as ");
		 sb.append(" maxtime from v_locatedata where starttime<@de and starttime>@dss and endtime>=@ds and state&0x40>0 ");
		 sb.append(" group by locatorid)as a, locator where a.locatorid=locator.locatorid  ) as QueryTable  where 1= 1");
		 param.add(stime);
	 	 param.add(etime);
		 if( minstime !=null && !minstime.equals("")){
	   		   sb.append(" and convert(char(8),stime,8)>= ? ");
	   		   param.add(minstime);
	   	 }if( maxstime !=null && !maxstime.equals("")){
			 sb.append(" and  convert(char(8),etime,8)< ? ");
			 param.add(maxstime);
		 }
		 
 		
 		
		 
	   	/*if( lid !=null && !lid.equals("")){//定位器号不为空
   			Global  gobal=new Global();
 		    List list=gobal.SuggestLocators(lid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and locatorid=? ");
				param.add(vo.getLocatorid());
			}else{//没有卡号或用户名
				sb.append(" and shortname=? ");
				param.add(lid);
			}
//   		   sb.append(" and locatorid = ? ");
//   		   param.add(lid);
   		}*/
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
			 relist=query.getResults(sb.toString(),param.toArray(),Locator_batteriesVO.class);
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
