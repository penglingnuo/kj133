package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_AreaStick;
import com.kj133.entity.vo.AreaStickVO;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;
public class AreaStickBO {
	
	    private final Logger log=Logger.getLogger(this.getClass());
		public  AreaStickBO(){
		   
	   }
	   /**
	    * 区域停留信息查询
	    * */
	     @SuppressWarnings("unchecked")
		public List init(Search_AreaStick stick,Pagination pagin)throws Exception{
	    	 Engine engine=null;
	    	 List relist=null;
	    	 List param=new ArrayList();
	    	
	    	 String cid=stick.getCid();  
	    	 String sid=stick.getSid(); 
             String gro=stick.getGro();  
             String type=stick.getType();  
             
             String minstime=stick.getMinstime();
             String maxstime=stick.getMaxstime();
             String stime=stick.getStime();//截取注册时间大于
     		 String etime=stick.getEtime();   //截取注册时间小于

     		 
	    	 StringBuffer sb=new StringBuffer();
	    	 sb.append(" select a.stafferID as sid,a.cardreaderid as cid,a.inTime as inttim,a.inlocator as inloca, ");
	    	 sb.append(" a.outtime as outcid,a.outlocator as outloca,startendinfo.info as outrea,a.stayInterval as ");
	    	 sb.append(" staty,a.Interrupttimes as times,a.ReinTime as resinti,case a.[Name] when '' then '未绑定'  ");
	    	 sb.append(" else a.[Name] end as username,case a.worktype when '' then '其他' else a.worktype end as type, ");
	    	 sb.append(" case a.[group] when '' then '未绑定' else a.[group]end as gro,isnull(shortname,'未注册或已删除') ");
	    	 sb.append(" as shname from(select   v_stayinterval.*,[Name],worktype,[group] from v_stayinterval,staffer where  ");
	    	 sb.append(" v_stayinterval.stafferID=staffer.stafferID and ( outtime>= ? and intime<convert(datetime,?)+1 and intime>dateadd(dd,-3,? )  ");
	    	 sb.append("  ");
      		 param.add(stime);
      		 param.add(etime);
      		 param.add(stime);
             
      		 if( sid != null && !sid.equals("")){//员工号
      			Global  gobal=new Global();
     		    List list2=gobal.SuggestEmployees(sid);
    			if(list2.size()>0){//根据用户名得到卡号或者直接查询卡号
    				SuggestEmployeeVO vo=(SuggestEmployeeVO)list2.get(0);
    				sb.append(" and rtrim(v_stayinterval.stafferid)=? ");
    				param.add(vo.getStafferid());
    			}else{//没有卡号或用户名
    				sb.append(" and [name]=? ");
    				param.add(sid);
    			}
//      			 sb.append(" and  rtrim(v_stayinterval.stafferid) = ?  ");
//      			 param.add(sid);
      		 }if( gro != null && !gro.equals("")){//班组
      			 sb.append(" and  staffer.[group] = ?  ");
      			 param.add(gro);
      		 }if( type != null && !type.equals("")){//职务类型
      			 sb.append(" and  staffer.worktype =? ");
      			 param.add(type);
      		 }if( minstime != null && !minstime.equals("")){//职务类型
      			 sb.append(" and  convert(char(8),outtime,8)>=? ");
      			 param.add(minstime);
      		 }if( maxstime != null && !maxstime.equals("")){//职务类型
      			 sb.append(" and  convert(char(8),intime,8)<? ");
      			 param.add(maxstime);
      		 }
      		 
      		 
      		 sb.append(" ))as a left join cardreader as b on a.cardreaderid=b.cardreaderid left join startendinfo on startendinfo.state=a.OutReason  where 1=1  ");
      		if( cid != null && !cid.equals("")){//分站号
      			Global  gobal=new Global();
     		    List list1=gobal.SuggestCardreaders(cid);
    			if(list1.size()>0){//根据用户名得到卡号或者直接查询卡号
    				SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
    				sb.append(" and rtrim(b.cardreaderid)=? ");
    				param.add(vo.getCardreaderid());
    			}else{//没有卡号或用户名
    				sb.append(" and b.shortname=? ");
    				param.add(cid);
    			}
//      			sb.append(" and  rtrim(cardreaderid) = ?  ");
//      			param.add(cid);
      		 }
      		sb.append(" order by a.intime,a.cardid,a.cardreaderid ");
      		 
	    	 try{
	    		 engine=EngineFactory.getEngine("test");
	    		 Query query=engine.getQuery();
	             relist=query.getResults(sb.toString(),param.toArray(),AreaStickVO.class,pagin);
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
	     public List initPrint(Search_AreaStick stick)throws Exception{
	    	 Engine engine=null;
	    	 List relist=null;
	    	 List param=new ArrayList();
	    	 
	    	 String cid=stick.getCid();  
	    	 String sid=stick.getSid(); 
	    	 String gro=stick.getGro();  
	    	 String type=stick.getType();  
	    	 
             String minstime=stick.getMinstime();
             String maxstime=stick.getMaxstime();
             String stime=stick.getStime();//截取注册时间大于
     		 String etime=stick.getEtime();   //截取注册时间小于

     		 
	    	 StringBuffer sb=new StringBuffer();
	    	 sb.append(" select a.stafferID as sid,a.cardreaderid as cid,a.inTime as inttim,a.inlocator as inloca, ");
	    	 sb.append(" a.outtime as outcid,a.outlocator as outloca,startendinfo.info as outrea,a.stayInterval as ");
	    	 sb.append(" staty,a.Interrupttimes as times,a.ReinTime as resinti,case a.[Name] when '' then '未绑定'  ");
	    	 sb.append(" else a.[Name] end as username,case a.worktype when '' then '其他' else a.worktype end as type, ");
	    	 sb.append(" case a.[group] when '' then '未绑定' else a.[group]end as gro,isnull(shortname,'未注册或已删除') ");
	    	 sb.append(" as shname from(select   v_stayinterval.*,[Name],worktype,[group] from v_stayinterval,staffer where  ");
	    	 sb.append(" v_stayinterval.stafferID=staffer.stafferID and ( outtime>= ? and intime<convert(datetime,?)+1 and intime>dateadd(dd,-3,? )  ");
	    	 sb.append("  ");
      		 param.add(stime);
      		 param.add(etime);
      		 param.add(stime);
             
      		if( sid != null && !sid.equals("")){//员工号
      			Global  gobal=new Global();
     		    List list2=gobal.SuggestEmployees(sid);
    			if(list2.size()>0){//根据用户名得到卡号或者直接查询卡号
    				SuggestEmployeeVO vo=(SuggestEmployeeVO)list2.get(0);
    				sb.append(" and rtrim(v_stayinterval.stafferid)=? ");
    				param.add(vo.getStafferid());
    			}else{//没有卡号或用户名
    				sb.append(" and [name]=? ");
    				param.add(sid);
    			}
//      			 sb.append(" and  rtrim(v_stayinterval.stafferid) = ?  ");
//      			 param.add(sid);
      		 }if( gro != null && !gro.equals("")){//班组
      			 sb.append(" and  staffer.[group] = ?  ");
      			 param.add(gro);
      		 }if( type != null && !type.equals("")){//职务类型
      			 sb.append(" and  staffer.worktype =? ");
      			 param.add(type);
      		 }if( minstime != null && !minstime.equals("")){//职务类型
      			 sb.append(" and  convert(char(8),outtime,8)>=? ");
      			 param.add(minstime);
      		 }if( maxstime != null && !maxstime.equals("")){//职务类型
      			 sb.append(" and  convert(char(8),intime,8)<? ");
      			 param.add(maxstime);
      		 }
      		 
      		 
      		 sb.append(" ))as a left join cardreader as b on a.cardreaderid=b.cardreaderid left join startendinfo on startendinfo.state=a.OutReason  where 1=1  ");
      		if( cid != null && !cid.equals("")){//分站号
      			Global  gobal=new Global();
     		    List list1=gobal.SuggestCardreaders(cid);
    			if(list1.size()>0){//根据用户名得到卡号或者直接查询卡号
    				SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
    				sb.append(" and rtrim(b.cardreaderid)=? ");
    				param.add(vo.getCardreaderid());
    			}else{//没有卡号或用户名
    				sb.append(" and b.shortname=? ");
    				param.add(cid);
    			}
//      			sb.append(" and  rtrim(cardreaderid) = ?  ");
//      			param.add(cid);
      		 }
      		sb.append(" order by a.intime,a.cardid,a.cardreaderid ");
	    	 try{
	    		 
	    		 engine=EngineFactory.getEngine("test");
	    		 Query query=engine.getQuery();
	    		 relist=query.getResults(sb.toString(),param.toArray(),AreaStickVO.class);
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
