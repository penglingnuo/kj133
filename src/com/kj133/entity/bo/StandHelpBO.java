package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_StandHelp;
import com.kj133.entity.vo.StandHelpVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;
public class StandHelpBO {
   private static final Logger log=Logger.getLogger(StandHelpBO.class);
   
   public StandHelpBO(){
	   
   }
   
   /**
    * 获取查询的结果集
    * */
   @SuppressWarnings("unchecked")
public List getList(Search_StandHelp standhelp,Pagination pagin)throws Exception {
	   List relist=null;
	   List param=new ArrayList();
	   Engine engine=null;
	   StringBuffer sb=new StringBuffer();

	   String cid=standhelp.getCid();
	   String stime=standhelp.getStime(); 
	   String etime=standhelp.getEtime(); 
	   String minstime=standhelp.getMinstime(); 
	   String maxstime=standhelp.getMaxstime(); 
	
	   sb.append(" declare @ds datetime,@de datetime,@dss datetime select @ds=convert(datetime, ? ),@de=convert(datetime,? )+1,@dss=@ds-3  ");
	   sb.append(" select * from (select a.cardreaderid as cardreaderid,shortname as shortname,info as info,mintime as mintime,maxtime as maxtime,  ");
	   sb.append(" alarmtimes as alarmtimes from( select cardreaderid,state,min(recordtime) as mintime,max(recordtime) as maxtime,sum(1) as alarmtimes  ");
	   sb.append(" from v_readerdata  where state&0xF0>0 and recordtime>=@ds and recordtime<@de    ");
	   param.add(stime);
	   param.add(etime);
	   if( minstime != null && !minstime.equals("")){
		   sb.append(" and  convert(char(8),recordtime,8)>=? ");
		   param.add(minstime);
	   }if( maxstime != null && !maxstime.equals("")){
		   sb.append(" and  convert(char(8),recordtime,8)< ? ");
		   param.add(maxstime);
	   }
	   sb.append(" group by cardreaderid,state ) as a,cardreader,readeralarminfo  where a.cardreaderid=cardreader.cardreaderid and a.state=readeralarminfo.state   ");
	   sb.append(" ) as QueryTable  where 1= 1  ");

	   
	   if( cid != null && !cid.equals("")){
		   Global  gobal=new Global();
		    List list=gobal.SuggestCardreaders(cid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and cardreaderid=? ");
				param.add(vo.getCardreaderid());
			}else{//没有卡号或用户名
				sb.append(" and shortname=? ");
				param.add(cid);
			}
//		   sb.append(" and  cardreaderid = ? ");
//		   param.add(cid);
	   }
	   
	   try{
		  
		   engine=EngineFactory.getEngine("test");
		   Query query=engine.getQuery();
		   relist=query.getResults(sb.toString(),param.toArray(),StandHelpVO.class,pagin);
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
   /**
    * 获取查询的结果集===姚桥矿
    * */
   @SuppressWarnings("unchecked")
public List getCardreaderList(Search_StandHelp standhelp,Pagination pagin)throws Exception {
	   List relist=null;
	   List param=new ArrayList();
	   Engine engine=null;
	   StringBuffer sb=new StringBuffer();

	   String cid=standhelp.getCid();
	   String stime=standhelp.getStime(); 
	   String etime=standhelp.getEtime(); 
	
	   sb.append(" select cr.CardReaderId,cr.CRName,ad.AreaName,convert(varchar(20),MAX(vr.Recordtime),120) Recordtime  ");
	   sb.append(" from CardReader cr,AreaDetail ad,readeralarminfo ra,v_readerdata vr  ");
	   sb.append(" where cr.CardReaderId=ad.PointID and type='分站'  and vr.state&0xF0>0  ");
	   sb.append(" and vr.cardreaderid=cr.CardReaderId and ad.PointID=vr.cardreaderid  ");
	   
	   sb.append(" and vr.Recordtime>=? ");
	   param.add(stime);
	   sb.append(" and vr.Recordtime<=? ");
	   param.add(etime);

	   if( cid != null && !cid.equals("")){
		   Global  gobal=new Global();
		    List list=gobal.SuggestCardreaders(cid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and vr.cardreaderid=? ");
				param.add(vo.getCardreaderid());
			}else{//没有卡号或用户名
				sb.append(" and vr.shortname=? ");
				param.add(cid);
			}
//		   sb.append(" and  cardreaderid = ? ");
//		   param.add(cid);
	   }
	   
	   sb.append(" group by cr.CardReaderId,cr.CRName,ad.AreaName  ");

	   try{
		  
		   engine=EngineFactory.getEngine("test");
		   Query query=engine.getQuery();
		   relist=query.getResults(sb.toString(),param.toArray(),StandHelpVO.class,pagin);
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
   
   public List getListPrint(Search_StandHelp standhelp)throws Exception {
	   List relist=null;
	   List param=new ArrayList();
	   Engine engine=null;
	   StringBuffer sb=new StringBuffer();
	   
	   String cid=standhelp.getCid();
	   String stime=standhelp.getStime(); 
	   String etime=standhelp.getEtime(); 
	   String minstime=standhelp.getMinstime(); 
	   String maxstime=standhelp.getMaxstime(); 
	
	   sb.append(" declare @ds datetime,@de datetime,@dss datetime select @ds=convert(datetime, ? ),@de=convert(datetime,? )+1,@dss=@ds-3  ");
	   sb.append(" select * from (select a.cardreaderid as cardreaderid,shortname as shortname,info as info,mintime as mintime,maxtime as maxtime,  ");
	   sb.append(" alarmtimes as alarmtimes from( select cardreaderid,state,min(recordtime) as mintime,max(recordtime) as maxtime,sum(1) as alarmtimes  ");
	   sb.append(" from v_readerdata  where state&0xF0>0 and recordtime>=@ds and recordtime<@de    ");
	   param.add(stime);
	   param.add(etime);
	   if( minstime != null && !minstime.equals("")){
		   sb.append(" and  convert(char(8),recordtime,8)>=? ");
		   param.add(minstime);
	   }if( maxstime != null && !maxstime.equals("")){
		   sb.append(" and  convert(char(8),recordtime,8)< ? ");
		   param.add(maxstime);
	   }
	   sb.append(" group by cardreaderid,state ) as a,cardreader,readeralarminfo  where a.cardreaderid=cardreader.cardreaderid and a.state=readeralarminfo.state   ");
	   sb.append(" ) as QueryTable  where 1= 1  ");
	   
	   
	   
	   
	   if( cid != null && !cid.equals("")){
		   Global  gobal=new Global();
		    List list=gobal.SuggestCardreaders(cid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sb.append(" and cardreaderid=? ");
				param.add(vo.getCardreaderid());
			}else{//没有卡号或用户名
				sb.append(" and shortname=? ");
				param.add(cid);
			}
	   }
	   
	   try{
		   
		   engine=EngineFactory.getEngine("test");
		   Query query=engine.getQuery();
		   relist=query.getResults(sb.toString(),param.toArray(),StandHelpVO.class);
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
