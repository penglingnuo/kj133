package com.kj133.entity.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.speedframework.engine.Engine;
import org.speedframework.engine.EngineFactory;
import org.speedframework.engine.Query;
import org.speedframework.entity.Pagination;

import com.kj133.entity.Search_ViewLocatorHistory;
import com.kj133.entity.vo.SuggestEmployeeVO;
import com.kj133.entity.vo.ViewLocatorHistoryVO;
import com.kj133.util.Global;

public class ViewLocatorHistoryBO {
	
    private final Logger log=Logger.getLogger(this.getClass());
	public ViewLocatorHistoryBO(){
		
	}
	/**
	 * 定位器历史查询
	 * */
	@SuppressWarnings("unchecked")
	public List getList(Search_ViewLocatorHistory loca_history,Pagination pagination)throws Exception{
		List list=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		String lid=loca_history.getLid();  
	    String lname=loca_history.getLname();  
	    String stime=loca_history.getStime(); 
	    String etime=loca_history.getEtime();
	    String minstime=loca_history.getMinstime();
		String maxstime=loca_history.getMaxstime();
	   	    
			
	    sb.append(" select * from (select 有效起日 as loca_startdate,有效止日 as loca_modifydate,  " 
	    		+"  定位器号 as loca_locatorid,定位器名 as loca_lname,简称 as loca_shortname, " 
	    		+"  地图X坐标 as loca_x,地图Y坐标 as loca_y,地图Z坐标 as loca_z,注册时间 as loca_regdate,地图号 as  " 
	    		+"  loca_mapid from viewlocatorhistory) as QueryTable where 1=1 ");
	      
	    
			if( lid!=null && !lid.equals("")){
				Global  gobal=new Global();
	 		    List list1=gobal.SuggestLocators(lid);
				if(list1.size()>0){//根据用户名得到卡号或者直接查询卡号
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
					sb.append(" and rtrim(loca_locatorid) like ? ");
					param.add(vo.getLocatorid()+"%");
				}else{//没有卡号或用户名
					sb.append(" and rtrim(loca_lname) like ? ");
					param.add(lid);
				}
//				sb.append(" and rtrim(loca_locatorid) like ? ");
//				param.add(lid+"%");
			}
//			if( lname!=null && !lname.equals("")){
//				sb.append(" and rtrim(loca_lname) like ?  ");
//				param.add(lname+"%");
//			}
			if( stime!=null && !stime.equals("")){
				sb.append(" and loca_regdate>= ?  ");
				param.add(stime);
				
			}if( minstime!=null && !minstime.equals("")){
				sb.append("  and  convert(char(8),loca_regdate,8)>= ? ");
				param.add(minstime);
			}if( etime!=null &&!etime.equals("")){
				sb.append(" and loca_regdate<= ?  ");
				param.add(etime);
				
			}if( maxstime!=null &&!maxstime.equals("")){
				sb.append("  and  convert(char(8),loca_regdate,8)<= ? ");
				
				param.add(maxstime);
			}
		try{
			
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
		    list=query.getResults(sb.toString(),param.toArray(),ViewLocatorHistoryVO.class,pagination);
		    engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;
		}finally{
			engine.closeEngine();
		}
		return list;
	}
	public List getListPrint(Search_ViewLocatorHistory loca_history)throws Exception{
		List list=null;
		List param=new ArrayList();
		Engine engine=null;
		StringBuffer sb=new StringBuffer();
		String lid=loca_history.getLid();  
	    String lname=loca_history.getLname();  
	    String stime=loca_history.getStime(); 
	    String etime=loca_history.getEtime();
	    String minstime=loca_history.getMinstime();
		String maxstime=loca_history.getMaxstime();
	   	    
			
	    sb.append(" select * from (select 有效起日 as loca_startdate,有效止日 as loca_modifydate,  " 
	    		+"  定位器号 as loca_locatorid,定位器名 as loca_lname,简称 as loca_shortname, " 
	    		+"  地图X坐标 as loca_x,地图Y坐标 as loca_y,地图Z坐标 as loca_z,注册时间 as loca_regdate,地图号 as  " 
	    		+"  loca_mapid from viewlocatorhistory) as QueryTable where 1=1 ");
	      
	    if( lid!=null && !lid.equals("")){
			Global  gobal=new Global();
 		    List list1=gobal.SuggestLocators(lid);
			if(list1.size()>0){//根据用户名得到卡号或者直接查询卡号
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
				sb.append(" and rtrim(loca_locatorid) like ? ");
				param.add(vo.getLocatorid()+"%");
			}else{//没有卡号或用户名
				sb.append(" and rtrim(loca_lname) like ? ");
				param.add(lid);
			}
//			sb.append(" and rtrim(loca_locatorid) like ? ");
//			param.add(lid+"%");
		}
//			if( lid!=null && !lid.equals("")){
//				sb.append(" and rtrim(loca_locatorid) like ? ");
//				param.add(lid+"%");
//			}if( lname!=null && !lname.equals("")){
//				sb.append(" and rtrim(loca_lname) like ?  ");
//				param.add(lname+"%");
//			}
			if( stime!=null && !stime.equals("")){
				sb.append(" and loca_regdate>= ?  ");
				param.add(stime);
				
			}if( minstime!=null && !minstime.equals("")){
				sb.append("  and  convert(char(8),loca_regdate,8)>= ? ");
				param.add(minstime);
			}if( etime!=null &&!etime.equals("")){
				sb.append(" and loca_regdate<= ?  ");
				param.add(etime);
				
			}if( maxstime!=null &&!maxstime.equals("")){
				sb.append("  and  convert(char(8),loca_regdate,8)<= ? ");
				
				param.add(maxstime);
			}
/*		sb.append(" select * from (select 有效起日 as loca_startdate,有效止日 as loca_modifydate,  " 
				+"  定位器号 as loca_locatorid,定位器名 as loca_lname,简称 as loca_shortname, " 
				+"  地图X坐标 as loca_x,地图Y坐标 as loca_y,convert(varchar(100),注册时间,20) as loca_regdate,地图号 as  " 
				+"  loca_mapid from viewlocatorhistory) as QueryTable where 1=1 ");
		String lid=loca_history.getLid();  
		String lname=loca_history.getLname();  
		
		String stime=loca_history.getStime(); 
		String s1=stime.substring(0,10);  
		String s2=stime.substring(11,stime.length()); 
		
		String etime=loca_history.getEtime();
		String e1=etime.substring(0,10); 
		String e2=etime.substring(11,etime.length());  
		
		if( lid!=null && !lid.equals("")){
			sb.append(" and rtrim(loca_locatorid) like ? ");
			param.add(lid+"%");
		}if( lname!=null && !lname.equals("")){
			sb.append(" and rtrim(loca_lname) like ?  ");
			param.add(lname+"%");
		}if( stime!=null && !stime.equals("")){
			sb.append(" and loca_regdate>= ? and  convert(char(8),loca_regdate,8)>= ? ");
			param.add(s1);
			param.add(s2);
		}if( etime!=null &&!etime.equals("")){
			sb.append(" and loca_regdate<= ? and  convert(char(8),loca_regdate,8)<= ? ");
			param.add(e1);
			param.add(e2);
		}*/
		try{
			
			engine=EngineFactory.getEngine("test");
			Query query=engine.getQuery();
			list=query.getResults(sb.toString(),param.toArray(),ViewLocatorHistoryVO.class);
			engine.commit();
		}catch(Exception e){
			engine.rollback();
			log.error(e);
			throw e;
		}finally{
			engine.closeEngine();
		}
		return list;
	}
}
