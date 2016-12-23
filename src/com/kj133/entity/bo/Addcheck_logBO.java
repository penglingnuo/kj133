package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Addcheck_log;
import com.kj133.entity.Locator;
import com.kj133.entity.Search_addcheck_log;
import com.kj133.entity.WorkattendanceEx;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.util.Global;


public class Addcheck_logBO {
   
	private Logger log=Logger.getLogger(this.getClass());
	
	public Addcheck_logBO(){
    	 
     }
	
	  /**编辑定位器
	   *
	   */
     @SuppressWarnings("unchecked")
	public List init(Search_addcheck_log locator)throws Exception{
    	 List relist=null;
    	 List param=new ArrayList();
    	 StringBuffer sb=new StringBuffer();
    	 Engine engine=null;
    	 String stime=locator.getStime(); 
    	 String etime=locator.getEtime(); 
    	 String sid = locator.getStafferid();
//    	 sb.append(" select * from (select LocatorId,LName,ShortName,x,y,z,RegDate,Mapid,modifydate,case ground when 1 then '井上设备' when 0 then '井下设备' else '不区分' end as ground,state from Locator where LocatorID<>0 ");
//    	 sb.append(" ) as QueryTable  where 1=1 ");
    	 sb.append(" select * from (select a.stafferid,convert(varchar(19),a.downtime,20) downtime,convert(varchar(19),a.uptime,20) uptime," +
    	 		"substring(rtrim(100+datepart(dd,uptime-downtime)-1),2,2)+'天'+substring(rtrim(100+datepart(hh,uptime-downtime)),2,2)+'小时'" +
    	 		"+substring(rtrim(100+datepart(n,uptime-downtime)),2,2)+'分钟' as worktime,b.[name],b.worktype gro,b.occupation from " +
    	 		"(select stafferid,downtime,uptime from workattendanceex where ifadd=1 and Downtime>=? and DownTime<=?) as a " +
    	 		"left join staffer as b on  a.stafferid=b.stafferid ");
    	 param.add(stime);
    	 param.add(etime);
    	 sb.append(" ) as QueryTable  ");
    	 if(sid !=null && !sid.equals("")){
//    		 sb.append(" where  rtrim(stafferid) =?  ");
//    		 param.add(sid);
    		 Global  gobal=new Global();
  		    List list=gobal.SuggestEmployees(sid);
 			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
 				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
 				sb.append(" where  rtrim(stafferid) =? ");
 				param.add(vo.getStafferid());
 			}else{//没有卡号或用户名
 				sb.append(" where  rtrim([name]) =? ");
 				param.add(sid);
 			}
    		 
    	 }
 
    	 try{
    		 
    		 engine=EngineFactory.getEngine("test");
    		 Query query=engine.getQuery();
             relist=query.getResults(sb.toString(),param.toArray(),Addcheck_log.class);
//             System.out.println("sb="+sb.toString());
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
     
	  /**查询考勤信息listNew
	   *add by YZh
	   */
   @SuppressWarnings("unchecked")
	public List initnew(Search_addcheck_log locator,Pagination pagin)throws Exception{
  	 List relist=null;
  	 List param=new ArrayList();
  	 StringBuffer sb=new StringBuffer();
  	 Engine engine=null;
  	 String stime=locator.getStime(); 
  	 String etime=locator.getEtime(); 
  	 String staffName = locator.getStafferid();//员工编号和姓名
  	 String sid=null;//员工编号
  	 if(staffName!=null && !staffName.equals("")){
  		 String []sns=staffName.split("--");
  		 sid=sns[0];
  	 }
  	 String banname = locator.getBanname();
  	 String userid = locator.getUserid();
  	 String department= locator.getGro();
  	sb.append("select stafferid,cardid,name,tb.department,gro,occupation,downtime,uptime,worktime,banname,modifyuser, modifytime,modifyreason from ");
  	sb.append("(select b.shunxu,b.cardid,a.stafferid,b.[name],b.department,b.worktype gro,b.occupation,downtime,uptime,worktime,banname,a.modifyuser, a.modifytime,modifyreason from " );
	sb.append("(select stafferid,downtime,uptime,ban_name as banname,we.banid as banid,substring(rtrim(100 + datepart(dd, uptime - downtime) - 1), 2, 2) + '天' +   "  );
	sb.append(" substring(rtrim(100 + datepart(hh, uptime - downtime)), 2, 2) + '小时'+substring(rtrim(100 + datepart(n, uptime - downtime)), 2, 2) + '分钟' as worktime,modifyuser, modifytime,modifyreason  ");
	sb.append("from workattendanceex we,bantype bt  where bt.ban_id=we.banid ");
	if( stime!=null && !stime.equals("")){
		//sb.append("and downtime between dateadd(d,-1,?) and dateadd(d,+1,?) and CONVERT(VARCHAR(10),we.kqtime,20) =? ");
		sb.append("and downtime between dateadd(d,-1,?) and dateadd(d,+1,?) and CONVERT(VARCHAR(10),we.kqtime,20) between ? and ? ");
		param.add(stime);
		param.add(etime);
		param.add(stime);
		param.add(etime);
	}
	if( sid!=null && !sid.equals("")){
		Global  gobal=new Global();
		    List list=gobal.SuggestEmployees(sid);
		if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
			SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
			sb.append(" and rtrim(stafferid)=? ");
			param.add(vo.getStafferid());
		}else{//没有卡号或用户名
			sb.append(" and name=? ");
			param.add(sid);
		}
	}
	sb.append(" ) as a left join staffer as b on  a.stafferid=b.stafferid  ");
	if( banname!=null && !banname.equals("")){
		sb.append("  and banname=? ");
		param.add(banname);
	}
	if( department!=null && !department.equals("")){
		sb.append("  and department=? ");
		param.add(department);
	}
	
	sb.append(") as tb ,reportpopedom rp where rp.department=tb.department and rp.userid='"+userid+"' ");
	sb.append("order by tb.shunxu,tb.department,tb.stafferid");
	
  	 try{
  		 
  		 engine=EngineFactory.getEngine("test");
  		 Query query=engine.getQuery();
         relist=query.getResults(sb.toString(),param.toArray(),Addcheck_log.class,pagin);
         //System.out.println("sb="+sb.toString());
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
     
 	public void save1(String stime,String etime,String sid)throws Exception{
   	 List param=new ArrayList();
   	 StringBuffer sb=new StringBuffer();
   	 Engine engine=null;
	 sb.append(" insert into workattendanceex(cardid,Stafferid,Downtime,uptime,CardState,DownCardreaderid,downlocatorid,UpCardreaderid,Uplocatorid,ifadd,kqtime) values(?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?) ");
	 param.add("");
	 param.add(sid);
	 param.add(stime);
	 param.add(etime);
	 param.add("0");
	 param.add("0");
	 param.add("0");
	 param.add("0");
	 param.add("0");
	 param.add("1");
	 param.add(stime);

   	 try{
   		 
   		 engine=EngineFactory.getEngine("test");
   		engine.executeSpecialSQL(sb.toString(),param.toArray());
         engine.commit();
   	 }catch(Exception e){
   		 engine.rollback();
   		 log.error(e);
   		 throw e;
   	 }finally{
   		 engine.closeEngine();
   	 }
    }
     
 	public void save2(String stime,String etime,String sid,int banid,int bantypeid,String modifyreason,String userid,String cardid)throws Exception{
 	   	 List param=new ArrayList();
 	   	 StringBuffer sb=new StringBuffer();
 	   	 Engine engine=null;
 		 sb.append(" insert into workattendanceex(cardid,Stafferid,Downtime,uptime,CardState,DownCardreaderid,downlocatorid," +
 		 		"UpCardreaderid,Uplocatorid,ifadd,kqtime,banid,bantypeid,modifyreason,modifyuser,modifytime) " +
 		 		"values(?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?,getdate()) ");
 		 param.add(cardid);
 		 param.add(sid);
 		 param.add(stime);
 		 param.add(etime);
 		 param.add("0");
 		 param.add("0");
 		 param.add("0");
 		 param.add("0");
 		 param.add("0");
 		 param.add("1");
 		 param.add(stime);
 		 param.add(banid);
 		 param.add(bantypeid);
 		 param.add(modifyreason);
 		 param.add(userid);

 	   	 try{
 	   		 
 	   		 engine=EngineFactory.getEngine("test");
 	   		engine.executeSpecialSQL(sb.toString(),param.toArray());
 	         engine.commit();
 	   	 }catch(Exception e){
 	   		 engine.rollback();
 	   		 log.error(e);
 	   		 throw e;
 	   	 }finally{
 	   		 engine.closeEngine();
 	   	 }
 	    }
 	     
 	//增加前增加
 	public void save3(String stime,String etime,String sid,int banid,int bantypeid,String modifyreason,String userid,String cardid)throws Exception{
	   	 List param=new ArrayList();
	   	 StringBuffer sb=new StringBuffer();
	   	 Engine engine=null;
		 sb.append(" insert into OldWorkattendanceEx(cardid,Stafferid,Downtime,uptime,CardState,DownCardreaderid,downlocatorid," +
		 		"UpCardreaderid,Uplocatorid,ifadd,kqtime,banid,bantypeid,modifyreason,modifyuser,modifytime) " +
		 		"values(?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?,getdate()) ");
		 param.add(cardid);
		 param.add(sid);
		 param.add(stime);
		 param.add(etime);
		 param.add("0");
		 param.add("0");
		 param.add("0");
		 param.add("0");
		 param.add("0");
		 param.add("1");
		 param.add(stime);
		 param.add(banid);
		 param.add(bantypeid);
		 param.add(modifyreason);
		 param.add(userid);

	   	 try{
	   		 
	   		 engine=EngineFactory.getEngine("test");
	   		engine.executeSpecialSQL(sb.toString(),param.toArray());
	         engine.commit();
	   	 }catch(Exception e){
	   		 engine.rollback();
	   		 log.error(e);
	   		 throw e;
	   	 }finally{
	   		 engine.closeEngine();
	   	 }
	    }
 	
 	//修改------------------
	public void update2(String stime,String etime,String sid,int banid,int bantypeid,String kqtime,String modifyreason,String userid,String downtime)throws Exception{
	   	 List param=new ArrayList();
	   	 StringBuffer sb=new StringBuffer();
	   	 Engine engine=null;
		 sb.append(" update  workattendanceex set Stafferid=?,Downtime=?,uptime=?,ifadd=?,kqtime=?,banid=?, ");
		 sb.append(" bantypeid=?,modifyreason=?,modifyuser=?,modifytime=getdate() ");
		 sb.append("  where CONVERT(VARCHAR(19),downtime,20)=? and stafferid=? ");
		 param.add(sid);
		 param.add(stime);
		 param.add(etime);
		 param.add(5);
		 param.add(kqtime);
		 param.add(banid);
		 param.add(bantypeid);
		 param.add(modifyreason);
		 param.add(userid);
		 param.add(downtime);
		 param.add(sid);
//System.out.println(sid+"+"+stime+"+"+etime+"+"+kqtime+"+"+banid+"+"+bantypeid+"+"+modifyreason+"+"+userid+"+"+downtime+"+"+sid);
	   	 try{
	   		// System.out.println("sbupdate="+sb);
	   		 engine=EngineFactory.getEngine("test");
	   		engine.executeSpecialSQL(sb.toString(),param.toArray());
	         engine.commit();
	   	 }catch(Exception e){
	   		 engine.rollback();
	   		 log.error(e);
	   		 throw e;
	   	 }finally{
	   		 engine.closeEngine();
	   	 }
	    }
	     
	//修改前增加
	public void update3(String stime,String etime,String sid,int banid,int bantypeid,String kqtime,String modifyreason,String userid,String downtime)throws Exception{
		List param=new ArrayList();
	   	 StringBuffer sb=new StringBuffer();
	   	 Engine engine=null;
		 sb.append(" insert into OldWorkattendanceEx(cardid,Stafferid,Downtime,uptime,CardState,DownCardreaderid,downlocatorid," +
		 		"UpCardreaderid,Uplocatorid,ifadd,kqtime,banid,bantypeid,modifyreason,modifyuser,modifytime) " +
		 		"values(?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?,getdate()) ");
		 param.add("");
		 param.add(sid);
		 param.add(stime);
		 param.add(etime);
		 param.add("0");
		 param.add("0");
		 param.add("0");
		 param.add("0");
		 param.add("0");
		 param.add("2");
		 param.add(stime);
		 param.add(banid);
		 param.add(bantypeid);
		 param.add(modifyreason);
		 param.add(userid);

	   	 try{
	   		 
	   		 engine=EngineFactory.getEngine("test");
	   		engine.executeSpecialSQL(sb.toString(),param.toArray());
	         engine.commit();
	   	 }catch(Exception e){
	   		 engine.rollback();
	   		 log.error(e);
	   		 throw e;
	   	 }finally{
	   		 engine.closeEngine();
	   	 }
	    }
	//修改前增加原始记录
	public void update4(String stime,String etime,String sid,int banid,int bantypeid,String kqtime,String modifyreason,String userid,String downtime,String uptime)throws Exception{
		List param=new ArrayList();
	   	 StringBuffer sb=new StringBuffer();
	   	 Engine engine=null;
		 sb.append(" insert into OldWorkattendanceEx(cardid,Stafferid,Downtime,uptime,CardState,DownCardreaderid,downlocatorid," +
		 		"UpCardreaderid,Uplocatorid,ifadd,kqtime,banid,bantypeid,modifyreason,modifyuser,modifytime) " +
		 		"values(?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?,getdate()) ");
		 param.add("");
		 param.add(sid);
		 param.add(downtime);
		 param.add(uptime);
		 param.add("0");
		 param.add("0");
		 param.add("0");
		 param.add("0");
		 param.add("0");
		 param.add("4");
		 param.add(downtime);
		 param.add(banid);
		 param.add(bantypeid);
		 param.add(modifyreason);
		 param.add(userid);

	   	 try{
	   		 
	   		 engine=EngineFactory.getEngine("test");
	   		engine.executeSpecialSQL(sb.toString(),param.toArray());
	         engine.commit();
	   	 }catch(Exception e){
	   		 engine.rollback();
	   		 log.error(e);
	   		 throw e;
	   	 }finally{
	   		 engine.closeEngine();
	   	 }
	    }
 	
 	//删除前插入
 	public void save4(Addcheck_log alinfo)throws Exception{
	   	 List param=new ArrayList();
	   	 StringBuffer sb=new StringBuffer();
	   	 Engine engine=null;
		 sb.append(" insert into OldWorkattendanceEx(cardid,Stafferid,Downtime,uptime,CardState,DownCardreaderid,downlocatorid," +
		 		"UpCardreaderid,Uplocatorid,ifadd,kqtime,banid,bantypeid,modifyreason,modifyuser,modifytime) " +
		 		"values(?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?,getdate()) ");
		 
		 param.add(alinfo.getCardid());
		 param.add(alinfo.getStafferid());
		 param.add(alinfo.getDowntime());
		 param.add(alinfo.getUptime());
		 param.add(alinfo.getCardstate());
		 param.add(alinfo.getDowncardreaderid());
		 param.add(alinfo.getDownlocatorid());
		 param.add(alinfo.getUpcardreaderid());
		 param.add(alinfo.getUplocatorid());
		 param.add(alinfo.getIfadd());
		 param.add(alinfo.getKqtime());
		 param.add(alinfo.getBanid());
		 param.add(alinfo.getBantypeid());
		 param.add(alinfo.getModifyreason());
		 param.add(alinfo.getModifyuser());

	   	 try{
	   		 
	   		 engine=EngineFactory.getEngine("test");
	   		engine.executeSpecialSQL(sb.toString(),param.toArray());
	         engine.commit();
	   	 }catch(Exception e){
	   		 engine.rollback();
	   		 log.error(e);
	   		 throw e;
	   	 }finally{
	   		 engine.closeEngine();
	   	 }
	    }
 	
	 /**
		 * 查询历史记录中是否有该定位器
		 */
	 public List ifcard(String cid)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select top 1 locatorid from v_locatedata where locatorid=? ");
		 param.add(cid);
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
          relist=query.getResults(sb.toString(),param.toArray(),Locator.class);
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
		 * 修改前查询基础数据返回
		 */
	 public List updateBefor(String stafferid,String downtime)throws Exception{
		 Engine engine=null;
		 List relist=null;
		 List param=new ArrayList();
		 StringBuffer sb=new StringBuffer();
		 sb.append(" select cardid,downtime,uptime,kqtime,bantypename,ban_name banname,modifyreason from WorkattendanceEx we   ");
		 sb.append(" left join mainbantype mbt on mbt.bantypeid=we.bantypeid left join bantype ban on ban.ban_id=we.banid ");
		 sb.append(" where CONVERT(VARCHAR(19),we.downtime,20) =? and stafferid=? ");
		 param.add(downtime);
		 param.add(stafferid);
		 try{
			 engine=EngineFactory.getEngine("test");
			 Query query=engine.getQuery();
			 relist=query.getResults(sb.toString(),param.toArray(),Addcheck_log.class);
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
      * LocatorBO
      * 删除
      */
     public void delete(String id,String dtime)throws Exception{
    	 Engine engine=null;
    	 StringBuffer sb=new StringBuffer();
  		 sb.append(" delete workattendanceex where stafferid=? and downtime=? ");
  		 List param=new ArrayList();
  		 param.add(id);
  		 param.add(dtime);
    	 try{
    		 engine=EngineFactory.getEngine("test");
    		 engine.executeSpecialSQL(sb.toString(),param.toArray());
    		 engine.commit();
    	 }catch(Exception e){
    		 engine.rollback();
    		 log.error(e);
    		 throw e;
    	 }finally{
    		 engine.closeEngine();
    	 }
      }
     
     /**
      * LocatorBO
      * 删除前的查找
      */
     public List deleteBeforeAdd(String id,String dtime)throws Exception{
    	 Engine engine=null;
    	 List relist=null;
    	 StringBuffer sb=new StringBuffer();
  		 sb.append(" select cardid,Stafferid,Downtime,uptime,CardState,DownCardreaderid,downlocatorid," +
		 		"UpCardreaderid,Uplocatorid,ifadd,kqtime,banid,bantypeid,modifyreason,modifyuser,modifytime from  workattendanceex where stafferid=? and downtime=? ");
  		 List param=new ArrayList();
  		 param.add(id);
  		 param.add(dtime);
    	 try{
    		 engine=EngineFactory.getEngine("test");
      		 Query query=engine.getQuery();
    		 relist=query.getResults(sb.toString(),param.toArray(),Addcheck_log.class);
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
      * LocatorBO
      * 提取单条数据
      */
     public Locator load(String id)throws Exception{
    	  Engine engine=null;
          Locator locator=null;
    	  try{
    		  engine=EngineFactory.getEngine("test");
    		  locator=(Locator)engine.load(Locator.class,id);
    		  engine.commit();
    	  }catch(Exception e){
    		  engine.rollback();
    		  log.error(e);
    		  throw e;
    	  }finally{
    		  engine.closeEngine();
    	  }
    	  return locator;
      }
     
     
      
     /**
      * LocatorBO
      * 执行特殊语句的修改
      **/
     public void executeSpecialSQL(String  sql,Object[] params)throws Exception{
    	   Engine engine=null;    	  
    	   try{
    		   engine=EngineFactory.getEngine("test");
    		   engine.executeSpecialSQL(sql,params);
    		   engine.commit();
    	   }catch(Exception e){
    		   engine.rollback();
    		   log.error(e);
    		   throw e;    	   
    		}finally{
    			engine.closeEngine();
    		}
       }
     
     /**
      * LocatorBO
      * 保存
      * */
    public void save(Locator locator)throws Exception {
    	Engine engine=null;
    	try{
    		engine=EngineFactory.getEngine("test");
    		engine.save(locator);
    		engine.commit();
    	}catch(Exception e){
    		engine.rollback();
    		log.error(e);
    		throw e;
    	}finally{
    		engine.closeEngine();
    	}
    }
     
}
