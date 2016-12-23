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
	 * ��վ��ʷ��ѯ
	 * */
	@SuppressWarnings("unchecked")
	public List init(Search_ViewReaderHistory viewreaderhistory,Pagination pagin)throws Exception{
		List relist=null;
	    List param = new ArrayList();
        Engine engine=null;
        String cid=viewreaderhistory.getHistory_cid();     
		String cname=viewreaderhistory.getHistory_cname();
        String stime=viewreaderhistory.getHistory_stime();//��ȡע��ʱ�����
		String etime=viewreaderhistory.getHistory_etime();    //��ȡע��ʱ��С��
		String minstime = viewreaderhistory.getMinstime();
		String maxstime = viewreaderhistory.getMaxstime();
		

        String sql=" select * from (select ��Ч���� as c_strat,��Чֹ�� as c_modifydate,��վ�� as c_cid,��վ�� as c_name, " 
        	+"   ��� as c_sname,��ͼX���� as c_x,��ͼY���� as c_y,ע��ʱ�� as c_regdate,��ͼ�� as c_mapid,�����ж�" 
        	+"  as c_ignoretimes,ָ����λ�� as c_ignorelocator,��λ������ as l_lname,ָ�������ж� as c_locatorignoretimes, "
        	+" ʹ��״̬ as c_state from viewreaderhistory ) as QueryTable where 1=1";
         
		
		if( cid!=null && !cid.equals("")){//��վ��
			Global  gobal=new Global();
		    List list=gobal.SuggestCardreaders(cid);
			if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
				SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
				sql +=  " and QueryTable.c_cid  like ?";
				param.add(vo.getCardreaderid()+"%");
			}else{//û�п��Ż��û���
				sql += "  and QueryTable.c_name like ?";
				param.add(cid);
			}
//			sql +=  " and QueryTable.c_cid  like ?";
//			param.add(cid+"%");
			
		}
//		if(cname!=null && !cname.equals("")){//��վ����
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
	        String stime=viewreaderhistory.getHistory_stime();//��ȡע��ʱ�����
			String etime=viewreaderhistory.getHistory_etime();    //��ȡע��ʱ��С��
			String minstime = viewreaderhistory.getMinstime();
			String maxstime = viewreaderhistory.getMaxstime();
			

	        String sql=" select * from (select ��Ч���� as c_strat,��Чֹ�� as c_modifydate,��վ�� as c_cid,��վ�� as c_name, " 
	        	+"   ��� as c_sname,��ͼX���� as c_x,��ͼY���� as c_y,ע��ʱ�� as c_regdate,��ͼ�� as c_mapid,�����ж�" 
	        	+"  as c_ignoretimes,ָ����λ�� as c_ignorelocator,��λ������ as l_lname,ָ�������ж� as c_locatorignoretimes, "
	        	+" ʹ��״̬ as c_state from viewreaderhistory ) as QueryTable where 1=1";
	         
	        if( cid!=null && !cid.equals("")){//��վ��
				Global  gobal=new Global();
			    List list=gobal.SuggestCardreaders(cid);
				if(list.size()>0){//�����û����õ����Ż���ֱ�Ӳ�ѯ����
					SuggestEmployeeVO vo=(SuggestEmployeeVO)list.get(0);
					sql +=  " and QueryTable.c_cid  like ?";
					param.add(vo.getCardreaderid()+"%");
				}else{//û�п��Ż��û���
					sql += "  and QueryTable.c_name like ?";
					param.add(cid);
				}
//				sql +=  " and QueryTable.c_cid  like ?";
//				param.add(cid+"%");
				
			}
//			if( cid!=null && !cid.equals("")){//��վ��
//				sql +=  " and QueryTable.c_cid  like ?";
//				param.add(cid+"%");
//				
//			}if(cname!=null && !cname.equals("")){//��վ����
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
/*		String sql=" select * from (select ��Ч���� as c_strat,��Чֹ�� as c_modifydate,��վ�� as c_cid,��վ�� as c_name, " 
			+"   ��� as c_sname,��ͼX���� as c_x,��ͼY���� as c_y,convert(varchar(100),ע��ʱ��,20) as c_regdate,��ͼ�� as c_mapid,�����ж�" 
			+"  as c_ignoretimes,ָ����λ�� as c_ignorelocator,��λ������ as l_lname,ָ�������ж� as c_locatorignoretimes, "
			+" ʹ��״̬ as c_state from viewreaderhistory ) as QueryTable where 1=1";
		String cid=viewreaderhistory.getHistory_cid();     
		String cname=viewreaderhistory.getHistory_cname();  
		
		String stime=viewreaderhistory.getHistory_stime();//��ȡע��ʱ�����
		if(stime==null ||stime.equals("") ){ 
			stime="2001-01-01 00:00:00"; 
		}
		String s1=stime.substring(0,10);  
		String s2=stime.substring(11,stime.length()); 
		
		String etime=viewreaderhistory.getHistory_etime();    //��ȡע��ʱ��С��
		if(etime==null || etime.equals("") ) {
			etime="2021-12-31 23:59:59";
		}
		String e1=etime.substring(0,10);  
		String e2=etime.substring(11,etime.length());  
		
		if( cid!=null && !cid.equals("")){//��վ��
			sql +=  " and QueryTable.c_cid  like ?";
			param.add(cid+"%");
			
		}if(cname!=null && !cname.equals("")){//��վ����
			sql += "  and QueryTable.c_name like ?";
			param.add(cname+"%");
		}if(stime!=null && !stime.equals("")){//ע��ʱ�����
			sql += "and QueryTable.c_regdate>= ? and  convert(char(8),QueryTable.c_regdate,8)>= ? ";
			param.add(s1);
			param.add(s2);
		}if(etime!=null && !etime.equals("")){//ע��ʱ��С��
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
