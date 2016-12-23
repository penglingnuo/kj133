package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;

import com.kj133.entity.BanType;
import com.kj133.entity.Search_BanType;

public class BantypeBO {
	
   private static final Logger log=Logger.getLogger(BantypeBO.class);
   public  BantypeBO(){
	   
   }
   
   public List getList()throws Exception {
	   List relist=null;
	   List param=new ArrayList();
	   Engine engine=null;
	   StringBuffer sb=new StringBuffer();
//	   sb.append(" select * from bantype ");
	   sb.append(" select convert(varchar,ban_id)+convert(varchar,bantypeid) lid,case when  isnull(bantypeid,0)=1 then '膨鎗崙' else '眉伊崙' end bd,* from BanType order by bantypeid,ban_ID ");
	   try{
		  engine=EngineFactory.getEngine("test");
		  Query query=engine.getQuery();
		  relist=query.getResults(sb.toString(),param.toArray(),BanType.class);
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
   public List getList1(String btid,String bid)throws Exception {
	   List relist=null;
	   List param=new ArrayList();
	   Engine engine=null;
	   StringBuffer sb=new StringBuffer();
//	   sb.append(" select * from bantype ");
	   sb.append(" select case when  isnull(bantypeid,0)=1 then '膨鎗崙' else '眉伊崙' end bd,* from BanType where bantypeid= ? and ban_id=? order by bantypeid,ban_ID ");
	   
	   param.add(btid);
	   param.add(bid);
	   
	   try{
		   engine=EngineFactory.getEngine("test");
		   Query query=engine.getQuery();
		   relist=query.getResults(sb.toString(),param.toArray(),BanType.class);
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
    * save
    * **/
   @SuppressWarnings("unchecked")
public void save(Search_BanType ban)throws Exception{
	   Engine engine=null;
	   List param=new ArrayList();
	   StringBuffer sb=new StringBuffer();
	   String h="",m="";
	   if( Integer.parseInt(ban.getStart_time_h())<10 ){//貧萎扮寂
		   h="0"+ban.getStart_time_h();
	   }else{
		   h=ban.getStart_time_h();
	   }
	   if( Integer.parseInt(ban.getStart_time_m()) < 10){
		   m="0"+ban.getStart_time_m();
	   }else{
		   m=ban.getStart_time_m();
	   }
	    String start_time=h+":"+m;
	   //！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
	   if( Integer.parseInt(ban.getStart_stime_h())<10){//蝕兵深輩
		   h="0"+ban.getStart_stime_h();
	   }else{
		   h=ban.getStart_stime_h();
	   }
	   if( Integer.parseInt(ban.getStart_stime_m()) < 10){
		   m="0"+ban.getStart_stime_m();
	   }else{
		   m=ban.getStart_stime_m();
	   }
	    String start_stime=h+":"+m;
	   
     //！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
	   if( Integer.parseInt(ban.getEnd_stime_h())<10){// 潤崩深輩
		   h="0"+ban.getEnd_stime_h();
	   }else{
		   h=ban.getEnd_stime_h();
	   }
	   if( Integer.parseInt(ban.getEnd_stime_m()) < 10){
		   m="0"+ban.getEnd_stime_m();
	   }else{
		   m=ban.getEnd_stime_m();
	   }
	   String end_stime=h+":"+m;
	  
	   //！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
	   if( Integer.parseInt(ban.getEnd_time_h())<10){// 和萎扮寂
		   h="0"+ban.getEnd_time_h();
	   }else{
		   h=ban.getEnd_time_h();
	   }
	   if( Integer.parseInt(ban.getEnd_time_m()) < 10){
		   m="0"+ban.getEnd_time_m();
	   }else{
		   m=ban.getEnd_time_m();
	   }
	   String end_time=h+":"+m;

	  
	   sb.append("insert into bantype(ban_name,start_time,end_time,qidao_time,zaotui_time,start_stime,end_stime ,add_time,start_xtime,end_xtime,ban_id,bantypeid)  values(?,?,?,?,?,?,?,?,?,?,?,?)");
//	   sb.append("insert into bantype(ban_name,start_time,end_time,qidao_time,zaotui_time,start_stime,end_stime ,add_time,start_xtime,end_xtime,ifupdate,ban_id,bantypeid)  values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
//	   sb.append(" insert into bantype(ban_name,start_time,end_time,qidao_time,zaotui_time,start_stime,end_stime ,add_time,ban_id,bantypeid)  values(?,?,?,?,?,?,?,?,?,?) ");
                                                                                                      
	   param.add(ban.getBan_name());
	   param.add(start_time);
	   param.add(end_time);
//	   param.add(ban.getQidao_time());
//	   param.add(ban.getZaotui_time());
	   param.add("30");
	   param.add("30");
	   param.add(start_stime);
	   param.add(end_stime);

	  // param.add(Start_xtime);
	  // param.add(end_xtime);
	   param.add(String.valueOf(ban.getAdd_time()));
	   
	   param.add("17:30");
	   param.add("18:30");
//	   param.add("10");
	   param.add(String.valueOf(ban.getBan_id()));
//	   System.out.println("-----------"+ban.getBan_id());
	   if(ban.getBd().equals("眉伊崙")){
		   
		   param.add(0);
		   
	   }if(ban.getBd().equals("膨鎗崙")){
		   param.add(1);
	   }
	   
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
    * delete
    */
   public void delete(String id,String sid)throws Exception{
	   Engine engine=null;
	   List param=new ArrayList();
	   StringBuffer sb=new StringBuffer();
	   sb.append(" delete from bantype where ban_id= ? and bantypeid = ? ");
	   param.add(id);
	   param.add(sid);
	   try{
		   engine=EngineFactory.getEngine("test");
//		   engine.executeSpecialSQL(" delete from bantype where ban_name = ? ",new Object[]{id});
//		   engine.executeSpecialSQL(" delete from bantype where convert(varchar,ban_id)+convert(varchar,bantypeid) = ? ",new Object[]{id});
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
     * load
     * */
    @SuppressWarnings("unchecked")
	public List load(String name)throws Exception {
    	List relist=null; 
        Engine engine=null;
        List param=new ArrayList();
        StringBuffer sb=new StringBuffer();
        sb.append(" select * from bantype where ban_name= ? ");
        param.add(name);
//        System.out.println("------"+name);
        try{
        	engine=EngineFactory.getEngine("test");
        	Query query=engine.getQuery();
            relist=query.getResults(sb.toString(),param.toArray(),BanType.class);
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
     * load
     * */
    @SuppressWarnings("unchecked")
    public List load1(String btid,String bid)throws Exception {
    	List relist=null; 
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer sb=new StringBuffer();
    	sb.append(" select * from bantype where bantypeid= ? and ban_id=? ");
//    	sb.append(" select * from bantype where ban_name= ? ");
    	param.add(btid);
    	param.add(bid);
    	
//        System.out.println("------"+name);
    	try{
    		engine=EngineFactory.getEngine("test");
    		Query query=engine.getQuery();
    		relist=query.getResults(sb.toString(),param.toArray(),BanType.class);
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
     * update
     * */
    
    @SuppressWarnings("unchecked")
	public void update(Search_BanType ban,String btid,String bid)throws Exception{
    	Engine engine=null;
    	List param=new ArrayList();
    	StringBuffer sb=new StringBuffer();
    	
//    	System.out.println("n------"+ban.getBan_name());
    	 String h="",m="";
  	   if( Integer.parseInt(ban.getStart_time_h())<10 ){//貧萎扮寂
  		   h="0"+ban.getStart_time_h();
  	   }else{
  		   h=ban.getStart_time_h();
  	   }
  	   if( Integer.parseInt(ban.getStart_time_m()) < 10){
  		   m="0"+ban.getStart_time_m();
  	   }else{
  		   m=ban.getStart_time_m();
  	   }
  	    String start_time=h+":"+m;
  	   //！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
  	   if( Integer.parseInt(ban.getStart_stime_h())<10){//蝕兵深輩
  		   h="0"+ban.getStart_stime_h();
  	   }else{
  		   h=ban.getStart_stime_h();
  	   }
  	   if( Integer.parseInt(ban.getStart_stime_m()) < 10){
  		   m="0"+ban.getStart_stime_m();
  	   }else{
  		   m=ban.getStart_stime_m();
  	   }
  	    String start_stime=h+":"+m;
  	   
       //！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
  	   if( Integer.parseInt(ban.getEnd_stime_h())<10){// 潤崩深輩
  		   h="0"+ban.getEnd_stime_h();
  	   }else{
  		   h=ban.getEnd_stime_h();
  	   }
  	   if( Integer.parseInt(ban.getEnd_stime_m()) < 10){
  		   m="0"+ban.getEnd_stime_m();
  	   }else{
  		   m=ban.getEnd_stime_m();
  	   }
  	   String end_stime=h+":"+m;
  	  
  	   //！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
  	   if( Integer.parseInt(ban.getEnd_time_h())<10){// 和萎扮寂
  		   h="0"+ban.getEnd_time_h();
  	   }else{
  		   h=ban.getEnd_time_h();
  	   }
  	   if( Integer.parseInt(ban.getEnd_time_m()) < 10){
  		   m="0"+ban.getEnd_time_m();
  	   }else{
  		   m=ban.getEnd_time_m();
  	   }
  	  String end_time=h+":"+m;
  	   //！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
  	   /*if( Integer.parseInt(ban.getStart_xtime_h())<10){// 和萎蝕兵深輩
  		   h="0"+ban.getStart_xtime_h();  
  	   }else{
  		   h=ban.getStart_xtime_h();
  	   }
  	   if( Integer.parseInt(ban.getStart_xtime_m()) < 10){
  		   m="0"+ban.getStart_xtime_m();
  	   }else{
  		   m=ban.getStart_xtime_m();
  	   }
  	   String Start_xtime=h+":"+m;*/
  	    
  	   //！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
  	  /* if( Integer.parseInt(ban.getEnd_xtime_h())<10){// 和萎潤崩深輩
  		   h="0"+ban.getEnd_xtime_h();
  	   }else{
  		   h=ban.getEnd_xtime_h();
  	   }
  	   if( Integer.parseInt(ban.getEnd_xtime_m()) < 10){
  		   m="0"+ban.getEnd_xtime_m();
  	   }else{
  		   m=ban.getEnd_xtime_m();
  	   }
  	   String end_xtime=h+":"+m;*/
//       sb.append(" update bantype set  ban_name = ?,start_time = ?,end_time = ?,qidao_time = ?,zaotui_time = ?, ");
//       sb.append(" start_stime = ?, end_stime = ?,add_time = ?,ban_id = ?,bantypeid=? where ban_name = ? ");
       
       sb.append(" update bantype set  ban_name = ?,start_time = ?,end_time = ?, ");
       sb.append(" start_stime = ?, end_stime = ?,add_time = ?,ban_id = ?,bantypeid=? where bantypeid= ? and ban_id=? ");
//       sb.append(" start_stime = ?, end_stime = ?,add_time = ?,ban_id = ?,bantypeid=? where ban_id=? and  bantypeid=? ");
       param.add(ban.getBan_name());
//       System.out.println("----------ban.getBan_name()-------"+ban.getBan_name());
   	   param.add(start_time);
   	   param.add(end_time);
//   	   param.add(ban.getQidao_time());
//   	   param.add(ban.getZaotui_time());
   	   param.add(start_stime);
   	   param.add(end_stime);
   	  // param.add(Start_xtime);
   	   //param.add(end_xtime);
   	   param.add(String.valueOf(ban.getAdd_time()));
   	   
   	   param.add(ban.getBan_id());
   	   if(ban.getBd().equals("眉伊崙")){
   		   
   		   param.add(0);
	   }else{
		   param.add(1);
	   }
   	param.add(btid);
	param.add(bid);
    	try{
//    	    System.out.println("sb.toString=="+sb.toString());	
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
}
