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
	 * ��λ����ʷ��ѯ
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
	   	    
			
	    sb.append(" select * from (select ��Ч���� as loca_startdate,��Чֹ�� as loca_modifydate,  " 
	    		+"  ��λ���� as loca_locatorid,��λ���� as loca_lname,��� as loca_shortname, " 
	    		+"  ��ͼX���� as loca_x,��ͼY���� as loca_y,��ͼZ���� as loca_z,ע��ʱ�� as loca_regdate,��ͼ�� as  " 
	    		+"  loca_mapid from viewlocatorhistory) as QueryTable where 1=1 ");
	      
	    
			if( lid!=null && !lid.equals("")){
				Global  gobal=new Global();
	 		    List list1=gobal.SuggestLocators(lid);
				if(list1.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
					sb.append(" and rtrim(loca_locatorid) like ? ");
					param.add(vo.getLocatorid()+"%");
				}else{//û�п��Ż��û���
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
	   	    
			
	    sb.append(" select * from (select ��Ч���� as loca_startdate,��Чֹ�� as loca_modifydate,  " 
	    		+"  ��λ���� as loca_locatorid,��λ���� as loca_lname,��� as loca_shortname, " 
	    		+"  ��ͼX���� as loca_x,��ͼY���� as loca_y,��ͼZ���� as loca_z,ע��ʱ�� as loca_regdate,��ͼ�� as  " 
	    		+"  loca_mapid from viewlocatorhistory) as QueryTable where 1=1 ");
	      
	    if( lid!=null && !lid.equals("")){
			Global  gobal=new Global();
 		    List list1=gobal.SuggestLocators(lid);
			if(list1.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list1.get(0);
				sb.append(" and rtrim(loca_locatorid) like ? ");
				param.add(vo.getLocatorid()+"%");
			}else{//û�п��Ż��û���
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
/*		sb.append(" select * from (select ��Ч���� as loca_startdate,��Чֹ�� as loca_modifydate,  " 
				+"  ��λ���� as loca_locatorid,��λ���� as loca_lname,��� as loca_shortname, " 
				+"  ��ͼX���� as loca_x,��ͼY���� as loca_y,convert(varchar(100),ע��ʱ��,20) as loca_regdate,��ͼ�� as  " 
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
