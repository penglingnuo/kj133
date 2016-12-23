package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_ViewReaderHistory;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.entity.vo.ViewReaderHistoryVO;
import com.kj133.util.Global;

public class ViewReaderHistoryBO {
     
	private final Logger log=Logger.getLogger(this.getClass());
	public  ViewReaderHistoryBO(){
    	 
     }
	
	/**
	 * 分站历史查询
	 * */
	@SuppressWarnings("unchecked")
	public List init(Search_ViewReaderHistory viewreaderhistory,Pagination pagin)throws Exception{
		List relist=null;
	    List param = new ArrayList();
        Engine engine=null;
        String cid=viewreaderhistory.getHistory_cid();     
		String cname=viewreaderhistory.getHistory_cname();
        String stime=viewreaderhistory.getHistory_stime();//截取注册时间大于
		String etime=viewreaderhistory.getHistory_etime();    //截取注册时间小于
		String minstime = viewreaderhistory.getMinstime();
		String maxstime = viewreaderhistory.getMaxstime();
		

        String sql=" select * from (select 有效起日 as c_strat,有效止日 as c_modifydate,分站号 as c_cid,分站名 as c_name, " 
        	+"   简称 as c_sname,地图X坐标 as c_x,地图Y坐标 as c_y,注册时间 as c_regdate,地图号 as c_mapid,忽略中断" 
        	+"  as c_ignoretimes,指定定位器 as c_ignorelocator,定位器名称 as l_lname,指定忽略中断 as c_locatorignoretimes, "
        	+" 使用状态 as c_state from viewreaderhistory ) as QueryTable where 1=1";
         
		
		if( cid!=null && !cid.equals("")){//分站号
			Global  gobal=new Global();
		    List list=gobal.SuggestCardreaders(cid);
			if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sql +=  " and QueryTable.c_cid  like ?";
				param.add(vo.getCardreaderid()+"%");
			}else{//没有卡号或用户名
				sql += "  and QueryTable.c_name like ?";
				param.add(cid);
			}
//			sql +=  " and QueryTable.c_cid  like ?";
//			param.add(cid+"%");
			
		}
//		if(cname!=null && !cname.equals("")){//分站名称
//			sql += "  and QueryTable.c_name like ?";
//			param.add(cname+"%");
//		}
		if( stime!=null && !stime.equals("")){
			sql += " and QueryTable.c_regdate>= ?  ";
			param.add(stime);
			
		}if( minstime!=null && !minstime.equals("")){
			sql += "  and  convert(char(8),QueryTable.c_regdate,8)>= ? ";
			param.add(minstime);
		}if( etime!=null &&!etime.equals("")){
			sql += " and QueryTable.c_regdate<= ?  ";
			param.add(etime);
			
		}if( maxstime!=null &&!maxstime.equals("")){
			sql +="  and  convert(char(8),QueryTable.c_regdate,8)<=? ";
			
			param.add(maxstime);
		}
		
		
        try{
        	engine=EngineFactory.getEngine("test");
        	Query query=engine.getQuery();
        	relist=query.getResults(sql,param.toArray(),ViewReaderHistoryVO.class,pagin);
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
	public List initPrint(Search_ViewReaderHistory viewreaderhistory)throws Exception{
		List relist=null;
		List param = new ArrayList();
		Engine engine=null;
	       String cid=viewreaderhistory.getHistory_cid();     
			String cname=viewreaderhistory.getHistory_cname();
	        String stime=viewreaderhistory.getHistory_stime();//截取注册时间大于
			String etime=viewreaderhistory.getHistory_etime();    //截取注册时间小于
			String minstime = viewreaderhistory.getMinstime();
			String maxstime = viewreaderhistory.getMaxstime();
			

	        String sql=" select * from (select 有效起日 as c_strat,有效止日 as c_modifydate,分站号 as c_cid,分站名 as c_name, " 
	        	+"   简称 as c_sname,地图X坐标 as c_x,地图Y坐标 as c_y,注册时间 as c_regdate,地图号 as c_mapid,忽略中断" 
	        	+"  as c_ignoretimes,指定定位器 as c_ignorelocator,定位器名称 as l_lname,指定忽略中断 as c_locatorignoretimes, "
	        	+" 使用状态 as c_state from viewreaderhistory ) as QueryTable where 1=1";
	         
	        if( cid!=null && !cid.equals("")){//分站号
				Global  gobal=new Global();
			    List list=gobal.SuggestCardreaders(cid);
				if(list.size()>0){//根据用户名得到卡号或者直接查询卡号
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
					sql +=  " and QueryTable.c_cid  like ?";
					param.add(vo.getCardreaderid()+"%");
				}else{//没有卡号或用户名
					sql += "  and QueryTable.c_name like ?";
					param.add(cid);
				}
//				sql +=  " and QueryTable.c_cid  like ?";
//				param.add(cid+"%");
				
			}
//			if( cid!=null && !cid.equals("")){//分站号
//				sql +=  " and QueryTable.c_cid  like ?";
//				param.add(cid+"%");
//				
//			}if(cname!=null && !cname.equals("")){//分站名称
//				sql += "  and QueryTable.c_name like ?";
//				param.add(cname+"%");
//			}
			if( stime!=null && !stime.equals("")){
				sql += " and QueryTable.c_regdate>= ?  ";
				param.add(stime);
				
			}if( minstime!=null && !minstime.equals("")){
				sql += "  and  convert(char(8),QueryTable.c_regdate,8)>= ? ";
				param.add(minstime);
			}if( etime!=null &&!etime.equals("")){
				sql += " and QueryTable.c_regdate<= ?  ";
				param.add(etime);
				
			}if( maxstime!=null &&!maxstime.equals("")){
				sql +="  and  convert(char(8),QueryTable.c_regdate,8)<=? ";
				
				param.add(maxstime);
			}
/*		String sql=" select * from (select 有效起日 as c_strat,有效止日 as c_modifydate,分站号 as c_cid,分站名 as c_name, " 
			+"   简称 as c_sname,地图X坐标 as c_x,地图Y坐标 as c_y,convert(varchar(100),注册时间,20) as c_regdate,地图号 as c_mapid,忽略中断" 
			+"  as c_ignoretimes,指定定位器 as c_ignorelocator,定位器名称 as l_lname,指定忽略中断 as c_locatorignoretimes, "
			+" 使用状态 as c_state from viewreaderhistory ) as QueryTable where 1=1";
		String cid=viewreaderhistory.getHistory_cid();     
		String cname=viewreaderhistory.getHistory_cname();  
		
		String stime=viewreaderhistory.getHistory_stime();//截取注册时间大于
		if(stime==null ||stime.equals("") ){ 
			stime="2001-01-01 00:00:00"; 
		}
		String s1=stime.substring(0,10);  
		String s2=stime.substring(11,stime.length()); 
		
		String etime=viewreaderhistory.getHistory_etime();    //截取注册时间小于
		if(etime==null || etime.equals("") ) {
			etime="2021-12-31 23:59:59";
		}
		String e1=etime.substring(0,10);  
		String e2=etime.substring(11,etime.length());  
		
		if( cid!=null && !cid.equals("")){//分站号
			sql +=  " and QueryTable.c_cid  like ?";
			param.add(cid+"%");
			
		}if(cname!=null && !cname.equals("")){//分站名称
			sql += "  and QueryTable.c_name like ?";
			param.add(cname+"%");
		}if(stime!=null && !stime.equals("")){//注册时间大于
			sql += "and QueryTable.c_regdate>= ? and  convert(char(8),QueryTable.c_regdate,8)>= ? ";
			param.add(s1);
			param.add(s2);
		}if(etime!=null && !etime.equals("")){//注册时间小于
			sql += " and QueryTable.c_regdate<= ? and  convert(char(8),QueryTable.c_regdate,8)<=?  ";
			param.add(e1);
			param.add(e2);
		}*/
		try{
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			relist=query.getResults(sql,param.toArray(),ViewReaderHistoryVO.class);
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
